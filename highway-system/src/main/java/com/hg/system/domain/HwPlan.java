package com.hg.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hg.common.annotation.Excel;
import com.hg.common.core.domain.BaseEntity;

/**
 * 年度计划对象 hw_plan
 * 
 * @author Cxh
 * @date 2023-03-15
 */
public class HwPlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 计划id */
    private Long id;

    /** 计划名称 */
    @Excel(name = "计划名称")
    private String name;

    /** 计划类型     0 路网  1  危桥改造  2  生命安全防护  3  撤并建制村 */
    @Excel(name = "计划类型     0 路网  1  危桥改造  2  生命安全防护  3  撤并建制村")
    private Integer type;

    /** 计划归属     0  省级项目  1 市级项目 */
    @Excel(name = "计划归属     0  省级项目  1 市级项目")
    private Integer affiliation;

    /** 计划年度 */
    @Excel(name = "计划年度")
    private String planYear;

    /** 填报周期   0 每周  1  每月 */
    @Excel(name = "填报周期   0 每周  1  每月")
    private Integer fillPeriod;

    /** 批复角色 */
    @Excel(name = "批复角色")
    private String replyCharacter;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setAffiliation(Integer affiliation) 
    {
        this.affiliation = affiliation;
    }

    public Integer getAffiliation() 
    {
        return affiliation;
    }
    public void setPlanYear(String planYear) 
    {
        this.planYear = planYear;
    }

    public String getPlanYear() 
    {
        return planYear;
    }
    public void setFillPeriod(Integer fillPeriod) 
    {
        this.fillPeriod = fillPeriod;
    }

    public Integer getFillPeriod() 
    {
        return fillPeriod;
    }
    public void setReplyCharacter(String replyCharacter) 
    {
        this.replyCharacter = replyCharacter;
    }

    public String getReplyCharacter() 
    {
        return replyCharacter;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("type", getType())
            .append("affiliation", getAffiliation())
            .append("planYear", getPlanYear())
            .append("fillPeriod", getFillPeriod())
            .append("replyCharacter", getReplyCharacter())
            .toString();
    }
}
