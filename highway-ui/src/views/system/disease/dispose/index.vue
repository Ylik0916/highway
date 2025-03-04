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
            icon="el-icon-finished"
            @click="getDinfo(scope.row)"
            v-hasPermi="['system:disease:query']"
          >详情</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-tickets"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:disease:edit']"
          >处置</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-close"
            @click="handleClose(scope.row)"
            v-hasPermi="['system:disease:edit']"
          >关闭</el-button>
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
    <el-dialog :title="title" :visible.sync="open" width="1100px" append-to-body>
      <el-form ref="form" :rules="rules" :model="form" label-position="top" label-width="80px">
        <el-row :gutter="20">
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="管养单位" prop="maintainUnit">
                <el-input v-model="form.maintainUnit" placeholder="请输入管养单位" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="损坏情况" prop="damageCase">
                <el-input v-model="form.damageCase" placeholder="请输入损坏情况" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="行驶方向" prop="drivingDirection">
                <el-select v-model="form.drivingDirection" placeholder="请选择行驶方向" style="width: 100%">
                  <el-option
                    v-for="dict in dict.type.driving_direction"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                  ></el-option>
                </el-select>
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="预估信息" prop="forecastCase">
                <el-input v-model="form.forecastCase" placeholder="请输入预估信息" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="处置措施" prop="disposeMeasure">
                <el-input v-model="form.disposeMeasure" placeholder="请输入处置措施" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="处置中心桩号" prop="disposeStake">
                <el-input v-model="form.disposeStake" placeholder="请输入处置中心桩号" />
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-form-item label="处置备注" prop="disposeRemark">
          <el-input v-model="form.disposeRemark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="处置图片" prop="disposeImg">
          <image-upload :limit="1" v-model="form.disposeImg"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm(2)">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 关闭 -->
    <el-dialog :title="titleClose" :visible.sync="openClose" width="1100px" append-to-body>
      <el-form ref="form" :model="form" label-position="top" label-width="80px">
        <el-form-item label="撤销原因" prop="wherePath">
          <el-input v-model="form.wherePath" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm(4)">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listDisease, getDisease, delDisease, addDisease, updateDisease } from "@/api/system/disease";

export default {
  name: "Dispose",
  dicts: ['reporting_type', 'disease_state', 'driving_direction'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      diseaseNames: [],
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
      titleClose:"",
      // 是否显示弹出层
      open: false,
      openClose: false,
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
        maintainUnit: [
          { required: true, message: "管养单位不能为空", trigger: "blur" }
        ],
        damageCase: [
          { required: true, message: "损坏情况不能为空", trigger: "blur" }
        ],
        forecastCase: [
          { required: true, message: "预估信息不能为空", trigger: "blur" }
        ],
        disposeMeasure: [
          { required: true, message: "处置措施不能为空", trigger: "blur" }
        ],
        drivingDirection: [
          { required: true, message: "行驶方向不能为空", trigger: "change" }
        ],
        disposeStake: [
          { required: true, message: "处置中心桩号不能为空", trigger: "blur" }
        ],
        disposeImg: [
          { required: true, message: "处置图片不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询道路病害管理列表 */
    getList() {
      this.queryParams.statusid="待处置";
      this.loading = true;
      listDisease(this.queryParams).then(response => {
        this.diseaseList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    //跳转详情页面
    getDinfo(row){
      this.$router.push({path:'/conserve/dinfo',query: {id:row.wdid,compentList:["Dinfomatio"]}})
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.openClose = false;
      this.reset();
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
      this.diseaseNames = selection.map(item => item.diseaseName)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加道路病害管理";
    },
    /** 关闭按钮操作 */
    handleClose(row){
      this.reset();
      const wdid = row.wdid || this.ids
      getDisease(wdid).then(response => {
        this.form = response.data;
        this.openClose = true;
        this.titleClose = "病害关闭";
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const wdid = row.wdid || this.ids
      getDisease(wdid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "病害处置";
      });
    },
    /** 提交按钮 */
    submitForm(statusId) {
      this.$refs["form"].validate(valid => {
        this.form.statusid=statusId;
        if (valid) {
          if (this.form.wdid != null) {
            updateDisease(this.form).then(response => {
              this.$modal.msgSuccess("操作成功");
              this.open = false;
              this.openClose = false;
              this.getList();
            });
          } else {
            addDisease(this.form).then(response => {
              this.$modal.msgSuccess("操作成功");
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
      const diseaseNames = row.diseaseName || this.diseaseNames;
      this.$modal.confirm('是否确认删除道路病害名称为"' + diseaseNames + '"的数据项？').then(function() {
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
