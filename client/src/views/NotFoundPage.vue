<template>
  <div class="box-container">
    <h1 class="title">🚧 페이지를 찾을 수 없습니다.</h1>
    <p>
      잘못된 접근입니다.
      <br />
      로그인 페이지로 이동해서 로그인하세요.
    </p>
    <BeotButton @click="moveSignInPage()">로그인 페이지로 이동</BeotButton>
  </div>
</template>

<script lang="ts" setup>
import BeotButton from '@/components/BeotButton.vue';
import router from '@/router';

const moveSignInPage = () => {
  router.push('/sign-in');
};
</script>

<style lang="scss" scoped>
.title {
  font-size: 24px;
}
</style>
