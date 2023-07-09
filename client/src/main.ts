import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import member from '@/stores/member.store';

createApp(App).use(router).use(member).mount('#app');
