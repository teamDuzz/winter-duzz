async function loadMenteeDashboard() {
  const dashboardContent = document.getElementById('dashboard-content');
  const dashboardTitle = document.querySelector('h1');
  const API_BASE_URL = 'http://anacnu.kr:9027/member/';

  try {
      const userInfo = JSON.parse(localStorage.getItem('userInfo'));
      if (userInfo && userInfo.name) {
          dashboardTitle.textContent = `${userInfo.name}님의 대시보드`;
      } else {
          dashboardTitle.textContent = '대시보드';
      }

      const state = await getState();
      console.log('Current state:', state);

      if (state === 0) {

          dashboardContent.innerHTML = `
            <div class="state-message input-class">
              <p>현재는 멘토 매칭 정보 입력 기간입니다.</p>
              <p>멘토 정보를 확인할 수 없습니다.</p>
            </div>
          `;
      } else if (state === 1) {

          dashboardContent.innerHTML = `
            <div class="state-message matching-class">
              <p>현재는 매칭 중입니다.</p>
              <p>매칭 결과를 기다려주세요.</p>
            </div>
          `;
      } else if (state === 2) {
          const mentorId = userInfo.mentorId;
          if (!mentorId) {
              dashboardContent.innerHTML = `
                <div class="state-message error-class">
                  <p>연결된 멘토가 없습니다.</p>
                </div>
              `;
              return;
          }

          const response = await fetch(`${API_BASE_URL}${mentorId}`);
          if (!response.ok) {
              throw new Error(`Failed to fetch mentor info for ID: ${mentorId}`);
          }

          const mentorInfo = await response.json();

          dashboardContent.innerHTML = `
            <div class="mentor-info">
              <h2>연결된 멘토 정보</h2>
              <div class="mentor-tile">
                <h3>${mentorInfo.name} 멘토님</h3>
                <p>학과: ${mentorInfo.major}</p>
                <p>이메일: ${mentorInfo.email}</p>
                <p>전화번호: ${mentorInfo.phone}</p>
              </div>
            </div>
          `;
      } else {
          dashboardContent.innerHTML = `
            <div class="state-message error-class">
              <p>상태를 불러오는 데 실패했습니다. 다시 시도해주세요.</p>
            </div>
          `;
      }
  } catch (error) {
      console.error('Error loading mentee dashboard:', error);
      dashboardContent.innerHTML = `
        <div class="state-message error-class">
          <p>오류가 발생했습니다. 관리자에게 문의하세요.</p>
        </div>
      `;
  }
}

document.addEventListener('DOMContentLoaded', loadMenteeDashboard);
