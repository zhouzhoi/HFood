<template>
  <el-container style="height: 100%; border: 1px solid #eee">
    <el-aside :width="aside_witdh" style="height: 100vh;background-color: rgb(238, 241, 246);margin-left: -1px;">
      <SideBar :isCollapse="isCollapse"></SideBar>
      <!-- 通过isCollapse传递一个值 -->
    </el-aside>

    <el-container style="height: 100%;">
<!--      <el-header style="text-align: right; font-size: 12px">-->
<!--        <PageHeader @doCollapse="doCollapse" :icon="icon"></PageHeader>-->
<!--        &lt;!&ndash; doCollapse是自定义调用函数&ndash;&gt;-->
<!--      </el-header>-->

      <el-header style="text-align: right; font-size: 12px;height: 100%;border-bottom: rgba(168,168,168,0.3) 1px solid;background-color: #545c64;">
        <PageHeader @doCollapse="doCollapse" :icon="icon"></PageHeader>
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
    data(){
      return {
        isCollapse:false,
        aside_witdh:'200px',
        icon:'el-icon-s-fold'
      }
    },
    methods:{
      doCollapse(){
        console.log(1111)
        this.isCollapse = !this.isCollapse
        if(!this.isCollapse){// 展开
          this.aside_witdh='200px'
          this.icon='el-icon-s-fold'
        }else{//关起、关闭、收起
          this.aside_witdh='64px'
          this.icon='el-icon-s-unfold'
        }
      }
    }
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
