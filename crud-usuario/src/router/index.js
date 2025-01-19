import Vue from 'vue';
import Router from 'vue-router';
import Home from '@/components/FIAP.vue';
import UserList from '@/components/UserList.vue';
import UserCreate from '@/components/UserCreate.vue';
import UserEdit from '@/components/UserEdit.vue';

Vue.use(Router);

export default new Router({
    mode: 'history',
    routes: [
        { path: '/', component: Home },
        { path: '/users', component: UserList },
        { path: '/users/new', component: UserCreate },
        { path: '/users/edit/:id', component: UserEdit },
    ]
});
