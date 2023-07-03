<template>
  <RouterLink
    v-for="room of rooms"
    class="room-cantainer"
    :key="room.id"
    :to="{ path: '/rooms/' + room.id }"
  >
    <img class="image-circle-l" src="../assets/logo.png" alt="room image" />
    <div class="room-info">
      <div id="name">{{ room.toMember.nickname }}</div>
      <div id="chat">{{ room.latestChat }}</div>
    </div>
  </RouterLink>
</template>

<script lang="ts" setup>
import { ref } from 'vue';

const rooms = ref([
  {
    id: '임시아이디',
    toMember: { id: '임시', name: '임시', nickname: '임시' },
    latestChat: '임시 채팅',
  },
]);
</script>

<style lang="scss" scoped>
@import '../assets/bases/image.scss';

.room-cantainer {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 8px;
  .room-info {
    display: flex;
    flex-direction: column;
    gap: 4px;
    font-size: 14px;
    #name {
      font-weight: 700;
    }
    #chat {
      color: gray;
      font-size: 0.8rem;
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
