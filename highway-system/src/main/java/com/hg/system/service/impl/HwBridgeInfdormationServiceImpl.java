package com.hg.system.service.impl;

import java.util.List;
import com.hg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hg.system.mapper.HwBridgeInfdormationMapper;
import com.hg.system.domain.HwBridgeInfdormation;
import com.hg.system.service.IHwBridgeInfdormationService;

/**
 * 桥梁信息Service业务层处理
 *
 * @author ruoyi
 * @date 2023-03-14
 */
@Service
public class HwBridgeInfdormationServiceImpl implements IHwBridgeInfdormationService
{
    @Autowired
    private HwBridgeInfdormationMapper hwBridgeInfdormationMapper;

    /**
     * 查询桥梁信息
     *
     * @param routeId 桥梁信息主键
     * @return 桥梁信息
     */
    @Override
    public HwBridgeInfdormation selectHwBridgeInfdormationByRouteId(Long routeId)
    {
        return hwBridgeInfdormationMapper.selectHwBridgeInfdormationByRouteId(routeId);
    }

    /**
     * 查询桥梁信息列表
     *
     * @param hwBridgeInfdormation 桥梁信息
     * @return 桥梁信息
     */
    @Override
    public List<HwBridgeInfdormation> selectHwBridgeInfdormationList(HwBridgeInfdormation hwBridgeInfdormation)
    {
        return hwBridgeInfdormationMapper.selectHwBridgeInfdormationList(hwBridgeInfdormation);
    }

    /**
     * 新增桥梁信息
     *
     * @param hwBridgeInfdormation 桥梁信息
     * @return 结果
     */
    @Override
    public int insertHwBridgeInfdormation(HwBridgeInfdormation hwBridgeInfdormation)
    {
        return hwBridgeInfdormationMapper.insertHwBridgeInfdormation(hwBridgeInfdormation);
    }

    /**
     * 修改桥梁信息
     *
     * @param hwBridgeInfdormation 桥梁信息
     * @return 结果
     */
    @Override
    public int updateHwBridgeInfdormation(HwBridgeInfdormation hwBridgeInfdormation)
    {
        hwBridgeInfdormation.setUpdateTime(DateUtils.getNowDate());
        return hwBridgeInfdormationMapper.updateHwBridgeInfdormation(hwBridgeInfdormation);
    }

    /**
     * 批量删除桥梁信息
     *
     * @param routeIds 需要删除的桥梁信息主键
     * @return 结果
     */
    @Override
    public int deleteHwBridgeInfdormationByRouteIds(Long[] routeIds)
    {
        return hwBridgeInfdormationMapper.deleteHwBridgeInfdormationByRouteIds(routeIds);
    }

    /**
     * 删除桥梁信息信息
     *
     * @param routeId 桥梁信息主键
     * @return 结果
     */
    @Override
    public int deleteHwBridgeInfdormationByRouteId(Long routeId)
    {
        return hwBridgeInfdormationMapper.deleteHwBridgeInfdormationByRouteId(routeId);
    }
}
