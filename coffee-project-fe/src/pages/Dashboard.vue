<script>
import Sidebar from '../components/Sidebar.vue'

export default {
  name: 'Dashboard',
  components: {
    Sidebar
  }
}
</script>

<template>
  <div class="dashboard">
    <Sidebar />
    <h1>Test</h1>
    <!-- <div class="dashboard container">
      <h1>Dashboard</h1>
    </div> -->
  </div>
</template>

<style scoped>
.dashboard {
  height: 100%;
  background-color: red;

  display: flex;
  flex: 1;
  flex-direction: row;
}
</style>
