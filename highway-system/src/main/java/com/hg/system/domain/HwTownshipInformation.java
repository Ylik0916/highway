package com.hg.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hg.common.annotation.Excel;
import com.hg.common.core.domain.BaseEntity;

/**
 * 乡镇对象 hw_township_information
 * 
 * @author ruoyi
 * @date 2023-03-14
 */
public class HwTownshipInformation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 乡镇ID */
    private Long townshipId;

    /** 乡镇名称 */
    @Excel(name = "乡镇名称")
    private String nameOfTownship;

    /** 区域人口 */
    @Excel(name = "区域人口")
    private Long regionalPopulation;

    /** 所属建制村数量 */
    private Integer numberOfIncorporatedVillages;

    /** 行政区编号 */
    @Excel(name = "行政区编号")
    private Long administrativeDivisionNumber;

    /** 经纬度类型 1高德 2GPS */
    private String latitudeAndLongitudeType;

    /** 经度 */
    private String longitude;

    /** 维度 */
    private String latitude;

    /** 所属地形 */
    private String landform;

    /** 岛屿是否建有陆岛交通码头 */
    private String islandTransportationTerminal;

    /** 码头与陆地距离(公里) */
    private BigDecimal distanceFromPierToLand;

    /** 岛内是否建有公路 */
    private String roadsOnTheIsland;

    /** 是否适宜通公路 */
    private String suitableForRoadAccess;

    /** 乡镇政府高程（米） */
    private Long elevationOfTownshipGovernment;

    /** 不适宜原因 */
    private String causeOfUnsuitability;

    /** 通达现状 */
    @Excel(name = "通达现状")
    private String accessStatusQuo;

    /** 乡镇通达位置 */
    private String locationOfTownshipAccess;

    /** 优选通达路线行政等级 */
    private String administrativeLevelAccessRoute;

    /** 优选通达路线编码 */
    private String optimalAccessRouteCoding;

    /** 优选通达路线名称 */
    private String accessRouteIsPreferred;

    /** 通达方向 */
    private String directionOfAccess;

    /** 待建路段里程(公里) */
    private BigDecimal lengthOfRoadToBeBuilt;

    /** 待建路段起点桩号 */
    private String pointOfTheRoadSection;

    /** 待建路段讫点桩号 */
    private String endPointOfTheRoadToBeBuilt;

    /** 填报单位代码 */
    private String fillInTheUnitCode;

    /** 填报单位 */
    private String reportingUnit;

    /** 填报单位负责人 */
    private String personInCharge;

    /** 填表人 */
    private String formFiller;

    /** 填表人电话 */
    private String personFillingInTheForm;

    /** 审核人 */
    private String auditor;

    /** 审核人电话 */
    private String auditorTelephone;

    /** 变化程度 */
    private String degreeOfChange;

    /** 采集标记 */
    private String acquisitionMark;

    /** 是否纳入统计 */
    private String includedInStatistics;

    /** 采集时间 */
    private Date acquisitionTime;

    /** 修改时间 */
    private Date modificationTime;

    /** 备注 */
    private String remarks;

    public void setTownshipId(Long townshipId) 
    {
        this.townshipId = townshipId;
    }

    public Long getTownshipId() 
    {
        return townshipId;
    }
    public void setNameOfTownship(String nameOfTownship) 
    {
        this.nameOfTownship = nameOfTownship;
    }

    public String getNameOfTownship() 
    {
        return nameOfTownship;
    }
    public void setRegionalPopulation(Long regionalPopulation) 
    {
        this.regionalPopulation = regionalPopulation;
    }

    public Long getRegionalPopulation() 
    {
        return regionalPopulation;
    }
    public void setNumberOfIncorporatedVillages(Integer numberOfIncorporatedVillages) 
    {
        this.numberOfIncorporatedVillages = numberOfIncorporatedVillages;
    }

    public Integer getNumberOfIncorporatedVillages() 
    {
        return numberOfIncorporatedVillages;
    }
    public void setAdministrativeDivisionNumber(Long administrativeDivisionNumber) 
    {
        this.administrativeDivisionNumber = administrativeDivisionNumber;
    }

    public Long getAdministrativeDivisionNumber() 
    {
        return administrativeDivisionNumber;
    }
    public void setLatitudeAndLongitudeType(String latitudeAndLongitudeType)
    {
        this.latitudeAndLongitudeType = latitudeAndLongitudeType;
    }

    public String getLatitudeAndLongitudeType()
    {
        return latitudeAndLongitudeType;
    }
    public void setLongitude(String longitude) 
    {
        this.longitude = longitude;
    }

    public String getLongitude() 
    {
        return longitude;
    }
    public void setLatitude(String latitude) 
    {
        this.latitude = latitude;
    }

    public String getLatitude() 
    {
        return latitude;
    }
    public void setLandform(String landform)
    {
        this.landform = landform;
    }

    public String getLandform()
    {
        return landform;
    }
    public void setIslandTransportationTerminal(String islandTransportationTerminal) 
    {
        this.islandTransportationTerminal = islandTransportationTerminal;
    }

    public String getIslandTransportationTerminal() 
    {
        return islandTransportationTerminal;
    }
    public void setDistanceFromPierToLand(BigDecimal distanceFromPierToLand) 
    {
        this.distanceFromPierToLand = distanceFromPierToLand;
    }

    public BigDecimal getDistanceFromPierToLand() 
    {
        return distanceFromPierToLand;
    }
    public void setRoadsOnTheIsland(String roadsOnTheIsland) 
    {
        this.roadsOnTheIsland = roadsOnTheIsland;
    }

    public String getRoadsOnTheIsland() 
    {
        return roadsOnTheIsland;
    }
    public void setSuitableForRoadAccess(String suitableForRoadAccess) 
    {
        this.suitableForRoadAccess = suitableForRoadAccess;
    }

    public String getSuitableForRoadAccess() 
    {
        return suitableForRoadAccess;
    }
    public void setElevationOfTownshipGovernment(Long elevationOfTownshipGovernment) 
    {
        this.elevationOfTownshipGovernment = elevationOfTownshipGovernment;
    }

    public Long getElevationOfTownshipGovernment() 
    {
        return elevationOfTownshipGovernment;
    }
    public void setCauseOfUnsuitability(String causeOfUnsuitability) 
    {
        this.causeOfUnsuitability = causeOfUnsuitability;
    }

    public String getCauseOfUnsuitability() 
    {
        return causeOfUnsuitability;
    }
    public void setAccessStatusQuo(String accessStatusQuo)
    {
        this.accessStatusQuo = accessStatusQuo;
    }

    public String getAccessStatusQuo()
    {
        return accessStatusQuo;
    }
    public void setLocationOfTownshipAccess(String locationOfTownshipAccess)
    {
        this.locationOfTownshipAccess = locationOfTownshipAccess;
    }

    public String getLocationOfTownshipAccess()
    {
        return locationOfTownshipAccess;
    }
    public void setAdministrativeLevelAccessRoute(String administrativeLevelAccessRoute) 
    {
        this.administrativeLevelAccessRoute = administrativeLevelAccessRoute;
    }

    public String getAdministrativeLevelAccessRoute() 
    {
        return administrativeLevelAccessRoute;
    }
    public void setOptimalAccessRouteCoding(String optimalAccessRouteCoding) 
    {
        this.optimalAccessRouteCoding = optimalAccessRouteCoding;
    }

    public String getOptimalAccessRouteCoding() 
    {
        return optimalAccessRouteCoding;
    }
    public void setAccessRouteIsPreferred(String accessRouteIsPreferred) 
    {
        this.accessRouteIsPreferred = accessRouteIsPreferred;
    }

    public String getAccessRouteIsPreferred() 
    {
        return accessRouteIsPreferred;
    }
    public void setDirectionOfAccess(String directionOfAccess)
    {
        this.directionOfAccess = directionOfAccess;
    }

    public String getDirectionOfAccess()
    {
        return directionOfAccess;
    }
    public void setLengthOfRoadToBeBuilt(BigDecimal lengthOfRoadToBeBuilt) 
    {
        this.lengthOfRoadToBeBuilt = lengthOfRoadToBeBuilt;
    }

    public BigDecimal getLengthOfRoadToBeBuilt() 
    {
        return lengthOfRoadToBeBuilt;
    }
    public void setPointOfTheRoadSection(String pointOfTheRoadSection) 
    {
        this.pointOfTheRoadSection = pointOfTheRoadSection;
    }

    public String getPointOfTheRoadSection() 
    {
        return pointOfTheRoadSection;
    }
    public void setEndPointOfTheRoadToBeBuilt(String endPointOfTheRoadToBeBuilt) 
    {
        this.endPointOfTheRoadToBeBuilt = endPointOfTheRoadToBeBuilt;
    }

    public String getEndPointOfTheRoadToBeBuilt() 
    {
        return endPointOfTheRoadToBeBuilt;
    }
    public void setFillInTheUnitCode(String fillInTheUnitCode) 
    {
        this.fillInTheUnitCode = fillInTheUnitCode;
    }

    public String getFillInTheUnitCode() 
    {
        return fillInTheUnitCode;
    }
    public void setReportingUnit(String reportingUnit) 
    {
        this.reportingUnit = reportingUnit;
    }

    public String getReportingUnit() 
    {
        return reportingUnit;
    }
    public void setPersonInCharge(String personInCharge) 
    {
        this.personInCharge = personInCharge;
    }

    public String getPersonInCharge() 
    {
        return personInCharge;
    }
    public void setFormFiller(String formFiller) 
    {
        this.formFiller = formFiller;
    }

    public String getFormFiller() 
    {
        return formFiller;
    }
    public void setPersonFillingInTheForm(String personFillingInTheForm) 
    {
        this.personFillingInTheForm = personFillingInTheForm;
    }

    public String getPersonFillingInTheForm() 
    {
        return personFillingInTheForm;
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
    public void setDegreeOfChange(String degreeOfChange) 
    {
        this.degreeOfChange = degreeOfChange;
    }

    public String getDegreeOfChange() 
    {
        return degreeOfChange;
    }
    public void setAcquisitionMark(String acquisitionMark) 
    {
        this.acquisitionMark = acquisitionMark;
    }

    public String getAcquisitionMark() 
    {
        return acquisitionMark;
    }
    public void setIncludedInStatistics(String includedInStatistics) 
    {
        this.includedInStatistics = includedInStatistics;
    }

    public String getIncludedInStatistics() 
    {
        return includedInStatistics;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("townshipId", getTownshipId())
            .append("nameOfTownship", getNameOfTownship())
            .append("regionalPopulation", getRegionalPopulation())
            .append("numberOfIncorporatedVillages", getNumberOfIncorporatedVillages())
            .append("administrativeDivisionNumber", getAdministrativeDivisionNumber())
            .append("latitudeAndLongitudeType", getLatitudeAndLongitudeType())
            .append("longitude", getLongitude())
            .append("latitude", getLatitude())
            .append("landform", getLandform())
            .append("islandTransportationTerminal", getIslandTransportationTerminal())
            .append("distanceFromPierToLand", getDistanceFromPierToLand())
            .append("roadsOnTheIsland", getRoadsOnTheIsland())
            .append("suitableForRoadAccess", getSuitableForRoadAccess())
            .append("elevationOfTownshipGovernment", getElevationOfTownshipGovernment())
            .append("causeOfUnsuitability", getCauseOfUnsuitability())
            .append("accessStatusQuo", getAccessStatusQuo())
            .append("locationOfTownshipAccess", getLocationOfTownshipAccess())
            .append("administrativeLevelAccessRoute", getAdministrativeLevelAccessRoute())
            .append("optimalAccessRouteCoding", getOptimalAccessRouteCoding())
            .append("accessRouteIsPreferred", getAccessRouteIsPreferred())
            .append("directionOfAccess", getDirectionOfAccess())
            .append("lengthOfRoadToBeBuilt", getLengthOfRoadToBeBuilt())
            .append("pointOfTheRoadSection", getPointOfTheRoadSection())
            .append("endPointOfTheRoadToBeBuilt", getEndPointOfTheRoadToBeBuilt())
            .append("fillInTheUnitCode", getFillInTheUnitCode())
            .append("reportingUnit", getReportingUnit())
            .append("personInCharge", getPersonInCharge())
            .append("formFiller", getFormFiller())
            .append("personFillingInTheForm", getPersonFillingInTheForm())
            .append("auditor", getAuditor())
            .append("auditorTelephone", getAuditorTelephone())
            .append("degreeOfChange", getDegreeOfChange())
            .append("acquisitionMark", getAcquisitionMark())
            .append("includedInStatistics", getIncludedInStatistics())
            .append("acquisitionTime", getAcquisitionTime())
            .append("modificationTime", getModificationTime())
            .append("remarks", getRemarks())
            .toString();
    }
}
