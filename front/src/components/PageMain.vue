<template>
  <div>
    <div style="margin-bottom: 5px;">
<!--      @keyup.enter.native="loadPost"的意思是按下回车键运行loadPost-->
<!--      clearable是可清空的输入框-->
      <el-input v-model="name" placeholder="请输入名字" suffix-icon="el-icon-search" style="width: 200px;"
                @keyup.enter.native="loadPost" clearable></el-input>
      <el-select v-model="sex" filterable placeholder="请选择性别" style="margin-left: 5px;">
        <el-option
            v-for="item in sexs"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>
      <el-button type="info" style="margin-left: 5px;" @click="loadPost">查询</el-button>

      <el-button type="warning" style="margin-left: 5px;" @click="add">新增</el-button>
    </div>
  <!--  设置表头样式-->
    <el-table :data="tableData"
              :header-cell-style="{ background: 'rgb(255,255,255)', color: '#000000' }"
              border
      >
      <el-table-column prop="id" label="ID" width="60">
      </el-table-column>
      <el-table-column prop="no" label="账号" width="180">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="180">
      </el-table-column>
      <el-table-column prop="age" label="年龄" width="100">
        <template slot-scope="scope">
          <span v-if="scope.row.age >= 18">{{ scope.row.age }}</span>
          <el-tag v-else type="danger">{{ scope.row.age }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="sex" label="性别" width="80">
        <template slot-scope="scope">
          <el-tag
              :type="scope.row.sex === 1 ? 'primary' : 'success'"
              disable-transitions>{{scope.row.sex === 1 ? '男' : '女'}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="roleId" label="角色" width="120">
        <template slot-scope="scope">
          <el-tag
              :type="scope.row.roleId === 0 ? 'danger' : (scope.row.roleId === 1 ? 'primary' : 'success')"
              disable-transitions>{{scope.row.roleId === 0 ? '超级管理员' :
              (scope.row.roleId === 1 ? '管理员' : '用户')}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="phone" label="电话" width="180">
      </el-table-column>
      <el-table-column prop="operate" label="操作" fixed="right">
        <template slot-scope="scope">
          <el-button size="small" type="info" icon="el-icon-edit" @click="mod(scope.row)">编辑</el-button>
          <el-popconfirm
              title="确定删除吗？"
              @confirm="del(scope.row.id)"
              style="margin-left: 5px;"
          >
            <el-button slot="reference" size="small" type="danger" icon="el-icon-delete" >删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[5, 10, 20,30]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>
  </div>
</template>

<script>
export default {
  name: "PageMain",
  data() {

    return {
      tableData: [],
      //分页数据
      pageSize:10,
      pageNum:1,
      total:0,
      //监视namo和sex
      name:'',
      sex:'',
      //设置性别搜索的下拉框内容
      sexs:[
        {
          value: '1',
          label: '男'
        }, {
          value: '0',
          label: '女'
        }
      ],
    }
  },
  methods:{
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageNum=1//固定查询页是第一页
      this.pageSize=val//设置当前页面数据大小
      this.loadPost()//调用数据
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum=val//设置当前页面
      this.loadPost()//调用数据
    },

    loadGet(){
      //用于发送 GET 请求到指定的 URL，然后处理响应数据
      this.$axios.get(this.$httpUrl+'/user/list').then(res=>res.data).then(res=>{
        console.log(res)
      })
    },
    loadPost(){
      //全局地址在main.js中设置
      //listP不适用分页，将其修改为listPageC1，同时在data中添加数据
      this.$axios.post(this.$httpUrl+'/user/listPageC1', {
        pageSize:this.pageSize,
        pageNum:this.pageNum,
        //查询需要的参数
        param:{
          name:this.name,
          //性别搜索的参数
          sex:this.sex
        }
      }).then(res=>res.data).then(res=> {
        console.log(res)
        if (res.code==200){
          this.tableData=res.data
          this.total = res.total //总数据
        }else {
          alert('获取数据失败·')
        }

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