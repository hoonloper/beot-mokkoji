<template>
  <LayoutHeader v-if="store.state.isLoggedIn" id="header" />
  <div class="contents-wrap">
    <ItemDivider prefix="설정" />
    <div>내 프로필 보기</div>
    <BeotButton @click="logout">로그아웃</BeotButton>
  </div>
  <LayoutFooter v-if="store.state.isLoggedIn" id="footer" />
</template>

<script lang="ts" setup>
import BeotButton from '@/components/BeotButton.vue';
import ItemDivider from '@/components/ItemDivider.vue';
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

<style lang="scss" scoped>
.contents-wrap {
  min-height: calc(100% - 100px);
}
</style>
