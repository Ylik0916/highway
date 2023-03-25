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

    /** 计划类型 */
    @Excel(name = "计划类型", dictType = "plan_type")
    private Integer type;

    /** 计划归属 */
    @Excel(name = "计划归属", dictType = "affiliation")
    private Integer affiliation;

    /** 计划年度 */
    @Excel(name = "计划年度", dictType = "plan_year")
    private String planYear;

    /** 填报周期 */
    @Excel(name = "填报周期", dictType = "fill_period")
    private Integer fillPeriod;

    /** 批复角色 */
    @Excel(name = "批复角色", dictType = "reply_character")
    private String replyCharacter;
    
    /** 项目个数 */
    @Excel(name = "项目个数")
    private String numberItems;

    /** 计划投资(万元) */
    @Excel(name = "计划投资(万元)")
    private String totalFunds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(Integer affiliation) {
        this.affiliation = affiliation;
    }

    public String getPlanYear() {
        return planYear;
    }

    public void setPlanYear(String planYear) {
        this.planYear = planYear;
    }

    public Integer getFillPeriod() {
        return fillPeriod;
    }

    public void setFillPeriod(Integer fillPeriod) {
        this.fillPeriod = fillPeriod;
    }

    public String getReplyCharacter() {
        return replyCharacter;
    }

    public void setReplyCharacter(String replyCharacter) {
        this.replyCharacter = replyCharacter;
    }

    public String getNumberItems() {
        return numberItems;
    }

    public void setNumberItems(String numberItems) {
        this.numberItems = numberItems;
    }

    public String getTotalFunds() {
        return totalFunds;
    }

    public void setTotalFunds(String totalFunds) {
        this.totalFunds = totalFunds;
    }

    @Override
    public String toString() {
        return "HwPlan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", affiliation=" + affiliation +
                ", planYear='" + planYear + '\'' +
                ", fillPeriod=" + fillPeriod +
                ", replyCharacter='" + replyCharacter + '\'' +
                ", numberItems='" + numberItems + '\'' +
                ", totalFunds='" + totalFunds + '\'' +
                '}';
    }
}
