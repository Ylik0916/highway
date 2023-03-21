<template>
  <div class="bigBox">

    <div class="app-container smallBox">

      <div>
        <el-tabs v-model="activeName" type="card" style="width: 100%" @tab-click="handleClick" tab-position="top">
          <el-tab-pane label="进度上报" name="first"><progress-report></progress-report></el-tab-pane>
          <el-tab-pane label="进度审核" name="second"><progress-audit></progress-audit></el-tab-pane>
          <el-tab-pane label="进度明细" name="thirdly"><schedule-detail></schedule-detail></el-tab-pane>
        </el-tabs>
      </div>
    </div>
  </div>
</template>

<script>
import { listPlan, getPlan, delPlan, addPlan, updatePlan } from "@/api/system/plan";
import ProgressReport from "@/views/system/progressReport";
import ProgressAudit from "@/views/system/progressAudit";
import ScheduleDetail from "@/views/system/scheduleDetail";

export default {
  name: "Plan",
  components:{ScheduleDetail, ProgressAudit, ProgressReport},
  dicts: ['plan_type', 'plan_year', 'fill_period', 'reply_character', 'affiliation'],
  data() {
    return {
      activeName:'first',
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
    /** 详情按钮操作 */
    planDetails(row){
      this.$router.push({path:'/plan/item',query: {id:row.id}})
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
