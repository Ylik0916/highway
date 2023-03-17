package com.hg.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hg.common.annotation.Excel;
import com.hg.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 动态养护管理对象 hw_maintain_dynamic
 * 
 * @author ruoyi
 * @date 2023-03-07
 */
public class HwMaintainDynamic extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long mdid;

    /** 行政区 */
    @Excel(name = "行政区")
    private String administrative;

    /** 行政编码 */
    @Excel(name = "行政编码")
    private String administrativeCode;

    /** 养护人 */
    @Excel(name = "养护人")
    private String maintainPeople;

    /** 养护时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "养护时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date maintainTime;

    /** 养护单位 */
    @Excel(name = "养护单位")
    private String maintainUnit;

    /** 路线id */
    @Excel(name = "路线id")
    private Long pathid;

    /** 路线名称 */
    @Excel(name = "路线名称")
    private String pathName;

    /** 路线编号 */
    @Excel(name = "路线编号")
    private String pathCode;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String licensePlate;

    /** 开始桩号 */
    @Excel(name = "开始桩号")
    private String beginStake;

    /** 结束时桩号 */
    @Excel(name = "结束时桩号")
    private String overStake;

    /** 养护内容 */
    @Excel(name = "养护内容")
    private String maintainContent;

    /** 养护附件 */
    @Excel(name = "养护附件")
    private String maintainAccessory;

    public void setMdid(Long mdid) 
    {
        this.mdid = mdid;
    }

    public Long getMdid() 
    {
        return mdid;
    }
    public void setAdministrative(String administrative) 
    {
        this.administrative = administrative;
    }

    public String getAdministrative() 
    {
        return administrative;
    }
    public void setAdministrativeCode(String administrativeCode) 
    {
        this.administrativeCode = administrativeCode;
    }

    public String getAdministrativeCode() 
    {
        return administrativeCode;
    }
    public void setMaintainPeople(String maintainPeople) 
    {
        this.maintainPeople = maintainPeople;
    }

    public String getMaintainPeople() 
    {
        return maintainPeople;
    }
    public void setMaintainTime(Date maintainTime) 
    {
        this.maintainTime = maintainTime;
    }

    public Date getMaintainTime() 
    {
        return maintainTime;
    }
    public void setMaintainUnit(String maintainUnit) 
    {
        this.maintainUnit = maintainUnit;
    }

    public String getMaintainUnit() 
    {
        return maintainUnit;
    }
    public void setPathid(Long pathid) 
    {
        this.pathid = pathid;
    }

    public Long getPathid() 
    {
        return pathid;
    }
    public void setPathName(String pathName) 
    {
        this.pathName = pathName;
    }

    public String getPathName() 
    {
        return pathName;
    }
    public void setPathCode(String pathCode) 
    {
        this.pathCode = pathCode;
    }

    public String getPathCode() 
    {
        return pathCode;
    }
    public void setLicensePlate(String licensePlate) 
    {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() 
    {
        return licensePlate;
    }
    public void setBeginStake(String beginStake) 
    {
        this.beginStake = beginStake;
    }

    public String getBeginStake() 
    {
        return beginStake;
    }
    public void setOverStake(String overStake) 
    {
        this.overStake = overStake;
    }

    public String getOverStake() 
    {
        return overStake;
    }
    public void setMaintainContent(String maintainContent) 
    {
        this.maintainContent = maintainContent;
    }

    public String getMaintainContent() 
    {
        return maintainContent;
    }
    public void setMaintainAccessory(String maintainAccessory) 
    {
        this.maintainAccessory = maintainAccessory;
    }

    public String getMaintainAccessory() 
    {
        return maintainAccessory;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("mdid", getMdid())
            .append("administrative", getAdministrative())
            .append("administrativeCode", getAdministrativeCode())
            .append("maintainPeople", getMaintainPeople())
            .append("maintainTime", getMaintainTime())
            .append("maintainUnit", getMaintainUnit())
            .append("pathid", getPathid())
            .append("pathName", getPathName())
            .append("pathCode", getPathCode())
            .append("licensePlate", getLicensePlate())
            .append("beginStake", getBeginStake())
            .append("overStake", getOverStake())
            .append("maintainContent", getMaintainContent())
            .append("maintainAccessory", getMaintainAccessory())
            .toString();
    }
}
