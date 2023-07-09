<template>
  <ChatRoom v-if="pending" :room="(room as Room)" />
</template>

<script lang="ts" setup>
import { onMounted, ref } from 'vue';
import axios from 'axios';
import ChatRoom from '@/components/ChatRoom.vue';

// const DEFAULT_IMAGE = '../assets/logo.png';
const props = defineProps(['roomId']);

type Room = {
  name: string;
  roomId: string;
  members: {
    id: number;
    memberId: string;
  }[];
};

const pending = ref(false);
const room = ref<Room>();
onMounted(async () => {
  const response = await axios(
    'http://localhost:8080/api/v1/rooms/room/' + props.roomId,
    {
      method: 'GET',
    }
  );
  room.value = response.data.reduce(
    (
      acc: Room,
      cur: {
        name: string;
        roomId: string;
        id: number;
        memberId: string;
      }
    ) => {
      const member = { id: cur.id, memberId: cur.memberId };
      if (acc.roomId) {
        acc.members.push(member);
      } else {
        acc = { roomId: cur.roomId, name: cur.name, members: [member] };
      }
      return acc;
    },
    {}
  );
  pending.value = true;
});
</script>

<style lang="scss" scoped></style>
