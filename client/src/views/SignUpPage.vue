<template>
  <h2 align="center">회원가입</h2>
  <div class="sign-up-container">
    <div>
      <input
        id="name"
        type="text"
        placeholder="이름"
        :value="name"
        @input="onChangeName"
      />
      <div id="error">{{ nameMessage }}</div>
    </div>
    <div>
      <input
        id="nickname"
        type="text"
        placeholder="닉네임"
        :value="nickname"
        @input="onChangeNickname"
      />
      <div id="error">{{ nicknameMessage }}</div>
    </div>
    <div>
      <input
        id="birthday"
        type="date"
        :value="birthday"
        @input="onChangeBirthday"
      />
      <div id="error">{{ birthdayMessage }}</div>
    </div>
    <div>
      <button @click="signUp()">가입하기</button>
      <button @click="handleSignIn()">로그인 하기</button>
    </div>
    <div id="error" v-if="errorMessage">{{ errorMessage }}</div>
  </div>
</template>

<script lang="ts" setup>
import { computed, ref } from 'vue';
import { useStore } from 'vuex';
import axios from 'axios';
import { HttpStatus } from '@/common/constant';
import router from '@/router';

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
const onChangeName = (event) => {
  name.value = event.target.value;
  if (!isValidatedName.value) {
    nameMessage.value = '이름은 2글자 이상, 20글자 이하만 가능합니다.';
  } else {
    nameMessage.value = '';
  }
};
const onChangeNickname = (event) => {
  nickname.value = event.target.value;
  if (!isValidatedNickname.value) {
    nicknameMessage.value = '닉네임은 3글자 이상, 15글자 이하만 가능합니다.';
  } else {
    nicknameMessage.value = '';
  }
};
const onChangeBirthday = (event) => {
  birthday.value = event.target.value ?? null;
};
const signUp = async () => {
  if (!isValidatedName.value || !isValidatedNickname.value) {
    errorMessage.value = '입력값을 다시 확인해 주세요.';
    return;
  }
  try {
    const data = await axios('http://localhost:8080/api/v1/auth/sign-up', {
      method: 'POST',
      data: {
        name: name.value,
        nickname: nickname.value,
        birthday: birthday.value,
      },
    });
    if (data.status === HttpStatus.CREATED) {
      router.push('/');
      store.commit('setAll', {
        uuid: data.data,
        name: name.value,
        nickname: nickname.value,
        isLoggedIn: true,
      });
      console.log('SignUp Success!');
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
</script>

<style lang="scss" scoped>
.sign-up-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 16px;
  div {
    display: flex;
    flex-direction: column;
    gap: 4px;
    width: 300px;
  }
}

#error {
  font-size: small;
  font-weight: 500;
  color: red;
}
</style>
