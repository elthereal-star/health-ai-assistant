<template>
  <div>
    <h2>Record Food</h2>
    <el-form :model="form" label-width="120px">
      <el-form-item label="Food">
        <el-input v-model="form.foodName" />
      </el-form-item>
      <el-form-item label="Portion">
        <el-input-number v-model="form.portion" :min="0" />
      </el-form-item>
      <el-form-item label="Unit">
        <el-input v-model="form.unit" />
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
  foodName: 'beef noodles',
  portion: 1,
  unit: 'bowl'
})

const submit = async () => {
  await store.addFoodRecord(form)
  ElMessage.success('Food record added')
}
</script>
