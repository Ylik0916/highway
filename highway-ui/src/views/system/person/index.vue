<template>
  <div class="bigBox">
    <div class="smallBox">
      <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
                 label-width="68px">
          <el-form-item label="人员姓名" prop="personName">
            <el-input
              v-model="queryParams.personName"
              placeholder="请输入从业人员姓名"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="所属单位" prop="unitName">
            <el-input
              v-model="queryParams.unitName"
              placeholder="请输入所属单位"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="单位类型" prop="unitName">
            <el-select v-model="queryParams.unitType" @keyup.enter.native="handleQuery" clearable placeholder="请选择单位类型">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="评价年度" prop="evaluateYears">
            <div class="block">
              <el-date-picker
                v-model="queryParams.evaluateYears"
                value-format="yyyy"
                type="year"
                @keyup.enter.native="handleQuery"
                placeholder="请输入评价年度">
              </el-date-picker>
            </div>
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
              v-hasPermi="['system:person:add']"
            >新增
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
              v-hasPermi="['system:person:remove']"
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
              v-hasPermi="['system:person:export']"
            >导出
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="personList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center" fixed/>
          <el-table-column label="从业人员名称" align="center" prop="personName"/>
          <el-table-column label="所属单位" align="center" prop="hwUnitEvaluate.unitName" :show-overflow-tooltip='true'/>
          <el-table-column label="单位类型" align="center" prop="unitType"/>
          <el-table-column label="评价年度" align="center" prop="evaluateYears">
            <template slot-scope="scope">
              {{ scope.row.evaluateYears }}年
            </template>
          </el-table-column>
          <el-table-column label="评价分数" align="center" prop="hwHistoryEvaluate.evaluateScore"/>
          <el-table-column label="评价等级" align="center" prop="hwHistoryEvaluate.evaluateGrade"/>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-tickets"
                @click="getHistoryList(scope.row)"
              >历史评价
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['system:person:edit']"
              >修改
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['system:person:remove']"
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

        <!-- 添加或修改从业人员评价对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
          <el-form ref="form" :model="form" :rules="rules" label-width="80px" label-position="top"
                   style="margin-left: 80px">
            <el-form-item label="评价年度" prop="evaluateYears">
              <div class="block">
                <el-date-picker
                  v-model="form.evaluateYears"
                  value-format="yyyy"
                  type="year"
                  style="width: 300px"
                  placeholder="请选择评价年度">
                </el-date-picker>
              </div>
            </el-form-item>
            <el-form-item label="从业人员姓名" prop="personName">
              <el-input v-model="form.personName" placeholder="请输入从业人员姓名" style="width: 300px"/>
            </el-form-item>
            <el-form-item label="所属单位" prop="personUnit">
              <el-select v-model="form.personUnit" clearable placeholder="请输入所属单位" style="width: 300px">
                <el-option
                  v-for="item in unitList"
                  :key="item.unitId"
                  :label="item.unitName"
                  :value="item.unitId">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="单位类型" prop="unitType">
              <el-select v-model="form.unitType" clearable placeholder="请选择单位类型" style="width: 300px">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="submitForm">确 定</el-button>
            <el-button @click="cancel">取 消</el-button>
          </div>
        </el-dialog>
        <!-- 历史评价对话框 -->
        <el-dialog :title="historyTitle" :visible.sync="historyOpen" width="700px" append-to-body>
          <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
              <el-button
                type="primary"
                plain
                icon="el-icon-plus"
                size="mini"
                @click="handleHistoryAdd"
                v-hasPermi="['system:evaluate:add']"
              >新增
              </el-button>
            </el-col>
          </el-row>
          <el-table v-loading="loading" :data="historyList">
            <el-table-column label="评价时间" align="center" prop="evaluateTime"/>
            <el-table-column label="评价分数" align="center" prop="evaluateScore"/>
            <el-table-column label="评价等级" align="center" prop="evaluateGrade"/>
            <el-table-column label="评价附件" align="center" prop="evaluateDocumentUpload"/>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  type="primary"
                  style="width: 45px"
                  @click="handleHistoryUpdate(scope.row)"
                  v-hasPermi="['system:evaluate:edit']"
                >编辑
                </el-button>
                <el-button
                  size="mini"
                  type="danger"
                  style="width: 45px"
                  @click="handleHistoryDelete(scope.row)"
                  v-hasPermi="['system:evaluate:remove']"
                >删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-dialog>
        <!-- 添加或修改历史评价对话框 -->
        <el-dialog :title="historyAddTitle" :visible.sync="historyAddOpen" width="500px" append-to-body>
          <el-form ref="historyForm" :model="historyForm" :rules="historyRules" label-width="80px">
            <el-form-item label="评分" prop="evaluateScore" style="width: 300px">
              <el-input-number v-model="historyForm.evaluateScore" controls-position="right" placeholder="请输入评分施工标名称"
                               :min="0"/>
            </el-form-item>
            <el-form-item label="附件" prop="evaluateDocumentUpload">
              <file-upload v-model="historyForm.evaluateDocumentUpload"/>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="historySubmitForm">确 定</el-button>
            <el-button @click="cancel">取 消</el-button>
          </div>
        </el-dialog>
      </div>
    </div>
  </div>
</template>

<script>
import {
  listPerson,
  getPerson,
  delPerson,
  addPerson,
  updatePerson,
  listEvaluate,
  getHistoryPersonList
} from "@/api/system/person";
import {addHistory, delHistory, getHistory, updateHistory} from "@/api/system/evaluate";

export default {
  name: "Person",
  data() {
    return {
      options: [{
        value: '建设单位',
        label: '建设单位'
      }, {
        value: '勘察设计单位',
        label: '勘察设计单位'
      }, {
        value: '施工单位',
        label: '施工单位'
      }, {
        value: '监理单位',
        label: '监理单位'
      }, {
        value: '其他单位',
        label: '其他单位'
      }],
      value: '',
      // 用于历史评价添加
      personId: 0,
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
      // 从业人员评价表格数据
      personList: [],
      unitList: [],
      // 历史评价表格数据
      historyList: [],
      // 弹出层标题
      title: "",
      // 历史评价弹出层标题
      historyTitle: "",
      // 历史评价添加修改弹出层标题
      historyAddTitle: "",
      // 是否显示弹出层
      open: false,
      // 是否显示历史评价弹出层
      historyOpen: false,
      // 是否显示历史评价添加修改弹出层
      historyAddOpen: false,
      // 查询参数
      hwUnitEvaluate: {
        unitName: null
      },
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        personName: null,
        personUnit: null,
        unitType: null,
        evaluateYears: null,
        unitName: null
      },
      // 表单参数
      form: {},
      // 历史评价表单参数
      historyForm: {},
      // 表单校验
      rules: {
        personName: [
          {required: true, message: "从业人员名称不能为空", trigger: "blur"}
        ],
        personUnit: [
          {required: true, message: "所属单位id不能为空", trigger: "blur"}
        ],
        unitType: [
          {required: true, message: "单位类型不能为空", trigger: "change"}
        ],
        evaluateYears: [
          {required: true, message: "评价年度不能为空", trigger: "blur"}
        ]
      },
      // 历史评价表单校验
      historyRules: {
        evaluateScore: [
          {required: true, message: "评分不能为空", trigger: "blur"}
        ],
        evaluateDocumentUpload: [
          {required: true, message: "附件不能为空", trigger: "blur"}
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询历史评价列表 */
    getHistoryList(row) {
      const personId = row.personId || this.ids
      getHistoryPersonList(personId).then(response => {
        this.historyList = response.rows;
        this.personId = personId;
        this.historyOpen = true;
        this.historyTitle = "历史评价";
      });
    },
    /** 查询从业人员评价列表 */
    getList() {
      this.loading = true;
      this.hwUnitEvaluate.unitName = this.queryParams.unitName;
      listPerson(this.queryParams, this.hwUnitEvaluate).then(response => {
        this.personList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
      listEvaluate().then(response => {
        this.unitList = response.rows;
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
        personId: null,
        personName: null,
        personUnit: null,
        unitType: null,
        evaluateYears: null
      };
      this.resetForm("form");
    },
    // 历史评价表单重置
    historyReset() {
      this.historyForm = {
        evaluateId: null,
        evaluateTime: null,
        evaluateScore: null,
        evaluateUnitId: null,
        evaluateDocumentUpload: null,
      };
      this.resetForm("historyForm");
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
      this.ids = selection.map(item => item.personId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增历史评价按钮操作 */
    handleHistoryAdd() {
      this.historyReset();
      this.historyAddOpen = true;
      this.historyAddTitle = "评价新增";
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加从业人员评价";
    },
    /** 修改历史评价按钮操作 */
    handleHistoryUpdate(row) {
      this.historyReset();
      const evaluateId = row.evaluateId || this.ids
      getHistory(evaluateId).then(response => {
        this.historyForm = response.data;
        this.historyAddOpen = true;
        this.historyAddTitle = "评价修改";
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const personId = row.personId || this.ids
      getPerson(personId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改从业人员评价";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.personId != null) {
            updatePerson(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPerson(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 历史评价提交按钮 */
    historySubmitForm() {
      this.$refs["historyForm"].validate(valid => {
        if (valid) {
          if (this.historyForm.evaluateId != null) {
            this.historyForm.evaluateUnitId = this.unitId;
            updateHistory(this.historyForm).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.historyAddOpen = false;
              this.historyOpen = false;
              this.getList();
            });
          } else {
            this.historyForm.evaluatePersonId = this.personId;
            addHistory(this.historyForm).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.historyAddOpen = false;
              this.historyOpen = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const personIds = row.personId || this.ids;
      this.$modal.confirm('是否确认删除从业人员评价编号为"' + personIds + '"的数据项？').then(function () {
        return delPerson(personIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 删除按钮操作 */
    handleHistoryDelete(row) {
      const evaluateId = row.evaluateId || this.ids;
      this.$modal.confirm('是否确认删除历史评价编号为"' + evaluateId + '"的数据项？').then(function () {
        return delHistory(evaluateId);
      }).then(() => {
        this.historyOpen = false;
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/person/export', {
        ...this.queryParams
      }, `person_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
