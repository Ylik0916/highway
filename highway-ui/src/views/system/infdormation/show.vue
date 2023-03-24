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
              v-hasPermi="['system:infdormation:add']"
            >新增
            </el-button>
          </el-col>
<!--          <el-col :span="1.5">-->
<!--            <el-button-->
<!--              type="success"-->
<!--              plain-->
<!--              icon="el-icon-edit"-->
<!--              size="mini"-->
<!--              :disabled="single"-->
<!--              @click="handleUpdate"-->
<!--              v-hasPermi="['system:infdormation:edit']"-->
<!--            >修改-->
<!--            </el-button>-->
<!--          </el-col>-->
          <el-col :span="1.5">
            <el-button
              type="danger"
              plain
              icon="el-icon-delete"
              size="mini"
              :disabled="multiple"
              @click="handleDelete"
              v-hasPermi="['system:infdormation:remove']"
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
              v-hasPermi="['system:infdormation:export']"
            >导出
            </el-button>
          </el-col>
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
          <el-table-column width="150px" label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-finished"
                @click="handledetails(scope.row)"
                v-hasPermi="['system:infdormation:edit']"
              >详情
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['system:infdormation:edit']"
              >修改
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['system:infdormation:remove']"
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
        <!-- 桥梁信息对话框 -->
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

        <!-- 添加或修改桥梁信息对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
          <el-form ref="form" :inline="true" :model="form" :rules="rules" :label-position="labelPosition"
                   label-width="200px">
            <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
              <el-tab-pane label="基本信息" name="first">
                <el-row>
                  <el-col :span="6">
                    <div class="grid-content bg-purple">
                      <el-form-item label="选择路线" prop="selectRoute">
                        <el-input v-model="form.selectRoute" :disabled="true" placeholder="请输入选择路线"/>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="桥梁编码" prop="routeCode">
                        <el-input v-model="form.routeCode" placeholder="请输入桥梁编码"/>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple">
                      <el-form-item label="桥梁名称" prop="routeName">
                        <el-input v-model="form.routeName" placeholder="请输入桥梁名称"/>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="行政区划" prop="routeAdministrativeArea">
                        <treeselect style="width: 200px" v-model="form.routeAdministrativeArea" :options="ordinaryOptions" :normalizer="normalizer" placeholder="请选择行政区" />
                      </el-form-item>
                    </div>
                  </el-col>
                </el-row>

                <el-row>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="桥梁经度" prop="routeLongitude">
                        <el-input v-model="form.routeLongitude" placeholder="请输入桥梁经度"/>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="桥梁纬度" prop="routeLatitude">
                        <el-input v-model="form.routeLatitude" placeholder="请输入桥梁纬度"/>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="桥梁程高" prop="routeHigh">
                        <el-input v-model="form.routeHigh" placeholder="请输入桥梁程高"/>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="桥梁长度" prop="routeLong">
                        <el-input v-model="form.routeLong" placeholder="请输入桥梁长度"/>
                      </el-form-item>
                    </div>
                  </el-col>
                </el-row>

                <el-row>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="跨境总长" prop="routeCrossLong">
                        <el-input v-model="form.routeCrossLong" placeholder="请输入跨境总长"/>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="单孔最大跨径(米)" prop="routeSingleLong">
                        <el-input v-model="form.routeSingleLong" placeholder="请输入单孔最大跨径(米)"/>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="桥梁跨境组合" prop="routeSpanCombination">
                        <el-input v-model="form.routeSpanCombination" placeholder="请输入桥梁跨境组合"/>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="桥面全宽" prop="routeWide">
                        <el-input v-model="form.routeWide" placeholder="请输入桥面全宽"/>
                      </el-form-item>
                    </div>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="桥梁全长" prop="routeOverallLength">
                        <el-input v-model="form.routeOverallLength" placeholder="请输入桥梁全长"/>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="桥面净宽(米)" prop="routeClearWidth">
                        <el-input v-model="form.routeClearWidth" placeholder="请输入桥面净宽(米)"/>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="设计荷载" prop="routeLoad">
                        <el-select v-model="form.routeLoad" placeholder="请选择设计荷载">
                          <el-option
                            v-for="dict in dict.type.bridge_load"
                            :key="dict.value"
                            :label="dict.label"
                            :value="dict.value"
                          ></el-option>
                        </el-select>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="跨境分类" prop="routeCross">
                        <el-select v-model="form.routeCross" placeholder="请选择跨境分类">
                          <el-option
                            v-for="dict in dict.type.bridge_cross"
                            :key="dict.value"
                            :label="dict.label"
                            :value="dict.value"
                          ></el-option>
                        </el-select>
                      </el-form-item>
                    </div>
                  </el-col>
                </el-row>

                <el-row>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="按建筑材料和使用年限分" prop="routeYear">
                        <el-select v-model="form.routeYear" placeholder="请选择按建筑材料和使用年限分">
                          <el-option
                            v-for="dict in dict.type.bridge_age_limit"
                            :key="dict.value"
                            :label="dict.label"
                            :value="dict.value"
                          ></el-option>
                        </el-select>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="是否危桥" prop="routeDanger">
                        <el-select v-model="form.routeDanger" placeholder="请选择是否危桥">
                          <el-option
                            v-for="dict in dict.type.underwater_tunnel_or_not"
                            :key="dict.value"
                            :label="dict.label"
                            :value="dict.value"
                          ></el-option>
                        </el-select>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="评定等级" prop="routeGrade">
                        <el-select v-model="form.routeGrade" placeholder="请选择评定等级">
                          <el-option
                            v-for="dict in dict.type.technical_evaluation"
                            :key="dict.value"
                            :label="dict.label"
                            :value="dict.value"
                          ></el-option>
                        </el-select>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="评定日期" prop="routeEvaluationDate">
                        <el-date-picker clearable
                                        v-model="form.routeEvaluationDate"
                                        type="date"
                                        value-format="yyyy-MM-dd"
                                        placeholder="请选择评定日期">
                        </el-date-picker>
                      </el-form-item>
                    </div>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="上部结构材料" prop="routeTopMaterial">
                        <el-select v-model="form.routeTopMaterial" placeholder="请选择上部结构材料">
                          <el-option
                            v-for="dict in dict.type.top_material"
                            :key="dict.value"
                            :label="dict.label"
                            :value="dict.value"
                          ></el-option>
                        </el-select>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="立交桥类别" prop="routeType">
                        <el-select v-model="form.routeType" placeholder="请选择立交桥类别">
                          <el-option
                            v-for="dict in dict.type.bridge_sort"
                            :key="dict.value"
                            :label="dict.label"
                            :value="dict.value"
                          ></el-option>
                        </el-select>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="桥墩类型" prop="routePierType">
                        <el-select v-model="form.routePierType" placeholder="请选择桥墩类型">
                          <el-option
                            v-for="dict in dict.type.pier_sort"
                            :key="dict.value"
                            :label="dict.label"
                            :value="dict.value"
                          ></el-option>
                        </el-select>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="收费性质" prop="routeCharge">
                        <el-select v-model="form.routeCharge" placeholder="请选择收费性质">
                          <el-option
                            v-for="dict in dict.type.collect_fees"
                            :key="dict.value"
                            :label="dict.label"
                            :value="dict.value"
                          ></el-option>
                        </el-select>
                      </el-form-item>
                    </div>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="主桥上部构造结构形式" prop="routeTopShape">
                        <el-select v-model="form.routeTopShape" placeholder="请选择主桥上部构造结构形式">
                          <el-option
                            v-for="dict in dict.type.top_shap"
                            :key="dict.value"
                            :label="dict.label"
                            :value="dict.value"
                          ></el-option>
                        </el-select>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="原桥梁编码" prop="routeBeforeCode">
                        <el-input v-model="form.routeBeforeCode" placeholder="请输入原桥梁编码"/>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="建成时间(年份)" prop="routeCompletionTime">
                        <el-date-picker clearable
                                        v-model="form.routeCompletionTime"
                                        type="date"
                                        value-format="yyyy-MM-dd"
                                        placeholder="请选择建成时间(年份)">
                        </el-date-picker>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="跨越地物类型" prop="routeCrossFigure">
                        <el-select v-model="form.routeCrossFigure" placeholder="请选择跨越地物类型">
                          <el-option
                            v-for="dict in dict.type.species"
                            :key="dict.value"
                            :label="dict.label"
                            :value="dict.value"
                          ></el-option>
                        </el-select>
                      </el-form-item>
                    </div>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="跨越地物名称" prop="routeCrossName">
                        <el-input v-model="form.routeCrossName" placeholder="请输入跨越地物名称"/>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="主要病害位置" prop="routeDiseasePlace">
                        <el-select v-model="form.routeDiseasePlace" placeholder="请选择主要病害位置">
                          <el-option
                            v-for="dict in dict.type.disease_location"
                            :key="dict.value"
                            :label="dict.label"
                            :value="dict.value"
                          ></el-option>
                        </el-select>
                      </el-form-item>
                    </div>
                  </el-col>

                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="墩台防撞设施类型" prop="routeAnticollisionType">
                        <el-select v-model="form.routeAnticollisionType" placeholder="请选择墩台防撞设施类型">
                          <el-option
                            v-for="dict in dict.type.anti_collision_type"
                            :key="dict.value"
                            :label="dict.label"
                            :value="dict.value"
                          ></el-option>
                        </el-select>
                      </el-form-item>
                    </div>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="抗震等级" prop="routeAntiseismic">
                        <el-select v-model="form.routeAntiseismic" placeholder="请选择抗震等级">
                          <el-option
                            v-for="dict in dict.type.seismic_grade"
                            :key="dict.value"
                            :label="dict.label"
                            :value="dict.value"
                          ></el-option>
                        </el-select>
                      </el-form-item>
                    </div>
                  </el-col>

                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="通航等级" prop="routeFlight">
                        <el-select v-model="form.routeFlight" placeholder="请选择通航等级">
                          <el-option
                            v-for="dict in dict.type.navigation_level"
                            :key="dict.value"
                            :label="dict.label"
                            :value="dict.value"
                          ></el-option>
                        </el-select>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="改建年度" prop="routeRebuild">
                        <el-date-picker clearable
                                        v-model="form.routeRebuild"
                                        type="date"
                                        value-format="yyyy-MM-dd"
                                        placeholder="请选择改建年度">
                        </el-date-picker>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="改造完工日期" prop="routeRebuildEnd">
                        <el-date-picker clearable
                                        v-model="form.routeRebuildEnd"
                                        type="date"
                                        value-format="yyyy-MM-dd"
                                        placeholder="请选择改造完工日期">
                        </el-date-picker>
                      </el-form-item>
                    </div>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="改造部位" prop="routeRebuildPlace">
                        <el-select v-model="form.routeRebuildPlace" placeholder="请选择改造部位">
                          <el-option
                            v-for="dict in dict.type.reconstruction_part"
                            :key="dict.value"
                            :label="dict.label"
                            :value="dict.value"
                          ></el-option>
                        </el-select>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="最近改造工程性质" prop="routeRebuildNature">
                        <el-select v-model="form.routeRebuildNature" placeholder="请选择最近改造工程性质">
                          <el-option
                            v-for="dict in dict.type.transform"
                            :key="dict.value"
                            :label="dict.label"
                            :value="dict.value"
                          ></el-option>
                        </el-select>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="是否部补助项目" prop="routeSubsidy">
                        <el-select v-model="form.routeSubsidy" placeholder="请选择是否部补助项目">
                          <el-option
                            v-for="dict in dict.type.underwater_tunnel_or_not"
                            :key="dict.value"
                            :label="dict.label"
                            :value="dict.value"
                          ></el-option>
                        </el-select>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="已采取交通管制措施" prop="routeMethod">
                        <el-input v-model="form.routeMethod" placeholder="请输入已采取交通管制措施"/>
                      </el-form-item>
                    </div>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="桥梁所在位置" prop="routePosition">
                        <el-select v-model="form.routePosition" placeholder="请选择桥梁所在位置">
                          <el-option
                            v-for="dict in dict.type.bridge_location"
                            :key="dict.value"
                            :label="dict.label"
                            :value="dict.value"
                          ></el-option>
                        </el-select>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="是否宽路窄桥" prop="routeWroadNbridge">
                        <el-select v-model="form.routeWroadNbridge" placeholder="请选择是否宽路窄桥">
                          <el-option
                            v-for="dict in dict.type.underwater_tunnel_or_not"
                            :key="dict.value"
                            :label="dict.label"
                            :value="dict.value"
                          ></el-option>
                        </el-select>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="是否在长大桥梁目录" prop="routeLongBridges">
                        <el-select v-model="form.routeLongBridges" placeholder="请选择是否在长大桥梁目录">
                          <el-option
                            v-for="dict in dict.type.underwater_tunnel_or_not"
                            :key="dict.value"
                            :label="dict.label"
                            :value="dict.value"
                          ></el-option>
                        </el-select>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="下部结构形式" prop="routeBottomShape">
                        <el-select v-model="form.routeBottomShape" placeholder="请选择下部结构形式">
                          <el-option
                            v-for="dict in dict.type.bottom_shap"
                            :key="dict.value"
                            :label="dict.label"
                            :value="dict.value"
                          ></el-option>
                        </el-select>
                      </el-form-item>
                    </div>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="导入评定结果" prop="routeResult">
                        <el-input v-model="form.routeResult" placeholder="请输入导入评定结果"/>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="变更原因" prop="routeReason">
                        <el-select v-model="form.routeReason" placeholder="请选择变更原因">
                          <el-option
                            v-for="dict in dict.type.change_reason"
                            :key="dict.value"
                            :label="dict.label"
                            :value="dict.value"
                          ></el-option>
                        </el-select>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="支座类型" prop="routeSupport">
                        <el-select v-model="form.routeSupport" placeholder="请选择支座类型">
                          <el-option
                            v-for="dict in dict.type.bearing_type"
                            :key="dict.value"
                            :label="dict.label"
                            :value="dict.value"
                          ></el-option>
                        </el-select>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="通车日期" prop="routeTrafficDate">
                        <el-date-picker clearable
                                        v-model="form.routeTrafficDate"
                                        type="date"
                                        value-format="yyyy-MM-dd"
                                        placeholder="请选择通车日期">
                        </el-date-picker>
                      </el-form-item>
                    </div>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="改造施工单位" prop="routeRenovationUnit">
                        <el-input v-model="form.routeRenovationUnit" placeholder="请输入改造施工单位"/>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="桥台类型" prop="routeAbutmentType">
                        <el-select v-model="form.routeAbutmentType" placeholder="请选择桥台类型">
                          <el-option
                            v-for="dict in dict.type.abutment_type"
                            :key="dict.value"
                            :label="dict.label"
                            :value="dict.value"
                          ></el-option>
                        </el-select>
                      </el-form-item>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple-light">
                      <el-form-item label="主要病害描述" prop="routeDiseaseDescribe">
                        <el-input v-model="form.routeDiseaseDescribe" type="textarea" placeholder="请输入内容"/>
                      </el-form-item>
                    </div>
                  </el-col>
                </el-row>
              </el-tab-pane>
              <el-tab-pane label="建养信息" name="second">
                <el-col :span="6">
                  <div class="grid-content bg-purple-light">
                    <el-form-item label="设计单位名称" prop="routeDesigner">
                      <el-input v-model="form.routeDesigner" placeholder="请输入设计单位名称"/>
                    </el-form-item>
                  </div>
                </el-col>

                <el-col :span="6">
                  <div class="grid-content bg-purple-light">
                    <el-form-item label="评定单位" prop="evaluationUnit">
                      <el-input v-model="form.evaluationUnit" placeholder="请输入评定单位"/>
                    </el-form-item>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div class="grid-content bg-purple-light">
                    <el-form-item label="施工单位名称" prop="routeConstructionUnit">
                      <el-input v-model="form.routeConstructionUnit" placeholder="请输入施工单位名称"/>
                    </el-form-item>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div class="grid-content bg-purple-light">
                    <el-form-item label="监理单位名称" prop="routeInspectionUnit">
                      <el-input v-model="form.routeInspectionUnit" placeholder="请输入监理单位名称"/>
                    </el-form-item>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div class="grid-content bg-purple-light">
                    <el-form-item label="建设单位名称" prop="routeBuildUnit">
                      <el-input v-model="form.routeBuildUnit" placeholder="请输入建设单位名称"/>
                    </el-form-item>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div class="grid-content bg-purple-light">
                    <el-form-item label="监管单位名称" prop="routeSupervise">
                      <el-input v-model="form.routeSupervise" placeholder="请输入监管单位名称"/>
                    </el-form-item>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div class="grid-content bg-purple-light">
                    <el-form-item label="管养单位性质" prop="routeCuring">
                      <el-select v-model="form.routeCuring" placeholder="请选择管养单位性质">
                        <el-option
                          v-for="dict in dict.type.management_maintenance"
                          :key="dict.value"
                          :label="dict.label"
                          :value="dict.value"
                        ></el-option>
                      </el-select>
                    </el-form-item>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div class="grid-content bg-purple-light">
                    <el-form-item label="管养单位代码" prop="routeCuringCode">
                      <el-input v-model="form.routeCuringCode" placeholder="请输入管养单位代码"/>
                    </el-form-item>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div class="grid-content bg-purple-light">
                    <el-form-item label="管养单位名称" prop="routeCuringName">
                      <el-input v-model="form.routeCuringName" placeholder="请输入管养单位名称"/>
                    </el-form-item>
                  </div>
                </el-col>
              </el-tab-pane>
              <el-tab-pane label="填报信息" name="third">
                <el-col :span="6">
                  <div class="grid-content bg-purple-light">
                    <el-form-item label="填报单位" prop="fillingUnit">
                      <el-input v-model="form.fillingUnit" placeholder="请输入填报单位"/>
                    </el-form-item>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div class="grid-content bg-purple-light">
                    <el-form-item label="填报单位代码" prop="fillingUnitCode">
                      <el-input v-model="form.fillingUnitCode" placeholder="请输入填报单位代码"/>
                    </el-form-item>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div class="grid-content bg-purple-light">
                    <el-form-item label="填报单位负责人" prop="fillingUnitLeader">
                      <el-input v-model="form.fillingUnitLeader" placeholder="请输入填报单位负责人"/>
                    </el-form-item>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div class="grid-content bg-purple-light">
                    <el-form-item label="填表人" prop="preparer">
                      <el-input v-model="form.preparer" placeholder="请输入填表人"/>
                    </el-form-item>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div class="grid-content bg-purple-light">
                    <el-form-item label="填表人电话" prop="preparerTel">
                      <el-input v-model="form.preparerTel" placeholder="请输入填表人电话"/>
                    </el-form-item>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div class="grid-content bg-purple-light">
                    <el-form-item label="审核人" prop="process">
                      <el-input v-model="form.process" placeholder="请输入审核人"/>
                    </el-form-item>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div class="grid-content bg-purple-light">
                    <el-form-item label="审核人电话" prop="processTel">
                      <el-input v-model="form.processTel" placeholder="请输入审核人电话"/>
                    </el-form-item>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div class="grid-content bg-purple-light">
                    <el-form-item label="采集时间" prop="acquisitionTime">
                      <el-date-picker clearable
                                      v-model="form.acquisitionTime"
                                      type="date"
                                      value-format="yyyy-MM-dd"
                                      placeholder="请选择采集时间">
                      </el-date-picker>
                    </el-form-item>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div class="grid-content bg-purple-light">
                    <el-form-item label="备注" prop="remarks">
                      <el-input v-model="form.remarks" type="textarea" placeholder="请输入内容"/>
                    </el-form-item>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div class="grid-content bg-purple-light">
                    <el-form-item label="中心桩号" prop="centerStake">
                      <el-input v-model="form.centerStake" placeholder="请输入中心桩号"/>
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
</template>

<script>
import {
  listInfdormation,
  getInfdormation,
  delInfdormation,
  addInfdormation,
  updateInfdormation
} from "@/api/system/infdormation";
import {listDept} from "@/api/system/dept";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
export default {
  props: ["vis", "routeId"],
  name: "Infdormation",
  dicts: ['seismic_grade', 'top_shap', 'bottom_shap', 'disease_location', 'transform', 'bearing_type', 'bridge_sort', 'bridge_load', 'sys_yes_no', 'navigation_level', 'management_maintenance', 'technical_evaluation', 'bridge_location', 'change_reason', 'abutment_type', 'bridge_zoning', 'bridge_cross', 'anti_collision_type', 'bridge_age_limit', 'top_material', 'species', 'pier_sort', 'collect_fees', 'reconstruction_part', 'underwater_tunnel_or_not'],
  components: {
    Treeselect
  },
  data() {
    return {
      ordinaryOptions: [],
      flag: this.vis,
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
        luCode: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        routeCode: [
          {required: true, message: "桥梁编码不能为空", trigger: "blur"}
        ],
        routeName: [
          {required: true, message: "桥梁名称不能为空", trigger: "blur"}
        ],
        routeLongitude: [
          {required: true, message: "桥梁经度不能为空", trigger: "blur"}
        ],
        routeLatitude: [
          {required: true, message: "桥梁纬度不能为空", trigger: "blur"}
        ],
        routeLong: [
          {required: true, message: "桥梁长度不能为空", trigger: "blur"}
        ],
        routeCrossLong: [
          {required: true, message: "跨境总长不能为空", trigger: "blur"}
        ],
        routeOverallLength: [
          {required: true, message: "桥梁全长不能为空", trigger: "blur"}
        ],
        routeClearWidth: [
          {required: true, message: "桥面净宽(米)不能为空", trigger: "blur"}
        ],
        routeCross: [
          {required: true, message: "跨境分类不能为空", trigger: "change"}
        ],
        routeYear: [
          {required: true, message: "按建筑材料和使用年限分不能为空", trigger: "change"}
        ],
        routeGrade: [
          {required: true, message: "评定等级不能为空", trigger: "change"}
        ],
        routeEvaluationDate: [
          {required: true, message: "评定日期不能为空", trigger: "blur"}
        ],
        routeTopShape: [
          {required: true, message: "主桥上部构造结构形式不能为空", trigger: "change"}
        ],
        routeType: [
          {required: true, message: "立交桥类别不能为空", trigger: "change"}
        ],
        routeRebuild: [
          {required: true, message: "改建年度不能为空", trigger: "blur"}
        ],
        routeMethod: [
          {required: true, message: "已采取交通管制措施不能为空", trigger: "blur"}
        ],
        routeTrafficDate: [
          {required: true, message: "通车日期不能为空", trigger: "blur"}
        ],
      }
    };
  },
  created() {
    this.getTreeselect();
    this.getList();
  },
  methods: {
    //标签页
    handleClick(tab, event) {
      console.log(tab, event);
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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加桥梁信息";
    },
    /**详情按钮操作*/
    handledetails(row) {
      this.reset();
      const routeId = row.routeId || this.ids
      getInfdormation(routeId).then(response => {
        this.form = response.data;
        this.opens = true;
        this.title = "桥梁信息";
        console.log(response.data);
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const routeId = row.routeId || this.ids
      getInfdormation(routeId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改桥梁信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.routeId != null) {
            this.form.luId = this.routeId
            updateInfdormation(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.luId = this.routeId
            addInfdormation(this.form).then(response => {
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
      const routeIds = row.routeId || this.ids;
      this.$modal.confirm('是否确认删除桥梁信息编号为"' + routeIds + '"的数据项？').then(function () {
        return delInfdormation(routeIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/infdormation/export', {
        ...this.queryParams
      }, `infdormation_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
