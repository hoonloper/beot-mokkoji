<template>
  <div class="footer-container">
    <RouterLink to="/">Main(친구목록)</RouterLink>
    <RouterLink to="/rooms">채팅방목록</RouterLink>
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
  background-color: cornflowerblue;
  > a {
    flex: 1;
    padding: 20px;
    text-align: center;
  }
  > a:hover {
    background-color: darkcyan;
    cursor: pointer;
  }
  > a:active {
    background-color: blue;
  }
}
</style>
