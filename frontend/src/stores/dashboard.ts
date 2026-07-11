import { ref } from 'vue'
import { defineStore } from 'pinia'
import { getDashboard } from '../api/analytics'

export const useDashboardStore = defineStore('dashboard', () => {
  const dashboard = ref<any>(null)
  const loading = ref(false)

  const fetchDashboard = async () => {
    loading.value = true
    try {
      const res = await getDashboard()
      dashboard.value = res.data
    } finally {
      loading.value = false
    }
  }

  return { dashboard, loading, fetchDashboard }
})
