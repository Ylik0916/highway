<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目名称" prop="projectName">
        <el-input
          v-model="queryParams.projectName"
          placeholder="请输入项目名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目阶段" prop="projectPhase">
        <el-select v-model="queryParams.projectPhase" clearable placeholder="请选择项目阶段">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="行政区划" prop="administrativeRegion">
        <el-input
          v-model="queryParams.administrativeRegion"
          placeholder="请输入行政区域"
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
          v-hasPermi="['system:projectInformation:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:projectInformation:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:projectInformation:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:projectInformation:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="projectInformationList" @selection-change="handleSelectionChange"
              style="width: 100%">
      <el-table-column type="selection" width="55" align="center" fixed/>
      <el-table-column label="项目名称" align="center" :show-overflow-tooltip='true' prop="projectName" width="150"/>
      <el-table-column label="项目阶段" align="center" prop="projectPhase" width="150"/>
      <el-table-column label="行政区域" align="center" prop="administrativeRegion" width="150"/>
      <el-table-column label="路线信息" align="center">
        <el-table-column label="关联路线编号" align="center" prop="routeCoding" width="150"/>
        <el-table-column label="关联路线名称" align="center" :show-overflow-tooltip='true' prop="routeName" width="150"/>
      </el-table-column>
      <el-table-column label="项目标段" align="center">
        <el-table-column label="监理标" align="center" prop="" width="150"/>
        <el-table-column label="施工标" align="center" prop="" width="150"/>
      </el-table-column>
      <el-table-column label="招标费用" align="center">
        <el-table-column label="合同价（万元）" align="center" prop="contractCost" width="150">
          <template slot-scope="scope">
            <span v-if="`${scope.row.contractCost}` == 0">-</span>
            <span v-if="`${scope.row.contractCost}` != 0">{{ scope.row.contractCost }}</span>
          </template>
        </el-table-column>
        <el-table-column label="暂定金（万元）" align="center" prop="provisionalPayment" width="150">
          <template slot-scope="scope">
            <span v-if="`${scope.row.provisionalPayment}` == 0">-</span>
            <span v-if="`${scope.row.provisionalPayment}` != 0">{{ scope.row.provisionalPayment }}</span>
          </template>
        </el-table-column>
      </el-table-column>
      <el-table-column label="交竣工时间" align="center">
        <el-table-column label="开工时间" align="center" prop="startTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="交工时间" align="center" prop="submitTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.submitTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="竣工时间" align="center" prop="endTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="275" fixed="right">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="success"
            style="width: 45px"
          >详情
          </el-button>
          <el-button
            size="mini"
            type="primary"
            style="width: 45px"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:information:edit']"
          >编辑
          </el-button>
          <el-button
            size="mini"
            type="success"
            style="width: 45px"
          >标段
          </el-button>
          <el-button
            size="mini"
            type="danger"
            style="width: 45px"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:information:remove']"
          >删除
          </el-button>
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

    <!-- 添加或修改项目信息管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入项目名称"/>
        </el-form-item>
        <el-form-item label="路线信息==》路线编号" prop="routeCoding">
          <el-input v-model="form.routeCoding" placeholder="请输入路线信息==》路线编号"/>
        </el-form-item>
        <el-form-item label="路线信息==》路线名称" prop="routeName">
          <el-input v-model="form.routeName" placeholder="请输入路线信息==》路线名称"/>
        </el-form-item>
        <el-form-item label="项目阶段" prop="projectPhase">
          <el-input v-model="form.projectPhase" placeholder="请输入项目阶段"/>
        </el-form-item>
        <el-form-item label="开工时间" prop="startTime">
          <el-date-picker clearable
                          v-model="form.startTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择开工时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="交工时间" prop="submitTime">
          <el-date-picker clearable
                          v-model="form.submitTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择交工时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="竣工时间" prop="endTime">
          <el-date-picker clearable
                          v-model="form.endTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择竣工时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="投资估算==》建安费，单位：万元" prop="investCost">
          <el-input v-model="form.investCost" placeholder="请输入投资估算==》建安费，单位：万元"/>
        </el-form-item>
        <el-form-item label="投资估算==》设备费，单位：万元" prop="investEquipmentCost">
          <el-input v-model="form.investEquipmentCost" placeholder="请输入投资估算==》设备费，单位：万元"/>
        </el-form-item>
        <el-form-item label="投资估算==》工程费，单位：万元" prop="investEngineeringCost">
          <el-input v-model="form.investEngineeringCost" placeholder="请输入投资估算==》工程费，单位：万元"/>
        </el-form-item>
        <el-form-item label="设计概算==》建安费，单位：万元" prop="designCost">
          <el-input v-model="form.designCost" placeholder="请输入设计概算==》建安费，单位：万元"/>
        </el-form-item>
        <el-form-item label="设计概算==》设备费，单位：万元" prop="designEquipmentCost">
          <el-input v-model="form.designEquipmentCost" placeholder="请输入设计概算==》设备费，单位：万元"/>
        </el-form-item>
        <el-form-item label="设计概算==》工程费，单位：万元" prop="designEngineeringCost">
          <el-input v-model="form.designEngineeringCost" placeholder="请输入设计概算==》工程费，单位：万元"/>
        </el-form-item>
        <el-form-item label="施工预算==》建安费，单位：万元" prop="constructionCost">
          <el-input v-model="form.constructionCost" placeholder="请输入施工预算==》建安费，单位：万元"/>
        </el-form-item>
        <el-form-item label="施工预算==》设备费，单位：万元" prop="constructionEquipmentCost">
          <el-input v-model="form.constructionEquipmentCost" placeholder="请输入施工预算==》设备费，单位：万元"/>
        </el-form-item>
        <el-form-item label="施工预算==》工程费，单位：万元" prop="constructionEngineeringCost">
          <el-input v-model="form.constructionEngineeringCost" placeholder="请输入施工预算==》工程费，单位：万元"/>
        </el-form-item>
        <el-form-item label="招标费用==》合同价，单位：万元，不含暂定金" prop="contractCost">
          <el-input v-model="form.contractCost" placeholder="请输入招标费用==》合同价，单位：万元，不含暂定金"/>
        </el-form-item>
        <el-form-item label="招标费用==》暂定金，单位：万元" prop="provisionalPayment">
          <el-input v-model="form.provisionalPayment" placeholder="请输入招标费用==》暂定金，单位：万元"/>
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
import {
  listProjectInformation,
  getProjectInformation,
  delProjectInformation,
  addProjectInformation,
  updateProjectInformation
} from "@/api/system/projectInformation";

export default {
  name: "ProjectInformation",
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
      // 项目信息管理表格数据
      projectInformationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        projectName: null,
        administrativeRegion: null,
        routeCoding: null,
        routeName: null,
        projectPhase: null,
        startTime: null,
        submitTime: null,
        endTime: null,
        investCost: null,
        investEquipmentCost: null,
        investEngineeringCost: null,
        designCost: null,
        designEquipmentCost: null,
        designEngineeringCost: null,
        constructionCost: null,
        constructionEquipmentCost: null,
        constructionEngineeringCost: null,
        contractCost: null,
        provisionalPayment: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        projectName: [
          {required: true, message: "项目名称不能为空", trigger: "blur"}
        ],
        administrativeRegion: [
          {required: true, message: "行政区域不能为空", trigger: "change"}
        ],
        routeCoding: [
          {required: true, message: "路线信息==》路线编号不能为空", trigger: "blur"}
        ],
        routeName: [
          {required: true, message: "路线信息==》路线名称不能为空", trigger: "blur"}
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询项目信息管理列表 */
    getList() {
      this.loading = true;
      listProjectInformation(this.queryParams).then(response => {
        this.projectInformationList = response.rows;
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
        projectId: null,
        projectName: null,
        administrativeRegion: null,
        routeCoding: null,
        routeName: null,
        projectPhase: null,
        startTime: null,
        submitTime: null,
        endTime: null,
        investCost: null,
        investEquipmentCost: null,
        investEngineeringCost: null,
        designCost: null,
        designEquipmentCost: null,
        designEngineeringCost: null,
        constructionCost: null,
        constructionEquipmentCost: null,
        constructionEngineeringCost: null,
        contractCost: null,
        provisionalPayment: null
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
      this.ids = selection.map(item => item.projectId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加项目信息管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const projectId = row.projectId || this.ids
      getProjectInformation(projectId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改项目信息管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.projectId != null) {
            updateProjectInformation(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addProjectInformation(this.form).then(response => {
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
      const projectIds = row.projectId || this.ids;
      this.$modal.confirm('是否确认删除项目信息管理编号为"' + projectIds + '"的数据项？').then(function () {
        return delProjectInformation(projectIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/projectInformation/export', {
        ...this.queryParams
      }, `projectInformation_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
