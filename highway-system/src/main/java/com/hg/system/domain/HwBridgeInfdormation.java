package com.hg.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hg.common.annotation.Excel;
import com.hg.common.core.domain.BaseEntity;

/**
 * 桥梁信息对象 hw_bridge_infdormation
 *
 * @author ruoyi
 * @date 2023-03-14
 */
public class HwBridgeInfdormation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 桥梁id */
    private Long routeId;

    private Integer luId;


    /** 桥梁编码 */
    @Excel(name = "桥梁编码")
    private String routeCode;

    /** 桥梁名称 */
    @Excel(name = "桥梁名称")
    private String routeName;

    /** 桥梁经度 */
    @Excel(name = "桥梁经度")
    private String routeLongitude;

    /** 桥梁纬度 */
    @Excel(name = "桥梁纬度")
    private String routeLatitude;

    /** 桥梁程高 */
    @Excel(name = "桥梁程高")
    private String routeHigh;

    /** 桥梁长度 */
    @Excel(name = "桥梁长度")
    private String routeLong;

    /** 跨境总长 */
    @Excel(name = "跨境总长")
    private String routeCrossLong;

    /** 单孔最大跨径(米) */
    @Excel(name = "单孔最大跨径(米)")
    private String routeSingleLong;

    /** 桥梁跨境组合（孔*米） */
    @Excel(name = "桥梁跨境组合", readConverterExp = "孔=*米")
    private String routeSpanCombination;

    /** 桥面全宽 */
    @Excel(name = "桥面全宽")
    private String routeWide;

    /** 桥梁全长 */
    @Excel(name = "桥梁全长")
    private String routeOverallLength;

    /** 桥面净宽(米) */
    @Excel(name = "桥面净宽(米)")
    private String routeClearWidth;

    /** 设计荷载 */
    @Excel(name = "设计荷载")
    private String routeLoad;

    /** 跨境分类 */
    @Excel(name = "跨境分类")
    private String routeCross;

    /** 按建筑材料和使用年限分 */
    @Excel(name = "按建筑材料和使用年限分")
    private String routeYear;

    /** 是否危桥 */
    @Excel(name = "是否危桥")
    private String routeDanger;

    /** 评定等级 */
    @Excel(name = "评定等级")
    private String routeGrade;

    /** 评定日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "评定日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date routeEvaluationDate;

    /** 主桥上部构造结构形式 */
    @Excel(name = "主桥上部构造结构形式")
    private String routeTopShape;

    /** 上部结构材料 */
    @Excel(name = "上部结构材料")
    private String routeTopMaterial;

    /** 立交桥类别 */
    @Excel(name = "立交桥类别")
    private String routeType;

    /** 桥墩类型 */
    @Excel(name = "桥墩类型")
    private String routePierType;

    /** 收费性质 */
    @Excel(name = "收费性质")
    private String routeCharge;

    /** 原桥梁编码 */
    @Excel(name = "原桥梁编码")
    private String routeBeforeCode;

    /** 建成时间(年份) */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "建成时间(年份)", width = 30, dateFormat = "yyyy-MM-dd")
    private Date routeCompletionTime;

    /** 跨越地物类型 */
    @Excel(name = "跨越地物类型")
    private String routeCrossFigure;

    /** 跨越地物名称 */
    @Excel(name = "跨越地物名称")
    private String routeCrossName;

    /** 主要病害位置 */
    @Excel(name = "主要病害位置")
    private String routeDiseasePlace;

    /** 主要病害描述 */
    @Excel(name = "主要病害描述")
    private String routeDiseaseDescribe;

    /** 墩台防撞设施类型 */
    @Excel(name = "墩台防撞设施类型")
    private String routeAnticollisionType;

    /** 抗震等级 */
    @Excel(name = "抗震等级")
    private String routeAntiseismic;

    /** 通航等级 */
    @Excel(name = "通航等级")
    private String routeFlight;

    /** 改建年度 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "改建年度", width = 30, dateFormat = "yyyy-MM-dd")
    private Date routeRebuild;

    /** 改造完工日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "改造完工日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date routeRebuildEnd;

    /** 改造部位 */
    @Excel(name = "改造部位")
    private String routeRebuildPlace;

    /** 最近改造工程性质 */
    @Excel(name = "最近改造工程性质")
    private String routeRebuildNature;

    /** 是否部补助项目 */
    @Excel(name = "是否部补助项目")
    private String routeSubsidy;

    /** 已采取交通管制措施 */
    @Excel(name = "已采取交通管制措施")
    private String routeMethod;

    /** 桥梁所在位置 */
    @Excel(name = "桥梁所在位置")
    private Long routePosition;

    /** 是否宽路窄桥 */
    @Excel(name = "是否宽路窄桥")
    private String routeWroadNbridge;

    /** 是否在长大桥梁目录 */
    @Excel(name = "是否在长大桥梁目录")
    private String routeLongBridges;

    /** 下部结构形式 */
    @Excel(name = "下部结构形式")
    private String routeBottomShape;

    /** 导入评定结果 */
    @Excel(name = "导入评定结果")
    private String routeResult;

    /** 变更原因 */
    @Excel(name = "变更原因")
    private String routeReason;

    /** 支座类型 */
    @Excel(name = "支座类型")
    private String routeSupport;

    /** 通车日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "通车日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date routeTrafficDate;

    /** 改造施工单位 */
    @Excel(name = "改造施工单位")
    private String routeRenovationUnit;

    /** 桥台类型 */
    @Excel(name = "桥台类型")
    private String routeAbutmentType;

    /** 设计单位名称 */
    @Excel(name = "设计单位名称")
    private String routeDesigner;

    /** 施工单位名称 */
    @Excel(name = "施工单位名称")
    private String routeConstructionUnit;

    /** 监理单位名称 */
    @Excel(name = "监理单位名称")
    private String routeInspectionUnit;

    /** 建设单位名称 */
    @Excel(name = "建设单位名称")
    private String routeBuildUnit;

    /** 监管单位名称 */
    @Excel(name = "监管单位名称")
    private String routeSupervise;

    /** 管养单位性质 */
    @Excel(name = "管养单位性质")
    private String routeCuring;

    /** 管养单位代码 */
    @Excel(name = "管养单位代码")
    private String routeCuringCode;

    /** 管养单位名称 */
    @Excel(name = "管养单位名称")
    private String routeCuringName;

    /** 填报单位 */
    @Excel(name = "填报单位")
    private String fillingUnit;

    /** 填报单位代码 */
    @Excel(name = "填报单位代码")
    private String fillingUnitCode;

    /** 填报单位负责人 */
    @Excel(name = "填报单位负责人")
    private String fillingUnitLeader;

    /** 填表人 */
    @Excel(name = "填表人")
    private String preparer;

    /** 填表人电话 */
    @Excel(name = "填表人电话")
    private String preparerTel;

    /** 审核人 */
    @Excel(name = "审核人")
    private String process;

    /** 审核人电话 */
    @Excel(name = "审核人电话")
    private String processTel;

    /** 采集时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "采集时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date acquisitionTime;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 行政区划 */
    @Excel(name = "行政区划")
    private String routeAdministrativeArea;

    /** 选择路线 */
    @Excel(name = "选择路线")
    private String selectRoute;

    /** 中心桩号 */
    @Excel(name = "中心桩号")
    private String centerStake;

    /** 路线编码 */
    @Excel(name = "路线编码")
    private String luCode;


    /** 评定单位 */
    @Excel(name = "评定单位")
    private String evaluationUnit;

    public void setRouteId(Long routeId)
    {
        this.routeId = routeId;
    }

    public Integer getLuId() {
        return luId;
    }

    public void setLuId(Integer luId) {
        this.luId = luId;
    }

    public Long getRouteId()
    {
        return routeId;
    }
    public void setRouteCode(String routeCode)
    {
        this.routeCode = routeCode;
    }

    public String getRouteCode()
    {
        return routeCode;
    }
    public void setRouteName(String routeName)
    {
        this.routeName = routeName;
    }

    public String getRouteName()
    {
        return routeName;
    }
    public void setRouteLongitude(String routeLongitude)
    {
        this.routeLongitude = routeLongitude;
    }

    public String getRouteLongitude()
    {
        return routeLongitude;
    }
    public void setRouteLatitude(String routeLatitude)
    {
        this.routeLatitude = routeLatitude;
    }

    public String getRouteLatitude()
    {
        return routeLatitude;
    }
    public void setRouteHigh(String routeHigh)
    {
        this.routeHigh = routeHigh;
    }

    public String getRouteHigh()
    {
        return routeHigh;
    }
    public void setRouteLong(String routeLong)
    {
        this.routeLong = routeLong;
    }

    public String getRouteLong()
    {
        return routeLong;
    }
    public void setRouteCrossLong(String routeCrossLong)
    {
        this.routeCrossLong = routeCrossLong;
    }

    public String getRouteCrossLong()
    {
        return routeCrossLong;
    }
    public void setRouteSingleLong(String routeSingleLong)
    {
        this.routeSingleLong = routeSingleLong;
    }

    public String getRouteSingleLong()
    {
        return routeSingleLong;
    }
    public void setRouteSpanCombination(String routeSpanCombination)
    {
        this.routeSpanCombination = routeSpanCombination;
    }

    public String getRouteSpanCombination()
    {
        return routeSpanCombination;
    }
    public void setRouteWide(String routeWide)
    {
        this.routeWide = routeWide;
    }

    public String getRouteWide()
    {
        return routeWide;
    }
    public void setRouteOverallLength(String routeOverallLength)
    {
        this.routeOverallLength = routeOverallLength;
    }

    public String getRouteOverallLength()
    {
        return routeOverallLength;
    }
    public void setRouteClearWidth(String routeClearWidth)
    {
        this.routeClearWidth = routeClearWidth;
    }

    public String getRouteClearWidth()
    {
        return routeClearWidth;
    }
    public void setRouteLoad(String routeLoad)
    {
        this.routeLoad = routeLoad;
    }

    public String getRouteLoad()
    {
        return routeLoad;
    }
    public void setRouteCross(String routeCross)
    {
        this.routeCross = routeCross;
    }

    public String getRouteCross()
    {
        return routeCross;
    }
    public void setRouteYear(String routeYear)
    {
        this.routeYear = routeYear;
    }

    public String getRouteYear()
    {
        return routeYear;
    }
    public void setRouteDanger(String routeDanger)
    {
        this.routeDanger = routeDanger;
    }

    public String getRouteDanger()
    {
        return routeDanger;
    }
    public void setRouteGrade(String routeGrade)
    {
        this.routeGrade = routeGrade;
    }

    public String getRouteGrade()
    {
        return routeGrade;
    }
    public void setRouteEvaluationDate(Date routeEvaluationDate)
    {
        this.routeEvaluationDate = routeEvaluationDate;
    }

    public Date getRouteEvaluationDate()
    {
        return routeEvaluationDate;
    }
    public void setRouteTopShape(String routeTopShape)
    {
        this.routeTopShape = routeTopShape;
    }

    public String getRouteTopShape()
    {
        return routeTopShape;
    }
    public void setRouteTopMaterial(String routeTopMaterial)
    {
        this.routeTopMaterial = routeTopMaterial;
    }

    public String getRouteTopMaterial()
    {
        return routeTopMaterial;
    }
    public void setRouteType(String routeType)
    {
        this.routeType = routeType;
    }

    public String getRouteType()
    {
        return routeType;
    }
    public void setRoutePierType(String routePierType)
    {
        this.routePierType = routePierType;
    }

    public String getRoutePierType()
    {
        return routePierType;
    }
    public void setRouteCharge(String routeCharge)
    {
        this.routeCharge = routeCharge;
    }

    public String getRouteCharge()
    {
        return routeCharge;
    }
    public void setRouteBeforeCode(String routeBeforeCode)
    {
        this.routeBeforeCode = routeBeforeCode;
    }

    public String getRouteBeforeCode()
    {
        return routeBeforeCode;
    }
    public void setRouteCompletionTime(Date routeCompletionTime)
    {
        this.routeCompletionTime = routeCompletionTime;
    }

    public Date getRouteCompletionTime()
    {
        return routeCompletionTime;
    }
    public void setRouteCrossFigure(String routeCrossFigure)
    {
        this.routeCrossFigure = routeCrossFigure;
    }

    public String getRouteCrossFigure()
    {
        return routeCrossFigure;
    }
    public void setRouteCrossName(String routeCrossName)
    {
        this.routeCrossName = routeCrossName;
    }

    public String getRouteCrossName()
    {
        return routeCrossName;
    }
    public void setRouteDiseasePlace(String routeDiseasePlace)
    {
        this.routeDiseasePlace = routeDiseasePlace;
    }

    public String getRouteDiseasePlace()
    {
        return routeDiseasePlace;
    }
    public void setRouteDiseaseDescribe(String routeDiseaseDescribe)
    {
        this.routeDiseaseDescribe = routeDiseaseDescribe;
    }

    public String getRouteDiseaseDescribe()
    {
        return routeDiseaseDescribe;
    }
    public void setRouteAnticollisionType(String routeAnticollisionType)
    {
        this.routeAnticollisionType = routeAnticollisionType;
    }

    public String getRouteAnticollisionType()
    {
        return routeAnticollisionType;
    }
    public void setRouteAntiseismic(String routeAntiseismic)
    {
        this.routeAntiseismic = routeAntiseismic;
    }

    public String getRouteAntiseismic()
    {
        return routeAntiseismic;
    }
    public void setRouteFlight(String routeFlight)
    {
        this.routeFlight = routeFlight;
    }

    public String getRouteFlight()
    {
        return routeFlight;
    }
    public void setRouteRebuild(Date routeRebuild)
    {
        this.routeRebuild = routeRebuild;
    }

    public Date getRouteRebuild()
    {
        return routeRebuild;
    }
    public void setRouteRebuildEnd(Date routeRebuildEnd)
    {
        this.routeRebuildEnd = routeRebuildEnd;
    }

    public Date getRouteRebuildEnd()
    {
        return routeRebuildEnd;
    }
    public void setRouteRebuildPlace(String routeRebuildPlace)
    {
        this.routeRebuildPlace = routeRebuildPlace;
    }

    public String getRouteRebuildPlace()
    {
        return routeRebuildPlace;
    }
    public void setRouteRebuildNature(String routeRebuildNature)
    {
        this.routeRebuildNature = routeRebuildNature;
    }

    public String getRouteRebuildNature()
    {
        return routeRebuildNature;
    }
    public void setRouteSubsidy(String routeSubsidy)
    {
        this.routeSubsidy = routeSubsidy;
    }

    public String getRouteSubsidy()
    {
        return routeSubsidy;
    }
    public void setRouteMethod(String routeMethod)
    {
        this.routeMethod = routeMethod;
    }

    public String getRouteMethod()
    {
        return routeMethod;
    }
    public void setRoutePosition(Long routePosition)
    {
        this.routePosition = routePosition;
    }

    public Long getRoutePosition()
    {
        return routePosition;
    }
    public void setRouteWroadNbridge(String routeWroadNbridge)
    {
        this.routeWroadNbridge = routeWroadNbridge;
    }

    public String getRouteWroadNbridge()
    {
        return routeWroadNbridge;
    }
    public void setRouteLongBridges(String routeLongBridges)
    {
        this.routeLongBridges = routeLongBridges;
    }

    public String getRouteLongBridges()
    {
        return routeLongBridges;
    }
    public void setRouteBottomShape(String routeBottomShape)
    {
        this.routeBottomShape = routeBottomShape;
    }

    public String getRouteBottomShape()
    {
        return routeBottomShape;
    }
    public void setRouteResult(String routeResult)
    {
        this.routeResult = routeResult;
    }

    public String getRouteResult()
    {
        return routeResult;
    }
    public void setRouteReason(String routeReason)
    {
        this.routeReason = routeReason;
    }

    public String getRouteReason()
    {
        return routeReason;
    }
    public void setRouteSupport(String routeSupport)
    {
        this.routeSupport = routeSupport;
    }

    public String getRouteSupport()
    {
        return routeSupport;
    }
    public void setRouteTrafficDate(Date routeTrafficDate)
    {
        this.routeTrafficDate = routeTrafficDate;
    }

    public Date getRouteTrafficDate()
    {
        return routeTrafficDate;
    }
    public void setRouteRenovationUnit(String routeRenovationUnit)
    {
        this.routeRenovationUnit = routeRenovationUnit;
    }

    public String getRouteRenovationUnit()
    {
        return routeRenovationUnit;
    }
    public void setRouteAbutmentType(String routeAbutmentType)
    {
        this.routeAbutmentType = routeAbutmentType;
    }

    public String getRouteAbutmentType()
    {
        return routeAbutmentType;
    }
    public void setRouteDesigner(String routeDesigner)
    {
        this.routeDesigner = routeDesigner;
    }

    public String getRouteDesigner()
    {
        return routeDesigner;
    }
    public void setRouteConstructionUnit(String routeConstructionUnit)
    {
        this.routeConstructionUnit = routeConstructionUnit;
    }

    public String getRouteConstructionUnit()
    {
        return routeConstructionUnit;
    }
    public void setRouteInspectionUnit(String routeInspectionUnit)
    {
        this.routeInspectionUnit = routeInspectionUnit;
    }

    public String getRouteInspectionUnit()
    {
        return routeInspectionUnit;
    }
    public void setRouteBuildUnit(String routeBuildUnit)
    {
        this.routeBuildUnit = routeBuildUnit;
    }

    public String getRouteBuildUnit()
    {
        return routeBuildUnit;
    }
    public void setRouteSupervise(String routeSupervise)
    {
        this.routeSupervise = routeSupervise;
    }

    public String getRouteSupervise()
    {
        return routeSupervise;
    }
    public void setRouteCuring(String routeCuring)
    {
        this.routeCuring = routeCuring;
    }

    public String getRouteCuring()
    {
        return routeCuring;
    }
    public void setRouteCuringCode(String routeCuringCode)
    {
        this.routeCuringCode = routeCuringCode;
    }

    public String getRouteCuringCode()
    {
        return routeCuringCode;
    }
    public void setRouteCuringName(String routeCuringName)
    {
        this.routeCuringName = routeCuringName;
    }

    public String getRouteCuringName()
    {
        return routeCuringName;
    }
    public void setFillingUnit(String fillingUnit)
    {
        this.fillingUnit = fillingUnit;
    }

    public String getFillingUnit()
    {
        return fillingUnit;
    }
    public void setFillingUnitCode(String fillingUnitCode)
    {
        this.fillingUnitCode = fillingUnitCode;
    }

    public String getFillingUnitCode()
    {
        return fillingUnitCode;
    }
    public void setFillingUnitLeader(String fillingUnitLeader)
    {
        this.fillingUnitLeader = fillingUnitLeader;
    }

    public String getFillingUnitLeader()
    {
        return fillingUnitLeader;
    }
    public void setPreparer(String preparer)
    {
        this.preparer = preparer;
    }

    public String getPreparer()
    {
        return preparer;
    }
    public void setPreparerTel(String preparerTel)
    {
        this.preparerTel = preparerTel;
    }

    public String getPreparerTel()
    {
        return preparerTel;
    }
    public void setProcess(String process)
    {
        this.process = process;
    }

    public String getProcess()
    {
        return process;
    }
    public void setProcessTel(String processTel)
    {
        this.processTel = processTel;
    }

    public String getProcessTel()
    {
        return processTel;
    }
    public void setAcquisitionTime(Date acquisitionTime)
    {
        this.acquisitionTime = acquisitionTime;
    }

    public Date getAcquisitionTime()
    {
        return acquisitionTime;
    }
    public void setRemarks(String remarks)
    {
        this.remarks = remarks;
    }

    public String getRemarks()
    {
        return remarks;
    }
    public void setRouteAdministrativeArea(String routeAdministrativeArea)
    {
        this.routeAdministrativeArea = routeAdministrativeArea;
    }

    public String getRouteAdministrativeArea()
    {
        return routeAdministrativeArea;
    }
    public void setSelectRoute(String selectRoute)
    {
        this.selectRoute = selectRoute;
    }

    public String getSelectRoute()
    {
        return selectRoute;
    }
    public void setCenterStake(String centerStake)
    {
        this.centerStake = centerStake;
    }

    public String getCenterStake()
    {
        return centerStake;
    }
    public void setLuCode(String luCode)
    {
        this.luCode = luCode;
    }

    public String getLuCode()
    {
        return luCode;
    }
    public void setEvaluationUnit(String evaluationUnit)
    {
        this.evaluationUnit = evaluationUnit;
    }

    public String getEvaluationUnit()
    {
        return evaluationUnit;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("routeId", getRouteId())
                .append("routeCode", getRouteCode())
                .append("routeName", getRouteName())
                .append("routeLongitude", getRouteLongitude())
                .append("routeLatitude", getRouteLatitude())
                .append("routeHigh", getRouteHigh())
                .append("routeLong", getRouteLong())
                .append("routeCrossLong", getRouteCrossLong())
                .append("routeSingleLong", getRouteSingleLong())
                .append("routeSpanCombination", getRouteSpanCombination())
                .append("routeWide", getRouteWide())
                .append("routeOverallLength", getRouteOverallLength())
                .append("routeClearWidth", getRouteClearWidth())
                .append("routeLoad", getRouteLoad())
                .append("routeCross", getRouteCross())
                .append("routeYear", getRouteYear())
                .append("routeDanger", getRouteDanger())
                .append("routeGrade", getRouteGrade())
                .append("routeEvaluationDate", getRouteEvaluationDate())
                .append("routeTopShape", getRouteTopShape())
                .append("routeTopMaterial", getRouteTopMaterial())
                .append("routeType", getRouteType())
                .append("routePierType", getRoutePierType())
                .append("routeCharge", getRouteCharge())
                .append("routeBeforeCode", getRouteBeforeCode())
                .append("routeCompletionTime", getRouteCompletionTime())
                .append("routeCrossFigure", getRouteCrossFigure())
                .append("routeCrossName", getRouteCrossName())
                .append("routeDiseasePlace", getRouteDiseasePlace())
                .append("routeDiseaseDescribe", getRouteDiseaseDescribe())
                .append("routeAnticollisionType", getRouteAnticollisionType())
                .append("routeAntiseismic", getRouteAntiseismic())
                .append("routeFlight", getRouteFlight())
                .append("routeRebuild", getRouteRebuild())
                .append("routeRebuildEnd", getRouteRebuildEnd())
                .append("routeRebuildPlace", getRouteRebuildPlace())
                .append("routeRebuildNature", getRouteRebuildNature())
                .append("routeSubsidy", getRouteSubsidy())
                .append("routeMethod", getRouteMethod())
                .append("routePosition", getRoutePosition())
                .append("routeWroadNbridge", getRouteWroadNbridge())
                .append("routeLongBridges", getRouteLongBridges())
                .append("routeBottomShape", getRouteBottomShape())
                .append("routeResult", getRouteResult())
                .append("routeReason", getRouteReason())
                .append("routeSupport", getRouteSupport())
                .append("routeTrafficDate", getRouteTrafficDate())
                .append("routeRenovationUnit", getRouteRenovationUnit())
                .append("routeAbutmentType", getRouteAbutmentType())
                .append("routeDesigner", getRouteDesigner())
                .append("routeConstructionUnit", getRouteConstructionUnit())
                .append("routeInspectionUnit", getRouteInspectionUnit())
                .append("routeBuildUnit", getRouteBuildUnit())
                .append("routeSupervise", getRouteSupervise())
                .append("routeCuring", getRouteCuring())
                .append("routeCuringCode", getRouteCuringCode())
                .append("routeCuringName", getRouteCuringName())
                .append("fillingUnit", getFillingUnit())
                .append("fillingUnitCode", getFillingUnitCode())
                .append("fillingUnitLeader", getFillingUnitLeader())
                .append("preparer", getPreparer())
                .append("preparerTel", getPreparerTel())
                .append("process", getProcess())
                .append("processTel", getProcessTel())
                .append("acquisitionTime", getAcquisitionTime())
                .append("updateTime", getUpdateTime())
                .append("remarks", getRemarks())
                .append("routeAdministrativeArea", getRouteAdministrativeArea())
                .append("selectRoute", getSelectRoute())
                .append("centerStake", getCenterStake())
                .append("luCode", getLuCode())
                .append("evaluationUnit", getEvaluationUnit())
                .append("luId", getLuId())
                .toString();
    }
}
