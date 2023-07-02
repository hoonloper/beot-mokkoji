<template>
  <div class="footer-container">
    <RouterLink to="/">Main(친구목록)</RouterLink>
    <RouterLink to="/rooms">채팅방목록</RouterLink>
    <RouterLink to="/setting">설정</RouterLink>
    <RouterLink v-if="!isLoggedIn" to="/sign-up">회원가입</RouterLink>
  </div>
</template>

<script lang="ts" setup>
import { computed } from 'vue';
import { useStore } from 'vuex';

const isLoggedIn = computed(() => useStore().state.isLoggedIn);
</script>

<style scoped>
.footer-container {
  display: flex;
  align-items: center;
  justify-content: space-around;
  padding: 20px;
}
</style>
