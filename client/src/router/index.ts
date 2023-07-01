import { createWebHistory, createRouter } from 'vue-router';
import Main from '../views/main.vue';
import About from '../views/about.vue';
import * as NotFound from '../views/NotFound.vue';

const routes = [
  {
    path: '/main',
    name: 'Main',
    component: Main,
  },
  {
    path: '/about',
    name: 'About',
    component: About,
  },
  {
    path: '/:catchAll(.*)',
    component: NotFound,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
