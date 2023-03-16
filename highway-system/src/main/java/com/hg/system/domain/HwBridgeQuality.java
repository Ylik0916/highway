package com.hg.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hg.common.annotation.Excel;
import com.hg.common.core.domain.BaseEntity;

/**
 * 桥梁质量对象 hw_bridge_quality
 * 
 * @author ruoyi
 * @date 2023-03-01
 */
public class HwBridgeQuality extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 桥梁质量id */
    private Long routeQualityId;

    /** 上部结构评分SPCI */
    @Excel(name = "上部结构评分SPCI")
    private Long routeTopScore;

    /** 下部结构评分SBCI */
    @Excel(name = "下部结构评分SBCI")
    private Long routeBottomScore;

    /** 桥面系评分BDCI */
    @Excel(name = "桥面系评分BDCI")
    private Long routeFaceScore;

    /** 评定时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "评定时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date routeScoreDate;

    /** 评定单位 */
    @Excel(name = "评定单位")
    private String routeCompany;

    /** 桥梁名称 */
    @Excel(name = "桥梁名称")
    private String routeName;

    /** 桥梁编码 */
    @Excel(name = "桥梁编码")
    private String routeCode;

    /** 行政区划 */
    @Excel(name = "行政区划")
    private String routeAdministrativeArea;

    /** 技术状况得分DR */
    @Excel(name = "技术状况得分DR")
    private Long routeDr;

    public void setRouteQualityId(Long routeQualityId) 
    {
        this.routeQualityId = routeQualityId;
    }

    public Long getRouteQualityId() 
    {
        return routeQualityId;
    }
    public void setRouteTopScore(Long routeTopScore) 
    {
        this.routeTopScore = routeTopScore;
    }

    public Long getRouteTopScore() 
    {
        return routeTopScore;
    }
    public void setRouteBottomScore(Long routeBottomScore) 
    {
        this.routeBottomScore = routeBottomScore;
    }

    public Long getRouteBottomScore() 
    {
        return routeBottomScore;
    }
    public void setRouteFaceScore(Long routeFaceScore) 
    {
        this.routeFaceScore = routeFaceScore;
    }

    public Long getRouteFaceScore() 
    {
        return routeFaceScore;
    }
    public void setRouteScoreDate(Date routeScoreDate) 
    {
        this.routeScoreDate = routeScoreDate;
    }

    public Date getRouteScoreDate() 
    {
        return routeScoreDate;
    }
    public void setRouteCompany(String routeCompany) 
    {
        this.routeCompany = routeCompany;
    }

    public String getRouteCompany() 
    {
        return routeCompany;
    }
    public void setRouteName(String routeName) 
    {
        this.routeName = routeName;
    }

    public String getRouteName() 
    {
        return routeName;
    }
    public void setRouteCode(String routeCode) 
    {
        this.routeCode = routeCode;
    }

    public String getRouteCode() 
    {
        return routeCode;
    }
    public void setRouteAdministrativeArea(String routeAdministrativeArea) 
    {
        this.routeAdministrativeArea = routeAdministrativeArea;
    }

    public String getRouteAdministrativeArea() 
    {
        return routeAdministrativeArea;
    }
    public void setRouteDr(Long routeDr) 
    {
        this.routeDr = routeDr;
    }

    public Long getRouteDr() 
    {
        return routeDr;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("routeQualityId", getRouteQualityId())
            .append("routeTopScore", getRouteTopScore())
            .append("routeBottomScore", getRouteBottomScore())
            .append("routeFaceScore", getRouteFaceScore())
            .append("routeScoreDate", getRouteScoreDate())
            .append("routeCompany", getRouteCompany())
            .append("routeName", getRouteName())
            .append("routeCode", getRouteCode())
            .append("routeAdministrativeArea", getRouteAdministrativeArea())
            .append("routeDr", getRouteDr())
            .toString();
    }
}
