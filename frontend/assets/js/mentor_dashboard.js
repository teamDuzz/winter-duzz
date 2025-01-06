async function loadDashboard() {
  const dashboardContent = document.getElementById('dashboard-content');
  const dashboardTitle = document.querySelector('h1'); // 대시보드 제목

  try {
      // 로컬 스토리지에서 사용자 정보 가져오기
      const userInfo = JSON.parse(localStorage.getItem('userInfo'));
      if (userInfo && userInfo.name) {
          dashboardTitle.textContent = `${userInfo.name}님의 대시보드`;
      } else {
          dashboardTitle.textContent = '대시보드'; // 이름이 없으면 기본값
      }

      const state = await getState(); // state.js의 함수 호출

      // 상태별 클래스와 콘텐츠 추가
      if (state === 0) {
          // 상태: 정보 입력 기간
          dashboardContent.innerHTML = `
            <div class="state-message input-class">
              <p>현재는 멘토 매칭 정보 입력 기간입니다.</p>
              <p>멘티 정보를 입력해주세요.</p>
            </div>
          `;
      } else if (state === 1) {
          // 상태: 매칭 중
          dashboardContent.innerHTML = `
            <div class="state-message matching-class">
              <p>현재는 매칭 중입니다.</p>
              <p>매칭 결과를 기다려주세요.</p>
            </div>
          `;
      } else if (state === 2) {
          // 상태: 매칭 완료
          dashboardContent.innerHTML = `
            <div class="state-message completed-class">
              <h2>매칭된 멘티 정보</h2>
              <div class="mentee-tile">
                <h3>멘티 이름</h3>
                <p>학과: 컴퓨터공학과</p>
                <p>학년: 1학년</p>
                <p>이메일: mentee@example.com</p>
              </div>
            </div>
          `;
      } else {
          // 상태를 불러오지 못한 경우
          dashboardContent.innerHTML = `
            <div class="state-message error-class">
              <p>상태를 불러오는 데 실패했습니다. 다시 시도해주세요.</p>
            </div>
          `;
      }
  } catch (error) {
      console.error("Error loading dashboard:", error);
      dashboardContent.innerHTML = `
        <div class="state-message error-class">
          <p>오류가 발생했습니다. 관리자에게 문의하세요.</p>
        </div>
      `;
  }
}

// DOMContentLoaded 이벤트에서 대시보드 로드
document.addEventListener('DOMContentLoaded', loadDashboard);
