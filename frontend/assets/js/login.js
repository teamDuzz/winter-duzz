document.getElementById('login-form').addEventListener('submit', async function (event) {
    event.preventDefault(); // 폼 기본 동작 막기

    const studentId = document.getElementById('student-id').value.trim();
    const password = document.getElementById('student-password').value.trim();
    const API_BASE_URL = 'http://59.29.157.89:8080';
    const loginUrl = `${API_BASE_URL}/member/signin`;
    const userInfoUrl = `${API_BASE_URL}/member/me`;

    try {
        // 로그인 요청
        const loginResponse = await fetch(loginUrl, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ id: studentId, password }),
        });

        if (!loginResponse.ok) {
            const errorData = await loginResponse.json();
            throw new Error(errorData.message || '로그인 요청 실패');
        }

        const loginData = await loginResponse.json();
        console.log('Login Response:', loginData);

        // JWT 토큰 확인
        const accessToken = loginData.accessToken;
        if (!accessToken || accessToken === '') {
            alert('로그인 실패: 잘못된 학번 또는 비밀번호입니다.');
            return;
        }

        // JWT 토큰 저장
        localStorage.setItem('accessToken', accessToken);
        console.log('Access token saved to local storage:', accessToken);

        // 관리자 계정 확인
        if (studentId === '999999999') {
            alert('관리자로 로그인되었습니다.');

            const userResponse = await fetch(userInfoUrl, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${accessToken}`,
                },
            });

            if (!userResponse.ok) {
                const errorData = await userResponse.json();
                throw new Error(errorData.message || '사용자 정보 요청 실패');
            }

            const userInfo = await userResponse.json();
            localStorage.setItem('userInfo', JSON.stringify(userInfo));
            console.log('User info saved to local storage:', userInfo);

            window.location.href = '/pages/admin.html';
            return;
        }

        const userResponse = await fetch(userInfoUrl, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${accessToken}`,
            },
        });

        if (!userResponse.ok) {
            const errorData = await userResponse.json();
            throw new Error(errorData.message || '사용자 정보 요청 실패');
        }

        const userInfo = await userResponse.json();
        console.log('User Info:', userInfo);

        // 사용자 정보를 로컬 스토리지에 저장
        localStorage.setItem('userInfo', JSON.stringify(userInfo));
        console.log('User info saved to local storage:', userInfo);

        // 사용자 이름 기반 알림 메시지
        const userName = userInfo.name || '사용자';
        if (userInfo.isMentor) {
            alert(`${userName}님, 멘토로 로그인되었습니다.`);
            window.location.href = '/pages/mentor/dashboard.html';
        } else {
            alert(`${userName}님, 멘티로 로그인되었습니다.`);
            window.location.href = '/pages/mentee/dashboard.html';
        }
    } catch (error) {
        console.error('Error during login or fetching user info:', error);
        alert('서버 오류가 발생했습니다. 나중에 다시 시도하세요.');
    }
});
