<template>
  <div>
    <div id="title">내 프로필</div>
    <ProfileCard
      :id="store.state.id"
      :name="store.state.name"
      :nickname="store.state.nickname"
    />
    <div id="title">
      <div>벗 목록</div>
      <div>팔로우한 사람: {{ beotList.length }}명</div>
    </div>
    <div v-for="beot of beotList" :key="beot.id" class="beot-profile-container">
      <ProfileCard
        :id="beot.toMember.id"
        :name="beot.toMember.name"
        :nickname="beot.toMember.nickname"
      />
      <div>
        <button>팔로우 끊기</button>
        <button>채팅하기</button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { useStore } from 'vuex';
import axios from 'axios';
import ProfileCard from '@/components/ProfileCard.vue';
import { onMounted, ref } from 'vue';

const store = useStore();
type Beot = {
  id: number;
  toMember: {
    id: string;
    name: string;
    nickname: string;
    birthday: string;
  };
  createdAt: string;
};
const beotList = ref<Beot[]>([]);

onMounted(async () => {
  const data = await axios('http://localhost:8080/api/v1/beots/following/:id', {
    method: 'GET',
    params: {
      id: store.state.id,
    },
  });
  beotList.value = data.data;
});
</script>

<style lang="scss" scoped>
.beot-profile-container {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-right: 8px;
  button {
    padding: 8px;
  }
}
.beot-profile-container:hover {
  background-color: #f9f9f9;
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
