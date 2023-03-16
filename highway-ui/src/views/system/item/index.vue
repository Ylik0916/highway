<template>
  <div class="app-container">

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:item:add']"
        >新增</el-button>
      </el-col>

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
      <el-table-column label="路线名称" align="center" prop="name" />
      <el-table-column label="行政区域" align="center" prop="region" />
      <el-table-column label="国省补助" align="center" prop="nationalProvinceSubsidy" />
      <el-table-column label="市州投资" align="center" prop="municipalityInvest" />
      <el-table-column label="县区自筹" align="center" prop="countySelfFund" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:item:edit']"
          >修改</el-button>
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

    <!-- 添加或修改进度对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="路线编码" prop="encoding">
          <el-input v-model="form.encoding" placeholder="请输入路线编码" />
        </el-form-item>
        <el-form-item label="路线名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入路线名称" />
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
          <el-input v-model="form.level" placeholder="请输入技术等级" />
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
          <el-input v-model="form.productionDrawing" placeholder="请输入施工图设计是否完成" />
        </el-form-item>
        <el-form-item label="施工图审批是否完成" prop="productionDrawingApproval">
          <el-input v-model="form.productionDrawingApproval" placeholder="请输入施工图审批是否完成" />
        </el-form-item>
        <el-form-item label="施工招投标是否完成" prop="bidWhetherCompletion">
          <el-input v-model="form.bidWhetherCompletion" placeholder="请输入施工招投标是否完成" />
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
  </div>
</template>

<script>
import { listItem, getItem, delItem, addItem, updateItem } from "@/api/system/item";

export default {
  name: "Item",
  dicts: ['plan_year', 'constructed_type'],
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
      // 进度表格数据
      itemList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
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
          { required: true, message: "路线名称不能为空", trigger: "blur" }
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
  created() {
    this.getList();
  },
  methods: {
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
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getItem(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改进度";
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
  }
};
</script>
