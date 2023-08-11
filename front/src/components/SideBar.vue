<template>
  <el-menu
      style="height: 100%;"
      default-active="/HomePage"
      :collapse="isCollapse"
      :collapse-transition="false"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b">
<!--default-active	当前激活菜单的 index-->
    <el-menu-item index="/HomePage">
      <i class="el-icon-s-home"></i>
      <span slot="title">首页</span>
    </el-menu-item>


  </el-menu>
</template>

<script>
export default {
  name: "SideBar"
}
</script>

<style scoped>

</style>