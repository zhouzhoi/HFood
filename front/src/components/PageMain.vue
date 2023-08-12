<template>
  <el-table :data="tableData">
    <el-table-column prop="date" label="日期" width="140">
    </el-table-column>
    <el-table-column prop="name" label="姓名" width="120">
    </el-table-column>
    <el-table-column prop="address" label="地址">
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  name: "PageMain",
  data() {
    const item = {
      date: '2016-05-02',
      name: '王小虎',
      address: '上海市普陀区金沙江路 1518 弄'
    };
    return {
      tableData: Array(20).fill(item)
    }
  },
  methods:{
    loadGet(){
      //用于发送 GET 请求到指定的 URL，然后处理响应数据
      this.$axios.get(this.$httpUrl+'/user/list').then(res=>res.data).then(res=>{
        console.log(res)
      })
    },
    loadPost(){
      //全局地址在main.js中设置
      this.$axios.post(this.$httpUrl+'/user/listPageC1', {}).then(res=>res.data).then(res=> {
        console.log(res)
      })
    }
  },
  beforeMount() {
    //this.loadGet();
    this.loadPost()
  }
}
</script>

<style scoped>

</style>