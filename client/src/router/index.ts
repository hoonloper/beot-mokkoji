import { createWebHistory, createRouter } from 'vue-router';
import MainPage from '../views/MainPage.vue';
import SignUpPage from '../views/SignUpPage.vue';
import SettingPage from '../views/SettingPage.vue';
import RoomsPage from '../views/RoomsPage.vue';
import RoomPage from '../views/rooms/RoomPage.vue';
import NotFoundPage from '../views/NotFoundPage.vue';

const routes = [
  {
    path: '/',
    name: 'MainPage',
    component: MainPage,
  },
  {
    path: '/sign-up',
    name: 'SignUpPage',
    component: SignUpPage,
  },
  {
    path: '/setting',
    name: 'SettingPage',
    component: SettingPage,
  },
  {
    path: '/rooms',
    name: 'RoomsPage',
    component: RoomsPage,
  },
  {
    path: '/rooms/:roomId',
    name: 'RoomPage',
    props: true,
    component: RoomPage,
  },
  // {
  //   path: '/:*',
  //   component: NotFoundPage,
  // },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
