package com.hg.system.domain;

import com.hg.common.annotation.Excel;
import com.hg.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 精品示范对象 hw_maintain_plan_delicacy
 * 
 * @author ruoyi
 * @date 2023-03-08
 */
public class HwMaintainPlanDelicacy extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long mpdid;

    /** 行政区 */
    @Excel(name = "行政区")
    private String administrative;

    /** 路线名称 */
    @Excel(name = "路线名称")
    private String pathName;

    /** 所在乡镇 */
    @Excel(name = "所在乡镇")
    private String whereVillage;

    /** 路面类型id */
    @Excel(name = "路面类型id")
    private Long roadTypeid;

    /** 技术等级id */
    @Excel(name = "技术等级id")
    private Long technicalGradeid;

    /** 路面宽度 */
    @Excel(name = "路面宽度")
    private String roadWidth;

    /** 里程 */
    @Excel(name = "里程")
    private String mileage;

    /** 金额 */
    @Excel(name = "金额")
    private String amount;

    /** 审核状态id */
    @Excel(name = "审核状态id")
    private String auditStatusid;

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

    public void setMpdid(Long mpdid) 
    {
        this.mpdid = mpdid;
    }

    public Long getMpdid() 
    {
        return mpdid;
    }
    public void setAdministrative(String administrative) 
    {
        this.administrative = administrative;
    }

    public String getAdministrative() 
    {
        return administrative;
    }
    public void setPathName(String pathName) 
    {
        this.pathName = pathName;
    }

    public String getPathName() 
    {
        return pathName;
    }
    public void setWhereVillage(String whereVillage) 
    {
        this.whereVillage = whereVillage;
    }

    public String getWhereVillage() 
    {
        return whereVillage;
    }
    public void setRoadTypeid(Long roadTypeid) 
    {
        this.roadTypeid = roadTypeid;
    }

    public Long getRoadTypeid() 
    {
        return roadTypeid;
    }
    public void setTechnicalGradeid(Long technicalGradeid) 
    {
        this.technicalGradeid = technicalGradeid;
    }

    public Long getTechnicalGradeid() 
    {
        return technicalGradeid;
    }
    public void setRoadWidth(String roadWidth) 
    {
        this.roadWidth = roadWidth;
    }

    public String getRoadWidth() 
    {
        return roadWidth;
    }
    public void setMileage(String mileage) 
    {
        this.mileage = mileage;
    }

    public String getMileage() 
    {
        return mileage;
    }
    public void setAmount(String amount) 
    {
        this.amount = amount;
    }

    public String getAmount() 
    {
        return amount;
    }
    public void setAuditStatusid(String auditStatusid)
    {
        this.auditStatusid = auditStatusid;
    }

    public String getAuditStatusid()
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("mpdid", getMpdid())
            .append("administrative", getAdministrative())
            .append("pathName", getPathName())
            .append("whereVillage", getWhereVillage())
            .append("roadTypeid", getRoadTypeid())
            .append("technicalGradeid", getTechnicalGradeid())
            .append("roadWidth", getRoadWidth())
            .append("mileage", getMileage())
            .append("amount", getAmount())
            .append("auditStatusid", getAuditStatusid())
            .append("administrativeName", getAdministrativeName())
            .append("administrativeCode", getAdministrativeCode())
            .append("auditPeople", getAuditPeople())
            .append("time", getTime())
            .append("message", getMessage())
            .toString();
    }
}
