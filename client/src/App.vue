<template>
  <LayoutHeader v-if="isLoggedIn" id="header" />
  <RouterView />
  <LayoutFooter v-if="isLoggedIn" id="footer" />
</template>

<script lang="ts" setup>
import { computed } from 'vue';
import { useStore } from 'vuex';
import router from './router';
import LayoutHeader from './layouts/LayoutHeader.vue';
import LayoutFooter from './layouts/LayoutFooter.vue';

const store = useStore();
if (!store.state.isLoggedIn) {
  router.push('sign-in');
}
const isLoggedIn = computed(() => store.state.isLoggedIn);
</script>

<style>
html,
body {
  margin: 0px;
  padding: 0px;
  width: 100%;
  height: 100%;
  background-color: #f0f0f0;
}
#app {
  height: 100%;
}
</style>
