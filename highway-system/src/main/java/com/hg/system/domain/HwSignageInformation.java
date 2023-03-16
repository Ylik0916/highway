package com.hg.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hg.common.annotation.Excel;
import com.hg.common.core.domain.BaseEntity;

/**
 * 标识标牌对象 hw_signage_information
 *
 * @author ruoyi
 * @date 2023-03-14
 */
public class HwSignageInformation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 标识标牌ID */
    /** 标识标牌ID */
    private Long identificationTagId;

    /** 路线ID */
    @Excel(name = "路线ID")
    private Long routeid;

    /** 标识标牌名称 */
    @Excel(name = "标识标牌名称")
    private String labelName;

    /** 行政区域 */
    @Excel(name = "行政区域")
    private String administrativeRegion;


    /** 标识标牌经度 */
    @Excel(name = "标识标牌经度")
    private String markTheLongitudeSign;

    /** 标识标牌纬度 */
    @Excel(name = "标识标牌纬度")
    private String labelLatitude;

    /** 优先通达路线名称 */
    @Excel(name = "优先通达路线名称")
    private String nameOfPriorityRoute;

    /** 优先通达路线编码 */
    @Excel(name = "优先通达路线编码")
    private String priorityAccessRouteCode;

    /** 介绍 */
    @Excel(name = "介绍")
    private String introduce;

    public void setIdentificationTagId(Long identificationTagId)
    {
        this.identificationTagId = identificationTagId;
    }

    public Long getIdentificationTagId()
    {
        return identificationTagId;
    }
    public void setRouteid(Long routeid)
    {
        this.routeid = routeid;
    }

    public Long getRouteid()
    {
        return routeid;
    }
    public void setLabelName(String labelName)
    {
        this.labelName = labelName;
    }

    public String getLabelName()
    {
        return labelName;
    }
    public void setAdministrativeRegion(String administrativeRegion)
    {
        this.administrativeRegion = administrativeRegion;
    }

    public String getAdministrativeRegion()
    {
        return administrativeRegion;
    }

    public void setMarkTheLongitudeSign(String markTheLongitudeSign)
    {
        this.markTheLongitudeSign = markTheLongitudeSign;
    }

    public String getMarkTheLongitudeSign()
    {
        return markTheLongitudeSign;
    }
    public void setLabelLatitude(String labelLatitude)
    {
        this.labelLatitude = labelLatitude;
    }

    public String getLabelLatitude()
    {
        return labelLatitude;
    }
    public void setNameOfPriorityRoute(String nameOfPriorityRoute)
    {
        this.nameOfPriorityRoute = nameOfPriorityRoute;
    }

    public String getNameOfPriorityRoute()
    {
        return nameOfPriorityRoute;
    }
    public void setPriorityAccessRouteCode(String priorityAccessRouteCode)
    {
        this.priorityAccessRouteCode = priorityAccessRouteCode;
    }

    public String getPriorityAccessRouteCode()
    {
        return priorityAccessRouteCode;
    }
    public void setIntroduce(String introduce)
    {
        this.introduce = introduce;
    }

    public String getIntroduce()
    {
        return introduce;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("identificationTagId", getIdentificationTagId())
            .append("routeid", getRouteid())
            .append("labelName", getLabelName())
            .append("administrativeRegion", getAdministrativeRegion())
            .append("markTheLongitudeSign", getMarkTheLongitudeSign())
            .append("labelLatitude", getLabelLatitude())
            .append("nameOfPriorityRoute", getNameOfPriorityRoute())
            .append("priorityAccessRouteCode", getPriorityAccessRouteCode())
            .append("introduce", getIntroduce())
            .toString();
    }
}
