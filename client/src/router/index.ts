import { createWebHistory, createRouter } from 'vue-router';
import Main from '../views/main.vue';
import About from '../views/about.vue';
import Rooms from '../views/rooms.vue';
import Room from '../views/rooms/index.vue';
import NotFound from '../views/NotFound.vue';

const routes = [
  {
    path: '/',
    name: 'Main',
    component: Main,
  },
  {
    path: '/about',
    name: 'About',
    component: About,
  },
  {
    path: '/rooms',
    name: 'Rooms',
    component: Rooms,
  },
  {
    path: '/rooms/:roomId',
    name: 'Room',
    props: true,
    component: Room,
  },
  // {
  //   path: '/:*',
  //   component: NotFound,
  // },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
