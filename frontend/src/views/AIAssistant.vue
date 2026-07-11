<template>
  <div>
    <h2>AI Health Assistant</h2>
    <el-input
      v-model="context"
      type="textarea"
      rows="4"
      placeholder="Describe your recent diet and exercise..."
    />
    <el-button type="primary" @click="ask" :loading="aiStore.loading">Ask</el-button>
    <el-card v-if="aiStore.advice" style="margin-top: 20px;">
      <p>{{ aiStore.advice }}</p>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useAIStore } from '../stores/ai'

const context = ref('I ate beef noodles and ran 5km today.')
const aiStore = useAIStore()

const ask = () => {
  aiStore.fetchHealthAdvice(context.value)
}
</script>
