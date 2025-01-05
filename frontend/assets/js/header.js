// assets/js/header.js

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
  } catch (error) {
      console.error('Error loading header:', error);
  }
}

// Load the header when the DOM is fully loaded
document.addEventListener('DOMContentLoaded', loadHeader);
