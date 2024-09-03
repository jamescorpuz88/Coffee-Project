<script setup>
import Login from './pages/Login.vue'
import SignUp from './pages/SignUp.vue'

import { RouterView } from 'vue-router'
</script>

<template>
  <main>
    <RouterView />
  </main>
</template>

<style scoped>
main {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: aqua;
}
</style>
