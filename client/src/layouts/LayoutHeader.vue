<template>
  <div class="header-container">
    <img
      class="image-square-m"
      alt="Vue logo"
      :width="50"
      :height="50"
      src="../assets/logo.png"
    />
    <span>벗-모꼬지</span>
  </div>
</template>

<style lang="scss" scoped>
@import '../assets/bases/image.scss';

.header-container {
  position: sticky;
  top: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 4px;
  padding: 16px;
  background-color: cornflowerblue;
}
</style>
