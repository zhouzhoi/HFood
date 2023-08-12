import VueRouter from 'vue-router';
//添加路由文件
const routes = [
  {
    path:'/',
    name:'login',
    component:()=>import('../components/LoginPage')
  },
  {
    //成功后跳转页面
    path:'/HomePage',
    name:'HomePage',
    component:()=>import('../components/HomePage'),
    /*children:[
      {
        path:'/HomePage',
        name:'home',
        meta:{
          title:'首页'
        },
        component:()=>import('../components/HomePage')
      },
      /!*{
          path:'/Admin',
          name:'admin',
          meta:{
              title:'管理员管理'
          },
          component:()=>import('../components/admin/AdminManage.vue')
      },
      {
          path:'/User',
          name:'user',
          meta:{
              title:'用户管理'
          },
          component:()=>import('../components/user/UserManage.vue')
      },*!/
    ]*/
  }
]
//不添加这段，则网址会出现警号
const router = new VueRouter({
  mode:'history',
  routes
})

export function resetRouter() {
  router.matcher = new VueRouter({
    mode:'history',
    routes: []
  }).matcher
}
const VueRouterPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (to) {
  return VueRouterPush.call(this, to).catch(err => err)
}
export  default router;
