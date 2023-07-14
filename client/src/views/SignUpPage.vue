<template>
  <div class="box-container">
    <h2>회원가입</h2>
    <BeotInput
      id="name"
      type="text"
      placeholder="이름"
      :value="name"
      @input="onChangeName"
    />
    <div v-if="nameMessage" class="error">{{ nameMessage }}</div>
    <BeotInput
      id="nickname"
      type="text"
      placeholder="닉네임"
      :value="nickname"
      @input="onChangeNickname"
    />
    <div v-if="nicknameMessage" class="error">{{ nicknameMessage }}</div>
    <BeotInput
      id="birthday"
      type="date"
      :value="birthday"
      @input="onChangeBirthday"
    />
    <div v-if="birthdayMessage" class="error">{{ birthdayMessage }}</div>
    <div class="buttons">
      <BeotButton @click="signUp()">가입하기</BeotButton>
      <BeotButton @click="handleSignIn()">로그인 하기</BeotButton>
    </div>
    <div class="error" v-if="errorMessage">{{ errorMessage }}</div>
  </div>
</template>

<script lang="ts" setup>
import { computed, ref } from 'vue';
import { useStore } from 'vuex';
import axios from 'axios';
import { HttpStatus } from '@/common/constant';
import router from '@/router';
import BeotButton from '@/components/BeotButton.vue';
import BeotInput from '@/components/BeotInput.vue';

const store = useStore();
const name = ref('');
const nameMessage = ref('');
const nickname = ref('');
const nicknameMessage = ref('');
const birthday = ref('');
const birthdayMessage = ref('');
const errorMessage = ref('');

const isValidatedName = computed(
  () => name.value.length >= 2 && name.value.length <= 20
);
const isValidatedNickname = computed(
  () => nickname.value.length >= 3 && nickname.value.length <= 15
);
const handleSignIn = () => {
  router.push('sign-in');
};
const onChangeName = (event: any) => {
  name.value = event.target.value;
  if (!isValidatedName.value) {
    nameMessage.value = '이름은 2글자 이상, 20글자 이하만 가능합니다.';
  } else {
    nameMessage.value = '';
  }
};
const onChangeNickname = (event: any) => {
  nickname.value = event.target.value;
  if (!isValidatedNickname.value) {
    nicknameMessage.value = '닉네임은 3글자 이상, 15글자 이하만 가능합니다.';
  } else {
    nicknameMessage.value = '';
  }
};
const onChangeBirthday = (event: any) => {
  birthday.value = event.target.value ?? null;
};
const signUp = async () => {
  if (!isValidatedName.value || !isValidatedNickname.value) {
    errorMessage.value = '입력값을 다시 확인해 주세요.';
    return;
  }
  try {
    const response = await axios('http://localhost:8080/api/v1/auth/sign-up', {
      method: 'POST',
      data: {
        name: name.value,
        nickname: nickname.value,
        birthday: birthday.value,
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
      console.log('SignUp Success!');
    } else {
      errorMessage.value = '에러가 발생했습니다. 다시 가입하십시오.';
      store.commit('clear');
    }
  } catch (err) {
    console.error('error:', err);
    errorMessage.value = '에러가 발생했습니다. 다시 가입하십시오.';
    store.commit('clear');
  }
};
</script>
