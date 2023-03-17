package com.hg.system.service;

import java.util.List;
import com.hg.system.domain.HwPlanItem;

/**
 * 进度Service接口
 * 
 * @author Cxh
 * @date 2023-03-17
 */
public interface IHwPlanItemService 
{
    /**
     * 查询进度
     * 
     * @param id 进度主键
     * @return 进度
     */
    public HwPlanItem selectHwPlanItemById(Long id);

    /**
     * 查询进度列表
     * 
     * @param hwPlanItem 进度
     * @return 进度集合
     */
    public List<HwPlanItem> selectHwPlanItemList(HwPlanItem hwPlanItem);

    /**
     * 新增进度
     * 
     * @param hwPlanItem 进度
     * @return 结果
     */
    public int insertHwPlanItem(HwPlanItem hwPlanItem);

    /**
     * 修改进度
     * 
     * @param hwPlanItem 进度
     * @return 结果
     */
    public int updateHwPlanItem(HwPlanItem hwPlanItem);

    /**
     * 批量删除进度
     * 
     * @param ids 需要删除的进度主键集合
     * @return 结果
     */
    public int deleteHwPlanItemByIds(Long[] ids);

    /**
     * 删除进度信息
     * 
     * @param id 进度主键
     * @return 结果
     */
    public int deleteHwPlanItemById(Long id);
}
