<template>
  <div class="app-container">
    <div :style="{display:this.flag}">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="标识标牌名称" prop="labelName">
        <el-input
          v-model="queryParams.labelName"
          placeholder="请输入标识标牌名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="行政区域" prop="administrativeRegion">
          <treeselect style="width: 200px" v-model="queryParams.administrativeRegion" :options="ordinaryOptions" :normalizer="normalizer" placeholder="请选择行政区" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    </div>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:signage:add']"
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
          v-hasPermi="['system:signage:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:signage:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="signageList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="标识标牌名称" align="center" prop="labelName" />
      <el-table-column label="行政区域" align="center" prop="administrativeRegion" />
      <el-table-column label="标识标牌经度" align="center" prop="markTheLongitudeSign" />
      <el-table-column label="标识标牌纬度" align="center" prop="labelLatitude" />
      <el-table-column label="优先通达路线名称" align="center" prop="nameOfPriorityRoute" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-finished"
            @click="getSignageXq(scope.row)"
            v-hasPermi="['system:signage:edit']"
          >详情</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:signage:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:signage:remove']"
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
    <el-dialog :title="title" :visible.sync="openXq" width="1000px" append-to-body>
      <el-tabs v-model="activeName" type="card">
        <el-tab-pane label="标识标牌信息详情" name="first">
          <el-descriptions :model="form">
            <el-descriptions-item label="标识标牌名称">{{ form.labelName }}</el-descriptions-item>
            <el-descriptions-item label="行政区域">{{ form.administrativeRegion }}</el-descriptions-item>
            <el-descriptions-item label="标识标牌经度">{{ form.markTheLongitudeSign }}</el-descriptions-item>

            <el-descriptions-item label="标识标牌纬度">{{ form.labelLatitude }}</el-descriptions-item>
            <el-descriptions-item label="优先通达路线名称">{{ form.nameOfPriorityRoute }}</el-descriptions-item>
            <el-descriptions-item label="优先通达路线编码">{{ form.priorityAccessRouteCode }}</el-descriptions-item>
            <el-descriptions-item label="介绍">{{ form.introduce }}</el-descriptions-item>
          </el-descriptions>
        </el-tab-pane>
      </el-tabs>
    </el-dialog>
    <!-- 添加或修改标识标牌对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" style="display: flex;flex-wrap: wrap;justify-content: space-between">
        <el-form-item label="路线名称" prop="routeid" :style="{display:updateDis}">
          <el-select placeholder="请选择"  v-model="selectValue">
            <el-option
              v-for="item in routeList"
              :key="item.id"
              :label="item.routeName"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="标识标牌名称" prop="labelName">
          <el-input v-model="form.labelName" placeholder="请输入标识标牌名称" />
        </el-form-item>
        <el-form-item label="行政区域" prop="administrativeRegion">
          <el-input v-model="form.administrativeRegion" placeholder="请输入行政区域" />
        </el-form-item>

        <el-form-item label="标识标牌经度" prop="markTheLongitudeSign">
          <el-input v-model="form.markTheLongitudeSign" placeholder="请输入标识标牌经度" />
        </el-form-item>
        <el-form-item label="标识标牌纬度" prop="labelLatitude">
          <el-input v-model="form.labelLatitude" placeholder="请输入标识标牌纬度" />
        </el-form-item>
        <el-form-item label="优先通达路线名称" prop="nameOfPriorityRoute">
          <el-input v-model="form.nameOfPriorityRoute" placeholder="请输入优先通达路线名称" />
        </el-form-item>
        <el-form-item label="优先通达路线编码" prop="priorityAccessRouteCode">
          <el-input v-model="form.priorityAccessRouteCode" placeholder="请输入优先通达路线编码" />
        </el-form-item>
        <el-form-item label="介绍" prop="introduce">
          <el-input v-model="form.introduce" type="textarea" placeholder="请输入内容" />
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
import { listSignage, getSignage, delSignage, addSignage, updateSignage } from "@/api/system/signage";
import {listInformation} from "@/api/system/information";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import {listDept} from "@/api/system/dept";

export default {
  props:["vis","routeId"],
  name: "Signage",
  data() {
    return {
      ordinaryOptions: [],
      activeName:'first',
      updateDis:'',
      flag :this.vis,
      //绑定下拉列表选中数据
      selectValue:'',
      //路线列表
      routeList:[
        {
          id:null,
          routeName:null
        }
      ],
      openXq:false,
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
      // 标识标牌表格数据
      signageList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        routeid:this.$props.routeId,
        labelName: null,
        administrativeRegion: null,
        priorityAccessRouteCode: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  components: {
    Treeselect
  },
  created() {
    this.getTreeselect();
    this.getList();
  },
  methods: {
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
    /** 查询标识标牌详情 */
    getSignageXq(row){
      const identificationTagId = row.identificationTagId || this.ids
      getSignage(identificationTagId).then(response => {
        this.form = response.data;
        this.openXq = true;
        this.title = "标识标牌信息详情";
      });
    },
    /** 查询标识标牌列表 */
    getList() {
      this.loading = true;
      listSignage(this.queryParams).then(response => {
        this.signageList = response.rows;
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
        identificationTagId: null,
        routeid: null,
        labelName: null,
        administrativeRegion: null,
        markTheLongitudeSign: null,
        labelLatitude: null,
        nameOfPriorityRoute: null,
        priorityAccessRouteCode: null,
        introduce: null
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
      this.ids = selection.map(item => item.identificationTagId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.updateDis="block";
      this.reset();
      this.open = true;
      /** 查找路线列表的路线名称并展示*/
      this.getRouteList();
      this.title = "添加标识标牌";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.updateDis="none";
      const identificationTagId = row.identificationTagId || this.ids
      getSignage(identificationTagId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改标识标牌";
      });
    },
    /** 查询路线列表列表 */
    getRouteList() {
      listInformation().then(response => {
        this.routeList=response.rows
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.identificationTagId != null) {
            updateSignage(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.routeid=this.selectValue
            addSignage(this.form).then(response => {
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
      const identificationTagIds = row.identificationTagId || this.ids;
      this.$modal.confirm('是否确认删除标识标牌编号为"' + identificationTagIds + '"的数据项？').then(function() {
        return delSignage(identificationTagIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/signage/export', {
        ...this.queryParams
      }, `signage_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
