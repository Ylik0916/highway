package com.hg.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hg.common.annotation.Excel;
import com.hg.common.core.domain.BaseEntity;

/**
 * 隧道信息对象 hw_tunnel
 * 
 * @author ruoyi
 * @date 2023-03-14
 */
public class HwTunnel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 隧道ID */
    private Long tunnelId;

    /** 路线编码 */
    @Excel(name = "路线编码")
    private String routeCoding;

    /** 路线名称 */
    @Excel(name = "路线名称")
    private String routeName;

    /** 隧道名称 */
    @Excel(name = "隧道名称")
    private String tunnelName;

    /** 隧道编号 */
    @Excel(name = "隧道编号")
    private String tunnelNumber;

    /** 隧道中心桩号 */
    @Excel(name = "隧道中心桩号")
    private String tunnelCenterPileNumber;

    /** 入口桩号/起点桩号 */
    private String startingPileNumber;

    /** 隧道长度（米） */
    @Excel(name = "隧道长度", readConverterExp = "米=")
    private Long tunnelLength;

    /** 隧道全宽（米） */
    @Excel(name = "隧道全宽", readConverterExp = "米=")
    private Long tunnelOverallWidth;

    /** 隧道净宽（米） */
    private Long tunnelClearWidth;

    /** 隧道净高（米） */
    private Long tunnelClearHeight;

    /** 人行道宽（米） */
    private Long sidewalkWidth;

    /** 按隧道长度分类 */
    private String tunnelLengthType;

    /** 断面形式 */
    private String sectionForm;

    /** 洞口形式 */
    private String openingForm;

    /** 衬砌材料 */
    private String liningMaterial;

    /** 消防设施 */
    private String fireEquipments;

    /** 安全通道数量 */
    private String numberOfSafeChannels;

    /** 是否水下隧道 */
    private String underwaterTunnelOrNot;

    /** 管养单位性质 */
    private String natureOfCustodialUnit;

    /** 隧道评定等级 */
    private String tunnelRating;

    /** 评定日期 */
    private Date dateOfAssessment;

    /** 评定单位 */
    private String ratingUnit;

    /** 隧道评定等级_土建 */
    private String tunnelRatingCivilConstruction;

    /** 评定日期土建 */
    private Date ratingDateCivilConstruction;

    /** 评定单位土建 */
    private String evaluateTheCivilConstruction;

    /** 隧道评定等级_机电 */
    private String tunnelRatingGrade;

    /** 评定单位_机电 */
    private String ratingUnitMechanical;

    /** 评定日期_机电 */
    private Date dateOfEvaluation;

    /** 隧道评定等级_其他 */
    private String tunnelRatingOther;

    /** 评定单位_其他 */
    private String ratingUnitOther;

    /** 原隧道编码 */
    private String originalTunnelCoding;

    /** 路面面层类型 */
    private String typeOfPavementLayer;

    /** 隧道照明 */
    private String tunnelLighting;

    /** 隧道通风 */
    private String tunnelVentilation;

    /** 隧道高程（米） */
    private String tunnelElevation;

    /** 隧道养护等级 */
    private String tunnelMaintenanceGrade;

    /** 隧道排水类型 */
    private String tunnelDrainageType;

    /** 隧道所在地点 */
    private String tunnelLocation;

    /** 建成时间(年份) */
    private Date completionTime;

    /** 通车日期 */
    private Date openingDate;

    /** 变更原因 */
    private String reasonForChange;

    /** 隧道电子设备 */
    private String tunnelElectronics;

    /** 设计单位名称 */
    private String nameOfDesignCompany;

    /** 监管单位名称 */
    private String nameOfSupervisoryUnit;

    /** 施工单位名称 */
    private String nameOfConstructionUnit;

    /** 监理单位名称 */
    private String nameOfSupervisionUnit;

    /** 建设单位名称 */
    private String nameOfConstruction;

    /** 管养单位代码 */
    private String custodialUnitCode;

    /** 管养单位名称 */
    private String nameOfCustodialUnit;

    /** 改建年度 */
    private Date conversionYear;

    /** 隧道改造部位 */
    private String tunnelReconstructionSite;

    /** 最近改造工程性质 */
    private String recentRenovationProject;

    /** 改造完工日期 */
    private Date dateOfCompletion;

    /** 隧道病害位置 */
    private String tunnelTroubleLocation;

    /** 隧道病害描述 */
    private String tunnelDamageDescription;

    /** 入口位置经度/隧道经度 */
    private String tunnelLongitude;

    /** 入口位置纬度/隧道纬度 */
    private String tunnelLatitude;

    /** 填报单位 */
    private String reportingUnit;

    /** 填报单位代码 */
    private String reportingUnitCode;

    /** 填报单位负责人 */
    private String reportingUnitPrincipal;

    /** 填表人 */
    private String reportingPerson;

    /** 填表人电话 */
    private String reportingPersonTelephone;

    /** 审核人 */
    private String auditor;

    /** 审核人电话 */
    private String auditorTelephone;

    /** 采集时间 */
    private Date acquisitionTime;

    /** 修改时间 */
    private Date modificationTime;

    /** 备注 */
    private String remarks;

    /** 路线ID */
    private Long routeId;

    /** 是否在长大隧道目录 */
    private String longTunnelDirectory;

    public void setTunnelId(Long tunnelId) 
    {
        this.tunnelId = tunnelId;
    }

    public Long getTunnelId() 
    {
        return tunnelId;
    }
    public void setRouteCoding(String routeCoding) 
    {
        this.routeCoding = routeCoding;
    }

    public String getRouteCoding() 
    {
        return routeCoding;
    }
    public void setRouteName(String routeName) 
    {
        this.routeName = routeName;
    }

    public String getRouteName() 
    {
        return routeName;
    }
    public void setTunnelName(String tunnelName) 
    {
        this.tunnelName = tunnelName;
    }

    public String getTunnelName() 
    {
        return tunnelName;
    }
    public void setTunnelNumber(String tunnelNumber) 
    {
        this.tunnelNumber = tunnelNumber;
    }

    public String getTunnelNumber() 
    {
        return tunnelNumber;
    }
    public void setTunnelCenterPileNumber(String tunnelCenterPileNumber) 
    {
        this.tunnelCenterPileNumber = tunnelCenterPileNumber;
    }

    public String getTunnelCenterPileNumber() 
    {
        return tunnelCenterPileNumber;
    }
    public void setStartingPileNumber(String startingPileNumber) 
    {
        this.startingPileNumber = startingPileNumber;
    }

    public String getStartingPileNumber() 
    {
        return startingPileNumber;
    }
    public void setTunnelLength(Long tunnelLength) 
    {
        this.tunnelLength = tunnelLength;
    }

    public Long getTunnelLength() 
    {
        return tunnelLength;
    }
    public void setTunnelOverallWidth(Long tunnelOverallWidth) 
    {
        this.tunnelOverallWidth = tunnelOverallWidth;
    }

    public Long getTunnelOverallWidth() 
    {
        return tunnelOverallWidth;
    }
    public void setTunnelClearWidth(Long tunnelClearWidth) 
    {
        this.tunnelClearWidth = tunnelClearWidth;
    }

    public Long getTunnelClearWidth() 
    {
        return tunnelClearWidth;
    }
    public void setTunnelClearHeight(Long tunnelClearHeight) 
    {
        this.tunnelClearHeight = tunnelClearHeight;
    }

    public Long getTunnelClearHeight() 
    {
        return tunnelClearHeight;
    }
    public void setSidewalkWidth(Long sidewalkWidth) 
    {
        this.sidewalkWidth = sidewalkWidth;
    }

    public Long getSidewalkWidth() 
    {
        return sidewalkWidth;
    }
    public void setTunnelLengthType(String tunnelLengthType) 
    {
        this.tunnelLengthType = tunnelLengthType;
    }

    public String getTunnelLengthType() 
    {
        return tunnelLengthType;
    }
    public void setSectionForm(String sectionForm) 
    {
        this.sectionForm = sectionForm;
    }

    public String getSectionForm() 
    {
        return sectionForm;
    }
    public void setOpeningForm(String openingForm) 
    {
        this.openingForm = openingForm;
    }

    public String getOpeningForm() 
    {
        return openingForm;
    }
    public void setLiningMaterial(String liningMaterial) 
    {
        this.liningMaterial = liningMaterial;
    }

    public String getLiningMaterial() 
    {
        return liningMaterial;
    }
    public void setFireEquipments(String fireEquipments) 
    {
        this.fireEquipments = fireEquipments;
    }

    public String getFireEquipments() 
    {
        return fireEquipments;
    }
    public void setNumberOfSafeChannels(String numberOfSafeChannels) 
    {
        this.numberOfSafeChannels = numberOfSafeChannels;
    }

    public String getNumberOfSafeChannels() 
    {
        return numberOfSafeChannels;
    }
    public void setUnderwaterTunnelOrNot(String underwaterTunnelOrNot) 
    {
        this.underwaterTunnelOrNot = underwaterTunnelOrNot;
    }

    public String getUnderwaterTunnelOrNot() 
    {
        return underwaterTunnelOrNot;
    }
    public void setNatureOfCustodialUnit(String natureOfCustodialUnit) 
    {
        this.natureOfCustodialUnit = natureOfCustodialUnit;
    }

    public String getNatureOfCustodialUnit() 
    {
        return natureOfCustodialUnit;
    }
    public void setTunnelRating(String tunnelRating) 
    {
        this.tunnelRating = tunnelRating;
    }

    public String getTunnelRating() 
    {
        return tunnelRating;
    }
    public void setDateOfAssessment(Date dateOfAssessment) 
    {
        this.dateOfAssessment = dateOfAssessment;
    }

    public Date getDateOfAssessment() 
    {
        return dateOfAssessment;
    }
    public void setRatingUnit(String ratingUnit) 
    {
        this.ratingUnit = ratingUnit;
    }

    public String getRatingUnit() 
    {
        return ratingUnit;
    }
    public void setTunnelRatingCivilConstruction(String tunnelRatingCivilConstruction) 
    {
        this.tunnelRatingCivilConstruction = tunnelRatingCivilConstruction;
    }

    public String getTunnelRatingCivilConstruction() 
    {
        return tunnelRatingCivilConstruction;
    }
    public void setRatingDateCivilConstruction(Date ratingDateCivilConstruction) 
    {
        this.ratingDateCivilConstruction = ratingDateCivilConstruction;
    }

    public Date getRatingDateCivilConstruction() 
    {
        return ratingDateCivilConstruction;
    }
    public void setEvaluateTheCivilConstruction(String evaluateTheCivilConstruction) 
    {
        this.evaluateTheCivilConstruction = evaluateTheCivilConstruction;
    }

    public String getEvaluateTheCivilConstruction() 
    {
        return evaluateTheCivilConstruction;
    }
    public void setTunnelRatingGrade(String tunnelRatingGrade) 
    {
        this.tunnelRatingGrade = tunnelRatingGrade;
    }

    public String getTunnelRatingGrade() 
    {
        return tunnelRatingGrade;
    }
    public void setRatingUnitMechanical(String ratingUnitMechanical) 
    {
        this.ratingUnitMechanical = ratingUnitMechanical;
    }

    public String getRatingUnitMechanical() 
    {
        return ratingUnitMechanical;
    }
    public void setDateOfEvaluation(Date dateOfEvaluation) 
    {
        this.dateOfEvaluation = dateOfEvaluation;
    }

    public Date getDateOfEvaluation() 
    {
        return dateOfEvaluation;
    }
    public void setTunnelRatingOther(String tunnelRatingOther) 
    {
        this.tunnelRatingOther = tunnelRatingOther;
    }

    public String getTunnelRatingOther() 
    {
        return tunnelRatingOther;
    }
    public void setRatingUnitOther(String ratingUnitOther) 
    {
        this.ratingUnitOther = ratingUnitOther;
    }

    public String getRatingUnitOther() 
    {
        return ratingUnitOther;
    }
    public void setOriginalTunnelCoding(String originalTunnelCoding) 
    {
        this.originalTunnelCoding = originalTunnelCoding;
    }

    public String getOriginalTunnelCoding() 
    {
        return originalTunnelCoding;
    }
    public void setTypeOfPavementLayer(String typeOfPavementLayer) 
    {
        this.typeOfPavementLayer = typeOfPavementLayer;
    }

    public String getTypeOfPavementLayer() 
    {
        return typeOfPavementLayer;
    }
    public void setTunnelLighting(String tunnelLighting) 
    {
        this.tunnelLighting = tunnelLighting;
    }

    public String getTunnelLighting() 
    {
        return tunnelLighting;
    }
    public void setTunnelVentilation(String tunnelVentilation) 
    {
        this.tunnelVentilation = tunnelVentilation;
    }

    public String getTunnelVentilation() 
    {
        return tunnelVentilation;
    }
    public void setTunnelElevation(String tunnelElevation) 
    {
        this.tunnelElevation = tunnelElevation;
    }

    public String getTunnelElevation() 
    {
        return tunnelElevation;
    }
    public void setTunnelMaintenanceGrade(String tunnelMaintenanceGrade) 
    {
        this.tunnelMaintenanceGrade = tunnelMaintenanceGrade;
    }

    public String getTunnelMaintenanceGrade() 
    {
        return tunnelMaintenanceGrade;
    }
    public void setTunnelDrainageType(String tunnelDrainageType) 
    {
        this.tunnelDrainageType = tunnelDrainageType;
    }

    public String getTunnelDrainageType() 
    {
        return tunnelDrainageType;
    }
    public void setTunnelLocation(String tunnelLocation) 
    {
        this.tunnelLocation = tunnelLocation;
    }

    public String getTunnelLocation() 
    {
        return tunnelLocation;
    }
    public void setCompletionTime(Date completionTime) 
    {
        this.completionTime = completionTime;
    }

    public Date getCompletionTime() 
    {
        return completionTime;
    }
    public void setOpeningDate(Date openingDate) 
    {
        this.openingDate = openingDate;
    }

    public Date getOpeningDate() 
    {
        return openingDate;
    }
    public void setReasonForChange(String reasonForChange) 
    {
        this.reasonForChange = reasonForChange;
    }

    public String getReasonForChange() 
    {
        return reasonForChange;
    }
    public void setTunnelElectronics(String tunnelElectronics) 
    {
        this.tunnelElectronics = tunnelElectronics;
    }

    public String getTunnelElectronics() 
    {
        return tunnelElectronics;
    }
    public void setNameOfDesignCompany(String nameOfDesignCompany) 
    {
        this.nameOfDesignCompany = nameOfDesignCompany;
    }

    public String getNameOfDesignCompany() 
    {
        return nameOfDesignCompany;
    }
    public void setNameOfSupervisoryUnit(String nameOfSupervisoryUnit) 
    {
        this.nameOfSupervisoryUnit = nameOfSupervisoryUnit;
    }

    public String getNameOfSupervisoryUnit() 
    {
        return nameOfSupervisoryUnit;
    }
    public void setNameOfConstructionUnit(String nameOfConstructionUnit) 
    {
        this.nameOfConstructionUnit = nameOfConstructionUnit;
    }

    public String getNameOfConstructionUnit() 
    {
        return nameOfConstructionUnit;
    }
    public void setNameOfSupervisionUnit(String nameOfSupervisionUnit) 
    {
        this.nameOfSupervisionUnit = nameOfSupervisionUnit;
    }

    public String getNameOfSupervisionUnit() 
    {
        return nameOfSupervisionUnit;
    }
    public void setNameOfConstruction(String nameOfConstruction) 
    {
        this.nameOfConstruction = nameOfConstruction;
    }

    public String getNameOfConstruction() 
    {
        return nameOfConstruction;
    }
    public void setCustodialUnitCode(String custodialUnitCode) 
    {
        this.custodialUnitCode = custodialUnitCode;
    }

    public String getCustodialUnitCode() 
    {
        return custodialUnitCode;
    }
    public void setNameOfCustodialUnit(String nameOfCustodialUnit) 
    {
        this.nameOfCustodialUnit = nameOfCustodialUnit;
    }

    public String getNameOfCustodialUnit() 
    {
        return nameOfCustodialUnit;
    }
    public void setConversionYear(Date conversionYear) 
    {
        this.conversionYear = conversionYear;
    }

    public Date getConversionYear() 
    {
        return conversionYear;
    }
    public void setTunnelReconstructionSite(String tunnelReconstructionSite) 
    {
        this.tunnelReconstructionSite = tunnelReconstructionSite;
    }

    public String getTunnelReconstructionSite() 
    {
        return tunnelReconstructionSite;
    }
    public void setRecentRenovationProject(String recentRenovationProject) 
    {
        this.recentRenovationProject = recentRenovationProject;
    }

    public String getRecentRenovationProject() 
    {
        return recentRenovationProject;
    }
    public void setDateOfCompletion(Date dateOfCompletion) 
    {
        this.dateOfCompletion = dateOfCompletion;
    }

    public Date getDateOfCompletion() 
    {
        return dateOfCompletion;
    }
    public void setTunnelTroubleLocation(String tunnelTroubleLocation) 
    {
        this.tunnelTroubleLocation = tunnelTroubleLocation;
    }

    public String getTunnelTroubleLocation() 
    {
        return tunnelTroubleLocation;
    }
    public void setTunnelDamageDescription(String tunnelDamageDescription) 
    {
        this.tunnelDamageDescription = tunnelDamageDescription;
    }

    public String getTunnelDamageDescription() 
    {
        return tunnelDamageDescription;
    }
    public void setTunnelLongitude(String tunnelLongitude) 
    {
        this.tunnelLongitude = tunnelLongitude;
    }

    public String getTunnelLongitude() 
    {
        return tunnelLongitude;
    }
    public void setTunnelLatitude(String tunnelLatitude) 
    {
        this.tunnelLatitude = tunnelLatitude;
    }

    public String getTunnelLatitude() 
    {
        return tunnelLatitude;
    }
    public void setReportingUnit(String reportingUnit) 
    {
        this.reportingUnit = reportingUnit;
    }

    public String getReportingUnit() 
    {
        return reportingUnit;
    }
    public void setReportingUnitCode(String reportingUnitCode) 
    {
        this.reportingUnitCode = reportingUnitCode;
    }

    public String getReportingUnitCode() 
    {
        return reportingUnitCode;
    }
    public void setReportingUnitPrincipal(String reportingUnitPrincipal) 
    {
        this.reportingUnitPrincipal = reportingUnitPrincipal;
    }

    public String getReportingUnitPrincipal() 
    {
        return reportingUnitPrincipal;
    }
    public void setReportingPerson(String reportingPerson) 
    {
        this.reportingPerson = reportingPerson;
    }

    public String getReportingPerson() 
    {
        return reportingPerson;
    }
    public void setReportingPersonTelephone(String reportingPersonTelephone) 
    {
        this.reportingPersonTelephone = reportingPersonTelephone;
    }

    public String getReportingPersonTelephone() 
    {
        return reportingPersonTelephone;
    }
    public void setAuditor(String auditor) 
    {
        this.auditor = auditor;
    }

    public String getAuditor() 
    {
        return auditor;
    }
    public void setAuditorTelephone(String auditorTelephone) 
    {
        this.auditorTelephone = auditorTelephone;
    }

    public String getAuditorTelephone() 
    {
        return auditorTelephone;
    }
    public void setAcquisitionTime(Date acquisitionTime) 
    {
        this.acquisitionTime = acquisitionTime;
    }

    public Date getAcquisitionTime() 
    {
        return acquisitionTime;
    }
    public void setModificationTime(Date modificationTime) 
    {
        this.modificationTime = modificationTime;
    }

    public Date getModificationTime() 
    {
        return modificationTime;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }
    public void setRouteId(Long routeId) 
    {
        this.routeId = routeId;
    }

    public Long getRouteId() 
    {
        return routeId;
    }
    public void setLongTunnelDirectory(String longTunnelDirectory) 
    {
        this.longTunnelDirectory = longTunnelDirectory;
    }

    public String getLongTunnelDirectory() 
    {
        return longTunnelDirectory;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tunnelId", getTunnelId())
            .append("routeCoding", getRouteCoding())
            .append("routeName", getRouteName())
            .append("tunnelName", getTunnelName())
            .append("tunnelNumber", getTunnelNumber())
            .append("tunnelCenterPileNumber", getTunnelCenterPileNumber())
            .append("startingPileNumber", getStartingPileNumber())
            .append("tunnelLength", getTunnelLength())
            .append("tunnelOverallWidth", getTunnelOverallWidth())
            .append("tunnelClearWidth", getTunnelClearWidth())
            .append("tunnelClearHeight", getTunnelClearHeight())
            .append("sidewalkWidth", getSidewalkWidth())
            .append("tunnelLengthType", getTunnelLengthType())
            .append("sectionForm", getSectionForm())
            .append("openingForm", getOpeningForm())
            .append("liningMaterial", getLiningMaterial())
            .append("fireEquipments", getFireEquipments())
            .append("numberOfSafeChannels", getNumberOfSafeChannels())
            .append("underwaterTunnelOrNot", getUnderwaterTunnelOrNot())
            .append("natureOfCustodialUnit", getNatureOfCustodialUnit())
            .append("tunnelRating", getTunnelRating())
            .append("dateOfAssessment", getDateOfAssessment())
            .append("ratingUnit", getRatingUnit())
            .append("tunnelRatingCivilConstruction", getTunnelRatingCivilConstruction())
            .append("ratingDateCivilConstruction", getRatingDateCivilConstruction())
            .append("evaluateTheCivilConstruction", getEvaluateTheCivilConstruction())
            .append("tunnelRatingGrade", getTunnelRatingGrade())
            .append("ratingUnitMechanical", getRatingUnitMechanical())
            .append("dateOfEvaluation", getDateOfEvaluation())
            .append("tunnelRatingOther", getTunnelRatingOther())
            .append("ratingUnitOther", getRatingUnitOther())
            .append("originalTunnelCoding", getOriginalTunnelCoding())
            .append("typeOfPavementLayer", getTypeOfPavementLayer())
            .append("tunnelLighting", getTunnelLighting())
            .append("tunnelVentilation", getTunnelVentilation())
            .append("tunnelElevation", getTunnelElevation())
            .append("tunnelMaintenanceGrade", getTunnelMaintenanceGrade())
            .append("tunnelDrainageType", getTunnelDrainageType())
            .append("tunnelLocation", getTunnelLocation())
            .append("completionTime", getCompletionTime())
            .append("openingDate", getOpeningDate())
            .append("reasonForChange", getReasonForChange())
            .append("tunnelElectronics", getTunnelElectronics())
            .append("nameOfDesignCompany", getNameOfDesignCompany())
            .append("nameOfSupervisoryUnit", getNameOfSupervisoryUnit())
            .append("nameOfConstructionUnit", getNameOfConstructionUnit())
            .append("nameOfSupervisionUnit", getNameOfSupervisionUnit())
            .append("nameOfConstruction", getNameOfConstruction())
            .append("custodialUnitCode", getCustodialUnitCode())
            .append("nameOfCustodialUnit", getNameOfCustodialUnit())
            .append("conversionYear", getConversionYear())
            .append("tunnelReconstructionSite", getTunnelReconstructionSite())
            .append("recentRenovationProject", getRecentRenovationProject())
            .append("dateOfCompletion", getDateOfCompletion())
            .append("tunnelTroubleLocation", getTunnelTroubleLocation())
            .append("tunnelDamageDescription", getTunnelDamageDescription())
            .append("tunnelLongitude", getTunnelLongitude())
            .append("tunnelLatitude", getTunnelLatitude())
            .append("reportingUnit", getReportingUnit())
            .append("reportingUnitCode", getReportingUnitCode())
            .append("reportingUnitPrincipal", getReportingUnitPrincipal())
            .append("reportingPerson", getReportingPerson())
            .append("reportingPersonTelephone", getReportingPersonTelephone())
            .append("auditor", getAuditor())
            .append("auditorTelephone", getAuditorTelephone())
            .append("acquisitionTime", getAcquisitionTime())
            .append("modificationTime", getModificationTime())
            .append("remarks", getRemarks())
            .append("routeId", getRouteId())
            .append("longTunnelDirectory", getLongTunnelDirectory())
            .toString();
    }
}
