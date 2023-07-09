<template>
  <div class="header-container">
    <div class="logo-container">
      <img
        class="image-square-m"
        alt="Vue logo"
        :width="50"
        :height="50"
        src="../assets/logo.png"
      />
      <span>벗-모꼬지</span>
    </div>
  </div>
</template>

<style lang="scss" scoped>
@import '../assets/bases/image.scss';

.header-container {
  position: sticky;
  top: 0px;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #111111;
  .logo-container {
    display: flex;
    align-items: center;
    gap: 8px;
    padding: 8px;
    border-radius: 4px;
    background-color: #f0f0f0;
  }
}
</style>
