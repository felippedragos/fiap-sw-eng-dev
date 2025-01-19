<template>
  <div>
    <h2>Lista de Usuários</h2>
    <ul>
      <li v-for="user in users" :key="user.id">
        {{ user.name }} - {{ user.email }} -{{ user.status }}
        <button @click="$emit('edit-user', user)">Editar</button>
        <button @click="$emit('delete-user', user.id)">Excluir</button>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  props: ['users']
};
</script>

<style scoped>
/* Adicione estilos aqui */
</style>
