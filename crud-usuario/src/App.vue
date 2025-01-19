<template>
  <div id="app">
    <header>
      <h1>CRUD de Usuários</h1>
    </header>
    <main>
      <CreateUser @user-created="fetchUsers"/>
      <UserList 
        :users="users" 
        @edit-user="setUserToEdit" 
        @delete-user="deleteUser"
      />
      <EditUser 
        v-if="userToEdit" 
        :user="userToEdit" 
        @user-updated="fetchUsers"
      />
    </main>
  </div>
</template>

<script>
import axios from 'axios';
import CreateUser from './components/CreateUser.vue';
import UserList from './components/UserList.vue';
import EditUser from './components/EditUser.vue';

export default {
  components: {
    CreateUser,
    UserList,
    EditUser
  },
  data() {
    return {
      users: [],
      userToEdit: null
    };
  },
  created() {
    this.fetchUsers();
  },
  methods: {
    fetchUsers() {
      axios.get('http://localhost:8080/usuarios')
        .then(response => {
          this.users = response.data;
        })
        .catch(error => {
          console.error('Erro ao buscar usuários:', error);
        });
    },
    setUserToEdit(user) {
      this.userToEdit = { ...user };
    },
    deleteUser(userId) {
      axios.delete(`http://localhost:8080/usuarios/${userId}`)
        .then(() => {
          this.fetchUsers();
        })
        .catch(error => {
          console.error('Erro ao deletar usuário:', error);
        });
    }
  }
};
</script>

<style>
/* Estilos globais */
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

header {
  background-color: #42b983;
  color: white;
  padding: 20px;
}

main {
  padding: 20px;
}

button {
  background-color: #42b983;
  color: white;
  border: none;
  padding: 10px 20px;
  cursor: pointer;
  border-radius: 5px;
  margin: 5px;
}

button:hover {
  background-color: #368a72;
}
</style>






