async function loadHeader() {
    try {
        // Fetch header HTML
        const response = await fetch('/assets/templates/header.html');
        if (!response.ok) {
            throw new Error('Failed to load header');
        }
        const headerHTML = await response.text();

        // Insert header into the body
        const body = document.body;
        body.insertAdjacentHTML('afterbegin', headerHTML);

        console.log('Header loaded successfully.');

        // 로그아웃 버튼 이벤트 리스너 추가
        const logoutButton = document.getElementById('logout-button');
        if (logoutButton) {
            logoutButton.addEventListener('click', () => {
                console.log('로그아웃 버튼 클릭됨');

                // 로컬 스토리지에서 userInfo 및 accessToken 삭제
                localStorage.removeItem('userInfo');
                localStorage.removeItem('accessToken');
                console.log('User info and access token removed from local storage.');

                // 페이지 최상위로 이동
                window.location.href = '/';
            });
        }
    } catch (error) {
        console.error('Error loading header:', error);
    }
}

// Load the header when the DOM is fully loaded
document.addEventListener('DOMContentLoaded', loadHeader);
