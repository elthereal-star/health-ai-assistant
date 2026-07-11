import { ref } from 'vue'
import { defineStore } from 'pinia'
import { createFoodRecord, listFoodRecords } from '../api/nutrition'
import { createExerciseRecord, listExerciseRecords } from '../api/exercise'

export const useRecordStore = defineStore('record', () => {
  const foodRecords = ref<any[]>([])
  const exerciseRecords = ref<any[]>([])

  const fetchFoodRecords = async () => {
    const res = await listFoodRecords()
    foodRecords.value = res.data
  }

  const fetchExerciseRecords = async () => {
    const res = await listExerciseRecords()
    exerciseRecords.value = res.data
  }

  const addFoodRecord = async (data: any) => {
    await createFoodRecord(data)
    await fetchFoodRecords()
  }

  const addExerciseRecord = async (data: any) => {
    await createExerciseRecord(data)
    await fetchExerciseRecords()
  }

  return {
    foodRecords,
    exerciseRecords,
    fetchFoodRecords,
    fetchExerciseRecords,
    addFoodRecord,
    addExerciseRecord
  }
})
