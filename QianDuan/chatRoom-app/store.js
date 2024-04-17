// store/index.js
import Vue from 'vue'
import Vuex from 'vuex'
import { getStorageSync, setStorageSync } from '@dcloudio/uni-app';

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    token: null,
  },
  
  mutations: {
    SET_TOKEN(state, token) {
      state.token = token;
    },
  },

  actions: {
    // 设置token动作
    setToken({ commit }, token) {
      commit('SET_TOKEN', token);
      // 同时写入本地存储
      uni.setStorageSync('token', token);
	  console.log('存入token');
    },
	//设置用户Id
	setUserId({commit},userId){
		uni.setStorageSync('userId',userId);
		console.log('存入用户ID');
	},

    // 获取token动作
    getToken({ commit }) {
      // 首先尝试从Vuex的state中获取
      let token = this.state.token;
      
      // 如果state中没有token，则尝试从本地存储中获取
      if (!token) {
        token = getStorageSync('token');
        console.log('获取token' + token);
        // 如果从本地存储中找到了token，则同步到Vuex的state中
        if (token) {
          commit('SET_TOKEN', token);
        }
      }
      return token;
    },
	
	getUserId({commit}){
		let userId = uni.getStorageSync('userId');
		if(userId){
			console.log("获取用户ID成功");
		}
		return userId;
	},

    // 移除token的动作
    async removeToken({ commit }) {
      commit('SET_TOKEN', null);
      uni.removeStorageSync('token');
    },
  },
});

export default store;