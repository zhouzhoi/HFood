<template>
  <div style="display: flex;line-height: 60px;">
    <div style="margin-top: 8px;">
      <i class="el-icon-s-fold" style="font-size: 20px;cursor: pointer;" ></i>
    </div>
    <div style="flex: 1;text-align: center;font-size: 34px;">
      <span>欢迎来到HFood配餐管理系统</span>
    </div>
    <el-dropdown>
      <i class="el-icon-setting" style="margin-right: 15px"></i>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item @click.native="toUser">个人中心</el-dropdown-item>
        <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
    <span>王小虎</span>
  </div>
</template>

<script>
export default {
  name: "PageHeader",
  methods:{
    toUser(){
      console.log('to_user')
    },
    logout() {
      console.log('logout')
    }
  }
}
</script>

<style scoped>

</style>