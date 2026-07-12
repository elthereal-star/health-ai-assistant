 <template>
   <div class="auth-container">
     <el-card class="auth-card">
       <h2>Register</h2>
       <el-form
         ref="formRef"
         :model="form"
         :rules="rules"
         label-position="top"
         @keyup.enter="handleRegister"
       >
         <el-form-item label="Username" prop="username">
           <el-input v-model="form.username" placeholder="3-64 characters" />
         </el-form-item>
         <el-form-item label="Email" prop="email">
           <el-input v-model="form.email" placeholder="your@email.com" />
         </el-form-item>
         <el-form-item label="Password" prop="password">
           <el-input
             v-model="form.password"
             type="password"
             placeholder="At least 6 characters"
             show-password
           />
         </el-form-item>
         <el-form-item>
           <el-button
             type="primary"
             class="submit-btn"
             :loading="loading"
             @click="handleRegister"
           >
             Register
           </el-button>
         </el-form-item>
       </el-form>
       <div class="link">
         <router-link to="/login">Already have an account? Login</router-link>
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
 
 const form = reactive({ username: '', email: '', password: '' })
 const loading = ref(false)
 const formRef = ref<FormInstance>()
 const authStore = useAuthStore()
 const router = useRouter()
 
 const rules: FormRules = {
   username: [{ required: true, message: 'Username is required', trigger: 'blur' }],
   email: [
     { required: true, message: 'Email is required', trigger: 'blur' },
     { type: 'email', message: 'Invalid email format', trigger: 'blur' }
   ],
   password: [
     { required: true, message: 'Password is required', trigger: 'blur' },
     { min: 6, message: 'Password must be at least 6 characters', trigger: 'blur' }
   ]
 }
 
 const handleRegister = async () => {
   if (!formRef.value) return
   try {
     await formRef.value.validate()
   } catch {
     return
   }
   loading.value = true
   try {
     await authStore.register(form)
     ElMessage.success('Registration successful')
     router.push('/')
   } catch (err: any) {
     ElMessage.error(err?.response?.data?.message || err?.message || 'Registration failed')
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
