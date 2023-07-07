<template>
  <h1>{{ room.name }}</h1>
  <h2>상태: {{ status }}</h2>
  <h2>연결 메시지: {{ connectionMessage }}</h2>
  <h2>받은 메시지: {{ responseMessage }}</h2>
  <div
    v-for="(chat, i) of chats"
    :class="[chat.senderId === store.state.id ? 'me' : 'you']"
    :key="i"
  >
    {{ chat.message }}
    {{ chat.sendAt }}
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
  <button @click="disconnect">연결끊기</button>
  <button @click="sendMessage">메시지 보내기</button>
</template>

<script lang="ts" setup>
import { ref } from 'vue';
import { useStore } from 'vuex';
import axios from 'axios';

type Room = {
  name: string;
  roomId: string;
  members: {
    id: number;
    memberId: string;
  }[];
};
const props = defineProps<{ room: Room }>();
const store = useStore();
const text = ref('');
const chats = ref<{ senderId: string; message: string; sendAt: string }[]>([]);

const sendChat = () => {
  chats.value.push();
};

const status = ref('status');
const connectionMessage = ref('connectionMessage');
const responseMessage = ref('responseMessage');
const isConnected = ref(false);

const sendMessage = () => {
  const event = {
    type: 'CONNECT',
    roomId: props.room.roomId,
    senderId: store.state.id,
    sendAt: new Date(),
  };
  if (isConnected.value) {
    ws.value.send(JSON.stringify(event));
    // ws.value.send(connectionMessage.value);
    // ws.value.onmessage = (event: MessageEvent) => {
    //   const msg = event.data.split(' ');
    //   responseMessage.value = msg.at(-1);
    // };
  }
};
const disconnect = () => {
  if (isConnected.value) {
    ws.value.close(1000);
    connectionMessage.value = '연결이 끊어졌습니다.';
  } else {
    connectionMessage.value = '이미 연결이 끊겼습니다.';
  }
};

// 소켓 연결
const ws = ref<WebSocket>(new WebSocket('ws://localhost:8080/ws/chat'));

// 연결되었을 때
ws.value.onopen = (e) => {
  console.log(e);
  console.log('[open] Web Socket connected!');
  isConnected.value = e.isTrusted;
  connectionMessage.value = '연결되었습니다.';
};

// 응답 받은 메시지
ws.value.onmessage = (e) => {
  if (e.isTrusted) {
    chats.value.push(JSON.parse(e.data));
  }
};

// 소켓이 끊어졌을 때
ws.value.onclose = (e) => {
  // code가 1000이면 정상 종료
  if (e.code === 1000) {
    console.log('[close] Web Socket closed!');
  } else {
    console.log(`[close] Web Socket Dead code: ${e.code}, reason: ${e.reason}`);
  }
};

// 소켓 통신중 에러가 발생했을 때
ws.value.onerror = (e) => {
  console.log('[Error] Web Socket Error');
  console.log(e);
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
