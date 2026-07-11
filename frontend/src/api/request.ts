import axios from 'axios'

const request = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080/api',
  timeout: 10000
})

request.interceptors.response.use(
  response => response.data,
  error => {
    console.error(error)
    return Promise.reject(error)
  }
)

export default request
