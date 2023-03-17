package com.hg.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hg.system.mapper.HwBridgeQualityMapper;
import com.hg.system.domain.HwBridgeQuality;
import com.hg.system.service.IHwBridgeQualityService;

/**
 * 桥梁质量Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-01
 */
@Service
public class HwBridgeQualityServiceImpl implements IHwBridgeQualityService 
{
    @Autowired
    private HwBridgeQualityMapper hwBridgeQualityMapper;

    /**
     * 查询桥梁质量
     * 
     * @param routeQualityId 桥梁质量主键
     * @return 桥梁质量
     */
    @Override
    public HwBridgeQuality selectHwBridgeQualityByRouteQualityId(Long routeQualityId)
    {
        return hwBridgeQualityMapper.selectHwBridgeQualityByRouteQualityId(routeQualityId);
    }

    /**
     * 查询桥梁质量列表
     * 
     * @param hwBridgeQuality 桥梁质量
     * @return 桥梁质量
     */
    @Override
    public List<HwBridgeQuality> selectHwBridgeQualityList(HwBridgeQuality hwBridgeQuality)
    {
        return hwBridgeQualityMapper.selectHwBridgeQualityList(hwBridgeQuality);
    }

    /**
     * 新增桥梁质量
     * 
     * @param hwBridgeQuality 桥梁质量
     * @return 结果
     */
    @Override
    public int insertHwBridgeQuality(HwBridgeQuality hwBridgeQuality)
    {
        return hwBridgeQualityMapper.insertHwBridgeQuality(hwBridgeQuality);
    }

    /**
     * 修改桥梁质量
     * 
     * @param hwBridgeQuality 桥梁质量
     * @return 结果
     */
    @Override
    public int updateHwBridgeQuality(HwBridgeQuality hwBridgeQuality)
    {
        return hwBridgeQualityMapper.updateHwBridgeQuality(hwBridgeQuality);
    }

    /**
     * 批量删除桥梁质量
     * 
     * @param routeQualityIds 需要删除的桥梁质量主键
     * @return 结果
     */
    @Override
    public int deleteHwBridgeQualityByRouteQualityIds(Long[] routeQualityIds)
    {
        return hwBridgeQualityMapper.deleteHwBridgeQualityByRouteQualityIds(routeQualityIds);
    }

    /**
     * 删除桥梁质量信息
     * 
     * @param routeQualityId 桥梁质量主键
     * @return 结果
     */
    @Override
    public int deleteHwBridgeQualityByRouteQualityId(Long routeQualityId)
    {
        return hwBridgeQualityMapper.deleteHwBridgeQualityByRouteQualityId(routeQualityId);
    }
}
