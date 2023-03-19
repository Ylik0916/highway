package com.hg.system.service;

import java.util.List;
import com.hg.system.domain.HwBridgeQuality;
import org.apache.ibatis.annotations.Select;

/**
 * 桥梁质量Service接口
 * 
 * @author ruoyi
 * @date 2023-03-01
 */
public interface IHwBridgeQualityService 
{
    /**
     * 查询桥梁质量
     * 
     * @param routeQualityId 桥梁质量主键
     * @return 桥梁质量
     */
    public HwBridgeQuality selectHwBridgeQualityByRouteQualityId(Long routeQualityId);

    /**
     * 查询桥梁质量列表
     * 
     * @param hwBridgeQuality 桥梁质量
     * @return 桥梁质量集合
     */
    public List<HwBridgeQuality> selectHwBridgeQualityList(HwBridgeQuality hwBridgeQuality);

    /**
     * 新增桥梁质量
     * 
     * @param hwBridgeQuality 桥梁质量
     * @return 结果
     */
    public int insertHwBridgeQuality(HwBridgeQuality hwBridgeQuality);

    /**
     * 修改桥梁质量
     * 
     * @param hwBridgeQuality 桥梁质量
     * @return 结果
     */
    public int updateHwBridgeQuality(HwBridgeQuality hwBridgeQuality);

    /**
     * 批量删除桥梁质量
     * 
     * @param routeQualityIds 需要删除的桥梁质量主键集合
     * @return 结果
     */
    public int deleteHwBridgeQualityByRouteQualityIds(Long[] routeQualityIds);

    /**
     * 删除桥梁质量信息
     * 
     * @param routeQualityId 桥梁质量主键
     * @return 结果
     */
    public int deleteHwBridgeQualityByRouteQualityId(Long routeQualityId);

    /**
     * 桥梁质量优
     *
     * @return 结果
     */
    public int you();
    public int hao();
    public int zhong();

}
