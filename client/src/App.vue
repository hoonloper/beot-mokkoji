<template>
  <LayoutHeader />
  <RouterView />
  <LayoutFooter />
</template>

<script lang="ts" setup>
import LayoutHeader from './layouts/LayoutHeader.vue';
import LayoutFooter from './layouts/LayoutFooter.vue';
</script>

<style>
#app {
  max-width: 600px;
  margin: 0 auto;
}
</style>
