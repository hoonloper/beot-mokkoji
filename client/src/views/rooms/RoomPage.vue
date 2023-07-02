<template>
  <ChatRoom :room="room" />
</template>

<script lang="ts" setup>
import ChatRoom from '@/components/ChatRoom.vue';

const DEFAULT_IMAGE = '../assets/logo.png';
const rooms = Array.from({ length: 10 }, (_, i) => ({
  key: i + 1,
  image: DEFAULT_IMAGE,
  name: '아무개' + (i + 1),
  latestChat: i + 1 + '번째 채팅',
})).sort((a, b) => b.key - a.key);
const props = defineProps(['roomId']);

const room = rooms.find((el) => el.key === +props.roomId);
</script>
