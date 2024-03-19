<template>
	<v-scale-screen  width="1920"
    height="1080"
    :delay="500"
    :fullScreen="false"
    :boxStyle="{
      background: '#fff',
      overflow: isScale ? 'hidden' : 'auto',
    }"
    :wrapperStyle="wrapperStyle"
    :autoScale="isScale"
>
	<div class="wrapper">
	  <img src="../assets/logo.jpg" class="login-logo">
	  <ul class="form-box">
		<li>
		  <div class="content">
			<svg t="1709387651504" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2468" width="20" height="20"><path d="M913.066667 804.352H110.933333a34.133333 34.133333 0 0 1-34.133333-34.133333V253.781333a34.133333 34.133333 0 0 1 34.133333-34.133333h802.133334a34.133333 34.133333 0 0 1 34.133333 34.133333v516.437334a34.133333 34.133333 0 0 1-34.133333 34.133333z m-768-68.266667h733.866666V287.914667H145.066667z" p-id="2469"></path><path d="M512 580.778667a34.133333 34.133333 0 0 1-20.138667-6.570667L90.794667 281.6A34.133333 34.133333 0 0 1 110.933333 219.648h802.133334a34.133333 34.133333 0 0 1 20.138666 61.952l-401.066666 292.864a34.133333 34.133333 0 0 1-20.138667 6.314667zM215.552 287.914667L512 504.405333l296.448-216.490666z" p-id="2470"></path></svg>
			<input type="text" id="username-login" v-model="username" placeholder="请输入账号">
		  </div>
		</li>
		<li>
		  <div class="content">
			<svg t="1709387731252" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2617" width="20" height="20"><path d="M855.466667 946.261333h-686.933334a34.133333 34.133333 0 0 1-34.133333-34.133333v-435.2a34.133333 34.133333 0 0 1 34.133333-34.133333h686.933334a34.133333 34.133333 0 0 1 34.133333 34.133333v435.2a34.133333 34.133333 0 0 1-34.133333 34.133333z m-652.8-68.266666h618.666666v-366.933334h-618.666666z" p-id="2618"></path><path d="M733.866667 511.146667H290.133333a34.133333 34.133333 0 0 1-34.133333-34.133334V318.634667A241.152 241.152 0 0 1 496.810667 77.738667h30.378666A241.152 241.152 0 0 1 768 318.634667V477.013333a34.133333 34.133333 0 0 1-34.133333 34.133334z m-409.6-68.266667h375.466666V318.634667a172.8 172.8 0 0 0-172.544-172.629334h-30.378666A172.8 172.8 0 0 0 324.266667 318.634667zM653.397333 794.368H370.602667a34.133333 34.133333 0 0 1 0-68.266667h282.794666a34.133333 34.133333 0 0 1 0 68.266667z" p-id="2619"></path></svg>
			<input type="password" id="password-login" v-model="password" placeholder="请输入密码">
		  </div>
		</li>
	  </ul>
	  <div class="button-login">
		<button :disabled="loginProcessing" @click="login()">登录</button>
	  </div>
	</div>
</v-scale-screen>
  </template>
  
  <script>
  import Vue from 'vue'
  import { Message } from 'element-ui';
  import Footer from '../components/Footer.vue';
  import axios from 'axios';
  Vue.prototype.$message = Message;
  
  export default {
	name: 'Index',
	data() {
	  return {
		username: '',
		password: '',
		loginSuccess: false,
		currentUserType: '',
		currentUserName: '',
		loginProcessing: false // Add loginProcessing flag
	  }
	},
	
	watch: {
		// 监听data中的变化，保存到LocalStorage中
		currentUserType(value) {
		localStorage.setItem('userData', JSON.stringify(this.$data));
		},
		currentUserName(value) {
		localStorage.setItem('userData', JSON.stringify(this.$data));
		}
		
	},
	methods: {
	  login() {
		// Prevent multiple clicks while processing login
		if (this.loginProcessing) return;
		this.loginProcessing = true; // Disable the login button
		const data = {
		  username: this.username,
		  password: this.password
		};
		const url = 'http://47.98.58.79:8080/Manager/find';
		axios.post(url, {
			username: data.username,
			password: data.password
		  })
		  .then((res) => {
			if (res.data.manager_id != -1) {
				localStorage.setItem('token', JSON.stringify(true));
			  const managerType = res.data.type;
			  this.loginSuccess = true;
			  localStorage.setItem('currentUserType', res.data.type);
			  this.currentUserType = localStorage.getItem('currentUserType');
			  localStorage.setItem('currentUserName', res.data.name);
			  this.currentUserName = localStorage.getItem('currentUserName');
			  this.$message({
				message: '登录成功',
				type: 'success',
				duration: 1500 // 将消息显示时间设置为 2 秒
			  });
			 
			  this.$nextTick(() => {
				this.$router.push('/OpHome');
				if (managerType === 1 || managerType === 0) {
				  this.$router.push('/OpHome');
				} else if (managerType === 2) {
				  this.$router.push('/Data');
				}
			  });
			} else {
			  this.$message({
				message: '账号或密码错误',
				type: 'error'
			  });
			}
		  })
		  .catch(error => {
			if (error && error.response) {
			  this.$message({
				message: '发生错误: ' + error.response.data.message,
				type: 'error'
			  });
			} else {
			  this.$message({
				message: '网络错误',
				type: 'error'
			  });
			}
		  })
		  .finally(() => {
			this.loginProcessing = false; // Re-enable the login button
		  });
	  }
	},
	components: {
	  Footer
	}
  }
  </script>



<style scoped>
.login-logo{
	width: 30%;
	height: auto;
	display: flex;
	margin-top:10%;
	margin-left: 35%;
}


.form-box{
	width: 100%;
	height: 200px;
	margin-top: 0%;
	font-size: 3mm;
	display: flex;
	flex-direction: column;
	justify-content: center;
	display: flex;
	align-items: flex-end; /* 将容器内元素右对齐 */
}

.form-box li{
	display: flex;
	width: 20%;
	margin-right: 40%;
	flex-direction: row;
	border: #e7e7e7;
}

.form-box svg{
	margin-top: 0.5%;
	float: left;
}

.form-box li .content{
	padding: 10px;
	border: 1px solid #e7e7e7;
	height: 4vh;
	width: 100%;
	margin-left: 2%;
	margin-bottom: 10px;
	border-radius: 10px;
}

.form-box li .content input{
	float: left;
	direction: ltl;
	border: none;
	margin-left: 20px;
	height:auto;
	width: 80%;
	margin-top: 0.3%;
	outline: none;
	position: absolute;
	font-size: 4mm;
}

.button-login{
	width: 20%;
	height: 6vh;
	position: absolute;
	margin-right: 3%;
	margin-left:40.2%;
	margin-top: 2%;
	border: none;
	border-radius: 5px;
	background-color: #282F87; /* 设置背景色 */
	
}


.button-login:hover{
	background-color: #74acff;
	border: 1px solid #74acff;
}

.button-login button{
	background-color: transparent;
	border: none;
	color: #fff; /* 设置文字颜色 */
	margin-top: 2%;
	margin-left: 40%;
}

</style>