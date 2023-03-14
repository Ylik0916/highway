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
      <el-form-item label="路线编号" prop="pathCode">
        <el-input
          v-model="queryParams.pathCode"
          placeholder="请输入路线编号"
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
      <el-form-item label="路段名称" prop="sectionName">
        <el-input
          v-model="queryParams.sectionName"
          placeholder="请输入路段名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上报人" prop="reporter">
        <el-input
          v-model="queryParams.reporter"
          placeholder="请输入上报人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上报日期" prop="reportDate">
        <el-date-picker clearable
          v-model="queryParams.reportDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择上报日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="状态" prop="statusid">
        <el-input
          v-model="queryParams.statusid"
          placeholder="请输入状态"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属路线" prop="wherePath">
        <el-input
          v-model="queryParams.wherePath"
          placeholder="请输入所属路线"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="病害中心桩号" prop="diseaseStake">
        <el-input
          v-model="queryParams.diseaseStake"
          placeholder="请输入病害中心桩号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="病害纬度" prop="diseaseLatitude">
        <el-input
          v-model="queryParams.diseaseLatitude"
          placeholder="请输入病害纬度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="病害经度" prop="diseaseLongitude">
        <el-input
          v-model="queryParams.diseaseLongitude"
          placeholder="请输入病害经度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="病害信息" prop="diseaseMessage">
        <el-input
          v-model="queryParams.diseaseMessage"
          placeholder="请输入病害信息"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="养护地点" prop="maintainSite">
        <el-input
          v-model="queryParams.maintainSite"
          placeholder="请输入养护地点"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="养护情况" prop="maintainCase">
        <el-input
          v-model="queryParams.maintainCase"
          placeholder="请输入养护情况"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="养护人" prop="maintainPeople">
        <el-input
          v-model="queryParams.maintainPeople"
          placeholder="请输入养护人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="养护经费" prop="maintainFund">
        <el-input
          v-model="queryParams.maintainFund"
          placeholder="请输入养护经费"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开始时间" prop="beginTime">
        <el-date-picker clearable
          v-model="queryParams.beginTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="overTime">
        <el-date-picker clearable
          v-model="queryParams.overTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择结束时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="养护备注" prop="maintainRemark">
        <el-input
          v-model="queryParams.maintainRemark"
          placeholder="请输入养护备注"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="管养单位" prop="maintainUnit">
        <el-input
          v-model="queryParams.maintainUnit"
          placeholder="请输入管养单位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="损坏情况" prop="damageCase">
        <el-input
          v-model="queryParams.damageCase"
          placeholder="请输入损坏情况"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="横向位置" prop="horizontalPositions">
        <el-input
          v-model="queryParams.horizontalPositions"
          placeholder="请输入横向位置"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预估信息" prop="forecastCase">
        <el-input
          v-model="queryParams.forecastCase"
          placeholder="请输入预估信息"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="处置措施" prop="disposeMeasure">
        <el-input
          v-model="queryParams.disposeMeasure"
          placeholder="请输入处置措施"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="行驶方向" prop="drivingDirection">
        <el-input
          v-model="queryParams.drivingDirection"
          placeholder="请输入行驶方向"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="处置中心桩号" prop="disposeStake">
        <el-input
          v-model="queryParams.disposeStake"
          placeholder="请输入处置中心桩号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="处置备注" prop="disposeRemark">
        <el-input
          v-model="queryParams.disposeRemark"
          placeholder="请输入处置备注"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="处置时间" prop="disposeTime">
        <el-date-picker clearable
          v-model="queryParams.disposeTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择处置时间">
        </el-date-picker>
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
      <el-table-column label="" align="center" prop="wdid" />
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
      <el-table-column label="上报类型" align="center" prop="reportType" />
      <el-table-column label="状态" align="center" prop="statusid" />
      <el-table-column label="所属路线" align="center" prop="wherePath" />
      <el-table-column label="病害中心桩号" align="center" prop="diseaseStake" />
      <el-table-column label="病害纬度" align="center" prop="diseaseLatitude" />
      <el-table-column label="病害经度" align="center" prop="diseaseLongitude" />
      <el-table-column label="病害信息" align="center" prop="diseaseMessage" />
      <el-table-column label="病害图片" align="center" prop="diseaseImg" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.diseaseImg" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="养护地点" align="center" prop="maintainSite" />
      <el-table-column label="养护情况" align="center" prop="maintainCase" />
      <el-table-column label="养护人" align="center" prop="maintainPeople" />
      <el-table-column label="养护经费" align="center" prop="maintainFund" />
      <el-table-column label="开始时间" align="center" prop="beginTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.beginTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="overTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.overTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="养护备注" align="center" prop="maintainRemark" />
      <el-table-column label="养护图片" align="center" prop="maintainImg" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.maintainImg" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="管养单位" align="center" prop="maintainUnit" />
      <el-table-column label="损坏情况" align="center" prop="damageCase" />
      <el-table-column label="横向位置" align="center" prop="horizontalPositions" />
      <el-table-column label="预估信息" align="center" prop="forecastCase" />
      <el-table-column label="处置措施" align="center" prop="disposeMeasure" />
      <el-table-column label="行驶方向" align="center" prop="drivingDirection" />
      <el-table-column label="处置中心桩号" align="center" prop="disposeStake" />
      <el-table-column label="处置备注" align="center" prop="disposeRemark" />
      <el-table-column label="处置时间" align="center" prop="disposeTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.disposeTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="处置图片" align="center" prop="disposeImg" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.disposeImg" :width="50" :height="50"/>
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
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="病害名称" prop="diseaseName">
          <el-input v-model="form.diseaseName" placeholder="请输入病害名称" />
        </el-form-item>
        <el-form-item label="路线编号" prop="pathCode">
          <el-input v-model="form.pathCode" placeholder="请输入路线编号" />
        </el-form-item>
        <el-form-item label="路线名称" prop="pathName">
          <el-input v-model="form.pathName" placeholder="请输入路线名称" />
        </el-form-item>
        <el-form-item label="路段编号" prop="sectionCode">
          <el-input v-model="form.sectionCode" placeholder="请输入路段编号" />
        </el-form-item>
        <el-form-item label="路段名称" prop="sectionName">
          <el-input v-model="form.sectionName" placeholder="请输入路段名称" />
        </el-form-item>
        <el-form-item label="上报人" prop="reporter">
          <el-input v-model="form.reporter" placeholder="请输入上报人" />
        </el-form-item>
        <el-form-item label="上报日期" prop="reportDate">
          <el-date-picker clearable
            v-model="form.reportDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择上报日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="状态" prop="statusid">
          <el-input v-model="form.statusid" placeholder="请输入状态" />
        </el-form-item>
        <el-form-item label="所属路线" prop="wherePath">
          <el-input v-model="form.wherePath" placeholder="请输入所属路线" />
        </el-form-item>
        <el-form-item label="病害中心桩号" prop="diseaseStake">
          <el-input v-model="form.diseaseStake" placeholder="请输入病害中心桩号" />
        </el-form-item>
        <el-form-item label="病害纬度" prop="diseaseLatitude">
          <el-input v-model="form.diseaseLatitude" placeholder="请输入病害纬度" />
        </el-form-item>
        <el-form-item label="病害经度" prop="diseaseLongitude">
          <el-input v-model="form.diseaseLongitude" placeholder="请输入病害经度" />
        </el-form-item>
        <el-form-item label="病害信息" prop="diseaseMessage">
          <el-input v-model="form.diseaseMessage" placeholder="请输入病害信息" />
        </el-form-item>
        <el-form-item label="病害图片" prop="diseaseImg">
          <image-upload v-model="form.diseaseImg"/>
        </el-form-item>
        <el-form-item label="养护地点" prop="maintainSite">
          <el-input v-model="form.maintainSite" placeholder="请输入养护地点" />
        </el-form-item>
        <el-form-item label="养护情况" prop="maintainCase">
          <el-input v-model="form.maintainCase" placeholder="请输入养护情况" />
        </el-form-item>
        <el-form-item label="养护人" prop="maintainPeople">
          <el-input v-model="form.maintainPeople" placeholder="请输入养护人" />
        </el-form-item>
        <el-form-item label="养护经费" prop="maintainFund">
          <el-input v-model="form.maintainFund" placeholder="请输入养护经费" />
        </el-form-item>
        <el-form-item label="开始时间" prop="beginTime">
          <el-date-picker clearable
            v-model="form.beginTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="overTime">
          <el-date-picker clearable
            v-model="form.overTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="养护备注" prop="maintainRemark">
          <el-input v-model="form.maintainRemark" placeholder="请输入养护备注" />
        </el-form-item>
        <el-form-item label="养护图片" prop="maintainImg">
          <image-upload v-model="form.maintainImg"/>
        </el-form-item>
        <el-form-item label="管养单位" prop="maintainUnit">
          <el-input v-model="form.maintainUnit" placeholder="请输入管养单位" />
        </el-form-item>
        <el-form-item label="损坏情况" prop="damageCase">
          <el-input v-model="form.damageCase" placeholder="请输入损坏情况" />
        </el-form-item>
        <el-form-item label="横向位置" prop="horizontalPositions">
          <el-input v-model="form.horizontalPositions" placeholder="请输入横向位置" />
        </el-form-item>
        <el-form-item label="预估信息" prop="forecastCase">
          <el-input v-model="form.forecastCase" placeholder="请输入预估信息" />
        </el-form-item>
        <el-form-item label="处置措施" prop="disposeMeasure">
          <el-input v-model="form.disposeMeasure" placeholder="请输入处置措施" />
        </el-form-item>
        <el-form-item label="行驶方向" prop="drivingDirection">
          <el-input v-model="form.drivingDirection" placeholder="请输入行驶方向" />
        </el-form-item>
        <el-form-item label="处置中心桩号" prop="disposeStake">
          <el-input v-model="form.disposeStake" placeholder="请输入处置中心桩号" />
        </el-form-item>
        <el-form-item label="处置备注" prop="disposeRemark">
          <el-input v-model="form.disposeRemark" placeholder="请输入处置备注" />
        </el-form-item>
        <el-form-item label="处置时间" prop="disposeTime">
          <el-date-picker clearable
            v-model="form.disposeTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择处置时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="处置图片" prop="disposeImg">
          <image-upload v-model="form.disposeImg"/>
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
  name: "Disease",
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
