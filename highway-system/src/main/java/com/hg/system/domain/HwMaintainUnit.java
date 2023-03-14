package com.hg.system.domain;

import com.hg.common.annotation.Excel;
import com.hg.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;


/**
 * 养护单位管理对象 hw_maintain_unit
 * 
 * @author ruoyi
 * @date 2023-03-07
 */
public class HwMaintainUnit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long muid;

    /** 单位编码 */
    @Excel(name = "单位编码")
    private String unitCode;

    /** 单位全称 */
    @Excel(name = "单位全称")
    private String unitDesignation;

    /** 单位名称 */
    @Excel(name = "单位名称")
    private String unitName;

    /** 登录名 */
    @Excel(name = "登录名")
    private String loginName;

    /** 单位地址 */
    @Excel(name = "单位地址")
    private String unitSite;

    /** 单位电话 */
    @Excel(name = "单位电话")
    private String uniyPhone;

    private List<Integer> administrative;

    public void setMuid(Long muid) 
    {
        this.muid = muid;
    }

    public Long getMuid() 
    {
        return muid;
    }
    public void setUnitCode(String unitCode) 
    {
        this.unitCode = unitCode;
    }

    public String getUnitCode() 
    {
        return unitCode;
    }
    public void setUnitDesignation(String unitDesignation) 
    {
        this.unitDesignation = unitDesignation;
    }

    public String getUnitDesignation() 
    {
        return unitDesignation;
    }
    public void setUnitName(String unitName) 
    {
        this.unitName = unitName;
    }

    public String getUnitName() 
    {
        return unitName;
    }
    public void setLoginName(String loginName) 
    {
        this.loginName = loginName;
    }

    public String getLoginName() 
    {
        return loginName;
    }
    public void setUnitSite(String unitSite) 
    {
        this.unitSite = unitSite;
    }

    public String getUnitSite() 
    {
        return unitSite;
    }
    public void setUniyPhone(String uniyPhone)
    {
        this.uniyPhone = uniyPhone;
    }

    public String getUniyPhone()
    {
        return uniyPhone;
    }

    public List<Integer> getAdministrative() {
        return administrative;
    }

    public void setAdministrative(List<Integer> administrative) {
        this.administrative = administrative;
    }

    @Override
    public String toString() {
        return "HwMaintainUnit{" +
                "muid=" + muid +
                ", unitCode='" + unitCode + '\'' +
                ", unitDesignation='" + unitDesignation + '\'' +
                ", unitName='" + unitName + '\'' +
                ", loginName='" + loginName + '\'' +
                ", unitSite='" + unitSite + '\'' +
                ", uniyPhone='" + uniyPhone + '\'' +
                ", administrative=" + administrative +
                '}';
    }
}
