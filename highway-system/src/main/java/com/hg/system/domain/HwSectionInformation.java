package com.hg.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hg.common.annotation.Excel;
import com.hg.common.core.domain.BaseEntity;

/**
 * 路段信息对象 hw_section_information
 * 
 * @author ruoyi
 * @date 2023-03-13
 */
public class HwSectionInformation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 路段ID */
    private Long roadsectionid;

    /** 路线ID */
    @Excel(name = "路线ID")
    private Long routeid;

    /** 路线编码 */
    @Excel(name = "路线编码")
    private String routecoding;

    /** 路段编码 */
    @Excel(name = "路段编码")
    private String sectionCoding;

    /** 路段序列号 */
    @Excel(name = "路段序列号")
    private String sectionSerialNumber;

    /** 起点名称 */
    @Excel(name = "起点名称")
    private String originName;

    /** 起点经度 */
    @Excel(name = "起点经度")
    private String startingLongitude;

    /** 起点纬度 */
    @Excel(name = "起点纬度")
    private String startingLatitude;

    /** 起点桩号 */
    @Excel(name = "起点桩号")
    private String startingPileNumber;

    /** 起点高程（米） */
    @Excel(name = "起点高程", readConverterExp = "米=")
    private String startingElevation;

    /** 起点是否为分界点 */
    @Excel(name = "起点是否为分界点")
    private String cutoffPoint;

    /** 讫点名称 */
    @Excel(name = "讫点名称")
    private String destinationName;

    /** 讫点桩号 */
    @Excel(name = "讫点桩号")
    private String finishingPost;

    /** 讫点经度 */
    @Excel(name = "讫点经度")
    private String longitudepoint;

    /** 讫点纬度 */
    @Excel(name = "讫点纬度")
    private String latitudedestination;

    /** 讫点高程（米） */
    @Excel(name = "讫点高程", readConverterExp = "米=")
    private String elevationdestinationpoint;

    /** 讫点是否为分界点 */
    @Excel(name = "讫点是否为分界点")
    private String endpointdemarcationpoint;

    /** 路段技术等级 */
    @Excel(name = "路段技术等级")
    private String technicalGradeRoadSection;

    /** 路段路面类型 */
    @Excel(name = "路段路面类型")
    private String sectionPavementType;

    /** 路段里程(公里) */
    @Excel(name = "路段里程(公里)")
    private String roadLength;

    /** 实际里程(公里) */
    @Excel(name = "实际里程(公里)")
    private String actualMileage;

    /** 路面宽度（米） */
    @Excel(name = "路面宽度", readConverterExp = "米=")
    private String pavementWidth;

    /** 路基宽度（米） */
    @Excel(name = "路基宽度", readConverterExp = "米=")
    private String widthSubgrade;

    /** 是否为重复路段 */
    @Excel(name = "是否为重复路段")
    private String repeatedSection;

    /** 重复路线编码 */
    @Excel(name = "重复路线编码")
    private String repeatRouteCoding;

    /** 所重复路段序列 */
    @Excel(name = "所重复路段序列")
    private String sequenceRepeatedSections;

    /** 重复路段起点桩号 */
    @Excel(name = "重复路段起点桩号")
    private String repeatStartingPostNumber;

    /** 重复路段止点桩号 */
    @Excel(name = "重复路段止点桩号")
    private String repeatStopPostNumber;

    /** 可绿化里程(公里) */
    private String greenMileage;

    /** 已绿化里程(公里) */
    @Excel(name = "已绿化里程(公里)")
    private String okgreenMileage;

    /** 养护里程(公里) */
    @Excel(name = "养护里程(公里)")
    private String maintenanceMileage;

    /** 是否为城管路段 */
    @Excel(name = "是否为城管路段")
    private String chengguanSection;

    /** 是否晴雨通车 */
    @Excel(name = "是否晴雨通车")
    private String trafficFineRain;

    /** 是否一幅高速 */
    @Excel(name = "是否一幅高速")
    private String highSpeed;

    /** 晴雨通车里程(公里) */
    @Excel(name = "晴雨通车里程(公里)")
    private String fairRainMileage;

    /** 路面评定结果 */
    @Excel(name = "路面评定结果")
    private String pavementEvaluationResult;

    /** 纳入列养年份 */
    @Excel(name = "纳入列养年份")
    private String yearInclusion;

    /** 涵洞数量 */
    @Excel(name = "涵洞数量")
    private String numberCulverts;

    /** 收费性质 */
    @Excel(name = "收费性质")
    private String natureCharge;

    /** 是否断头路 */
    @Excel(name = "是否断头路")
    private String roadBroken;

    /** 最近改建时间(年份) */
    @Excel(name = "最近改建时间(年份)")
    private String lastModificationTime;

    /** 建成时间(年份) */
    @Excel(name = "建成时间(年份)")
    private String completionTime;

    /** 管养单位代码 */
    @Excel(name = "管养单位代码")
    private String custodialUnitCode;

    /** 管养单位名称 */
    @Excel(name = "管养单位名称")
    private String nameCustodialUnit;

    /** 养护类型(按时间分) */
    @Excel(name = "养护类型(按时间分)")
    private String curingType;

    /** 养护类型(按资金来源分) */
    @Excel(name = "养护类型(按资金来源分)")
    private String typeConservation;

    /** 变更原因 */
    @Excel(name = "变更原因")
    private String reasonChange;

    /** 地貌 */
    @Excel(name = "地貌")
    private String landform;

    /** 通车日期 */
    @Excel(name = "通车日期")
    private String openingDate;

    /** 车道数量 */
    @Excel(name = "车道数量")
    private String numberLanes;

    /** 断链类型 */
    @Excel(name = "断链类型")
    private String typeBrokenChain;

    /** 原路线编码 */
    @Excel(name = "原路线编码")
    private String originalRouteCoding;

    /** 原路线名称 */
    @Excel(name = "原路线名称")
    private String originalRouteName;

    /** 原路段起点桩号 */
    @Excel(name = "原路段起点桩号")
    private String ofTheOriginalSection;

    /** 原路段讫点桩号 */
    @Excel(name = "原路段讫点桩号")
    private String theOriginalSection;

    /** 原路段里程(公里) */
    @Excel(name = "原路段里程(公里)")
    private String originalRoadLength;

    /** 设计时速 */
    @Excel(name = "设计时速")
    private String designSpeed;

    /** 省际出入口 */
    @Excel(name = "省际出入口")
    private String interprovincialentrancesexits;

    /** 面层厚度(厘米) */
    @Excel(name = "面层厚度(厘米)")
    private String surfaceThickness;

    /** 国道桩号传递预留里程(公里) */
    @Excel(name = "国道桩号传递预留里程(公里)")
    private String nationalHighwayNumber;

    /** 是否按干线公路接养 */
    @Excel(name = "是否按干线公路接养")
    private String adoptTrunkRoad;

    /** 最近一次修复养护年度 */
    @Excel(name = "最近一次修复养护年度")
    private String lastyearrepairmaintenance;

    /** 填报单位 */
    @Excel(name = "填报单位")
    private String reportingUnit;

    /** 填报单位代码 */
    @Excel(name = "填报单位代码")
    private String fillUnitCode;

    /** 填报单位负责人 */
    @Excel(name = "填报单位负责人")
    private String personchargereportingUnit;

    /** 填表人 */
    @Excel(name = "填表人")
    private String formfiller;

    /** 填表人电话 */
    @Excel(name = "填表人电话")
    private String telephonenumberperson;

    /** 审核人 */
    @Excel(name = "审核人")
    private String auditor;

    /** 审核人电话 */
    @Excel(name = "审核人电话")
    private String auditorTelephone;

    /** 采集时间 */
    @Excel(name = "采集时间")
    private String acquisitionTime;

    /** 修改时间 */
    @Excel(name = "修改时间")
    private String modificationTime;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    public void setRoadsectionid(Long roadsectionid) 
    {
        this.roadsectionid = roadsectionid;
    }

    public Long getRoadsectionid() 
    {
        return roadsectionid;
    }
    public void setRouteid(Long routeid) 
    {
        this.routeid = routeid;
    }

    public Long getRouteid() 
    {
        return routeid;
    }
    public void setRoutecoding(String routecoding) 
    {
        this.routecoding = routecoding;
    }

    public String getRoutecoding() 
    {
        return routecoding;
    }
    public void setSectionCoding(String sectionCoding) 
    {
        this.sectionCoding = sectionCoding;
    }

    public String getSectionCoding() 
    {
        return sectionCoding;
    }
    public void setSectionSerialNumber(String sectionSerialNumber) 
    {
        this.sectionSerialNumber = sectionSerialNumber;
    }

    public String getSectionSerialNumber() 
    {
        return sectionSerialNumber;
    }
    public void setOriginName(String originName) 
    {
        this.originName = originName;
    }

    public String getOriginName() 
    {
        return originName;
    }
    public void setStartingLongitude(String startingLongitude) 
    {
        this.startingLongitude = startingLongitude;
    }

    public String getStartingLongitude() 
    {
        return startingLongitude;
    }
    public void setStartingLatitude(String startingLatitude) 
    {
        this.startingLatitude = startingLatitude;
    }

    public String getStartingLatitude() 
    {
        return startingLatitude;
    }
    public void setStartingPileNumber(String startingPileNumber) 
    {
        this.startingPileNumber = startingPileNumber;
    }

    public String getStartingPileNumber() 
    {
        return startingPileNumber;
    }
    public void setStartingElevation(String startingElevation) 
    {
        this.startingElevation = startingElevation;
    }

    public String getStartingElevation() 
    {
        return startingElevation;
    }
    public void setCutoffPoint(String cutoffPoint) 
    {
        this.cutoffPoint = cutoffPoint;
    }

    public String getCutoffPoint() 
    {
        return cutoffPoint;
    }
    public void setDestinationName(String destinationName) 
    {
        this.destinationName = destinationName;
    }

    public String getDestinationName() 
    {
        return destinationName;
    }
    public void setFinishingPost(String finishingPost) 
    {
        this.finishingPost = finishingPost;
    }

    public String getFinishingPost() 
    {
        return finishingPost;
    }
    public void setLongitudepoint(String longitudepoint) 
    {
        this.longitudepoint = longitudepoint;
    }

    public String getLongitudepoint() 
    {
        return longitudepoint;
    }
    public void setLatitudedestination(String latitudedestination) 
    {
        this.latitudedestination = latitudedestination;
    }

    public String getLatitudedestination() 
    {
        return latitudedestination;
    }
    public void setElevationdestinationpoint(String elevationdestinationpoint) 
    {
        this.elevationdestinationpoint = elevationdestinationpoint;
    }

    public String getElevationdestinationpoint() 
    {
        return elevationdestinationpoint;
    }
    public void setEndpointdemarcationpoint(String endpointdemarcationpoint) 
    {
        this.endpointdemarcationpoint = endpointdemarcationpoint;
    }

    public String getEndpointdemarcationpoint() 
    {
        return endpointdemarcationpoint;
    }
    public void setTechnicalGradeRoadSection(String technicalGradeRoadSection) 
    {
        this.technicalGradeRoadSection = technicalGradeRoadSection;
    }

    public String getTechnicalGradeRoadSection() 
    {
        return technicalGradeRoadSection;
    }
    public void setSectionPavementType(String sectionPavementType) 
    {
        this.sectionPavementType = sectionPavementType;
    }

    public String getSectionPavementType() 
    {
        return sectionPavementType;
    }
    public void setRoadLength(String roadLength) 
    {
        this.roadLength = roadLength;
    }

    public String getRoadLength() 
    {
        return roadLength;
    }
    public void setActualMileage(String actualMileage) 
    {
        this.actualMileage = actualMileage;
    }

    public String getActualMileage() 
    {
        return actualMileage;
    }
    public void setPavementWidth(String pavementWidth) 
    {
        this.pavementWidth = pavementWidth;
    }

    public String getPavementWidth() 
    {
        return pavementWidth;
    }
    public void setWidthSubgrade(String widthSubgrade) 
    {
        this.widthSubgrade = widthSubgrade;
    }

    public String getWidthSubgrade() 
    {
        return widthSubgrade;
    }
    public void setRepeatedSection(String repeatedSection) 
    {
        this.repeatedSection = repeatedSection;
    }

    public String getRepeatedSection() 
    {
        return repeatedSection;
    }
    public void setRepeatRouteCoding(String repeatRouteCoding) 
    {
        this.repeatRouteCoding = repeatRouteCoding;
    }

    public String getRepeatRouteCoding() 
    {
        return repeatRouteCoding;
    }
    public void setSequenceRepeatedSections(String sequenceRepeatedSections) 
    {
        this.sequenceRepeatedSections = sequenceRepeatedSections;
    }

    public String getSequenceRepeatedSections() 
    {
        return sequenceRepeatedSections;
    }
    public void setRepeatStartingPostNumber(String repeatStartingPostNumber) 
    {
        this.repeatStartingPostNumber = repeatStartingPostNumber;
    }

    public String getRepeatStartingPostNumber() 
    {
        return repeatStartingPostNumber;
    }
    public void setRepeatStopPostNumber(String repeatStopPostNumber) 
    {
        this.repeatStopPostNumber = repeatStopPostNumber;
    }

    public String getRepeatStopPostNumber() 
    {
        return repeatStopPostNumber;
    }
    public void setGreenMileage(String greenMileage) 
    {
        this.greenMileage = greenMileage;
    }

    public String getGreenMileage() 
    {
        return greenMileage;
    }
    public void setOkgreenMileage(String okgreenMileage) 
    {
        this.okgreenMileage = okgreenMileage;
    }

    public String getOkgreenMileage() 
    {
        return okgreenMileage;
    }
    public void setMaintenanceMileage(String maintenanceMileage) 
    {
        this.maintenanceMileage = maintenanceMileage;
    }

    public String getMaintenanceMileage() 
    {
        return maintenanceMileage;
    }
    public void setChengguanSection(String chengguanSection) 
    {
        this.chengguanSection = chengguanSection;
    }

    public String getChengguanSection() 
    {
        return chengguanSection;
    }
    public void setTrafficFineRain(String trafficFineRain) 
    {
        this.trafficFineRain = trafficFineRain;
    }

    public String getTrafficFineRain() 
    {
        return trafficFineRain;
    }
    public void setHighSpeed(String highSpeed) 
    {
        this.highSpeed = highSpeed;
    }

    public String getHighSpeed() 
    {
        return highSpeed;
    }
    public void setFairRainMileage(String fairRainMileage) 
    {
        this.fairRainMileage = fairRainMileage;
    }

    public String getFairRainMileage() 
    {
        return fairRainMileage;
    }
    public void setPavementEvaluationResult(String pavementEvaluationResult) 
    {
        this.pavementEvaluationResult = pavementEvaluationResult;
    }

    public String getPavementEvaluationResult() 
    {
        return pavementEvaluationResult;
    }
    public void setYearInclusion(String yearInclusion) 
    {
        this.yearInclusion = yearInclusion;
    }

    public String getYearInclusion() 
    {
        return yearInclusion;
    }
    public void setNumberCulverts(String numberCulverts) 
    {
        this.numberCulverts = numberCulverts;
    }

    public String getNumberCulverts() 
    {
        return numberCulverts;
    }
    public void setNatureCharge(String natureCharge) 
    {
        this.natureCharge = natureCharge;
    }

    public String getNatureCharge() 
    {
        return natureCharge;
    }
    public void setRoadBroken(String roadBroken) 
    {
        this.roadBroken = roadBroken;
    }

    public String getRoadBroken() 
    {
        return roadBroken;
    }
    public void setLastModificationTime(String lastModificationTime) 
    {
        this.lastModificationTime = lastModificationTime;
    }

    public String getLastModificationTime() 
    {
        return lastModificationTime;
    }
    public void setCompletionTime(String completionTime) 
    {
        this.completionTime = completionTime;
    }

    public String getCompletionTime() 
    {
        return completionTime;
    }
    public void setCustodialUnitCode(String custodialUnitCode) 
    {
        this.custodialUnitCode = custodialUnitCode;
    }

    public String getCustodialUnitCode() 
    {
        return custodialUnitCode;
    }
    public void setNameCustodialUnit(String nameCustodialUnit) 
    {
        this.nameCustodialUnit = nameCustodialUnit;
    }

    public String getNameCustodialUnit() 
    {
        return nameCustodialUnit;
    }
    public void setCuringType(String curingType) 
    {
        this.curingType = curingType;
    }

    public String getCuringType() 
    {
        return curingType;
    }
    public void setTypeConservation(String typeConservation) 
    {
        this.typeConservation = typeConservation;
    }

    public String getTypeConservation() 
    {
        return typeConservation;
    }
    public void setReasonChange(String reasonChange) 
    {
        this.reasonChange = reasonChange;
    }

    public String getReasonChange() 
    {
        return reasonChange;
    }
    public void setLandform(String landform) 
    {
        this.landform = landform;
    }

    public String getLandform() 
    {
        return landform;
    }
    public void setOpeningDate(String openingDate) 
    {
        this.openingDate = openingDate;
    }

    public String getOpeningDate() 
    {
        return openingDate;
    }
    public void setNumberLanes(String numberLanes) 
    {
        this.numberLanes = numberLanes;
    }

    public String getNumberLanes() 
    {
        return numberLanes;
    }
    public void setTypeBrokenChain(String typeBrokenChain) 
    {
        this.typeBrokenChain = typeBrokenChain;
    }

    public String getTypeBrokenChain() 
    {
        return typeBrokenChain;
    }
    public void setOriginalRouteCoding(String originalRouteCoding) 
    {
        this.originalRouteCoding = originalRouteCoding;
    }

    public String getOriginalRouteCoding() 
    {
        return originalRouteCoding;
    }
    public void setOriginalRouteName(String originalRouteName) 
    {
        this.originalRouteName = originalRouteName;
    }

    public String getOriginalRouteName() 
    {
        return originalRouteName;
    }
    public void setOfTheOriginalSection(String ofTheOriginalSection) 
    {
        this.ofTheOriginalSection = ofTheOriginalSection;
    }

    public String getOfTheOriginalSection() 
    {
        return ofTheOriginalSection;
    }
    public void setTheOriginalSection(String theOriginalSection) 
    {
        this.theOriginalSection = theOriginalSection;
    }

    public String getTheOriginalSection() 
    {
        return theOriginalSection;
    }
    public void setOriginalRoadLength(String originalRoadLength) 
    {
        this.originalRoadLength = originalRoadLength;
    }

    public String getOriginalRoadLength() 
    {
        return originalRoadLength;
    }
    public void setDesignSpeed(String designSpeed) 
    {
        this.designSpeed = designSpeed;
    }

    public String getDesignSpeed() 
    {
        return designSpeed;
    }
    public void setInterprovincialentrancesexits(String interprovincialentrancesexits) 
    {
        this.interprovincialentrancesexits = interprovincialentrancesexits;
    }

    public String getInterprovincialentrancesexits() 
    {
        return interprovincialentrancesexits;
    }
    public void setSurfaceThickness(String surfaceThickness) 
    {
        this.surfaceThickness = surfaceThickness;
    }

    public String getSurfaceThickness() 
    {
        return surfaceThickness;
    }
    public void setNationalHighwayNumber(String nationalHighwayNumber) 
    {
        this.nationalHighwayNumber = nationalHighwayNumber;
    }

    public String getNationalHighwayNumber() 
    {
        return nationalHighwayNumber;
    }
    public void setAdoptTrunkRoad(String adoptTrunkRoad) 
    {
        this.adoptTrunkRoad = adoptTrunkRoad;
    }

    public String getAdoptTrunkRoad() 
    {
        return adoptTrunkRoad;
    }
    public void setLastyearrepairmaintenance(String lastyearrepairmaintenance) 
    {
        this.lastyearrepairmaintenance = lastyearrepairmaintenance;
    }

    public String getLastyearrepairmaintenance() 
    {
        return lastyearrepairmaintenance;
    }
    public void setReportingUnit(String reportingUnit) 
    {
        this.reportingUnit = reportingUnit;
    }

    public String getReportingUnit() 
    {
        return reportingUnit;
    }
    public void setFillUnitCode(String fillUnitCode) 
    {
        this.fillUnitCode = fillUnitCode;
    }

    public String getFillUnitCode() 
    {
        return fillUnitCode;
    }
    public void setPersonchargereportingUnit(String personchargereportingUnit) 
    {
        this.personchargereportingUnit = personchargereportingUnit;
    }

    public String getPersonchargereportingUnit() 
    {
        return personchargereportingUnit;
    }
    public void setFormfiller(String formfiller) 
    {
        this.formfiller = formfiller;
    }

    public String getFormfiller() 
    {
        return formfiller;
    }
    public void setTelephonenumberperson(String telephonenumberperson) 
    {
        this.telephonenumberperson = telephonenumberperson;
    }

    public String getTelephonenumberperson() 
    {
        return telephonenumberperson;
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
    public void setAcquisitionTime(String acquisitionTime) 
    {
        this.acquisitionTime = acquisitionTime;
    }

    public String getAcquisitionTime() 
    {
        return acquisitionTime;
    }
    public void setModificationTime(String modificationTime) 
    {
        this.modificationTime = modificationTime;
    }

    public String getModificationTime() 
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
            .append("roadsectionid", getRoadsectionid())
            .append("routeid", getRouteid())
            .append("routecoding", getRoutecoding())
            .append("sectionCoding", getSectionCoding())
            .append("sectionSerialNumber", getSectionSerialNumber())
            .append("originName", getOriginName())
            .append("startingLongitude", getStartingLongitude())
            .append("startingLatitude", getStartingLatitude())
            .append("startingPileNumber", getStartingPileNumber())
            .append("startingElevation", getStartingElevation())
            .append("cutoffPoint", getCutoffPoint())
            .append("destinationName", getDestinationName())
            .append("finishingPost", getFinishingPost())
            .append("longitudepoint", getLongitudepoint())
            .append("latitudedestination", getLatitudedestination())
            .append("elevationdestinationpoint", getElevationdestinationpoint())
            .append("endpointdemarcationpoint", getEndpointdemarcationpoint())
            .append("technicalGradeRoadSection", getTechnicalGradeRoadSection())
            .append("sectionPavementType", getSectionPavementType())
            .append("roadLength", getRoadLength())
            .append("actualMileage", getActualMileage())
            .append("pavementWidth", getPavementWidth())
            .append("widthSubgrade", getWidthSubgrade())
            .append("repeatedSection", getRepeatedSection())
            .append("repeatRouteCoding", getRepeatRouteCoding())
            .append("sequenceRepeatedSections", getSequenceRepeatedSections())
            .append("repeatStartingPostNumber", getRepeatStartingPostNumber())
            .append("repeatStopPostNumber", getRepeatStopPostNumber())
            .append("greenMileage", getGreenMileage())
            .append("okgreenMileage", getOkgreenMileage())
            .append("maintenanceMileage", getMaintenanceMileage())
            .append("chengguanSection", getChengguanSection())
            .append("trafficFineRain", getTrafficFineRain())
            .append("highSpeed", getHighSpeed())
            .append("fairRainMileage", getFairRainMileage())
            .append("pavementEvaluationResult", getPavementEvaluationResult())
            .append("yearInclusion", getYearInclusion())
            .append("numberCulverts", getNumberCulverts())
            .append("natureCharge", getNatureCharge())
            .append("roadBroken", getRoadBroken())
            .append("lastModificationTime", getLastModificationTime())
            .append("completionTime", getCompletionTime())
            .append("custodialUnitCode", getCustodialUnitCode())
            .append("nameCustodialUnit", getNameCustodialUnit())
            .append("curingType", getCuringType())
            .append("typeConservation", getTypeConservation())
            .append("reasonChange", getReasonChange())
            .append("landform", getLandform())
            .append("openingDate", getOpeningDate())
            .append("numberLanes", getNumberLanes())
            .append("typeBrokenChain", getTypeBrokenChain())
            .append("originalRouteCoding", getOriginalRouteCoding())
            .append("originalRouteName", getOriginalRouteName())
            .append("ofTheOriginalSection", getOfTheOriginalSection())
            .append("theOriginalSection", getTheOriginalSection())
            .append("originalRoadLength", getOriginalRoadLength())
            .append("designSpeed", getDesignSpeed())
            .append("interprovincialentrancesexits", getInterprovincialentrancesexits())
            .append("surfaceThickness", getSurfaceThickness())
            .append("nationalHighwayNumber", getNationalHighwayNumber())
            .append("adoptTrunkRoad", getAdoptTrunkRoad())
            .append("lastyearrepairmaintenance", getLastyearrepairmaintenance())
            .append("reportingUnit", getReportingUnit())
            .append("fillUnitCode", getFillUnitCode())
            .append("personchargereportingUnit", getPersonchargereportingUnit())
            .append("formfiller", getFormfiller())
            .append("telephonenumberperson", getTelephonenumberperson())
            .append("auditor", getAuditor())
            .append("auditorTelephone", getAuditorTelephone())
            .append("acquisitionTime", getAcquisitionTime())
            .append("modificationTime", getModificationTime())
            .append("remarks", getRemarks())
            .toString();
    }
}
