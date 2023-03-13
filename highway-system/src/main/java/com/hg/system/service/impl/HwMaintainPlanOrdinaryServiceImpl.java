package com.hg.system.service.impl;

import java.util.List;

import com.hg.system.domain.HwMaintainPlanOrdinary;
import com.hg.system.mapper.HwMaintainPlanOrdinaryMapper;
import com.hg.system.service.IHwMaintainPlanOrdinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 一般养护Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-08
 */
@Service
public class HwMaintainPlanOrdinaryServiceImpl implements IHwMaintainPlanOrdinaryService
{
    @Autowired
    private HwMaintainPlanOrdinaryMapper hwMaintainPlanOrdinaryMapper;

    /**
     * 查询一般养护
     * 
     * @param mpoid 一般养护主键
     * @return 一般养护
     */
    @Override
    public HwMaintainPlanOrdinary selectHwMaintainPlanOrdinaryByMpoid(Long mpoid)
    {
        return hwMaintainPlanOrdinaryMapper.selectHwMaintainPlanOrdinaryByMpoid(mpoid);
    }

    /**
     * 查询一般养护列表
     * 
     * @param hwMaintainPlanOrdinary 一般养护
     * @return 一般养护
     */
    @Override
    public List<HwMaintainPlanOrdinary> selectHwMaintainPlanOrdinaryList(HwMaintainPlanOrdinary hwMaintainPlanOrdinary)
    {
        return hwMaintainPlanOrdinaryMapper.selectHwMaintainPlanOrdinaryList(hwMaintainPlanOrdinary);
    }

    /**
     * 新增一般养护
     * 
     * @param hwMaintainPlanOrdinary 一般养护
     * @return 结果
     */
    @Override
    public int insertHwMaintainPlanOrdinary(HwMaintainPlanOrdinary hwMaintainPlanOrdinary)
    {
        return hwMaintainPlanOrdinaryMapper.insertHwMaintainPlanOrdinary(hwMaintainPlanOrdinary);
    }

    /**
     * 修改一般养护
     * 
     * @param hwMaintainPlanOrdinary 一般养护
     * @return 结果
     */
    @Override
    public int updateHwMaintainPlanOrdinary(HwMaintainPlanOrdinary hwMaintainPlanOrdinary)
    {
        return hwMaintainPlanOrdinaryMapper.updateHwMaintainPlanOrdinary(hwMaintainPlanOrdinary);
    }

    /**
     * 批量删除一般养护
     * 
     * @param mpoids 需要删除的一般养护主键
     * @return 结果
     */
    @Override
    public int deleteHwMaintainPlanOrdinaryByMpoids(Long[] mpoids)
    {
        return hwMaintainPlanOrdinaryMapper.deleteHwMaintainPlanOrdinaryByMpoids(mpoids);
    }

    /**
     * 删除一般养护信息
     * 
     * @param mpoid 一般养护主键
     * @return 结果
     */
    @Override
    public int deleteHwMaintainPlanOrdinaryByMpoid(Long mpoid)
    {
        return hwMaintainPlanOrdinaryMapper.deleteHwMaintainPlanOrdinaryByMpoid(mpoid);
    }
}
