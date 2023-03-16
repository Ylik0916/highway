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
      <el-table-column label="上报类型" align="center" prop="reportType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.reporting_type" :value="scope.row.reportType"/>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="statusid">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.disease_state" :value="scope.row.statusid"/>
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

  </div>
</template>

<script>
import { listDisease, getDisease, delDisease, addDisease, updateDisease } from "@/api/system/disease";

export default {
  name: "Info",
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
