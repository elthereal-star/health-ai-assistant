<template>
  <el-container class="app">
    <el-header>
      <h1>Health AI Assistant</h1>
      <div class="header-actions">
        <el-menu
          v-if="authStore.isAuthenticated"
          mode="horizontal"
          :router="true"
          :default-active="route.path"
          class="nav-menu"
        >
          <el-menu-item index="/">Dashboard</el-menu-item>
          <el-menu-item index="/record/food">Record Food</el-menu-item>
          <el-menu-item index="/record/exercise">Record Exercise</el-menu-item>
          <el-menu-item index="/ai">AI Assistant</el-menu-item>
        </el-menu>
        <div v-if="authStore.isAuthenticated" class="user-info">
          <span>{{ authStore.username }}</span>
          <el-button link type="primary" @click="logout">Logout</el-button>
        </div>
        <div v-else class="guest-links">
          <router-link to="/login">Login</router-link>
          <router-link to="/register">Register</router-link>
        </div>
      </div>
    </el-header>
    <el-main>
      <router-view />
    </el-main>
  </el-container>
</template>

<script setup lang="ts">
import { useRoute, useRouter } from 'vue-router'
import { useAuthStore } from './stores/auth'

const route = useRoute()
const router = useRouter()
const authStore = useAuthStore()

const logout = () => {
  authStore.logout()
  router.push('/login')
}
</script>

<style>
.app {
  min-height: 100vh;
}
.el-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  border-bottom: 1px solid #eee;
}
.header-actions {
  display: flex;
  align-items: center;
  gap: 16px;
}
.nav-menu {
  flex: 1;
}
.user-info {
  display: flex;
  align-items: center;
  gap: 12px;
  white-space: nowrap;
}
.guest-links a {
  margin-left: 12px;
  text-decoration: none;
  color: var(--el-color-primary);
}
.guest-links a:hover {
  text-decoration: underline;
}
</style>
