<template>
  <LayoutHeader style="height: 5%" v-if="isLoggedIn" id="header" />
  <div style="height: 90%">
    <RouterView />
  </div>
  <LayoutFooter style="height: 5%" v-if="isLoggedIn" id="footer" />
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
@font-face {
  font-family: 'ChosunGs';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@1.0/ChosunGs.woff')
    format('woff');
  font-weight: normal;
  font-style: normal;
}
#app {
  height: 100%;
  font-size: 16px;
  font-family: ChosunGs;
}
</style>
