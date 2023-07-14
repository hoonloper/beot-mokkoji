<template>
  <div class="sign-container">
    <h2>로그인</h2>
    <BeotInput
      id="name"
      type="text"
      placeholder="이름"
      :value="name"
      @input="(event:any) => (name = event.target.value)"
      @keyup.enter="signIn()"
    />
    <BeotInput
      id="nickname"
      type="text"
      placeholder="닉네임"
      :value="nickname"
      @input="(event:any) => (nickname = event.target.value)"
      @keyup.enter="signIn()"
    />
    <div class="buttons">
      <BeotButton @click="signIn()">로그인</BeotButton>
      <BeotButton @click="handleSignUp()">회원가입</BeotButton>
    </div>
    <div class="error" v-if="errorMessage">{{ errorMessage }}</div>
  </div>
</template>

<script lang="ts" setup>
import { HttpStatus } from '@/common/constant';
import router from '@/router';
import { ref } from 'vue';
import { useStore } from 'vuex';
import axios from 'axios';
import BeotButton from '@/components/BeotButton.vue';
import BeotInput from '@/components/BeotInput.vue';

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
    if (response.status === HttpStatus.CREATED) {
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
