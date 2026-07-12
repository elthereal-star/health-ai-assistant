 <template>
   <div class="auth-container">
     <el-card class="auth-card">
       <h2>Login</h2>
       <el-form
         ref="formRef"
         :model="form"
         :rules="rules"
         label-position="top"
         @keyup.enter="handleLogin"
       >
         <el-form-item label="Username" prop="username">
           <el-input v-model="form.username" placeholder="Enter username" />
         </el-form-item>
         <el-form-item label="Password" prop="password">
           <el-input
             v-model="form.password"
             type="password"
             placeholder="Enter password"
             show-password
           />
         </el-form-item>
         <el-form-item>
           <el-button
             type="primary"
             class="submit-btn"
             :loading="loading"
             @click="handleLogin"
           >
             Login
           </el-button>
         </el-form-item>
       </el-form>
       <div class="link">
         <router-link to="/register">Create an account</router-link>
       </div>
     </el-card>
   </div>
 </template>
 
 <script setup lang="ts">
 import { reactive, ref } from 'vue'
 import { useRouter } from 'vue-router'
 import { ElMessage } from 'element-plus'
 import { useAuthStore } from '../stores/auth'
 import type { FormInstance, FormRules } from 'element-plus'
 
 const form = reactive({ username: '', password: '' })
 const loading = ref(false)
 const formRef = ref<FormInstance>()
 const authStore = useAuthStore()
 const router = useRouter()
 
 const rules: FormRules = {
   username: [{ required: true, message: 'Username is required', trigger: 'blur' }],
   password: [{ required: true, message: 'Password is required', trigger: 'blur' }]
 }
 
 const handleLogin = async () => {
   if (!formRef.value) return
   try {
     await formRef.value.validate()
   } catch {
     return
   }
   loading.value = true
   try {
     await authStore.login(form)
     ElMessage.success('Login successful')
     router.push('/')
   } catch (err: any) {
     ElMessage.error(err?.response?.data?.message || err?.message || 'Login failed')
   } finally {
     loading.value = false
   }
 }
 </script>
 
 <style scoped>
 .auth-container {
   display: flex;
   justify-content: center;
   align-items: center;
   min-height: 90vh;
 }
 .auth-card {
   width: 360px;
 }
 .submit-btn {
   width: 100%;
 }
 .link {
   text-align: center;
   margin-top: 12px;
 }
 </style>
