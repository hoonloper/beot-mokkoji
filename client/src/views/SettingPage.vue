<template>
  <div>
    <h1>Setting</h1>
    <button @click="logout">로그아웃</button>
  </div>
</template>

<script lang="ts" setup>
import router from '@/router';
import { useStore } from 'vuex';

const store = useStore();
const logout = () => {
  store.commit('clear');
  router.push('sign-in');
};
</script>
