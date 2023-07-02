import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import AntD from 'ant-design-vue';
import member from '@/stores/member.store';

createApp(App).use(router).use(AntD).use(member).mount('#app');
