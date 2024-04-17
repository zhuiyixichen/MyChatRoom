<script>
	import store from '@/store'; // 导入你的store实例
	
	
	// 假设你有这样一个函数来检查token是否过期
	function isTokenExpired(token) {
	  // 这里只是一个示例，实际逻辑可能包括解码JWT并检查exp字段，或者向后端API发送请求验证token有效性
	  // 返回true表示token过期，false表示未过期
	  // 实现这个函数的具体逻辑要根据你的业务需求和后端API设计来编写
	  // ...
	  // 示例简化逻辑，仅用于说明，实际需替换为真实验证过程
	  if (!token || token === '') {
	    return true;
	  }
	  
	  // 实际逻辑应该检查token的有效截止时间等信息
	  // 这里仅作演示，假设token为空就认为过期
	  return false;
	}
	
export default {
	onLaunch: function() {
		console.log('App Launch');

		setTimeout(() => {
			uni.setTabBarBadge({
				index: 1,
				text: '31'
			});
			uni.showTabBarRedDot({
				index: 3
			});
		}, 1000);
	},
	onShow: function() {
		console.log('App Show');
	},
	onHide: function() {
		console.log('App Hide');
	},
	onLaunch(options) {
	    // 从store中获取token
	    // const token = this.$store.state.token;
		const token = uni.getStorageSync('token');
		console.log(token);
	    // 检查token是否过期
	    if (isTokenExpired(token)) {
	      // 如果过期，清空token并跳转到登录页面
	      // store.dispatch('removeToken');
		  uni.setStorageSync('token','');
	      uni.navigateTo({
	      	url: '/pages/login/login'
	      }) // 替换为你的登录页面路径
	    } else {
	      // 如果token有效，执行其他初始化逻辑
	      // ...
	    }
		store.dispatch('getUserId').then(userId =>{
			this.$socket.init(userId)
		})
	  }
	
};
</script>

<style>
/*每个页面公共css */ 
</style>
