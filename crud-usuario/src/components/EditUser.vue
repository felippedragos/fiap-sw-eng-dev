<template>
  <div v-if="localUser">
    <h2>Editar Usuário</h2>
    <form @submit.prevent="updateUser">
      <label for="name">Nome:</label>
      <input type="text" v-model="localUser.name" id="name" required>
      <label for="email">Email:</label>
      <input type="email" v-model="localUser.email" id="email" required>
      <button type="submit">Atualizar</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  props: ['user'],
  data() {
    return {
      localUser: { ...this.user }
    };
  },
  watch: {
    user: {
      immediate: true,
      handler(newUser) {
        this.localUser = { ...newUser };
      }
    }
  },
  methods: {
    updateUser() {
      axios.put(`http://localhost:8080/usuarios/${this.localUser.id}`, this.localUser)
        .then(response => {
          this.$emit('user-updated', response.data);
        })
        .catch(error => {
          console.error('Erro ao atualizar usuário:', error);
        });
    }
  }
};
</script>

<style scoped>
/* Adicione estilos aqui */
</style>
