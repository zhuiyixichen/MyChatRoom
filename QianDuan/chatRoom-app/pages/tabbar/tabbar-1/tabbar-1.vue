<template>
	<view class="content">
		<!-- 消息页 -->
		
		<view class="messages" :style="{ backgroundColor: item.isActive ? 'gray' : 'white' }"  v-for="(item,index) in items" :key="index" @tap="onclick(index)">
			<view>
				<image class="avatars" src="../../../static/img/activate.png" ></image>
			</view>
			<view class="contents">
				<view>
					<text style="font-size: 45upx;">
						hello
					</text>
				</view>
				<view style="margin-top: 10upx; font-size: ;">
					<text style="color: gray;">
						你好
					</text>
				</view>
			</view>
			
		</view>
	</view>
</template>

<script>
export default {
	data() {
		return {
			title: 'Hello',
			clicked: false,
			items: [{ isActive: false }, { isActive: false }, { isActive: false }],
		};
	},
	onLoad() {},
	methods: {
		onclick(index){
			console.log('点击');
			// 清除所有项的激活状态
			  this.items.forEach((item) => {
				item.isActive = false;
			  });
		
			  // 将当前项设置为激活状态
			  this.items[index].isActive = true;
		
			  // 延迟恢复原色
			  setTimeout(() => {
				this.items[index].isActive = false;
				
				uni.navigateTo({
					url: '/pages/chat/newChat'
				})
			  }, 100);
			
		}
	}
};
</script>

<style>
.content {
	text-align: center;
	height: 400upx;
	margin-top: 20upx;
}
.avatars{
	margin-top: 20upx;
	margin-left: 20upx;
	width: 110upx;
	height: 110upx;
	border-radius: 50%;
}
.contents{
	margin-left: 5vw;
}
.messages{
	display: flex;
}
</style>
