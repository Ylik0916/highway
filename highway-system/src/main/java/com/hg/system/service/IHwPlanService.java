package com.hg.system.service;

import java.util.List;
import com.hg.system.domain.HwPlan;

/**
 * 年度计划Service接口
 * 
 * @author Cxh
 * @date 2023-03-15
 */
public interface IHwPlanService 
{
    /**
     * 查询年度计划
     * 
     * @param id 年度计划主键
     * @return 年度计划
     */
    public HwPlan selectHwPlanById(Long id);

    /**
     * 查询年度计划列表
     * 
     * @param hwPlan 年度计划
     * @return 年度计划集合
     */
    public List<HwPlan> selectHwPlanList(HwPlan hwPlan);

    /**
     * 新增年度计划
     * 
     * @param hwPlan 年度计划
     * @return 结果
     */
    public int insertHwPlan(HwPlan hwPlan);

    /**
     * 修改年度计划
     * 
     * @param hwPlan 年度计划
     * @return 结果
     */
    public int updateHwPlan(HwPlan hwPlan);

    /**
     * 批量删除年度计划
     * 
     * @param ids 需要删除的年度计划主键集合
     * @return 结果
     */
    public int deleteHwPlanByIds(Long[] ids);

    /**
     * 删除年度计划信息
     * 
     * @param id 年度计划主键
     * @return 结果
     */
    public int deleteHwPlanById(Long id);
}
