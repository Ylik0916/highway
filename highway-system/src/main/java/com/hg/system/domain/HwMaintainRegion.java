package com.hg.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hg.common.annotation.Excel;
import com.hg.common.core.domain.BaseEntity;

/**
 * 养护单位关联行政区对象 hw_maintain_region
 * 
 * @author hg
 * @date 2023-03-14
 */
public class HwMaintainRegion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long mrid;

    /** 养护单位id */
    @Excel(name = "养护单位id")
    private Long muid;

    /** 行政区县id */
    @Excel(name = "行政区县id")
    private Long regionid;

    public void setMrid(Long mrid) 
    {
        this.mrid = mrid;
    }

    public Long getMrid() 
    {
        return mrid;
    }
    public void setMuid(Long muid) 
    {
        this.muid = muid;
    }

    public Long getMuid() 
    {
        return muid;
    }
    public void setRegionid(Long regionid) 
    {
        this.regionid = regionid;
    }

    public Long getRegionid() 
    {
        return regionid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("mrid", getMrid())
            .append("muid", getMuid())
            .append("regionid", getRegionid())
            .toString();
    }
}
