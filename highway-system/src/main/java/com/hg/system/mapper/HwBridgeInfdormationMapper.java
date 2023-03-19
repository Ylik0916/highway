package com.hg.system.mapper;

import java.util.List;
import com.hg.system.domain.HwBridgeInfdormation;

/**
 * 桥梁信息Mapper接口
 *
 * @author ruoyi
 * @date 2023-03-14
 */
public interface HwBridgeInfdormationMapper
{
    /**
     * 查询桥梁信息
     *
     * @param routeId 桥梁信息主键
     * @return 桥梁信息
     */
    public HwBridgeInfdormation selectHwBridgeInfdormationByRouteId(Long routeId);

    /**
     * 查询桥梁信息列表
     *
     * @param hwBridgeInfdormation 桥梁信息
     * @return 桥梁信息集合
     */
    public List<HwBridgeInfdormation> selectHwBridgeInfdormationList(HwBridgeInfdormation hwBridgeInfdormation);

    /**
     * 新增桥梁信息
     *
     * @param hwBridgeInfdormation 桥梁信息
     * @return 结果
     */
    public int insertHwBridgeInfdormation(HwBridgeInfdormation hwBridgeInfdormation);

    /**
     * 修改桥梁信息
     *
     * @param hwBridgeInfdormation 桥梁信息
     * @return 结果
     */
    public int updateHwBridgeInfdormation(HwBridgeInfdormation hwBridgeInfdormation);

    /**
     * 删除桥梁信息
     *
     * @param routeId 桥梁信息主键
     * @return 结果
     */
    public int deleteHwBridgeInfdormationByRouteId(Long routeId);

    /**
     * 批量删除桥梁信息
     *
     * @param routeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHwBridgeInfdormationByRouteIds(Long[] routeIds);
}
