<template>
  <input :type="type" :placeholder="placeholder" />
</template>

<script lang="ts" setup>
withDefaults(defineProps<{ type?: 'text' | 'date'; placeholder?: string }>(), {
  type: 'text',
  placeholder: '',
});
</script>

<style lang="scss" scoped>
input {
  background-color: #ffffff;
  border: 1px solid #d0d0d0;
  padding: 6px 12px;
  border-radius: 12px;
  min-height: 32px;
}
</style>
