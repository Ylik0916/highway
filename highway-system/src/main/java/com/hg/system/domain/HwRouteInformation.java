package com.hg.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hg.common.annotation.Excel;
import com.hg.common.core.domain.BaseEntity;

/**
 * 路线列表对象 hw_route_information
 * 
 * @author ruoyi
 * @date 2023-03-07
 */
public class HwRouteInformation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 路线信息id */
    private Long id;

    /** 路线名称 */
    @Excel(name = "路线名称")
    private String routeName;

    /** 路线编码 */
    @Excel(name = "路线编码")
    private String routeCoding;

    /** 行政区域 */
    @Excel(name = "行政区域")
    private String adminiStrative;

    /** 建设类型 */
    @Excel(name = "建设类型")
    private String typeConstruction;

    /** 路线里程 */
    @Excel(name = "路线里程")
    private String routeMileage;

    /** 路线类型 */
    @Excel(name = "路线类型")
    private String routeType;

    /** 起点名称 */
    @Excel(name = "起点名称")
    private String startingPointname;

    /** 终点名称 */
    @Excel(name = "终点名称")
    private String endPointName;

    /** 起点经度 */
    private String startingPointLongitude;

    /** 终点经度 */
    private String endPointLongitude;

    /** 起点纬度 */
    @Excel(name = "起点纬度")
    private String startLatitude;

    /** 终点纬度 */
    @Excel(name = "终点纬度")
    private String endLatitude;

    /** 起点桩号 */
    @Excel(name = "起点桩号")
    private String startingPointStation;

    /** 终点桩号 */
    @Excel(name = "终点桩号")
    private String endPointStation;

    /** 路段数量 */
    @Excel(name = "路段数量")
    private String numberRoadSegments;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRouteName(String routeName) 
    {
        this.routeName = routeName;
    }

    public String getRouteName() 
    {
        return routeName;
    }
    public void setRouteCoding(String routeCoding) 
    {
        this.routeCoding = routeCoding;
    }

    public String getRouteCoding() 
    {
        return routeCoding;
    }
    public void setAdminiStrative(String adminiStrative) 
    {
        this.adminiStrative = adminiStrative;
    }

    public String getAdminiStrative() 
    {
        return adminiStrative;
    }
    public void setTypeConstruction(String typeConstruction) 
    {
        this.typeConstruction = typeConstruction;
    }

    public String getTypeConstruction() 
    {
        return typeConstruction;
    }
    public void setRouteMileage(String routeMileage) 
    {
        this.routeMileage = routeMileage;
    }

    public String getRouteMileage() 
    {
        return routeMileage;
    }
    public void setRouteType(String routeType) 
    {
        this.routeType = routeType;
    }

    public String getRouteType() 
    {
        return routeType;
    }
    public void setStartingPointname(String startingPointname) 
    {
        this.startingPointname = startingPointname;
    }

    public String getStartingPointname() 
    {
        return startingPointname;
    }
    public void setEndPointName(String endPointName) 
    {
        this.endPointName = endPointName;
    }

    public String getEndPointName() 
    {
        return endPointName;
    }
    public void setStartingPointLongitude(String startingPointLongitude) 
    {
        this.startingPointLongitude = startingPointLongitude;
    }

    public String getStartingPointLongitude() 
    {
        return startingPointLongitude;
    }
    public void setEndPointLongitude(String endPointLongitude) 
    {
        this.endPointLongitude = endPointLongitude;
    }

    public String getEndPointLongitude() 
    {
        return endPointLongitude;
    }
    public void setStartLatitude(String startLatitude) 
    {
        this.startLatitude = startLatitude;
    }

    public String getStartLatitude() 
    {
        return startLatitude;
    }
    public void setEndLatitude(String endLatitude) 
    {
        this.endLatitude = endLatitude;
    }

    public String getEndLatitude() 
    {
        return endLatitude;
    }
    public void setStartingPointStation(String startingPointStation) 
    {
        this.startingPointStation = startingPointStation;
    }

    public String getStartingPointStation() 
    {
        return startingPointStation;
    }
    public void setEndPointStation(String endPointStation) 
    {
        this.endPointStation = endPointStation;
    }

    public String getEndPointStation() 
    {
        return endPointStation;
    }
    public void setNumberRoadSegments(String numberRoadSegments) 
    {
        this.numberRoadSegments = numberRoadSegments;
    }

    public String getNumberRoadSegments() 
    {
        return numberRoadSegments;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("routeName", getRouteName())
            .append("routeCoding", getRouteCoding())
            .append("adminiStrative", getAdminiStrative())
            .append("typeConstruction", getTypeConstruction())
            .append("routeMileage", getRouteMileage())
            .append("routeType", getRouteType())
            .append("startingPointname", getStartingPointname())
            .append("endPointName", getEndPointName())
            .append("startingPointLongitude", getStartingPointLongitude())
            .append("endPointLongitude", getEndPointLongitude())
            .append("startLatitude", getStartLatitude())
            .append("endLatitude", getEndLatitude())
            .append("startingPointStation", getStartingPointStation())
            .append("endPointStation", getEndPointStation())
            .append("numberRoadSegments", getNumberRoadSegments())
            .toString();
    }
}
