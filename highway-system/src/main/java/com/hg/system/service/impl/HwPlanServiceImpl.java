package com.hg.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hg.system.mapper.HwPlanMapper;
import com.hg.system.domain.HwPlan;
import com.hg.system.service.IHwPlanService;

/**
 * 年度计划Service业务层处理
 * 
 * @author Cxh
 * @date 2023-03-15
 */
@Service
public class HwPlanServiceImpl implements IHwPlanService 
{
    @Autowired
    private HwPlanMapper hwPlanMapper;

    /**
     * 查询年度计划
     * 
     * @param id 年度计划主键
     * @return 年度计划
     */
    @Override
    public HwPlan selectHwPlanById(Long id)
    {
        return hwPlanMapper.selectHwPlanById(id);
    }

    /**
     * 查询年度计划列表
     * 
     * @param hwPlan 年度计划
     * @return 年度计划
     */
    @Override
    public List<HwPlan> selectHwPlanList(HwPlan hwPlan)
    {
        return hwPlanMapper.selectHwPlanList(hwPlan);
    }

    /**
     * 新增年度计划
     * 
     * @param hwPlan 年度计划
     * @return 结果
     */
    @Override
    public int insertHwPlan(HwPlan hwPlan)
    {
        return hwPlanMapper.insertHwPlan(hwPlan);
    }

    /**
     * 修改年度计划
     * 
     * @param hwPlan 年度计划
     * @return 结果
     */
    @Override
    public int updateHwPlan(HwPlan hwPlan)
    {
        return hwPlanMapper.updateHwPlan(hwPlan);
    }

    /**
     * 批量删除年度计划
     * 
     * @param ids 需要删除的年度计划主键
     * @return 结果
     */
    @Override
    public int deleteHwPlanByIds(Long[] ids)
    {
        return hwPlanMapper.deleteHwPlanByIds(ids);
    }

    /**
     * 删除年度计划信息
     * 
     * @param id 年度计划主键
     * @return 结果
     */
    @Override
    public int deleteHwPlanById(Long id)
    {
        return hwPlanMapper.deleteHwPlanById(id);
    }
}
