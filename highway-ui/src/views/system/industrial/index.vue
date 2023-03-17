<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="85px">
      <el-form-item label="产业园名称" prop="nameOfIndustrialPark">
        <el-input
          v-model="queryParams.nameOfIndustrialPark"
          placeholder="请输入产业园名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产业园经度" prop="longitudeOfIndustrialPark">
        <el-input
          v-model="queryParams.longitudeOfIndustrialPark"
          placeholder="请输入产业园经度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产业园纬度" prop="latitudeOfIndustrialPark">
        <el-input
          v-model="queryParams.latitudeOfIndustrialPark"
          placeholder="请输入产业园纬度"
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
          v-hasPermi="['system:industrial:add']"
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
          v-hasPermi="['system:industrial:edit']"
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
          v-hasPermi="['system:industrial:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:industrial:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="industrialList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="产业园ID" align="center" prop="industrialParkId" />
      <el-table-column label="产业园名称" align="center" prop="nameOfIndustrialPark" />
      <el-table-column label="产业园经度" align="center" prop="longitudeOfIndustrialPark" />
      <el-table-column label="产业园纬度" align="center" prop="latitudeOfIndustrialPark" />
      <el-table-column label="优先通达路线名称" align="center" prop="nameOfPriorityRoute" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:industrial:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:industrial:remove']"
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

    <!-- 添加或修改现代产业园对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" style="display: flex;flex-wrap: wrap;justify-content: space-between">
        <el-form-item label="产业园名称" prop="nameOfIndustrialPark">
          <el-input v-model="form.nameOfIndustrialPark" placeholder="请输入产业园名称" />
        </el-form-item>
        <el-form-item label="行政区域" prop="administrativeRegion">
          <el-input v-model="form.administrativeRegion" placeholder="请输入行政区域" />
        </el-form-item>
        <el-form-item label="产业园经度" prop="longitudeOfIndustrialPark">
          <el-input v-model="form.longitudeOfIndustrialPark" placeholder="请输入产业园经度" />
        </el-form-item>
        <el-form-item label="产业园纬度" prop="latitudeOfIndustrialPark">
          <el-input v-model="form.latitudeOfIndustrialPark" placeholder="请输入产业园纬度" />
        </el-form-item>
        <el-form-item label="优先通达路线名称" prop="nameOfPriorityRoute">
          <el-input v-model="form.nameOfPriorityRoute" placeholder="请输入优先通达路线名称" />
        </el-form-item>
        <el-form-item label="优先通达路线编码" prop="priorityAccessRouteCode">
          <el-input v-model="form.priorityAccessRouteCode" placeholder="请输入优先通达路线编码" />
        </el-form-item>
        <el-form-item label="介绍" prop="introduce">
          <el-input v-model="form.introduce" type="textarea" placeholder="请输入内容" />
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
import { listIndustrial, getIndustrial, delIndustrial, addIndustrial, updateIndustrial } from "@/api/system/industrial";

export default {
  name: "Industrial",
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
      // 现代产业园表格数据
      industrialList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        nameOfIndustrialPark: null,
        administrativeRegion: null,
        longitudeOfIndustrialPark: null,
        latitudeOfIndustrialPark: null,
        priorityAccessRouteCode: null,
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
    /** 查询现代产业园列表 */
    getList() {
      this.loading = true;
      listIndustrial(this.queryParams).then(response => {
        this.industrialList = response.rows;
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
        industrialParkId: null,
        nameOfIndustrialPark: null,
        administrativeRegion: null,
        longitudeOfIndustrialPark: null,
        latitudeOfIndustrialPark: null,
        nameOfPriorityRoute: null,
        priorityAccessRouteCode: null,
        introduce: null
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
      this.ids = selection.map(item => item.industrialParkId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加现代产业园";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const industrialParkId = row.industrialParkId || this.ids
      getIndustrial(industrialParkId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改现代产业园";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.industrialParkId != null) {
            updateIndustrial(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addIndustrial(this.form).then(response => {
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
      const industrialParkIds = row.industrialParkId || this.ids;
      this.$modal.confirm('是否确认删除现代产业园编号为"' + industrialParkIds + '"的数据项？').then(function() {
        return delIndustrial(industrialParkIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/industrial/export', {
        ...this.queryParams
      }, `industrial_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
