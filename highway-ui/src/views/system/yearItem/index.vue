<template>
  <div class="bigBox">

    <div class="app-container smallBox">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="计划名称" prop="name">
          <el-input
            v-model="queryParams.name"
            placeholder="请输入计划名称"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="计划类型" prop="type">
          <el-select v-model="queryParams.type" placeholder="请选择计划类型" clearable>
            <el-option
              v-for="dict in dict.type.plan_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="计划年度" prop="planYear">
          <el-select v-model="queryParams.planYear" placeholder="请选择计划年度" clearable>
            <el-option
              v-for="dict in dict.type.plan_year"
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

      <!--
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
            v-hasPermi="['system:plan:add']"
          >新增</el-button>
        </el-col>

        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['system:plan:export']"
          >导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
      -->

      <el-table v-loading="loading" :data="planList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="计划名称" align="center" prop="name" />
        <el-table-column label="计划投资(万元)" align="center" prop="investment">1</el-table-column>
        <el-table-column label="计划年度" align="center" prop="planYear">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.plan_year" :value="scope.row.planYear"/>
          </template>
        </el-table-column>
        <el-table-column label="计划归属" align="center" prop="affiliation">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.affiliation" :value="scope.row.affiliation"/>
          </template>
        </el-table-column>
        <el-table-column label="待上报" align="center" prop="numberOfItems" >10</el-table-column>
        <el-table-column label="待审核" align="center" prop="numberOfItems" >5</el-table-column>

        <!--
        <el-table-column label="计划类型" align="center" prop="type">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.plan_type" :value="scope.row.type"/>
          </template>
        </el-table-column>
        -->
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">

            <el-button
              size="mini"
              type="text"
              icon="el-icon-finished"
              @click="detailedList(scope.row)"
              v-hasPermi="['system:information:edit']"
            >明细列表</el-button>

          <!--  <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['system:plan:edit']"
            >修改</el-button>

            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['system:plan:remove']"
            >删除</el-button>-->
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

      <!-- 添加或修改年度计划对话框 -->

      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="计划名称" style="width: 297px" prop="name">
            <el-input v-model="form.name" placeholder="请输入计划名称" />
          </el-form-item>
          <el-form-item label="计划类型" prop="type">
            <el-select v-model="form.type" placeholder="请选择计划类型">
              <el-option
                v-for="dict in dict.type.plan_type"
                :key="dict.value"
                :label="dict.label"
                :value="parseInt(dict.value)"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="计划归属" prop="affiliation">
            <el-select v-model="form.affiliation" placeholder="请选择计划归属">
              <el-option
                v-for="dict in dict.type.affiliation"
                :key="dict.value"
                :label="dict.label"
                :value="parseInt(dict.value)"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="计划年度" prop="planYear">
            <el-select v-model="form.planYear" placeholder="请选择计划年度">
              <el-option
                v-for="dict in dict.type.plan_year"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="填报周期" prop="fillPeriod">
            <el-select v-model="form.fillPeriod" placeholder="请选择填报周期">
              <el-option
                v-for="dict in dict.type.fill_period"
                :key="dict.value"
                :label="dict.label"
                :value="parseInt(dict.value)"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="批复角色" prop="replyCharacter">
            <el-select v-model="form.replyCharacter" placeholder="请选择批复角色">
              <el-option
                v-for="dict in dict.type.reply_character"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>


    </div>
  </div>
</template>

<script>
import { listPlan, getPlan, delPlan, addPlan, updatePlan } from "@/api/system/plan";

export default {
  name: "YearPlan",
  dicts: ['plan_type', 'plan_year', 'fill_period', 'reply_character', 'affiliation'],
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
      // 年度计划表格数据
      planList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        type: null,
        affiliation: null,
        planYear: null,
        fillPeriod: null,
        replyCharacter: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "计划名称不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "计划类型不能为空", trigger: "change" }
        ],
        affiliation: [
          { required: true, message: "计划归属不能为空", trigger: "change" }
        ],
        planYear: [
          { required: true, message: "计划年度不能为空", trigger: "change" }
        ],
        fillPeriod: [
          { required: true, message: "填报周期不能为空", trigger: "change" }
        ],
        replyCharacter: [
          { required: true, message: "批复角色不能为空", trigger: "change" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {

    /** 查询年度计划列表 */
    getList() {
      this.loading = true;
      listPlan(this.queryParams).then(response => {
        this.planList = response.rows;
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
        id: null,
        name: null,
        type: null,
        affiliation: null,
        planYear: null,
        fillPeriod: null,
        replyCharacter: null
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加年度计划";
    },
    /** 明细列表按钮操作 */
    detailedList(row){
      this.$router.push({path:'/schedule/detailedList',query: {id:row.id}})
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getPlan(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改年度计划";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updatePlan(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPlan(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除年度计划编号为"' + ids + '"的数据项？').then(function() {
        return delPlan(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/plan/export', {
        ...this.queryParams
      }, `plan_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
