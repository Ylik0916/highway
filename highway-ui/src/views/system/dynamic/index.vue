<template>
  <div class="bigBox">
    <div class="smallBox">

  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="110px">
      <el-form-item label="养护路线名称" prop="pathName">
        <el-input
          v-model="queryParams.pathName"
          placeholder="请输入养护路线名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="养护路线编号" prop="pathCode">
        <el-input
          v-model="queryParams.pathCode"
          placeholder="请输入养护路线编号"
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
          v-hasPermi="['system:dynamic:add']"
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
          v-hasPermi="['system:dynamic:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:dynamic:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="dynamicList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="" align="center" prop="mdid" />-->
      <el-table-column label="养护人" align="center" prop="maintainPeople" />
<!--      <el-table-column label="养护单位" align="center" prop="maintainUnit" />-->
      <el-table-column label="养护路线名称" align="center" prop="pathName" />
      <el-table-column label="养护路线编号" align="center" prop="pathCode" />
      <el-table-column label="开始桩号" align="center" prop="beginStake" />
      <el-table-column label="结束桩号" align="center" prop="overStake" />
      <el-table-column label="养护时间" align="center" prop="maintainTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.maintainTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-finished"
            @click="handleOne(scope.row)"
            v-hasPermi="['system:dynamic:query']"
          >详情</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:dynamic:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:dynamic:remove']"
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

    <!-- 添加或修改动态养护管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1100px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-position="top" label-width="90px">
        <el-row :gutter="20">
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="路线编号" prop="pathName">
                <el-select v-model="form.pathName" value-key="id" style="width: 100%" placeholder="请选择">
                  <el-option
                    v-for="item in listInformation"
                    :key="item.id"
                    :label="item.routeName"
                    :value="item">
<!--          :label="item.routeName"          -->
                    <span style="">{{ item.routeName+"("+item.routeCoding+")" }}</span>
                  </el-option>
                </el-select>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="养护人" prop="maintainPeople">
                <el-input v-model="form.maintainPeople" autosize placeholder="请输入养护人" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="养护时间" prop="maintainTime">
                <el-date-picker style="width: 100%"
                  clearable
                  v-model="form.maintainTime"
                  type="date"
                  value-format="yyyy-MM-dd"
                  placeholder="请选择养护时间">
                </el-date-picker>
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="8">
            <div class="grid-content ">
              <el-form-item label="车牌号" prop="licensePlate">
                <el-input v-model="form.licensePlate" placeholder="请输入车牌号" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content ">
              <el-form-item label="开始桩号" prop="beginStake">
                <el-input v-model="form.beginStake" placeholder="请输入开始桩号" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content ">
              <el-form-item label="结束桩号" prop="overStake">
                <el-input v-model="form.overStake" placeholder="请输入结束桩号" />
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="24">
            <div class="grid-content ">
              <el-form-item label="养护内容">
                <el-input v-model="form.maintainContent" type="textarea" placeholder="请输入养护内容"/>
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="24">
            <div class="grid-content ">
              <el-form-item label="养护附件" prop="maintainAccessory">
                <file-upload v-model="form.maintainAccessory"/>
              </el-form-item>
            </div>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!--    -->
    <el-dialog :title="details" :visible.sync="detailsOpen" width="1200px" append-to-body>
      <h3 style="font-weight: bold">基础信息</h3>
      <el-row>
        <el-col :span="8"><div class="grid-content"><span>行政区划:</span><span>{{form.administrative}}</span></div></el-col>
        <el-col :span="8"><div class="grid-content"><span>行政编码:</span><span>{{form.administrativeCode}}</span></div></el-col>
        <el-col :span="8"><div class="grid-content"><span>养护人:</span><span>{{form.maintainPeople}}</span></div></el-col>
      </el-row>
      <el-row>
        <el-col :span="8"><div class="grid-content"><span>养护时间:</span><span>{{form.maintainTime}}</span></div></el-col>
        <el-col :span="8"><div class="grid-content"><span>养护单位:</span><span>{{form.maintainUnit}}</span></div></el-col>
        <el-col :span="8"><div class="grid-content"><span>养护路线名称:</span><span>{{form.pathName}}</span></div></el-col>
      </el-row>
      <el-row>
        <el-col :span="8"><div class="grid-content"><span>养护路线编号:</span><span>{{form.pathCode}}</span></div></el-col>
        <el-col :span="8"><div class="grid-content"><span>车牌号:</span><span>{{form.licensePlate}}</span></div></el-col>
        <el-col :span="8"><div class="grid-content"><span>开始桩号:</span><span>{{form.beginStake}}</span></div></el-col>
      </el-row>
      <el-row>
        <el-col :span="8"><div class="grid-content"><span>结束时桩号:</span><span>{{form.overStake}}</span></div></el-col>
        <el-col :span="8"><div class="grid-content"><span>养护内容:</span><span>{{form.maintainContent}}</span></div></el-col>
        <el-col :span="8"><div class="grid-content"><span></span><span></span></div></el-col>
      </el-row>
      <h3 style="font-weight: bold">养护附件</h3>
      <el-row>
        <el-col :span="24"><div class="grid-content">{{form.maintainAccessory}}</div></el-col>
      </el-row>
    </el-dialog>
  </div>

    </div>
  </div>
</template>

<script>
import { listDynamic, getDynamic, delDynamic, addDynamic, updateDynamic } from "@/api/system/dynamic";
import {listInformation} from "@/api/system/information";

export default {
  name: "Dynamic",
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
      // 动态养护管理表格数据
      dynamicList: [],
      listInformation: [],
      // 弹出层标题
      title: "",
      details: "",
      // 是否显示弹出层
      open: false,
      // 详情是否显示弹出层
      detailsOpen: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        administrative: null,
        administrativeCode: null,
        maintainPeople: null,
        maintainTime: null,
        maintainUnit: null,
        pathid: null,
        pathName: null,
        pathCode: null,
        licensePlate: null,
        beginStake: null,
        overStake: null,
        maintainContent: null,
        maintainAccessory: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        pathName: [
          { required: true, message: "养护路线不能为空", trigger: "blur" }
        ],
        maintainPeople: [
          { required: true, message: "养护人不能为空", trigger: "blur" }
        ],
        maintainTime: [
          { required: true, message: "养护时间不能为空", trigger: "blur" }
        ],
        maintainAccessory: [
          { required: true, message: "行政区不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询动态养护管理列表 */
    getList() {
      this.loading = true;
      listDynamic(this.queryParams).then(response => {
        this.dynamicList = response.rows;
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
        mdid: null,
        administrative: null,
        administrativeCode: null,
        maintainPeople: null,
        maintainTime: null,
        maintainUnit: null,
        pathid: null,
        pathName: null,
        pathCode: null,
        licensePlate: null,
        beginStake: null,
        overStake: null,
        maintainContent: null,
        maintainAccessory: null
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
    /** 下拉查询路线 */
    getListInformation(){
      listInformation(null).then(response=>{
        this.listInformation=response.rows;
      });
    },
    /** 详情按钮操作 */
    handleOne(row) {
      this.reset();
      const mdid = row.mdid || this.ids
      getDynamic(mdid).then(response => {
        this.form = response.data;
        this.detailsOpen = true;
        this.details = "养护单位详情";
      });
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.mdid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.getListInformation();
      this.open = true;
      this.title = "添加动态养护管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getListInformation();
      const mdid = row.mdid || this.ids
      getDynamic(mdid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改动态养护管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        let routeItem = this.form.pathName;
        this.form.pathCode=routeItem.routeCoding;
        this.form.pathName=routeItem.routeName;
        this.form.pathid=routeItem.id;
        this.form.administrative=routeItem.adminiStrative;
        if (valid) {
          if (this.form.mdid != null) {
            updateDynamic(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDynamic(this.form).then(response => {
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
      const mdids = row.mdid || this.ids;
      this.$modal.confirm('是否确认删除动态养护管理编号为"' + mdids + '"的数据项？').then(function() {
        return delDynamic(mdids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/dynamic/export', {
        ...this.queryParams
      }, `dynamic_${new Date().getTime()}.xlsx`)
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
</style>
