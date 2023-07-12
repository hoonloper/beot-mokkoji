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
body,
h1,
h2,
h3,
p,
li,
ul {
  height: 100%;
  margin: 0px;
  padding: 0px;
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
  background-color: #f0f0f0;
}
.error {
  font-size: small;
  font-weight: 500;
  color: red;
  text-align: center;
}

.sign-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 16px;
  padding: 20px;
  margin: 0 auto;
  max-width: 400px;
  background-color: #f9f9f9;
  border-radius: 12px;
  box-shadow: 2px 3px 15px -10px;
}
.buttons {
  display: flex;
  gap: 8px;
}
</style>
