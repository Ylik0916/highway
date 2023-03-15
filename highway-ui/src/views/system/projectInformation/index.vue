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
      <el-table-column label="项目阶段" align="center" prop="projectPhase" width="150">
        <template slot-scope="scope">
          <span v-if="`${scope.row.projectPhase}` == 1">立项阶段</span>
          <span v-if="`${scope.row.projectPhase}` == 2">工可阶段</span>
          <span v-if="`${scope.row.projectPhase}` == 3">勘察设计招标阶段</span>
          <span v-if="`${scope.row.projectPhase}` == 4">勘察设计阶段</span>
          <span v-if="`${scope.row.projectPhase}` == 5">施工招标阶段</span>
          <span v-if="`${scope.row.projectPhase}` == 6">开工准备</span>
          <span v-if="`${scope.row.projectPhase}` == 7">施工阶段</span>
          <span v-if="`${scope.row.projectPhase}` == 8">试运营阶段</span>
        </template>
      </el-table-column>
      <el-table-column label="行政区域" align="center" prop="administrativeRegion" width="150"/>
      <el-table-column label="路线信息" align="center">
        <el-table-column label="关联路线编号" align="center" prop="routeCoding" width="150"/>
        <el-table-column label="关联路线名称" align="center" :show-overflow-tooltip='true' prop="routeName" width="150"/>
      </el-table-column>
      <el-table-column label="项目标段" align="center">
        <el-table-column label="监理标" align="center" prop="hwProjectSupervisor.supervisorName" width="150"/>
        <el-table-column label="施工标" align="center" prop="hwProjectConstruction.constructionName" width="150"/>
      </el-table-column>
      <el-table-column label="招标费用" align="center">
        <el-table-column label="合同价（万元）" align="center" prop="contractCost" width="150"/>
        <el-table-column label="暂定金（万元）" align="center" prop="provisionalPayment" width="150"/>
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
            @click="handleDetails(scope.row)"
            v-hasPermi="['system:projectInformation:query']"
          >详情
          </el-button>
          <el-button
            size="mini"
            type="primary"
            style="width: 45px"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:projectInformation:edit']"
          >编辑
          </el-button>
          <el-button
            size="mini"
            type="success"
            style="width: 45px"
            @click="handleBdQuery(scope.row)"
            v-hasPermi="['system:projectInformation:edit']"
          >标段
          </el-button>
          <el-button
            size="mini"
            type="danger"
            style="width: 45px"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:projectInformation:remove']"
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
    <el-dialog :title="title" :visible.sync="open" width="1200px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目阶段" prop="projectPhase">
          <el-steps :active="active" finish-status="success" align-center>
            <el-step title="立项阶段" @click.native="next(1)"></el-step>
            <el-step title="工可阶段" @click.native="next(2)"></el-step>
            <el-step title="勘察设计招标阶段" @click.native="next(3)"></el-step>
            <el-step title="勘察设计阶段" @click.native="next(4)"></el-step>
            <el-step title="施工招标阶段" @click.native="next(5)"></el-step>
            <el-step title="开工准备" @click.native="next(6)"></el-step>
            <el-step title="施工阶段" @click.native="next(7)"></el-step>
            <el-step title="试运营阶段" @click.native="next(8)"></el-step>
          </el-steps>
        </el-form-item>

        <div class="projectMainInformationTitle">
          <span style="line-height: 30px;margin-left: 10px;font-weight: bold">项目基本信息</span>
          <div class="projectMainInformation">
            <el-form-item label="项目名称" prop="projectName" style="width: 400px;height:100px;padding-top: 50px">
              <el-input v-model="form.projectName" placeholder="请输入项目名称" :disabled="disabled"/>
            </el-form-item>
            <el-form-item label="行政区划" prop="administrativeRegion"
                          style="width: 400px;height:100px;padding-top: 50px;margin-left: 50px">
              <el-input v-model="form.administrativeRegion" placeholder="请输入行政区划名称" :disabled="disabled"/>
            </el-form-item>
            <el-form-item label="路线名称" prop="routeName" style="width: 400px;height:100px;padding-top: 30px">
              <el-input v-model="form.routeName" placeholder="请输入路线名称" :disabled="disabled"/>
            </el-form-item>
            <el-form-item label="路线编号" prop="routeCoding"
                          style="width: 400px;height:100px;padding-top: 30px;margin-left: 50px">
              <el-input v-model="form.routeCoding" placeholder="请输入路线编号" :disabled="disabled"/>
            </el-form-item>
          </div>
        </div>

        <div class="projectDetailsInformationTitle">
          <span style="line-height: 30px;margin-left: 10px;font-weight: bold">项目详情</span>
          <div class="projectDetailsInformation">

            <div class="tzCostTitle" style="border-bottom: 1px solid #f0f0f0">
              <span style="line-height: 30px;margin-left: 10px;font-weight: bold">投资估算（万元）</span>
              <div class="cost">
                <el-form-item label="建安费" prop="investCost" class="detailsInformation">
                  <el-input v-model="form.investCost" placeholder="请输入建安费" :disabled="disabled"/>
                </el-form-item>
                <el-form-item label="设备及工器购置费" prop="investEquipmentCost" class="detailsInformation"
                              style="line-height: 80px;align-items: center">
                  <el-input v-model="form.investEquipmentCost" placeholder="请输入设备及工器购置费" :disabled="disabled"/>
                </el-form-item>
                <el-form-item label="工程费" prop="investEngineeringCost" class="detailsInformation">
                  <el-input v-model="form.investEngineeringCost" placeholder="请输入工程费" :disabled="disabled"/>
                </el-form-item>
              </div>
            </div>

            <div class="sjCostTitle" style="border-bottom: 1px solid #f0f0f0">
              <span style="line-height: 30px;margin-left: 10px;font-weight: bold">设计概算（万元）</span>
              <div class="cost">
                <el-form-item label="建安费" prop="designCost" class="detailsInformation">
                  <el-input v-model="form.designCost" placeholder="请输入建安费" :disabled="disabled"/>
                </el-form-item>
                <el-form-item label="设备及工器购置费" prop="designEquipmentCost" class="detailsInformation">
                  <el-input v-model="form.designEquipmentCost" placeholder="请输入设备及工器购置费" :disabled="disabled"/>
                </el-form-item>
                <el-form-item label="工程费" prop="designEngineeringCost" class="detailsInformation">
                  <el-input v-model="form.designEngineeringCost" placeholder="请输入工程费" :disabled="disabled"/>
                </el-form-item>
              </div>
            </div>

            <div class="sgCostTitle" style="border-bottom: 1px solid #f0f0f0">
              <span style="line-height: 30px;margin-left: 10px;font-weight: bold">施工图预算（万元）</span>
              <div class="cost">
                <el-form-item label="建安费" prop="constructionCost" class="detailsInformation">
                  <el-input v-model="form.constructionCost" placeholder="请输入建安费" :disabled="disabled"/>
                </el-form-item>
                <el-form-item label="设备及工器购置费" prop="constructionEquipmentCost" class="detailsInformation">
                  <el-input v-model="form.constructionEquipmentCost" placeholder="请输入设备及工器购置费" :disabled="disabled"/>
                </el-form-item>
                <el-form-item label="工程费" prop="constructionEngineeringCost" class="detailsInformation">
                  <el-input v-model="form.constructionEngineeringCost" placeholder="请输入工程费" :disabled="disabled"/>
                </el-form-item>
              </div>
            </div>

            <div class="sgCostTitle" style="border-bottom: 1px solid #f0f0f0">
              <span style="line-height: 30px;margin-left: 10px;font-weight: bold">招标费用（万元）</span>
              <div class="cost">
                <el-form-item label="合同价(不含暂定金)" prop="contractCost" class="detailsInformation">
                  <el-input v-model="form.contractCost" placeholder="请输入合同价(不含暂定金)" :disabled="disabled"/>
                </el-form-item>
                <el-form-item label="暂定金" prop="provisionalPayment" class="detailsInformation">
                  <el-input v-model="form.provisionalPayment" placeholder="请输入暂定金" :disabled="disabled"/>
                </el-form-item>
              </div>
            </div>

            <div class="sgCostTitle">
              <span style="line-height: 30px;margin-left: 10px;font-weight: bold">交竣工时间</span>
              <div class="cost">
                <el-form-item label="开工时间" prop="startTime" class="detailsInformation">
                  <el-date-picker clearable
                                  v-model="form.startTime"
                                  type="date"
                                  value-format="yyyy-MM-dd"
                                  :disabled="disabled"
                                  placeholder="请选择开工时间">
                  </el-date-picker>
                </el-form-item>
                <el-form-item label="交工时间" prop="submitTime" class="detailsInformation">
                  <el-date-picker clearable
                                  v-model="form.submitTime"
                                  type="date"
                                  value-format="yyyy-MM-dd"
                                  :disabled="disabled"
                                  placeholder="请选择交工时间">
                  </el-date-picker>
                </el-form-item>
                <el-form-item label="竣工时间" prop="endTime" class="detailsInformation">
                  <el-date-picker clearable
                                  v-model="form.endTime"
                                  type="date"
                                  value-format="yyyy-MM-dd"
                                  :disabled="disabled"
                                  placeholder="请选择竣工时间">
                  </el-date-picker>
                </el-form-item>
              </div>
            </div>
          </div>
        </div>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog :title="bdTitle" :visible.sync="startOpen" width="500px" append-to-body>
      <el-table v-loading="loading" :data="supervisorList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"/>
        <el-table-column label="监理标段id" align="center" prop="supervisorId"/>
        <el-table-column label="监理标段名称" align="center" prop="supervisorName"/>
        <el-table-column label="关联==》项目信息管理表==》监理标id" align="center" prop="supervisorProjectId"/>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['system:supervisor:edit']"
            >修改
            </el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['system:supervisor:remove']"
            >删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
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
      disabled: false,
      // 项目阶段搜索
      options: [{
        value: '1',
        label: '立项阶段'
      }, {
        value: '2',
        label: '工可阶段'
      }, {
        value: '3',
        label: '勘察设计招标阶段'
      }, {
        value: '4',
        label: '勘察设计阶段'
      }, {
        value: '5',
        label: '施工招标阶段'
      }, {
        value: '6',
        label: '开工准备'
      }, {
        value: '7',
        label: '施工阶段'
      }, {
        value: '8',
        label: '试运营阶段'
      }],
      value: '',
      active: 0,
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
      // 监理标段表格数据
      supervisorList: [],
      // 弹出层标题
      title: "",
      // 标段弹出层标题
      bdTitle: "",
      // 是否显示弹出层
      open: false,
      // 是否显示标段弹出层
      startOpen: false,
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
          {required: true, message: "路线编号不能为空", trigger: "blur"}
        ],
        routeName: [
          {required: true, message: "路线名称不能为空", trigger: "blur"}
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 项目阶段进度条 */
    next(num) {
      this.active = num;
    },
    /** 查询项目信息管理列表 */
    getList() {
      this.loading = true;
      listProjectInformation(this.queryParams).then(response => {
        this.projectInformationList = response.rows;
        console.log(this.projectInformationList);
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
    /** 标段按钮操作 */
    handleBdQuery(row) {
      this.reset();
      this.startOpen = true;
    },
    /** 详情按钮操作 */
    handleDetails(row) {
      this.reset();
      const projectId = row.projectId || this.ids
      getProjectInformation(projectId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "项目信息管理详情";
        this.active = response.data.projectPhase;
        this.disabled = true;
      });
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加项目信息管理";
      this.active = 0;
      this.disabled = false;
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const projectId = row.projectId || this.ids
      getProjectInformation(projectId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改项目信息管理";
        this.active = response.data.projectPhase;
        this.disabled = false;
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.disabled == true) {
            this.$modal.msgSuccess("查看成功");
            this.open = false;
            this.getList();
          } else if (this.form.projectId != null) {
            this.form.projectPhase = this.active;
            updateProjectInformation(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.projectPhase = this.active;
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

<style>
.projectMainInformationTitle {
  width: 1000px;
  height: 300px;
  border: #f0f0f0 1px solid;
  margin-top: 50px;
  margin-bottom: 30px;
}

.projectMainInformation {
  width: 999px;
  height: 250px;
  border-top: #f0f0f0 1px solid;
  margin-top: 5px;
  display: flex;
  flex-wrap: wrap;
}

.projectDetailsInformationTitle {
  width: 1000px;
  height: 700px;
  border: #f0f0f0 1px solid;
  margin-top: 50px;
  margin-bottom: 30px;
}

.projectDetailsInformation {
  width: 999px;
  height: 700px;
  border-top: #f0f0f0 1px solid;
  margin-top: 5px;
  padding: 10px;
}

.detailsInformation {
  width: 330px;
  height: 80px;
  padding-top: 20px;
}

.cost {
  display: flex;
}
</style>
