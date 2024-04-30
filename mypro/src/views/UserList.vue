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
	
			<div class="list_all">
	
				<p class="user-wx-title">小程序用户列表  ({{this.wxuserNum}})</p>
				<div class="user-list">
					<ul class="user-wx-list">
					<li class="user-wx" v-for="(user,index) in wxUserList" :key="user.id">
						<a>{{ index+1 }}</a>
						<img :src="user.avatar" alt="User Avatar">
						<p>{{ user.name }}</p>
					</li>
					</ul>
				</div>
				<p class="user-web-title">管理员列表</p>
				<div class="search-box-admin">
					<svg t="1709038374337" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2605" width="20" height="20"><path d="M450.218667 818.517333a368.298667 368.298667 0 0 1-260.437334-628.736 368.298667 368.298667 0 0 1 520.533334 520.533334 365.909333 365.909333 0 0 1-260.096 108.202666z m0-668.16A299.946667 299.946667 0 1 0 662.442667 238.08a298.666667 298.666667 0 0 0-212.224-87.722667z" p-id="2606"></path><path d="M907.946667 942.08a34.133333 34.133333 0 0 1-24.149334-9.984L662.442667 710.656a34.133333 34.133333 0 0 1 48.213333-48.213333l221.866667 221.354666a34.133333 34.133333 0 0 1-24.149334 58.282667z" p-id="2607"></path>
					</svg>
					<input type="text" id="searchInput" placeholder="请输入用户名" v-model="searchManagerName" @keypress="onKeyPress">
	
	
				</div>
				<div class="user-web-list">
			<div class="left-selector">
				<p class="admin-type1">一级管理员</p>
				<p class="admin-type1-in">可进行excel文件上传及个人操作记录查看</p>
				<button class="add-admin" :disabled="!LoginCheck" @click="checkMagType(1)">添加</button>
				<button class="delete-admin1" @click="showDeleteModal = true">删除</button>
				<div class="admin-left">
					<div class="scrollable-container">
						<ul class="item">
							<li class="manager-item-left" v-for="webuser in filteredLeftUserList" :key="webuser.name">
								<input type="checkbox" v-model="selectedManagers" :value="webuser.name" class="item1">
								<label for="item1">{{ webuser.name }}</label>
								<div class="edit" >
									<button class="edit-admin" @click="checkEditMagType(webuser,1)">修改</button>
								</div>
							</li>
						</ul>
						<!-- 添加一个条件判断，如果没有数据，则显示“无数据” -->
						<div v-if="filteredLeftUserList.length === 0" class="noData">无数据</div>
					</div>
				</div>
			</div>
			<div class="right-selector">
				<p class="admin-type1">二级管理员</p>
				<p class="admin-type1-in">可查看所有操作记录以及包裹记录</p>
				<button class="add-admin2" @click="checkMagType(2)" :disabled="!LoginCheck">添加</button>
				<button class="delete-admin2" @click="showDeleteModal = true">删除</button>
				<div class="admin-right">
					<div class="scrollable-container">
						<ul class="item">
							<li class="manager-item-left" v-for="webuser in filteredRightUserList" :key="webuser.name">
								<input type="checkbox" v-model="selectedManagers" :value="webuser.name" class="item2">
								<label for="item2">{{ webuser.name }}</label>
								<div class="edit" >
									<button class="edit-admin" @click="checkEditMagType(webuser,2)">修改</button>
								</div>
	
							</li>
						</ul>
						<!-- 添加一个条件判断，如果没有数据，则显示“无数据” -->
						<div v-if="filteredRightUserList.length === 0" class="noData">无数据</div>
					</div>
				</div>
			</div>
		</div>
	
	
				<div class="banner-upload">
					<a>用户端banner上传</a>
					<p>点击上传jpg/png文件,尺寸为1920*620px/335*110px,大小不超过10M,点击上传后请等待几秒
					</p>
	
					<input type="file" id="fileInput" accept="image/jpeg, image/png"  @change="handleFileChange">
					<button @click="uploadImage()" class="img_upload" :disabled="!LoginCheck">上传</button>
					<button @click="deleteImage()" class="img_delete">删除</button>
					<!-- <button @click="getImage()" class="img_get">获取</button>
					<img :src = "ImageUrl" class="img-url"> -->
	
				</div>
				<!-- 模态框 -->
	
				<!-- 添加管理员模态框 -->
				<div :class="{ 'modal-overlay': showAddModal }">
					<div v-if="showAddModal" class="addManagerModal">
						<input  v-if="showEditModal" type="text" v-model="newManager.username" placeholder="用户名">
						<input type="password" v-model="newManager.password" placeholder="密码">
						<button @click="addManager" class="addManagerModal-true">确定</button>
						<button @click="showAddModal = false" class="addManagerModal-false">取消</button>
					</div>
				</div>
				<!-- 删除管理员模态框 -->
				<div :class="{ 'modal-overlay': showDeleteModal }">
				<div v-if="showDeleteModal" class="deleteManagerModal">
	
	
				<!-- 显示已选中的管理员信息 -->
					<div v-for="manager in selectedManagers" :key="manager.id">
						{{ manager.username }}
					</div>
					<button @click="deleteManagers">删除</button>
					<button @click="showDeleteModal = false">取消</button>
				</div>
			</div>
			</div>
		</div>
	</v-scale-screen>
	</template>
	
	<script>
		import Footer from '../components/Footer.vue';
		import axios from 'axios';
		import Vue from 'vue'
		import VScaleScreen from 'v-scale-screen'
	
		Vue.use(VScaleScreen)
	
		export default {
			name: 'UserList',
			props: ['router', 'route'], // 确保这两个 props 在父组件中传递了
			data() {
				return {
				currentUserType:this.$route.query.currentUserType,
				currentUserName:this.$route.query.currentUserType,
				wxUserList: [],
				userList:[],
	
				leftUserList: [],
				rightUserList: [],
				showAddModal: false,
				showDeleteModal: false,
				showEditModal: false,
				newManager: {
					name: '',
					password: '',
					type:''
				},
				selectedManagers: [], // 存储勾选的管理员
				deleteList:{
					username:''
				},
				deleteListFlag:false, //是否全部删除标志
				deleteList_F:[],//删除失败列表
				filteredItems: [], // 用于存储筛选后的数据
				searchKeyword: '', // 添加搜索关键词属性
				noData: false,
				searchManagerName: '' ,// 添加搜索管理员名字属性
				file:[],
				addType:'',
				editName:'',
				LoginCheck: false,
				KeyWord:'',
				wxuserNum:'',
				avatar:''
				// ImageUrl:''
				};
			},
			mounted() {
				//console.log("props UT:", this.$route.query.currentUserType);
				//console.log("props UN:", this.$route.query.currentUserName);
				this.avatar = 'https://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRna42FI242Lcia07jQodd2FJGIYQfG0LAJGFxM4FbnQP6yfMxBgJ0F3YRqJCJ1aPAK2dQagdusBZg/0';
				// 页面加载时从localStorage读取用户类型
				if (this.$route.query.currentUserType && this.$route.query.currentUserName) {
	
							if(this.$route.query.currentUserType === ''|| this.$route.query.currentUserName ===''){
								this.LoginCheck = false;
								this.$router.push('/');
								return;
							}else if (this.$route.query.currentUserType === "0"){
								this.LoginCheck = true;
								this.fetchUserList();
								this.fetchManagerList() ;
							}
							else{
								this.LoginCheck = false;
								this.$message({
									message: "无访问权限",
									type: 'error'
									});
							}
				}
	
				else{
	
				this.LoginCheck = false;
				this.$router.push('/');
				return;
	
				}
	
	
			},
			components: {
				Footer
			},
			computed: {
	
				filteredLeftUserList() {
				// 根据搜索管理员名字过滤左侧管理员列表
				return this.leftUserList.filter(webuser => {
					// 使用正则表达式匹配搜索关键词中的数字
					const digitPattern = /\d+/g; // 匹配一个或多个数字的模式
					const searchDigits = this.searchManagerName.match(digitPattern); // 提取搜索关键词中的数字
					if (searchDigits) {
						// 如果搜索关键词中有数字，则只匹配含有该数字的管理员名字
						return searchDigits.some(digit => webuser.name.includes(digit));
					} else {
						// 如果搜索关键词中没有数字，则按照原来的方式匹配管理员名字
						const pattern = new RegExp(this.searchManagerName, 'i'); // 'i' 表示不区分大小写
						return pattern.test(String(webuser.name));
					}
				});
			},
			filteredRightUserList() {
				// 根据搜索管理员名字过滤右侧管理员列表
				return this.rightUserList.filter(webuser => {
					// 使用正则表达式匹配搜索关键词中的数字
					const digitPattern = /\d+/g; // 匹配一个或多个数字的模式
					const searchDigits = this.searchManagerName.match(digitPattern); // 提取搜索关键词中的数字
					if (searchDigits) {
						// 如果搜索关键词中有数字，则只匹配含有该数字的管理员名字
						return searchDigits.some(digit => webuser.name.includes(digit));
					} else {
						// 如果搜索关键词中没有数字，则按照原来的方式匹配管理员名字
						const pattern = new RegExp(this.searchManagerName, 'i'); // 'i' 表示不区分大小写
						return pattern.test(String(webuser.name));
					}
				});
			}
	
	
			},
			methods: {
				checkEditMagType(webuser,n){
					this.showEditModal = false;
					this.showAddModal = true;
					// console.log("type:");
					// console.log("webuser");
					// console.log(webuser);
					this.newManager["type"]=n;
					this.editName = webuser.name;
					this.addType = 3;
				},
	
				cancelEditManager() {
					// 关闭修改管理员模态框
					this.showEditModal = false;
				},
				searchManagers() {
					// 搜索逻辑已经在 computed 属性中实现，这里不需要再写代码
					// 搜索结果将通过 computed 属性的 filteredLeftUserList 和 filteredRightUserList 返回
				},
				 // 在键盘事件监听中判断是否按下了回车键，如果是则调用搜索方法
				 onKeyPress(event) {
					if (event.key === 'Enter') {
						this.searchManagers();
					}
				},
				checkMagType(n){
					this.showEditModal = true;
					this.showAddModal = true;
					// console.log("type:");
					// console.log(n);
					this.newManager["type"]=n;
					this.addType=n;
				},
				addDeleteList(id){
					this.selectedManagers.push(id);
					// console.log("deleteList:");
					// console.log(this.selectedManagers);
				},
				fetchUserList() {
				axios.get('https://www.hxlogistics.top/to_url/Wxuser/getall/')
					.then(response => {
						
					this.wxUserList = response.data; // 假设返回的数据格式为 [{ id: 1, avatar: 'path/to/avatar.jpg', username: 'username1' }, ...]
					this.wxuserNum = response.data.length;
					
					for(let i =0; i<this.wxUserList.length;i++){
						this.wxUserList[i].avatar="https://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRna42FI242Lcia07jQodd2FJGIYQfG0LAJGFxM4FbnQP6yfMxBgJ0F3YRqJCJ1aPAK2dQagdusBZg/0";
						
					}
					console.log(response);
					console.log("fetchUserList");
					})
					.catch(error => {
					console.error('Failed to fetch user list:', error);
					});
				},
				fetchManagerList() {
				axios.get('https://www.hxlogistics.top/to_url/Manager/getall/') // 假设后端接口为 '/api/users'
					.then(response => {
	
					 // 获取所有管理员列表
					 this.userList = response.data;
					// 分别筛选类型为 1 和类型为 2 的管理员
					this.leftUserList = this.userList.filter(webuser => webuser.type === 1);
					this.rightUserList = this.userList.filter(webuser => webuser.type === 2);
					// console.log(response.data);
					})
					.catch(error => {
					console.error('Failed to fetch user list:', error);
					});
				},
	
				to(path) {
				// this.$router.push(path);
				let stringWithSlash = path;
				this.KeyWord = stringWithSlash.replace(/\//g, '');
	
				this.$router.push({
						path: path,
						name: this.KeyWord,
						query: {
						currentUserType: this.$route.query.currentUserType,
						currentUserName: this.$route.query.currentUserName
						}
						});
			},
				addManager() {
					if(this.addType!=3){
						// 发送添加管理员请求
						axios.post('https://www.hxlogistics.top/to_url/Manager/', this.newManager)
						.then(response => {
						//处理添加成功的逻辑
						// console.log('response.data:', response.data);
						if(response.data.manager_id!=-1){
							this.$message({
							message: '添加成功',
							type: 'success'
						});
	
						setTimeout(() => {
							this.fetchManagerList(); // 调用获取数据的方法方法
							}, 2000);
	
						}
						else{
							this.$message({
							message: '添加失败，请检查用户名是否已存在',
							type: 'error'
							});
	
						}
	
						this.newManager.password = '';
						this.newManager.username = '';
						this.showAddModal = false;
						})
						.catch(error => {
						// 处理添加失败的逻辑
						// console.error('添加失败', error);
						this.$message({
							message: '添加失败，请检查用户名是否已存在',
							type: 'error'
						});
						this.newManager.password = '';
						this.newManager.username = '';
						});
					}
					else{
						this.newManager.username = this.editName;
						// console.log('this.editName',this.editName);
						// console.log('this.newManager',this.newManager.username);
						// 发送添加管理员请求
						axios.put('https://www.hxlogistics.top/to_url/Manager/', this.newManager)
						.then(response => {
						// 处理添加成功的逻辑
						// console.log('修改成功', response.data);
						if(response.data.manager_id!=-1){
							this.$message({
							message: '修改成功',
							type: 'success'
						});
	
						setTimeout(() => {
							this.fetchManagerList(); // 调用获取数据的方法方法
							}, 2000);
						}
						else{
							this.$message({
							message: '修改失败',
							type: 'error'
							});
	
						}
	
						this.newManager.password = '';
						this.newManager.username = '';
						this.showAddModal = false;
						})
						.catch(error => {
						// 处理添加失败的逻辑
						// console.error('添加失败', error);
						this.$message({
							message: '修改失败，请检查用户名是否已存在',
							type: 'error'
						});
						this.newManager.password = '';
						this.newManager.username = '';
						});
	
	
					}
	
					},
					deleteManagers() {
						// console.log("selectedManagers:");
						// console.log(this.selectedManagers);
						// 逐个发送删除管理员请求
						for(let i = 0; i<this.selectedManagers.length;i++){
							// console.log("this.selectedManagers[i]:");
							// console.log(this.selectedManagers[i]);
							this.deleteList['username']=this.selectedManagers[i];
							axios.delete('https://www.hxlogistics.top/to_url/Manager/',{params:this.deleteList})
							// axios.delete('http://47.98.58.79:8080/Manager', {
							// 	name: this.selectedManagers[i]
							// })
							.then(response => {
										// 处理添加成功的逻辑
										// console.log("deleteManager")
										// console.log(this.deleteManager);
										// console.log('删除成功', response);
										if(response.data.message!="OK"){
											this.deleteList_F.push(this.selectedManagers[i]);
											// this.deleteListFlag=false;
										// 	this.$message({
										// 	message: '删除成功',
										// 	type: 'success'
										// });
										}
	
	
											// this.deleteListFlag=true;
	
											// this.$message({
											// message: '删除失败',
											// type: 'error'
											// });
											// console.log('删除成功', response.data);
	
	
	
	
										this.showDeleteModal = false;
										})
										.catch(error => {
										// 处理添加失败的逻辑
										// console.error('删除失败', error);
										this.$message({
											message: '删除失败',
											type: 'error'
										});
										});
	
						}
						if(this.deleteList_F.length===0){
							this.$message({
											message: '删除成功',
											type: 'success'
										});
						setTimeout(() => {
							this.fetchManagerList(); // 调用获取数据的方法方法
							}, 2000);
	
						}
						else{
							this.$message({
											message: '删除失败',
											type: 'error'
										});
						}
	
					},
					uploadImage() {
						// 获取文件对象
						const file = this.file;
	
						// 检查文件大小是否小于10MB
						if (file.size > 10 * 1024 * 1024) { // 将文件大小转换为字节
							// 文件大小超过10MB，给出提示并阻止上传
							this.$message.error("文件大小超过10MB，请选择大小在10MB以下的文件。");
							console.log("文件大小超过10MB，请选择大小在10MB以下的文件。");
						} else {
							// 文件大小符合要求，执行上传逻辑
							// const reader = new FileReader();
							// reader.onload = () => {
								const ImageUrl = this.ImageUrl;
								this.getBase64(file)
								.then((result) => {
									// console.log("result");
									// console.log(result);
									
									const ImageUrl = result;	
									console.log("imageUrl");
									console.log(ImageUrl);

									// 发送数据给后端的逻辑
									fetch('https://www.hxlogistics.top/to_url/Banner/', {
									method: 'POST',
									headers: {
										'Content-Type': 'application/json'
									},
									body: JSON.stringify({ url: ImageUrl }) // 将图片URL作为参数传递给后端
								})
							
								.then(response => {
									// 处理上传成功后的逻辑
									if (response.ok != false) {
										this.$message({
											message: '上传成功',
											type: 'success'
										});
									} else {
										this.$message({
											message: '上传失败',
											type: 'error'
										});
									}
								})
								.catch(error => {
									// 处理上传失败的逻辑
									this.$message({
										message: '上传失败',
										type: 'error'
									});
								})
								})

 
								// this.ImageUrl = imageUrl;
								// imageUrl = 
								// console.log("imageUrl");
								// console.log(this.ImageUrl);
								// 发送数据给后端的逻辑
								
							// };
							// reader.readAsDataURL(file);
						}
	
					},
					// 图片转Base64
					getBase64(file) {
					return new Promise((resolve, reject) => {
						const reader = new FileReader();
						let imgBase64 = '';
						reader.readAsDataURL(file);
						reader.onload = () => {
						imgBase64 = reader.result;
						};
						reader.onerror = error => reject(error);
						reader.onloadend = () => resolve(imgBase64);
					});
					},

					handleFileChange(event) {
						this.file = event.target.files[0];
						const formData = new FormData();
						formData.append('file', this.file);
					},
					deleteImage(){
						axios.delete('https://www.hxlogistics.top/to_url/Banner/')
							.then(response => {
								// console.log("response:");
								// console.log(response);
								if(response.data.message==="OK"){
									this.$message({
											message: '删除成功',
											type: 'success'
										});
								}
								else{
									this.$message({
											message: '请勿重复删除',
											type: 'error'
										});
								}
	
	
							})
							.catch(error => {
									console.log(error);
	
							});
					},
					// getImage(){
					// 	axios.get('http://47.98.58.79:8080/Banner/get')
					// 		.then(response => {
					// 			console.log("response:");
					// 			console.log(response);
					// 			this.ImageUrl = response.data.url;
	
	
					// 		})
					// 		.catch(error => {
					// 				console.log(error);
	
					// 		});
					// }
	
	
	
			}
		}
	</script>
	
	<style scoped>
	.list_all{
		position: absolute;
		display: flex;
		width: 78%;
		height: 98%;
		margin-left: 20%;
		margin-top: 2%;
	}
	
	.scrollable-container {
		max-height: 100%; /* 设置最大高度 */
		overflow-y: auto; /* 当内容溢出时显示垂直滚动条 */
	}
	
	
	.user-wx-title{
		position: absolute;
		margin-left: 2%;
		margin-top: 1%;
	
	}
	
	.user-list{
		width: 30%;
		height: 88%;
		margin-top: 2%;
		margin-left: 2%;
	}
	
	
	.user-wx-list {
		margin-top: 6%;
		width: 100%;
		height: 100%;
		border-radius:10px;
		border: 1px solid #ccc;
	}
	
	
	.user-wx-list .user-wx {
		width: 92%;
		height: 5%;
		margin-left: 4%;
		margin-top: 2%;
		font-size: 3mm;
		border-bottom: 1px solid #e7e7e7;
		display: flex;
		flex-direction: row;
	}
	
	.user-wx-list .user-wx:first-child{
		margin-top: 5%;
	}
	
	.user-wx-list .user-wx a {
	 margin-top: 3.5%;
	 margin-left: 20%;
	}
	.user-wx-list .user-wx img {
		width: 40px;
		height: 40px;
		margin-left: 18%;
		border-radius: 50%;
	}
	
	.user-wx-list .user-wx p {
		margin-top: 2%;
		margin-left: 20%;
		font-size: 18px;
	}
	
	.user-web-title{
		position: absolute;
		margin-left: 35%;
		margin-top: 1%;
	}
	
	.user-web-list {
		position: absolute;
		width: 60%;
		margin-top: 3.5%;
		margin-left: 35%;
		box-shadow: 0 0 50px rgba(40, 47, 135, 0.1);
		height: 72%;
		border-radius: 10px;
		list-style-type: none;
		padding: 0;
		background-color: #282F87;
	
	}
	.user-web-list .admin-type1{
		margin-left: 8%;
		margin-top: 6%;
		color: #FFF;
		font-size: 5mm;
		float: left; /* 左浮动 */
	}
	
	.user-web-list .admin-type1-in{
		margin-left: -21%;
		margin-top: 13%;
		color: #F67A21;
		font-size: 3mm;
		float: left; /* 左浮动 */
	}
	
	
	.user-web-list .admin-left{
		height:87.9%;
		margin-top: 11.3%;
		background-color: #FFF;
		border: 1px solid #282F87;
		border-bottom-left-radius: 9px;
	}
	
	
	.user-web-list .item{
		margin-left: 20px;
		padding: 8px;
	}
	
	.user-web-list .item:first-child{
		margin-top: 15px;
	}
	
	
	.left-selector {
			position: absolute;
			left: 0;
			top: 0;
			width: 50%;
			height: 99.6%;
			border: 1px solid #FFF;
			border-top-left-radius: 10px;
			border-bottom-left-radius: 10px; /* 左下角圆角 */
	
			overflow: hidden; /* 清除浮动 */
	}
	
	.left-selector li{
		display: flex;
		overflow: hidden; /* 清除浮动 */
		height: 5vh;
		font-size: 6mm;
	}
	
	.left-selector li label{
		flex: 0.8;
	}
	
	.right-selector li{
		display: flex;
		overflow: hidden; /* 清除浮动 */
		font-size: 6mm;
		height: 5vh;
	}
	.right-selector li label{
		flex: 0.8;
	}
	
	.add-admin,
	.delete-admin1,
	.delete-admin2,
	.add-admin2,
	.edit-admin{
		border: none; /* 去掉边框 */
		background-color: transparent; /* 设置背景色为透明 */
		/* 如果需要去掉按钮的默认样式（如阴影、内边距等），可以添加以下样式 */
		box-shadow: none;
		padding: 0; /* 如果需要去掉内边距 */
		position: relative;
	}
	
	.add-admin,
	.add-admin2
	{
		margin-top: 5%;
		margin-left: 10%;
		color: #1cda9b;
		font-size: 5mm;
	}
	
	.add-admin2{
		margin-left: 18%;
	}
	
	.delete-admin1,
	.delete-admin2{
		margin-top: 5%;
		margin-left: 2%;
		color: #ff632f;
		font-size: 5mm;
	}
	
	.edit{
		/* position: relative; */
		display: flex;
		font-size: 5mm;
		/* margin-right: 10%; */
		/* margin-top: 1%; */
		/* width: 30px; */
	
	}
	.edit-admin{
		font-size: 5mm;
	
		color: #282F87;
	}
	
	.add-admin:hover,
	.add-admin2:hover{
		color: #fff;
	}
	
	.delete-admin1:hover,
	.delete-admin2:hover{
		color: #fff;
	}
	
	.edit-admin:hover{
		color: #b8d7ff;
	}
	
	.right-selector {
		position: absolute;
		right: 0;
		top: 0;
		width: 50%;
		height: 99.6%;
		background-color: #282F87;
		border: 1px solid #FFF;
		border-top-right-radius: 10px;
		border-bottom-right-radius: 10px; /* 右下角圆角 */
		overflow: hidden; /* 清除浮动 */
	}
	
	.user-web-list .admin-right{
		height:87.3%;
		margin-top: 11.3%;
		background-color: #FFF;
		border: 1px solid #282F87;
		border-bottom-right-radius: 9px;
	}
	
	.button-container {
		width: 20px;
		position: absolute;
		left: 50%;
		top: 50%;
		display: flex; /* 设置按钮容器为flex布局 */
		flex-direction: column; /* 设置按钮容器为垂直布局 */
		align-items: center; /* 水平居中按钮 */
		transform: translate(-50%, -50%); /* 将按钮容器水平垂直居中 */
	}
	
	.button {
		display: block;
		margin-top: 10px;
		font-size: 1px;
	}
	
	
	/* 模态框 */
	.modal {
	  display: none; /* 初始隐藏 */
	  position: fixed; /* 固定定位，覆盖在页面上 */
	  z-index: 1; /* 设置模态框在页面上的层级 */
	  left: 0;
	  top: 0;
	  width: 100%; /* 全宽度 */
	  height: 100%; /* 全高度 */
	  overflow: auto; /* 允许滚动 */
	  background-color: rgba(0,0,0,0.4); /* 背景颜色，半透明黑色 */
	}
	
	/* 模态框内容 */
	.modal-content {
	  background-color: #fefefe; /* 内容区域背景色 */
	  margin: 15% auto; /* 居中 */
	  padding: 20px;
	  border: 1px solid #888;
	  width: 40%; /* 宽度为80% */
	  height: 150px;
	  text-align: center; /* 文字居中 */
	}
	
	.username,
	.password{
		display: flex;
		margin-left: 27%;
	}
	
	.modal-content input[type="text"],
	.modal-content input[type="password"] {
	  display: block; /* 将输入框显示为块级元素 */
	  margin-top: 13px;
	  margin-left: 2%;
	  height: 20px;
	}
	
	/* 关闭按钮 */
	.close {
	  color: #aaa;
	  margin-right: 23% ;
	  float: right;
	  font-weight: bold;
	  margin-top: -10px;
	  width:  80px;
	  height: 30px;
	  margin-top: 3%;
	  border: none; /* 去掉边框 */
	  border-radius: 8px;
	}
	
	.close:hover,
	.close:focus {
	  color: black;
	  text-decoration: none;
	  cursor: pointer;
	}
	
	.submit{
		background-color: #7ab55a;
		border: none; /* 去掉边框 */
		margin-top: 3%;
		margin-left: 23%;
		width:  80px;
		height: 30px;
		border-radius: 8px;
		color: #FFF;
		float: left;
	}
	
	.submit:hover,
	.submit:focus {
	  background-color: #00a500;
	  text-decoration: none;
	  cursor: pointer;
	}
	
	.deleteManagerModal{
		background-color: #fff;
		width: 50%;
		height: 20%;
		border-radius: 10px;
		display: flex; /* 设置按钮容器为flex布局 */
		justify-content: center;
		align-items: center; /* 水平居中按钮 */
	}
	
	.deleteManagerModal button{
		padding:6px;
		margin-left: 10px;
		border: none;
		outline: none;
		border-radius: 8px;
		height: 20%;
		width: 30%;
	}
	
	.deleteManagerModal button:first-child{
		background-color: #c8e1ba;
		color: #00a500;
	}
	
	.deleteManagerModal button:first-child:hover{
		background-color: #00a500;
		color:#fff;
	}
	
	.deleteManagerModal button:last-child{
		background-color: #f1b4ab;
		color: #ef644f;
	}
	
	.deleteManagerModal button:last-child:hover{
		background-color: #ef644f;
		color: #fff;
	}
	
	.banner-upload{
		position: absolute;
		overflow: hidden; /* 清除浮动 */
		margin-left: 35%;
		border: 1px solid #282F87;
		margin-top: 56%;
		display: flex;
		width: 60%;
		height: 22vh;
		border-radius: 10px;
	}
	
	.banner-upload a{
		position: absolute;
		font-size: 5mm;
		font-weight: 600;
		float: left;
		width: 45%;
		margin-left: 5%;
		margin-top: 5%;
	}
	
	.banner-upload p{
		position: absolute;
		font-size: 4mm;
		float: left;
		width:100%;
		margin-top: 9%;
		margin-left: 5%;
	
	}
	
	.banner-upload input{
		position: absolute;
		margin-left: 2%;
		background-color: transparent;
		border: none;
		padding: 0;
	}
	
	
	.banner-upload button,
	.banner-upload input
	{
		position: relative;
		height: 24px;
		margin-top: 5%;
	}
	
	.banner-upload input
	{
		position: absolute;
		margin-left: 40%;
	}
	
	.banner-upload button{
		position: absolute;
		width: 10%;
		height: 4vh;
		margin-left:85%;
		border: 1px solid #282F87;
		border-radius: 50px;
		background-color: #282F87; /* 设置背景色 */
		color: #fff; /* 设置文字颜色 */
	}
	.banner-upload .img_delete{
		position: absolute;
		width: 50px;
		margin-left:87%;
		margin-top: 8%;
		font-size: 4mm;
		border: none;
		background-color: transparent; /* 设置背景色 */
		color: #eb6e6e; /* 设置文字颜色 */
	}
	.banner-upload .img_delete:hover{
		color: #f01717; /* 设置文字颜色 */
		background-color: transparent;
		border: none;
	}
	
	.banner-upload button:hover{
		background-color: #74acff;
		border: 1px solid #74acff;
	}
	
	#lastPage{
		position: fixed;
		margin-left: 21.5%;
		margin-top: 1%;
	
	}
	
	#lastPage:hover{
		fill: #55aaff;
	}
	
	
	.search-box-admin {
		position: absolute;
		margin-top: 0.5%; /* 顶部外边距 */
		margin-left: 76%;
		height: 5vh;
		width:18%;
		padding: 1px;
		background-color: #fff; /* 搜索框的背景色 */
		border: 1px solid #ccc; /* 添加边框 */
		border-radius: 10px;
	
	}
	
	.search-box-admin input {
		width: 140px;
		margin-left: 40px;
		padding: 8px;
		box-sizing: border-box;
		border-radius: 4px;
		border: none; /* 去除搜索框的边框 */
		outline: none; /* 移除输入框的聚焦时的边框 */
	}
	
	.search-box-admin svg{
		position: absolute;
		margin-top: 7px;
		margin-left: 15px;
	}
	
	.modal-overlay {
	  position: fixed;
	  top: 0;
	  left: 0;
	  width: 100%;
	  height: 100%;
	  background-color: rgba(0, 0, 0, 0.5); /* 半透明背景 */
	  display: flex;
	  justify-content: center;
	  align-items: center;
	}
	
	.addManagerModal {
	  background-color: white;
	  width: 35%;
	  height: 25vh;
	  padding: 20px;
	  border-radius: 5px;
	  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5); /* 阴影效果 */
	  display: flex;
	  flex-direction: column; /* 设置按钮容器为垂直布局 */
		align-items: center; /* 水平居中按钮 */
	}
	
	.addManagerModal input{
	  margin-top: 5px;
	  width: 60%;
	  border: 1px solid #e7e7e7;
	  border-radius: 5px;
	  height: 8vh;
	}
	.addManagerModal .addManagerModal-true{
		position: relative;
		border: none;
		outline: none;
		margin-top: 2%;
		width: 50%;
		height: 8vh;
		background-color: #7ab55a;
		border-radius: 5px;
	}
	
	.addManagerModal .addManagerModal-true:hover{
		background-color: #fff;
		color:#7ab55a;
		border: 1px solid #7ab55a;
	}
	
	.addManagerModal .addManagerModal-false{
		border: none;
		outline: none;
		height: 8vh;
		width: 50%;
		border-radius: 5px;
		margin-top: 1%;
		background-color:#fbc1b8;
		color:#ef644f;
	
	}
	
	.addManagerModal .addManagerModal-false:hover{
		background-color:#ef644f;
		color:#fff;
	}
	.noData{
		display: flex;
		justify-content: center;
		color: #888;
		font-size: 4mm;
	}
	
	.editManagerModal {
	  background-color: white;
	  padding: 20px;
	  border-radius: 5px;
	  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5); /* 阴影效果 */
	  display: flex;
	  flex-direction: column; /* 设置容器为垂直布局 */
	  align-items: center; /* 水平居中内容 */
	}
	
	.editManagerModal input {
	  margin-top: 5px;
	  border: 1px solid #e7e7e7;
	  border-radius: 5px;
	  height: 20px;
	}
	
	.editManagerModal button {
	  width: 50%;
	  margin-top: 3%;
	  border-radius: 5px;
	  cursor: pointer;
	}
	
	.editManagerModal button.editManagerModal-true {
	  background-color: #7ab55a;
	  border: none;
	  outline: none;
	}
	
	.editManagerModal button.editManagerModal-true:hover {
	  background-color: #fff;
	  color: #7ab55a;
	  border: 1px solid #7ab55a;
	}
	
	.editManagerModal button.editManagerModal-false {
	  background-color: #fbc1b8;
	  border: none;
	  outline: none;
	  color: #ef644f;
	}
	
	.editManagerModal button.editManagerModal-false:hover {
	  background-color: #ef644f;
	  color: #fff;
	}
	
	</style>../components/Footer.vue
	