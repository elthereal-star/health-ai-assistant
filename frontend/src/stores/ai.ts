import { ref } from 'vue'
import { defineStore } from 'pinia'
import { getHealthAdvice } from '../api/ai'

export const useAIStore = defineStore('ai', () => {
  const advice = ref('')
  const loading = ref(false)

  const fetchHealthAdvice = async (context: string) => {
    loading.value = true
    try {
      const res = await getHealthAdvice({ context })
      advice.value = res.data.advice
    } finally {
      loading.value = false
    }
  }

  return { advice, loading, fetchHealthAdvice }
})
