package com.hg.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hg.common.annotation.Excel;
import com.hg.common.core.domain.BaseEntity;

/**
 * W-yf对象 hw_project_construction
 * 
 * @author W-yf
 * @date 2023-03-15
 */
public class HwProjectConstruction extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 施工标段id */
    private Long constructionId;

    /** 施工标段名称 */
    @Excel(name = "施工标段名称")
    private String constructionName;

    /** 关联==》项目信息管理表==》施工标id */
    @Excel(name = "关联==》项目信息管理表==》施工标id")
    private Long constructionProjectId;

    /** 监理标段名称 */
    @Excel(name = "监理标段名称")
    private Long constructionSupervisorId;

    public void setConstructionId(Long constructionId) 
    {
        this.constructionId = constructionId;
    }

    public Long getConstructionId() 
    {
        return constructionId;
    }
    public void setConstructionName(String constructionName) 
    {
        this.constructionName = constructionName;
    }

    public String getConstructionName() 
    {
        return constructionName;
    }
    public void setConstructionProjectId(Long constructionProjectId) 
    {
        this.constructionProjectId = constructionProjectId;
    }

    public Long getConstructionProjectId() 
    {
        return constructionProjectId;
    }
    public void setConstructionSupervisorId(Long constructionSupervisorId) 
    {
        this.constructionSupervisorId = constructionSupervisorId;
    }

    public Long getConstructionSupervisorId() 
    {
        return constructionSupervisorId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("constructionId", getConstructionId())
            .append("constructionName", getConstructionName())
            .append("constructionProjectId", getConstructionProjectId())
            .append("constructionSupervisorId", getConstructionSupervisorId())
            .toString();
    }
}
