<template>
  <div class="app-container">
    <div id="one">
      <div id="main"></div>
    </div>

    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="桥梁名称" prop="routeName">
        <el-input
          v-model="queryParams.routeName"
          placeholder="请输入桥梁名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="桥梁编码" prop="routeCode">
        <el-input
          v-model="queryParams.routeCode"
          placeholder="请输入桥梁编码"
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
          v-hasPermi="['system:quality:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:quality:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:quality:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:quality:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="qualityList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="桥梁名称" align="center" prop="routeName"/>
      <el-table-column label="桥梁编码" align="center" prop="routeCode"/>
      <el-table-column label="DR" align="center" prop="routeDr"/>
      <el-table-column label="桥梁技术状况指数评定" align="center">
        <!--      <el-table-column label="桥梁质量id" align="center" prop="routeQualityId" />-->
        <el-table-column label="SPCI" align="center" prop="routeTopScore"/>
        <el-table-column label="SBCI" align="center" prop="routeBottomScore"/>
        <el-table-column label="BDCI" align="center" prop="routeFaceScore"/>
      </el-table-column>
      <el-table-column label="评定时间" align="center" prop="routeScoreDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.routeScoreDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handledetails(scope.row)"
            v-hasPermi="['system:infdormation:edit']"
          >详情
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:quality:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:quality:remove']"
          >删除
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
    <el-dialog :title="title" :visible.sync="opens" width="1000px" append-to-body>
      <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
        <el-tab-pane label="基本信息" name="first">
          <el-descriptions :model="form">
            <el-descriptions-item label="路线编码">{{ form.luCode }}</el-descriptions-item>
            <el-descriptions-item label="路线名称">{{ form.selectRoute }}</el-descriptions-item>
            <el-descriptions-item label="桥梁编号">{{ form.routeCode }}</el-descriptions-item>
            <el-descriptions-item label="桥梁名称">{{ form.routeName }}</el-descriptions-item>
            <el-descriptions-item label="行政区域">{{ form.routeAdministrativeArea }}</el-descriptions-item>
            <el-descriptions-item label="中心桩号">{{ form.centerStake }}</el-descriptions-item>
            <el-descriptions-item label="桥梁经度">{{ form.routeLongitude }}</el-descriptions-item>
            <el-descriptions-item label="桥梁纬度">{{ form.routeLatitude }}</el-descriptions-item>
            <el-descriptions-item label="桥梁高程">{{ form.routeHigh }}</el-descriptions-item>
            <el-descriptions-item label="桥梁长度(米)">{{ form.routeLong }}</el-descriptions-item>
            <el-descriptions-item label="跨径总长">{{ form.routeCrossLong }}</el-descriptions-item>
            <el-descriptions-item label="桥梁全长(米)">{{ form.routeOverallLength }}</el-descriptions-item>
            <el-descriptions-item label="桥面净宽(米)">{{ form.routeClearWidth }}</el-descriptions-item>
            <el-descriptions-item label="设计荷载">{{ form.routeLoad }}</el-descriptions-item>
            <el-descriptions-item label="按跨径分">{{ form.routeCross }}</el-descriptions-item>
            <el-descriptions-item label="按建筑材料和使用年限分">{{ form.routeYear }}</el-descriptions-item>
            <el-descriptions-item label="是否危桥">{{ form.routeDanger }}</el-descriptions-item>
            <el-descriptions-item label="技术状况评定">{{ form.routeGrade }}</el-descriptions-item>
            <el-descriptions-item label="评定日期">{{ form.routeEvaluationDate }}</el-descriptions-item>
            <el-descriptions-item label="主桥上部构造结构形式">{{ form.routeTopShape }}</el-descriptions-item>
            <el-descriptions-item label="上部结构材料">{{ form.routeTopMaterial }}</el-descriptions-item>
            <el-descriptions-item label="立交桥类别">{{ form.routeType }}</el-descriptions-item>
            <el-descriptions-item label="桥墩类型">{{ form.routePierType }}</el-descriptions-item>
            <el-descriptions-item label="收费性质">{{ form.routeCharge }}</el-descriptions-item>
            <el-descriptions-item label="原桥梁编码">{{ form.routeBeforeCode }}</el-descriptions-item>
            <el-descriptions-item label="建成时间">{{ form.routeCompletionTime }}</el-descriptions-item>
            <el-descriptions-item label="跨越地物类型">{{ form.routeCrossFigure }}</el-descriptions-item>
            <el-descriptions-item label="跨越地物名称">{{ form.routeCrossName }}</el-descriptions-item>
            <el-descriptions-item label="主要病害位置">{{ form.routeDiseasePlace }}</el-descriptions-item>
            <el-descriptions-item label="主要病害描述">{{ form.routeDiseaseDescribe }}</el-descriptions-item>
            <el-descriptions-item label="墩台防撞设施类型">{{ form.routeAnticollisionType }}</el-descriptions-item>
            <el-descriptions-item label="抗震等级">{{ form.routeAntiseismic }}</el-descriptions-item>
            <el-descriptions-item label="通航等级">{{ form.routeFlight }}</el-descriptions-item>
            <el-descriptions-item label="改建年度">{{ form.routeRebuild }}</el-descriptions-item>
            <el-descriptions-item label="改造完工日期">{{ form.routeRebuildEnd }}</el-descriptions-item>
            <el-descriptions-item label="改造部位">{{ form.routeRebuildPlace }}</el-descriptions-item>
            <el-descriptions-item label="最近改造工程性质">{{ form.routeRebuildNature }}</el-descriptions-item>
            <el-descriptions-item label="是否部补助项目">{{ form.routeSubsidy }}</el-descriptions-item>
            <el-descriptions-item label="已采取交通管制措施">{{ form.routeMethod }}</el-descriptions-item>
            <el-descriptions-item label="桥梁所在位置">{{ form.routePosition }}</el-descriptions-item>
            <el-descriptions-item label="是否宽路窄桥">{{ form.routeWroadNbridge }}</el-descriptions-item>
            <el-descriptions-item label="是否在长大桥梁目录">{{ form.routeLongBridges }}</el-descriptions-item>
          </el-descriptions>
        </el-tab-pane>
        <el-tab-pane label="建养信息" name="second">
          <el-descriptions :model="form">
            <el-descriptions-item label="下部结构形式">{{ form.routeBottomShape }}</el-descriptions-item>
            <el-descriptions-item label="导入评定结果">{{ form.routeResult }}</el-descriptions-item>
            <el-descriptions-item label="变更原因">{{ form.routeReason }}</el-descriptions-item>
            <el-descriptions-item label="支座类型">{{ form.routeSupport }}</el-descriptions-item>
            <el-descriptions-item label="通车日期">{{ form.routeTrafficDate }}</el-descriptions-item>
            <el-descriptions-item label="评定单位">{{ form.evaluationUnit }}</el-descriptions-item>
            <el-descriptions-item label="改造施工单位">{{ form.routeRenovationUnit }}</el-descriptions-item>
            <el-descriptions-item label="桥台类型">{{ form.routeAbutmentType }}</el-descriptions-item>
            <el-descriptions-item label="设计单位名称">{{ form.routeDesigner }}</el-descriptions-item>
            <el-descriptions-item label="施工单位名称">{{ form.routeConstructionUnit }}</el-descriptions-item>
            <el-descriptions-item label="监理单位名称">{{ form.routeInspectionUnit }}</el-descriptions-item>
            <el-descriptions-item label="建设单位名称">{{ form.routeBuildUnit }}</el-descriptions-item>
            <el-descriptions-item label="监管单位名称">{{ form.routeSupervise }}</el-descriptions-item>
            <el-descriptions-item label="管养单位性质">{{ form.routeCuring }}</el-descriptions-item>
            <el-descriptions-item label="管养单位代码">{{ form.routeCuringCode }}</el-descriptions-item>
            <el-descriptions-item label="管养单位名称">{{ form.routeCuringName }}</el-descriptions-item>
          </el-descriptions>
        </el-tab-pane>
        <el-tab-pane label="填报信息" name="third">
          <el-descriptions :model="form">
            <el-descriptions-item label="填报单位">{{ form.fillingUnit }}</el-descriptions-item>
            <el-descriptions-item label="填报单位代码">{{ form.fillingUnitCode }}</el-descriptions-item>
            <el-descriptions-item label="填报单位负责人">{{ form.fillingUnitLeader }}</el-descriptions-item>
            <el-descriptions-item label="填表人">{{ form.preparer }}</el-descriptions-item>
            <el-descriptions-item label="填表人电话">{{ form.preparerTel }}</el-descriptions-item>
            <el-descriptions-item label="审核人">{{ form.process }}</el-descriptions-item>
            <el-descriptions-item label="审核人电话">{{ form.processTel }}</el-descriptions-item>
            <el-descriptions-item label="采集时间">{{ form.acquisitionTime }}</el-descriptions-item>
            <el-descriptions-item label="修改时间">{{ form.updateTime }}</el-descriptions-item>
            <el-descriptions-item label="备注">{{ form.remarks }}</el-descriptions-item>
          </el-descriptions>
        </el-tab-pane>
      </el-tabs>
    </el-dialog>
    <!-- 添加或修改桥梁质量对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="上部结构评分SPCI" prop="routeTopScore">
          <el-input v-model="form.routeTopScore" placeholder="请输入上部结构评分SPCI"/>
        </el-form-item>
        <el-form-item label="下部结构评分SBCI" prop="routeBottomScore">
          <el-input v-model="form.routeBottomScore" placeholder="请输入下部结构评分SBCI"/>
        </el-form-item>
        <el-form-item label="桥面系评分BDCI" prop="routeFaceScore">
          <el-input v-model="form.routeFaceScore" placeholder="请输入桥面系评分BDCI"/>
        </el-form-item>
        <el-form-item label="评定时间" prop="routeScoreDate">
          <el-date-picker clearable
                          v-model="form.routeScoreDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择评定时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="评定单位" prop="routeCompany">
          <el-input v-model="form.routeCompany" placeholder="请输入评定单位"/>
        </el-form-item>
        <el-form-item label="桥梁名称" prop="routeName">
          <el-input v-model="form.routeName" placeholder="请输入桥梁名称"/>
        </el-form-item>
        <el-form-item label="桥梁编码" prop="routeCode">
          <el-input v-model="form.routeCode" placeholder="请输入桥梁编码"/>
        </el-form-item>
        <el-form-item label="行政区划" prop="routeAdministrativeArea">
          <el-input v-model="form.routeAdministrativeArea" placeholder="请输入行政区划"/>
        </el-form-item>
        <el-form-item label="技术状况得分DR" prop="routeDr">
          <el-input v-model="form.routeDr" placeholder="请输入技术状况得分DR"/>
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
import {listQuality, getQuality, delQuality, addQuality, updateQuality} from "@/api/system/quality";
import * as echarts from "echarts";
import {getInfdormation} from "@/api/system/infdormation";

export default {
  mounted() {
    // echarts仅有一个方法init，执行init时传入一个具备大小
    // （如果没有指定容器的大小将会按照0大小来处理即无法看到图标）的dom节点后即可实例化出图表对象，图标库实现为多实例的，
    // 同一页面可多次init出多个图表
    var myEcharts = echarts.init(document.getElementById("main"));
    myEcharts.setOption({
      title: {
        text: "北戴河区桥梁质量",
        textStyle: {
          color: 'blue',

        },
        // backgroundColor:'',
        link: "http://www.baidu.com",
        target: 'self',
        // borderColor:'red',
        // borderWidth:5,
        x: 'center',


      },
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: "shadow",
        },
        backgroundColor: 'yellow',
        borderColor: 'black',
        borderWidth: 2,
        textStyle: {
          color: 'blue',
        },
        formatter(params) {
          console.log(params)
          for (var i = 0; i < params.length; i++) {
            return params[i].axisValue + "桥的数量：" + params[i].data.value
          }
        }
      },

      legend: {
        show: true,//设置图例的开启或者关闭
        // icon:'circle',//设置图例的形状
        right: '0%', //设置位置

        //设置宽高
        itemWidth: 20,
        itemHeight: 20,
        textStyle: {
          color: 'blue',
          fontSize: 15,

        }
      },
      xAxis: {
        data: ["优", "良", "中", "次", "差"],
      },
      yAxis: {},
      series: {
        name: "数量",
        type: "bar",
        data: [
          {"value": "5"},
          {"value": "10"},
          {"value": "15"},
          {"value": "20"},
          {"value": "25"},

        ]
      }
    })

  },
  name: "Quality",
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
      // 桥梁质量表格数据
      qualityList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      opens: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        routeTopScore: null,
        routeBottomScore: null,
        routeFaceScore: null,
        routeScoreDate: null,
        routeCompany: null,
        routeName: null,
        routeCode: null,
        routeAdministrativeArea: null,
        routeDr: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        routeTopScore: [
          {required: true, message: "上部结构评分SPCI不能为空", trigger: "blur"}
        ],
        routeBottomScore: [
          {required: true, message: "下部结构评分SBCI不能为空", trigger: "blur"}
        ],
        routeFaceScore: [
          {required: true, message: "桥面系评分BDCI不能为空", trigger: "blur"}
        ],
        routeScoreDate: [
          {required: true, message: "评定时间不能为空", trigger: "blur"}
        ],
        routeCompany: [
          {required: true, message: "评定单位不能为空", trigger: "blur"}
        ],
        routeName: [
          {required: true, message: "桥梁名称不能为空", trigger: "blur"}
        ],
        routeCode: [
          {required: true, message: "桥梁编码不能为空", trigger: "blur"}
        ],
        routeAdministrativeArea: [
          {required: true, message: "行政区划不能为空", trigger: "blur"}
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询桥梁质量列表 */
    getList() {
      this.loading = true;
      listQuality(this.queryParams).then(response => {
        this.qualityList = response.rows;
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
        routeQualityId: null,
        routeTopScore: null,
        routeBottomScore: null,
        routeFaceScore: null,
        routeScoreDate: null,
        routeCompany: null,
        routeName: null,
        routeCode: null,
        routeAdministrativeArea: null,
        routeDr: null
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
      this.ids = selection.map(item => item.routeQualityId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加桥梁质量";
    },
    /**详情按钮操作*/
    handledetails(row) {
      this.reset();
      const routeQualityId = row.routeQualityId || this.ids
      getQuality(routeQualityId).then(response => {
        this.form = response.data;
        this.opens = true;
        this.title = "桥梁质量";
        console.log(response.data)
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const routeQualityId = row.routeQualityId || this.ids
      getQuality(routeQualityId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改桥梁质量";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.routeQualityId != null) {
            updateQuality(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addQuality(this.form).then(response => {
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
      const routeQualityIds = row.routeQualityId || this.ids;
      this.$modal.confirm('是否确认删除桥梁质量编号为"' + routeQualityIds + '"的数据项？').then(function () {
        return delQuality(routeQualityIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/quality/export', {
        ...this.queryParams
      }, `quality_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

<style>
#main {
  /*border: 1px solid red;*/
  background-color: white;
  width: 300px;
  height: 330px;
  margin-left: 200px;
  margin-bottom: 20px;
}
</style>
