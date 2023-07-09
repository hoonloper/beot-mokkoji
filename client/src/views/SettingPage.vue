<template>
  <div>
    <h1>Setting</h1>
    <BeotButton @click="logout">로그아웃</BeotButton>
  </div>
</template>

<script lang="ts" setup>
import BeotButton from '@/components/BeotButton.vue';
import router from '@/router';
import { useStore } from 'vuex';

const store = useStore();
const logout = () => {
  store.commit('clear');
  router.push('sign-in');
};
</script>
