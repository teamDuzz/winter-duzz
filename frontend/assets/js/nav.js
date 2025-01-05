async function loadNav() {
  try {
    // Fetch nav HTML template
    const response = await fetch('/assets/templates/nav.html');
    if (!response.ok) {
      throw new Error('Failed to load nav template');
    }

    // Get nav HTML and insert it into the DOM, below the header
    const navHTML = await response.text();
    const header = document.querySelector('#main-header');
    if (header) {
      header.insertAdjacentHTML('afterend', navHTML);
    } else {
      console.error('Header not found. Nav inserted at the top of the body.');
      document.body.insertAdjacentHTML('afterbegin', navHTML);
    }

    console.log('Nav loaded successfully');
  } catch (error) {
    console.error('Error loading nav:', error);
  }
}

// Load nav when DOM is ready
document.addEventListener('DOMContentLoaded', loadNav);
