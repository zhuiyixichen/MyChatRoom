import App from './App.vue'
import store from 'store.js'
// #ifndef VUE3
import Vue from 'vue'
Vue.config.productionTip = false
App.mpType = 'app'

// 创建WebSocket实例
let socket;
const initSocket = (userId) => {
	if(!userId){
		console.log('id不存在');
	}else{
	  socket = uni.connectSocket({
		url: `ws://localhost:8080/ws/${userId}`,
		success: function () {
		  console.log('WebSocket连接成功');
		},
		fail: function () {
		  console.log('WebSocket连接失败');
		},
		// complete: function () {},
		// onClose: function () {
		//   // 断线重连处理
		//   // setTimeout(initSocket, 5000); // 示例：5秒后尝试重新连接
		// }
	  });
	}
};

Vue.prototype.$socket = {}; // 创建全局对象
Vue.prototype.$socket.init = initSocket;

const app = new Vue({
	...App,
	store
})
app.$mount()
// #endif


// #ifdef VUE3
import {
	createSSRApp
} from 'vue'
export function createApp() {
	const app = createSSRApp(App)
	return {
		app
	}
}
// #endif
