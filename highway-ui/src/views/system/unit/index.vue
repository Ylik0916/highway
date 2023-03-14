<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="登录名" prop="loginName">
        <el-input
          v-model="queryParams.loginName"
          placeholder="请输入登录名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="单位地址" prop="unitSite">
        <el-input
          v-model="queryParams.unitSite"
          placeholder="请输入单位地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="单位电话" prop="uniyPhone">
        <el-input
          v-model="queryParams.uniyPhone"
          placeholder="请输入单位电话"
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
          v-hasPermi="['system:unit:add']"
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
          v-hasPermi="['system:unit:edit']"
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
          v-hasPermi="['system:unit:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:unit:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    <el-table v-loading="loading" :data="unitList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="" align="center" prop="muid" />
      <el-table-column label="单位编码" :show-overflow-tooltip="true" align="center" prop="unitCode" />
      <el-table-column label="单位全称" :show-overflow-tooltip="true" align="center" prop="unitDesignation" />
      <el-table-column label="单位名称" :show-overflow-tooltip="true" align="center" prop="unitName" />
      <el-table-column label="登录名" :show-overflow-tooltip="true" align="center" prop="loginName" />
      <el-table-column label="单位地址" :show-overflow-tooltip="true" align="center" prop="unitSite" />
      <el-table-column label="单位电话" :show-overflow-tooltip="true" align="center" prop="uniyPhone" />
      <el-table-column label="备注"  align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleOne(scope.row)"
          >详情</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:unit:edit']"
          >编辑</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:unit:remove']"
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

    <!-- 添加或修改养护单位管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1100px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-row>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="单位编码" prop="unitCode">
                <el-input v-model="form.unitCode" placeholder="请输入单位编码" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="单位全称" prop="unitDesignation">
                <el-input v-model="form.unitDesignation" placeholder="请输入单位全称" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="单位名称" prop="unitName">
                <el-input v-model="form.unitName" placeholder="请输入单位名称" />
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="养护用户登录名" prop="loginName">
                <el-input v-model="form.loginName" placeholder="请输入登录名" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="单位地址" prop="unitSite">
                <el-input v-model="form.unitSite" placeholder="请输入单位地址" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="单位电话" prop="uniyPhone">
                <el-input v-model="form.uniyPhone" placeholder="请输入单位电话" />
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="备注" prop="remark">
                <el-input v-model="form.remark" placeholder="请输入备注" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8"><div class="grid-content"></div></el-col>
          <el-col :span="8"><div class="grid-content"></div></el-col>
        </el-row>
        <el-form-item label="行政区划权限">
          <treeselect :show-count="true" :multiple="true" v-model="form.administrative" :options="options" :normalizer="normalizer" placeholder="请选择行政区" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 详情按钮页面 append-to-body-->
    <el-dialog :title="details" :visible.sync="detailsOpen" width="1200px" append-to-body >
      <el-row>
        <el-col :span="8"><div class="grid-content"><span>单位编码:</span><span>{{form.unitCode}}</span></div></el-col>
        <el-col :span="8"><div class="grid-content"><span>单位全称:</span><span>{{form.unitDesignation}}</span></div></el-col>
        <el-col :span="8"><div class="grid-content"><span>单位名称:</span><span>{{form.unitName}}</span></div></el-col>
      </el-row>
      <el-row>
        <el-col :span="8"><div class="grid-content"><span>养护用户登录名:</span><span>{{form.loginName}}</span></div></el-col>
        <el-col :span="8"><div class="grid-content"><span>单位地址:</span><span>{{form.unitSite}}</span></div></el-col>
        <el-col :span="8"><div class="grid-content"><span>单位电话:</span><span>{{form.uniyPhone}}</span></div></el-col>
      </el-row>
      <el-row>
        <el-col :span="24"><div class="grid-content"><span>备注:</span><span>{{form.remark}}</span></div></el-col>
      </el-row>
      <el-row>
        <el-col :span="24"><div class="grid-content"><span>行政区划权限:</span><span>
          <el-tree
            :data="options"
            show-checkbox
            node-key="deptId"
            :props="defaultProps"
            default-expand-all
            :highlight-current = "true"
            :default-checked-keys="form.administrative">
          </el-tree>
        </span></div></el-col>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
import { listUnit, getUnit, delUnit, addUnit, updateUnit } from "@/api/system/unit";
import { listDept } from "@/api/system/dept";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Unit",
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
      // 养护单位管理表格数据
      unitList: [],
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
        unitCode: null,
        unitDesignation: null,
        unitName: null,
        loginName: null,
        unitSite: null,
        uniyPhone: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      options: [],
      //详情默认配置
      defaultProps: {
        label: 'deptName',
        disabled: 'disableStatus'
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询养护单位管理列表 */
    getList() {
      this.loading = true;
      listUnit(this.queryParams).then(response => {
        this.unitList = response.rows;
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
        muid: null,
        unitCode: null,
        unitDesignation: null,
        unitName: null,
        loginName: null,
        unitSite: null,
        uniyPhone: null,
        remark: null,
        administrative: null
      };
      this.resetForm("form");
    },
    /** 详情按钮禁用 */
    disabledDetails(list) {
      let res = [];
      list.forEach((i)=>{
          i['disableStatus'] = true;
          res.push(i);
      });
      return res;
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
        // const data = { deptId: 0, deptName: '大陆', children: [] };
        this.options = this.handleTree(this.disabledDetails(response.data), "deptId", "parentId");
      });
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
      this.ids = selection.map(item => item.muid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.getTreeselect();
      this.open = true;
      this.title = "添加养护单位管理";
    },
    /** 详情按钮操作 */
    handleOne(row) {
      this.reset();
      this.getTreeselect();
      const muid = row.muid || this.ids
      getUnit(muid).then(response => {
        this.form = response.data;
        this.detailsOpen = true;
        this.details = "养护单位详情";
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const muid = row.muid || this.ids
      getUnit(muid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改养护单位管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.muid != null) {
            updateUnit(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            console.log(this.form)
            addUnit(this.form).then(response => {
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
      const muids = row.muid || this.ids;
      this.$modal.confirm('是否确认删除养护单位管理编号为"' + muids + '"的数据项？').then(function() {
        return delUnit(muids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/unit/export', {
        ...this.queryParams
      }, `unit_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
<style>
.el-row {
  margin-bottom: 10px;
}
.el-col {
  border-radius: 4px;
}
.grid-content span:nth-child(1){
  margin-right: 0.5em;
}
.grid-content {
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
}
</style>
