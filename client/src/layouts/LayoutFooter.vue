<template>
  <div class="footer-container">
    <RouterLink to="/">Main(친구목록)</RouterLink>
    <RouterLink to="/rooms">채팅방목록</RouterLink>
    <RouterLink to="/about">About(설정)</RouterLink>
  </div>
</template>

<style scoped>
.footer-container {
  display: flex;
  align-items: center;
  justify-content: space-around;
  padding: 20px;
}
</style>
