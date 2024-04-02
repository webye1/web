<template>

    <div class="wrapper-navigation">
        <div class="navigation">
            <div class="logo">华信物流</div>
            <ul>
                <li 
                :class="{ 'active': $route.path === '/OpHome' }"
                @click="to('/OpHome')"   v-if="this.currentUserType==='0' || this.currentUserType==='1'"
                >
                   <svg t="1708876136028" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1066" width="35" height="35" style="vertical-align: middle;"><path d="M395.008 465.92H185.4976A81.152 81.152 0 0 1 104.448 385.0752V175.5648a81.2032 81.2032 0 0 1 81.0496-81.0496h209.5104A81.152 81.152 0 0 1 476.16 175.5648v209.5104A81.152 81.152 0 0 1 395.008 465.92zM185.4976 155.9552a19.6608 19.6608 0 0 0-19.6096 19.6096v209.5104A19.6608 19.6608 0 0 0 185.4976 404.48h209.5104A19.6608 19.6608 0 0 0 414.72 385.0752V175.5648a19.6608 19.6608 0 0 0-19.6096-19.6096zM847.6672 465.92h-209.4592a81.152 81.152 0 0 1-81.0496-81.0496V175.5648a81.152 81.152 0 0 1 81.0496-81.0496h209.4592a81.152 81.152 0 0 1 81.0496 81.0496v209.5104A81.152 81.152 0 0 1 847.6672 465.92z m-209.4592-309.9648a19.6096 19.6096 0 0 0-19.6096 19.6096v209.5104a19.6096 19.6096 0 0 0 19.6096 19.4048h209.4592a19.6608 19.6608 0 0 0 19.6096-19.6096V175.5648a19.6608 19.6608 0 0 0-19.6096-19.6096zM395.008 914.1248H185.4976a81.152 81.152 0 0 1-81.0496-81.0496v-209.5104A81.2032 81.2032 0 0 1 185.4976 542.72h209.5104A81.152 81.152 0 0 1 476.16 623.5648v209.5104a81.152 81.152 0 0 1-81.152 81.0496zM185.4976 604.16a19.6608 19.6608 0 0 0-19.6096 19.6096v209.5104a19.6608 19.6608 0 0 0 19.6096 19.6096h209.5104a19.6608 19.6608 0 0 0 19.712-19.8144v-209.5104a19.6608 19.6608 0 0 0-19.712-19.6096zM847.6672 914.1248h-209.4592a81.152 81.152 0 0 1-81.0496-81.0496v-209.5104A81.152 81.152 0 0 1 638.208 542.72h209.4592a81.152 81.152 0 0 1 81.0496 81.0496v209.5104a81.152 81.152 0 0 1-81.0496 80.8448zM638.208 604.16a19.6096 19.6096 0 0 0-19.6096 19.6096v209.5104a19.6096 19.6096 0 0 0 19.6096 19.6096h209.4592a19.6608 19.6608 0 0 0 19.6096-19.6096v-209.7152a19.6608 19.6608 0 0 0-19.6096-19.6096z" fill="#57576D" p-id="1067"></path></svg>
               
                    <a>操作台</a>
                </li>
                <li 
                :class="{ 'active': $route.path === '/Data' }"
                @click="to('/Data')"  v-if="this.currentUserType==='0' || this.currentUserType==='2' || this.currentUserType==='1'"
                >
                   <svg t="1708876136028" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1066" width="35" height="35" style="vertical-align: middle;"><path d="M395.008 465.92H185.4976A81.152 81.152 0 0 1 104.448 385.0752V175.5648a81.2032 81.2032 0 0 1 81.0496-81.0496h209.5104A81.152 81.152 0 0 1 476.16 175.5648v209.5104A81.152 81.152 0 0 1 395.008 465.92zM185.4976 155.9552a19.6608 19.6608 0 0 0-19.6096 19.6096v209.5104A19.6608 19.6608 0 0 0 185.4976 404.48h209.5104A19.6608 19.6608 0 0 0 414.72 385.0752V175.5648a19.6608 19.6608 0 0 0-19.6096-19.6096zM847.6672 465.92h-209.4592a81.152 81.152 0 0 1-81.0496-81.0496V175.5648a81.152 81.152 0 0 1 81.0496-81.0496h209.4592a81.152 81.152 0 0 1 81.0496 81.0496v209.5104A81.152 81.152 0 0 1 847.6672 465.92z m-209.4592-309.9648a19.6096 19.6096 0 0 0-19.6096 19.6096v209.5104a19.6096 19.6096 0 0 0 19.6096 19.4048h209.4592a19.6608 19.6608 0 0 0 19.6096-19.6096V175.5648a19.6608 19.6608 0 0 0-19.6096-19.6096zM395.008 914.1248H185.4976a81.152 81.152 0 0 1-81.0496-81.0496v-209.5104A81.2032 81.2032 0 0 1 185.4976 542.72h209.5104A81.152 81.152 0 0 1 476.16 623.5648v209.5104a81.152 81.152 0 0 1-81.152 81.0496zM185.4976 604.16a19.6608 19.6608 0 0 0-19.6096 19.6096v209.5104a19.6608 19.6608 0 0 0 19.6096 19.6096h209.5104a19.6608 19.6608 0 0 0 19.712-19.8144v-209.5104a19.6608 19.6608 0 0 0-19.712-19.6096zM847.6672 914.1248h-209.4592a81.152 81.152 0 0 1-81.0496-81.0496v-209.5104A81.152 81.152 0 0 1 638.208 542.72h209.4592a81.152 81.152 0 0 1 81.0496 81.0496v209.5104a81.152 81.152 0 0 1-81.0496 80.8448zM638.208 604.16a19.6096 19.6096 0 0 0-19.6096 19.6096v209.5104a19.6096 19.6096 0 0 0 19.6096 19.6096h209.4592a19.6608 19.6608 0 0 0 19.6096-19.6096v-209.7152a19.6608 19.6608 0 0 0-19.6096-19.6096z" fill="#57576D" p-id="1067"></path></svg>
              
                    <a>华信数据</a>
                </li>
                <li 
                :class="{ 'active': $route.path === '/UserCorrent' }"
                @click="to('/UserCorrent')"
                >
                  <svg t="1708876136028" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1066" width="35" height="35" style="vertical-align: middle;"><path d="M395.008 465.92H185.4976A81.152 81.152 0 0 1 104.448 385.0752V175.5648a81.2032 81.2032 0 0 1 81.0496-81.0496h209.5104A81.152 81.152 0 0 1 476.16 175.5648v209.5104A81.152 81.152 0 0 1 395.008 465.92zM185.4976 155.9552a19.6608 19.6608 0 0 0-19.6096 19.6096v209.5104A19.6608 19.6608 0 0 0 185.4976 404.48h209.5104A19.6608 19.6608 0 0 0 414.72 385.0752V175.5648a19.6608 19.6608 0 0 0-19.6096-19.6096zM847.6672 465.92h-209.4592a81.152 81.152 0 0 1-81.0496-81.0496V175.5648a81.152 81.152 0 0 1 81.0496-81.0496h209.4592a81.152 81.152 0 0 1 81.0496 81.0496v209.5104A81.152 81.152 0 0 1 847.6672 465.92z m-209.4592-309.9648a19.6096 19.6096 0 0 0-19.6096 19.6096v209.5104a19.6096 19.6096 0 0 0 19.6096 19.4048h209.4592a19.6608 19.6608 0 0 0 19.6096-19.6096V175.5648a19.6608 19.6608 0 0 0-19.6096-19.6096zM395.008 914.1248H185.4976a81.152 81.152 0 0 1-81.0496-81.0496v-209.5104A81.2032 81.2032 0 0 1 185.4976 542.72h209.5104A81.152 81.152 0 0 1 476.16 623.5648v209.5104a81.152 81.152 0 0 1-81.152 81.0496zM185.4976 604.16a19.6608 19.6608 0 0 0-19.6096 19.6096v209.5104a19.6608 19.6608 0 0 0 19.6096 19.6096h209.5104a19.6608 19.6608 0 0 0 19.712-19.8144v-209.5104a19.6608 19.6608 0 0 0-19.712-19.6096zM847.6672 914.1248h-209.4592a81.152 81.152 0 0 1-81.0496-81.0496v-209.5104A81.152 81.152 0 0 1 638.208 542.72h209.4592a81.152 81.152 0 0 1 81.0496 81.0496v209.5104a81.152 81.152 0 0 1-81.0496 80.8448zM638.208 604.16a19.6096 19.6096 0 0 0-19.6096 19.6096v209.5104a19.6096 19.6096 0 0 0 19.6096 19.6096h209.4592a19.6608 19.6608 0 0 0 19.6096-19.6096v-209.7152a19.6608 19.6608 0 0 0-19.6096-19.6096z" fill="#57576D" p-id="1067"></path></svg>
                
                    <a>个人中心</a>
                </li>
            </ul>
            <div :class="{ 'active': $route.path === '/UserCorrent' }" @click="to('/UserCorrent')"  id="user-info">
                <img src="../assets/R-C.jpg" alt="User Avatar">
                <a>{{currentUserName}}</a>
                <!-- <span>{{AdminType}}</span> -->
                <span v-if="currentUserType === '0'">超级管理员</span>
                <span v-if="currentUserType === '1'">操作员</span>
                <span v-if="currentUserType === '2'">二级管理员</span>
                <svg t="1709036768560" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2459" width="20" height="20"><path d="M670.976 546.133333a34.133333 34.133333 0 0 1-24.064-9.984L328.874667 218.112a34.133333 34.133333 0 1 1 48.213333-48.298667l318.037333 318.037334A34.133333 34.133333 0 0 1 670.976 546.133333z" p-id="2460"></path><path d="M353.024 864.170667a34.133333 34.133333 0 0 1-24.149333-58.282667l318.037333-318.037333a34.133333 34.133333 0 0 1 48.213333 48.298666L377.088 854.186667a34.133333 34.133333 0 0 1-24.064 9.984z" p-id="2461"></path></svg>
            </div>
        </div>
    </div>
</template>

<script>

export default {
    props: ['router', 'route'], // 确保这两个 props 在父组件中传递了
    data() {
    return {
      currentUserType:'',
      currentUserName:'',
      AdminType:'',
      KeyWord:''
        };
    },
    mounted() {
        this.currentUserName = this.$route.query.currentUserName;
        this.currentUserType = this.$route.query.currentUserType;

            // 页面加载时从localStorage读取用户类型
            if (this.$route.query.currentUserName && this.$route.query.currentUserType) {
                this.currentUserType = this.$route.query.currentUserType;
                this.currentUserName = this.$route.query.currentUserName;
            }
            // if (localStorage.getItem('currentUserName')) {
            //     this.currentUserName = localStorage.getItem('currentUserName');
            // }
            // else{
            //  /// 当组件挂载时，尝试从LocalStorage中获取保存的数据
            //  const savedData = JSON.parse(localStorage.getItem('userData'));
            // if (savedData) {
            //     // 如果有保存的数据，则恢复到组件的data中
            //     this.currentUserType = savedData.currentUserType;
            //     this.currentUserName = savedData.currentUserName;
            // }
        
    

    
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
        }
       
    }
};
</script>

<style>
.wrapper-navigation  {
    margin: 0;
    width: 15%;
    height: 100%;
    padding: 0;
    font-family: Arial, sans-serif;
}


.logo {
    color: #333; /* Logo 文字颜色 */
    font-size: 35px;
    margin-bottom: 30px;
    margin-top:0px
}

.navigation {
	position: fixed;
    width: 15%;
	height:100%;
    background-color: #fff; /* 导航栏背景颜色 */
    color: #333; /* 导航栏文字颜色 */
    padding: 20px;
	box-shadow: 5px 0 10px rgba(0, 0, 0, 0.1); /* 添加外阴影，参数分别为水平偏移量、垂直偏移量、模糊半径和阴影颜色 */
}

.navigation ul {
    list-style-type: none;
    padding: 0;
}

.navigation ul li {
    margin-bottom: 20px;
	height: 55px;
	border-radius: 6px; /* 添加圆角 */
	text-align: left; /* 水平居中 */
}

.navigation ul li svg {
    display: inline-block;
    margin-top: -1.5%;
    margin-left: 11%;
}

.navigation ul li a {
    text-decoration: none;
    font-size:6mm;
    color: #333; /* 导航栏文字颜色 */
    display: inline-block;
    margin-left: 14%;
	margin-top: 6px;
    padding: 6px;
    /* 其他样式 */
}


.navigation ul li:hover,
.navigation ul li.active {
  background-color: #282F87; /* 悬停或选中后的背景颜色 */
  color: #fff; /* 悬停或选中后的文字颜色 */
}

.navigation ul li:hover a,
.navigation ul li.active a,
.navigation ul li:hover svg path,
.navigation ul li.active svg path {
  color: #fff; /* 悬停或选中后的文字颜色 */
  fill: #fff; /* 悬停或选中后的SVG填充颜色 */
}

.navigation ul li.hover {
  background-color: #282F87; /* 悬停后的背景颜色 */
  color: #fff; /* 悬停后的文字颜色 */
}

#user-info {
    position: absolute;
    margin-top: 300px;
    width: 75%;
    height: 6.5%;
    margin-left: 8px;
	display: flex;
	align-items: center; /* 垂直居中 */
	background-color: #282F87;
	border-radius: 12px;
}

#user-info img {
    position: relative;
    width: 45px;
    height: 45px;
    border-radius: 50%;
	margin-top: 5%;
    margin-bottom: 10px;
	margin-left: 13%;
	display: inline-block;
}

#user-info a {
    position: fixed;
	font-size: 4.5mm;
	font-weight: 500;
	color: #fff;
	margin-left: 5%;
    margin-top: -15px;
	
}

#user-info span {
	position: fixed;
	font-size: 3mm;
	color: #fff;
	margin-top: 20px;
	margin-left: 5%;
}

#user-info svg{
	fill: #FFF;
	position: absolute;
	margin-left:86%;
}

</style>
