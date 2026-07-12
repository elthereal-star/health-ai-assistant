import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import { login as loginApi, register as registerApi } from '../api/auth'
import type { AuthResponse, LoginForm, RegisterForm } from '../api/auth'

export const useAuthStore = defineStore('auth', () => {
  const token = ref<string | null>(localStorage.getItem('token'))
  const username = ref<string | null>(localStorage.getItem('username'))
  const userId = ref<number | null>(safeParseInt(localStorage.getItem('userId')))

  const isAuthenticated = computed(() => !!token.value)

  function setAuth(data: AuthResponse) {
    token.value = data.token
    username.value = data.username
    userId.value = data.userId
    localStorage.setItem('token', data.token)
    localStorage.setItem('username', data.username)
    localStorage.setItem('userId', data.userId.toString())
  }

  function clearAuth() {
    token.value = null
    username.value = null
    userId.value = null
    localStorage.removeItem('token')
    localStorage.removeItem('username')
    localStorage.removeItem('userId')
  }

  async function login(form: LoginForm) {
    const res = await loginApi(form)
    setAuth(res.data as AuthResponse)
  }

  async function register(form: RegisterForm) {
    const res = await registerApi(form)
    setAuth(res.data as AuthResponse)
  }

  function logout() {
    clearAuth()
  }

  return {
    token,
    username,
    userId,
    isAuthenticated,
    login,
    register,
    logout
  }
})

function safeParseInt(value: string | null): number | null {
  if (!value) return null
  const parsed = parseInt(value)
  return isNaN(parsed) ? null : parsed
}
