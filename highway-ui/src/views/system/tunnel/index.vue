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
          v-hasPermi="['system:tunnel:add']"
        >新增</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['system:tunnel:edit']"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:tunnel:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:tunnel:export']"
        >导出</el-button>
      </el-col>
<!--      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>-->
    </el-row>


    <!-- 详情桥梁信息对话框 -->
    <el-dialog :title="title" :visible.sync="openXq" width="1000px" append-to-body>
      <el-tabs v-model="activeName" type="card">
        <el-tab-pane label="基本信息" name="first">
          <el-descriptions :model="form">
            <el-descriptions-item label="隧道名称">{{ form.tunnelName }}</el-descriptions-item>
            <el-descriptions-item label="隧道编号">{{ form.tunnelNumber }}</el-descriptions-item>
            <el-descriptions-item label="隧道中心桩号">{{ form.tunnelCenterPileNumber }}</el-descriptions-item>
            <el-descriptions-item label="入口桩号/起点桩号">{{ form.startingPileNumber }}</el-descriptions-item>
            <el-descriptions-item label="隧道长度">{{ form.tunnelLength }}</el-descriptions-item>
            <el-descriptions-item label="隧道全宽">{{ form.tunnelOverallWidth }}</el-descriptions-item>
            <el-descriptions-item label="隧道净宽">{{ form.tunnelClearWidth }}</el-descriptions-item>
            <el-descriptions-item label="隧道净高">{{ form.tunnelClearHeight }}</el-descriptions-item>
            <el-descriptions-item label="人行道宽">{{ form.sidewalkWidth }}</el-descriptions-item>
            <el-descriptions-item label="按隧道长度分类">{{ form.tunnelLengthType }}</el-descriptions-item>
            <el-descriptions-item label="断面形式">{{ form.sectionForm }}</el-descriptions-item>
            <el-descriptions-item label="洞口形式">{{ form.openingForm }}</el-descriptions-item>
            <el-descriptions-item label="衬砌材料">{{ form.liningMaterial }}</el-descriptions-item>
            <el-descriptions-item label="消防设施">{{ form.fireEquipments }}</el-descriptions-item>
            <el-descriptions-item label="安全通道数量">{{ form.numberOfSafeChannels }}</el-descriptions-item>
            <el-descriptions-item label="是否水下隧道">{{ form.underwaterTunnelOrNot }}</el-descriptions-item>
            <el-descriptions-item label="管养单位性质">{{ form.natureOfCustodialUnit }}</el-descriptions-item>
            <el-descriptions-item label="隧道评定等级">{{ form.tunnelRating }}</el-descriptions-item>
            <el-descriptions-item label="评定日期">{{ form.dateOfAssessment }}</el-descriptions-item>
            <el-descriptions-item label="评定单位">{{ form.ratingUnit }}</el-descriptions-item>
            <el-descriptions-item label="隧道评定等级_土建">{{ form.tunnelRatingCivilConstruction }}</el-descriptions-item>
            <el-descriptions-item label="评定日期土建">{{ form.ratingDateCivilConstruction }}</el-descriptions-item>
            <el-descriptions-item label="评定单位土建">{{ form.evaluateTheCivilConstruction }}</el-descriptions-item>
            <el-descriptions-item label="隧道评定等级_机电">{{ form.tunnelRatingGrade }}</el-descriptions-item>
            <el-descriptions-item label="评定单位_机电">{{ form.ratingUnitMechanical }}</el-descriptions-item>
            <el-descriptions-item label="评定日期_机电">{{ form.dateOfEvaluation }}</el-descriptions-item>
            <el-descriptions-item label="隧道评定等级_其他">{{ form.tunnelRatingOther }}</el-descriptions-item>
            <el-descriptions-item label="评定单位_其他">{{ form.ratingUnitOther }}</el-descriptions-item>
            <el-descriptions-item label="原隧道编码">{{ form.originalTunnelCoding }}</el-descriptions-item>
            <el-descriptions-item label="路面面层类型">{{ form.typeOfPavementLayer }}</el-descriptions-item>
            <el-descriptions-item label="隧道照明">{{ form.tunnelLighting }}</el-descriptions-item>
            <el-descriptions-item label="隧道通风">{{ form.tunnelVentilation }}</el-descriptions-item>
            <el-descriptions-item label="隧道高程">{{ form.tunnelElevation }}</el-descriptions-item>
            <el-descriptions-item label="隧道养护等级">{{ form.tunnelMaintenanceGrade }}</el-descriptions-item>
            <el-descriptions-item label="是否在长大隧道目录">{{ form.longTunnelDirectory }}</el-descriptions-item>
          </el-descriptions>
        </el-tab-pane>
        <el-tab-pane label="养护信息" name="second">
          <el-descriptions :model="form">
            <el-descriptions-item label="隧道排水类型">{{ form.tunnelDrainageType}}</el-descriptions-item>
            <el-descriptions-item label="隧道所在地点">{{ form.tunnelLocation }}</el-descriptions-item>
            <el-descriptions-item label="建成时间(年份)">{{ form.completionTime }}</el-descriptions-item>
            <el-descriptions-item label="通车日期">{{ form.openingDate}}</el-descriptions-item>
            <el-descriptions-item label="变更原因">{{ form.reasonForChange}}</el-descriptions-item>
            <el-descriptions-item label="隧道电子设备">{{ form.tunnelElectronics}}</el-descriptions-item>
            <el-descriptions-item label="设计单位名称">{{ form.nameOfDesignCompany }}</el-descriptions-item>
            <el-descriptions-item label="监管单位名称">{{ form.nameOfSupervisoryUnit }}</el-descriptions-item>
            <el-descriptions-item label="施工单位名称">{{ form.nameOfConstructionUnit }}</el-descriptions-item>
            <el-descriptions-item label="监理单位名称">{{ form.nameOfSupervisionUnit }}</el-descriptions-item>
            <el-descriptions-item label="建设单位名称">{{ form.nameOfConstruction }}</el-descriptions-item>
            <el-descriptions-item label="管养单位代码">{{ form.custodialUnitCode }}</el-descriptions-item>
            <el-descriptions-item label="管养单位名称">{{ form.nameOfCustodialUnit }}</el-descriptions-item>
            <el-descriptions-item label="改建年度">{{ form.conversionYear }}</el-descriptions-item>
            <el-descriptions-item label="隧道改造部位">{{ form.tunnelReconstructionSite }}</el-descriptions-item>
            <el-descriptions-item label="最近改造工程性质">{{ form.recentRenovationProject }}</el-descriptions-item>
            <el-descriptions-item label="改造完工日期">{{ form.dateOfCompletion }}</el-descriptions-item>
            <el-descriptions-item label="隧道病害位置">{{ form.tunnelTroubleLocation }}</el-descriptions-item>
            <el-descriptions-item label="入口位置经度/隧道经度">{{ form.tunnelLongitude }}</el-descriptions-item>
            <el-descriptions-item label="入口位置纬度/隧道纬度">{{ form.tunnelLatitude }}</el-descriptions-item>
            <el-descriptions-item label="隧道病害描述">{{ form.tunnelDamageDescription }}</el-descriptions-item>
          </el-descriptions>
        </el-tab-pane>
        <el-tab-pane label="其他信息" name="third">
          <el-descriptions :model="form">
            <el-descriptions-item label="填报单位">{{ form.reportingUnit }}</el-descriptions-item>
            <el-descriptions-item label="填报单位代码">{{ form.reportingUnitCode }}</el-descriptions-item>
            <el-descriptions-item label="填报单位负责人">{{ form.reportingUnitPrincipal }}</el-descriptions-item>
            <el-descriptions-item label="填表人">{{ form.reportingPerson }}</el-descriptions-item>
            <el-descriptions-item label="填表人电话">{{ form.reportingPersonTelephone }}</el-descriptions-item>
            <el-descriptions-item label="审核人">{{ form.auditor }}</el-descriptions-item>
            <el-descriptions-item label="审核人电话">{{ form.auditorTelephone }}</el-descriptions-item>
            <el-descriptions-item label="采集时间">{{ form.acquisitionTime }}</el-descriptions-item>
            <el-descriptions-item label="修改时间">{{ form.modificationTime }}</el-descriptions-item>
            <el-descriptions-item label="备注">{{ form.remarks }}</el-descriptions-item>
          </el-descriptions>
        </el-tab-pane>
      </el-tabs>
    </el-dialog>

    <el-table v-loading="loading" :data="tunnelList" @selection-change="handleSelectionChange">
<!--      <el-table-column type="selection" width="55" align="center" />-->
<!--      <el-table-column label="隧道ID" align="center" prop="tunnelId" />-->
      <el-table-column label="路线编码" align="center" prop="routeCoding" />
      <el-table-column label="路线名称" align="center" prop="routeName" />
      <el-table-column label="隧道名称" align="center" prop="tunnelName" />
      <el-table-column label="隧道编号" align="center" prop="tunnelNumber" />
      <el-table-column label="隧道中心桩号" align="center" prop="tunnelCenterPileNumber" />
      <el-table-column label="隧道长度" align="center" prop="tunnelLength" />
      <el-table-column label="隧道全宽" align="center" prop="tunnelOverallWidth" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-finished"
            @click="getSdXq(scope.row)"
            v-hasPermi="['system:tunnel:edit']"
          >详情</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:tunnel:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:tunnel:remove']"
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

    <!-- 添加或修改隧道信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body width="1000px">
      <el-form ref="form" :inline="true" :model="form" :rules="rules" :label-position="labelPosition">
        <el-tabs v-model="activeName" type="card">
          <el-tab-pane label="基本信息" name="first">
<!--        <el-form-item label="路线编码" prop="routeCoding">-->
<!--          <el-input v-model="form.routeCoding" placeholder="请输入路线编码" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="路线名称" prop="routeName">-->
<!--          <el-input v-model="form.routeName" placeholder="请输入路线名称" />-->
<!--        </el-form-item>-->
        <el-form-item label="隧道名称" prop="tunnelName">
          <el-input v-model="form.tunnelName" placeholder="请输入隧道名称"/>
        </el-form-item>
        <el-form-item label="隧道编号" prop="tunnelNumber">
          <el-input v-model="form.tunnelNumber" placeholder="请输入隧道编号"/>
        </el-form-item>
        <el-form-item label="隧道中心桩号" prop="tunnelCenterPileNumber">
          <el-input v-model="form.tunnelCenterPileNumber" placeholder="请输入隧道中心桩号"/>
        </el-form-item>
        <el-form-item label="入口桩号/起点桩号" prop="startingPileNumber">
          <el-input v-model="form.startingPileNumber" placeholder="请输入入口桩号/起点桩号"/>
        </el-form-item>
        <el-form-item label="隧道长度" prop="tunnelLength">
          <el-input v-model="form.tunnelLength" placeholder="请输入隧道长度"/>
        </el-form-item>
        <el-form-item label="隧道全宽" prop="tunnelOverallWidth">
          <el-input v-model="form.tunnelOverallWidth" placeholder="请输入隧道全宽"/>
        </el-form-item>
        <el-form-item label="隧道净宽" prop="tunnelClearWidth">
          <el-input v-model="form.tunnelClearWidth" placeholder="请输入隧道净宽"/>
        </el-form-item>
        <el-form-item label="隧道净高" prop="tunnelClearHeight">
          <el-input v-model="form.tunnelClearHeight" placeholder="请输入隧道净高"/>
        </el-form-item>
        <el-form-item label="人行道宽" prop="sidewalkWidth">
          <el-input v-model="form.sidewalkWidth" placeholder="请输入人行道宽"/>
        </el-form-item>
        <el-form-item label="按隧道长度分类" prop="tunnelLengthType">
          <el-select v-model="form.tunnelLengthType" placeholder="请选择按隧道长度分类">
            <el-option
              v-for="dict in dict.type.tunnel_length_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="断面形式" prop="sectionForm">
          <el-select v-model="form.sectionForm" placeholder="请选择断面形式">
            <el-option
              v-for="dict in dict.type.section_form"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="洞口形式" prop="openingForm">
          <el-select v-model="form.openingForm" placeholder="请选择洞口形式">
            <el-option
              v-for="dict in dict.type.opening_form"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="衬砌材料" prop="liningMaterial">
          <el-select v-model="form.liningMaterial" placeholder="请选择衬砌材料">
            <el-option
              v-for="dict in dict.type.lining_material"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="消防设施" prop="fireEquipments">
          <el-input v-model="form.fireEquipments" placeholder="请输入消防设施"/>
        </el-form-item>
        <el-form-item label="安全通道数量" prop="numberOfSafeChannels">
          <el-input v-model="form.numberOfSafeChannels" placeholder="请输入安全通道数量"/>
        </el-form-item>
        <el-form-item label="是否水下隧道" prop="underwaterTunnelOrNot">
          <el-select v-model="form.underwaterTunnelOrNot" placeholder="请选择是否水下隧道">
            <el-option
              v-for="dict in dict.type.underwater_tunnel_or_not"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="管养单位性质" prop="natureOfCustodialUnit">
          <el-select v-model="form.natureOfCustodialUnit" placeholder="请选择管养单位性质">
            <el-option
              v-for="dict in dict.type.management_maintenance"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="隧道评定等级" prop="tunnelRating">
          <el-select v-model="form.tunnelRating" placeholder="请选择隧道评定等级">
            <el-option
              v-for="dict in dict.type.tunnel_level"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="评定日期" prop="dateOfAssessment">
          <el-date-picker clearable
            v-model="form.dateOfAssessment"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择评定日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="评定单位" prop="ratingUnit">
          <el-input v-model="form.ratingUnit" placeholder="请输入评定单位"/>
        </el-form-item>
        <el-form-item label="隧道评定等级_土建" prop="tunnelRatingCivilConstruction">
          <el-select v-model="form.tunnelRatingCivilConstruction" placeholder="请选择隧道评定等级_土建">
            <el-option
              v-for="dict in dict.type.tunnel_rating"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="评定日期土建" prop="ratingDateCivilConstruction">
          <el-date-picker clearable
            v-model="form.ratingDateCivilConstruction"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择评定日期土建">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="评定单位土建" prop="evaluateTheCivilConstruction">
          <el-input v-model="form.evaluateTheCivilConstruction" placeholder="请输入评定单位土建"/>
        </el-form-item>
        <el-form-item label="隧道评定等级_机电" prop="tunnelRatingGrade">
          <el-select v-model="form.tunnelRatingGrade" placeholder="请选择隧道评定等级_机电">
            <el-option
              v-for="dict in dict.type.tunnel_rating"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="评定单位_机电" prop="ratingUnitMechanical">
          <el-input v-model="form.ratingUnitMechanical" placeholder="请输入评定单位_机电"/>
        </el-form-item>
        <el-form-item label="评定日期_机电" prop="dateOfEvaluation">
          <el-date-picker clearable
            v-model="form.dateOfEvaluation"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择评定日期_机电" >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="隧道评定等级_其他" prop="tunnelRatingOther">
          <el-input v-model="form.tunnelRatingOther" placeholder="请输入隧道评定等级_其他" />
        </el-form-item>
        <el-form-item label="评定单位_其他" prop="ratingUnitOther">
          <el-input v-model="form.ratingUnitOther" placeholder="请输入评定单位_其他" />
        </el-form-item>
        <el-form-item label="原隧道编码" prop="originalTunnelCoding">
          <el-input v-model="form.originalTunnelCoding" placeholder="请输入原隧道编码" />
        </el-form-item>
        <el-form-item label="路面面层类型" prop="typeOfPavementLayer">
          <el-select v-model="form.typeOfPavementLayer" placeholder="请选择路面面层类型" >
            <el-option
              v-for="dict in dict.type.type_of_pavement_layer"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="隧道照明" prop="tunnelLighting">
          <el-select v-model="form.tunnelLighting" placeholder="请选择隧道照明" >
            <el-option
              v-for="dict in dict.type.tunnel_lighting"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="隧道通风" prop="tunnelVentilation">
          <el-select v-model="form.tunnelVentilation" placeholder="请选择隧道通风" >
            <el-option
              v-for="dict in dict.type.tunnel_ventilation"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="隧道高程" prop="tunnelElevation">
          <el-input v-model="form.tunnelElevation" placeholder="请输入隧道高程" />
        </el-form-item>
        <el-form-item label="隧道养护等级" prop="tunnelMaintenanceGrade">
          <el-select v-model="form.tunnelMaintenanceGrade" placeholder="请选择隧道养护等级" >
            <el-option
              v-for="dict in dict.type.tunnel_maintenance_grade"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
            <el-form-item label="是否在长大隧道目录" prop="longTunnelDirectory">
              <el-select v-model="form.longTunnelDirectory" placeholder="请选择是否在长大隧道目录" >
                <el-option
                  v-for="dict in dict.type.underwater_tunnel_or_not"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-tab-pane>
          <el-tab-pane label="养护信息" name="second">
        <el-form-item label="隧道排水类型" prop="tunnelDrainageType">
          <el-select v-model="form.tunnelDrainageType" placeholder="请选择隧道排水类型" >
            <el-option
              v-for="dict in dict.type.tunnel_drainage_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="隧道所在地点" prop="tunnelLocation">
          <el-input v-model="form.tunnelLocation" placeholder="请输入隧道所在地点" />
        </el-form-item>
        <el-form-item label="建成时间(年份)" prop="completionTime">
          <el-date-picker clearable
            v-model="form.completionTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择建成时间(年份)" >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="通车日期" prop="openingDate">
          <el-date-picker clearable
            v-model="form.openingDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择通车日期" >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="变更原因" prop="reasonForChange">
          <el-select v-model="form.reasonForChange" placeholder="请选择变更原因" >
            <el-option
              v-for="dict in dict.type.reason_for_change"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="隧道电子设备" prop="tunnelElectronics">
          <el-select v-model="form.tunnelElectronics" placeholder="请选择隧道电子设备" >
            <el-option
              v-for="dict in dict.type.tunnel_electronics"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="设计单位名称" prop="nameOfDesignCompany">
          <el-input v-model="form.nameOfDesignCompany" placeholder="请输入设计单位名称" />
        </el-form-item>
        <el-form-item label="监管单位名称" prop="nameOfSupervisoryUnit">
          <el-input v-model="form.nameOfSupervisoryUnit" placeholder="请输入监管单位名称" />
        </el-form-item>
        <el-form-item label="施工单位名称" prop="nameOfConstructionUnit">
          <el-input v-model="form.nameOfConstructionUnit" placeholder="请输入施工单位名称" />
        </el-form-item>
        <el-form-item label="监理单位名称" prop="nameOfSupervisionUnit">
          <el-input v-model="form.nameOfSupervisionUnit" placeholder="请输入监理单位名称" />
        </el-form-item>
        <el-form-item label="建设单位名称" prop="nameOfConstruction">
          <el-input v-model="form.nameOfConstruction" placeholder="请输入建设单位名称" />
        </el-form-item>
        <el-form-item label="管养单位代码" prop="custodialUnitCode">
          <el-input v-model="form.custodialUnitCode" placeholder="请输入管养单位代码" />
        </el-form-item>
        <el-form-item label="管养单位名称" prop="nameOfCustodialUnit">
          <el-input v-model="form.nameOfCustodialUnit" placeholder="请输入管养单位名称" />
        </el-form-item>
        <el-form-item label="改建年度" prop="conversionYear">
          <el-date-picker clearable
            v-model="form.conversionYear"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择改建年度" >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="隧道改造部位" prop="tunnelReconstructionSite">
          <el-select v-model="form.tunnelReconstructionSite" placeholder="请选择隧道改造部位" >
            <el-option
              v-for="dict in dict.type.tunnel_reconstruction_site"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="最近改造工程性质" prop="recentRenovationProject">
          <el-select v-model="form.recentRenovationProject" placeholder="请选择最近改造工程性质" >
            <el-option
              v-for="dict in dict.type.recent_renovation_project"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="改造完工日期" prop="dateOfCompletion">
          <el-date-picker clearable
            v-model="form.dateOfCompletion"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择改造完工日期" >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="隧道病害位置" prop="tunnelTroubleLocation">
          <el-select v-model="form.tunnelTroubleLocation" placeholder="请选择隧道病害位置" >
            <el-option
              v-for="dict in dict.type.tunnel_trouble_location"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="入口位置经度/隧道经度" prop="tunnelLongitude">
          <el-input v-model="form.tunnelLongitude" placeholder="请输入入口位置经度/隧道经度" />
        </el-form-item>
        <el-form-item label="入口位置纬度/隧道纬度" prop="tunnelLatitude">
          <el-input v-model="form.tunnelLatitude" placeholder="请输入入口位置纬度/隧道纬度" />
        </el-form-item>
        <el-form-item label="隧道病害描述" prop="tunnelDamageDescription">
          <el-input v-model="form.tunnelDamageDescription" type="textarea" placeholder="请输入内容" />
        </el-form-item>
          </el-tab-pane>
          <el-tab-pane label="其他信息" name="third">
        <el-form-item label="填报单位" prop="reportingUnit">
          <el-input v-model="form.reportingUnit" placeholder="请输入填报单位" />
        </el-form-item>
        <el-form-item label="填报单位代码" prop="reportingUnitCode">
          <el-input v-model="form.reportingUnitCode" placeholder="请输入填报单位代码" />
        </el-form-item>
        <el-form-item label="填报单位负责人" prop="reportingUnitPrincipal">
          <el-input v-model="form.reportingUnitPrincipal" placeholder="请输入填报单位负责人" />
        </el-form-item>
        <el-form-item label="填表人" prop="reportingPerson">
          <el-input v-model="form.reportingPerson" placeholder="请输入填表人" />
        </el-form-item>
        <el-form-item label="填表人电话" prop="reportingPersonTelephone">
          <el-input v-model="form.reportingPersonTelephone" placeholder="请输入填表人电话" />
        </el-form-item>
        <el-form-item label="审核人" prop="auditor">
          <el-input v-model="form.auditor" placeholder="请输入审核人" />
        </el-form-item>
        <el-form-item label="审核人电话" prop="auditorTelephone">
          <el-input v-model="form.auditorTelephone" placeholder="请输入审核人电话" />
        </el-form-item>
        <el-form-item label="采集时间" prop="acquisitionTime">
          <el-date-picker clearable
            v-model="form.acquisitionTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择采集时间" >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="修改时间" prop="modificationTime">
          <el-date-picker clearable
            v-model="form.modificationTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择修改时间" >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="remarks">
          <el-input v-model="form.remarks" type="textarea" placeholder="请输入内容" />
        </el-form-item>
<!--        <el-form-item label="路线ID" prop="routeId">-->
<!--          <el-input v-model="form.routeId" placeholder="请输入路线ID" />-->
<!--        </el-form-item>-->
          </el-tab-pane>
        </el-tabs>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listTunnel, getTunnel, delTunnel, addTunnel, updateTunnel } from "@/api/system/tunnel";

export default {
  props:['rou'],
  name: "Tunnel",
  dicts: ['section_form', 'tunnel_ventilation', 'underwater_tunnel_or_not', 'tunnel_length_type', 'tunnel_reconstruction_site', 'tunnel_drainage_type', 'opening_form', 'management_maintenance', 'recent_renovation_project', 'tunnel_trouble_location', 'tunnel_rating', 'tunnel_electronics', 'tunnel_maintenance_grade', 'reason_for_change', 'tunnel_level', 'type_of_pavement_layer', 'tunnel_lighting', 'lining_material'],
  data() {
    return {
      labelPosition: 'top',
      activeName: 'first',
      flag:false,
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
      // 隧道信息表格数据
      tunnelList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否显示详情弹出层
      openXq: false,
      // 查询参数
      queryParams: {
        routeId: this.rou,
        pageNum: 1,
        pageSize: 10,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        tunnelName: [
          { required: true, message: "隧道名称不能为空", trigger: "blur" }
        ],
        tunnelNumber: [
          { required: true, message: "隧道编号不能为空", trigger: "blur" }
        ],
        tunnelLocation: [
          { required: true, message: "隧道所在地点不能为空", trigger: "blur" }
        ],
        tunnelLongitude: [
          { required: true, message: "入口位置经度/隧道经度不能为空", trigger: "blur" }
        ],
        tunnelLatitude: [
          { required: true, message: "入口位置纬度/隧道纬度不能为空", trigger: "blur" }
        ],
        reportingUnit: [
          { required: true, message: "填报单位不能为空", trigger: "blur" }
        ],
        reportingUnitCode: [
          { required: true, message: "填报单位代码不能为空", trigger: "blur" }
        ],
        reportingUnitPrincipal: [
          { required: true, message: "填报单位负责人不能为空", trigger: "blur" }
        ],
        reportingPerson: [
          { required: true, message: "填表人不能为空", trigger: "blur" }
        ],
        reportingPersonTelephone: [
          { required: true, message: "填表人电话不能为空", trigger: "blur" }
        ],
        auditor: [
          { required: true, message: "审核人不能为空", trigger: "blur" }
        ],
        auditorTelephone: [
          { required: true, message: "审核人电话不能为空", trigger: "blur" }
        ],
        remarks: [
          { required: true, message: "备注不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询隧道信息列表 */
    getList() {
      this.loading = true;
      listTunnel(this.queryParams).then(response => {
        this.tunnelList = response.rows;
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
        tunnelId: null,
        routeCoding: null,
        routeName: null,
        tunnelName: null,
        tunnelNumber: null,
        tunnelCenterPileNumber: null,
        startingPileNumber: null,
        tunnelLength: null,
        tunnelOverallWidth: null,
        tunnelClearWidth: null,
        tunnelClearHeight: null,
        sidewalkWidth: null,
        tunnelLengthType: null,
        sectionForm: null,
        openingForm: null,
        liningMaterial: null,
        fireEquipments: null,
        numberOfSafeChannels: null,
        underwaterTunnelOrNot: null,
        natureOfCustodialUnit: null,
        tunnelRating: null,
        dateOfAssessment: null,
        ratingUnit: null,
        tunnelRatingCivilConstruction: null,
        ratingDateCivilConstruction: null,
        evaluateTheCivilConstruction: null,
        tunnelRatingGrade: null,
        ratingUnitMechanical: null,
        dateOfEvaluation: null,
        tunnelRatingOther: null,
        ratingUnitOther: null,
        originalTunnelCoding: null,
        typeOfPavementLayer: null,
        tunnelLighting: null,
        tunnelVentilation: null,
        tunnelElevation: null,
        tunnelMaintenanceGrade: null,
        tunnelDrainageType: null,
        tunnelLocation: null,
        completionTime: null,
        openingDate: null,
        reasonForChange: null,
        tunnelElectronics: null,
        nameOfDesignCompany: null,
        nameOfSupervisoryUnit: null,
        nameOfConstructionUnit: null,
        nameOfSupervisionUnit: null,
        nameOfConstruction: null,
        custodialUnitCode: null,
        nameOfCustodialUnit: null,
        conversionYear: null,
        tunnelReconstructionSite: null,
        recentRenovationProject: null,
        dateOfCompletion: null,
        tunnelTroubleLocation: null,
        tunnelDamageDescription: null,
        tunnelLongitude: null,
        tunnelLatitude: null,
        reportingUnit: null,
        reportingUnitCode: null,
        reportingUnitPrincipal: null,
        reportingPerson: null,
        reportingPersonTelephone: null,
        auditor: null,
        auditorTelephone: null,
        acquisitionTime: null,
        modificationTime: null,
        remarks: null,
        routeId: null,
        longTunnelDirectory: null
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
      this.ids = selection.map(item => item.tunnelId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.flag=false,
      this.reset();
      this.open = true;
      this.title = "添加隧道信息";
    },
    /**
     * 查询隧道详细信息操作
     * @param row
     */
    getSdXq(row){
      this.reset();
      const tunnelId = row.tunnelId || this.ids
      getTunnel(tunnelId).then(response => {
        this.form = response.data;
        this.flag=true,
        this.openXq = true;
        this.title = "隧道详细信息";
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const tunnelId = row.tunnelId || this.ids
      getTunnel(tunnelId).then(response => {
        this.form = response.data;
        this.flag=false,
        this.open = true;
        this.title = "修改隧道信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.tunnelId != null) {
            updateTunnel(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.routeId=this.rou
            addTunnel(this.form).then(response => {
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
      const tunnelIds = row.tunnelId || this.ids;
      this.$modal.confirm('是否确认删除隧道信息编号为"' + tunnelIds + '"的数据项？').then(function() {
        return delTunnel(tunnelIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      console.log(this.queryParams)
      this.download('system/tunnel/export', {...this.queryParams
      }, `tunnel_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
