document.getElementById('login-form').addEventListener('submit', async function (event) {
    event.preventDefault(); // 폼 기본 동작 막기
  
    const studentId = document.getElementById('student-id').value;
    const password = document.getElementById('student-password').value;
  
    try {
        // 관리자 계정 확인
        if (studentId === '00' && password === '11') {
            alert('관리자로 로그인되었습니다.');
            window.location.href = '/pages/admin.html';
            return; // 관리자 로그인 시 이후 로직 실행 방지
        }
  
        // Flask 백엔드에서 JSON 데이터 가져오기
        const response = await fetch('/api/getUserData');
        const userData = await response.json();
  
        // 사용자 검증
        const user = userData.find(u => u.studentId === studentId && u.password === password);
  
        if (user) {
            if (user.role === 'mentor') {
                alert('멘토로 로그인되었습니다.');
                window.location.href = '/pages/mentor/dashboard.html';
            } else if (user.role === 'mentee') {
                alert('멘티로 로그인되었습니다.');
                window.location.href = '/pages/mentee/dashboard.html';
            }
        } else {
            alert('학번 또는 비밀번호가 일치하지 않습니다.');
        }
    } catch (error) {
        console.error('Error during login:', error);
        alert('서버 오류가 발생했습니다. 나중에 다시 시도하세요.');
    }
  });
  