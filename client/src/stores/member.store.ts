import { createStore } from 'vuex';

export default createStore({
  state: {
    uuid: '',
    name: '',
    nickname: '',
    isLoggedIn: false,
  },
  getters: {
    getUUID(state) {
      return state.uuid;
    },
    getName(state) {
      return state.name;
    },
    getNickname(state) {
      return state.nickname;
    },
  },
  mutations: {
    setAll(state, value) {
      state.uuid = value.uuid;
      state.name = value.name;
      state.nickname = value.nickname;
      state.isLoggedIn = value.isLoggedIn;
    },
    setUUID(state, value) {
      state.uuid = value;
    },
    setName(state, value) {
      state.name = value;
    },
    setNickname(state, value) {
      state.nickname = value;
    },
    setIsLoggedIn(state, value) {
      state.isLoggedIn = value;
    },
    clear(state) {
      state.name = '';
      state.nickname = '';
      state.uuid = '';
    },
  },
});
