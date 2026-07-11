<template>
  <div>
    <h2>Dashboard</h2>
    <el-row :gutter="20">
      <el-col :span="6" v-for="item in cards" :key="item.label">
        <el-card>
          <div>{{ item.label }}</div>
          <div class="number">{{ item.value }}</div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup lang="ts">
import { computed, onMounted } from 'vue'
import { useDashboardStore } from '../stores/dashboard'

const store = useDashboardStore()

onMounted(() => store.fetchDashboard())

const cards = computed(() => {
  const d = store.dashboard
  if (!d) return []
  return [
    { label: 'Calories In', value: d.caloriesIn },
    { label: 'Calories Out', value: d.caloriesOut },
    { label: 'Net Calories', value: d.netCalories },
    { label: 'Goal', value: `${(d.goalCompletionRate * 100).toFixed(0)}%` }
  ]
})
</script>

<style>
.number {
  font-size: 24px;
  font-weight: bold;
  margin-top: 10px;
}
</style>
