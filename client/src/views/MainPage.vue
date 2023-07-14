<template>
  <LayoutHeader v-if="store.state.isLoggedIn" id="header" />
  <div class="contents-wrap">
    <ItemDivider prefix="내 프로필" />
    <ProfileCard
      :id="store.state.id"
      :name="store.state.name"
      :nickname="store.state.nickname"
    />
    <ItemDivider
      prefix="벗 목록"
      :suffix="`팔로우한 사람: ${beotList.length}명`"
    />
    <div v-for="beot of beotList" :key="beot.id" class="beot-profile-container">
      <ProfileCard
        :id="beot.toMember.id"
        :name="beot.toMember.name"
        :nickname="beot.toMember.nickname"
      />
      <div class="buttons">
        <BeotButton>팔로우 끊기</BeotButton>
        <BeotButton>채팅하기</BeotButton>
      </div>
    </div>
  </div>
  <LayoutFooter v-if="store.state.isLoggedIn" id="footer" />
</template>

<script lang="ts" setup>
import { useStore } from 'vuex';
import axios from 'axios';
import ProfileCard from '@/components/ProfileCard.vue';
import { onMounted, ref } from 'vue';
import BeotButton from '@/components/BeotButton.vue';
import LayoutHeader from '@/layouts/LayoutHeader.vue';
import LayoutFooter from '@/layouts/LayoutFooter.vue';
import ItemDivider from '@/components/ItemDivider.vue';

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
  const response = await axios(
    'http://localhost:8080/api/v1/beots/following/:id',
    {
      method: 'GET',
      params: {
        id: store.state.id,
      },
    }
  );
  beotList.value = response.data;
});
</script>

<style lang="scss" scoped>
.contents-wrap {
  min-height: calc(100% - 100px);
}
.beot-profile-container {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-right: 8px;
}
.beot-profile-container:hover {
  background-color: #f9f9f9;
}
.buttons {
  display: flex;
  gap: 8px;
}
</style>
