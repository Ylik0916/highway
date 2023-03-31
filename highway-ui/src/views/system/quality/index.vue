<template>
  <div class="bigBox">
    <div class="smallBox">
      <div class="app-container">
        <div id="one">
          <div id="main"></div>
        </div>
      </div>
    </div>
    <div class="bigBox">
      <div class="smallBox" style="padding: 25px;">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
                 label-width="68px">
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
            <el-table-column label="SPCI" align="center" prop="routeTopScore"/>
            <el-table-column label="SBCI" align="center" prop="routeBottomScore"/>
            <el-table-column label="BDCI" align="center" prop="routeFaceScore"/>
          </el-table-column>
          <el-table-column label="评定时间" align="center" prop="routeScoreDate" width="180">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.routeScoreDate, '{y}-{m}-{d}') }}</span>
            </template>
          </el-table-column>

          <el-table-column width="150px" label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-finished"
                @click="handlexq(scope.row)"
                v-hasPermi="['system:quality:edit']"
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
        <!--详情页面-->
        <el-dialog :title="title" :visible.sync="opener" width="1000px" append-to-body>
          <el-tabs v-model="activeName" type="card" @tab-click="handlexq">
            <el-tab-pane label="桥梁质量" name="first">
              <el-descriptions :model="form">
                <el-descriptions-item label="桥梁名称">{{ form.routeName }}</el-descriptions-item>
                <el-descriptions-item label="桥梁编码">{{ form.routeCode }}</el-descriptions-item>
                <el-descriptions-item label="行政区划">{{ form.routeAdministrativeArea }}</el-descriptions-item>
                <el-descriptions-item label="上部结构评分SPCI">{{ form.routeTopScore }}</el-descriptions-item>
                <el-descriptions-item label="下部结构评分SBCI">{{ form.routeBottomScore }}</el-descriptions-item>
                <el-descriptions-item label="桥面系评分BDCI">{{ form.routeFaceScore }}</el-descriptions-item>
                <el-descriptions-item label="技术状况得分DR">{{ form.routeDr }}</el-descriptions-item>
                <el-descriptions-item label="评定时间">{{ form.routeScoreDate }}</el-descriptions-item>
                <el-descriptions-item label="评定单位">{{ form.routeCompany }}</el-descriptions-item>
              </el-descriptions>
            </el-tab-pane>
          </el-tabs>
        </el-dialog>
        <!--选择桥梁-->
        <el-dialog :title="title" :visible.sync="openers" width="1500px" append-to-body>
          <Infdormations @chuan="shou"></Infdormations>
        </el-dialog>
        <!-- 添加或修改桥梁质量对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
          <el-form ref="form" :model="form" :rules="rules" label-width="200px" :label-position="labelPosition">
            <el-tabs v-model="activeName" type="card" @tab-click="handleAdd">
              <el-tab-pane label="基本信息" name="first">
                <el-row>
                  <el-col :span="8">
                    <div class="grid-content bg-purple">
                      <el-form-item label="桥梁名称" prop="routeName">
                        <el-input v-model="form.routeName" placeholder="请输入桥梁名称" :disabled="true">
                          {{ bridge }}
                        </el-input>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="8">
                    <div class="grid-content bg-purple">
                      <el-form-item label="桥梁编码" prop="routeCode">
                        <el-input v-model="form.routeCode" placeholder="请输入桥梁编码" :disabled="true">
                          {{ bridgeCode }}
                        </el-input>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="8">
                    <div class="grid-content bg-purple">
                      <el-form-item label="点击选择桥梁">
                        <el-button type="primary" @click="selectRout">选择桥梁</el-button>
                      </el-form-item>

                    </div>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="8">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="下部结构评分SBCI" prop="routeBottomScore">
                        <el-input v-model="form.routeBottomScore" placeholder="请输入下部结构评分SBCI"/>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="8">
                    <div class="grid-content bg-purple">
                      <el-form-item label="桥面系评分BDCI" prop="routeFaceScore">
                        <el-input v-model="form.routeFaceScore" placeholder="请输入桥面系评分BDCI"/>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="8">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="上部结构评分SPCI" prop="routeTopScore">
                        <el-input v-model="form.routeTopScore" placeholder="请输入上部结构评分SPCI"/>
                      </el-form-item>
                    </div>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="8">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="行政区划" prop="routeAdministrativeArea">
                        <el-input v-model="form.routeAdministrativeArea" placeholder="请输入行政区划" :disabled="true">
                          {{ form.routeAdministrativeArea }}
                        </el-input>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="8">
                    <div class="grid-content bg-purple">
                      <el-form-item label="技术状况得分DR" prop="routeDr">
                        <el-input v-model="form.routeDr" :disabled="true" placeholder="请输入技术状况得分DR">
                          {{ form.routeDr }}
                        </el-input>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="8">
                    <div class="grid-content bg-purple">
                      <el-form-item label="评定时间" prop="routeScoreDate">
                        <el-date-picker clearable
                                        v-model="form.routeScoreDate"
                                        type="date"
                                        value-format="yyyy-MM-dd"
                                        placeholder="请选择评定时间">
                        </el-date-picker>
                      </el-form-item>
                    </div>
                  </el-col>
                </el-row>
                <el-col :span="8">
                  <div class="grid-content bg-purple">
                    <el-form-item label="评定单位" prop="routeCompany">
                      <el-input v-model="form.routeCompany" placeholder="请输入评定单位"/>
                    </el-form-item>
                  </div>
                </el-col>
              </el-tab-pane>
            </el-tabs>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="submitForm">确 定</el-button>
            <el-button @click="cancel">取 消</el-button>
          </div>
        </el-dialog>
      </div>
    </div>
  </div>
</template>

<script>
import {listQuality, getQuality, delQuality, addQuality, updateQuality, you, hao, zhong} from "@/api/system/quality";
import * as echarts from "echarts";
import Infdormations from "@/views/system/infdormations";
import $axios from "core-js/internals/queue";
import request from "@/utils/request";

export default {
  props: ["bridge", "bridgeCode"],
  components: {Infdormations},
  mounted() {
    this.tu();
  },
  name: "Quality",
  data() {
    return {
      datay: [],
      labelPosition: 'top',
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
      // 桥梁质量表格数据
      qualityList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      opener: false,
      openers: false,
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
        routeDr: null,
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
    tu() {
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
                return params[i].axisValue + "桥的数量：" + params[i].data
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
            data: ["优", "良", "中"],
          },
          yAxis: {},
          series: {
            name: "数量",
            type: "bar",
            data: [2, 4, 6]
          },
        },
        request('/system/quality/you').then(response => {
          this.datay = response
          console.log(this.datay)
          myEcharts.setOption({
            series: {
              name: "数量",
              type: "bar",
              data: this.datay
            },
          })
        })
      )
    },
    //选择桥梁信息
    shou(bridge, bridgeCode, bi, qu) {
      this.form.routeName = bridge;
      this.form.routeCode = bridgeCode;
      this.form.routeAdministrativeArea = qu;
      this.openers = bi;
    },
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
    /**选择桥梁*/
    selectRout() {
      this.openers = true;
      this.title = "选择桥梁";
    },
    /**详情按钮操作*/
    handlexq(row) {
      this.reset();
      const routeQualityId = row.routeQualityId || this.ids
      getQuality(routeQualityId).then(response => {
        this.form = response.data;
        console.log(response.data);
        this.opener = true;
        this.title = "桥梁质量";
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
  /*margin-bottom: 20px;*/
}
</style>
