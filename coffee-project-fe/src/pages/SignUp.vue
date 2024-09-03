<script>
import axios from 'axios'
import { ref } from 'vue'

export default {
  name: 'SignUp',
  components: {},
  setup() {
    const firstName = ref('firstName')
    const lastName = ref('lastName')
    const username = ref('username')
    const password = ref('password')
    const email = ref('email@email.com')

    return {
      firstName,
      lastName,
      username,
      password,
      email
    }
  },
  methods: {
    async signUp() {
      const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/

      if (this.firstName === '') {
        console.log('First name is required')
        return
      }

      if (this.lastName === '') {
        console.log('Last name is required')
        return
      }

      if (this.email === '') {
        console.log('Email is required')
        return
      }

      if (!emailPattern.test(this.email)) {
        console.log('Invalid email')
        return
      }

      if (this.username === '') {
        console.log('Username is required')
        return
      }

      if (this.password === '' || this.password.length < 8) {
        console.log('Password is required')
        return
      }

      const signUpData = {
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.email,
        username: this.username,
        password: this.password
      }

      await axios
        .post(`${import.meta.env.VITE_API_URL}/api/v1/user/register`, signUpData)
        .then((response) => {
          if (response.data.status) {
            this.redirectToLogin()
          }
        })
        .catch((error) => {
          console.log(error.response.data)
        })
    },

    redirectToLogin() {
      this.$router.push('/')
    }
  }
}
</script>

<template>
  <div class="sign-up container">
    <div class="">
      <h1>Get Started</h1>
    </div>

    <div>
      <h1>Create your account</h1>
      <form @submit.prevent="signUp">
        <div class="name-container">
          <label>
            First Name
            <input type="text" placeholder="First name" required v-model="firstName" />
          </label>
          <label>
            Last Name
            <input type="text" placeholder="Last name" required v-model="lastName" />
          </label>
        </div>
        <label>
          Email
          <input type="email" placeholder="email@address.com" required v-model="email" />
        </label>
        <label>
          Username
          <input type="text" placeholder="Username" required v-model="username" />
        </label>
        <label>
          Password
          <input type="password" placeholder="8+ characters" minlength="8" v-model="password" />
        </label>
        <button @click="signUp" type="button">Sign up</button>
      </form>
    </div>
  </div>
</template>

<style scoped>
.sign-up {
  padding: 64px 32px;
  border-radius: 24px;

  width: 50vw;
  height: 600px;

  display: flex;
  flex: 1;

  div {
    flex: 1 1 0px;
    margin: auto;
  }
}

h1 {
  text-align: center;
}

.name-container {
  display: flex;
  flex-direction: row;
  gap: 8px;
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

  input:not(:placeholder-shown):valid {
    outline-color: rgb(82, 255, 125);
  }

  input:not(:placeholder-shown):invalid {
    outline-color: rgb(255, 60, 60);
  }

  p {
    font-size: 12px;
    font-weight: 500;
  }
}
</style>
