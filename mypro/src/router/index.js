import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from '../views/Index.vue'
import OpHome from '../views/OpHome.vue'
import DataPack from '../views/DataPack.vue'
import Data from '../views/Data.vue'
import UserList from '../views/UserList.vue'
import UserCorrent from '../views/UserCorrent.vue'

Vue.use(VueRouter)

const routes = [{
			path:'/',
			name:'Home',
			component:Index
		},{
			path:'/OpHome',
			name:'OpHome',
			component:OpHome,
			meta:{requireAuth:true}
		},{
			path:'/Index',
			name:'Index',
			component:Index
		},
		{
			path:'/Data',
			name:'Data',
			component:Data,
			meta:{requireAuth:true}
		},
		{
			path:'/DataPack',
			name:'DataPack',
			component:DataPack,
			meta:{requireAuth:true}
		},
		{
			path:'/UserCorrent',
			name:'UserCorrent',
			component:UserCorrent,
			meta:{requireAuth:true}
		},{

			path:'/UserList',
			name:'UserList',
			component:UserList,
			meta:{requireAuth:true}
		},
		]

		//解决重复路由报异常问题
		const originalPush = VueRouter.prototype.push;
		VueRouter.prototype.push = function push(location) {
			return originalPush.call(this, location).catch(err => err)
		}
		const router = new VueRouter({
			mode: 'history',
			base: process.env.BASE_URL,
			routes
		})
			// 全局路由守卫
		router.beforeEach((to, from, next) => {
			let token = localStorage.getItem('token')
			if (to.meta.needLogin) { // 判断该路由是否需要登录权限
			if (token) { // 判断是否已经登录
				next()
			}
			else {
				next({path: '/Index'}) //跳转到登录页
			}
			} else { 
				next()
			}
		})
		
		export default router
