<template>
	<!-- 登录页面 -->
	<view class="flex-col flex-auto group">
	  <!-- <text class="self-center text_2" v-if="type==1000">手机号登录</text> -->
	  <text class="self-center text_2" v-if="type==2000">账号登录</text>
	  <!-- 手机号登录 -->
	  <view class="" v-if="type==1000">
		<view class="flex-col justify-start items-start text-wrapper">
		  <input type="text" class="font_1" @input="onInput" placeholder="请输入手机号" v-model="phoneNumber">
		</view>
		<!-- svg画线 -->
		<view style="height: 1px;">
			<svg xmlns="http://www.w3.org/2000/svg" version="1.1">
			  <line x1="0" y1="0" x2="400" y2="0" style="stroke:rgb(0,0,0);stroke-width:0.2" />
			</svg>
		</view>
		
		<view class="flex-col justify-start items-start text-wrapper_2">
		  <input type="text" password="true" class="font_1" @input="onInput"  placeholder="请输入密码" v-model="phonePassword">
		</view>
		
		<!-- svg画线 -->
		<view style="height: 1px;">
			<svg xmlns="http://www.w3.org/2000/svg" version="1.1">
			  <line x1="0" y1="0" x2="400" y2="0" style="stroke:rgb(0,0,0);stroke-width:0.2" />
			</svg>
		</view>
	  </view>
	  <!-- 账号登录 -->
	  <view class="" v-if="type==2000">
		<view class="flex-col justify-start items-start text-wrapper">
		  <input type="text" class="font_1" @input="onInput" placeholder="请输入用户名" v-model="idNumber">
		</view>
		<!-- svg画线 -->
		<view style="height: 1px;">
			<svg xmlns="http://www.w3.org/2000/svg" version="1.1">
			  <line x1="0" y1="0" x2="400" y2="0" style="stroke:rgb(0,0,0);stroke-width:0.2" />
			</svg>
		</view>
		<view class="flex-col justify-start items-start text-wrapper_2">
		  <input type="text" password="true" class="font_1" @input="onInput" placeholder="请输入用户密码" v-model="idPassword">
		</view>
		<!-- svg画线 -->
		<view style="height: 1px;">
			<svg xmlns="http://www.w3.org/2000/svg" version="1.1">
			  <line x1="0" y1="0" x2="400" y2="0" style="stroke:rgb(0,0,0);stroke-width:0.2" />
			</svg>
		</view>
	  </view>
	  
	  <view class="self-start font_1 text_3"  v-if="type==1000">用账号登录</view>
	  <!-- <view class="self-start font_1 text_3" @click="type=1000" v-if="type==2000">用手机号登录</view> -->
	  <!-- 登录按钮1 -->
	  <view class="flex-col justify-start items-center button" >
		  <button class="font_1 text_4" @click="onSubmit">登 录</button>
	  </view>
	  <!-- 登录按钮2 -->
<!-- 	  <view class="flex-col justify-start items-center button2" v-else>
		  <button class="font_1 text_66">登 录</button>
	  </view> -->
	  
	  
	  <view class="flex-row justify-between group_2" v-if="type==1000">
		<text class="font_2" @click="onPageJump('/pages/login/phoneVerify')">短信验证码登录</text>
		<text class="font_2" @click="onPageJump('/pages/login/findBack')">找回密码</text>
	  </view>
	  <view class="flex-row justify-between group_3" v-else>
		<text class="font_2" @click="onPageJump('/pages/login/findBack')">找回密码</text>
	  </view>
	</view>
 
</template>
 
<script>
	import httpRequest, { login, socketOnOpen, ws } from '../../httpRequest.js'
	import store from '../../store.js'
	export default {
		components:{
			
		},
		data() {
			return {
				type: 2000,						//判断登录类型手机登录或账号登录
				phoneNumber:'',					//手机账号
				phonePassword:'',				//手机密码
				idNumber:'',					//账号
				idPassword:'',					//账号密码
				btnShow:false,					//判断登录按钮显示隐藏
				timeOut:null,				//添加定时器
				user:{
					id: '',
					username: '',
					password: '',
					name: '',
					lastLoginTime: '',
					phone: '',
					token: '',
					signature: '',
					status: '',
					avatarUrl: '',
					birthday: '',
					createTime: '',
					meail: ''
				}
			}
		},
		onLoad() {
 
		},
		created() {
			
		},
		// 方法
		methods: {
			// 找回密码跳转页面
			onPageJump(url) {
				uni.navigateTo({
					url: url
				});
			},
			// 判断显示登录按钮
			onInput() {
				this.timeOut && clearTimeout(this.timeOut)
				this.timeOut = setTimeout(() => {
					if (this.type == 1000) {
						if (this.phoneNumber && this.phonePassword) {
							this.btnShow = true;
						} else {
							this.btnShow = false;
						}
					} else {
						if (this.idNumber && this.idPassword) {
							this.btnShow = true;
						} else {
							this.btnShow = false;
						}
					}
				}, 100);
			},
			
			// 点击登录
			onSubmit(){

				// 判断验证账号
				if(!this.idNumber){
					uni.showToast({
						title: '请输入账号',
						icon: 'none',
					});
					return;
				}
				// 判断验证账号密码
				if(!this.idPassword){
					uni.showToast({
						title: '请输入账号密码',
						icon: 'none',
					});
					return;
				}
				const user = {
					username: this.idNumber,
					password: this.idPassword
				}
				login(user).then(data =>{
					console.log(data[1].data.data);
					if(data[1].data.code == 1){
						//存入token
						// const token = data[1].data.data.token;
						// store.dispatch('setToken',token);
						uni.setStorageSync('token',data[1].data.data.token)
						console.log('登陆成功');
						this.user = data[1].data.data;
						//存入ID
						store.dispatch('setUserId',this.user.id);
						uni.setStorageSync('userId',this.user.id);
						console.log(uni.getStorageSync('userId'));
						//跳转到消息页
						uni.navigateBack({
						  delta: 1 // 返回上一级页面
						}).then(() => {
						  // 在页面关闭后执行跳转					  
						  uni.switchTab({
						    url: '/pages/tabbar/tabbar-1/tabbar-1'
						  });
						});
						
						//创建websocket连接
						this.$socket.init(this.user.id)
					}else{
						console.log('登录失败');
					}
				}).catch(error => {
					console.log(error);
				})
				
			},
			
		}
	}
</script>
 
<style>
	@import"./login.css";
</style>