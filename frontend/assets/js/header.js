async function loadHeader() {
    try {
        const response = await fetch('/assets/templates/header.html');
        if (!response.ok) {
            throw new Error('Failed to load header');
        }
        const headerHTML = await response.text();

        const body = document.body;
        body.insertAdjacentHTML('afterbegin', headerHTML);

        console.log('Header loaded successfully.');

        const logoutButton = document.getElementById('logout-button');
        if (logoutButton) {
            logoutButton.addEventListener('click', () => {
                console.log('로그아웃 버튼 클릭됨');

                localStorage.removeItem('userInfo');
                localStorage.removeItem('accessToken');
                console.log('User info and access token removed from local storage.');

                window.location.href = '/';
            });
        }
    } catch (error) {
        console.error('Error loading header:', error);
    }
}

document.addEventListener('DOMContentLoaded', loadHeader);
