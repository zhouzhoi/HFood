<template>
  <el-menu
      style="height: 100%;"
      default-active="/HomePage"
      :collapse="isCollapse"
      :collapse-transition="false"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b">
<!--default-active当前激活菜单的index-->
<!--collapse是否水平折叠收起菜单-->
<!--collapse-transition是否开启折叠动画-->
    <el-menu-item index="/HomePage">
      <i class="el-icon-s-home"></i>
      <span slot="title">首页</span>
    </el-menu-item>


  </el-menu>
</template>

<script>
export default {
  name: "SideBar",
  data() {
    return {
      //isCollapse:false
    }
  },
  props:{
    isCollapse:Boolean
  }
}
</script>

<style scoped>

</style>