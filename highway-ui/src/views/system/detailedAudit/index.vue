<template>
  <div>

    <div class="app-container">
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

      <el-row :gutter="10" class="mb8">
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

      <el-table v-loading="loading" :data="planList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="所属年度计划" align="center" prop="planName" />
        <el-table-column label="明细名称" align="center" prop="name" />
        <el-table-column label="国省补助(万元)" align="center" prop="nationalProvinceSubsidy"/>
        <el-table-column label="市州投资(万元)" align="center" prop="municipalityInvest"/>
        <el-table-column label="县区自筹(万元)" align="center" prop="countySelfFund" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">

            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="detailXq(scope.row)"
              v-hasPermi="['system:information:edit']"
            >详情</el-button>

            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="reportedData(scope.row)"
              v-hasPermi="['system:plan:edit']"
            >通过</el-button>

            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="reject(scope.row)"
              v-hasPermi="['system:plan:remove']"
            >驳回</el-button>
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


      <!-- 详情明细上报对话框 -->
      <el-dialog :title="title" :visible.sync="openXq" width="1000px" append-to-body>
        <el-tabs v-model="activeName" type="card">
          <el-tab-pane label="路线信息" name="first">
            <el-descriptions :model="form">
              <el-descriptions-item label="路线编码">{{ form.encoding }}</el-descriptions-item>
              <el-descriptions-item label="项目名称">{{ form.name }}</el-descriptions-item>
              <el-descriptions-item label="路线全长">{{ form.length }}</el-descriptions-item>
              <el-descriptions-item label="管养单位">{{ form.custodyUnit }}</el-descriptions-item>
              <el-descriptions-item label="行政区域">{{ form.region }}</el-descriptions-item>
              <el-descriptions-item label="待建里程(公里)">{{ form.unfinished }}</el-descriptions-item>
              <el-descriptions-item label="技术等级" >
                <dict-tag style="margin-left: 3px" :options="dict.type.technical_grade" :value="form.level"/>
              </el-descriptions-item>

              <el-descriptions-item label="计划修建路面类型" >
                <dict-tag style="margin-left: 3px" :options="dict.type.constructed_type" :value="form.constructedType"/>
              </el-descriptions-item>

              <el-descriptions-item label="路面宽度">{{ form.surfaceWidth }}</el-descriptions-item>
              <el-descriptions-item label="路基宽度">{{ form.roadbedWidth }}</el-descriptions-item>
              <el-descriptions-item label="起点桩号">{{ form.startingNumber }}</el-descriptions-item>
              <el-descriptions-item label="终点桩号">{{ form.terminusNumber }}</el-descriptions-item>
            </el-descriptions>
          </el-tab-pane>
          <el-tab-pane label="建设资金" name="second">
            <el-descriptions :model="form">
              <el-descriptions-item label="国省补助">{{ form.nationalProvinceSubsidy }}  万元</el-descriptions-item>
              <el-descriptions-item label="市州投资">{{ form.municipalityInvest }}  万元</el-descriptions-item>
              <el-descriptions-item label="县区自筹">{{ form.countySelfFund }}  万元</el-descriptions-item>
              <el-descriptions-item label="批复资金">{{ form.replyCapital }}  万元</el-descriptions-item>
              <el-descriptions-item label="合同资金">{{ form.contractCapital }}  万元</el-descriptions-item>
            </el-descriptions>
          </el-tab-pane>
          <el-tab-pane label="其他信息" name="third">
            <el-descriptions :model="form">
              <el-descriptions-item label="未开工原因">{{ form.notInServiceCause }}</el-descriptions-item>
              <el-descriptions-item label="进展进度">{{ form.progress }}</el-descriptions-item>
              <el-descriptions-item label="批复/整合文件文号">{{ form.replyFileNumber }}</el-descriptions-item>
              <el-descriptions-item label="责任单位">{{ form.accountabilityUnit }}</el-descriptions-item>
              <el-descriptions-item label="负责人">{{ form.personInCharge }}</el-descriptions-item>
              <el-descriptions-item label="联系电话">{{ form.phone }}</el-descriptions-item>
              <el-descriptions-item label="开工年限" >
                <dict-tag style="margin-left: 3px" :options="dict.type.plan_year" :value="form.startWorkYear"/>
              </el-descriptions-item>
              <el-descriptions-item label="完工年限" >
                <dict-tag style="margin-left: 3px" :options="dict.type.plan_year" :value="form.completionYear"/>
              </el-descriptions-item>
              <el-descriptions-item label="施工图设计是否完成" >
                {{form.productionDrawing}}
              </el-descriptions-item>
              <el-descriptions-item label="施工图审批是否完成" >
                {{form.productionDrawingApproval}}
              </el-descriptions-item>
              <el-descriptions-item label="施工图招投标是否完成" >
                {{form.bidWhetherCompletion}}
              </el-descriptions-item>
              <el-descriptions-item label="建议(养护内容)">{{ form.disease }}</el-descriptions-item>
              <el-descriptions-item label="备注">{{ form.postscript }}</el-descriptions-item>
            </el-descriptions>
          </el-tab-pane>
        </el-tabs>
      </el-dialog>


    </div>
  </div>
</template>

<script>
import {listPlan, getPlan, delPlan, addPlan, updatePlan, listItemAnd1} from "@/api/system/plan";
import {listItem, getItem, delItem, addItem, updateItem, updateItemScale, rejectScale} from "@/api/system/item";
//行政区
import {listDept} from "@/api/system/dept";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "DetailedReporting",
  dicts: ['plan_type', 'plan_year', 'technical_grade','item_true_false', 'constructed_type', 'affiliation'],
  components: {
    Treeselect
  },
  data() {
    return {
      //年度计划集合
      plans: [],
      //行政下拉
      ordinaryOptions: [],
      listInformation: [],
      //默认打开的窗口
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
      // 明细上报表格数据
      planList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否显示详情弹出层
      openXq: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        encoding: null,
        name: null,
        length: null,
        custodyUnit: null,
        region: null,
        unfinished: null,
        level: null,
        constructedType: null,
        surfaceWidth: null,
        roadbedWidth: null,
        startingNumber: null,
        terminusNumber: null,
        nationalProvinceSubsidy: null,
        municipalityInvest: null,
        countySelfFund: null,
        replyCapital: null,
        contractCapital: null,
        notInServiceCause: null,
        progress: null,
        replyFileNumber: null,
        accountabilityUnit: null,
        personInCharge: null,
        phone: null,
        startWorkYear: null,
        completionYear: null,
        productionDrawing: null,
        productionDrawingApproval: null,
        bidWhetherCompletion: null,
        postscript: null,
        planId: null,
        disease: null,
        planName: null
      },
      // 表单参数
      form: {
      },
      // 表单校验
      rules: {
        planId: [
          { required: true, message: "年度计划不能为空", trigger: "blur" }
        ],
        encoding: [
          { required: true, message: "路线编码不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "路线名称不能为空", trigger: "blur" }
        ],
        length: [
          { required: true, message: "路线全长不能为空", trigger: "blur" }
        ],
        // region: [
        //   { required: true, message: "行政区域不能为空", trigger: "blur" }
        // ],
        nationalProvinceSubsidy: [
          { required: true, message: "国省补助不能为空", trigger: "blur" }
        ],
        municipalityInvest: [
          { required: true, message: "市州投资不能为空", trigger: "blur" }
        ],
        countySelfFund: [
          { required: true, message: "县区自筹不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getPlanList();
  },
  methods: {
    /** 查询年度计划列表 */
    getPlanList() {
      listPlan(this.queryParams).then(response => {
        this.plans = response.rows;
      });
    },
    /** 查询已驳回明细上报列表 */
    getList() {
      this.loading = true;
      listItemAnd1(this.queryParams).then(response => {
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
        encoding: null,
        name: null,
        length: null,
        custodyUnit: null,
        region: null,
        unfinished: null,
        level: null,
        constructedType: null,
        surfaceWidth: null,
        roadbedWidth: null,
        startingNumber: null,
        terminusNumber: null,
        nationalProvinceSubsidy: null,
        municipalityInvest: null,
        countySelfFund: null,
        replyCapital: null,
        contractCapital: null,
        notInServiceCause: null,
        progress: null,
        replyFileNumber: null,
        accountabilityUnit: null,
        personInCharge: null,
        phone: null,
        startWorkYear: null,
        completionYear: null,
        productionDrawing: null,
        productionDrawingApproval: null,
        bidWhetherCompletion: null,
        postscript: null,
        planId: null,
        disease: null,
        detailScale: null
      };
      this.resetForm("form");
    },

    /** 转换一般养护数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.deptName,
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

    /** 详情按钮操作 */
    detailXq(row){
      this.getTreeselect();
      const id = row.id || this.ids
      getItem(id).then(response => {
        this.form = response.data;
        this.openXq = true;
        this.title = "详细进度";
      });
    },


    /** 上报按钮操作 */
/*    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      const id = row.id || this.ids
      getItem(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "计划明细上报";
      });
    },*/

    /** 通过按钮操作 */
    reportedData(row) {
      const id = row.id || this.id;
      updateItemScale(id).then(response => {
        this.$modal.msgSuccess("已通过");
        this.open = false;
        this.getList();
      });
    },

    /** 通过按钮操作 */
    reject(row) {
      const id = row.id || this.id;
      rejectScale(id).then(response => {
        this.$modal.msgSuccess("已通过");
        this.open = false;
        this.getList();
      });
    },

    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateItem(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addItem(this.form).then(response => {
              this.$modal.msgSuccess("保存成功");
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
      this.$modal.confirm('是否确认删除明细上报编号为"' + ids + '"的数据项？').then(function() {
        return delItem(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/item/export', {
        ...this.queryParams
      }, `plan_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
