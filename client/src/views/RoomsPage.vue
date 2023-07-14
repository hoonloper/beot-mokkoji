<template>
  <LayoutHeader v-if="store.state.isLoggedIn" id="header" />
  <div class="contents-wrap">
    <ItemDivider prefix="채팅방 목록" :suffix="`총 ${rooms.length}개`" />
    <div v-for="(room, i) of rooms" :key="i">
      <RouterLink
        class="room-cantainer"
        :to="{ path: '/rooms/room/' + room.roomId }"
      >
        <img class="image-circle-l" src="../assets/logo.png" alt="room image" />
        <div class="room-info">
          <div id="name">{{ room.name }}</div>
          <div id="members">{{ room.roomMembers.length }}명 참여중</div>
        </div>
      </RouterLink>
    </div>
  </div>
  <LayoutFooter v-if="store.state.isLoggedIn" id="footer" />
</template>

<script lang="ts" setup>
import { onMounted, ref } from 'vue';
import { useStore } from 'vuex';
import axios from 'axios';
import LayoutHeader from '@/layouts/LayoutHeader.vue';
import LayoutFooter from '@/layouts/LayoutFooter.vue';
import ItemDivider from '@/components/ItemDivider.vue';

const store = useStore();

const rooms = ref<
  {
    roomId: string;
    name: string;
    roomMembers: [
      {
        id: number;
        memberName: string;
        memberNickname: string;
        memberId: string;
      }
    ];
  }[]
>([]);

onMounted(async () => {
  const response = await axios(
    'http://localhost:8080/api/v1/rooms/' + store.state.id,
    {
      method: 'GET',
    }
  );
  rooms.value = response.data;
});
</script>

<style lang="scss" scoped>
@import '../assets/bases/image.scss';

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
