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
          placeholder="请输入审核状态"
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
          type="info"
          plain
          icon="el-icon-sort"
          size="mini"
          @click="toggleExpandAll"
        >展开/折叠</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-if="refreshTable"
      v-loading="loading"
      :data="ordinaryList"
      :default-expand-all="isExpandAll"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
<!--      row-key="administrative"-->
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
            icon="el-icon-plus"
            @click="handleAdd(scope.row)"
            v-hasPermi="['system:ordinary:add']"
          >新增</el-button>
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

    <!-- 添加或修改一般养护对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="行政区" prop="administrative">
          <treeselect v-model="form.administrative" :options="ordinaryOptions" :normalizer="normalizer" placeholder="请选择行政区" />
        </el-form-item>
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
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import { listDept } from "@/api/system/dept";

export default {
  name: "Ordinary",
  components: {
    Treeselect
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 一般养护表格数据
      ordinaryList: [],
      // 一般养护树选项
      ordinaryOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否展开，默认全部展开
      isExpandAll: true,
      // 重新渲染表格状态
      refreshTable: true,
      // 查询参数
      queryParams: {
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
        // this.ordinaryList = this.handleTree(response.data, "deptId", "parentId");
        this.ordinaryList = response.data;
        this.loading = false;
      });
    },
    /** 转换一般养护数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.deptId,
        label: node.deptName,
        children: node.children
      };
    },
	/** 查询一般养护下拉树结构 */
    getTreeselect() {
      listDept().then(response => {
        this.ordinaryOptions = [];
        // const data = { deptId: 0, deptName: '大陆', children: [] };
        this.ordinaryOptions = this.handleTree(response.data, "deptId", "parentId");
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
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      this.getTreeselect();
      // if (row != null && row.administrative) {
      //   this.form.administrative = row.administrative;
      // } else {
      //   this.form.administrative = 0;
      // }
      this.open = true;
      this.title = "添加一般养护";
    },
    /** 展开/折叠操作 */
    toggleExpandAll() {
      this.refreshTable = false;
      this.isExpandAll = !this.isExpandAll;
      this.$nextTick(() => {
        this.refreshTable = true;
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      if (row != null) {
        this.form.administrative = row.administrative;
      }
      getOrdinary(row.mpoid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改一般养护";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        console.log(this.form)
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
      this.$modal.confirm('是否确认删除一般养护编号为"' + row.mpoid + '"的数据项？').then(function() {
        return delOrdinary(row.mpoid);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    }
  }
};
</script>
