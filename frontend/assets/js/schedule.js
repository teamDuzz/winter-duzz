document.addEventListener('DOMContentLoaded', async function () {
  const API_BASE_URL = 'http://anacnu.kr:9027';
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

  async function fetchSubjectDetails(subjectId) {
      try {
          const response = await fetch(`${API_BASE_URL}/subject/list?keyword=${encodeURIComponent(subjectId)}&page=0`);
          if (!response.ok) {
              throw new Error(`Failed to fetch details for subject ID: ${subjectId}`);
          }
          const data = await response.json();
          return data.content[0] || null;
      } catch (error) {
          console.error('Error fetching subject details:', error);
          return null;
      }
  }

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

  async function renderSubjects() {
      subjectsListElement.innerHTML = '';
      let totalCredits = 0;
      const subjects = await updateUserInfo();

      console.log('Rendering subjects:', subjects);

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
      console.log(`Subjects rendered. Total credits: ${totalCredits}`);
  }

  async function deleteSubject(subjectId) {
      try {
          const deleteUrl = `${API_BASE_URL}/schedule/delete/${subjectId}`;
          console.log(`Deleting subject with ID: ${subjectId}`);

          const response = await fetch(deleteUrl, {
              method: 'POST',
              headers: { 'Authorization': `Bearer ${accessToken}` }
          });

          if (!response.ok) {
              console.error('Delete subject failed:', response);
              throw new Error('Failed to delete subject.');
          }

          console.log(`Subject ${subjectId} deleted successfully.`);

          alert('수강과목을 성공적으로 삭제했습니다.');

          // 렌더링 갱신
          renderSubjects();
      } catch (error) {
          console.error('Error deleting subject:', error);
          alert('과목 삭제에 실패했습니다.');
      }
  }

  searchButton.addEventListener('click', async () => {
      const keyword = searchInput.value.trim();
      if (!keyword) {
          console.warn('No search keyword entered.');
          alert('검색어를 입력하세요.');
          return;
      }

      modalResults.innerHTML = '<p>검색 중...</p>';
      modal.classList.remove('hidden');

      try {
          const searchUrl = `${API_BASE_URL}/subject/list?keyword=${encodeURIComponent(keyword)}&page=0`;
          console.log(`Search initiated with URL: ${searchUrl}`);

          const response = await fetch(searchUrl);
          console.log(`Search response status: ${response.status}`);

          if (!response.ok) {
              throw new Error('Failed to fetch search results.');
          }

          const data = await response.json();
          console.log('Search results data:', data);

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
                  console.log(`Selected subject: ${selectedSubject}`);
              });

              modalResults.appendChild(item);
          });
      } catch (error) {
          console.error('Error during search:', error);
          modalResults.innerHTML = '<p>검색 실패. 다시 시도해주세요.</p>';
      }
  });

  addButton.addEventListener('click', async () => {
      if (!selectedSubject) {
          alert('과목을 선택해주세요.');
          return;
      }

      try {
          const addUrl = `${API_BASE_URL}/schedule/add/${selectedSubject}`;
          console.log(`Attempting to add subject with URL: ${addUrl}`);
          console.log('Access Token:', accessToken);

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

          renderSubjects();
      } catch (error) {
          console.error('Error adding subject:', error);
          alert('과목 추가에 실패했습니다. 다시 시도해주세요.');
      }
  });

  closeModalButton.addEventListener('click', () => {
      modal.classList.add('hidden');
      selectedSubject = null;
      addButton.disabled = true;
  });

  subjectsListElement.addEventListener('click', (event) => {
      if (event.target.classList.contains('delete-button')) {
          const subjectId = event.target.dataset.id;
          console.log(`Delete button clicked for subject ID: ${subjectId}`);
          deleteSubject(subjectId);
      }
  });

  renderSubjects();
});
