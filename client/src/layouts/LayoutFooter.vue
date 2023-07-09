<template>
  <div class="footer-container">
    <RouterLink to="/">벗 목록</RouterLink>
    <RouterLink to="/rooms">채팅방 목록</RouterLink>
    <RouterLink to="/setting">설정</RouterLink>
  </div>
</template>

<script lang="ts" setup></script>

<style scoped>
.footer-container {
  position: sticky;
  bottom: 0;
  display: flex;
  align-items: center;
  justify-content: space-evenly;
  background-color: #111111;
  height: 50px;
  > a {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100%;
    font-size: large;
    text-decoration-line: none;
    color: #ffffff;
  }
  > a:hover {
    background-color: #444444;
    cursor: pointer;
  }
  > a:active {
    background-color: #777777;
  }
}
</style>
