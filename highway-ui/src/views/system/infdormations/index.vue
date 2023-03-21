<template>
  <div class="app-container">
    <div>
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="桥梁编码" prop="routeCode">
          <el-input
            v-model="queryParams.routeCode"
            placeholder="请输入桥梁编码"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="桥梁名称" prop="routeName">
          <el-input
            v-model="queryParams.routeName"
            placeholder="请输入桥梁名称"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="是否危桥" prop="routeDanger">
          <el-select v-model="queryParams.routeDanger" placeholder="请选择是否危桥" clearable>
            <el-option
              v-for="dict in dict.type.underwater_tunnel_or_not"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="行政区划" prop="routeAdministrativeArea">
          <treeselect style="width: 200px" v-model="queryParams.routeAdministrativeArea" :options="ordinaryOptions" :normalizer="normalizer" placeholder="请选择行政区" />
        </el-form-item>
        <el-form-item label="选择路线" prop="luId">
          <el-select v-model="queryParams.luId" filterable placeholder="请输选择路线">
            <el-option
              v-for="item in options"
              :key="item.id"
              :label="item.routeName"
              :value="item.id"/>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
    </div>

    <el-row :gutter="10" class="mb8">
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="infdormationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="路线编码" align="center" prop="luCode"/>
      <el-table-column label="路线名称" align="center" prop="selectRoute"/>
      <el-table-column label="桥梁编码" align="center" prop="routeCode"/>
      <el-table-column label="桥梁名称" align="center" prop="routeName"/>
      <el-table-column label="桥梁长度" align="center" prop="routeLong"/>
      <el-table-column label="桥面全宽" align="center" prop="routeWide"/>
      <el-table-column label="评定等级" align="center" prop="routeGrade">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.technical_evaluation" :value="scope.row.routeGrade"/>
        </template>
      </el-table-column>
      <el-table-column label="跨境分类" align="center" prop="routeCross">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.bridge_cross" :value="scope.row.routeCross"/>
        </template>
      </el-table-column>
      <el-table-column label="行政区划" align="center" prop="routeAdministrativeArea">
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-finished"
            @click="handleSubmit(scope.row)"
          >选择
          </el-button>
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
  </div>
</template>

<script>
import {
  getInfdormation,
  listInfdormation,
} from "@/api/system/infdormation";
import {listDept} from "@/api/system/dept";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import {listInformation} from "@/api/system/information";
export default {
  components: {
    Treeselect
  },
  name: "Infdormation",
  dicts: ['seismic_grade', 'top_shap', 'bottom_shap', 'disease_location', 'transform', 'bearing_type', 'bridge_sort', 'bridge_load', 'sys_yes_no', 'navigation_level', 'management_maintenance', 'technical_evaluation', 'bridge_location', 'change_reason', 'abutment_type', 'bridge_zoning', 'bridge_cross', 'anti_collision_type', 'bridge_age_limit', 'top_material', 'species', 'pier_sort', 'collect_fees', 'reconstruction_part', 'underwater_tunnel_or_not'],
  data() {
    return {
      ordinaryOptions: [],
      labelPosition: 'top',
      //标签页
      activeName: 'first',
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
      // 桥梁信息表格数据
      infdormationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      opens: false,
      openers: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        luId: this.routeId,
        routeCode: null,
        routeName: null,
        routeLongitude: null,
        routeLatitude: null,
        routeHigh: null,
        routeLong: null,
        routeCrossLong: null,
        routeSingleLong: null,
        routeSpanCombination: null,
        routeWide: null,
        routeOverallLength: null,
        routeClearWidth: null,
        routeLoad: null,
        routeCross: null,
        routeYear: null,
        routeDanger: null,
        routeGrade: null,
        routeEvaluationDate: null,
        routeTopShape: null,
        routeTopMaterial: null,
        routeType: null,
        routePierType: null,
        routeCharge: null,
        routeBeforeCode: null,
        routeCompletionTime: null,
        routeCrossFigure: null,
        routeCrossName: null,
        routeDiseasePlace: null,
        routeDiseaseDescribe: null,
        routeAnticollisionType: null,
        routeAntiseismic: null,
        routeFlight: null,
        routeRebuild: null,
        routeRebuildEnd: null,
        routeRebuildPlace: null,
        routeRebuildNature: null,
        routeSubsidy: null,
        routeMethod: null,
        routePosition: null,
        routeWroadNbridge: null,
        routeLongBridges: null,
        routeBottomShape: null,
        routeResult: null,
        routeReason: null,
        routeSupport: null,
        routeTrafficDate: null,
        routeRenovationUnit: null,
        routeAbutmentType: null,
        routeDesigner: null,
        evaluationUnit: null,
        routeConstructionUnit: null,
        routeInspectionUnit: null,
        routeBuildUnit: null,
        routeSupervise: null,
        routeCuring: null,
        routeCuringCode: null,
        routeCuringName: null,
        fillingUnit: null,
        fillingUnitCode: null,
        fillingUnitLeader: null,
        preparer: null,
        preparerTel: null,
        process: null,
        processTel: null,
        acquisitionTime: null,
        remarks: null,
        routeAdministrativeArea: null,
        selectRoute: null,
        centerStake: null,
        luCode: null,
        bridge:null,
        bridgeCode:null,
        qu:null
      },
      // 表单参数
      form: {}
    };
  },
  created() {
    this.getTreeselect();
    this.getList();
    this.getLu();
  },
  methods: {
    //标签页
    handleClick(tab, event) {
      console.log(tab, event);
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
    /** 查询桥梁信息列表 */
    getList() {
      this.loading = true;
      listInfdormation(this.queryParams).then(response => {
        this.infdormationList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getLu() {
      listInformation(this.queryParams).then(response => {
        this.options = response.rows;
      });
    },
    /** 选择桥梁*/
    handleSubmit(row) {
      this.reset();
      const routeId = row.routeId || this.ids
      getInfdormation(routeId).then(response => {
          console.log(response.data)
          this.bridge = response.data.routeName
          this.bridgeCode = response.data.routeCode
          this.qu = response.data.routeAdministrativeArea
          console.log(this.bridge+this.bridgeCode)
          this.$emit('chuan',this.bridge,this.bridgeCode,this.openers,this.qu)
    })
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        routeId: null,
        routeCode: null,
        routeName: null,
        routeLongitude: null,
        routeLatitude: null,
        routeHigh: null,
        routeLong: null,
        routeCrossLong: null,
        routeSingleLong: null,
        routeSpanCombination: null,
        routeWide: null,
        routeOverallLength: null,
        routeClearWidth: null,
        routeLoad: null,
        routeCross: null,
        routeYear: null,
        routeDanger: null,
        routeGrade: null,
        routeEvaluationDate: null,
        routeTopShape: null,
        routeTopMaterial: null,
        routeType: null,
        routePierType: null,
        routeCharge: null,
        routeBeforeCode: null,
        routeCompletionTime: null,
        routeCrossFigure: null,
        routeCrossName: null,
        routeDiseasePlace: null,
        routeDiseaseDescribe: null,
        routeAnticollisionType: null,
        routeAntiseismic: null,
        routeFlight: null,
        routeRebuild: null,
        routeRebuildEnd: null,
        routeRebuildPlace: null,
        routeRebuildNature: null,
        routeSubsidy: null,
        routeMethod: null,
        routePosition: null,
        routeWroadNbridge: null,
        routeLongBridges: null,
        routeBottomShape: null,
        routeResult: null,
        routeReason: null,
        routeSupport: null,
        routeTrafficDate: null,
        routeRenovationUnit: null,
        routeAbutmentType: null,
        routeDesigner: null,
        routeConstructionUnit: null,
        routeInspectionUnit: null,
        routeBuildUnit: null,
        routeSupervise: null,
        routeCuring: null,
        routeCuringCode: null,
        routeCuringName: null,
        fillingUnit: null,
        fillingUnitCode: null,
        fillingUnitLeader: null,
        preparer: null,
        preparerTel: null,
        process: null,
        processTel: null,
        acquisitionTime: null,
        updateTime: null,
        remarks: null,
        routeAdministrativeArea: null,
        selectRoute: null,
        centerStake: null,
        luCode: null
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
      this.ids = selection.map(item => item.routeId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
  }
};
</script>
