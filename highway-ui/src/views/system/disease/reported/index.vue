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
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:disease:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:disease:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:disease:remove']"
        >删除</el-button>
      </el-col>
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
      <el-table-column label="编辑日期" align="center" prop="reportDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.reportDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:disease:edit']"
          >上报</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:disease:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:disease:remove']"
          >删除</el-button>
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
      <el-form ref="form" :model="form"  label-position="top" label-width="80px" ><!--:rules="rules"-->
        <el-row :gutter="20">
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="病害名称" prop="diseaseName">
                <el-input v-model="form.diseaseName" placeholder="请输入病害名称" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="行政区域" prop="pathName">
                <el-input v-model="form.pathName" placeholder="请输入路线名称" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="病害中心桩号" prop="diseaseStake">
                <el-input v-model="form.diseaseStake" placeholder="请输入病害中心桩号" />
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="病害纬度" prop="diseaseLatitude">
                <el-input v-model="form.diseaseLatitude" placeholder="请输入病害纬度" />
              </el-form-item>
              <el-form-item label="病害经度" prop="diseaseLongitude">
                <el-input v-model="form.diseaseLongitude" placeholder="请输入病害经度" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="所属路线" prop="wherePath">
                <el-input v-model="form.wherePath" placeholder="请输入所属路线" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="所属路段" prop="wherePath">
                <el-input v-model="form.wherePath" placeholder="请输入所属路线" />
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <div class="grid-content">
              <el-form-item label="病害信息" prop="diseaseMessage">
                <el-input v-model="form.diseaseMessage" type="textarea" placeholder="请输入病害信息" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="grid-content">
            </div>
          </el-col>
        </el-row>
        <el-form-item label="病害图片" prop="diseaseImg">
          <image-upload v-model="form.diseaseImg"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listDisease, getDisease, delDisease, addDisease, updateDisease } from "@/api/system/disease";

export default {
  name: "Reported",
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
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        diseaseName: [
          { required: true, message: "病害名称不能为空", trigger: "blur" }
        ],
        pathCode: [
          { required: true, message: "路线编号不能为空", trigger: "change" }
        ],
        pathName: [
          { required: true, message: "路线名称不能为空", trigger: "blur" }
        ],
        sectionCode: [
          { required: true, message: "路段编号不能为空", trigger: "blur" }
        ],
        sectionName: [
          { required: true, message: "路段名称不能为空", trigger: "blur" }
        ],
        reporter: [
          { required: true, message: "上报人不能为空", trigger: "blur" }
        ],
        reportDate: [
          { required: true, message: "上报日期不能为空", trigger: "blur" }
        ],
        reportType: [
          { required: true, message: "上报类型不能为空", trigger: "change" }
        ],
        statusid: [
          { required: true, message: "状态不能为空", trigger: "change" }
        ],
        wherePath: [
          { required: true, message: "所属路线不能为空", trigger: "blur" }
        ],
        diseaseStake: [
          { required: true, message: "病害中心桩号不能为空", trigger: "blur" }
        ],
        diseaseLatitude: [
          { required: true, message: "病害纬度不能为空", trigger: "blur" }
        ],
        diseaseLongitude: [
          { required: true, message: "病害经度不能为空", trigger: "blur" }
        ],
        diseaseMessage: [
          { required: true, message: "病害信息不能为空", trigger: "blur" }
        ],
        diseaseImg: [
          { required: true, message: "病害图片不能为空", trigger: "blur" }
        ],
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
          { required: true, message: "养护经费不能为空", trigger: "blur" }
        ],
        beginTime: [
          { required: true, message: "开始时间不能为空", trigger: "blur" }
        ],
        overTime: [
          { required: true, message: "结束时间不能为空", trigger: "blur" }
        ],
        maintainImg: [
          { required: true, message: "养护图片不能为空", trigger: "blur" }
        ],
        maintainUnit: [
          { required: true, message: "管养单位不能为空", trigger: "blur" }
        ],
        damageCase: [
          { required: true, message: "损坏情况不能为空", trigger: "blur" }
        ],
        horizontalPositions: [
          { required: true, message: "横向位置不能为空", trigger: "blur" }
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
        disposeTime: [
          { required: true, message: "处置时间不能为空", trigger: "blur" }
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
    submitForm() {
      this.$refs["form"].validate(valid => {
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
