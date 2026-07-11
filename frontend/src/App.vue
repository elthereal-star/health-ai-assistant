<template>
  <el-container class="app">
    <el-header>
      <h1>Health AI Assistant</h1>
      <el-menu mode="horizontal" :router="true" :default-active="route.path">
        <el-menu-item index="/">Dashboard</el-menu-item>
        <el-menu-item index="/record/food">Record Food</el-menu-item>
        <el-menu-item index="/record/exercise">Record Exercise</el-menu-item>
        <el-menu-item index="/ai">AI Assistant</el-menu-item>
      </el-menu>
    </el-header>
    <el-main>
      <router-view />
    </el-main>
  </el-container>
</template>

<script setup lang="ts">
import { useRoute } from 'vue-router'
const route = useRoute()
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
</style>
