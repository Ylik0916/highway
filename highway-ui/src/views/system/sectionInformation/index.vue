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
          v-hasPermi="['system:sectionInformation:add']"
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
          v-hasPermi="['system:sectionInformation:remove']"
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
          v-hasPermi="['system:sectionInformation:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="sectionInformationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <!--      <el-table-column label="路线编码" align="center" prop="routecoding" />-->
      <el-table-column label="路段编码" align="center" prop="sectionCoding" />
      <el-table-column label="路段序列号" align="center" prop="sectionSerialNumber" />
<!--      <el-table-column label="起点名称" align="center" prop="originName" />-->
<!--      <el-table-column label="起点经度" align="center" prop="startingLongitude" />-->
<!--      <el-table-column label="起点纬度" align="center" prop="startingLatitude" />-->
      <el-table-column label="起点桩号" align="center" prop="startingPileNumber" />
      <el-table-column label="起点高程" align="center" prop="startingElevation" />
<!--      <el-table-column label="起点是否为分界点" align="center" prop="cutoffPoint">-->
<!--        <template slot-scope="scope">-->
<!--          <dict-tag :options="dict.type.true_false" :value="scope.row.cutoffPoint"/>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="讫点名称" align="center" prop="destinationName" />
      <el-table-column label="讫点桩号" align="center" prop="finishingPost" />
<!--      <el-table-column label="讫点经度" align="center" prop="longitudepoint" />-->
<!--      <el-table-column label="讫点纬度" align="center" prop="latitudedestination" />-->
<!--      <el-table-column label="讫点高程" align="center" prop="elevationdestinationpoint" />-->
<!--      <el-table-column label="讫点是否为分界点" align="center" prop="endpointdemarcationpoint">-->
<!--        <template slot-scope="scope">-->
<!--          <dict-tag :options="dict.type.true_false" :value="scope.row.endpointdemarcationpoint"/>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="路段技术等级" align="center" prop="technicalGradeRoadSection" />-->
<!--      <el-table-column label="路段路面类型" align="center" prop="sectionPavementType" />-->
      <el-table-column label="路段里程(公里)" align="center" prop="roadLength" />
      <el-table-column label="实际里程(公里)" align="center" prop="actualMileage" />
<!--      <el-table-column label="路面宽度" align="center" prop="pavementWidth" />-->
<!--      <el-table-column label="路基宽度" align="center" prop="widthSubgrade" />-->
<!--      <el-table-column label="是否为重复路段" align="center" prop="repeatedSection">-->
<!--        <template slot-scope="scope">-->
<!--          <dict-tag :options="dict.type.true_false" :value="scope.row.repeatedSection"/>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="重复路线编码" align="center" prop="repeatRouteCoding" />-->
<!--      <el-table-column label="所重复路段序列" align="center" prop="sequenceRepeatedSections" />-->
<!--      <el-table-column label="重复路段起点桩号" align="center" prop="repeatStartingPostNumber" />-->
<!--      <el-table-column label="重复路段止点桩号" align="center" prop="repeatStopPostNumber" />-->
<!--      <el-table-column label="可绿化里程(公里)" align="center" prop="greenMileage" />-->
<!--      <el-table-column label="已绿化里程(公里)" align="center" prop="okgreenMileage" />-->
<!--      <el-table-column label="养护里程(公里)" align="center" prop="maintenanceMileage" />-->
<!--      <el-table-column label="是否为城管路段" align="center" prop="chengguanSection">-->
<!--        <template slot-scope="scope">-->
<!--          <dict-tag :options="dict.type.true_false" :value="scope.row.chengguanSection"/>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="是否晴雨通车" align="center" prop="trafficFineRain">-->
<!--        <template slot-scope="scope">-->
<!--          <dict-tag :options="dict.type.true_false" :value="scope.row.trafficFineRain"/>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="是否一幅高速" align="center" prop="highSpeed">-->
<!--        <template slot-scope="scope">-->
<!--          <dict-tag :options="dict.type.true_false" :value="scope.row.highSpeed"/>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="晴雨通车里程(公里)" align="center" prop="fairRainMileage" />-->
<!--      <el-table-column label="路面评定结果" align="center" prop="pavementEvaluationResult" />-->
<!--      <el-table-column label="纳入列养年份" align="center" prop="yearInclusion" />-->
<!--      <el-table-column label="涵洞数量" align="center" prop="numberCulverts" />-->
<!--      <el-table-column label="收费性质" align="center" prop="natureCharge" />-->
<!--      <el-table-column label="是否断头路" align="center" prop="roadBroken">-->
<!--        <template slot-scope="scope">-->
<!--          <dict-tag :options="dict.type.true_false" :value="scope.row.roadBroken"/>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="最近改建时间(年份)" align="center" prop="lastModificationTime" />-->
<!--      <el-table-column label="建成时间(年份)" align="center" prop="completionTime" />-->
<!--      <el-table-column label="管养单位代码" align="center" prop="custodialUnitCode" />-->
<!--      <el-table-column label="管养单位名称" align="center" prop="nameCustodialUnit" />-->
<!--      <el-table-column label="养护类型(按时间分)" align="center" prop="curingType" />-->
<!--      <el-table-column label="养护类型(按资金来源分)" align="center" prop="typeConservation" />-->
<!--      <el-table-column label="变更原因" align="center" prop="reasonChange" />-->
<!--      <el-table-column label="地貌" align="center" prop="landform" />-->
<!--      <el-table-column label="通车日期" align="center" prop="openingDate" />-->
<!--      <el-table-column label="车道数量" align="center" prop="numberLanes" />-->
<!--      <el-table-column label="断链类型" align="center" prop="typeBrokenChain" />-->
<!--      <el-table-column label="原路线编码" align="center" prop="originalRouteCoding" />-->
<!--      <el-table-column label="原路线名称" align="center" prop="originalRouteName" />-->
<!--      <el-table-column label="原路段起点桩号" align="center" prop="ofTheOriginalSection" />-->
<!--      <el-table-column label="原路段讫点桩号" align="center" prop="theOriginalSection" />-->
<!--      <el-table-column label="原路段里程(公里)" align="center" prop="originalRoadLength" />-->
<!--      <el-table-column label="设计时速" align="center" prop="designSpeed" />-->
<!--      <el-table-column label="省际出入口" align="center" prop="interprovincialentrancesexits" />-->
<!--      <el-table-column label="面层厚度(厘米)" align="center" prop="surfaceThickness" />-->
<!--      <el-table-column label="国道桩号传递预留里程(公里)" align="center" prop="nationalHighwayNumber" />-->
<!--      <el-table-column label="是否按干线公路接养" align="center" prop="adoptTrunkRoad">-->
<!--        <template slot-scope="scope">-->
<!--          <dict-tag :options="dict.type.true_false" :value="scope.row.adoptTrunkRoad"/>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="最近一次修复养护年度" align="center" prop="lastyearrepairmaintenance" />-->
<!--      <el-table-column label="填报单位" align="center" prop="reportingUnit" />-->
<!--      <el-table-column label="填报单位代码" align="center" prop="fillUnitCode" />-->
<!--      <el-table-column label="填报单位负责人" align="center" prop="personchargereportingUnit" />-->
<!--      <el-table-column label="填表人" align="center" prop="formfiller" />-->
<!--      <el-table-column label="填表人电话" align="center" prop="telephonenumberperson" />-->
<!--      <el-table-column label="审核人" align="center" prop="auditor" />-->
<!--      <el-table-column label="审核人电话" align="center" prop="auditorTelephone" />-->
<!--      <el-table-column label="采集时间" align="center" prop="acquisitionTime" />-->
<!--      <el-table-column label="修改时间" align="center" prop="modificationTime" />-->
<!--      <el-table-column label="备注" align="center" prop="remarks" />-->
      <el-table-column width="150px" label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-finished"
            @click="getRouteXq(scope.row)"
            v-hasPermi="['system:information:edit']"
          >详情</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:sectionInformation:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:sectionInformation:remove']"
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

    <!-- 详情桥梁信息对话框 -->
    <el-dialog :title="title" :visible.sync="openXq" width="1000px" append-to-body>
      <el-tabs v-model="activeName" type="card">
        <el-tab-pane label="基本信息" name="first">
          <el-descriptions :model="form">
            <el-descriptions-item label="路线编码">{{ form.routecoding }}</el-descriptions-item>
            <el-descriptions-item label="路段序列号">{{ form.sectionSerialNumber }}</el-descriptions-item>
            <el-descriptions-item label="起点名称">{{ form.originName }}</el-descriptions-item>
            <el-descriptions-item label="起点经度">{{ form.startingLongitude }}</el-descriptions-item>
            <el-descriptions-item label="起点纬度">{{ form.routeAdministrativeArea }}</el-descriptions-item>
            <el-descriptions-item label="起点桩号">{{ form.startingPileNumber }}</el-descriptions-item>
            <el-descriptions-item label="起点高程">{{ form.startingElevation }}</el-descriptions-item>
            <el-descriptions-item label="起点是否为分界点">{{ form.cutoffPoint }}</el-descriptions-item>
            <el-descriptions-item label="讫点名称">{{ form.destinationName }}</el-descriptions-item>
            <el-descriptions-item label="讫点桩号">{{ form.finishingPost }}</el-descriptions-item>
            <el-descriptions-item label="讫点经度">{{ form.longitudepoint }}</el-descriptions-item>
            <el-descriptions-item label="讫点纬度">{{ form.latitudedestination }}</el-descriptions-item>
            <el-descriptions-item label="讫点高程">{{ form.elevationdestinationpoint }}</el-descriptions-item>
            <el-descriptions-item label="讫点是否为分界点">{{ form.endpointdemarcationpoint }}</el-descriptions-item>
            <el-descriptions-item label="路段技术等级">{{ form.technicalGradeRoadSection }}</el-descriptions-item>
            <el-descriptions-item label="路段里程(公里)">{{ form.roadLength }}</el-descriptions-item>
            <el-descriptions-item label="实际里程(公里)">{{ form.actualMileage }}</el-descriptions-item>
            <el-descriptions-item label="路面宽度">{{ form.pavementWidth }}</el-descriptions-item>
            <el-descriptions-item label="路基宽度">{{ form.widthSubgrade }}</el-descriptions-item>
            <el-descriptions-item label="是否为重复路段">{{ form.repeatedSection }}</el-descriptions-item>
            <el-descriptions-item label="重复路线编码">{{ form.repeatRouteCoding }}</el-descriptions-item>
            <el-descriptions-item label="所重复路段序列">{{ form.sequenceRepeatedSections }}</el-descriptions-item>
            <el-descriptions-item label="重复路段起点桩号">{{ form.repeatStartingPostNumber }}</el-descriptions-item>
            <el-descriptions-item label="重复路段止点桩号">{{ form.repeatStopPostNumber }}</el-descriptions-item>
            <el-descriptions-item label="已绿化里程(公里)">{{ form.okgreenMileage }}</el-descriptions-item>
            <el-descriptions-item label="养护里程(公里)">{{ form.maintenanceMileage }}</el-descriptions-item>
            <el-descriptions-item label="是否为城管路段">{{ form.chengguanSection }}</el-descriptions-item>
            <el-descriptions-item label="是否晴雨通车">{{ form.trafficFineRain }}</el-descriptions-item>
            <el-descriptions-item label="是否一幅高速">{{ form.highSpeed }}</el-descriptions-item>
            <el-descriptions-item label="晴雨通车里程(公里)">{{ form.fairRainMileage }}</el-descriptions-item>
            <el-descriptions-item label="路面评定结果">{{ form.pavementEvaluationResult }}</el-descriptions-item>
            <el-descriptions-item label="纳入列养年份">{{ form.yearInclusion }}</el-descriptions-item>
            <el-descriptions-item label="涵洞数量">{{ form.numberCulverts }}</el-descriptions-item>
            <el-descriptions-item label="收费性质">{{ form.natureCharge }}</el-descriptions-item>
            <el-descriptions-item label="是否断头路">{{ form.roadBroken }}</el-descriptions-item>
            <el-descriptions-item label="最近改建时间(年份)">{{ form.lastModificationTime }}</el-descriptions-item>
            <el-descriptions-item label="建成时间(年份)">{{ form.completionTime }}</el-descriptions-item>
            <el-descriptions-item label="管养单位代码">{{ form.custodialUnitCode }}</el-descriptions-item>
            <el-descriptions-item label="管养单位名称">{{ form.nameCustodialUnit }}</el-descriptions-item>
            <el-descriptions-item label="养护类型(按资金来源分)">{{ form.typeConservation }}</el-descriptions-item>
            <el-descriptions-item label="变更原因">{{ form.reasonChange }}</el-descriptions-item>
            <el-descriptions-item label="地貌">{{ form.landform }}</el-descriptions-item>
          </el-descriptions>
        </el-tab-pane>
        <el-tab-pane label="养护信息" name="second">
          <el-descriptions :model="form">
            <el-descriptions-item label="通车日期">{{ form.openingDate }}</el-descriptions-item>
            <el-descriptions-item label="车道数量">{{ form.numberLanes }}</el-descriptions-item>
            <el-descriptions-item label="断链类型">{{ form.typeBrokenChain }}</el-descriptions-item>
            <el-descriptions-item label="原路线编码">{{ form.originalRouteCoding }}</el-descriptions-item>
            <el-descriptions-item label="原路线名称">{{ form.originalRouteName }}</el-descriptions-item>
            <el-descriptions-item label="原路段起点桩号">{{ form.ofTheOriginalSection }}</el-descriptions-item>
            <el-descriptions-item label="原路段讫点桩号">{{ form.theOriginalSection }}</el-descriptions-item>
            <el-descriptions-item label="原路段里程(公里)">{{ form.originalRoadLength }}</el-descriptions-item>
            <el-descriptions-item label="设计时速">{{ form.designSpeed }}</el-descriptions-item>
            <el-descriptions-item label="省际出入口">{{ form.interprovincialentrancesexits }}</el-descriptions-item>
            <el-descriptions-item label="面层厚度(厘米)">{{ form.surfaceThickness }}</el-descriptions-item>
            <el-descriptions-item label="国道桩号传递预留里程(公里)">{{ form.nationalHighwayNumber }}</el-descriptions-item>
            <el-descriptions-item label="是否按干线公路接养">{{ form.adoptTrunkRoad }}</el-descriptions-item>
            <el-descriptions-item label="最近一次修复养护年度">{{ form.lastyearrepairmaintenance }}</el-descriptions-item>
            <el-descriptions-item label="填报单位">{{ form.reportingUnit }}</el-descriptions-item>
            <el-descriptions-item label="填报单位代码">{{ form.fillUnitCode }}</el-descriptions-item>
          </el-descriptions>
        </el-tab-pane>
        <el-tab-pane label="其他信息" name="third">
          <el-descriptions :model="form">
            <el-descriptions-item label="填报单位负责人">{{ form.personchargereportingUnit }}</el-descriptions-item>
            <el-descriptions-item label="填表人">{{ form.formfiller }}</el-descriptions-item>
            <el-descriptions-item label="填表人电话">{{ form.telephonenumberperson }}</el-descriptions-item>
            <el-descriptions-item label="审核人">{{ form.auditor }}</el-descriptions-item>
            <el-descriptions-item label="审核人电话">{{ form.auditorTelephone }}</el-descriptions-item>
            <el-descriptions-item label="采集时间">{{ form.acquisitionTime }}</el-descriptions-item>
            <el-descriptions-item label="修改时间">{{ form.modificationTime }}</el-descriptions-item>
            <el-descriptions-item label="备注">{{ form.remarks }}</el-descriptions-item>
          </el-descriptions>
        </el-tab-pane>
      </el-tabs>
    </el-dialog>


        <!-- 添加或修改桥梁信息对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
          <el-form ref="form" :inline="true" :model="form" style="display: flex"  :rules="rules" :label-position="labelPosition"
                   label-width="200px">
          <el-tabs v-model="activeName" type="card" >
        <el-tab-pane label="基本信息" name="first">
          <!--        <el-form-item label="路线ID" prop="routeid">-->
          <!--          <el-input v-model="form.routeid" placeholder="请输入路线ID" />-->
          <!--        </el-form-item>-->
          <el-form-item label="路线编码" prop="routecoding">
            <el-input v-model="form.routecoding" placeholder="请输入路线编码" />
          </el-form-item>
          <el-form-item label="路段编码" prop="sectionCoding">
            <el-input v-model="form.sectionCoding" placeholder="请输入路段编码" />
          </el-form-item>
          <el-form-item label="路段序列号" prop="sectionSerialNumber">
            <el-input v-model="form.sectionSerialNumber" placeholder="请输入路段序列号" />
          </el-form-item>
          <el-form-item label="起点名称" prop="originName">
            <el-input v-model="form.originName" placeholder="请输入起点名称" />
          </el-form-item>
          <el-form-item label="起点经度" prop="startingLongitude">
            <el-input v-model="form.startingLongitude" placeholder="请输入起点经度" />
          </el-form-item>
          <el-form-item label="起点纬度" prop="startingLatitude">
            <el-input v-model="form.startingLatitude" placeholder="请输入起点纬度" />
          </el-form-item>
          <el-form-item label="起点桩号" prop="startingPileNumber">
            <el-input v-model="form.startingPileNumber" placeholder="请输入起点桩号" />
          </el-form-item>
          <el-form-item label="起点高程" prop="startingElevation">
            <el-input v-model="form.startingElevation" placeholder="请输入起点高程" />
          </el-form-item>
          <el-form-item label="起点是否为分界点" prop="cutoffPoint">
            <el-select v-model="form.cutoffPoint" placeholder="请选择起点是否为分界点">
              <el-option
                v-for="dict in dict.type.true_false"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="讫点名称" prop="destinationName">
            <el-input v-model="form.destinationName" placeholder="请输入讫点名称" />
          </el-form-item>
          <el-form-item label="讫点桩号" prop="finishingPost">
            <el-input v-model="form.finishingPost" placeholder="请输入讫点桩号" />
          </el-form-item>
          <el-form-item label="讫点经度" prop="longitudepoint">
            <el-input v-model="form.longitudepoint" placeholder="请输入讫点经度" />
          </el-form-item>
          <el-form-item label="讫点纬度" prop="latitudedestination">
            <el-input v-model="form.latitudedestination" placeholder="请输入讫点纬度" />
          </el-form-item>
          <el-form-item label="讫点高程" prop="elevationdestinationpoint">
            <el-input v-model="form.elevationdestinationpoint" placeholder="请输入讫点高程" />
          </el-form-item>
          <el-form-item label="讫点是否为分界点" prop="endpointdemarcationpoint">
            <el-select v-model="form.endpointdemarcationpoint" placeholder="请选择讫点是否为分界点">
              <el-option
                v-for="dict in dict.type.true_false"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="路段技术等级" prop="technicalGradeRoadSection">
            <el-input v-model="form.technicalGradeRoadSection" placeholder="请输入路段技术等级" />
          </el-form-item>
          <el-form-item label="路段里程(公里)" prop="roadLength">
            <el-input v-model="form.roadLength" placeholder="请输入路段里程(公里)" />
          </el-form-item>
          <el-form-item label="实际里程(公里)" prop="actualMileage">
            <el-input v-model="form.actualMileage" placeholder="请输入实际里程(公里)" />
          </el-form-item>
          <el-form-item label="路面宽度" prop="pavementWidth">
            <el-input v-model="form.pavementWidth" placeholder="请输入路面宽度" />
          </el-form-item>
          <el-form-item label="路基宽度" prop="widthSubgrade">
            <el-input v-model="form.widthSubgrade" placeholder="请输入路基宽度" />
          </el-form-item>
          <el-form-item label="是否为重复路段" prop="repeatedSection">
            <el-select v-model="form.repeatedSection" placeholder="请选择是否为重复路段">
              <el-option
                v-for="dict in dict.type.true_false"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="重复路线编码" prop="repeatRouteCoding">
            <el-input v-model="form.repeatRouteCoding" placeholder="请输入重复路线编码" />
          </el-form-item>
          <el-form-item label="所重复路段序列" prop="sequenceRepeatedSections">
            <el-input v-model="form.sequenceRepeatedSections" placeholder="请输入所重复路段序列" />
          </el-form-item>
          <el-form-item label="重复路段起点桩号" prop="repeatStartingPostNumber">
            <el-input v-model="form.repeatStartingPostNumber" placeholder="请输入重复路段起点桩号" />
          </el-form-item>
          <el-form-item label="重复路段止点桩号" prop="repeatStopPostNumber">
            <el-input v-model="form.repeatStopPostNumber" placeholder="请输入重复路段止点桩号" />
          </el-form-item>
          <el-form-item label="已绿化里程(公里)" prop="okgreenMileage">
            <el-input v-model="form.okgreenMileage" placeholder="请输入已绿化里程(公里)" />
          </el-form-item>
          <el-form-item label="养护里程(公里)" prop="maintenanceMileage">
            <el-input v-model="form.maintenanceMileage" placeholder="请输入养护里程(公里)" />
          </el-form-item>
          <el-form-item label="是否为城管路段" prop="chengguanSection">
            <el-select v-model="form.chengguanSection" placeholder="请选择是否为城管路段">
              <el-option
                v-for="dict in dict.type.true_false"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="是否晴雨通车" prop="trafficFineRain">
            <el-select v-model="form.trafficFineRain" placeholder="请选择是否晴雨通车">
              <el-option
                v-for="dict in dict.type.true_false"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="是否一幅高速" prop="highSpeed">
            <el-select v-model="form.highSpeed" placeholder="请选择是否一幅高速">
              <el-option
                v-for="dict in dict.type.true_false"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="晴雨通车里程(公里)" prop="fairRainMileage">
            <el-input v-model="form.fairRainMileage" placeholder="请输入晴雨通车里程(公里)" />
          </el-form-item>
          <el-form-item label="路面评定结果" prop="pavementEvaluationResult">
            <el-input v-model="form.pavementEvaluationResult" placeholder="请输入路面评定结果" />
          </el-form-item>
          <el-form-item label="纳入列养年份" prop="yearInclusion">
            <el-input v-model="form.yearInclusion" placeholder="请输入纳入列养年份" />
          </el-form-item>
          <el-form-item label="涵洞数量" prop="numberCulverts">
            <el-input v-model="form.numberCulverts" placeholder="请输入涵洞数量" />
          </el-form-item>
          <el-form-item label="收费性质" prop="natureCharge">
            <el-input v-model="form.natureCharge" placeholder="请输入收费性质" />
          </el-form-item>
          <el-form-item label="是否断头路" prop="roadBroken">
            <el-select v-model="form.roadBroken" placeholder="请选择是否断头路">
              <el-option
                v-for="dict in dict.type.true_false"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="最近改建时间(年份)" prop="lastModificationTime">
            <el-input v-model="form.lastModificationTime" placeholder="请输入最近改建时间(年份)" />
          </el-form-item>
          <el-form-item label="建成时间(年份)" prop="completionTime">
            <el-input v-model="form.completionTime" placeholder="请输入建成时间(年份)" />
          </el-form-item>
          <el-form-item label="管养单位代码" prop="custodialUnitCode">
            <el-input v-model="form.custodialUnitCode" placeholder="请输入管养单位代码" />
          </el-form-item>
          <el-form-item label="管养单位名称" prop="nameCustodialUnit">
            <el-input v-model="form.nameCustodialUnit" placeholder="请输入管养单位名称" />
          </el-form-item>
          <el-form-item label="养护类型(按资金来源分)" prop="typeConservation">
            <el-input v-model="form.typeConservation" placeholder="请输入养护类型(按资金来源分)" />
          </el-form-item>
          <el-form-item label="变更原因" prop="reasonChange">
            <el-input v-model="form.reasonChange" placeholder="请输入变更原因" />
          </el-form-item>

        </el-tab-pane>
        <el-tab-pane label="养护信息" name="second">
          <el-form-item label="地貌" prop="landform">
            <el-input v-model="form.landform" placeholder="请输入地貌" />
          </el-form-item>
          <el-form-item label="通车日期" prop="openingDate">
            <el-input v-model="form.openingDate" placeholder="请输入通车日期" />
          </el-form-item>
          <el-form-item label="车道数量" prop="numberLanes">
            <el-input v-model="form.numberLanes" placeholder="请输入车道数量" />
          </el-form-item>
          <el-form-item label="断链类型" prop="typeBrokenChain">
            <el-input v-model="form.typeBrokenChain" placeholder="请输入断链类型" />
          </el-form-item>
          <el-form-item label="原路线编码" prop="originalRouteCoding">
            <el-input v-model="form.originalRouteCoding" placeholder="请输入原路线编码" />
          </el-form-item>
          <el-form-item label="原路线名称" prop="originalRouteName">
            <el-input v-model="form.originalRouteName" placeholder="请输入原路线名称" />
          </el-form-item>
          <el-form-item label="原路段起点桩号" prop="ofTheOriginalSection">
            <el-input v-model="form.ofTheOriginalSection" placeholder="请输入原路段起点桩号" />
          </el-form-item>
          <el-form-item label="原路段讫点桩号" prop="theOriginalSection">
            <el-input v-model="form.theOriginalSection" placeholder="请输入原路段讫点桩号" />
          </el-form-item>
          <el-form-item label="原路段里程(公里)" prop="originalRoadLength">
            <el-input v-model="form.originalRoadLength" placeholder="请输入原路段里程(公里)" />
          </el-form-item>
          <el-form-item label="设计时速" prop="designSpeed">
            <el-input v-model="form.designSpeed" placeholder="请输入设计时速" />
          </el-form-item>
          <el-form-item label="省际出入口" prop="interprovincialentrancesexits">
            <el-input v-model="form.interprovincialentrancesexits" placeholder="请输入省际出入口" />
          </el-form-item>
          <el-form-item label="面层厚度(厘米)" prop="surfaceThickness">
            <el-input v-model="form.surfaceThickness" placeholder="请输入面层厚度(厘米)" />
          </el-form-item>
          <el-form-item label="国道桩号传递预留里程(公里)" prop="nationalHighwayNumber">
            <el-input v-model="form.nationalHighwayNumber" placeholder="请输入国道桩号传递预留里程(公里)" />
          </el-form-item>
          <el-form-item label="是否按干线公路接养" prop="adoptTrunkRoad">
            <el-select v-model="form.adoptTrunkRoad" placeholder="请选择是否按干线公路接养">
              <el-option
                v-for="dict in dict.type.true_false"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="最近一次修复养护年度" prop="lastyearrepairmaintenance">
            <el-input v-model="form.lastyearrepairmaintenance" placeholder="请输入最近一次修复养护年度" />
          </el-form-item>
          <el-form-item label="填报单位" prop="reportingUnit">
            <el-input v-model="form.reportingUnit" placeholder="请输入填报单位" />
          </el-form-item>
          <el-form-item label="填报单位代码" prop="fillUnitCode">
            <el-input v-model="form.fillUnitCode" placeholder="请输入填报单位代码" />
          </el-form-item>
        </el-tab-pane>
        <el-tab-pane label="其他信息" name="third">
          <el-form-item label="填报单位负责人" prop="personchargereportingUnit">
            <el-input v-model="form.personchargereportingUnit" placeholder="请输入填报单位负责人" />
          </el-form-item>
          <el-form-item label="填表人" prop="formfiller">
            <el-input v-model="form.formfiller" placeholder="请输入填表人" />
          </el-form-item>
          <el-form-item label="填表人电话" prop="telephonenumberperson">
            <el-input v-model="form.telephonenumberperson" placeholder="请输入填表人电话" />
          </el-form-item>
          <el-form-item label="审核人" prop="auditor">
            <el-input v-model="form.auditor" placeholder="请输入审核人" />
          </el-form-item>
          <el-form-item label="审核人电话" prop="auditorTelephone">
            <el-input v-model="form.auditorTelephone" placeholder="请输入审核人电话" />
          </el-form-item>
          <el-form-item label="采集时间" prop="acquisitionTime">
            <el-input v-model="form.acquisitionTime" placeholder="请输入采集时间" />
          </el-form-item>
          <el-form-item label="修改时间" prop="modificationTime">
            <el-input v-model="form.modificationTime" placeholder="请输入修改时间" />
          </el-form-item>
          <el-form-item label="备注" prop="remarks">
            <el-input v-model="form.remarks" placeholder="请输入备注" />
          </el-form-item>
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
import { listSectionInformation, getSectionInformation, delSectionInformation, addSectionInformation, updateSectionInformation } from "@/api/system/sectionInformation";

export default {
  name: "SectionInformation",
  dicts: ['true_false'],
  props:['routeId'],
  data() {
    return {
      // routeId:this.routeId,
      activeName:'first',
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
      // 路段信息表格数据
      sectionInformationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否显示详情弹出层
      openXq: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        routeid: this.routeId,
        routecoding: null,
        sectionCoding: null,
        sectionSerialNumber: null,
        originName: null,
        startingLongitude: null,
        startingLatitude: null,
        startingPileNumber: null,
        startingElevation: null,
        cutoffPoint: null,
        destinationName: null,
        finishingPost: null,
        longitudepoint: null,
        latitudedestination: null,
        elevationdestinationpoint: null,
        endpointdemarcationpoint: null,
        technicalGradeRoadSection: null,
        sectionPavementType: null,
        roadLength: null,
        actualMileage: null,
        pavementWidth: null,
        widthSubgrade: null,
        repeatedSection: null,
        repeatRouteCoding: null,
        sequenceRepeatedSections: null,
        repeatStartingPostNumber: null,
        repeatStopPostNumber: null,
        okgreenMileage: null,
        maintenanceMileage: null,
        chengguanSection: null,
        trafficFineRain: null,
        highSpeed: null,
        fairRainMileage: null,
        pavementEvaluationResult: null,
        yearInclusion: null,
        numberCulverts: null,
        natureCharge: null,
        roadBroken: null,
        lastModificationTime: null,
        completionTime: null,
        custodialUnitCode: null,
        nameCustodialUnit: null,
        curingType: null,
        typeConservation: null,
        reasonChange: null,
        landform: null,
        openingDate: null,
        numberLanes: null,
        typeBrokenChain: null,
        originalRouteCoding: null,
        originalRouteName: null,
        ofTheOriginalSection: null,
        theOriginalSection: null,
        originalRoadLength: null,
        designSpeed: null,
        interprovincialentrancesexits: null,
        surfaceThickness: null,
        nationalHighwayNumber: null,
        adoptTrunkRoad: null,
        lastyearrepairmaintenance: null,
        reportingUnit: null,
        fillUnitCode: null,
        personchargereportingUnit: null,
        formfiller: null,
        telephonenumberperson: null,
        auditor: null,
        auditorTelephone: null,
        acquisitionTime: null,
        modificationTime: null,
        remarks: null
      },
      labelPosition: 'top',
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        destinationName: [
          { required: true, message: "讫点名称不能为空", trigger: "blur" }
        ],
        finishingPost: [
          { required: true, message: "讫点桩号不能为空", trigger: "blur" }
        ],
        startingPileNumber: [
          { required: true, message: "起点桩号不能为空", trigger: "blur" }
        ],
        routecoding: [
          { required: true, message: "路线编码不能为空", trigger: "blur" }
        ],
        sectionCoding: [
          { required: true, message: "路段编码不能为空", trigger: "blur" }
        ],
        sectionSerialNumber: [
          { required: true, message: "路段序列号不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询路段信息列表 */
    getList() {
      this.loading = true;
      listSectionInformation(this.queryParams).then(response => {
        this.sectionInformationList = response.rows;
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
        roadsectionid: null,
        routeid: null,
        routecoding: null,
        sectionCoding: null,
        sectionSerialNumber: null,
        originName: null,
        startingLongitude: null,
        startingLatitude: null,
        startingPileNumber: null,
        startingElevation: null,
        cutoffPoint: null,
        destinationName: null,
        finishingPost: null,
        longitudepoint: null,
        latitudedestination: null,
        elevationdestinationpoint: null,
        endpointdemarcationpoint: null,
        technicalGradeRoadSection: null,
        sectionPavementType: null,
        roadLength: null,
        actualMileage: null,
        pavementWidth: null,
        widthSubgrade: null,
        repeatedSection: null,
        repeatRouteCoding: null,
        sequenceRepeatedSections: null,
        repeatStartingPostNumber: null,
        repeatStopPostNumber: null,
        greenMileage: null,
        okgreenMileage: null,
        maintenanceMileage: null,
        chengguanSection: null,
        trafficFineRain: null,
        highSpeed: null,
        fairRainMileage: null,
        pavementEvaluationResult: null,
        yearInclusion: null,
        numberCulverts: null,
        natureCharge: null,
        roadBroken: null,
        lastModificationTime: null,
        completionTime: null,
        custodialUnitCode: null,
        nameCustodialUnit: null,
        curingType: null,
        typeConservation: null,
        reasonChange: null,
        landform: null,
        openingDate: null,
        numberLanes: null,
        typeBrokenChain: null,
        originalRouteCoding: null,
        originalRouteName: null,
        ofTheOriginalSection: null,
        theOriginalSection: null,
        originalRoadLength: null,
        designSpeed: null,
        interprovincialentrancesexits: null,
        surfaceThickness: null,
        nationalHighwayNumber: null,
        adoptTrunkRoad: null,
        lastyearrepairmaintenance: null,
        reportingUnit: null,
        fillUnitCode: null,
        personchargereportingUnit: null,
        formfiller: null,
        telephonenumberperson: null,
        auditor: null,
        auditorTelephone: null,
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
      this.ids = selection.map(item => item.roadsectionid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加路段信息";
    },
    /** 详情按钮操作 */
    getRouteXq(row) {
      const roadsectionid = row.roadsectionid || this.ids
      getSectionInformation(roadsectionid).then(response => {
        this.form = response.data;
        this.openXq = true;
        this.title = "路段信息详情";
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const roadsectionid = row.roadsectionid || this.ids
      getSectionInformation(roadsectionid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改路段信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.roadsectionid != null) {
            updateSectionInformation(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.routeid=this.routeId
            addSectionInformation(this.form).then(response => {
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
      const roadsectionids = row.roadsectionid || this.ids;
      this.$modal.confirm('是否确认删除路段信息编号为"' + roadsectionids + '"的数据项？').then(function() {
        return delSectionInformation(roadsectionids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/sectionInformation/export', {
        ...this.queryParams
      }, `sectionInformation_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

