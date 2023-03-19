package com.hg.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hg.common.annotation.Excel;
import com.hg.common.core.domain.BaseEntity;

/**
 * 现代产业园对象 hw_modern_industrial_park_information
 *
 * @author ruoyi
 * @date 2023-03-14
 */
public class HwModernIndustrialParkInformation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 产业园ID */
    /** 产业园ID */
    private Long industrialParkId;

    /** 产业园名称 */
    @Excel(name = "产业园名称")
    private String nameOfIndustrialPark;

    /** 行政区域 */
    @Excel(name = "行政区域")
    private String administrativeRegion;

    /** 产业园经度 */
    @Excel(name = "产业园经度")
    private String longitudeOfIndustrialPark;

    /** 产业园纬度 */
    @Excel(name = "产业园纬度")
    private String latitudeOfIndustrialPark;

    /** 优先通达路线名称 */
    @Excel(name = "优先通达路线名称")
    private String nameOfPriorityRoute;

    /** 优先通达路线编码 */
    @Excel(name = "优先通达路线编码")
    private String priorityAccessRouteCode;

    /** 介绍 */
    @Excel(name = "介绍")
    private String introduce;

    public void setIndustrialParkId(Long industrialParkId)
    {
        this.industrialParkId = industrialParkId;
    }

    public Long getIndustrialParkId()
    {
        return industrialParkId;
    }
    public void setNameOfIndustrialPark(String nameOfIndustrialPark)
    {
        this.nameOfIndustrialPark = nameOfIndustrialPark;
    }

    public String getNameOfIndustrialPark()
    {
        return nameOfIndustrialPark;
    }
    public void setAdministrativeRegion(String administrativeRegion)
    {
        this.administrativeRegion = administrativeRegion;
    }

    public String getAdministrativeRegion()
    {
        return administrativeRegion;
    }
    public void setLongitudeOfIndustrialPark(String longitudeOfIndustrialPark)
    {
        this.longitudeOfIndustrialPark = longitudeOfIndustrialPark;
    }

    public String getLongitudeOfIndustrialPark()
    {
        return longitudeOfIndustrialPark;
    }
    public void setLatitudeOfIndustrialPark(String latitudeOfIndustrialPark)
    {
        this.latitudeOfIndustrialPark = latitudeOfIndustrialPark;
    }

    public String getLatitudeOfIndustrialPark()
    {
        return latitudeOfIndustrialPark;
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
            .append("industrialParkId", getIndustrialParkId())
            .append("nameOfIndustrialPark", getNameOfIndustrialPark())
            .append("administrativeRegion", getAdministrativeRegion())
            .append("longitudeOfIndustrialPark", getLongitudeOfIndustrialPark())
            .append("latitudeOfIndustrialPark", getLatitudeOfIndustrialPark())
            .append("nameOfPriorityRoute", getNameOfPriorityRoute())
            .append("priorityAccessRouteCode", getPriorityAccessRouteCode())
            .append("introduce", getIntroduce())
            .toString();
    }
}
