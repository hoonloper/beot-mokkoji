<template>
  <h1>{{ room.name }}</h1>
  <h2>상태: {{ status }}</h2>
  <h2>연결 메시지: {{ connectionMessage }}</h2>
  <h2>받은 메시지: {{ responseMessage }}</h2>
  <div class="chat-wrap">
    <div
      v-for="chat of chats"
      :class="[chat.senderId === store.state.id ? 'me' : 'you']"
      :key="chat.id"
    >
      <div>
        {{ chat.eventType === 'CONNECT' ? '🟢' : '🔵' }}
        {{ chat.message }}
      </div>
      <div>
        {{
          new Date(chat.sendAt).getHours() +
          ':' +
          new Date(chat.sendAt).getMinutes()
        }}
      </div>
    </div>
  </div>
  <div>
    <input
      type="text"
      id="input-chat"
      :value="text"
      @input="(event) => (text = event.target!.value)"
      @keyup.enter="sendChat()"
    />
    입력하기
  </div>
  <button @click="connect">연결하기</button>
  <button @click="disconnect">연결끊기</button>
</template>

<script lang="ts" setup>
import { onMounted, ref } from 'vue';
import { useStore } from 'vuex';
import axios from 'axios';

type EventType = 'CONNECT' | 'MESSAGE';
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

const chats = ref<
  {
    id: number;
    eventType: EventType;
    senderId: string;
    message: string;
    sendAt: string;
  }[]
>([]);
onMounted(async () => {
  const { data } = await axios(
    'http://localhost:8080/api/v1/chats/' + props.room.roomId,
    {
      method: 'GET',
    }
  );
  chats.value = data;
});

const chat = ref<{
  type: EventType;
  senderId: string;
  roomId: string;
  message: string;
  sendAt: string;
}>({
  type: 'CONNECT',
  senderId: store.state.id,
  roomId: props.room.roomId,
  message: '',
  sendAt: new Date().toISOString(),
});

const text = ref('');
const sendChat = () => {
  chat.value.type = 'MESSAGE';
  chat.value.message = text.value;
  chat.value.sendAt = new Date().toISOString();
  ws.value.send(JSON.stringify(chat.value));
  text.value = '';
};

const status = ref('status');
const connectionMessage = ref('connectionMessage');
const responseMessage = ref('responseMessage');
const isConnected = ref(false);

// TODO: 웹소켓이 연결됐을 때 바로 커넥트해줘야 함
const connect = () => {
  if (isConnected.value) {
    ws.value.send(JSON.stringify(chat.value));
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
@import '../assets/bases/image.scss';

.chat-wrap {
  display: flex;
  flex-direction: column;
  > * {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 4px;
    height: 42px;
    width: 300px;
    background-color: #f9f9f9;
    border-radius: 12px;
    box-shadow: 2px 3px 15px -10px;
  }
  > .me {
    margin: 10px 10px 10px auto;
  }
  > .you {
    margin: 10px auto 10px 10px;
  }
}
</style>
