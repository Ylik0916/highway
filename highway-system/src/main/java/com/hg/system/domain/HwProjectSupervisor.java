package com.hg.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hg.common.annotation.Excel;
import com.hg.common.core.domain.BaseEntity;

/**
 * 项目信息管理==》监理标段对象 hw_project_supervisor
 * 
 * @author W-yf
 * @date 2023-03-14
 */
public class HwProjectSupervisor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 监理标段id */
    private Long supervisorId;

    /** 监理标段名称 */
    @Excel(name = "监理标段名称")
    private String supervisorName;

    /** 关联==》项目信息管理表==》监理标id */
    @Excel(name = "关联==》项目信息管理表==》监理标id")
    private Long supervisorProjectId;

    public void setSupervisorId(Long supervisorId) 
    {
        this.supervisorId = supervisorId;
    }

    public Long getSupervisorId() 
    {
        return supervisorId;
    }
    public void setSupervisorName(String supervisorName) 
    {
        this.supervisorName = supervisorName;
    }

    public String getSupervisorName() 
    {
        return supervisorName;
    }
    public void setSupervisorProjectId(Long supervisorProjectId) 
    {
        this.supervisorProjectId = supervisorProjectId;
    }

    public Long getSupervisorProjectId() 
    {
        return supervisorProjectId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("supervisorId", getSupervisorId())
            .append("supervisorName", getSupervisorName())
            .append("supervisorProjectId", getSupervisorProjectId())
            .toString();
    }
}
