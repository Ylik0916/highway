package com.hg.system.service;

import com.hg.system.domain.HwRouteInformation;

import java.util.List;

/**
 * 路线列表Service接口
 *
 * @author ruoyi
 * @date 2023-03-07
 */
public interface IHwRouteInformationService
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
     * 批量删除路线列表
     *
     * @param ids 需要删除的路线列表主键集合
     * @return 结果
     */
    public int deleteHwRouteInformationByIds(Long[] ids);

    /**
     * 删除路线列表信息
     *
     * @param id 路线列表主键
     * @return 结果
     */
    public int deleteHwRouteInformationById(Long id);
}
