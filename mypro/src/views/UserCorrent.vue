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
    <Footer></Footer>
    <div class="user-information">
      <ul class="user-information-form-box">
        <li>
          <div class="user-image">
            <button @click="selectFile(index)" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; background: transparent; border: none;"></button>
            <img src="../assets/R-C.jpg" alt="User Avatar">
            <div class="overlay" onclick="uploadAvatar()">头像不能修改哦</div> <!-- 叠加层 -->
          </div>
        </li>
        <li>
          <div class="content">
            <p>账号</p>
            <input type="text" id="username-login" :placeholder="PlaceholderData" v-model.trim="username">
          </div>
        </li>
        <li>
          <div class="content">
            <p>密码</p>
            <input type="password" id="password-login" :placeholder="editingPassword ? '新密码' : '******'" :disabled="!editingPassword" v-model.trim="newPassword">
            <div class="password-edit" @click="toggleEditPassword">
              <svg t="1709391573319" class="password-icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2766" width="20" height="20">
                <path d="M337.322667 720.810667a34.133333 34.133333 0 0 1-32.682667-43.861334l52.650667-176.213333a34.133333 34.133333 0 0 1 8.533333-14.336l398.336-398.336a34.133333 34.133333 0 0 1 48.298667 0l123.477333 123.477333a34.133333 34.133333 0 0 1 0 48.298667L537.6 658.176a34.133333 34.133333 0 0 1-14.336 8.533333L347.050667 719.36a31.658667 31.658667 0 0 1-9.728 1.450667z m82.858666-192.256l-32.085333 107.349333 107.349333-32.085333 368.128-368.128-75.264-75.264z" p-id="2767"></path>
                <path d="M812.117333 369.322667a34.133333 34.133333 0 0 1-24.149333-9.984L664.490667 235.861333A34.133333 34.133333 0 0 1 712.789333 187.733333L836.266667 311.04a34.133333 34.133333 0 0 1-24.149334 58.282667zM828.416 945.92H112.213333a34.133333 34.133333 0 0 1-34.133333-34.133333V195.584a34.133333 34.133333 0 0 1 34.133333-34.133333h376.661334a34.133333 34.133333 0 1 1 0 68.266666H146.346667v647.936h647.936V535.125333a34.133333 34.133333 0 1 1 68.266666 0v376.661334a34.133333 34.133333 0 0 1-34.133333 34.133333z" p-id="2768"></path>
              </svg>
            </div>
          </div>
        </li>
      </ul>
      <button class="log-out" @click="to('/Index')">Log Out </button>


      <el-dialog title="输入新密码" :visible.sync="dialogVisible" width="30%">
        <el-form :model="passwordForm" ref="passwordForm" label-width="80px">
          <el-form-item label="新密码" prop="password">
            <el-input type="password" v-model="passwordForm.password"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="confirmEditPassword">确定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</v-scale-screen>
</template>

<script>
import Footer from '../components/Footer.vue';
import axios from 'axios';

export default {
  name: 'UserCorrent',
  data() {
    return {
      currentUserType: this.$route.query.currentUserType,
      currentUserName: this.$route.query.currentUserName,
      editingPassword: false,
      newPassword: '',
      dialogVisible: false,
      passwordForm: {
        password: ''
      },
      KeyWord:''
    };
  },
  mounted() {
    // 页面加载时从localStorage读取用户类型
    if (this.currentUserName && this.currentUserType) {
                  this.currentUserType =  this.$route.query.currentUserType;
                  this.currentUserName = this.$route.query.currentUserName;
      }

						
    else{
      this.$router.push('/');
      return;
      /// 当组件挂载时，尝试从LocalStorage中获取保存的数据
    //   const savedData = JSON.parse(localStorage.getItem('userData'));
    // if (savedData) {
    //   // 如果有保存的数据，则恢复到组件的data中
    //   this.currentUserType = savedData.currentUserType;
    //   this.currentUserName = savedData.currentUserName;
    // }
    }

  },


  components: {
    Footer
  },
  methods: {

    to(path) {
            // this.$router.push(path);
            let stringWithSlash = path;
            this.KeyWord = stringWithSlash.replace(/\//g, '');

            this.$router.push({
					path: path,
					name: this.KeyWord,
					query: {
					currentUserType: this.currentUserType,
					currentUserName: this.currentUserName
					}
					});
        },
    toggleEditPassword() {
      this.dialogVisible = true;
    },
    confirmEditPassword() {
      const data = {
		    username:this.currentUserName,
        password: this.passwordForm.password
      };
	  // console.log("data");
	  // console.log(data);
      // 发送数据到后端
      axios.put('https://www.hxlogistics.top/to_url/Manager/', data)
        .then(response => {
          // console.log("response");
	        // console.log(response);
          if(response.data.manager_id!=-1){
            this.$message.success('密码修改成功');
             this.dialogVisible = false;
          }
          else{
            this.$message.error('密码修改失败');
          }

        })
        .catch(error => {
			    console.log(error);
          this.$message.error('密码修改失败');
        });
    }
  },
  computed: {
    PlaceholderData() {
      return this.currentUserName;
    },
    username() {
      // 如果正在编辑密码，禁用用户名输入
      return this.editingPassword ? this.currentUserName : '';
    }
  }
}
</script>






<style scoped>

.info{
	position: absolute;
	margin-left: 25.1%;
	margin-top:-12px;
	font-size: 8mm;
	width:350px;
	height:760px;
	display: flex;
	border: 1px solid #69e891; /* 添加边框 */
	align-items: center; /* 垂直居中对齐 */
	justify-content: center; /* 水平方向居中 */
	border-radius: 10px;
}

.info div{
	width: 150px; /* 默认宽度 */
	text-align: center; /* 文本水平居中 */
	display: flex;
	margin-left: 10px;
	margin-top: 6px;
	justify-content: center; /* 水平方向居中 */
}
.info div .info_img{
	background: linear-gradient(to right, #D3FFE7, #69e891); /* 线性渐变 */
	border-radius: 60px;
	width: 85px;
	height: 45px;
	align-items: center; /* 垂直居中对齐 */
	justify-content: center; /* 水平方向居中 */
}

.info div .info_title{
	font-size: 2mm;
	font-weight: 200;
	color: darkgrey;
	margin-left: -5px;
	margin-top: 4px;
	align-items: flex-start; /* 让子项左对齐 */
}

.info div .info_num{
	font-size: 5mm;
	font-weight: 500;
	color: #000;
	margin-top: 18px;
	margin-left: -40px;

}

.user-information{
	position: absolute;
	width: 72%;
	height: 600px;
	margin-left: 22%;
	margin-top: 1.8%;

}

.user-information .user-image {
    position: relative; /* 添加position: relative; 使叠加层相对于该元素定位 */
    margin-left: 22%;
    margin-top: 100px;
    width: 80px;
    height: 80px;
    border-radius: 50px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.user-information .user-image img {
    width: 100%;
    border-radius: 50px;
}

.overlay {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
	  border-radius: 50px;
    background-color: rgba(211, 211, 211, 0.7);
    display: none;
    text-align: center;
    line-height: 80px; /* 改为80px，与 .user-image 的高度相同 */
}

.user-information .user-image:hover .overlay {
    display: block;
}


.user-information-form-box{
	width: 100%;
	height: 200px;
	margin-top: 10%;
	font-size: 3mm;
	display: flex;
	flex-direction: column;
	justify-content: center;

}

.user-information-form-box li{
	display: flex;
	width:25%;
	margin-left: 38%;
	border: #e7e7e7;
	margin-bottom: 10px;

}

.user-information-form-box li .content{
	display: flex;
	flex-direction: row;
	padding: 10px;
	border: 1px solid #e7e7e7;
	height: 4vh;
	margin-bottom: 10px;
  margin-top: 2%;
	border-radius: 10px;
  font-size: 4mm;
	align-items: center; /* 垂直居中对齐 */
	justify-content: center; /* 水平方向居中 */
}

.user-information-form-box li  p{
	display: flex;
	width:50px;
	margin-left: 3%;
	color: darkgray;
}

.user-information-form-box li .content input{
	float: left;
	direction: ltl;
	border: none;
	margin-left: 25%;
	height: 15px;
	width: 60%;
	outline: none;
	pointer-events: none; /* 禁用输入框 */
  font-size: 4mm;
	opacity: 0.5; /* 添加一些透明度，使输入框看起来被禁用 */
}

.user-information-form-box li .content svg{
	position: absolute;
	display: flex;
	margin-left: -2%;
	margin-top: -1%;
}

.log-out{
	margin-left: 42.5%;
	margin-top: 10%;
	border: none;
	background-color: transparent;
	color: #F67A21;
	font-weight: 600;
	font-size: 8mm;
}

.log-out:hover{
	color:#f60101;
}
</style>.
