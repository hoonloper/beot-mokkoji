<template>
  <div class="divider">
    <div>{{ prefix }}</div>
    <div>{{ suffix }}</div>
  </div>
</template>

<script lang="ts" setup>
withDefaults(defineProps<{ prefix: string; suffix?: string }>(), {
  suffix: '',
});
</script>

<style lang="scss" scoped>
.divider {
  font-size: 0.8rem;
  padding: 8px 0px 4px 0px;
  margin-left: 8px;
  margin-right: 8px;
  color: #505050;
  font-weight: 700;
  border-bottom: 1px #505050 solid;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
</style>
