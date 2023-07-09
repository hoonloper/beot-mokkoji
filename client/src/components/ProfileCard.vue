<template>
  <div class="profile-card-container">
    <img class="image-square-l" src="../assets/logo.png" alt="profile image" />
    <div>
      {{ nickname }}
      <span>{{ `[${replacedName}]` }}</span>
    </div>
  </div>
</template>

<script lang="ts" setup>
const SLICE_NUMBER = 2;
const props = defineProps<{ id: string; name: string; nickname: string }>();
// TODO: 서버에서 가공처리해서 보내주는 것으로 수정해야 함
const replacedName =
  props.name.slice(0, SLICE_NUMBER) +
  '*'.repeat(props.name.length - SLICE_NUMBER);
</script>

<style lang="scss" scoped>
@import '../assets/bases/image.scss';

.profile-card-container {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 8px;

  span {
    font-size: 0.8rem;
  }
}
</style>
