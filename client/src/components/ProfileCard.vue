<template>
  <div class="profile-card-container">
    <img class="image-square-l" src="../assets/logo.png" alt="profile image" />
    <div>{{ nickname }}</div>
  </div>
</template>

<script lang="ts" setup>
const props = defineProps<{ id: string; name: string; nickname: string }>();
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
