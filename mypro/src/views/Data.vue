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
     <!-- 菜单部分 -->
     <Footer></Footer>

	<div class="info" v-if="currentUserType === '0' || currentUserType === '2'" >
		<div :class="{ 'active': $route.path === '/UserList' }" @click="  to('/UserList')" v-if="currentUserType === '0'">
			<div class="info_img">
			<svg t="1709139181778" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="9454" width="20" height="20"><path d="M721 960.2H311.5c-8.7 0-17.1-1.8-25-5.4-30.1-13.8-43.4-49.5-29.6-79.6 0.4-1 1-2 1.5-2.9l84.7-140.1c0.2-1.2-0.4-2.4-1.5-3-54.7-31.3-100.1-76.8-131.3-131.7C114.7 428.8 174 213.8 342.6 118 511.3 22.2 726.3 81.6 822 250.2c95.7 168.5 36.6 383.4-131.8 479.2 0.6 0.5 0.6 5.8 5.5 16.6l78.4 125.7c0.7 1.1 1.3 2.2 1.8 3.4 3.6 7.9 5.4 16.3 5.4 25-0.2 33.3-27.2 60.1-60.3 60.1z m-412.2-60.4c-0.2 1.2 0.4 2.4 1.6 2.9l410.5 0.2c1.7 0 3.1-1.3 3.1-3l-77.9-125.2c-0.6-1-1.2-2-1.7-3.1-28.1-60.2 6-85.6 17.1-91.9 141.2-80.1 190.8-260.2 110.6-401.3-80.1-141-260-190.6-401.2-110.5C229.8 248 180.1 428 260.3 569.2c26.1 45.9 64.1 84 109.9 110.2 27.2 15.6 37.8 48.8 24.9 77.2-0.4 1-1 2-1.6 3l-84.7 140.2z m366.9-195.1l14.1 25-14.1-25 14.2 24.9-14.2-24.9z m14.2 24.9z" p-id="9455"></path><path d="M515.9 386.8c-14.5 0-29-2.8-42.9-8.3-28.5-11.4-50.9-33.2-63-61.5-6.2-14.5 0.5-31.4 15-37.7 14.4-6.3 31.4 0.4 37.7 15 6.1 14.2 17.4 25.2 31.7 30.9 14.3 5.7 30.1 5.6 44.4-0.5 14.2-6.1 25.2-17.4 31-31.7 5.9-14.7 22.7-21.8 37.3-16 14.7 5.9 21.9 22.6 16 37.3-11.4 28.6-33.3 51-61.7 63.1-14.8 6.3-30.1 9.4-45.5 9.4z" p-id="9456"></path></svg>
			</div>
			<div class="info_in" >
				<p class="info_title">总计用户人数</p>
				<p class="info_num">点击进入查看</p>
			</div>
		</div>
		<div :class="{ 'active': $route.path === '/DataPack' }" @click="to('/DataPack')" v-if="currentUserType === '0' || currentUserType === '2'">
			<div class="info_img">
			<svg t="1709139244465" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="10787" width="20" height="20"><path d="M789.1968 767.61088h-163.92192v93.67552h46.83776a23.42912 23.42912 0 1 1 0 46.85824H391.08608a23.42912 23.42912 0 1 1 0-46.85824h46.83776v-93.67552h-187.35104a93.65504 93.65504 0 0 1-93.67552-93.65504V252.39552a93.67552 93.67552 0 0 1 93.67552-93.67552h538.64448a93.696 93.696 0 0 1 93.696 93.67552v421.56032a93.696 93.696 0 0 1-93.71648 93.65504z m-210.75968 93.696v-93.67552H484.7616v93.67552h93.67552z m257.59744-608.91136a46.81728 46.81728 0 0 0-46.83776-46.83776H250.55232a46.81728 46.81728 0 0 0-46.83776 46.83776v327.8848h632.32v-327.8848z m0 374.72256H203.71456v46.83776a46.81728 46.81728 0 0 0 46.83776 46.81728h538.64448a46.81728 46.81728 0 0 0 46.83776-46.81728v-46.83776z m0 0" p-id="10788"></path></svg>
			</div>
			<div class="info_in">
			<p class="info_title">包裹总数</p>
			<p class="info_num">点击进入查看</p>
			</div>
		</div>
	</div>
	<div class="search-box">
		<svg t="1709038374337" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2605" width="20" height="20"><path d="M450.218667 818.517333a368.298667 368.298667 0 0 1-260.437334-628.736 368.298667 368.298667 0 0 1 520.533334 520.533334 365.909333 365.909333 0 0 1-260.096 108.202666z m0-668.16A299.946667 299.946667 0 1 0 662.442667 238.08a298.666667 298.666667 0 0 0-212.224-87.722667z" p-id="2606"></path><path d="M907.946667 942.08a34.133333 34.133333 0 0 1-24.149334-9.984L662.442667 710.656a34.133333 34.133333 0 0 1 48.213333-48.213333l221.866667 221.354666a34.133333 34.133333 0 0 1-24.149334 58.282667z" p-id="2607"></path>
		</svg>
		<input type="text" id="searchInput" placeholder="请输入快递单号" v-model="searchKeyword1" @keyup.enter.stop="filterByKeyword">

	</div>
	<div class="op-list">
		<a class="title-cn">操作记录</a>
		<a class="title-eng">All Records</a>
		<!--状态选择-->
		<div class="switcher">
     		<select id="status-switcher" @change="filterByStatus" :disabled="!LoginCheck">
                <option value="">所有状态</option>
                <option value="已入库">已入库</option>
                <option value="已装柜">已装柜</option>
                <option value="已到港">已到港</option>
                <option value="已到站">已到站</option>
                <option value="清关中">清关中</option>
                <option value="已装车">已装车</option>
                <option value="已到仓">已到仓</option>
				        <option value="已完成">已完成</option>
            </select>
        </div>
		<div id="data-display"></div>

		<div class="list-header">
		            <div class="header-item">柜号</div>
		            <div class="header-item">快递单号</div>
		            <div class="header-item">更新时间</div>
		            <div class="header-item">操作员</div>
		            <div class="header-item">状态</div>
		</div>
		<ul class="list">
   			<li class="list-item" v-for="item in paginatedItems" :key="item.id" v-show="item.show">
				<div class="list-item-div">{{ item.counter_number }}</div>
				<div class="list-item-div">{{ item.tracking_number }}</div>
				<div class="list-item-div">{{ item.time_operating }}</div>
				<div class="list-item-div">{{ item.manager }}</div>
				<div class="state" >{{ item.update_state }}</div>
			</li>
			</ul>
		<!-- 添加一个条件判断，如果没有数据，则显示“无数据” -->
		<div v-if="noData" class="noData">无数据</div>
		<div id="pagination">
			<button id="prevPage" class="page-button" @click="prevPage">上一页</button>
			<input type="text" v-model="currentPage" class="page-input" placeholder="1">
			<p>/{{ totalPages }}</p>
			<button id="nextPage" class="page-button" @click="nextPage">下一页</button>
		</div>
	</div>

</div>
</v-scale-screen>
</template>
<script>
import Footer from '../components/Footer.vue';
import axios from 'axios';

export default {
  name: 'Data',
  components: {
    Footer
  },
  data() {
    return {
		currentUserType: this.$route.query.currentUserType,
		currentUserName: this.$route.query.currentUserName,
		items: [],
		originalItems: [], // 新增一个变量来存储原始数据
		itemsPerPage: 9,
		currentPage: 1,
		totalPages: 0,
		filteredItems: [], // 用于存储筛选后的数据
		searchKeyword1: '', // 添加搜索关键词属性
		noData: false,
		LoginCheck:true,
        selectedStatus:'',
        KeyWord:''
    };
  },
  watch: {
  currentPage(newValue, oldValue) {
    // 当 currentPage 变化时，重新计算 paginatedItems 并更新 this.items 和显示状态
    this.updateItems();
  },
  filteredItems(newValue, oldValue) {
    // 当 filteredItems 变化时，重新计算 paginatedItems 并更新 this.items 和显示状态
    this.updateItems();
  },
  searchKeyword1(newValue, oldValue) {
    this.filterByKeyword();
  }
    },
  mounted() {
    //console.log("mounted:");
    //console.log("props UT:", this.$route.query.currentUserType);
    //console.log("props UN:", this.$route.query.currentUserName);

    // 页面加载时从localStorage读取用户类型和用户名
    if (this.$route.query.currentUserType && this.$route.query.currentUserName) {
      this.currentUserName = this.$route.query.currentUserName;
      this.currentUserType = this.$route.query.currentUserType;
        this.LoginCheck = true;
            // 执行其他操作，例如从后端获取数据
            this.fetchDataFromBackend();
            this.filterByStatus(); // 初始化时执行状态筛选方法，显示所有状态的列表项

        } else {
            // console.log("props UT:", this.$route.query.currentUserType);
            // console.log("props UN:", this.$route.query.currentUserName);

            this.LoginCheck = false;
            this.$router.push('/');
            this.$message({
                message: '请登录后操作',
                type: 'error',
                duration: 500
            });
        }

},



  computed: {
    paginatedItems() {

      const startIndex = (this.currentPage-1) * this.itemsPerPage;
      const endIndex = startIndex + this.itemsPerPage;

    //   console.log("startIndex");
    //   console.log(startIndex);
    //   console.log("endIndex");
    //   console.log(endIndex);
    //   console.log(this.filteredItems.slice(startIndex, endIndex));

      return this.filteredItems.slice(startIndex, endIndex);
    }
  },

  methods: {
    redirectToDatapack() {
      this.$router.push({
        path: '/DataPack',
        name: 'DataPack',
        query: {
          currentUserType: this.currentUserType,
          currentUserName: this.currentUserName
        }
      });
    },
    redirectToUserlist() {
      this.$router.push({
        path: '/UserList',
        name: 'UserList',
        query: {
          currentUserType: this.currentUserType,
          currentUserName: this.currentUserName
        }
      });
    },
    updateItems() {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;

    // 更新 this.items
    this.items = this.filteredItems.slice(startIndex, endIndex);

    // 更新列表项的显示状态
    this.items.forEach(item => {
      item.show = true;
    });
  },
  to(path) {
            // this.$router.push(path);
            // console.log("to:");
            // console.log("props UT:", this.$route.query.currentUserType);
            // console.log("props UN:", this.$route.query.currentUserName);
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

    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
      }
    },
    nextPage() {
        
        this.totalPages = Math.ceil(this.filteredItems.length / this.itemsPerPage);
      if(this.currentPage < this.totalPages){
        this.currentPage++;
      }
      if (this.currentPage >= this.totalPages) {
        this.currentPage = this.totalPages;
      }
    },
    stateCheck() {
      // 状态检查方法
    },

	filterByStatus() {
    // 获取选定的状态
    let  selectedStatus = ''
    const statusSwitcher = document.getElementById('status-switcher');
    if (statusSwitcher) {
        selectedStatus = statusSwitcher.value;
        this.selectedStatus  = statusSwitcher.value;
    }

    // 根据选定状态筛选数据
    this.filteredItems = this.originalItems.filter(item => {
        return selectedStatus === '' || item.update_state === selectedStatus;
    });
    if(this.searchKeyword1!==''){
        this.filteredItems = this.filteredItems.filter(item => item.tracking_number.includes(this.searchKeyword1));
    }

    // 更新总页数
    this.totalPages = Math.ceil(this.filteredItems.length / this.itemsPerPage);

    // 检查当前页码是否超出新的总页数，如果是则将当前页码设置为新的总页数
    if (this.currentPage > this.totalPages) {
        this.currentPage = this.totalPages;
    }

    // 根据当前页码和每页显示的行数重新计算要显示的项
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;

    // 更新列表项
    this.items = this.filteredItems.slice(startIndex, endIndex);

    // 更新列表项的显示状态
    this.items.forEach(item => {
        item.show = true;
    });

    if(this.filteredItems.length===0){

        // 如果过滤后的数组为空，则显示“无数据”
        this.noData =  0;
        }

    // 如果有搜索关键字，则进行进一步的状态和关键字筛选
    // if (this.searchKeyword1 !== '') {
    //     this.filterByStatusAndKeyword();
    // }

    // 如果当前页码为0，则将其设为1
    if (this.currentPage === 0) {
        this.currentPage = 1;
    }
},




async fetchDataFromBackend() {
    try {
        const response = await axios.get('https://www.hxlogistics.top/to_url/Record/getall/');
        let items = response.data;
        // 根据管理员类型为 1 仅显示特定用户的项目
        if (this.currentUserType === "1") {
            items = items.filter(item => item.manager === this.currentUserName);
        }
        this.items = items;
        this.originalItems = items; // 将原始数据赋值给 originalItems
        this.totalPages = Math.ceil(this.items.length / this.itemsPerPage);
        this.filteredItems = this.items; // 初始化 filteredItems
        // this.filterByStatus(); // 在获取数据后立即调用状态筛选方法
    } catch (error) {
        console.error('Error fetching data:', error);
    }
},


filterByKeyword() {
  if (this.searchKeyword1.trim() !== '') { // 检查搜索关键词是否为空
    // 使用关键字对快递单号进行筛选
    this.filteredItems = this.originalItems.filter(item => item.tracking_number.includes(this.searchKeyword1));

    if( this.selectedStatus !==''){
        // 根据选定状态筛选数据
    this.filteredItems = this.filteredItems.filter(item => {
        return selectedStatus === '' || item.update_state === selectedStatus;
    });
    }

    // 更新总页数和当前页码
    this.totalPages = Math.ceil( this.filteredItems.length / this.itemsPerPage);
    this.currentPage = 1;


    // 根据当前页码和每页显示的行数重新计算要显示的项
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;

    // 更新列表项
    this.items = this.filteredItems.slice(startIndex, endIndex);

    // 更新列表项的显示状态
    this.items.forEach(item => {
        item.show = true;
    });

    if(this.filteredItems.length===0){

    // 如果过滤后的数组为空，则显示“无数据”
    this.noData =  0;
    }

    // console.log( this.filteredItems); // 在这里打印筛选结果
  } else {
    // 如果搜索关键字为空，则重新应用状态筛选
    this.filterByStatus();
  }
},

// filterByStatusAndKeyword() {
//   const selectedStatus = document.getElementById('status-switcher').value;

//   // 先根据状态进行筛选
//   let filteredItems = this.originalItems.filter(item => {
//     return selectedStatus === '' || item.update_state === selectedStatus;
//   });

//   // 如果有搜索关键词，则再次筛选
//   if (this.searchKeyword1.trim() !== '') {
//     // 使用关键字对快递单号进行筛选
//     filteredItems = filteredItems.filter(item => item.tracking_number.includes(this.searchKeyword1));
//   }

//   // 更新总页数和当前页码
//   this.totalPages = Math.ceil(filteredItems.length / this.itemsPerPage);
//   this.currentPage = 1;

//   // 更新 paginatedItems
//   const startIndex = (this.currentPage - 1) * this.itemsPerPage;
//   const endIndex = startIndex + this.itemsPerPage;
//   this.paginatedItems = filteredItems.slice(startIndex, endIndex);

//   // 更新显示状态
//   this.items.forEach(item => {
//     item.show = filteredItems.includes(item);
//   });
//     if(this.filteredItems.length===0){
//     // 如果过滤后的数组为空，则显示“无数据”
//     this.noData =  0;
//     }

//   // console.log(filteredItems); // 在这里打印筛选结果
// }


// },
// 	created() {
// 		this.fetchDataFromBackend();
// 	}
}
};
</script>

<style scoped>
    /****************** 总容器 ******************/
    .wrapper {
    margin: 0;
    padding: 0;
    font-family: Arial, sans-serif;
}


.wrapper .info{
	position: absolute;
	margin-left: 25.1%;
	margin-top:13px;
	font-size: 8mm;
	width:25%;
	height:15vh;
	display: flex;
	border: 1px solid #69e891; /* 添加边框 */
	align-items: center; /* 垂直居中对齐 */
	justify-content: center; /* 水平方向居中 */
	border-radius: 10px;
}
.wrapper .info .info_in{
	/* border: 1px solid #ccc; */
	margin-top: 5%;
	width: 100%;
}
.wrapper .info div{
	width: 40%; /* 默认宽度 */
	text-align: center; /* 文本水平居中 */
	display: flex;
	margin-left: 2px;
	justify-content: center; /* 水平方向居中 */
}
.info div .info_img{
	background: linear-gradient(to right, #D3FFE7, #69e891); /* 线性渐变 */
	border-radius: 60px;
	width: 50%;
	height: 9vh;
	align-items: center; /* 垂直居中对齐 */
	justify-content: center; /* 水平方向居中 */
}

.info div .info_title{
	font-size: 3mm;
	width: 100%;
	font-weight: 200;
	color: darkgrey;
	margin-left: -6px;
	margin-top: 4px;
	align-items: flex-start; /* 让子项左对齐 */
}

.info div .info_num{
	font-size: 4mm;
	font-weight: 500;
	color: #000;
	margin-top: 18px;
	margin-left: -100px;

}

.search-box {
	position: absolute;
    margin-top: 3%; /* 顶部外边距 */
	margin-left: 70%;
	width:15%;

    padding: 8px;
    background-color: #fff; /* 搜索框的背景色 */
	border: 1px solid #ccc; /* 添加边框 */
	border-radius: 10px;

}

.search-box input {
	width: 80%;
	margin-left: 40px;
    padding: 10px;
    box-sizing: border-box;
    border-radius: 4px;
	border: none; /* 去除搜索框的边框 */
	outline: none; /* 移除输入框的聚焦时的边框 */
}

.search-box svg{
	position: absolute;
	margin-top: 7px;
	margin-left: 15px;
}

.op-list {
	position: absolute;
    width: 68%;
	margin-left:25% ;
	margin-top: 8%;
    height: auto;
	display: flex;
	flex-direction: column;
	align-items: flex-start; /* 让子项左对齐 */
	border: 1px solid #e7e7e7;
	border-radius: 10px;
}


.op-list .list {
	width:90%;
    list-style: none;
    padding: 4px;
    margin-left: 5%;
	margin-right: 5%;
	margin-top: 6px;
    overflow: hidden; /* 清除浮动 */

}

.op-list .list li {
    width: 100%; /* 默认宽度 */
	height: 9vh;
    align-items: flex-start; /* 让子项左对齐 */
	display: flex;
	margin-top: 5px;
	margin-bottom: 10px; /* 增加底部间距 */
	border-bottom: 1px solid #e7e7e7; /* 添加黑色底边，宽度为1像素 */
}

.op-list .list .list-item {
   width: 100%;
   align-items: center; /* 让子项左对齐 */
   text-align: center; /* 文本水平居中 */
   margin-top: -2px;
   border-radius: 3px;
}
.op-list .list .list-item-div,
.state {
   width: 25%;
   align-items: center; /* 让子项左对齐 */
   text-align: center; /* 文本水平居中 */
   margin-top: -2px;
   border-radius: 3px;
   color: gray;
   font-size: 5mm;
}

/* 设置第三个和第四个 li 的宽度为 7.44% */
/* .op-list .list .list-item-div:nth-child(5n+4),
.op-list .list .list-item-div:nth-child(4) {
    flex: 0 0 15%;
} */

.op-list .list .state,
.op-list .list .state {
    flex: 0 0 10%;
}


.op-list .list-header {
    margin-top: 3%;
	margin-bottom: 2px;
	color: #333;
	display: flex;
	width: 90%;
	font-size: 5mm;
	height: 5vh;
	margin-left: 5%;
	margin-right: 5%;
	align-items: flex-start; /* 让子项左对齐 */
	border-bottom:  1px solid #e7e7e7;
}

.op-list .list-header .header-item {
    width: 30%; /* 默认宽度 */
    text-align: center; /* 文本水平居中 */
	color:dimgrey;
}

.op-list .list-header .header-item:last-child {
    flex: 0 0 10%; /* 最后一个项目固定为 6% 的宽度 */
}

.op-list .title-cn{
	margin-top: 3%;
	margin-left: 5%;

}

.op-list .title-eng{
	margin-top: 1%;
	margin-left: 5%;
	font-size: 3mm;
	color: #55aa00;
	display: flex;
}

.op-list .switcher{
	width: 8%; /* 设置下拉列表宽度为200像素 */
	height: 5vh;
	margin-top: -6vh; /* 设置顶部外边距为20像素 */
	margin-left: 80%; /* 设置左侧外边距为50像素 */
	background-color: #e7e7e7;
	border-radius: 5px;
}

#status-switcher{
	margin-left: 15%;
	margin-top: 8%;
	background-color: transparent; /* 设置背景色为透明 */
	border: none; /* 去除边框 */
	outline: none; /* 去除切换后的内边框 */
	font-weight: 500;
}

#pagination {
    justify-content: center; /* 水平居中对齐 */
    align-items: center; /* 垂直居中对齐 */
	display: flex;
	margin-left: 36%;
	margin-bottom: 2%;
}

.page-button {
    border: none; /* 去除边框 */
    background-color: #e7e7e7; /* 设置背景色 */
    color: black; /* 设置文字颜色 */
    padding: 4px 16px; /* 设置内边距 */
    text-align: center; /* 文字居中 */
    text-decoration: none; /* 去除下划线 */
    display: inline-block; /* 设置为内联块级元素 */
    cursor: pointer; /* 鼠标样式为指针 */
	font-size: 3mm;
	margin-top: 10px; /* 设置上边距 */
}

.page-button:hover {
    color: blue; /* 悬停时文字颜色变为蓝色 */
}

.page-input {
    border: 1px solid #ccc;
    padding: 6px 10px;
	width: 20px;
	height: 6px;
    margin: 10px 5px 0px 8px;
    font-size: 3mm;
}

#pagination p{
	font-size: 4mm;
	padding: 2px;
	color: #ccc;
	height: 8px;
	width: auto;
	margin-right: 5px;
}

.noData{

	width: 100%;
	text-align: center;
	color: grey;
	margin-bottom: 10px;
	margin-top: 5px;
}




</style>./Data.vue../components/Footer.vue
