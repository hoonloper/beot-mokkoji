<template>
  <LayoutHeader v-if="store.state.isLoggedIn" id="header" />
  <div>
    <h1>Setting</h1>
    <BeotButton @click="logout">로그아웃</BeotButton>
  </div>
  <LayoutFooter v-if="store.state.isLoggedIn" id="footer" />
</template>

<script lang="ts" setup>
import BeotButton from '@/components/BeotButton.vue';
import LayoutFooter from '@/layouts/LayoutFooter.vue';
import LayoutHeader from '@/layouts/LayoutHeader.vue';
import router from '@/router';
import { useStore } from 'vuex';

const store = useStore();
const logout = () => {
  store.commit('clear');
  router.push('sign-in');
};
</script>
