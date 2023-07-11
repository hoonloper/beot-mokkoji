<template>
  <input ref="beotInput" :type="type" :placeholder="placeholder" />
</template>

<script lang="ts" setup>
import { onMounted, ref } from 'vue';

const beotInput = ref<any>(null);

const setFocus = () => {
  if (beotInput.value) {
    beotInput.value.focus();
  }
};

const props = withDefaults(
  defineProps<{
    type?: 'text' | 'date';
    placeholder?: string;
    canFocus?: boolean;
  }>(),
  {
    type: 'text',
    placeholder: '',
    canFocus: false,
  }
);

onMounted(() => {
  if (props.canFocus) {
    setFocus();
  }
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
