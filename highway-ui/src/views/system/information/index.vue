<template>
  <div class="bigBox">
  <div class="app-container smallBox">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="路线名称" prop="routeName">
        <el-input
          v-model="queryParams.routeName"
          placeholder="请输入路线名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="建设类型" prop="typeConstruction">
        <el-select v-model="queryParams.typeConstruction" placeholder="请选择建设类型" clearable>
          <el-option
            v-for="dict in dict.type.construction_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="路线类型" prop="routeType">
        <el-select v-model="queryParams.routeType" placeholder="请选择路线类型" clearable>
          <el-option
            v-for="dict in dict.type.route_type"
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
          v-hasPermi="['system:information:add']"
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
          v-hasPermi="['system:information:edit']"
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
          v-hasPermi="['system:information:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:information:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="informationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="路线编码" align="center" prop="routeCoding" />
      <el-table-column label="路线名称" align="center" prop="routeName" />
      <el-table-column label="行政区域" align="center" prop="adminiStrative" />
      <el-table-column label="建设类型" align="center" prop="typeConstruction">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.construction_type" :value="scope.row.typeConstruction"/>
        </template>
      </el-table-column>
      <el-table-column label="路线里程" align="center" prop="routeMileage" />
      <el-table-column label="路线类型" align="center" prop="routeType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.route_type" :value="scope.row.routeType"/>
        </template>
      </el-table-column>
      <el-table-column label="起点名称" align="center" prop="startingPointname" />
      <el-table-column label="终点名称" align="center" prop="endPointName" />
<!--      <el-table-column label="起点经度" align="center" prop="startingPointLongitude" />-->
<!--      <el-table-column label="终点经度" align="center" prop="endPointLongitude" />-->
<!--      <el-table-column label="起点纬度" align="center" prop="startLatitude" />-->
<!--      <el-table-column label="终点纬度" align="center" prop="endLatitude" />-->
<!--      <el-table-column label="起点桩号" align="center" prop="startingPointStation" />-->
<!--      <el-table-column label="终点桩号" align="center" prop="endPointStation" />-->
<!--      <el-table-column label="路段数量" align="center" prop="numberRoadSegments" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">

          <el-button
            size="mini"
            type="text"
            icon="el-icon-finished"
            @click="getMenuIs(scope.row)"
            v-hasPermi="['system:information:edit']"
          >详情</el-button>

          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:information:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:information:remove']"
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

    <!-- 添加或修改路线列表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="路线名称" prop="routeName">
          <el-input v-model="form.routeName" placeholder="请输入路线名称" />
        </el-form-item>
        <el-form-item label="路线编码" prop="routeCoding">
          <el-input v-model="form.routeCoding" placeholder="请输入路线编码" />
        </el-form-item>
        <el-form-item label="行政区域" prop="adminiStrative">
          <el-input v-model="form.adminiStrative" placeholder="请输入行政区域" />
        </el-form-item>
        <el-form-item label="建设类型" prop="typeConstruction">
          <el-select v-model="form.typeConstruction" placeholder="请选择建设类型">
            <el-option
              v-for="dict in dict.type.construction_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="路线里程" prop="routeMileage">
          <el-input v-model="form.routeMileage" placeholder="请输入路线里程" />
        </el-form-item>
        <el-form-item label="路线类型" prop="routeType">
          <el-select v-model="form.routeType" placeholder="请选择路线类型">
            <el-option
              v-for="dict in dict.type.route_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="起点名称" prop="startingPointname">
          <el-input v-model="form.startingPointname" placeholder="请输入起点名称" />
        </el-form-item>
        <el-form-item label="终点名称" prop="endPointName">
          <el-input v-model="form.endPointName" placeholder="请输入终点名称" />
        </el-form-item>
        <el-form-item label="起点经度" prop="startingPointLongitude">
          <el-input v-model="form.startingPointLongitude" placeholder="请输入起点经度" />
        </el-form-item>
        <el-form-item label="终点经度" prop="endPointLongitude">
          <el-input v-model="form.endPointLongitude" placeholder="请输入终点经度" />
        </el-form-item>
        <el-form-item label="起点纬度" prop="startLatitude">
          <el-input v-model="form.startLatitude" placeholder="请输入起点纬度" />
        </el-form-item>
        <el-form-item label="终点纬度" prop="endLatitude">
          <el-input v-model="form.endLatitude" placeholder="请输入终点纬度" />
        </el-form-item>
        <el-form-item label="起点桩号" prop="startingPointStation">
          <el-input v-model="form.startingPointStation" placeholder="请输入起点桩号" />
        </el-form-item>
        <el-form-item label="终点桩号" prop="endPointStation">
          <el-input v-model="form.endPointStation" placeholder="请输入终点桩号" />
        </el-form-item>
        <el-form-item label="路段数量" prop="numberRoadSegments">
          <el-input v-model="form.numberRoadSegments" placeholder="请输入路段数量" />
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
import { listInformation, getInformation, delInformation, addInformation, updateInformation } from "@/api/system/information";

export default {
  name: "Information",
  dicts: ['construction_type', 'route_type'],
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
      // 路线列表表格数据
      informationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        routeName: null,
        routeCoding: null,
        adminiStrative: null,
        typeConstruction: null,
        routeMileage: null,
        routeType: null,
        startingPointname: null,
        endPointName: null,
        startingPointLongitude: null,
        endPointLongitude: null,
        startLatitude: null,
        endLatitude: null,
        startingPointStation: null,
        endPointStation: null,
        numberRoadSegments: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getMenuIs(row){
      this.$router.push({path:'/basics/xiangqing',query: {id:row.id}})
    },
    /** 查询路线列表列表 */
    getList() {
      this.loading = true;
      listInformation(this.queryParams).then(response => {
        this.informationList = response.rows;
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
        routeName: null,
        routeCoding: null,
        adminiStrative: null,
        typeConstruction: null,
        routeMileage: null,
        routeType: null,
        startingPointname: null,
        endPointName: null,
        startingPointLongitude: null,
        endPointLongitude: null,
        startLatitude: null,
        endLatitude: null,
        startingPointStation: null,
        endPointStation: null,
        numberRoadSegments: null
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
      this.title = "添加路线列表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getInformation(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改路线列表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateInformation(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInformation(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除路线列表编号为"' + ids + '"的数据项？').then(function() {
        return delInformation(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/information/export', {
        ...this.queryParams
      }, `information_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
<style>

</style>
