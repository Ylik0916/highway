package com.hg.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hg.common.annotation.Excel;
import com.hg.common.core.domain.BaseEntity;

/**
 * 从业人员评价对象 hw_person_evaluate
 * 
 * @author W-yf
 * @date 2023-03-07
 */
public class HwPersonEvaluate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 人员id */
    private Long personId;

    /** 从业人员名称 */
    @Excel(name = "从业人员名称")
    private String personName;

    /** 所属单位id */
    @Excel(name = "所属单位id")
    private Long personUnit;

    /** 单位类型 */
    @Excel(name = "单位类型")
    private String unitType;

    /** 评价年度 */
    @Excel(name = "评价年度")
    private String evaluateYears;

    public void setPersonId(Long personId) 
    {
        this.personId = personId;
    }

    public Long getPersonId() 
    {
        return personId;
    }
    public void setPersonName(String personName) 
    {
        this.personName = personName;
    }

    public String getPersonName() 
    {
        return personName;
    }
    public void setPersonUnit(Long personUnit) 
    {
        this.personUnit = personUnit;
    }

    public Long getPersonUnit() 
    {
        return personUnit;
    }
    public void setUnitType(String unitType) 
    {
        this.unitType = unitType;
    }

    public String getUnitType() 
    {
        return unitType;
    }
    public void setEvaluateYears(String evaluateYears) 
    {
        this.evaluateYears = evaluateYears;
    }

    public String getEvaluateYears() 
    {
        return evaluateYears;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("personId", getPersonId())
            .append("personName", getPersonName())
            .append("personUnit", getPersonUnit())
            .append("unitType", getUnitType())
            .append("evaluateYears", getEvaluateYears())
            .toString();
    }
}
