<template>
  <div v-if="store.state.isLoggedIn" style="height: 100%">
    <LayoutHeader id="header" />
    <div class="contents-wrap">
      <ItemDivider prefix="설정" />
      <div class="button-wrap">
        <BeotButton @click="logout">로그아웃</BeotButton>
        <BeotButton @click="resign">회원탈퇴</BeotButton>
      </div>
    </div>
    <LayoutFooter id="footer" />
  </div>
</template>

<script lang="ts" setup>
import BeotButton from '@/components/BeotButton.vue';
import ItemDivider from '@/components/ItemDivider.vue';
import LayoutFooter from '@/layouts/LayoutFooter.vue';
import LayoutHeader from '@/layouts/LayoutHeader.vue';
import router from '@/router';
import { useStore } from 'vuex';
import { useMemberStorage } from '@/composables/useMemberStorage';
import axios from 'axios';
import { HttpStatus } from '@/common/constant';

const memberStorage = useMemberStorage();
memberStorage.setItem('end-point', '/settings');

const store = useStore();
// 비로그인 사용자는 로그인 페이지로 이동
if (router.currentRoute.value.name !== 'NOT_FOUND' && !store.state.isLoggedIn) {
  router.push('sign-in');
}

const logout = () => {
  memberStorage.clear();
  store.commit('clear');
  router.push('sign-in');
};

const resign = async () => {
  const { isLoggedIn, ...member } = store.state;
  const response = await axios.delete(
    'http://localhost:8080/api/v1/auth/resign',
    {
      data: member,
    }
  );
  if (response.status === HttpStatus.NO_CONTENT) {
    logout();
  }
};
</script>

<style lang="scss" scoped>
.contents-wrap {
  min-height: calc(100% - 100px);
}
.button-wrap {
  margin: 8px;
}
</style>
