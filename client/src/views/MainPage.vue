<template>
  <div>
    <div id="title">내 프로필</div>
    <ProfileCard
      :id="store.state.id"
      :name="store.state.name"
      :nickname="store.state.nickname"
    />
    <div id="title">벗 목록</div>
    <div v-for="beot of beotList" :key="beot.id" class="beot-profile-container">
      <ProfileCard
        :id="beot.toMember.id"
        :name="beot.toMember.name"
        :nickname="beot.toMember.nickname"
      />
      <div>
        <button>친구삭제</button>
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
      id: '54fdad27-a0e0-40b8-82dc-a063aaf19562',
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
#title {
  font-size: 0.8rem;
  padding: 8px 0px 4px 0px;
  margin-left: 8px;
  margin-right: 8px;
  color: #505050;
  font-weight: 700;
  border-bottom: 1px #505050 solid;
}
</style>
