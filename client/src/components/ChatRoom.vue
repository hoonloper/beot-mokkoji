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
  <div style="font-size: 30px">상태: {{ status }}</div>
  <div style="font-size: 30px">보낸 메시지: {{ message }}</div>
  <div style="font-size: 30px">받은 메시지: {{ responseMessage }}</div>
  <button @click="connect">연결하기</button>
  <button @click="disconnect">연결끊기</button>
  <button @click="sendMessage">메시지 보내기</button>
</template>

<script lang="ts" setup>
import { ref } from 'vue';

type Room = {
  name: string;
  roomId: string;
  members: {
    id: number;
    memberId: string;
  }[];
};
const props = defineProps<{ room: Room }>();

const text = ref('');
const chats = ref<string[]>([]);

const sendChat = () => {
  chats.value.push(text.value);
};

const status = ref('status');
const message = ref('message');
const responseMessage = ref('responseMessage');

const ws = ref<WebSocket>();
const connect = () => {
  try {
    ws.value = new WebSocket('ws://localhost:8080/chat');
    ws.value.addEventListener('open', () => {
      message.value = '연결되었습니다.';
    });
  } catch (err: unknown) {
    console.log(err);
  }
};
const sendMessage = () => {
  if (ws.value && checkStatus(ws.value.OPEN)) {
    message.value = '안녕?';
    ws.value.send(message.value);
    ws.value.addEventListener('message', (event: MessageEvent) => {
      const msg = event.data.split(' ');
      responseMessage.value = msg.at(-1);
    });
  }
};
const disconnect = () => {
  if (ws.value && checkStatus(ws.value.OPEN)) {
    ws.value.close();
    ws.value = undefined;
    message.value = '연결이 끊어졌습니다.';
  } else {
    message.value = '이미 연결이 끊겼습니다.';
  }
};
const checkStatus = (status: number) => ws.value?.readyState === status;
</script>

<style lang="scss" scoped>
.me {
  margin-right: 0px;
}
.you {
  background-color: cadetblue;
}
</style>
