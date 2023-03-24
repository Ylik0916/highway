<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="病害名称" prop="diseaseName">
        <el-input
          v-model="queryParams.diseaseName"
          placeholder="请输入病害名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="路线名称" prop="pathName">
        <el-input
          v-model="queryParams.pathName"
          placeholder="请输入路线名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="路段编号" prop="sectionCode">
        <el-input
          v-model="queryParams.sectionCode"
          placeholder="请输入路段编号"
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
          v-hasPermi="['system:disease:add']"
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
          v-hasPermi="['system:disease:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:disease:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="diseaseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="" align="center" prop="wdid" />-->
      <el-table-column label="病害名称" align="center" prop="diseaseName" />
      <el-table-column label="路线编号" align="center" prop="pathCode" />
      <el-table-column label="路线名称" align="center" prop="pathName" />
      <el-table-column label="路段编号" align="center" prop="sectionCode" />
      <el-table-column label="路段名称" align="center" prop="sectionName" />
      <el-table-column label="上报人" align="center" prop="reporter" />
      <el-table-column label="编辑日期" align="center" prop="reportDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.reportDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-s-order"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:disease:edit']"
          >上报</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:disease:remove']"
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

    <!-- 添加或修改道路病害管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1100px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-position="top" label-width="80px" >
        <el-row :gutter="20">
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="病害名称" prop="diseaseName">
                <el-input v-model="form.diseaseName" placeholder="请输入病害名称" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="行政区" prop="administrative">
                <treeselect v-model="form.administrative" :options="ordinaryOptions" :normalizer="normalizer" placeholder="请选择行政区" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="病害中心桩号" prop="diseaseStake">
                <el-input v-model="form.diseaseStake" placeholder="请输入病害中心桩号" />
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="8">
            <div class="grid-content">
              <el-row>
                <el-col :span="8">
                  <div class="grid-content">
                    <el-form-item label="病害纬度" prop="diseaseLatitude">
                      <el-input v-model="form.diseaseLatitude" :disabled="true" placeholder="请输入病害纬度" />
                    </el-form-item>
                  </div>
                </el-col>
                <el-col :span="8">
                  <div class="grid-content">
                    <el-form-item label="病害经度" prop="diseaseLongitude">
                      <el-input v-model="form.diseaseLongitude" :disabled="true" placeholder="请输入病害经度" />
                    </el-form-item>
                  </div>
                </el-col>
                <el-col :span="8">
                  <div class="grid-content">
                      <el-button style="margin-top: 46px;width: 100%"  type="primary" @click="openMap()">地图定位</el-button>
                  </div>
                </el-col>
              </el-row>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="所属路线" prop="pathName">
                <el-select v-model="form.pathName" value-key="id" @change="pathChange($event)" placeholder="请输入所属路线" style="width: 100%">
                  <el-option
                    v-for="item in listInformation"
                    :key="item.id"
                    :label="item.routeName"
                    :value="item"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content">
              <el-form-item label="所属路段" prop="sectionCode">
                <el-select v-model="form.sectionCode" value-key="sectionCoding" :disabled="sectionDisabled" placeholder="请输入所属路段" style="width: 100%">
                  <el-option
                    v-for="item in sectionList"
                    :key="item.sectionCoding"
                    :label="item.sectionCoding"
                    :value="item"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <div class="grid-content">
              <el-form-item label="病害信息" prop="diseaseMessage">
                <el-input v-model="form.diseaseMessage" type="textarea" placeholder="请输入病害信息" />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="grid-content">

            </div>
          </el-col>
        </el-row>
        <el-form-item label="病害图片" prop="diseaseImg">
          <image-upload :limit="1" v-model="form.diseaseImg"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm(1)">病害上报</el-button>
        <el-button type="primary" @click="submitForm(0)">保存病害</el-button>
<!--        <el-button @click="cancel">保存病害</el-button>-->
      </div>
    </el-dialog>

    <!-- 关闭 -->
    <el-dialog :title="titleMap" :visible.sync="mapClose" style="height: 800px;" width="800px" append-to-body>
      <MapContainer :getMapCode="getMapCode"/>
    </el-dialog>
  </div>
</template>

<script>
import { listDisease, getDisease, delDisease, addDisease, updateDisease } from "@/api/system/disease";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import { listDept } from "@/api/system/dept";
import {listInformation} from "@/api/system/information";
import {listSectionInformation} from "@/api/system/sectionInformation";
import MapContainer from "@/views/system/disease/components/MapContainer";


export default {
  name: "Reported",
  dicts: ['reporting_type', 'disease_state', 'driving_direction'],
  components: {
    MapContainer,
    Treeselect
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      diseaseNames: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      //路段禁用
      sectionDisabled: true,
      // 道路病害管理表格数据
      diseaseList: [],
      //行政下拉
      ordinaryOptions: [],
      //路线下拉
      listInformation: [],
      //路段下拉
      sectionList: [],
      // 弹出层标题
      title: "",
      titleMap: "",
      // 是否显示弹出层
      open: false,
      mapClose: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        diseaseName: null,
        pathName: null,
        sectionCode: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        diseaseName: [
          { required: true, message: "病害名称不能为空", trigger: "blur" }
        ],
        pathCode: [
          { required: true, message: "路线编号不能为空", trigger: "blur" }
        ],
        pathName: [
          { required: true, message: "路线名称不能为空", trigger: "blur" }
        ],
        sectionCode: [
          { required: true, message: "路段编号不能为空", trigger: "blur" }
        ],
        sectionName: [
          { required: true, message: "路段名称不能为空", trigger: "blur" }
        ],
        diseaseStake: [
          { required: true, message: "病害中心桩号不能为空", trigger: "blur" }
        ],
        diseaseLatitude: [
          { required: true, message: "病害纬度不能为空", trigger: "blur" },
        ],
        diseaseLongitude: [
          { required: true, message: "病害经度不能为空", trigger: "blur" },
        ],
        diseaseImg: [
          { required: true, message: "病害图片不能为空", trigger: "blur" }
        ],
        administrative: [
          { required: true, message: "行政区不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getMapCode(lng,lat){
      this.form.diseaseLongitude = lng;
      this.form.diseaseLatitude = lat;
    },
    closeMap(){
      this.mapClose = false;
    },
    openMap(){
      this.mapClose = true;
      this.titleMap = "地图定位";
    },
    /** 查询道路病害管理列表 */
    getList() {
      this.queryParams.statusid=0;
      this.loading = true;
      listDisease(this.queryParams).then(response => {
        this.diseaseList = response.rows;
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
        wdid: null,
        diseaseName: null,
        pathCode: null,
        pathName: null,
        sectionCode: null,
        sectionName: null,
        reporter: null,
        reportDate: null,
        reportType: null,
        statusid: null,
        wherePath: null,
        diseaseStake: null,
        diseaseLatitude: null,
        diseaseLongitude: null,
        diseaseMessage: null,
        diseaseImg: null,
        maintainSite: null,
        maintainCase: null,
        maintainPeople: null,
        maintainFund: null,
        beginTime: null,
        overTime: null,
        maintainRemark: null,
        maintainImg: null,
        maintainUnit: null,
        damageCase: null,
        horizontalPositions: null,
        forecastCase: null,
        disposeMeasure: null,
        drivingDirection: null,
        disposeStake: null,
        disposeRemark: null,
        disposeTime: null,
        disposeImg: null
      };
      this.resetForm("form");
    },
    /** 选中路线可用路段 */
    pathChange(item){
      this.getSectionList(item.id);
      this.sectionDisabled=false;
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    getSectionList(id){
      listSectionInformation({routeid:id}).then(response=>{
        this.sectionList=response.rows;
      });
    },
    /** 下拉查询路线 */
    getListInformation(){
      listInformation(null).then(response=>{
        this.listInformation=response.rows;
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
        this.ordinaryOptions = this.handleTree(response.data, "deptId", "parentId");
      });
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.wdid)
      this.diseaseNames = selection.map(item => item.diseaseName)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.getTreeselect();
      this.getListInformation();
      this.open = true;
      this.title = "添加道路病害管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      this.getListInformation();
      const wdid = row.wdid || this.ids
      getDisease(wdid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "上报";
      });
    },
    /** 提交按钮 */
    submitForm(statusId) {
      this.$refs["form"].validate(valid => {
        var userName = this.$store.state.user.name;
        this.form.reporter = userName;
        let routeItem = this.form.pathName;
        this.form.pathName=routeItem.routeName;
        this.form.pathCode=routeItem.routeCoding;
        this.form.statusid=statusId;
        let sectionItem = this.form.sectionCode;
        this.form.sectionName=sectionItem.originName;
        this.form.sectionCode=sectionItem.sectionCoding;
        this.form.reportType=0;
        this.form.reportDate = new Date();
        if (valid) {
          if (this.form.wdid != null) {
            updateDisease(this.form).then(response => {
              this.$modal.msgSuccess("操作成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDisease(this.form).then(response => {
              this.$modal.msgSuccess("操作成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const wdids = row.wdid || this.ids;
      const diseaseNames = row.diseaseName || this.diseaseNames;
      this.$modal.confirm('是否确认删除道路病害名称为"' + diseaseNames + '"的数据项？').then(function() {
        return delDisease(wdids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/disease/export', {
        ...this.queryParams
      }, `disease_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

