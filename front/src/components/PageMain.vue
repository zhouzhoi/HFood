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
<!--    设计表格-->
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
<!--          mod(scope.row)删除这行-->
          <el-button size="small" type="info" icon="el-icon-edit" @click="mod(scope.row)">编辑</el-button>
          <el-popconfirm
              title="确定删除吗？"
              @confirm="del(scope.row.id)"
              style="margin-left: 5px;"
          >
<!--            @confirm="del(scope.row.id)"点击确认才触发-->
            <el-button slot="reference" size="small" type="danger" icon="el-icon-delete" >删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
<!--    设置分页-->
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[5, 10, 20,30]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>
<!--    设置表单-->
    <el-dialog
        title="账户信息"
        :visible.sync="centerDialogVisible"
        width="30%"
        center>

      <el-form ref="form" :rules="rules" :model="form" label-width="80px">
        <el-form-item label="账号" prop="no">
<!--          设置输入框大小-->
          <el-col :span="20">
            <el-input v-model="form.no"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="名字" prop="name">
          <el-col :span="20">
            <el-input v-model="form.name" placeholder="名字不能超过20个字符"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-col :span="20">
            <el-input v-model="form.password" show-password></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-col :span="20">
            <el-input v-model="form.age"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="form.sex">
            <el-radio label="1">男</el-radio>
            <el-radio label="0">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-col :span="20">
            <el-input v-model="form.phone"></el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="save">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "PageMain",
  data() {
    //判断年龄大小
    let checkAge = (rule, value, callback) => {
      if(value>150){
        callback(new Error('年龄输入过大'));
      }else{
        callback();
      }
    };
    //检查账号是否存在过
    let checkNoExist =(rule,value,callback)=>{
      if(this.form.id){
        return callback();
      }
      this.$axios.get(this.$httpUrl+"/user/findByNo?no="+this.form.no).then(res=>res.data).then(res=>{
        if(res.code!=200){

          callback()
        }else{
          callback(new Error('账号已经存在'));
        }
      })
    };
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
      //初始设置为false
      centerDialogVisible:false,
      //表单中的相关数据
      form:{
        id:'',
        no:'',
        name:'',
        password:'',
        age:'',
        phone:'',
        sex:'0',
        roleId:'1'
      },
      //通过rules写入表单的规则
      rules: {
        //账号需满足小于20位，同时不能重复
        no: [
          {required: true, message: "账号不能为空", trigger: "blur"},
          {max: 20, message: "账号不能超过20个字符", trigger: "blur"},
          {validator:checkNoExist,trigger: 'blur'}
        ],
        //年龄范围在0-150，且必须是数字
        age: [
          {required: true, message: '请输入年龄', trigger: 'blur'},
          {min: 1, max: 3, message: '长度在 1 到 3 个位', trigger: 'blur'},
          {pattern: /^([1-9][0-9]*){1,3}$/,message: '年龄必须为正整数字',trigger: "blur"},
          {validator:checkAge,trigger: 'blur'}
        ],
        //密码必须大于6位，且同时包含数字和字母
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' },
          { min: 6, message: '密码长度不能少于6位', trigger: 'blur' },
          {
            pattern: /^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$/,
            message: '密码必须包含数字和字母',
            trigger: 'blur'
          }
        ],
        //电话必须是11位数，且是1开头，中间是3，4，5，6，7，8，9
        phone: [
          { required: true, message: '电话不能为空', trigger: 'blur' },
          {pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/, message: "请输入正确的手机号码", trigger: "blur"}
        ]
      }
    }
  },
  methods:{
    //调用的是更新信息的接口
    doMod(){
      this.$axios.post(this.$httpUrl+'/user/update',this.form).then(res=>res.data).then(res=>{
        console.log(res)
        if(res.code==200){

          this.$message({
            message: '更新成功！',
            type: 'success'
          });
          this.centerDialogVisible = false
          this.loadPost()
          this. resetForm()
        }else{
          this.$message({
            message: '更新失败！',
            type: 'error'
          });
        }

      })
    },
    //删除信息
    del(id){
      console.log(id)
      //res=>res.data获取数据中的data层
      this.$axios.get(this.$httpUrl+'/user/del?id='+id).then(res=>res.data).then(res=>{
        console.log(res)//打印这行的数据
        if(res.code==200){

          this.$message({
            message: '删除成功！',
            type: 'success'
          });
          //更新表格
          this.loadPost()
        }else{
          this.$message({
            message: '删除失败！',
            type: 'error'
          });
        }

      })
    },
    //编辑信息
    mod(row){
      console.log(row)
      //打开表单框
      this.centerDialogVisible = true
      this.$nextTick(()=>{
        //赋值到表单
        this.form.id = row.id
        this.form.no = row.no
        this.form.name = row.name
        this.form.password = ''
        this.form.age = row.age +''
        this.form.sex = row.sex +''
        this.form.phone = row.phone
        this.form.roleId = row.roleId
      })
    },
    //重置表单
    resetForm() {
      this.$refs.form.resetFields();
    },
    doSave(){
      this.$axios.post(this.$httpUrl+'/user/save',this.form).then(res=>res.data).then(res=>{
        console.log(res)
        if(res.code==200){

          this.$message({
            message: '操作成功！',
            type: 'success'
          });
          this.centerDialogVisible = false
          this.loadPost()//新增后自动刷新表单
          this. resetForm()//新增后重置表单
        }else{
          this.$message({
            message: '操作失败！',
            type: 'error'
          });
        }

      })
    },
    //保存表单信息
    save(){
      this.$refs.form.validate((valid) => {
        if (valid) {
          //如果信息id存在就是修改
          if(this.form.id){
            this.doMod();
            //否则就是保存
          }else{
            this.doSave();
          }
        } else {
          console.log('error submit!!');
          return false;
        }
      });

    },
    add(){
      this.centerDialogVisible = true//显示表单
      //新增后下一步重置表单
      this.$nextTick(()=>{
        this.resetForm()
      })
    },
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