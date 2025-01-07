const API_BASE_URL = 'http://anacnu.kr:9027';
async function getState() {
    const API_URL = `${API_BASE_URL}/state`;

    try {
        console.log(`Fetching state from ${API_URL}`);
        const response = await fetch(API_URL);
        if (!response.ok) {
            throw new Error(`Failed to fetch state from ${API_URL}`);
        }

        const data = await response.text();
        const state = parseInt(data, 10);
        if (isNaN(state)) {
            throw new Error('Invalid state value received from server');
        }

        console.log(`State fetched from server: ${state}`);
        return state;
    } catch (error) {
        console.error("Error fetching state:", error);
        return null;
    }
}

getState().then(state => {
    console.log("현재 상태 값:", state);
});
