<script>
import axios from 'axios'
import { ref } from 'vue'

export default {
  name: 'Login',
  components: {},
  setup() {
    const username = ref('')
    const password = ref('')

    return {
      username,
      password
    }
  },
  methods: {
    async login() {
      await axios
        .post('http://localhost:8080/api/v1/user/login', {
          username: this.username,
          password: this.password
        })
        .then((response) => {
          console.log(response.data)
        })
        .catch((error) => {
          console.log(error)
        })
    }
  }
}
</script>

<template>
  <div class="login container">
    <div>
      <h1>Welcome Back</h1>
    </div>

    <div>
      <h1>Login</h1>
      <form @submit.prevent="login">
        <label>
          Username
          <input type="text" v-model="username" placeholder="Username" />
        </label>
        <label>
          Password
          <input type="password" v-model="password" placeholder="Password" />
        </label>
        <button @click="login" type="button">Login</button>
        <p>Dont have an account? Register</p>
      </form>
    </div>
  </div>
</template>

<style scoped>
.login {
  padding: 64px 32px;
  border-radius: 24px;

  width: 50vw;
  height: 600px;

  display: flex;

  div {
    flex: 1 1 0px;
    margin: auto;
  }
}

h1 {
  text-align: center;
}

form {
  margin: 24px 0;
  padding: 0 32px;

  input {
    width: 100%;
    padding: 8px;
    border-radius: 8px;

    border: none;

    margin-bottom: 8px;
  }

  button {
    width: 100%;
    border: none;
    border-radius: 8px;
    margin-top: 32px;

    font-weight: 600;
    font-size: medium;
    padding: 12px 0;
    cursor: pointer;

    background-color: rgb(89, 89, 228);
    color: white;
  }

  button:hover {
    background-color: rgb(71, 71, 185);
  }
}
</style>
