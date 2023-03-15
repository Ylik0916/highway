package com.hg.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hg.system.mapper.HwUnitEvaluateMapper;
import com.hg.system.domain.HwUnitEvaluate;
import com.hg.system.service.IHwUnitEvaluateService;

/**
 * 从业单位评价Service业务层处理
 * 
 * @author W-yf
 * @date 2023-03-15
 */
@Service
public class HwUnitEvaluateServiceImpl implements IHwUnitEvaluateService 
{
    @Autowired
    private HwUnitEvaluateMapper hwUnitEvaluateMapper;

    /**
     * 查询从业单位评价
     * 
     * @param unitId 从业单位评价主键
     * @return 从业单位评价
     */
    @Override
    public HwUnitEvaluate selectHwUnitEvaluateByUnitId(Long unitId)
    {
        return hwUnitEvaluateMapper.selectHwUnitEvaluateByUnitId(unitId);
    }

    /**
     * 查询从业单位评价列表
     * 
     * @param hwUnitEvaluate 从业单位评价
     * @return 从业单位评价
     */
    @Override
    public List<HwUnitEvaluate> selectHwUnitEvaluateList(HwUnitEvaluate hwUnitEvaluate)
    {
        return hwUnitEvaluateMapper.selectHwUnitEvaluateList(hwUnitEvaluate);
    }

    /**
     * 新增从业单位评价
     * 
     * @param hwUnitEvaluate 从业单位评价
     * @return 结果
     */
    @Override
    public int insertHwUnitEvaluate(HwUnitEvaluate hwUnitEvaluate)
    {
        return hwUnitEvaluateMapper.insertHwUnitEvaluate(hwUnitEvaluate);
    }

    /**
     * 修改从业单位评价
     * 
     * @param hwUnitEvaluate 从业单位评价
     * @return 结果
     */
    @Override
    public int updateHwUnitEvaluate(HwUnitEvaluate hwUnitEvaluate)
    {
        return hwUnitEvaluateMapper.updateHwUnitEvaluate(hwUnitEvaluate);
    }

    /**
     * 批量删除从业单位评价
     * 
     * @param unitIds 需要删除的从业单位评价主键
     * @return 结果
     */
    @Override
    public int deleteHwUnitEvaluateByUnitIds(Long[] unitIds)
    {
        return hwUnitEvaluateMapper.deleteHwUnitEvaluateByUnitIds(unitIds);
    }

    /**
     * 删除从业单位评价信息
     * 
     * @param unitId 从业单位评价主键
     * @return 结果
     */
    @Override
    public int deleteHwUnitEvaluateByUnitId(Long unitId)
    {
        return hwUnitEvaluateMapper.deleteHwUnitEvaluateByUnitId(unitId);
    }
}
