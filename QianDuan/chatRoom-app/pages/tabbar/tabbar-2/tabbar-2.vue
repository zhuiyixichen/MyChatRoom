<template>
	<view class="content">
		<!-- 好友页 -->
		<!-- <view class="user"  > -->
			<view class="users" v-for="(friend,index) in this.friends" :key="index" :style="{ backgroundColor: friend.isActive ? 'gray' : 'white' }" @tap="onclick(index)">
				<image style="width: 110upx; height: 110upx; border-radius: 50%;" :src="friend.user.avatarUrl"></image>
				<view>
					<view>
						<text>
							{{friend.friendRemarkName}}
						</text>
					</view>
					<view>
						<text style="font-size: 20upx;">
							{{friend.status ? '在线' : '离线'}}
						</text>
					</view>
				</view>
			</view>
		<!-- </view> -->
	</view>
</template>

<script>
	import httpRequest, { downloadImage, queryFriends } from '../../../httpRequest.js'
	import store from '../../../store.js'
	export default {
		data() {
			return {
				title: 'Hello',
				friends: [{
					appendMsg: '',
					createTime: '',
					friendId: '',
					friendRemarkName: '',
					id: '', //好友关系列表的ID
					userId: '',//用户ID
					status: '',
					updateTime: '',
					user: {
						avatarUrl: '',
						image: '',
						birthday: '',
						createTime: '',
						email: '',
						id: '',
						lastLoginTime: '',
						name: '',
						password: '',
						phone: '',
						signature: '',
						status: '',
						username: ''
					},
					isActive: true,
				}],
			}
		},
		onLoad() {
		},
		onShow() {
			this.queryFriendsMethod();
		},
		methods: {
			queryFriendsMethod(){
				store.dispatch('getUserId').then(userId =>{
					queryFriends(userId).then(data =>{
						this.friends = data[1].data.data;
						console.log(this.friends);
					})
				})
				
			},
			queryImage(path){
				const base = ''
				downloadImage(path).then(res =>{
					// console.log();
					this.base =  'data:image/jpeg;base64,' + res[1].data
					
				})
				return this.base;
			},
			onclick(index){
				console.log('1231');
				this.friends.forEach((item) =>{
					item.isActive = false;
				});
				this.friends[index].isActive = true;
				console.log(this.friends[index]);
				uni.navigateTo({
					url: '/pages/chat/newChat'
				})
				setTimeout(() =>{
					this.friends[index].isActive = false;
				},1000)
			},
		}
	}
</script>

<style>
	.content {
		text-align: center;
		height: 20upx;
		margin-top: 20upx;
	}
	.user{
		height: 10vh;
		margin-top: 2vh;
	}
	.users{
		display: flex;
		width: 100vw;
		height: 10vh;
		margin-top: 2vh;
	}
</style>
