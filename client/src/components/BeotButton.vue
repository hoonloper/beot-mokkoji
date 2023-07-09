<template>
  <button>
    <slot />
  </button>
</template>

<script lang="ts" setup></script>

<style lang="scss" scoped>
button {
  font-family: 'ChosunGs';
  text-align: center;
  background-color: #111111;
  padding: 8px;
  color: #ffffff;
  border-radius: 6px;
}
button:hover {
  background-color: #444444;
  cursor: pointer;
}
button:active {
  background-color: #777777;
}
</style>
