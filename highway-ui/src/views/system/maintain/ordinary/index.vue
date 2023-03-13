<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="路线编码" prop="pathCode">
        <el-input
          v-model="queryParams.pathCode"
          placeholder="请输入路线编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="村道名称" prop="villageName">
        <el-input
          v-model="queryParams.villageName"
          placeholder="请输入村道名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审核状态" prop="auditStatusid">
        <el-input
          v-model="queryParams.auditStatusid"
          placeholder="请输入审核状态id"
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
          v-hasPermi="['system:ordinary:add']"
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
          v-hasPermi="['system:ordinary:edit']"
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
          v-hasPermi="['system:ordinary:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:ordinary:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ordinaryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="" align="center" prop="mpoid" />
      <el-table-column label="行政区" align="center" prop="administrative" />
      <el-table-column label="路线编码" align="center" prop="pathCode" />
      <el-table-column label="村道名称" align="center" prop="villageName" />
      <el-table-column label="养护里程" align="center" prop="maintainMileage" />
      <el-table-column label="沥青路面、水泥混凝土路面、砖路面(km)" align="center" prop="pitchRoad" />
      <el-table-column label="砂砾路面" align="center" prop="gravelRoad" />
      <el-table-column label="总养护资金(万元)" align="center" prop="allFund" />
      <el-table-column label="市养护资金(万元)" align="center" prop="cityFund" />
      <el-table-column label="县养护资金(万元)" align="center" prop="countyFund" />
      <el-table-column label="审核状态id" align="center" prop="auditStatusid" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:ordinary:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:ordinary:remove']"
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

    <!-- 添加或修改一般养护对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="路线编码" prop="pathCode">
          <el-input v-model="form.pathCode" placeholder="请输入路线编码" />
        </el-form-item>
        <el-form-item label="村道名称" prop="villageName">
          <el-input v-model="form.villageName" placeholder="请输入村道名称" />
        </el-form-item>
        <el-form-item label="养护里程" prop="maintainMileage">
          <el-input v-model="form.maintainMileage" placeholder="请输入养护里程" />
        </el-form-item>
        <el-form-item label="沥青路面、水泥混凝土路面、砖路面(km)" prop="pitchRoad">
          <el-input v-model="form.pitchRoad" placeholder="请输入沥青路面、水泥混凝土路面、砖路面(km)" />
        </el-form-item>
        <el-form-item label="砂砾路面" prop="gravelRoad">
          <el-input v-model="form.gravelRoad" placeholder="请输入砂砾路面" />
        </el-form-item>
        <el-form-item label="总养护资金(万元)" prop="allFund">
          <el-input v-model="form.allFund" placeholder="请输入总养护资金(万元)" />
        </el-form-item>
        <el-form-item label="市养护资金(万元)" prop="cityFund">
          <el-input v-model="form.cityFund" placeholder="请输入市养护资金(万元)" />
        </el-form-item>
        <el-form-item label="县养护资金(万元)" prop="countyFund">
          <el-input v-model="form.countyFund" placeholder="请输入县养护资金(万元)" />
        </el-form-item>
        <el-form-item label="审核状态id" prop="auditStatusid">
          <el-input v-model="form.auditStatusid" placeholder="请输入审核状态id" />
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
import { listOrdinary, getOrdinary, delOrdinary, addOrdinary, updateOrdinary } from "@/api/system/ordinary";

export default {
  name: "Ordinary",
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
      // 一般养护表格数据
      ordinaryList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        administrative: null,
        pathCode: null,
        villageName: null,
        maintainMileage: null,
        pitchRoad: null,
        gravelRoad: null,
        allFund: null,
        cityFund: null,
        countyFund: null,
        auditStatusid: null
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
    /** 查询一般养护列表 */
    getList() {
      this.loading = true;
      listOrdinary(this.queryParams).then(response => {
        this.ordinaryList = response.rows;
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
        mpoid: null,
        administrative: null,
        pathCode: null,
        villageName: null,
        maintainMileage: null,
        pitchRoad: null,
        gravelRoad: null,
        allFund: null,
        cityFund: null,
        countyFund: null,
        auditStatusid: null
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
      this.ids = selection.map(item => item.mpoid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加一般养护";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const mpoid = row.mpoid || this.ids
      getOrdinary(mpoid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改一般养护";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.mpoid != null) {
            updateOrdinary(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOrdinary(this.form).then(response => {
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
      const mpoids = row.mpoid || this.ids;
      this.$modal.confirm('是否确认删除一般养护编号为"' + mpoids + '"的数据项？').then(function() {
        return delOrdinary(mpoids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/ordinary/export', {
        ...this.queryParams
      }, `ordinary_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
