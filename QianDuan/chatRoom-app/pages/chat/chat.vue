<template>
	<view>
		<!-- 聊天页 -->
		<view style="margin-top: 30upx;margin-left: 15upx; display: flex;">
			<image src="../../static/img/activate.png" style="height: 110upx; width: 110upx; border-radius: 50%; "></image>
			<view class="message">
				<text>消息</text>
			</view>
			
		</view>
		
		<!-- 输入框 -->
		<view style="width: 100vw; height: 80upx;margin-bottom: 20upx; display: flex;justify-content: flex-end;align-items: flex-end;">
			<text>{{chatMessage}}</text>
			<button></button>
		</view>
	</view>
</template>

<script>
	export default{
		data() {
			return{
				chatMessage: '111',
			}
		},
		onShow() {
			
		},
		methods: {
			getMessages(){
				
			}
		}
	}
</script>

<style>
	.message{
		margin-left: 10upx;
		flex: 1;
		background-color: #f1f1f1;
		padding: 30upx;
		border-radius: 10upx;
		max-width: 60vw;
	}
</style>