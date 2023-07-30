<template>
  <div class="chatroom-wrap">
    <div class="chatroom-header">
      <BeotButton @click="back()">뒤로</BeotButton>
      <div class="chatroom-header-name">
        <div class="name">{{ room?.name ?? '채팅방' }}</div>
      </div>
    </div>
    <div ref="chatWrap" class="chat-wrap">
      <div
        v-for="chat of chats"
        :class="[chat.senderId === store.state.id ? 'me' : 'you']"
        :key="chat.id"
      >
        <div class="message">{{ chat.msg }}</div>
        <div align="right" class="time">{{ formatSendAt(chat.createdAt) }}</div>
      </div>
    </div>
    <div class="chat-input-wrap">
      <BeotInput
        type="text"
        class="text-input"
        placeholder="채팅을 입력해 주세요!"
        can-focus
        :value="text"
        @input="inputText"
      />
      <BeotButton @click="sendChat()">전송</BeotButton>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { onMounted, ref } from 'vue';
import { useStore } from 'vuex';
import axios from 'axios';
import BeotButton from '@/components/BeotButton.vue';
import BeotInput from '@/components/BeotInput.vue';
import router from '@/router';

const chatWrap = ref();
const props = defineProps(['roomId']);
const store = useStore();
const chats = ref<
  {
    createdAt: string;
    id: string;
    msg: string;
    receiverId: string;
    roomId: string;
    senderId: string;
    senderName: string;
  }[]
>([]);

const room = ref<{
  id: string;
  name: string;
  members: { id: string; name: string; nickname: string; memberId: string }[];
} | null>(null);

const receiver = ref();

onMounted(async () => {
  const response = await axios.get(
    'http://localhost:8080/api/v1/rooms/' + props.roomId
  );
  room.value = response.data;
  receiver.value = response.data.members.find(
    (member) => store.state.id !== member.memberId
  );

  const eventSource = new EventSource(
    `http://localhost:8080/api/v1/chats/chatrooms/${props.roomId}`
  );
  eventSource.onopen = (event) => {
    console.log('Connect');
  };

  eventSource.onmessage = (event) => {
    const data = JSON.parse(event.data);
    console.log(data);
    if (store.state.id !== data.senderIdx) {
      chats.value.push(data);
    }
  };
  eventSource.onerror = (error) => {
    console.warn(error);
    // eventSource.close();
  };
});

const sendChat = async () => {
  if (!room.value) {
    throw new Error();
  }
  const response = await axios.post('http://localhost:8080/api/v1/chats', {
    msg: text.value,
    senderId: store.state.id,
    senderName: store.state.name,
    receiverId: receiver.value.memberId,
    roomId: room.value.id,
  });
  chats.value.push(response.data);
};

const formatSendAt = (time: string) => {
  return (
    new Date(time).getHours() +
    ':' +
    ((new Date(time).getMinutes() + '').length === 1
      ? '0' + new Date(time).getMinutes()
      : new Date(time).getMinutes())
  );
};
const back = () => {
  router.push('/rooms');
};

const text = ref('');
const inputText = (e: any) => {
  text.value = e.target.value;
};
</script>

<style lang="scss" scoped>
@import '@/assets/styles/image.scss';

.chatroom-wrap {
  height: 100%;
}
.chat-input-wrap {
  bottom: 0;
  display: flex;
  align-items: center;
  gap: 10px;
  bottom: 0;
  background-color: #dddddd;
  height: 50px;
  padding: 10px;
  input {
    flex: 5;
  }
  button {
    flex: 1;
    height: 100%;
  }
}
.chatroom-header {
  position: sticky;
  top: 0px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 30px;
  padding: 10px;
  font-size: 1.2rem;
  background-color: #dddddd;
  .chatroom-header-name {
    display: flex;
    align-items: center;
    gap: 8px;
  }
}
.chat-wrap {
  overflow-y: scroll;
  height: calc(100% - 120px);
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
  > .connection {
    margin: 4px auto;
    background-color: inherit;
    border: none;
    box-shadow: none;
    font-size: 0.7rem;
    color: #999999;
    text-align: center;
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
