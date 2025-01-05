document.addEventListener('DOMContentLoaded', () => {
  const API_BASE_URL = 'http://59.29.157.89:8080'; //벡엔드 서버 주소 변경 가능

  document.getElementById('change-state-form').addEventListener('submit', async function (event) {
      event.preventDefault();

      const state = document.getElementById('state-value').value;
      const url = `${API_BASE_URL}/state?state=${state}`;
      console.log(`POST Request URL: ${url}`);

      try {
          const response = await fetch(url, {
              method: 'POST',
          });
          

          const data = await response.text();
          console.log('Backend Server Response:', data);

          if (response.ok) {
              alert(`상태가 성공적으로 변경되었습니다. (state=${state})`);
          } else {
              alert('상태 변경 요청에 실패했습니다.');
          }
      } catch (error) {
          console.error('Error during state update:', error);
          alert('서버 오류가 발생했습니다. 다시 시도해주세요.');
      }
  });

  document.getElementById('add-department-form').addEventListener('submit', async function (event) {
      event.preventDefault();

      const majorName = document.getElementById('department-name').value;
      const url = `${API_BASE_URL}/major/add/${encodeURIComponent(majorName)}`;
      console.log(`POST Request URL: ${url}`);

      try {
          const response = await fetch(url, {
              method: 'POST',
          });

          const data = await response.text();
          console.log('Backend Server Response:', data);

          if (response.ok) {
              alert(`학과(${majorName})가 성공적으로 추가되었습니다.`);
          } else if (data === '이미 존재하는 학과입니다.') {
              alert('이미 존재하는 학과입니다.');
          } else {
              alert('학과 추가에 실패했습니다.');
          }
      } catch (error) {
          console.error('Error during major addition:', error);
          alert('서버 오류가 발생했습니다. 다시 시도해주세요.');
      }
  });

  document.getElementById('add-user-form').addEventListener('submit', async function (event) {
        event.preventDefault();

        const id = document.getElementById('user-id').value;
        const name = document.getElementById('user-name').value;
        const password = document.getElementById('user-password').value;
        const isMentor = document.getElementById('mentor-status').value === 'true';

        const url = `${API_BASE_URL}/member/signup`;
        console.log(`POST Request URL: ${url}`);
        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ id, name, password, isMentor }),
            });

            console.log('POST : ' , JSON.stringify({ id, name, password, isMentor }));
            const data = await response.text();


            if (id.length !== 9) {
              console.log('id length:', id.length, '학번:', id);
              alert('학번 길이를 확인해주세요.');
            } else if (response.ok) {
                console.log('Backend Server Response:', data);
                alert('유저가 성공적으로 추가되었습니다.');
            } else if (data === '이미 존재하는 학번입니다.') {
                console.log('Backend Server Response:', data);
                alert('이미 존재하는 학번입니다.');
            } else {
                console.error('Backend Server Response[ERROR]:', errorData);
                alert('유저 추가 요청에 실패했습니다.');
            }
        } catch (error) {
            console.error('Error during user addition:', error);
            alert('서버 오류가 발생했습니다. 다시 시도해주세요.');
        }
    });
});
