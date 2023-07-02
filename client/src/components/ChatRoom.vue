<template>
  <div>{{ room.name }}</div>
  <div
    v-for="(chat, i) of chats"
    :class="[room.key === i ? 'me' : 'you']"
    :key="i"
  >
    {{ chat }}
  </div>
  <div>
    <input
      type="text"
      id="input-chat"
      :value="text"
      @input="(event) => (text = event.target!.value)"
    />
    <button type="button" @click="sendChat">입력</button>
  </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue';

const props = defineProps(['room']);
const text = ref('');
const chats = ref<string[]>([props.room.latestChat]);

const sendChat = () => {
  chats.value.push(text.value);
};
</script>

<style lang="scss" scoped>
.me {
  margin-right: 0px;
}
.you {
  background-color: cadetblue;
}
</style>
