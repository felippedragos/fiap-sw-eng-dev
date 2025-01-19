<template>
  <div>
    <h2>Criar Usuário</h2>
    <form @submit.prevent="createUser">
      <label for="name">Nome:</label>
      <input type="text" v-model="user.name" id="name" required>
      <label for="email">Email:</label>
      <input type="email" v-model="user.email" id="email" required>
      <button type="submit">Criar</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      user: {
        name: '',
        email: ''
      }
    };
  },
  methods: {
    createUser() {
      axios.post('http://localhost:8080/usuarios/', this.user)
        .then(response => {
          this.$emit('user-created', response.data);
          this.user.name = '';
          this.user.email = '';
        })
        .catch(error => {
          console.error('Erro ao criar usuário:', error);
        });
    }
  }
};
</script>

<style scoped>
/* Adicione estilos aqui */
</style>
