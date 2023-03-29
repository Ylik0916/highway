package com.hg.system.mapper;

import java.util.List;
import com.hg.system.domain.HwRouteInformation;
import com.hg.system.domain.vo.RouteMileage;
import com.hg.system.domain.vo.RouterType;

/**
 * 路线列表Mapper接口
 * 
 * @author ruoyi
 * @date 2023-03-07
 */
public interface HwRouteInformationMapper 
{
    /**
     * 查询路线列表
     * 
     * @param id 路线列表主键
     * @return 路线列表
     */
    public HwRouteInformation selectHwRouteInformationById(Long id);

    /**
     * 查询路线列表列表
     * 
     * @param hwRouteInformation 路线列表
     * @return 路线列表集合
     */
    public List<HwRouteInformation> selectHwRouteInformationList(HwRouteInformation hwRouteInformation);

    /**
     * 新增路线列表
     * 
     * @param hwRouteInformation 路线列表
     * @return 结果
     */
    public int insertHwRouteInformation(HwRouteInformation hwRouteInformation);

    /**
     * 修改路线列表
     * 
     * @param hwRouteInformation 路线列表
     * @return 结果
     */
    public int updateHwRouteInformation(HwRouteInformation hwRouteInformation);

    /**
     * 删除路线列表
     * 
     * @param id 路线列表主键
     * @return 结果
     */
    public int deleteHwRouteInformationById(Long id);

    /**
     * 批量删除路线列表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHwRouteInformationByIds(Long[] ids);

    public List<RouterType> routeType();


    public List<RouterType> routeMileage();

}
