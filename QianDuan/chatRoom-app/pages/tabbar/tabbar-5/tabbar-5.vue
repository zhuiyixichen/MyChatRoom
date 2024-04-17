<template>
	<view class="content" style="background-image: linear-gradient(-25deg, #baecf1, white); z-index: 1; width: 100vw;height: 100vh">
		<!-- <view>
			<button @click="chooseImage" style="background-color: aqua; width: 100vw;height: 20vh;"></button>
		</view>
		<view>
			<image :src="imagePath" style="width: 400upx;height: 400upx;"></image>
		</view> -->
		<view style="display: flex; z-index: 10;">
			<image :src="this.user.avatarUrl" class="userImage" @click="chooseImage()"></image>
			<view style="margin-left: 60upx;margin-top: 30upx;">
				<text style="font-size: 50upx; margin-top: 40upx;">{{this.user.name}}</text>
				<view>
					<text style="font-size: 35upx; color: dimgray;">用户名：</text>
					<text style="font-size: 30upx; color: dimgrey;">{{this.user.username}}</text>
				</view>
			</view>
		</view>
		
		<view style="margin-top: 80upx; z-index: 10;">
			<view style="margin-top: 15upx;">
				<view style="background-color: white; height: 90upx; width: 95vw;margin-left: 20upx; display: table-cell; vertical-align: middle; ">
					<view style="display: flex;">
					<text style="margin-left: 37upx; width: 80vw;">修改密码</text>
					<image src="../../../static/img/arrow.svg" style="height: 40upx;width: 40upx; margin-top: 10upx;"></image>
					</view>
				</view>
			</view>
			<view style="margin-top: 15upx;">
				<view style="background-color: white; height: 90upx; width: 95vw; display: table-cell; vertical-align: middle; ">
					<view style="display: flex;">
					<text style="margin-left: 37upx; width: 80vw;">修改密码</text>
					<image src="../../../static/img/arrow.svg" style="height: 40upx;width: 40upx; margin-top: 10upx;"></image>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
import { login, queryUserinfo, uploadImage } from '../../../httpRequest';
import store from '../../../store.js'
import httpRequest from '../../../httpRequest.js'
	export default {
		data() {
			return {
				title: 'Hello',
				imagePath: '',
				user:{
					avatarUrl: '',
					birthday: '',
					createTime: '',
					email: '',
					id: '',
					name: '',
					password: '',
					phone: '',
					signature: '',
					username: ''
				},
			}
		},
		onLoad() {
			this.getUserinfo();
			// this.downloadImage();
		},
		onShow(){
			
		},
		methods: {
			getUserinfo(){
				store.dispatch('getUserId').then(userId =>{
					queryUserinfo(userId).then(data =>{
						this.user = data[1].data.data
						console.log(this.user);
					})
				})
			},
			chooseImage(){
				uni.chooseImage({
					count: 1,
					success: (res) =>{
						const tempFilePath = res.tempFilePaths;
						console.log(res.tempFiles[0]);
						// uni.uploadFile({
						// 	url: 'http://localhost:8080/file/upload',
						// 	filePath: tempFilePath[0],
						// 	name: 'multipartFile',
						// 	success: (uploadFileRes) => {
						// 		console.log(uploadFileRes)
						// 	}
						// })
						const tempFile = res.tempFiles[0];
						const formData = new FormData();
						formData.append('image',tempFile,tempFile.name);
						uploadImage(formData).then(data =>{
							console.log(data);
						})
						
					}
				})
			},
			downloadImage(){
				uni.request({
					methods: 'GET',
					url: 'http://localhost:8080/file/download',
					data: {
						'path': 'E:\\MyChatRoom\\images\\preview20240331124647.jpg',
					},
					success: (res) => {
						this.imagePath = 'data:image/jpeg;base64,' + res.data;
					}
				})
			}
		}
	}
</script>

<style>
	.content {
		/* text-align: center; */
		/* margin-top: 20upx; */
	}
	.userImage{
		margin-left: 60upx;
		height: 150upx;
		width: 150upx;
		border-radius: 50%;
	}
</style>
