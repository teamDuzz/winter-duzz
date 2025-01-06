document.addEventListener('DOMContentLoaded', async function () {
  const API_BASE_URL = 'http://59.29.157.89:8080';
  const resourcesUrl = `${API_BASE_URL}/resources`;

  /**
   * 자료 데이터 가져오기
   */
  async function fetchResources() {
      try {
          const response = await fetch(resourcesUrl);
          if (!response.ok) {
              throw new Error('Failed to fetch resources.');
          }
          const data = await response.json();
          return data;
      } catch (error) {
          console.error('Error fetching resources:', error);
          return [];
      }
  }

  /**
   * 자료 리스트 렌더링
   */
  async function renderResources() {
      const resourcesList = document.getElementById('resources-list');
      const resources = await fetchResources();

      if (resources.length === 0) {
          resourcesList.innerHTML = '<p>자료가 없습니다.</p>';
          return;
      }

      resources.forEach(resource => {
          const resourceCard = document.createElement('div');
          resourceCard.className = 'resource-card';

          resourceCard.innerHTML = `
              <h2>${resource.title || '제목 없음'}</h2>
              <p>${resource.description || '설명이 없습니다.'}</p>
              <a href="${resource.link}" target="_blank">자세히 보기</a>
          `;

          resourcesList.appendChild(resourceCard);
      });
  }

  // 자료 리스트 렌더링 실행
  renderResources();
});
