package com.hg.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hg.common.annotation.Excel;
import com.hg.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 道路病害管理对象 hw_way_disease
 * 
 * @author ruoyi
 * @date 2023-03-08
 */
public class HwWayDisease extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */

    private Long routeId;

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    private Long wdid;

    /** 病害名称 */
    @Excel(name = "病害名称")
    private String diseaseName;

    /** 路线编号 */
    @Excel(name = "路线编号")
    private String pathCode;

    /** 路线名称 */
    @Excel(name = "路线名称")
    private String pathName;

    /** 路段编号 */
    @Excel(name = "路段编号")
    private String sectionCode;

    /** 路段名称 */
    @Excel(name = "路段名称")
    private String sectionName;

    /** 上报人 */
    @Excel(name = "上报人")
    private String reporter;

    /** 上报日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上报日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reportDate;

    /** 上报类型 */
    @Excel(name = "上报类型")
    private String reportType;

    /** 状态 */
    @Excel(name = "状态")
    private String statusid;

    /** 所属路线 */
    @Excel(name = "所属路线")
    private String wherePath;

    /** 病害中心桩号 */
    @Excel(name = "病害中心桩号")
    private String diseaseStake;

    /** 病害纬度 */
    @Excel(name = "病害纬度")
    private String diseaseLatitude;

    /** 病害经度 */
    @Excel(name = "病害经度")
    private String diseaseLongitude;

    /** 病害信息 */
    @Excel(name = "病害信息")
    private String diseaseMessage;

    /** 病害图片 */
    @Excel(name = "病害图片")
    private String diseaseImg;

    /** 养护地点 */
    @Excel(name = "养护地点")
    private String maintainSite;

    /** 养护情况 */
    @Excel(name = "养护情况")
    private String maintainCase;

    /** 养护人 */
    @Excel(name = "养护人")
    private String maintainPeople;

    /** 养护经费 */
    @Excel(name = "养护经费")
    private String maintainFund;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date beginTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date overTime;

    /** 养护备注 */
    @Excel(name = "养护备注")
    private String maintainRemark;

    /** 养护图片 */
    @Excel(name = "养护图片")
    private String maintainImg;

    /** 管养单位 */
    @Excel(name = "管养单位")
    private String maintainUnit;

    /** 损坏情况 */
    @Excel(name = "损坏情况")
    private String damageCase;

    /** 横向位置 */
    @Excel(name = "横向位置")
    private String horizontalPositions;

    /** 预估信息 */
    @Excel(name = "预估信息")
    private String forecastCase;

    /** 处置措施 */
    @Excel(name = "处置措施")
    private String disposeMeasure;

    /** 行驶方向 */
    @Excel(name = "行驶方向")
    private String drivingDirection;

    /** 处置中心桩号 */
    @Excel(name = "处置中心桩号")
    private String disposeStake;

    /** 处置备注 */
    @Excel(name = "处置备注")
    private String disposeRemark;

    /** 处置时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "处置时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date disposeTime;

    /** 处置图片 */
    @Excel(name = "处置图片")
    private String disposeImg;

    /** 行政区 */
    private String administrative;

    public String getAdministrative() {
        return administrative;
    }

    public void setAdministrative(String administrative) {
        this.administrative = administrative;
    }

    public void setWdid(Long wdid)
    {
        this.wdid = wdid;
    }

    public Long getWdid() 
    {
        return wdid;
    }
    public void setDiseaseName(String diseaseName) 
    {
        this.diseaseName = diseaseName;
    }

    public String getDiseaseName() 
    {
        return diseaseName;
    }
    public void setPathCode(String pathCode) 
    {
        this.pathCode = pathCode;
    }

    public String getPathCode() 
    {
        return pathCode;
    }
    public void setPathName(String pathName) 
    {
        this.pathName = pathName;
    }

    public String getPathName() 
    {
        return pathName;
    }
    public void setSectionCode(String sectionCode) 
    {
        this.sectionCode = sectionCode;
    }

    public String getSectionCode() 
    {
        return sectionCode;
    }
    public void setSectionName(String sectionName) 
    {
        this.sectionName = sectionName;
    }

    public String getSectionName() 
    {
        return sectionName;
    }
    public void setReporter(String reporter) 
    {
        this.reporter = reporter;
    }

    public String getReporter() 
    {
        return reporter;
    }
    public void setReportDate(Date reportDate) 
    {
        this.reportDate = reportDate;
    }

    public Date getReportDate() 
    {
        return reportDate;
    }
    public void setReportType(String reportType) 
    {
        this.reportType = reportType;
    }

    public String getReportType() 
    {
        return reportType;
    }
    public void setStatusid(String statusid)
    {
        this.statusid = statusid;
    }

    public String getStatusid()
    {
        return statusid;
    }
    public void setWherePath(String wherePath) 
    {
        this.wherePath = wherePath;
    }

    public String getWherePath() 
    {
        return wherePath;
    }
    public void setDiseaseStake(String diseaseStake) 
    {
        this.diseaseStake = diseaseStake;
    }

    public String getDiseaseStake() 
    {
        return diseaseStake;
    }
    public void setDiseaseLatitude(String diseaseLatitude) 
    {
        this.diseaseLatitude = diseaseLatitude;
    }

    public String getDiseaseLatitude() 
    {
        return diseaseLatitude;
    }
    public void setDiseaseLongitude(String diseaseLongitude) 
    {
        this.diseaseLongitude = diseaseLongitude;
    }

    public String getDiseaseLongitude() 
    {
        return diseaseLongitude;
    }
    public void setDiseaseMessage(String diseaseMessage) 
    {
        this.diseaseMessage = diseaseMessage;
    }

    public String getDiseaseMessage() 
    {
        return diseaseMessage;
    }
    public void setDiseaseImg(String diseaseImg) 
    {
        this.diseaseImg = diseaseImg;
    }

    public String getDiseaseImg() 
    {
        return diseaseImg;
    }
    public void setMaintainSite(String maintainSite) 
    {
        this.maintainSite = maintainSite;
    }

    public String getMaintainSite() 
    {
        return maintainSite;
    }
    public void setMaintainCase(String maintainCase) 
    {
        this.maintainCase = maintainCase;
    }

    public String getMaintainCase() 
    {
        return maintainCase;
    }
    public void setMaintainPeople(String maintainPeople) 
    {
        this.maintainPeople = maintainPeople;
    }

    public String getMaintainPeople() 
    {
        return maintainPeople;
    }
    public void setMaintainFund(String maintainFund)
    {
        this.maintainFund = maintainFund;
    }

    public String getMaintainFund()
    {
        return maintainFund;
    }
    public void setBeginTime(Date beginTime) 
    {
        this.beginTime = beginTime;
    }

    public Date getBeginTime() 
    {
        return beginTime;
    }
    public void setOverTime(Date overTime) 
    {
        this.overTime = overTime;
    }

    public Date getOverTime() 
    {
        return overTime;
    }
    public void setMaintainRemark(String maintainRemark) 
    {
        this.maintainRemark = maintainRemark;
    }

    public String getMaintainRemark() 
    {
        return maintainRemark;
    }
    public void setMaintainImg(String maintainImg) 
    {
        this.maintainImg = maintainImg;
    }

    public String getMaintainImg() 
    {
        return maintainImg;
    }
    public void setMaintainUnit(String maintainUnit) 
    {
        this.maintainUnit = maintainUnit;
    }

    public String getMaintainUnit() 
    {
        return maintainUnit;
    }
    public void setDamageCase(String damageCase) 
    {
        this.damageCase = damageCase;
    }

    public String getDamageCase() 
    {
        return damageCase;
    }
    public void setHorizontalPositions(String horizontalPositions) 
    {
        this.horizontalPositions = horizontalPositions;
    }

    public String getHorizontalPositions() 
    {
        return horizontalPositions;
    }
    public void setForecastCase(String forecastCase) 
    {
        this.forecastCase = forecastCase;
    }

    public String getForecastCase() 
    {
        return forecastCase;
    }
    public void setDisposeMeasure(String disposeMeasure) 
    {
        this.disposeMeasure = disposeMeasure;
    }

    public String getDisposeMeasure() 
    {
        return disposeMeasure;
    }
    public void setDrivingDirection(String drivingDirection) 
    {
        this.drivingDirection = drivingDirection;
    }

    public String getDrivingDirection() 
    {
        return drivingDirection;
    }
    public void setDisposeStake(String disposeStake) 
    {
        this.disposeStake = disposeStake;
    }

    public String getDisposeStake() 
    {
        return disposeStake;
    }
    public void setDisposeRemark(String disposeRemark) 
    {
        this.disposeRemark = disposeRemark;
    }

    public String getDisposeRemark() 
    {
        return disposeRemark;
    }
    public void setDisposeTime(Date disposeTime) 
    {
        this.disposeTime = disposeTime;
    }

    public Date getDisposeTime() 
    {
        return disposeTime;
    }
    public void setDisposeImg(String disposeImg) 
    {
        this.disposeImg = disposeImg;
    }

    public String getDisposeImg() 
    {
        return disposeImg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("wdid", getWdid())
            .append("diseaseName", getDiseaseName())
            .append("pathCode", getPathCode())
            .append("pathName", getPathName())
            .append("sectionCode", getSectionCode())
            .append("sectionName", getSectionName())
            .append("reporter", getReporter())
            .append("reportDate", getReportDate())
            .append("reportType", getReportType())
            .append("statusid", getStatusid())
            .append("wherePath", getWherePath())
            .append("diseaseStake", getDiseaseStake())
            .append("diseaseLatitude", getDiseaseLatitude())
            .append("diseaseLongitude", getDiseaseLongitude())
            .append("diseaseMessage", getDiseaseMessage())
            .append("diseaseImg", getDiseaseImg())
            .append("maintainSite", getMaintainSite())
            .append("maintainCase", getMaintainCase())
            .append("maintainPeople", getMaintainPeople())
            .append("maintainFund", getMaintainFund())
            .append("beginTime", getBeginTime())
            .append("overTime", getOverTime())
            .append("maintainRemark", getMaintainRemark())
            .append("maintainImg", getMaintainImg())
            .append("maintainUnit", getMaintainUnit())
            .append("damageCase", getDamageCase())
            .append("horizontalPositions", getHorizontalPositions())
            .append("forecastCase", getForecastCase())
            .append("disposeMeasure", getDisposeMeasure())
            .append("drivingDirection", getDrivingDirection())
            .append("disposeStake", getDisposeStake())
            .append("disposeRemark", getDisposeRemark())
            .append("disposeTime", getDisposeTime())
            .append("disposeImg", getDisposeImg())
            .append("administrative", getAdministrative())
            .toString();
    }
}
