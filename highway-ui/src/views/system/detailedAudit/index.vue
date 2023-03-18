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

      <el-table v-loading="loading" :data="itemList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="所属年度计划" align="center" prop="planId" />
        <el-table-column label="明细名称" align="center" prop="name" />
        <el-table-column label="国省补助(万元)" align="center" prop="nationalProvinceSubsidy" />
        <el-table-column label="市州投资(万元)" align="center" prop="municipalityInvest" />
        <el-table-column label="县区自筹(万元)" align="center" prop="countySelfFund" />
     <!--   <el-table-column label="是否开工" align="center" prop="detailName" />
        <el-table-column label="是否完工" align="center" prop="detailName" />-->
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">

            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="auditXq(scope.row)"
              v-hasPermi="['system:information:edit']"
            >详情</el-button>

            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['system:plan:edit']"
            >审核</el-button>

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




      <!-- 详情明细审核对话框 -->
      <el-dialog :title="title" :visible.sync="openXq" width="1000px" append-to-body>
        <el-tabs v-model="activeName" type="card">
          <el-tab-pane label="路线信息" name="first">
            <el-descriptions :model="form">
              <el-descriptions-item label="路线编码">{{ form.encoding }}</el-descriptions-item>
              <el-descriptions-item label="路线名称">{{ form.name }}</el-descriptions-item>
              <el-descriptions-item label="路线全长">{{ form.length }}</el-descriptions-item>
              <el-descriptions-item label="管养单位">{{ form.custodyUnit }}</el-descriptions-item>
              <el-descriptions-item label="行政区域">{{ form.region }}</el-descriptions-item>
              <el-descriptions-item label="待建里程(公里)">{{ form.unfinished }}</el-descriptions-item>
              <el-descriptions-item v-model="form.level" label="技术等级">
                <el-select v-model="form.level" placeholder="请选择技术等级">
                  <el-option
                    v-for="dict in dict.type.technical_grade"
                    :key="dict.value"
                    :label="dict.label"
                    :value="parseInt(dict.value)"
                  ></el-option>
                </el-select>
              </el-descriptions-item>
              <el-descriptions-item label="计划修建路面类型">
                <!--{{ form.cutoffPoint }}-->
                <el-select v-model="form.constructedType" placeholder="请选择计划修建路面类型">
                  <el-option
                    v-for="dict in dict.type.constructed_type"
                    :key="dict.value"
                    :label="dict.label"
                    :value="parseInt(dict.value)"
                  ></el-option>
                </el-select>

              </el-descriptions-item>
              <el-descriptions-item label="路面宽度">{{ form.surfaceWidth }}</el-descriptions-item>
              <el-descriptions-item label="路基宽度">{{ form.roadbedWidth }}</el-descriptions-item>
              <el-descriptions-item label="起点桩号">{{ form.startingNumber }}</el-descriptions-item>
              <el-descriptions-item label="终点桩号">{{ form.terminusNumber }}</el-descriptions-item>
            </el-descriptions>
          </el-tab-pane>
          <el-tab-pane label="建设资金(万元)" name="second">
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
              <el-descriptions-item label="开工年限">
                <!--{{ form.modificationTime }}-->
                <el-select v-model="form.startWorkYear" placeholder="请选择开工年限">
                  <el-option
                    v-for="dict in dict.type.plan_year"
                    :key="dict.value"
                    :label="dict.label"
                    :value="parseInt(dict.value)"
                  ></el-option>
                </el-select>
              </el-descriptions-item>
              <el-descriptions-item label="完工年限">
                <!--{{ form.remarks }}-->
                <el-select v-model="form.completionYear" placeholder="请选择完工年限">
                  <el-option
                    v-for="dict in dict.type.plan_year"
                    :key="dict.value"
                    :label="dict.label"
                    :value="parseInt(dict.value)"
                  ></el-option>
                </el-select>
              </el-descriptions-item>
              <el-descriptions-item label="施工图设计是否完成">
                <!--{{ form.remarks }}-->
                <el-radio-group v-model="form.productionDrawing">
                  <el-radio
                    v-for="dict in dict.type.true_false"
                    :key="dict.value"
                    :label="parseInt(dict.value)"
                  >{{dict.label}}</el-radio>
                </el-radio-group>
              </el-descriptions-item>
              <el-descriptions-item label="施工图审批是否完成">
                <!--{{ form.remarks }}-->
                <el-radio-group v-model="form.productionDrawingApproval">
                  <el-radio
                    v-for="dict in dict.type.true_false"
                    :key="dict.value"
                    :label="parseInt(dict.value)"
                  >{{dict.label}}</el-radio>
                </el-radio-group>
              </el-descriptions-item>
              <el-descriptions-item label="施工图招投标是否完成">
                {{ form.remarks }}
                <el-radio-group v-model="form.bidWhetherCompletion">
                  <el-radio
                    v-for="dict in dict.type.true_false"
                    :key="dict.value"
                    :label="parseInt(dict.value)"
                  >{{dict.label}}</el-radio>
                </el-radio-group>
              </el-descriptions-item>
              <el-descriptions-item label="建议(养护内容)">{{ form.disease }}</el-descriptions-item>
              <el-descriptions-item label="备注">{{ form.postscript }}</el-descriptions-item>

            </el-descriptions>
          </el-tab-pane>
        </el-tabs>
      </el-dialog>


      <!-- 添加或修改明细审核对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="审核意见" prop="name">
            <el-input
              type="textarea"
              :rows="2"
              placeholder="请输入审核意见"
              v-model="textarea">
            </el-input>
            <!--<el-input v-model="form.name" placeholder="请输入审核意见" />-->
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm">通 过</el-button>
          <el-button type="primary" @click="cancel">驳 回</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import {listPlan, getPlan, delPlan, addPlan, updatePlan, listItemAnd1} from "@/api/system/plan";
import {getItem} from "@/api/system/item";

export default {
  name: "DetailedAudit",
  dicts: ['plan_type', 'plan_year', 'fill_period', 'reply_character', 'affiliation'],
  data() {
    return {
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
      // 年度计划表格数据
      itemList: [],
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
        detailScale: null
      },
      // 表单参数
      form: {},
      // 表单校验
      // 表单校验
      rules: {
        encoding: [
          { required: true, message: "路线编码不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "路线名称不能为空", trigger: "blur" }
        ],
        length: [
          { required: true, message: "路线全长(公里)不能为空", trigger: "blur" }
        ],
        region: [
          { required: true, message: "行政区域不能为空", trigger: "blur" }
        ],
        nationalProvinceSubsidy: [
          { required: true, message: "国省补助不能为空", trigger: "blur" }
        ],
        municipalityInvest: [
          { required: true, message: "市州投资不能为空", trigger: "blur" }
        ],
        countySelfFund: [
          { required: true, message: "县区自筹不能为空", trigger: "blur" }
        ],
        bidWhetherCompletion: [
          { required: true, message: "施工招投标是否完成不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {

    /** 查询等待审核明细列表 */
    getList() {
      this.loading = true;
      listItemAnd1(this.queryParams).then(response => {
        this.itemList = response.rows;
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
    auditXq(row){
      const id = row.id || this.ids
      getItem(id).then(response => {
        this.form = response.data;
        this.openXq = true;
        this.title = "详细进度";
      });    },
    /** 审核按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getPlan(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "明细审核";
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
