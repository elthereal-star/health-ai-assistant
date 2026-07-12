import request from './request'

export interface LoginForm {
  username: string
  password: string
}

export interface RegisterForm {
  username: string
  email: string
  password: string
}

export interface AuthResponse {
  token: string
  tokenType: string
  userId: number
  username: string
}

export function login(data: LoginForm) {
  return request.post('/identity/login', data)
}

export function register(data: RegisterForm) {
  return request.post('/identity/register', data)
}
