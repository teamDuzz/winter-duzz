document.addEventListener('DOMContentLoaded', async function () {
  const API_BASE_URL = 'http://59.29.157.89:8080';
  const accessToken = localStorage.getItem('accessToken') || '';
  const totalCreditsElement = document.getElementById('total-credits');
  const subjectsListElement = document.getElementById('subjects-list');
  const modal = document.getElementById('modal');
  const modalResults = document.getElementById('modal-results');
  const addButton = document.getElementById('add-button');
  const closeModalButton = document.getElementById('close-modal');
  const searchButton = document.getElementById('search-button');
  const searchInput = document.getElementById('subject-search');
  let selectedSubject = null;

  /**
   * 개별 과목 정보 가져오기
   */
  async function fetchSubjectDetails(subjectId) {
      try {
          const response = await fetch(`${API_BASE_URL}/subject/list?keyword=${encodeURIComponent(subjectId)}&page=0`);
          if (!response.ok) {
              throw new Error(`Failed to fetch details for subject ID: ${subjectId}`);
          }
          const data = await response.json();
          return data.content[0] || null; // 첫 번째 결과 반환
      } catch (error) {
          console.error('Error fetching subject details:', error);
          return null;
      }
  }

  /**
   * /me 호출로 유저 정보 갱신
   */
  async function updateUserInfo() {
      try {
          const response = await fetch(`${API_BASE_URL}/member/me`, {
              headers: { 'Authorization': `Bearer ${accessToken}` }
          });
          if (!response.ok) {
              throw new Error('Failed to fetch user info.');
          }
          const updatedUserInfo = await response.json();
          localStorage.setItem('userInfo', JSON.stringify(updatedUserInfo));
          return updatedUserInfo.subjects || [];
      } catch (error) {
          console.error('Error updating user info:', error);
          return [];
      }
  }

  /**
   * 과목 렌더링
   */
  async function renderSubjects() {
      subjectsListElement.innerHTML = '';
      let totalCredits = 0;
      const subjects = await updateUserInfo(); // 업데이트된 subjects 가져오기

      console.log('Rendering subjects:', subjects); // 디버깅 로그

      for (const subjectId of subjects) {
          const subject = await fetchSubjectDetails(subjectId);
          if (!subject) {
              console.warn(`No details found for subject ID: ${subjectId}`);
              continue;
          }

          totalCredits += subject.credit;

          const subjectCard = document.createElement('div');
          subjectCard.className = 'subject-card';

          subjectCard.innerHTML = `
              <h3>${subject.name} (${subject.id})</h3>
              <p>교수: ${subject.professor}</p>
              <p>시간: ${subject.time.join(', ')}</p>
              <p>학점: ${subject.credit}</p>
              <button class="delete-button" data-id="${subject.id}">삭제</button>
          `;

          subjectsListElement.appendChild(subjectCard);
      }

      totalCreditsElement.textContent = `총 학점: ${totalCredits}`;
      console.log(`Subjects rendered. Total credits: ${totalCredits}`); // 디버깅 로그
  }

  /**
   * 과목 삭제
   */
  async function deleteSubject(subjectId) {
      try {
          const deleteUrl = `${API_BASE_URL}/schedule/delete/${subjectId}`;
          console.log(`Deleting subject with ID: ${subjectId}`); // 디버깅 로그

          const response = await fetch(deleteUrl, {
              method: 'POST',
              headers: { 'Authorization': `Bearer ${accessToken}` }
          });

          if (!response.ok) {
              console.error('Delete subject failed:', response); // 디버깅 로그
              throw new Error('Failed to delete subject.');
          }

          console.log(`Subject ${subjectId} deleted successfully.`); // 디버깅 로그

          alert('수강과목을 성공적으로 삭제했습니다.');

          // 렌더링 갱신
          renderSubjects();
      } catch (error) {
          console.error('Error deleting subject:', error); // 디버깅 로그
          alert('과목 삭제에 실패했습니다.');
      }
  }

  /**
   * 검색 기능
   */
  searchButton.addEventListener('click', async () => {
      const keyword = searchInput.value.trim();
      if (!keyword) {
          console.warn('No search keyword entered.'); // 디버깅 로그
          alert('검색어를 입력하세요.');
          return;
      }

      modalResults.innerHTML = '<p>검색 중...</p>';
      modal.classList.remove('hidden');

      try {
          const searchUrl = `${API_BASE_URL}/subject/list?keyword=${encodeURIComponent(keyword)}&page=0`;
          console.log(`Search initiated with URL: ${searchUrl}`); // 디버깅: 검색 API URL 확인

          const response = await fetch(searchUrl);
          console.log(`Search response status: ${response.status}`); // 디버깅: 응답 상태 확인

          if (!response.ok) {
              throw new Error('Failed to fetch search results.');
          }

          const data = await response.json();
          console.log('Search results data:', data); // 디버깅: 응답 데이터 출력

          if (!data.content || data.content.length === 0) {
              modalResults.innerHTML = '<p>검색 결과가 없습니다.</p>';
              return;
          }

          modalResults.innerHTML = '';
          data.content.forEach(subject => {
              const item = document.createElement('div');
              item.className = 'result-item';
              item.innerHTML = `
                  <h3>${subject.name} (${subject.id})</h3>
                  <p>교수: ${subject.professor}</p>
                  <p>학과: ${subject.major}</p>
                  <p>학점: ${subject.credit}</p>
                  <p>시간: ${subject.time.join(', ')}</p>
              `;
              item.dataset.id = subject.id;

              item.addEventListener('click', () => {
                  document.querySelectorAll('.result-item').forEach(el => el.classList.remove('selected'));
                  item.classList.add('selected');
                  selectedSubject = subject.id;
                  addButton.disabled = false;
                  console.log(`Selected subject: ${selectedSubject}`); // 디버깅: 선택된 과목 ID
              });

              modalResults.appendChild(item);
          });
      } catch (error) {
          console.error('Error during search:', error); // 디버깅: 에러 상세 출력
          modalResults.innerHTML = '<p>검색 실패. 다시 시도해주세요.</p>';
      }
  });

  /**
   * 과목 추가
   */
  addButton.addEventListener('click', async () => {
      if (!selectedSubject) {
          alert('과목을 선택해주세요.');
          return;
      }

      try {
          const addUrl = `${API_BASE_URL}/schedule/add/${selectedSubject}`;
          console.log(`Attempting to add subject with URL: ${addUrl}`);
          console.log('Access Token:', accessToken); // 디버깅용 Access Token 출력

          const response = await fetch(addUrl, {
              method: 'POST',
              headers: {
                  'Content-Type': 'application/json',
                  'Authorization': `Bearer ${accessToken}`
              }
          });

          if (!response.ok) {
              throw new Error('Failed to add subject.');
          }

          alert('과목이 성공적으로 추가되었습니다.');
          modal.classList.add('hidden');
          selectedSubject = null;
          addButton.disabled = true;

          // 렌더링 갱신
          renderSubjects();
      } catch (error) {
          console.error('Error adding subject:', error);
          alert('과목 추가에 실패했습니다. 다시 시도해주세요.');
      }
  });

  /**
   * 모달 닫기
   */
  closeModalButton.addEventListener('click', () => {
      modal.classList.add('hidden');
      selectedSubject = null;
      addButton.disabled = true;
  });

  /**
   * 삭제 버튼 이벤트
   */
  subjectsListElement.addEventListener('click', (event) => {
      if (event.target.classList.contains('delete-button')) {
          const subjectId = event.target.dataset.id;
          console.log(`Delete button clicked for subject ID: ${subjectId}`); // 디버깅 로그
          deleteSubject(subjectId);
      }
  });

  // 초기 렌더링
  renderSubjects();
});
