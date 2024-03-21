<template>
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
			<div class="button-login" >
				<button  @click="login">登录</button>
			</div>
</div>
</template>
<script>
import { getCurrentScope } from 'vue';
import Footer from '../components/Footer.vue';
import axios from 'axios';

export default {
  name: 'Login',
  data() {
    return {
      username: '',
      password: ''
    }
  },
  methods: {
    login() {
      const data = {
        username: this.username,
        password: this.password
      };
      axios.post('http://47.98.58.79:8080/Manager/find', data)
        .then(response => {
          let user = response.data.result;

			console.log(user);
          if (user.type === -1) {
            alert('账号或密码不正确！');
          } else {
            // 将用户数据保存到 session storage 中
            sessionStorage.setItem('user', JSON.stringify(user));
            // 根据管理员类型决定重定向到不同页面
            if (user.type === 1 || user.type === 0) {
              this.$router.push('/Index');
            } else if (user.type === 2) {
              this.$router.push('/Data');
            }
          }
        })
        .catch(error => {
          // 处理请求错误
          console.error(error);
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
	margin-right: 40%;
	flex-direction: row;
	border: #e7e7e7;
}

.form-box svg{
	margin-top: -0.5%;
	float: left;
}

.form-box li .content{
	padding: 10px;
	border: 1px solid #e7e7e7;
	height: 15px;
	margin-bottom: 10px;
	border-radius: 10px;
}

.form-box li .content input{
	float: left;
	direction: ltl;
	border: none;
	margin-left: 20px;
	height: 15px;
	outline: none;
}

.button-login{
	width: 17.5%;
	height: 30px;
	position: absolute;
	margin-right: 3%;
	margin-left:42%;
	margin-top: -2%;
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

</style>../components/Footer.vue
