document.addEventListener("DOMContentLoaded", () => {
  // Fetch the footer template
  fetch('../templates/footer.html')
    .then(response => {
      if (!response.ok) {
        throw new Error('Failed to load footer');
      }
      return response.text();
    })
    .then(html => {
      // Append the footer HTML to the body
      document.body.insertAdjacentHTML('beforeend', html);
    })
    .catch(err => console.error(err));
});
