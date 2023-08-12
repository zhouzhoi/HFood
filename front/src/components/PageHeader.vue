<template>
  <div style="display: flex;line-height: 60px;" >
    <div style="margin-top: 8px; color: #ffd04b;">
      <i :class="icon" style="font-size: 20px;cursor: pointer;" @click="collapse"></i>
    </div>
    <div style="flex: 1;text-align: center;font-size: 34px;">
      <span style="color: #ffd04b;">欢迎来到HFood配餐管理系统</span>
    </div>
    <el-dropdown>
      <i class="el-icon-setting" style="margin-right: 15px; color: #ffd04b;"></i>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item @click.native="toUser">个人中心</el-dropdown-item>
        <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
    <span style="color: #ffd04b;">王小虎</span>
  </div>
</template>

<script>
export default {
  name: "PageHeader",
  props:{
    icon:String//接收主页中icon的值
  },
  methods:{
    toUser(){
      console.log('to_user')
    },
    logout() {
      console.log('logout')
    },
    collapse(){
      this.$emit('doCollapse')//自定义doCollapse函数，点击后调用
    }
  }
}
</script>

<style scoped>

</style>