<template>
  <RouterView />
</template>

<script lang="ts" setup>
import { useStore } from 'vuex';
import router from './router';

const store = useStore();
if (!store.state.isLoggedIn) {
  router.push('sign-in');
}
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
@font-face {
  font-family: 'SUITE-Regular';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2304-2@1.0/SUITE-Regular.woff2')
    format('woff2');
  font-weight: 400;
  font-style: normal;
}
#app {
  height: 100%;
  font-size: 16px;
  font-family: SUITE-Regular;
}
</style>
