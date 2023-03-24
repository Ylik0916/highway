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
     *     查询进度列表 和 所属的年度计划
     *
     * @param hwPlanItem 进度
     * @return 进度集合
     */
    // public List<HwPlanItem> selectHwPlanAndItemList(HwPlanItem hwPlanItem);


    /**
     * 根据年度计划查询进度列表
     *
     * @param id 进度
     * @return 进度集合
     */
    public List<HwPlanItem> selectHwPlanItemListByPlanId(long id);

    /**
     * 查询进度列表
     * 
     * @param hwPlanItem 进度
     * @return 进度集合
     */
    public List<HwPlanItem> selectHwPlanItemList(HwPlanItem hwPlanItem);



    /**
     * 查询待上报明细列表
     *
     * @param hwPlanItem 进度
     * @return 进度集合
     */
    public List<HwPlanItem> selectHwPlanItemListAnd0(HwPlanItem hwPlanItem);


    /**
     * 查询等待审核明细列表
     *
     * @param hwPlanItem 进度
     * @return 进度集合
     */
    public List<HwPlanItem> selectHwPlanItemListAnd1(HwPlanItem hwPlanItem);
    
    /**
     * 查询已驳回明细上报列表 以及对应的年度计划名称
     *
     * @param hwPlanItem 进度
     * @return 进度集合
     */
    public List<HwPlanItem> selectHwPlanItemListAnd2(HwPlanItem hwPlanItem);



    /**
     * 查询已审核明细上报列表
     *
     * @param hwPlanItem 进度
     * @return 进度集合
     */
    public List<HwPlanItem> selectHwPlanItemListAnd3(HwPlanItem hwPlanItem);



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
