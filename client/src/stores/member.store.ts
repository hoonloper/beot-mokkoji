import { createStore } from 'vuex';

export default createStore({
  state: {
    id: '',
    name: '',
    nickname: '',
    isLoggedIn: false,
  },
  getters: {
    getId(state) {
      return state.id;
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
      state.id = value.id;
      state.name = value.name;
      state.nickname = value.nickname;
      state.isLoggedIn = value.isLoggedIn;
    },
    setId(state, value) {
      state.id = value;
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
      state.id = '';
      state.isLoggedIn = false;
    },
  },
});
