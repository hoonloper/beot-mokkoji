<template>
  <div id="title">
    <div>채팅방 목록</div>
    <div>총 {{ rooms.length }}개</div>
  </div>
  <RouterLink
    v-for="(room, i) of rooms"
    class="room-cantainer"
    :key="i"
    :to="{ path: '/rooms/room/' + room.roomId }"
  >
    <img class="image-circle-l" src="../assets/logo.png" alt="room image" />
    <div class="room-info">
      <div id="name">{{ room.name }}</div>
      <div id="members">{{ room.roomMembers.length }}명 참여중</div>
    </div>
  </RouterLink>
</template>

<script lang="ts" setup>
import { onMounted, ref } from 'vue';
import { useStore } from 'vuex';
import axios from 'axios';

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

#title {
  font-size: 0.8rem;
  padding: 8px 0px 4px 0px;
  margin-left: 8px;
  margin-right: 8px;
  color: #505050;
  font-weight: 700;
  border-bottom: 1px #505050 solid;
  display: flex;
  justify-content: space-between;
}
</style>
