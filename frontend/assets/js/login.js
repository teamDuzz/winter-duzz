document.getElementById('login-form').addEventListener('submit', async function (event) {
    event.preventDefault();

    const studentId = document.getElementById('student-id').value.trim();
    const password = document.getElementById('student-password').value.trim();
    const API_BASE_URL = 'http://anacnu.kr:9027';
    const loginUrl = `${API_BASE_URL}/member/signin`;
    const userInfoUrl = `${API_BASE_URL}/member/me`;

    try {
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

        const accessToken = loginData.accessToken;
        if (!accessToken || accessToken === '') {
            alert('로그인 실패: 잘못된 학번 또는 비밀번호입니다.');
            return;
        }

        localStorage.setItem('accessToken', accessToken);
        console.log('Access token saved to local storage:', accessToken);

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

        localStorage.setItem('userInfo', JSON.stringify(userInfo));
        console.log('User info saved to local storage:', userInfo);

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
        alert('오류가 발생했습니다. 나중에 다시 시도하세요.');
    }
});
