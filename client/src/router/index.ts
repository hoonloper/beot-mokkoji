import { createWebHistory, createRouter } from 'vue-router';
import MainPage from '@/views/MainPage.vue';
import SignUpPage from '@/views/SignUpPage.vue';
import SignInPage from '@/views/SignInPage.vue';
import SettingPage from '@/views/SettingPage.vue';
import RoomsPage from '@/views/RoomsPage.vue';
import RoomPage from '@/views/rooms/RoomPage.vue';
import NotFoundPage from '@/views/NotFoundPage.vue';
import { RouterPath } from '@/common/constant';

const routes = [
  {
    path: RouterPath.MAIN,
    name: 'MainPage',
    component: MainPage,
  },
  {
    path: RouterPath.SIGN_UP,
    name: 'SignUpPage',
    component: SignUpPage,
  },
  {
    path: RouterPath.SIGN_IN,
    name: 'SignInPage',
    component: SignInPage,
  },
  {
    path: RouterPath.SETTING,
    name: 'SettingPage',
    component: SettingPage,
  },
  {
    path: RouterPath.ROOMS,
    name: 'RoomsPage',
    component: RoomsPage,
  },
  {
    path: RouterPath.ROOMS + '/:roomId',
    name: 'RoomPage',
    props: true,
    component: RoomPage,
  },
  {
    path: '/:pathMatch(.*)*',
    name: 'NOT_FOUND',
    component: NotFoundPage,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
