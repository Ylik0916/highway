<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="病害名称" prop="diseaseName">
        <el-input
          v-model="queryParams.diseaseName"
          placeholder="请输入病害名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="路线名称" prop="pathName">
        <el-input
          v-model="queryParams.pathName"
          placeholder="请输入路线名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="路段编号" prop="sectionCode">
        <el-input
          v-model="queryParams.sectionCode"
          placeholder="请输入路段编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:disease:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="diseaseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="" align="center" prop="wdid" />-->
      <el-table-column label="病害名称" align="center" prop="diseaseName" />
      <el-table-column label="路线编号" align="center" prop="pathCode" />
      <el-table-column label="路线名称" align="center" prop="pathName" />
      <el-table-column label="路段编号" align="center" prop="sectionCode" />
      <el-table-column label="路段名称" align="center" prop="sectionName" />
      <el-table-column label="上报人" align="center" prop="reporter" />
      <el-table-column label="上报日期" align="center" prop="reportDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.reportDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-fork-spoon"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:disease:edit']"
          >养护</el-button>
          <el-button
            type="text"
            icon="el-icon-finished"
            size="mini"
            @click="getDinfo(scope.row)"
            v-hasPermi="['system:disease:add']"
          >详情</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改道路病害管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="750px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-position="top" label-width="80px">
        <el-row :gutter="20">
          <el-col :span="12">
            <div class="grid-content">
              <el-form-item label="病害名称" prop="diseaseName">
                <el-input v-model="form.diseaseName" :disabled="true" placeholder="请输入病害名称" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="grid-content">
              <el-form-item label="养护地点" prop="maintainSite">
                <el-input v-model="form.maintainSite" placeholder="请输入养护地点" />
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <div class="grid-content">
              <el-form-item label="养护情况" prop="maintainCase">
                <el-input v-model="form.maintainCase" placeholder="请输入养护情况" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="grid-content">
              <el-form-item label="养护时间" prop="beginTime">
                <el-date-picker
                  style="width: 100%"
                  v-model="form.beginTime"
                  type="daterange"
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期">
                </el-date-picker>
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <div class="grid-content">
              <el-form-item label="养护人" prop="maintainPeople">
                <el-input v-model="form.maintainPeople" placeholder="请输入养护人" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="grid-content">
              <el-form-item label="养护费用(万元)" prop="maintainFund">
                <el-input v-model.number="form.maintainFund" placeholder="请输入养护费用" />
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-form-item label="养护备注" prop="maintainRemark">
          <el-input v-model="form.maintainRemark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="养护图片" prop="maintainImg">
          <image-upload v-model="form.maintainImg"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm(3)">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listDisease, getDisease, delDisease, addDisease, updateDisease } from "@/api/system/disease";

export default {
  name: "Conserve",
  dicts: ['reporting_type', 'disease_state', 'driving_direction'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 道路病害管理表格数据
      diseaseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        diseaseName: null,
        pathName: null,
        sectionCode: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        maintainSite: [
          { required: true, message: "养护地点不能为空", trigger: "blur" }
        ],
        maintainCase: [
          { required: true, message: "养护情况不能为空", trigger: "blur" }
        ],
        maintainPeople: [
          { required: true, message: "养护人不能为空", trigger: "blur" }
        ],
        maintainFund: [
          { required: true, message: "养护经费不能为空", trigger: "blur" },
          { type: 'number', message: '养护经费必须为数字值', trigger: "blur"}
        ],
        beginTime: [
          { required: true, message: "开始时间不能为空", trigger: "blur" }
        ],
        maintainImg: [
          { required: true, message: "养护图片不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询道路病害管理列表 */
    getList() {
      this.queryParams.statusid=2;
      this.loading = true;
      listDisease(this.queryParams).then(response => {
        this.diseaseList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    //跳转详情页面
    getDinfo(row){
      this.$router.push({path:'/conserve/dinfo',query: {id:row.wdid,compentList:["Dinfomatio","Ddispose"]}})
    },
    // 表单重置
    reset() {
      this.form = {
        wdid: null,
        diseaseName: null,
        pathCode: null,
        pathName: null,
        sectionCode: null,
        sectionName: null,
        reporter: null,
        reportDate: null,
        reportType: null,
        statusid: null,
        wherePath: null,
        diseaseStake: null,
        diseaseLatitude: null,
        diseaseLongitude: null,
        diseaseMessage: null,
        diseaseImg: null,
        maintainSite: null,
        maintainCase: null,
        maintainPeople: null,
        maintainFund: null,
        beginTime: null,
        overTime: null,
        maintainRemark: null,
        maintainImg: null,
        maintainUnit: null,
        damageCase: null,
        horizontalPositions: null,
        forecastCase: null,
        disposeMeasure: null,
        drivingDirection: null,
        disposeStake: null,
        disposeRemark: null,
        disposeTime: null,
        disposeImg: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.wdid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加道路病害管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const wdid = row.wdid || this.ids
      getDisease(wdid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改道路病害管理";
      });
    },
    /** 提交按钮 */
    submitForm(statusId) {
      this.$refs["form"].validate(valid => {
        this.form.statusid=statusId;
        let time = this.form.beginTime;
        this.form.beginTime = time[0];
        this.form.overTime = time[1];
        if (valid) {
          if (this.form.wdid != null) {
            updateDisease(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDisease(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const wdids = row.wdid || this.ids;
      this.$modal.confirm('是否确认删除道路病害管理编号为"' + wdids + '"的数据项？').then(function() {
        return delDisease(wdids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/disease/export', {
        ...this.queryParams
      }, `disease_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
