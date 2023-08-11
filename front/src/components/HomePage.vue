<template>
  <el-container style="height: 500px; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <SideBar></SideBar>
    </el-aside>

    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <PageHeader></PageHeader>
      </el-header>

      <el-main>
        <PageMain></PageMain>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
  import SideBar from "@/components/SideBar";
  import PageHeader from "@/components/PageHeader";
  import PageMain from "@/components/PageMain";
  export default {
    name: "HomePage",
    components: {PageMain, PageHeader, SideBar},

  }
</script>

<style scoped>
  .el-header {
    #background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }
  .el-main{
    padding: 5px;
  }
  .el-aside {
    color: #333;
  }
</style>
