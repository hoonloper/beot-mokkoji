<template>
  <div class="footer-container">
    <router-link to="/main">Main(친구목록)</router-link>
    <div>예시(채팅방목록)</div>
    <router-link to="/about">About(설정)</router-link>
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
