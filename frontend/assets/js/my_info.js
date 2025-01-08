document.addEventListener('DOMContentLoaded', async function () {
  const API_BASE_URL = 'http://anacnu.kr:9027';
  const accessToken = localStorage.getItem('accessToken');
  const userInfo = JSON.parse(localStorage.getItem('userInfo'));

  async function fetchMajors() {
      try {
          const response = await fetch(`${API_BASE_URL}/major/list`);
          if (!response.ok) {
              throw new Error('Failed to fetch majors.');
          }
          const majors = await response.json();
          return majors;
      } catch (error) {
          console.error('Error fetching majors:', error);
          return [];
      }
  }

  async function populateMajorDropdown() {
      const majorSelect = document.getElementById('user-major');
      const majors = await fetchMajors();

      majorSelect.innerHTML = `<option value="">학과를 선택하세요</option>`;

      majors.forEach(major => {
          const option = document.createElement('option');
          option.value = major.name;
          option.textContent = major.name;
          majorSelect.appendChild(option);
      });

      if (userInfo && userInfo.major) {
          majorSelect.value = userInfo.major;
      }
  }

  if (userInfo) {
      document.getElementById('user-id').value = userInfo.id;
      document.getElementById('user-email').value = userInfo.email || '';
      document.getElementById('user-phone').value = userInfo.phone || '';
  }

  await populateMajorDropdown();

  document.getElementById('update-info-form').addEventListener('submit', async function (event) {
      event.preventDefault();

      const email = document.getElementById('user-email').value.trim();
      const password = document.getElementById('user-password').value.trim();
      const phone = document.getElementById('user-phone').value.trim();
      const major = document.getElementById('user-major').value;

      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      const passwordRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
      const phoneRegex = /^\d{11}$/;

      if (!emailRegex.test(email)) {
          alert('유효한 이메일 주소를 입력해주세요.');
          return;
      }

      if (!passwordRegex.test(password)) {
          alert('비밀번호는 숫자, 영문, 특수문자 조합으로 8글자 이상이어야 합니다.');
          return;
      }

      if (!phoneRegex.test(phone)) {
          alert('전화번호는 - 없이 11자리 숫자로 입력해주세요.');
          return;
      }

      if (!major) {
          alert('학과를 선택해주세요.');
          return;
      }

      try {
          const updateResponse = await fetch(`${API_BASE_URL}/member/update`, {
              method: 'POST',
              headers: {
                  'Content-Type': 'application/json',
                  'Authorization': `Bearer ${accessToken}`,
              },
              body: JSON.stringify({
                  name: userInfo.name,
                  email: email,
                  password: password,
                  phone: phone,
                  major: major,
                  isMentor: userInfo.isMentor,
              }),
          });

          if (!updateResponse.ok) {
              throw new Error('정보 업데이트에 실패했습니다.');
          }

          console.log('User info successfully updated.');

          const meResponse = await fetch(`${API_BASE_URL}/member/me`, {
              method: 'GET',
              headers: {
                  'Content-Type': 'application/json',
                  'Authorization': `Bearer ${accessToken}`,
              },
          });

          if (!meResponse.ok) {
              throw new Error('사용자 정보를 가져오는 데 실패했습니다.');
          }

          const updatedUserInfo = await meResponse.json();
          localStorage.setItem('userInfo', JSON.stringify(updatedUserInfo));
          console.log('Updated user info saved to local storage:', updatedUserInfo);

          alert('정보가 성공적으로 업데이트되었습니다.');
          window.location.reload(); // 페이지 새로고침
      } catch (error) {
          console.error('Error updating user info:', error);
          alert('서버 오류가 발생했습니다. 다시 시도해주세요.');
      }
  });
});
