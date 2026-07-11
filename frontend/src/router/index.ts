import { createRouter, createWebHistory } from 'vue-router'
import Dashboard from '../views/Dashboard.vue'
import RecordFood from '../views/RecordFood.vue'
import RecordExercise from '../views/RecordExercise.vue'
import AIAssistant from '../views/AIAssistant.vue'

const routes = [
  { path: '/', component: Dashboard },
  { path: '/record/food', component: RecordFood },
  { path: '/record/exercise', component: RecordExercise },
  { path: '/ai', component: AIAssistant }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
