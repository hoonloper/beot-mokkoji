<template>
  <div class="sign-in-container">
    <h2>로그인</h2>
    <div>
      <input
        id="name"
        type="text"
        placeholder="이름"
        :value="name"
        @input="(event) => (name = event.target.value)"
      />
    </div>
    <div>
      <input
        id="nickname"
        type="text"
        placeholder="닉네임"
        :value="nickname"
        @input="(event) => (nickname = event.target.value)"
      />
    </div>
    <button @click="signIn()">로그인</button>
    <button @click="handleSignUp()">회원가입</button>
  </div>
  <div id="error" v-if="errorMessage">{{ errorMessage }}</div>
</template>

<script lang="ts" setup>
import { HttpStatus } from '@/common/constant';
import router from '@/router';
import { ref } from 'vue';
import { useStore } from 'vuex';
import axios from 'axios';

const store = useStore();
const name = ref('');
const nickname = ref('');
const errorMessage = ref('');

const signIn = async () => {
  if (!name.value || !nickname.value) {
    errorMessage.value = '입력값을 다시 확인해 주세요.';
    return;
  }
  try {
    const response = await axios('http://localhost:8080/api/v1/auth/sign-in', {
      method: 'POST',
      data: {
        name: name.value,
        nickname: nickname.value,
      },
    });
    if (response.status === HttpStatus.OK) {
      router.push('/');
      store.commit('setAll', {
        id: response.data.id,
        name: response.data.name,
        nickname: response.data.nickname,
        isLoggedIn: true,
      });
      console.log('SignIn Success!');
    } else {
      errorMessage.value = '에러가 발생했습니다. 다시 가입하십시오.';
      store.commit('clear');
    }
  } catch (err) {
    console.error(err);
    errorMessage.value = '에러가 발생했습니다. 다시 가입하십시오.';
    store.commit('clear');
  }
};
const handleSignUp = () => {
  router.push('sign-up');
};
</script>

<style lang="scss" scoped>
.sign-in-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 16px;
  padding-top: 20px;
  padding-bottom: 20px;
  margin: 0 auto;
  margin-top: 50px;
  background-color: #f9f9f9;
  max-width: 400px;
  border-radius: 12px;
  box-shadow: 2px 3px 15px -10px;
}
#error {
  font-size: small;
  font-weight: 500;
  color: red;
}
</style>
