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
      <ProfileCard :id="beot.id" :name="beot.name" :nickname="beot.nickname" />
      <div>
        <button>친구삭제</button>
        <button>채팅하기</button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { useStore } from 'vuex';
import ProfileCard from '@/components/ProfileCard.vue';

const store = useStore();

const beotList = Array.from({ length: 10 }, (_, i) => ({
  id: 'test' + (i + 1),
  name: '이름' + (i + 1),
  nickname: '닉네임' + (i + 1),
})).sort((a, b) => a.nickname.localeCompare(b.nickname));
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
