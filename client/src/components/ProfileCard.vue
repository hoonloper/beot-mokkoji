<template>
  <div class="profile-card-container">
    <img class="image-square-l" src="../assets/logo.png" alt="profile image" />
    <div>{{ `${nickname}(${replacedName})` }}</div>
  </div>
</template>

<script lang="ts" setup>
const SLICE_NUMBER = 2;
const props = defineProps<{ id: string; name: string; nickname: string }>();
const replacedName =
  props.name.slice(0, SLICE_NUMBER) +
  '*'.repeat(props.name.length - SLICE_NUMBER);
</script>

<style lang="scss" scoped>
@import '../assets/bases/image.scss';

.profile-card-container {
  display: flex;
  align-items: center;
  gap: 4px;
  padding: 8px;
}
</style>
