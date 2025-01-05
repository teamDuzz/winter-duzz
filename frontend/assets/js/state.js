// assets/js/state.js

async function getState() {
  try {
      // state.json 파일의 데이터를 가져옴
      const response = await fetch('/assets/templates/state.json');
      if (!response.ok) {
          throw new Error('Failed to fetch state.json');
      }
      const data = await response.json();

      // 상태 문자열을 숫자로 변환
      let stateNumber;
      switch (data.state) {
          case "input":
              stateNumber = 0;
              break;
          case "matching":
              stateNumber = 1;
              break;
          case "completed":
              stateNumber = 2;
              break;
          default:
              throw new Error('Invalid state value in state.json');
      }

      return stateNumber; // 0, 1, 또는 2
  } catch (error) {
      console.error("Error fetching state:", error);
      return null; // 에러가 발생하면 null 반환
  }
}

// 예시: 상태에 따라 대시보드의 동작 변경
getState().then(state => {
  if (state === 0) {
      console.log("현재 상태: 정보 입력 기간");
      // 정보 입력 UI 표시
  } else if (state === 1) {
      console.log("현재 상태: 매칭 중");
      // 매칭 중 메시지 표시
  } else if (state === 2) {
      console.log("현재 상태: 매칭 완료");
      // 매칭 결과 표시
  } else {
      console.log("상태를 불러오지 못했습니다.");
  }
});
