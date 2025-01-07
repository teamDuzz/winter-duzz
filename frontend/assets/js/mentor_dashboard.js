async function loadDashboard() {
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

      if (state === 0) {
          dashboardContent.innerHTML = `
            <div class="state-message input-class">
              <p>현재는 멘토 매칭 정보 입력 기간입니다.</p>
              <p>멘티 정보를 입력해주세요.</p>
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
          const menteeIds = userInfo.menteeIds || [];
          if (menteeIds.length === 0) {
              dashboardContent.innerHTML = `
                <div class="state-message completed-class">
                  <h2>매칭된 멘티 정보</h2>
                  <p>매칭된 멘티가 없습니다.</p>
                </div>
              `;
              return;
          }
          dashboardContent.innerHTML = `<h2>매칭된 멘티 정보</h2>`;
          const menteeContainer = document.createElement('div');
          menteeContainer.classList.add('mentee-list');

          for (const menteeId of menteeIds) {
              try {
                  const response = await fetch(`${API_BASE_URL}${menteeId}`);
                  if (!response.ok) {
                      throw new Error(`Failed to fetch mentee info for ID: ${menteeId}`);
                  }

                  const menteeInfo = await response.json();
                  const menteeTile = document.createElement('div');
                  menteeTile.classList.add('mentee-tile');

                  menteeTile.innerHTML = `
                    <h3>${menteeInfo.name}</h3>
                    <p>학과: ${menteeInfo.major}</p>
                    <p>이메일: ${menteeInfo.email}</p>
                    <p>전화번호: ${menteeInfo.phone}</p>
                    <p>관심 분야: ${menteeInfo.interest || '없음'}</p>
                  `;

                  menteeContainer.appendChild(menteeTile);
              } catch (error) {
                  console.error(`Error fetching mentee data for ID: ${menteeId}`, error);
              }
          }

          dashboardContent.appendChild(menteeContainer);
      } else {
          dashboardContent.innerHTML = `
            <div class="state-message error-class">
              <p>상태를 불러오는 데 실패했습니다. 다시 시도해주세요.</p>
            </div>
          `;
      }
  } catch (error) {
      console.error('Error loading dashboard:', error);
      dashboardContent.innerHTML = `
        <div class="state-message error-class">
          <p>오류가 발생했습니다. 관리자에게 문의하세요.</p>
        </div>
      `;
  }
}

document.addEventListener('DOMContentLoaded', loadDashboard);
