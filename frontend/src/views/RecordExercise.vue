<template>
  <div>
    <h2>Record Exercise</h2>
    <el-form :model="form" label-width="120px">
      <el-form-item label="Exercise">
        <el-input v-model="form.exerciseType" />
      </el-form-item>
      <el-form-item label="Duration (min)">
        <el-input-number v-model="form.durationMinutes" :min="0" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submit">Submit</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup lang="ts">
import { reactive } from 'vue'
import { useRecordStore } from '../stores/record'
import { ElMessage } from 'element-plus'

const store = useRecordStore()
const form = reactive({
  exerciseType: 'running',
  durationMinutes: 30
})

const submit = async () => {
  await store.addExerciseRecord(form)
  ElMessage.success('Exercise record added')
}
</script>
