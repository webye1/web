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

	<svg :class="{ 'active': $route.path === '/Data' }"
                @click="to('/Data')" t="1709382634550" id="lastPage" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2321" width="20" height="20"><path d="M152.746667 546.133333a34.133333 34.133333 0 0 1-24.149334-58.282666l247.466667-247.466667a34.133333 34.133333 0 0 1 48.298667 48.298667l-247.466667 247.466666A34.133333 34.133333 0 0 1 152.746667 546.133333z" p-id="2322"></path><path d="M400.384 793.6a34.133333 34.133333 0 0 1-24.149333-9.984l-247.466667-247.466667A34.133333 34.133333 0 0 1 152.746667 477.866667h718.506666a34.133333 34.133333 0 0 1 0 68.266666H235.178667l189.354666 189.354667A34.133333 34.133333 0 0 1 400.384 793.6z" p-id="2323"></path></svg>

	<div class="op-list">
		<a class="title-cn">包裹记录({{ totalPackets }})</a>
		<a class="title-eng">All Records</a>

		<div class="search-box">
			<svg t="1709038374337" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2605" width="20" height="20"><path d="M450.218667 818.517333a368.298667 368.298667 0 0 1-260.437334-628.736 368.298667 368.298667 0 0 1 520.533334 520.533334 365.909333 365.909333 0 0 1-260.096 108.202666z m0-668.16A299.946667 299.946667 0 1 0 662.442667 238.08a298.666667 298.666667 0 0 0-212.224-87.722667z" p-id="2606"></path><path d="M907.946667 942.08a34.133333 34.133333 0 0 1-24.149334-9.984L662.442667 710.656a34.133333 34.133333 0 0 1 48.213333-48.213333l221.866667 221.354666a34.133333 34.133333 0 0 1-24.149334 58.282667z" p-id="2607"></path>
			</svg>
			<input type="text" id="searchInput" placeholder="请输入快递单号" v-model="searchKeyword" @keyup.enter.stop="filterByKeyword">

		</div>

		<div id="data-display"></div>

		<div class="list-header">
		            <div class="header-item">代理</div>
		            <div class="header-item">客户</div>
		            <div class="header-item">件数</div>
		            <div class="header-item">总毛重</div>
		            <div class="header-item">产品名称</div>
					<div class="header-item">柜号</div>
					<div class="header-item">快递单号</div>
		</div>
		<ul class="list">
			<li class="list-item" v-for="item in paginatedItems" :key="item.id">
				<div class="list-item-div">{{ item.proxy }}</div>
				<div class="list-item-div">{{ item.customer }}</div>
				<div class="list-item-div">{{ item.count }}</div>
				<div class="list-item-div">{{ item.weight }}</div>
				<div class="list-item-div">{{ item.product_name }}</div>
				<div class="list-item-div">{{ item.counter_number }}</div>
				<div class="state">{{ item.tracking_number }}</div>
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
        name: 'DataPack',
        props: ['router', 'route'], // 确保这两个 props 在父组件中传递了
        data() {
            return {
                currentUserType: '',
                items: [], // 用于存储从后端获取的数据
                itemsPerPage: 9, // 每页显示的项数
                currentPage: 1, // 当前页数
                totalPages: 0, // 添加totalPages属性
                totalPackets: 0, // 添加totalPages属性
                filteredItems: [], // 用于存储筛选后的数据
                searchKeyword: '', // 添加搜索关键词属性
                noData: false,
            };
        },
		mounted() {
			// 页面加载时从localStorage读取用户类型
			if (localStorage.getItem('currentUserType') && localStorage.getItem('currentUserName')) {
						this.currentUserType = localStorage.getItem('currentUserType');
						this.currentUserName = localStorage.getItem('currentUserName');
			}

			else{
				/// 当组件挂载时，尝试从LocalStorage中获取保存的数据
				const savedData = JSON.parse(localStorage.getItem('userData'));
			if (savedData) {
				// 如果有保存的数据，则恢复到组件的data中
				this.currentUserType = savedData.currentUserType;
				this.currentUserName = savedData.currentUserName;
			}
			}

            this.fetchProDataFromBackend(); // 加载页面时获取数据

		},

        computed: {
            paginatedItems() {
                const startIndex = (this.currentPage - 1) * this.itemsPerPage;
                const endIndex = startIndex + this.itemsPerPage;
                return this.items.slice(startIndex, endIndex);
            }
        },
        mounted() {
            // 页面加载时从localStorage读取用户类型
            if (localStorage.getItem('currentUserType')) {
                this.currentUserType = localStorage.getItem('currentUserType');
            }
            this.fetchProDataFromBackend(); // 加载页面时获取数据
        },
        components: {
            Footer
        },
        methods: {
            to(path) {
                this.$router.push(path);
            },
            async fetchProDataFromBackend() {
                try {
                    const response = await axios.get('http://47.98.58.79:8080/Product/getall');
                    this.items = response.data; // 假设后端返回的数据是一个数组
                    // 计算总页数
                    this.totalPages = Math.ceil(this.items.length / this.itemsPerPage);
                    this.totalPackets = Math.ceil(this.items.length);
                } catch (error) {
                    console.error('Error fetching data:', error);
                }
            },
            prevPage() {
                if (this.currentPage > 1) {
                    this.currentPage--;
                }
            },
            nextPage() {
                const totalPages = Math.ceil(this.items.length / this.itemsPerPage);
                if (this.currentPage < totalPages) {
                    this.currentPage++;
                }
            },
			filterByKeyword() {
				if (this.searchKeyword.trim() !== '') { // 检查搜索关键词是否为空
					// 使用关键字对快递单号进行筛选
          console.log("关键词"+searchKeyword);
					this.items = this.items.filter(item => item.tracking_number.includes(this.searchKeyword));
          console.log("record"+items);
				} else {
					// 关键字为空时，显示全部数据
					this.fetchProDataFromBackend();
				}
				// 计算总页数
				this.totalPages = Math.ceil(this.items.length / this.itemsPerPage);
				this.currentPage = 1;
			}




        }
    };
</script>


<style scoped>

#lastPage{
	position: fixed;
	margin-left: 21%;
	margin-top: 1.5%;

}

#lastPage:hover{
	fill: #55aaff;
}

.search-box {
	position: absolute;
    margin-top: 33px; /* 顶部外边距 */
	margin-left: 72%;
	width:15%;

    padding: 4px;
    background-color: #fff; /* 搜索框的背景色 */
	border: 1px solid #ccc; /* 添加边框 */
	border-radius: 10px;

}

.search-box input {
	width: 140px;
	margin-left: 40px;
    padding: 8px;
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
    width: 77%;
	margin-left:21.1% ;
	margin-top: 5%;
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
    margin-left: 4.6%;
	margin-right: 5%;
    overflow: hidden; /* 清除浮动 */


}
.op-list .list-item {
	align-items: flex-start; /* 让子项左对齐 */
	display: flex;
	width:100%;
	height: 10vh;
	margin-left: 1%;
    list-style: none;
    overflow: hidden; /* 清除浮动 */
	text-align: center;

}

/* 设置默认宽度为 14.3% */
.op-list .list .list-item-div,
.op-list .list .state  {
    width: 13%;
    height: 30px;
    align-items: center; /* 让子项左对齐 */
	text-align: center;
    margin-bottom: 10px;
    border-bottom: 1px solid #e7e7e7;
    font-size: 4mm;
}

/* 设置第三个和第四个 li 的宽度为 8% */
.op-list .list .list-item-div:nth-child(3),
.op-list .list .list-item-div:nth-child(4) {
    width: 8%;
}

/* 设置第六个和第七个 li 的宽度为 20% */
.op-list .list .list-item-div:nth-child(6),
.op-list .list .list-item-div:nth-child(7) {
    width: 20%;
}

/* 设置第三个和第四个 li 的宽度为 7.44% */
.op-list .list .list-item-div:nth-child(7n+3),
.op-list .list .list-item-div:nth-child(7n+4) {
    width: 8%;
}


/* 设置第六个和第七个 li 的宽度为 20% */
.op-list .list .list-item-div:nth-child(7n+6),
.op-list .list .state {
    width: 22%;
}



.op-list .list-header {
    margin-top: 40px;
	margin-bottom: 5vh;
	font-size: 5mm;
	color: #333;
	display: flex;
	width: 89%;
	height: 40px;
	margin-left: 6%;
	margin-right: 5%;
	align-items: flex-start; /* 让子项左对齐 */
	border-bottom:  1px solid #e7e7e7;
}

.op-list .list-header .header-item {
    width: 13%; /* 默认宽度 */
    text-align: center; /* 文本水平居中 */
	color:dimgrey;
}

.op-list .list-header .header-item:nth-child(3n) {
    flex: 0 0 8%; /* 固定为 6% 的宽度 */
}

.op-list .list-header .header-item:nth-child(4n) {
    flex: 0 0 8%; /* 固定为 6% 的宽度 */
}

.op-list .list-header .header-item:nth-child(6n) {
    flex: 0 0 20%; /* 固定为 6% 的宽度 */
}

.op-list .list-header .header-item:nth-child(7n) {
    flex: 0 0 22%; /* 固定为 6% 的宽度 */
	margin-left: 2.5%;

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
	width: 80px; /* 设置下拉列表宽度为200像素 */
	height: 30px;
	margin-top: -36px; /* 设置顶部外边距为20像素 */
	margin-left: 780px; /* 设置左侧外边距为50像素 */
	background-color: #e7e7e7;
	border-radius: 5px;
}

#status-switcher{
	margin-left: 10%;
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
	margin-bottom: 1%;
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




</style>
../components/Footer.vue
