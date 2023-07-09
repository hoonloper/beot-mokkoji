<template>
  <div class="chatroom-wrap">
    <div class="chatroom-name">
      <div class="status">{{ isConnected ? '🟢' : '🔴' }}</div>
      <div class="name">{{ room.name }}</div>
      <BeotButton @click="connect">연결하기</BeotButton>
      <BeotButton @click="disconnect">연결끊기</BeotButton>
    </div>
    <div class="chat-wrap">
      <div
        v-for="chat of chats"
        :class="[chat.senderId === store.state.id ? 'me' : 'you']"
        :key="chat.id"
      >
        <div class="message">
          {{ chat.message }}
        </div>
        <div align="right" class="time">
          {{
            new Date(chat.sendAt).getHours() +
            ':' +
            ((new Date(chat.sendAt).getMinutes() + '').length === 1
              ? '0' + new Date(chat.sendAt).getMinutes()
              : new Date(chat.sendAt).getMinutes())
          }}
        </div>
      </div>
    </div>
    <div>
      <input
        type="text"
        id="input-chat"
        :value="text"
        @input="inputTest"
        @keyup.enter="sendChat()"
      />
    </div>
  </div>
</template>

<script lang="ts" setup>
import { onMounted, ref } from 'vue';
import { useStore } from 'vuex';
import axios from 'axios';
import BeotButton from './BeotButton.vue';

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
const inputTest = (e: any) => {
  text.value = e.target.value;
};
const sendChat = () => {
  chat.value.type = 'MESSAGE';
  chat.value.message = text.value;
  chat.value.sendAt = new Date().toISOString();
  ws.value.send(JSON.stringify(chat.value));
  text.value = '';
};

const connectionMessage = ref('connectionMessage');
const isConnected = ref(false);

// TODO: 웹소켓이 연결됐을 때 바로 커넥트해줘야 함
const connect = () => {
  if (isConnected.value) {
    chat.value.type = 'CONNECT';
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
  isConnected.value = false;
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

.chatroom-wrap {
  overflow: auto;
  height: 100%;
}
.chatroom-name {
  position: sticky;
  top: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  padding: 10px 0px;
  font-size: 1.2rem;
  background-color: #dddddd;
}
.chat-wrap {
  display: flex;
  flex-direction: column;
  > * {
    display: flex;
    align-items: center;
    padding: 8px 16px;
    width: 300px;
    background-color: #f9f9f9;
    border-radius: 12px;
    box-shadow: 2px 3px 15px -10px;
    word-break: break-all;
  }
  > .me {
    margin: 10px 10px 10px auto;
  }
  > .you {
    margin: 10px auto 10px 10px;
  }
  .message {
    flex: 5;
    font-weight: 500;
  }
  .time {
    flex: 1;
    color: #999999;
  }
}
</style>
