<template>
  <LayoutHeader />
  <router-view />
  <LayoutFooter />
</template>

<script lang="ts" setup>
import LayoutHeader from './layout/LayoutHeader.vue';
import LayoutFooter from './layout/LayoutFooter.vue';
</script>

<style>
#app {
  max-width: 600px;
  margin: 0 auto;
}
</style>
