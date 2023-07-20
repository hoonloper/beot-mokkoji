<template>
  <RouterView />
</template>

<style>
@import './assets/styles/index.scss';
html,
body {
  height: 100%;
  margin: 0px;
  padding: 0px;
}
h1,
h2,
h3,
p,
li,
ul {
  margin: 0px;
  padding: 0px;
}

#app {
  height: 100%;
  font-size: 16px;
  font-family: SUITE-Regular;
  background-color: #f0f0f0;
}

.buttons {
  display: flex;
  gap: 8px;
}
</style>
