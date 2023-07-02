<template>
  <RouterLink
    v-for="room of rooms"
    class="room-cantainer"
    :key="room.key"
    :to="{ path: '/rooms/' + room.key }"
  >
    <img class="image-circle-l" src="../assets/logo.png" alt="room image" />
    <div class="room-info">
      <div id="name">{{ room.name }}</div>
      <div id="chat">{{ room.latestChat }}</div>
    </div>
  </RouterLink>
</template>

<script lang="ts" setup>
const DEFAULT_IMAGE = '../assets/logo.png';
const rooms = Array.from({ length: 15 }, (_, i) => ({
  key: i + 1,
  image: DEFAULT_IMAGE,
  name: '아무개' + (i + 1),
  latestChat: i + 1 + '번째 채팅',
})).sort((a, b) => b.key - a.key);
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
