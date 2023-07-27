<template>
  <div v-if="store.state.isLoggedIn" style="height: 100%">
    <LayoutHeader id="header" />
    <div class="contents-wrap">
      <ItemDivider prefix="채팅방 목록" :suffix="`총 ${rooms.length}개`" />
      <div v-for="room of rooms" :key="room.id">
        <RouterLink class="room-cantainer" :to="{ path: '/rooms/' + room.id }">
          <img
            class="image-circle-l"
            src="../assets/logo.png"
            alt="room image"
          />
          <div class="room-info">
            <div id="name">{{ room.name }}</div>
            <div id="members">{{ room.name }}명 참여중</div>
          </div>
        </RouterLink>
      </div>
    </div>
    <LayoutFooter id="footer" />
  </div>
</template>

<script lang="ts" setup>
import { onMounted, ref } from 'vue';
import { useStore } from 'vuex';
import axios from 'axios';
import LayoutHeader from '@/layouts/LayoutHeader.vue';
import LayoutFooter from '@/layouts/LayoutFooter.vue';
import ItemDivider from '@/components/ItemDivider.vue';
import router from '@/router';
import { useMemberStorage } from '@/composables/useMemberStorage';

useMemberStorage().setItem('end-point', '/rooms');

const store = useStore();
// 비로그인 사용자는 로그인 페이지로 이동
if (router.currentRoute.value.name !== 'NOT_FOUND' && !store.state.isLoggedIn) {
  router.push('sign-in');
}

const rooms = ref<
  {
    id: string;
    memberId: string;
    name: string;
  }[]
>([]);

onMounted(async () => {
  if (store.state.isLoggedIn) {
    const response = await axios(
      'http://localhost:8080/api/v1/rooms/64c251c795522d393d57050e',
      {
        method: 'GET',
      }
    );
    rooms.value = [response.data];
  }
});
</script>

<style lang="scss" scoped>
@import '../assets/styles/image.scss';

.contents-wrap {
  min-height: calc(100% - 100px);
}

.room-cantainer {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 8px;
  text-decoration-line: none;
  .room-info {
    display: flex;
    width: 100%;
    justify-content: space-between;
    gap: 4px;
    font-size: 16px;
    #name {
      font-weight: 700;
      color: #333333;
    }
    #members {
      font-size: 0.8rem;
      color: #636363;
    }
  }
}
.room-cantainer:hover {
  background-color: #e0e0e0;
  cursor: pointer;
}
.room-cantainer:active {
  background-color: #d0d0d0;
}
</style>
