<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="乡镇名称" prop="nameOfTownship">
        <el-input
          v-model="queryParams.nameOfTownship"
          placeholder="请输入乡镇名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="行政区划编号" prop="administrativeDivisionNumber">
        <el-input
          v-model="queryParams.administrativeDivisionNumber"
          placeholder="请输入行政区划编号"
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
          v-hasPermi="['system:township:add']"
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
          v-hasPermi="['system:township:edit']"
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
          v-hasPermi="['system:township:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:township:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="townshipList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="乡镇ID" align="center" prop="townshipId" />
      <el-table-column label="乡镇名称" align="center" prop="nameOfTownship" />
      <el-table-column label="区域人口" align="center" prop="regionalPopulation" />
      <el-table-column label="行政区划编号" align="center" prop="administrativeDivisionNumber" />
      <el-table-column label="通达现状" align="center" prop="accessStatusQuo">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.access" :value="scope.row.accessStatusQuo"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:township:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:township:remove']"
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

    <!-- 添加或修改乡镇对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" style="display: flex;flex-wrap: wrap;justify-content: space-between">
        <el-form-item label="乡镇名称" prop="nameOfTownship">
          <el-input v-model="form.nameOfTownship" placeholder="请输入乡镇名称" />
        </el-form-item>
        <el-form-item label="区域人口" prop="regionalPopulation">
          <el-input v-model="form.regionalPopulation" placeholder="请输入区域人口" />
        </el-form-item>
        <el-form-item label="所属建制村数量" prop="numberOfIncorporatedVillages">
          <el-input v-model="form.numberOfIncorporatedVillages" placeholder="请输入所属建制村数量" />
        </el-form-item>
        <el-form-item label="行政区划编号" prop="administrativeDivisionNumber">
          <el-input v-model="form.administrativeDivisionNumber" placeholder="请输入行政区划编号" />
        </el-form-item>
        <el-form-item label="经纬度类型" prop="latitudeAndLongitudeType">
          <el-radio-group v-model="form.latitudeAndLongitudeType">
            <el-radio
              v-for="dict in dict.type.latitude_and_longitude_type"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="经度" prop="longitude">
          <el-input v-model="form.longitude" placeholder="请输入经度" />
        </el-form-item>
        <el-form-item label="维度" prop="latitude">
          <el-input v-model="form.latitude" placeholder="请输入维度" />
        </el-form-item>
        <el-form-item label="所属地形" prop="landform">
          <el-select v-model="form.landform" placeholder="请选择所属地形">
            <el-option
              v-for="dict in dict.type.terrain"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="岛屿是否建有陆岛交通码头" prop="islandTransportationTerminal">
          <el-select v-model="form.islandTransportationTerminal" placeholder="请选择岛屿是否建有陆岛交通码头">
            <el-option
              v-for="dict in dict.type.sys_yes_no"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="码头与陆地距离(公里)" prop="distanceFromPierToLand">
          <el-input v-model="form.distanceFromPierToLand" placeholder="请输入码头与陆地距离(公里)" />
        </el-form-item>
        <el-form-item label="岛内是否建有公路" prop="roadsOnTheIsland">
          <el-select v-model="form.roadsOnTheIsland" placeholder="请选择岛内是否建有公路">
            <el-option
              v-for="dict in dict.type.sys_yes_no"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否适宜通公路" prop="suitableForRoadAccess">
          <el-select v-model="form.suitableForRoadAccess" placeholder="请选择是否适宜通公路">
            <el-option
              v-for="dict in dict.type.sys_yes_no"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="乡镇政府高程" prop="elevationOfTownshipGovernment">
          <el-input v-model="form.elevationOfTownshipGovernment" placeholder="请输入乡镇政府高程" />
        </el-form-item>
        <el-form-item label="不适宜原因" prop="causeOfUnsuitability">
          <el-input v-model="form.causeOfUnsuitability" placeholder="请输入不适宜原因" />
        </el-form-item>
        <el-form-item label="通达现状" prop="accessStatusQuo">
          <el-select v-model="form.accessStatusQuo" placeholder="请选择通达现状">
            <el-option
              v-for="dict in dict.type.access"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="乡镇通达位置" prop="locationOfTownshipAccess">
          <el-select v-model="form.locationOfTownshipAccess" placeholder="请选择乡镇通达位置">
            <el-option
              v-for="dict in dict.type.location_of_township_access"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="优选通达路线行政等级" prop="administrativeLevelAccessRoute">
          <el-select v-model="form.administrativeLevelAccessRoute" placeholder="请选择优选通达路线行政等级">
            <el-option
              v-for="dict in dict.type.optimization"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="优选通达路线编码" prop="optimalAccessRouteCoding">
          <el-input v-model="form.optimalAccessRouteCoding" placeholder="请输入优选通达路线编码" />
        </el-form-item>
        <el-form-item label="优选通达路线名称" prop="accessRouteIsPreferred">
          <el-input v-model="form.accessRouteIsPreferred" placeholder="请输入优选通达路线名称" />
        </el-form-item>
        <el-form-item label="通达方向" prop="directionOfAccess">
          <el-select v-model="form.directionOfAccess" placeholder="请选择通达方向">
            <el-option
              v-for="dict in dict.type.direction_of_access"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="待建路段里程(公里)" prop="lengthOfRoadToBeBuilt">
          <el-input v-model="form.lengthOfRoadToBeBuilt" placeholder="请输入待建路段里程(公里)" />
        </el-form-item>
        <el-form-item label="待建路段起点桩号" prop="pointOfTheRoadSection">
          <el-input v-model="form.pointOfTheRoadSection" placeholder="请输入待建路段起点桩号" />
        </el-form-item>
        <el-form-item label="待建路段讫点桩号" prop="endPointOfTheRoadToBeBuilt">
          <el-input v-model="form.endPointOfTheRoadToBeBuilt" placeholder="请输入待建路段讫点桩号" />
        </el-form-item>
        <el-form-item label="填报单位代码" prop="fillInTheUnitCode">
          <el-input v-model="form.fillInTheUnitCode" placeholder="请输入填报单位代码" />
        </el-form-item>
        <el-form-item label="填报单位" prop="reportingUnit">
          <el-input v-model="form.reportingUnit" placeholder="请输入填报单位" />
        </el-form-item>
        <el-form-item label="填报单位负责人" prop="personInCharge">
          <el-input v-model="form.personInCharge" placeholder="请输入填报单位负责人" />
        </el-form-item>
        <el-form-item label="填表人" prop="formFiller">
          <el-input v-model="form.formFiller" placeholder="请输入填表人" />
        </el-form-item>
        <el-form-item label="填表人电话" prop="personFillingInTheForm">
          <el-input v-model="form.personFillingInTheForm" placeholder="请输入填表人电话" />
        </el-form-item>
        <el-form-item label="审核人" prop="auditor">
          <el-input v-model="form.auditor" placeholder="请输入审核人" />
        </el-form-item>
        <el-form-item label="审核人电话" prop="auditorTelephone">
          <el-input v-model="form.auditorTelephone" placeholder="请输入审核人电话" />
        </el-form-item>
        <el-form-item label="变化程度" prop="degreeOfChange">
          <el-input v-model="form.degreeOfChange" placeholder="请输入变化程度" />
        </el-form-item>
        <el-form-item label="采集标记" prop="acquisitionMark">
          <el-input v-model="form.acquisitionMark" placeholder="请输入采集标记" />
        </el-form-item>
        <el-form-item label="是否纳入统计" prop="includedInStatistics">
          <el-select v-model="form.includedInStatistics" placeholder="请选择是否纳入统计">
            <el-option
              v-for="dict in dict.type.sys_yes_no"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="采集时间" prop="acquisitionTime">
          <el-date-picker clearable
            v-model="form.acquisitionTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择采集时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="修改时间" prop="modificationTime">
          <el-date-picker clearable
            v-model="form.modificationTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择修改时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="remarks">
          <el-input v-model="form.remarks" type="textarea" placeholder="请输入内容" />
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
import { listTownship, getTownship, delTownship, addTownship, updateTownship } from "@/api/system/township";

export default {
  name: "Township",
  dicts: ['latitude_and_longitude_type', 'access', 'optimization', 'sys_yes_no', 'terrain', 'location_of_township_access', 'direction_of_access'],
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
      // 乡镇表格数据
      townshipList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        nameOfTownship: null,
        administrativeDivisionNumber: null,
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
    /** 查询乡镇列表 */
    getList() {
      this.loading = true;
      listTownship(this.queryParams).then(response => {
        this.townshipList = response.rows;
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
        townshipId: null,
        nameOfTownship: null,
        regionalPopulation: null,
        numberOfIncorporatedVillages: null,
        administrativeDivisionNumber: null,
        latitudeAndLongitudeType: null,
        longitude: null,
        latitude: null,
        landform: null,
        islandTransportationTerminal: null,
        distanceFromPierToLand: null,
        roadsOnTheIsland: null,
        suitableForRoadAccess: null,
        elevationOfTownshipGovernment: null,
        causeOfUnsuitability: null,
        accessStatusQuo: null,
        locationOfTownshipAccess: null,
        administrativeLevelAccessRoute: null,
        optimalAccessRouteCoding: null,
        accessRouteIsPreferred: null,
        directionOfAccess: null,
        lengthOfRoadToBeBuilt: null,
        pointOfTheRoadSection: null,
        endPointOfTheRoadToBeBuilt: null,
        fillInTheUnitCode: null,
        reportingUnit: null,
        personInCharge: null,
        formFiller: null,
        personFillingInTheForm: null,
        auditor: null,
        auditorTelephone: null,
        degreeOfChange: null,
        acquisitionMark: null,
        includedInStatistics: null,
        acquisitionTime: null,
        modificationTime: null,
        remarks: null
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
      this.ids = selection.map(item => item.townshipId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加乡镇";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const townshipId = row.townshipId || this.ids
      getTownship(townshipId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改乡镇";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.townshipId != null) {
            updateTownship(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTownship(this.form).then(response => {
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
      const townshipIds = row.townshipId || this.ids;
      this.$modal.confirm('是否确认删除乡镇编号为"' + townshipIds + '"的数据项？').then(function() {
        return delTownship(townshipIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/township/export', {
        ...this.queryParams
      }, `township_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
