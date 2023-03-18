<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="80px">
      <el-form-item label="路线编码" prop="pathCode">
        <el-input
          v-model="queryParams.pathCode"
          placeholder="请输入路线编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="村道名称" prop="villageName">
        <el-input
          v-model="queryParams.villageName"
          placeholder="请输入村道名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审核状态" prop="auditStatusid">
        <el-select v-model="queryParams.auditStatusid" placeholder="请选择审核状态" clearable>
          <el-option
            v-for="dict in dict.type.audit_status"
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
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:ordinary:add']"
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
          v-hasPermi="['system:ordinary:edit']"
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
          v-hasPermi="['system:ordinary:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:ordinary:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ordinaryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="路线编码" align="center" prop="pathCode" />
      <el-table-column label="村道名称" align="center" prop="villageName" />
      <el-table-column label="养护里程" align="center" prop="maintainMileage" />
      <el-table-column label="沥青路面、水泥混凝土路面、砖路面(km)" align="center" prop="pitchRoad" />
      <el-table-column label="砂砾路面" align="center" prop="gravelRoad" />
      <el-table-column label="养护资金(万元)" align="center">
        <el-table-column label="总" align="center" prop="allFund" />
        <el-table-column label="市" align="center" prop="cityFund" />
        <el-table-column label="县" align="center" prop="countyFund" />
      </el-table-column>
      <el-table-column label="审核状态" align="center" prop="auditStatusid">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top" v-if="scope.row.auditStatusid !== 0">
            <p>审核人: {{ scope.row.auditPeople }}</p>
            <p>审核时间: {{ parseTime(scope.row.time, '{y}-{m}-{d}') }}</p>
            <p style="display: flex">审核状态:<dict-tag style="margin-left: 3px" :options="dict.type.audit_status" :value="scope.row.auditStatusid"/>
            </p>
            <p>审核意见: {{ scope.row.message }}</p>
            <div slot="reference" class="name-wrapper">
              <dict-tag :options="dict.type.audit_status" :value="scope.row.auditStatusid"/>
            </div>
          </el-popover>
          <dict-tag v-else :options="dict.type.audit_status" :value="scope.row.auditStatusid"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleOne(scope.row)"
            v-hasPermi="['system:ordinary:query']"
          >详情</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleAudit(scope.row)"
            :disabled="scope.row.auditStatusid !== 0"
            v-hasPermi="['system:ordinary:edit']"
          >审核</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            :disabled="scope.row.auditStatusid !== 0"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:ordinary:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            :disabled="scope.row.auditStatusid !== 0"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:ordinary:remove']"
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

    <!-- 添加或修改一般养护对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1100px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-position="top" label-width="140px">
        <el-row :gutter="20">
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="行政区" prop="administrative">
                <treeselect v-model="form.administrative" :options="ordinaryOptions" :normalizer="normalizer" placeholder="请选择行政区" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="路线编码" prop="pathCode">
                <el-select v-model="form.pathCode" value-key="id" placeholder="请输入路线编码" style="width: 100%">
                  <el-option
                    v-for="item in listInformation"
                    :key="item.id"
                    :label="item.routeCoding"
                    :value="item.routeCoding">
                  </el-option>
                </el-select>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="村道名称" prop="villageName">
                <el-input v-model="form.villageName" placeholder="请输入村道名称" />
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="养护里程" prop="maintainMileage">
                <el-input v-model.number="form.maintainMileage" placeholder="请输入养护里程" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="沥青路面、水泥混凝土路面、砖路面(km)" prop="pitchRoad">
                <el-input v-model.number="form.pitchRoad" placeholder="请输入沥青路面、水泥混凝土路面、砖路面(km)" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="砂砾路面" prop="gravelRoad">
                <el-input v-model.number="form.gravelRoad" placeholder="请输入砂砾路面" />
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="总养护资金(万元)" prop="allFund">
                <el-input v-model.number="form.allFund" placeholder="请输入总养护资金(万元)" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="市养护资金(万元)" prop="cityFund">
                <el-input v-model.number="form.cityFund" placeholder="请输入市养护资金(万元)" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="县养护资金(万元)" prop="countyFund">
                <el-input v-model.number="form.countyFund" placeholder="请输入县养护资金(万元)" />
              </el-form-item>
            </div>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm(0)">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 审核 -->
    <el-dialog :title="auidTitle" :visible.sync="openAuit" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-position="top" label-width="140px">
        <el-row :gutter="20">
          <el-col :span="24">
            <div class="grid-content">
              <el-form-item label="审核意见" prop="message">
                <el-input type="textarea" v-model="form.message" placeholder="请输入审核意见" />
              </el-form-item>
            </div>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm(1)">审 核</el-button>
        <el-button type="primary" @click="submitForm(2)">驳 回</el-button>
      </div>
    </el-dialog>


    <!-- 详情按钮页面 append-to-body-->
    <el-dialog :title="details" :visible.sync="detailsOpen" width="1200px" append-to-body >
      <el-row>
        <el-col :span="8"><div class="grid-content"><span>行政区划:</span><span>{{form.administrativeName}}</span></div></el-col>
        <el-col :span="8"><div class="grid-content"><span>行政编码:</span><span>{{form.administrativeCode}}</span></div></el-col>
        <el-col :span="8"><div class="grid-content"><span>路线编码:</span><span>{{form.pathCode}}</span></div></el-col>
      </el-row>
      <el-row>
        <el-col :span="8"><div class="grid-content"><span>村道名称:</span><span>{{form.villageName}}</span></div></el-col>
        <el-col :span="8"><div class="grid-content"><span>养护里程:</span><span>{{form.maintainMileage}}</span></div></el-col>
        <el-col :span="8"><div class="grid-content"><span>沥青路面、水泥混凝土路面、砖路面(km):</span><span>{{form.pitchRoad}}</span></div></el-col>
      </el-row>
      <el-row>
        <el-col :span="8"><div class="grid-content"><span>砂砾路面:</span><span>{{form.gravelRoad}}</span></div></el-col>
        <el-col :span="8"><div class="grid-content"><span>总养护资金(万元):</span><span>{{form.allFund}}</span></div></el-col>
        <el-col :span="8"><div class="grid-content"><span>市养护资金(万元):</span><span>{{form.cityFund}}</span></div></el-col>
      </el-row>
      <el-row>
        <el-col :span="8"><div class="grid-content"><span>县养护资金(万元):</span><span>{{form.countyFund}}</span></div></el-col>
        <el-col :span="8"><div class="grid-content"></div></el-col>
        <el-col :span="8"><div class="grid-content"></div></el-col>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
import { listOrdinary, getOrdinary, delOrdinary, addOrdinary, updateOrdinary } from "@/api/system/ordinary";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import { listDept } from "@/api/system/dept";
import {listInformation} from "@/api/system/information";

export default {
  name: "Ordinary",
  dicts: ['audit_status'],
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
      // 一般养护表格数据
      ordinaryList: [],
      // 一般养护树选项
      ordinaryOptions: [],
      listInformation: [] ,
      // 弹出层标题
      title: "",
      details: "",
      auidTitle: "",
      // 是否显示弹出层
      open: false,
      openAuit: false,
      // 详情是否显示弹出层
      detailsOpen: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        administrative: null,
        pathCode: null,
        villageName: null,
        maintainMileage: null,
        pitchRoad: null,
        gravelRoad: null,
        allFund: null,
        cityFund: null,
        countyFund: null,
        auditStatusid: null,
        administrativeName: null,
        administrativeCode: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        administrative: [
          { required: true, message: "行政区不能为空", trigger: "change" }
        ],
        pathCode: [
          { required: true, message: "路线编码不能为空", trigger: "change" }
        ],
        villageName: [
          { required: true, message: "村道名称不能为空", trigger: "blur" }
        ],
        maintainMileage: [
          { type: 'number', message: '养护里程必须为数字值', trigger: "blur"}
        ],
        pitchRoad: [
          { type: 'number', message: '沥青路面、水泥混凝土路面、砖路面必须为数字值', trigger: "blur"}
        ],
        gravelRoad: [
          { type: 'number', message: '砂砾路面必须为数字值', trigger: "blur"}
        ],
        allFund: [
          { type: 'number', message: "总养护资金必须为数字值", trigger: "blur" }
        ],
        cityFund: [
          { type: 'number', message: "市养护资金必须为数字值", trigger: "blur" }
        ],
        countyFund: [
          { type: 'number', message: "县养护资金必须为数字值", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询一般养护列表 */
    getList() {
      this.loading = true;
      listOrdinary(this.queryParams).then(response => {
        this.ordinaryList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
        this.ordinaryOptions = [];
        // const data = { deptId: 0, deptName: '大陆', children: [] };
        this.ordinaryOptions = this.handleTree(response.data, "deptId", "parentId");
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
        mpoid: null,
        administrative: null,
        pathCode: null,
        villageName: null,
        maintainMileage: null,
        pitchRoad: null,
        gravelRoad: null,
        allFund: null,
        cityFund: null,
        countyFund: null,
        auditStatusid: null,
        auditPeople: null,
        time: null,
        message: null,
        administrativeName: null,
        administrativeCode: null
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
      this.ids = selection.map(item => item.mpoid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 下拉查询路线 */
    getListInformation(){
      listInformation(null).then(response=>{
        this.listInformation=response.rows;
      });
    },
    /** 详情按钮操作 */
    handleOne(row) {
      this.reset();
      const mpoid = row.mpoid || this.ids
      getOrdinary(mpoid).then(response => {
        this.form = response.data;
        this.detailsOpen = true;
        this.details = "养护单位详情";
      });
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.getListInformation();
      this.getTreeselect();
      this.open = true;
      this.title = "添加一般养护";
    },
    /** 审核按钮 */
    handleAudit(row){
      this.reset();
      const mpoid = row.mpoid || this.ids
      getOrdinary(mpoid).then(response => {
        this.form = response.data;
        this.openAuit = true;
        this.auidTitle = "审核";
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getListInformation();
      this.getTreeselect();
      const mpoid = row.mpoid || this.ids
      getOrdinary(mpoid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改一般养护";
      });
    },
    /** 提交按钮 */
    submitForm(statusId) {
      this.$refs["form"].validate(valid => {
        if (statusId !== 0) {
          this.form.auditPeople = this.$store.state.user.name;
          this.form.time = new Date();
        }
        this.form.auditStatusid=statusId;
        if (valid) {
          if (this.form.mpoid != null) {
            updateOrdinary(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.openAuit = false;
              this.getList();
            });
          } else {
            addOrdinary(this.form).then(response => {
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
      const mpoids = row.mpoid || this.ids;
      this.$modal.confirm('是否确认删除一般养护编号为"' + mpoids + '"的数据项？').then(function() {
        return delOrdinary(mpoids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/ordinary/export', {
        ...this.queryParams
      }, `ordinary_${new Date().getTime()}.xlsx`)
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

.custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 8px;
}
</style>
