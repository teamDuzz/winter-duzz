const API_BASE_URL = 'http://59.29.157.89:8080'; // API 기본 URL

async function getState() {
    const API_URL = `${API_BASE_URL}/state`; // 기본 URL에 /state 추가

    try {
        console.log(`Fetching state from ${API_URL}`);
        // 서버에서 state 값을 가져옴
        const response = await fetch(API_URL);
        if (!response.ok) {
            throw new Error(`Failed to fetch state from ${API_URL}`);
        }

        // 서버가 숫자를 반환하는 경우
        const data = await response.text();
        const state = parseInt(data, 10); // 숫자로 변환
        if (isNaN(state)) {
            throw new Error('Invalid state value received from server');
        }

        console.log(`State fetched from server: ${state}`);
        return state; // 0, 1, 2 값을 반환
    } catch (error) {
        console.error("Error fetching state:", error);
        return null; // 에러가 발생하면 null 반환
    }
}

// 예시: 상태 출력
getState().then(state => {
    console.log("현재 상태 값:", state);
});
