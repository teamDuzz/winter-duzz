async function loadNav() {
  try {
    const response = await fetch('/assets/templates/nav.html');
    if (!response.ok) {
      throw new Error('Failed to load nav template');
    }

    const navHTML = await response.text();
    const header = document.querySelector('#main-header');
    if (header) {
      header.insertAdjacentHTML('afterend', navHTML);
    } else {
      console.error('Header not found. Nav inserted at the top of the body.');
      document.body.insertAdjacentHTML('afterbegin', navHTML);
    }

    console.log('Nav loaded successfully');

    const userInfo = JSON.parse(localStorage.getItem('userInfo'));
    if (userInfo && userInfo.isMentor) {
      console.log('User is a mentor. Updating nav links...');
      document.querySelector('a[href="#dashboard"]').setAttribute('href', '/pages/mentor/dashboard.html');
      document.querySelector('a[href="#resources"]').setAttribute('href', '/pages/mentor/resources.html');
      document.querySelector('a[href="#schedule"]').setAttribute('href', '/pages/mentor/schedule.html');
      document.querySelector('a[href="#my-info"]').setAttribute('href', '/pages/mentor/my_info.html');
    } else {
      console.log('User is a mentee. Updating nav links...');
      document.querySelector('a[href="#dashboard"]').setAttribute('href', '/pages/mentee/dashboard.html');
      document.querySelector('a[href="#resources"]').setAttribute('href', '/pages/mentee/resources.html');
      document.querySelector('a[href="#schedule"]').setAttribute('href', '/pages/mentee/schedule.html');
      document.querySelector('a[href="#my-info"]').setAttribute('href', '/pages/mentee/my_info.html');
    }
  } catch (error) {
    console.error('Error loading nav:', error);
  }
}

document.addEventListener('DOMContentLoaded', loadNav);
