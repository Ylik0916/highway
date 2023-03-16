<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="路线名称" prop="pathName">
        <el-input
          v-model="queryParams.pathName"
          placeholder="请输入路线名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所在乡镇" prop="whereVillage">
        <el-input
          v-model="queryParams.whereVillage"
          placeholder="请输入所在乡镇"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审核状态" prop="auditStatusid">
        <el-select v-model="queryParams.auditStatusid" placeholder="请选择审核状态" clearable>
          <el-option
            v-for="dict in dict.type.audit_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['system:delicacy:add']"
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
          v-hasPermi="['system:delicacy:edit']"
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
          v-hasPermi="['system:delicacy:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:delicacy:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="delicacyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="" align="center" prop="mpdid" />
      <el-table-column label="路线名称" align="center" prop="pathName" />
      <el-table-column label="所在乡镇" align="center" prop="whereVillage" />
      <el-table-column label="路面类型" align="center" prop="roadTypeid">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pavement_type" :value="scope.row.roadTypeid"/>
        </template>
      </el-table-column>
      <el-table-column label="技术等级" align="center" prop="technicalGradeid">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.technical_grade" :value="scope.row.technicalGradeid"/>
        </template>
      </el-table-column>
      <el-table-column label="路面宽度(m)" align="center" prop="roadWidth" />
      <el-table-column label="里程(km)" align="center" prop="mileage" />
      <el-table-column label="金额(万元)" align="center" prop="amount" />
      <el-table-column label="审核状态" align="center" prop="auditStatusid">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.audit_status" :value="scope.row.auditStatusid"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleOne(scope.row)"
            v-hasPermi="['system:delicacy:query']"
          >详情</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:delicacy:edit']"
          >审核</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:delicacy:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:delicacy:remove']"
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

    <!-- 添加或修改精品示范对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1100px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-position="top" label-width="80px">
        <el-row :gutter="20">
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="行政区" prop="administrative">
                <treeselect v-model="form.administrative" :options="ordinaryOptions" :normalizer="normalizer" placeholder="请选择行政区" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="路线名称" prop="pathName">
                <el-input v-model="form.pathName" placeholder="请输入路线名称" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="所在乡镇" prop="whereVillage">
                <el-input v-model="form.whereVillage" placeholder="请输入所在乡镇" />
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="路面类型" prop="roadTypeid">
                <el-select v-model="form.roadTypeid" placeholder="请选择路面类型" style="width: 100%">
                  <el-option
                    v-for="dict in dict.type.pavement_type"
                    :key="dict.value"
                    :label="dict.label"
                    :value="parseInt(dict.value)"
                  ></el-option>
                </el-select>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="技术等级" prop="technicalGradeid">
                <el-select v-model="form.technicalGradeid" placeholder="请选择技术等级" style="width: 100%">
                  <el-option
                    v-for="dict in dict.type.technical_grade"
                    :key="dict.value"
                    :label="dict.label"
                    :value="parseInt(dict.value)"
                  ></el-option>
                </el-select>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="路面宽度" prop="roadWidth">
                <el-input v-model="form.roadWidth" placeholder="请输入路面宽度" />
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="里程" prop="mileage">
                <el-input v-model="form.mileage" placeholder="请输入里程" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="金额" prop="amount">
                <el-input v-model="form.amount" placeholder="请输入金额" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
            </div>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 详情按钮页面 append-to-body-->
    <el-dialog :title="details" :visible.sync="detailsOpen" width="1200px" append-to-body >
      <el-row>
        <el-col :span="8"><div class="grid-content"><span>行政区:</span><span>{{form.administrative}}</span></div></el-col>
        <el-col :span="8"><div class="grid-content"><span>路线名称:</span><span>{{form.pathName}}</span></div></el-col>
        <el-col :span="8"><div class="grid-content"><span>所在乡镇·:</span><span>{{form.whereVillage}}</span></div></el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <div class="grid-content" style="display: flex">
            <span>路面类型:</span>
            <span>
              <dict-tag :options="dict.type.pavement_type" :value="form.roadTypeid"/>
            </span>
          </div>
        </el-col>
        <el-col :span="8">
          <div class="grid-content" style="display: flex">
            <span>技术等级:</span>
            <span>
                <dict-tag :options="dict.type.technical_grade" :value="form.technicalGradeid"/>
            </span>
          </div>
        </el-col>
        <el-col :span="8"><div class="grid-content"><span>路面宽度:</span><span>{{form.roadWidth}}</span></div></el-col>
      </el-row>
      <el-row>
        <el-col :span="8"><div class="grid-content"><span>里程:</span><span>{{form.mileage}}</span></div></el-col>
        <el-col :span="8"><div class="grid-content"><span>金额(万元):</span><span>{{form.amount}}</span></div></el-col>
        <el-col :span="8"><div class="grid-content"><span></span><span></span></div></el-col>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
import { listDelicacy, getDelicacy, delDelicacy, addDelicacy, updateDelicacy } from "@/api/system/delicacy";
import {listDept} from "@/api/system/dept";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Delicacy",
  dicts: ['audit_status', 'pavement_type', 'technical_grade'],
  components: {
    Treeselect
  },
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
      // 精品示范表格数据
      delicacyList: [],
      //行政下拉
      ordinaryOptions: [],
      // 弹出层标题
      title: "",
      details: "",
      // 是否显示弹出层
      open: false,
      // 详情是否显示弹出层
      detailsOpen: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        administrative: null,
        pathName: null,
        whereVillage: null,
        roadTypeid: null,
        technicalGradeid: null,
        roadWidth: null,
        mileage: null,
        amount: null,
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
    /** 查询精品示范列表 */
    getList() {
      this.loading = true;
      listDelicacy(this.queryParams).then(response => {
        this.delicacyList = response.rows;
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
        mpdid: null,
        administrative: null,
        pathName: null,
        whereVillage: null,
        roadTypeid: null,
        technicalGradeid: null,
        roadWidth: null,
        mileage: null,
        amount: null,
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
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.mpdid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    handleOne(row) {
      this.reset();
      const mpdid = row.mpdid || this.ids
      getDelicacy(mpdid).then(response => {
        this.form = response.data;
        this.detailsOpen = true;
        this.details = "精品示范详情";
      });
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.getTreeselect()
      this.open = true;
      this.title = "添加精品示范";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const mpdid = row.mpdid || this.ids
      getDelicacy(mpdid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改精品示范";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.mpdid != null) {
            updateDelicacy(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDelicacy(this.form).then(response => {
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
      const mpdids = row.mpdid || this.ids;
      this.$modal.confirm('是否确认删除精品示范编号为"' + mpdids + '"的数据项？').then(function() {
        return delDelicacy(mpdids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/delicacy/export', {
        ...this.queryParams
      }, `delicacy_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
