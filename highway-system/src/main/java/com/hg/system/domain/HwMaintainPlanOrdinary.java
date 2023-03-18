package com.hg.system.domain;

import com.hg.common.annotation.Excel;
import com.hg.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 一般养护对象 hw_maintain_plan_ordinary
 * 
 * @author ruoyi
 * @date 2023-03-08
 */
public class HwMaintainPlanOrdinary extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long mpoid;

    /** 行政区 */
    @Excel(name = "行政区")
    private String administrative;

    /** 路线编码 */
    @Excel(name = "路线编码")
    private String pathCode;

    /** 村道名称 */
    @Excel(name = "村道名称")
    private String villageName;

    /** 养护里程 */
    @Excel(name = "养护里程")
    private String maintainMileage;

    /** 沥青路面、水泥混凝土路面、砖路面(km) */
    @Excel(name = "沥青路面、水泥混凝土路面、砖路面(km)")
    private String pitchRoad;

    /** 砂砾路面 */
    @Excel(name = "砂砾路面")
    private String gravelRoad;

    /** 总养护资金(万元) */
    @Excel(name = "总养护资金(万元)")
    private String allFund;

    /** 市养护资金(万元) */
    @Excel(name = "市养护资金(万元)")
    private String cityFund;

    /** 县养护资金(万元) */
    @Excel(name = "县养护资金(万元)")
    private String countyFund;

    /** 审核状态id */
    @Excel(name = "审核状态id")
    private Long auditStatusid;

    /** 审核人 */
    @Excel(name = "审核人")
    private String auditPeople;

    /** 审核时间 */
    @Excel(name = "审核时间")
    private Date time;

    /** 审核意见 */
    @Excel(name = "审核意见")
    private String message;

    /** 行政区名 */
    private String administrativeName;

    /** 行政区编码 */
    private String administrativeCode;

    public String getAdministrativeName() {
        return administrativeName;
    }

    public void setAdministrativeName(String administrativeName) {
        this.administrativeName = administrativeName;
    }

    public String getAdministrativeCode() {
        return administrativeCode;
    }

    public void setAdministrativeCode(String administrativeCode) {
        this.administrativeCode = administrativeCode;
    }

    public void setMpoid(Long mpoid)
    {
        this.mpoid = mpoid;
    }

    public Long getMpoid() 
    {
        return mpoid;
    }
    public void setAdministrative(String administrative) 
    {
        this.administrative = administrative;
    }

    public String getAdministrative() 
    {
        return administrative;
    }
    public void setPathCode(String pathCode) 
    {
        this.pathCode = pathCode;
    }

    public String getPathCode() 
    {
        return pathCode;
    }
    public void setVillageName(String villageName) 
    {
        this.villageName = villageName;
    }

    public String getVillageName() 
    {
        return villageName;
    }
    public void setMaintainMileage(String maintainMileage) 
    {
        this.maintainMileage = maintainMileage;
    }

    public String getMaintainMileage() 
    {
        return maintainMileage;
    }
    public void setPitchRoad(String pitchRoad) 
    {
        this.pitchRoad = pitchRoad;
    }

    public String getPitchRoad() 
    {
        return pitchRoad;
    }
    public void setGravelRoad(String gravelRoad) 
    {
        this.gravelRoad = gravelRoad;
    }

    public String getGravelRoad() 
    {
        return gravelRoad;
    }
    public void setAllFund(String allFund) 
    {
        this.allFund = allFund;
    }

    public String getAllFund() 
    {
        return allFund;
    }
    public void setCityFund(String cityFund) 
    {
        this.cityFund = cityFund;
    }

    public String getCityFund() 
    {
        return cityFund;
    }
    public void setCountyFund(String countyFund) 
    {
        this.countyFund = countyFund;
    }

    public String getCountyFund() 
    {
        return countyFund;
    }
    public void setAuditStatusid(Long auditStatusid) 
    {
        this.auditStatusid = auditStatusid;
    }

    public Long getAuditStatusid() 
    {
        return auditStatusid;
    }

    public String getAuditPeople() {
        return auditPeople;
    }

    public void setAuditPeople(String auditPeople) {
        this.auditPeople = auditPeople;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("mpoid", getMpoid())
            .append("administrative", getAdministrative())
            .append("pathCode", getPathCode())
            .append("villageName", getVillageName())
            .append("maintainMileage", getMaintainMileage())
            .append("pitchRoad", getPitchRoad())
            .append("gravelRoad", getGravelRoad())
            .append("allFund", getAllFund())
            .append("cityFund", getCityFund())
            .append("countyFund", getCountyFund())
            .append("auditStatusid", getAuditStatusid())
            .append("administrativeName", getAdministrativeName())
            .append("administrativeCode", getAdministrativeCode())
            .append("auditPeople", getAuditPeople())
            .append("time", getTime())
            .append("message", getMessage())
            .toString();
    }
}
