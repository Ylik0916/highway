<template>
  <div class="app-container">

    <el-row :gutter="10" class="mb8">
      <!--<el-col :span="1.5">-->
      <!--  <el-button-->
      <!--    type="primary"-->
      <!--    plain-->
      <!--    icon="el-icon-plus"-->
      <!--    size="mini"-->
      <!--    @click="handleAdd"-->
      <!--    v-hasPermi="['system:item:add']"-->
      <!--  >新增</el-button>-->
      <!--</el-col>-->

      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:item:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:item:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="itemList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="区县" align="center" prop="region" />
      <el-table-column label="项目名称" align="center" prop="name" />
      <el-table-column label="国省补助(万元)" align="center" prop="nationalProvinceSubsidy" />
      <el-table-column label="市州投资(万元)" align="center" prop="municipalityInvest" />
      <el-table-column label="县区自筹(万元)" align="center" prop="countySelfFund" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="itemXq(scope.row)"
            v-hasPermi="['system:item:edit']"
          >详情</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:item:remove']"
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

    <!-- 详情进度对话框 -->

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

            <!--<el-descriptions-item v-model="form.level" label="技术等级">
              <el-select v-model="form.level" placeholder="请选择技术等级">
                <el-option
                  v-for="dict in dict.type.technical_grade"
                  :key="dict.value"
                  :label="dict.label"
                  :value="parseInt(dict.value)"
                ></el-option>
              </el-select>
            </el-descriptions-item>-->

           <!-- <el-descriptions-item v-model="form.constructedType" label="计划修建路面类型">
              <el-select v-model="form.constructedType" placeholder="请选择计划修建路面类型">
                <el-option
                  v-for="dict in dict.type.constructed_type"
                  :key="dict.value"
                  :label="dict.label"
                  :value="parseInt(dict.value)"
                ></el-option>
              </el-select>
            </el-descriptions-item>-->

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

           <!-- <el-descriptions-item label="开工年限">
              &lt;!&ndash;{{ form.modificationTime }}&ndash;&gt;
              <el-select v-model="form.startWorkYear" placeholder="请选择开工年限">
                <el-option
                  v-for="dict in dict.type.plan_year"
                  :key="dict.value"
                  :label="dict.label"
                  :value="parseInt(dict.value)"
                ></el-option>
              </el-select>
            </el-descriptions-item>-->

            <el-descriptions-item label="完工年限" >
              <dict-tag style="margin-left: 3px" :options="dict.type.plan_year" :value="form.completionYear"/>
            </el-descriptions-item>

           <!-- <el-descriptions-item label="完工年限">
              &lt;!&ndash;{{ form.remarks }}&ndash;&gt;
              <el-select v-model="form.completionYear" placeholder="请选择完工年限">
                <el-option
                  v-for="dict in dict.type.plan_year"
                  :key="dict.value"
                  :label="dict.label"
                  :value="parseInt(dict.value)"
                ></el-option>
              </el-select>
            </el-descriptions-item>-->

            <el-descriptions-item label="施工图设计是否完成" >
              <dict-tag style="margin-left: 3px" :options="dict.type.item_true_false" :value="form.productionDrawing"/>
            </el-descriptions-item>

           <!-- <el-descriptions-item label="施工图设计是否完成">
              &lt;!&ndash;{{ form.remarks }}&ndash;&gt;
              <el-radio-group v-model="form.productionDrawing">
                <el-radio
                  v-for="dict in dict.type.true_false"
                  :key="dict.value"
                  :label="parseInt(dict.value)"
                >{{dict.label}}</el-radio>
              </el-radio-group>
            </el-descriptions-item>-->

            <el-descriptions-item label="施工图审批是否完成" >
              <dict-tag style="margin-left: 3px" :options="dict.type.item_true_false" :value="form.productionDrawingApproval"/>
            </el-descriptions-item>
          <!--
            <el-descriptions-item label="施工图审批是否完成">
              &lt;!&ndash;{{ form.remarks }}&ndash;&gt;
              <el-radio-group v-model="form.productionDrawingApproval">
                <el-radio
                  v-for="dict in dict.type.true_false"
                  :key="dict.value"
                  :label="parseInt(dict.value)"
                >{{dict.label}}</el-radio>
              </el-radio-group>
            </el-descriptions-item>-->

            <el-descriptions-item label="施工图招投标是否完成" >
              <dict-tag style="margin-left: 3px" :options="dict.type.item_true_false" :value="form.bidWhetherCompletion"/>
            </el-descriptions-item>

           <!-- <el-descriptions-item label="施工图招投标是否完成">
              {{ form.remarks }}
              <el-radio-group v-model="form.bidWhetherCompletion">
                <el-radio
                  v-for="dict in dict.type.true_false"
                  :key="dict.value"
                  :label="parseInt(dict.value)"
                >{{dict.label}}</el-radio>
              </el-radio-group>
            </el-descriptions-item>-->

            <el-descriptions-item label="建议(养护内容)">{{ form.disease }}</el-descriptions-item>
            <el-descriptions-item label="备注">{{ form.postscript }}</el-descriptions-item>

          </el-descriptions>
        </el-tab-pane>
      </el-tabs>
    </el-dialog>

    <!-- 添加和修改进度对话框 -->
   <!-- <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="路线编码" prop="encoding">
          <el-input v-model="form.encoding" placeholder="请输入路线编码" />
        </el-form-item>
        <el-form-item label="项目名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入项目名称" />
        </el-form-item>
        <el-form-item label="路线全长" prop="length">
          <el-input v-model="form.length" placeholder="请输入路线全长" />
        </el-form-item>
        <el-form-item label="管养单位" prop="custodyUnit">
          <el-input v-model="form.custodyUnit" placeholder="请输入管养单位" />
        </el-form-item>
        <el-form-item label="行政区域" prop="region">
          <el-input v-model="form.region" placeholder="请输入行政区域" />
        </el-form-item>
        <el-form-item label="待建里程(公里)" prop="unfinished">
          <el-input v-model="form.unfinished" placeholder="请输入待建里程(公里)" />
        </el-form-item>
        <el-form-item label="技术等级" prop="level">
          <el-select v-model="form.level" placeholder="请选择技术等级">
            <el-option
              v-for="dict in dict.type.technical_grade"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="计划修建路面类型" prop="constructedType">
          <el-select v-model="form.constructedType" placeholder="请选择计划修建路面类型">
            <el-option
              v-for="dict in dict.type.constructed_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="路面宽度" prop="surfaceWidth">
          <el-input v-model="form.surfaceWidth" placeholder="请输入路面宽度" />
        </el-form-item>
        <el-form-item label="路基宽度" prop="roadbedWidth">
          <el-input v-model="form.roadbedWidth" placeholder="请输入路基宽度" />
        </el-form-item>
        <el-form-item label="起点桩号" prop="startingNumber">
          <el-input v-model="form.startingNumber" placeholder="请输入起点桩号" />
        </el-form-item>
        <el-form-item label="终点桩号" prop="terminusNumber">
          <el-input v-model="form.terminusNumber" placeholder="请输入终点桩号" />
        </el-form-item>
        <el-form-item label="国省补助" prop="nationalProvinceSubsidy">
          <el-input v-model="form.nationalProvinceSubsidy" placeholder="请输入国省补助" />
        </el-form-item>
        <el-form-item label="市州投资" prop="municipalityInvest">
          <el-input v-model="form.municipalityInvest" placeholder="请输入市州投资" />
        </el-form-item>
        <el-form-item label="县区自筹" prop="countySelfFund">
          <el-input v-model="form.countySelfFund" placeholder="请输入县区自筹" />
        </el-form-item>
        <el-form-item label="批复资金" prop="replyCapital">
          <el-input v-model="form.replyCapital" placeholder="请输入批复资金" />
        </el-form-item>
        <el-form-item label="合同资金" prop="contractCapital">
          <el-input v-model="form.contractCapital" placeholder="请输入合同资金" />
        </el-form-item>
        <el-form-item label="未开工原因" prop="notInServiceCause">
          <el-input v-model="form.notInServiceCause" placeholder="请输入未开工原因" />
        </el-form-item>
        <el-form-item label="进展进度" prop="progress">
          <el-input v-model="form.progress" placeholder="请输入进展进度" />
        </el-form-item>
        <el-form-item label="批复/整合文件文号" prop="replyFileNumber">
          <el-input v-model="form.replyFileNumber" placeholder="请输入批复/整合文件文号" />
        </el-form-item>
        <el-form-item label="责任单位" prop="accountabilityUnit">
          <el-input v-model="form.accountabilityUnit" placeholder="请输入责任单位" />
        </el-form-item>
        <el-form-item label="责任人" prop="personInCharge">
          <el-input v-model="form.personInCharge" placeholder="请输入责任人" />
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="开工年限" prop="startWorkYear">
          <el-select v-model="form.startWorkYear" placeholder="请选择开工年限">
            <el-option
              v-for="dict in dict.type.plan_year"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="完工年限" prop="completionYear">
          <el-select v-model="form.completionYear" placeholder="请选择完工年限">
            <el-option
              v-for="dict in dict.type.plan_year"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="施工图设计是否完成" prop="productionDrawing">
          <el-radio-group v-model="form.productionDrawing">
            <el-radio
              v-for="dict in dict.type.true_false"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="施工图审批是否完成" prop="productionDrawingApproval">
          <el-radio-group v-model="form.productionDrawingApproval">
            <el-radio
              v-for="dict in dict.type.true_false"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="施工招投标是否完成" prop="bidWhetherCompletion">
          <el-radio-group v-model="form.bidWhetherCompletion">
            <el-radio
              v-for="dict in dict.type.true_false"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="postscript">
          <el-input v-model="form.postscript" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="年度计划ID" prop="planId">
          <el-input v-model="form.planId" placeholder="请输入年度计划ID" />
        </el-form-item>
        <el-form-item label="病害内容" prop="disease">
          <el-input v-model="form.disease" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
-->
  </div>
</template>

<script>
import {listItem, getItem, delItem, addItem, updateItem, listItemByPlanId} from "@/api/system/item";
import {getSectionInformation} from "@/api/system/sectionInformation";

export default {
  name: "Item",
  dicts: ['plan_year', 'constructed_type', 'true_false', 'technical_grade'],
  data() {
    return {
      //使用技术等级字典
      technicalGradeOptions: [],
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
      // 进度表格数据
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
        disease: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        encoding: [
          { required: true, message: "路线编码不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "项目名称不能为空", trigger: "blur" }
        ],
        length: [
          { required: true, message: "路线全长不能为空", trigger: "blur" }
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
      }
    };
  },
  /*created() {
    this.getList();
  },*/
  created() {
    this.getItemByPlanId();
  },
  methods: {
    /** 根据年度计划查询进度列表 */
    getItemByPlanId(row) {
      //获取PlanId参数
      const id = this.$route.query.id
      //刷新页面
/*
      this.mounted()
*/
      this.loading = true;
      listItemByPlanId(id).then(response => {
        this.itemList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 刷新页面*/
   /* mounted() {
      if (location.href.indexOf("#reloaded") == -1) {
        location.href = location.href + "#reloaded";
        location.reload();
      }
    },*/
    /** 查询进度列表 */
    getList() {
      this.loading = true;
      listItem(this.queryParams).then(response => {
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
        disease: null
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
      this.title = "添加进度";
    },
    /** 详情按钮操作 */
    itemXq(row) {
      const id = row.id || this.ids
      getItem(id).then(response => {
        this.form = response.data;
        this.openXq = true;
        this.title = "详情进度";
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
      this.$modal.confirm('是否确认删除进度编号为"' + ids + '"的数据项？').then(function() {
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
      }, `item_${new Date().getTime()}.xlsx`)
    }
  }/*,
  mounted() {
    console.log("aaaaaaaa")
    console.log(this.$route.query.id)
    this.getItemByPlanId(this.$route.query.id)
  }*/
};
</script>
