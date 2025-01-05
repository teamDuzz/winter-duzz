document.addEventListener('DOMContentLoaded', async function () {
  try {
      // Footer HTML 파일 로드
      const response = await fetch('/assets/templates/footer.html');
      if (!response.ok) {
          throw new Error('Footer 파일을 불러오는데 실패했습니다.');
      }
      const footerHtml = await response.text();

      // Footer를 body의 마지막에 추가
      const footerElement = document.createElement('div');
      footerElement.innerHTML = footerHtml;
      document.body.appendChild(footerElement);
  } catch (error) {
      console.error('Footer 로드 중 오류:', error);
  }
});
