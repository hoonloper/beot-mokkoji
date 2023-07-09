<template>
  <div class="header-container">
    <div class="logo-container">
      <img class="image-square-s" alt="Vue logo" src="../assets/logo.png" />
      <span>모꼬지</span>
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
  height: 50px;
  .logo-container {
    display: flex;
    align-items: center;
    gap: 8px;
    padding: 4px;
    border-radius: 4px;
    background-color: #f0f0f0;
  }
}
</style>
