// import axios from 'axios';
import store from './store'; // 如果你的store在当前文件夹下，如果不是请相应调整路径

// 获取存储的token
// const getToken = () => store.state.token || '';
// const getToken = () => uni.getStorageSync('token');
export const ws = 'ws://localhost:8080/ws/'; 
// 设置axios的默认请求头
// axios.defaults.baseURL = 'https://localhost:8080/'; // 设置请求前缀
// axios.defaults.headers.common['Authorization'] = `Bearer ${getToken()}`;

//通过uni.request设置请求头
const baseURL = 'http://localhost:8080/user/';
const fileUrl = 'http://localhost:8080/file/';
const headers = {
	token: uni.getStorageSync('token')
};
const postHeaders = {
	// token: `Bearer ${getToken()}`,
	token: uni.getStorageSync('token')
}
/**
 * 创建各种api
 */
//登录
export const login = (params) => uni.request({
	url:baseURL + 'login',
	method:'POST',
	header: postHeaders,
	data: params
});

//查询好友
export const queryFriends = (userId) => uni.request({
	url: baseURL + 'queryFriends',
	method: 'GET',
	header: headers,
	data: {
		userId : userId
	}
})

//查询用户信息
export const queryUserinfo = (userId) => uni.request({
	url:baseURL + 'queryUserByUserId',
	method: 'GET',
	header: headers,
	data: {
		userId : userId
	}
})

//连接webSocket
export const socketOnOpen = (userId) => uni.connectSocket({
	url: ws + (userId)
})
//发送信息
// export const sendMessage = (msg) => uni.request({
// 	url:
// })

//上传图片
export const uploadImage = (multipartFile) => uni.request({
	method: 'POST',
	url: fileUrl + 'upload',
	header: {
		'Content-Type': 'multipart/form-data',
		'token': uni.getStorageSync('token'),
	},
	data: {
		'multipartFile': multipartFile,
	},
})

//下载图片
export const downloadImage = () => uni.request({
	methods: 'GET',
	url: 'http://localhost:8080/file/download',
	data: {
		'path': 'E:\\MyChatRoom\\images\\preview20240331124647.jpg',
	}
})
// export const login = (username, password) => axios.post('/login', { username, password });

export const logout = () => axios.post('/logout').then(() => store.dispatch('removeToken')); // 调用store中的action移除token

// 其他更多的API请求...

// 注意：如果token会在应用运行期间动态改变，你可能需要一个方法来实时更新请求头中的token
export const updateTokenInHeader = (newToken) => {
  axios.defaults.headers.common['Authorization'] = `Bearer ${newToken}`;
};

// 并在store的mutation或action中调用这个方法
// ...