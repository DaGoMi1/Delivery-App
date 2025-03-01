import axios from 'axios';

const api = axios.create({
  baseURL : 'http://localhost:8080/',
  headers : {"Content-Type" : "application/json"}, // JSON 데이터를 주고받음
})

// 요청 인터셉터 : 요청을 보내기 전 특정 작업을 수행하는 기능
api.interceptors.request.use((config)=> {
  const token = localStorage.getItem("accessToken");
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }

  return config
})

api.interceptors.response.use(
  (response) => response,
  async (error) => {
    if (error.response?.status === 401) { // 401 Unauthorized (토큰 만료)
      try {
        const refreshToken = localStorage.getItem("refreshToken");
        if (!refreshToken) throw new Error("Refresh Token 없음");

        // Refresh Token으로 새 Access Token 요청
        const res = await axios.post("https://localhost:8080/auth/refresh-token", {
          refreshToken,
        });

        // 새 Access Token 저장
        localStorage.setItem("accessToken", res.data.accessToken);
        error.config.headers.Authorization = `Bearer ${res.data.accessToken}`;

        // 실패했던 요청을 다시 시도
        return axios(error.config);
      } catch (refreshError) {
        console.error("Refresh Token도 만료됨, 다시 로그인 필요");
        localStorage.removeItem("accessToken");
        localStorage.removeItem("refreshToken");
        window.location.href = "/login"; // 로그인 페이지로 이동
        return Promise.reject(refreshError);
      }
    }
    return Promise.reject(error);
  }
);

export default api;