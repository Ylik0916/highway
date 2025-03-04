package com.hg.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hg.system.mapper.HwPlanItemMapper;
import com.hg.system.domain.HwPlanItem;
import com.hg.system.service.IHwPlanItemService;

/**
 * 进度Service业务层处理
 * 
 * @author Cxh
 * @date 2023-03-17
 */
@Service
public class HwPlanItemServiceImpl implements IHwPlanItemService 
{
    @Autowired
    private HwPlanItemMapper hwPlanItemMapper;

    /**
     * 查询进度
     * 
     * @param id 进度主键
     * @return 进度
     */
    @Override
    public HwPlanItem selectHwPlanItemById(Long id)
    {
        return hwPlanItemMapper.selectHwPlanItemById(id);
    }

    /**
     * 查询进度列表 和 所属的年度计划
     *
     * @param hwPlanItem 进度
     * @return 进度
     */
    // @Override
    // public List<HwPlanItem> selectHwPlanAndItemList(HwPlanItem hwPlanItem)
    // {
    //     return hwPlanItemMapper.selectHwPlanAndItemList(hwPlanItem);
    // }



    /**
     * 根据年度计划查询进度列表
     *
     * @param id 进度
     * @return 进度
     */
    @Override
    public List<HwPlanItem> selectHwPlanItemListByPlanId(long id)
    {
        return hwPlanItemMapper.selectHwPlanItemListByPlanId(id);
    }
    
    /**
     * 查询进度列表
     * 
     * @param hwPlanItem 进度
     * @return 进度
     */
    @Override
    public List<HwPlanItem> selectHwPlanItemList(HwPlanItem hwPlanItem)
    {
        return hwPlanItemMapper.selectHwPlanItemList(hwPlanItem);
    }


    /**
     * 查询待上报明细列表
     *
     * @param hwPlanItem 进度
     * @return 进度
     */
    @Override
    public List<HwPlanItem> selectHwPlanItemListAnd0(HwPlanItem hwPlanItem)
    {
        return hwPlanItemMapper.selectHwPlanItemListAnd0(hwPlanItem);
    }


    /**
     * 查询等待审核明细列表
     *
     * @param hwPlanItem 进度
     * @return 进度
     */
    @Override
    public List<HwPlanItem> selectHwPlanItemListAnd1(HwPlanItem hwPlanItem)
    {
        return hwPlanItemMapper.selectHwPlanItemListAnd1(hwPlanItem);
    }



    /**
     * 查询已驳回明细上报列表 以及对应的年度计划名称
     *
     * @param hwPlanItem 进度
     * @return 进度
     */
    @Override
    public List<HwPlanItem> selectHwPlanItemListAnd2(HwPlanItem hwPlanItem)
    {
        return hwPlanItemMapper.selectHwPlanItemListAnd2(hwPlanItem);
    }



    /**
     * 查询已审核明细上报列表
     *
     * @param hwPlanItem 进度
     * @return 进度
     */
    @Override
    public List<HwPlanItem> selectHwPlanItemListAnd3(HwPlanItem hwPlanItem)
    {
        return hwPlanItemMapper.selectHwPlanItemListAnd3(hwPlanItem);
    }



    /**
     * 新增进度
     * 
     * @param hwPlanItem 进度
     * @return 结果
     */
    @Override
    public int insertHwPlanItem(HwPlanItem hwPlanItem)
    {
        return hwPlanItemMapper.insertHwPlanItem(hwPlanItem);
    }

    /**
     * 修改进度
     * 
     * @param hwPlanItem 进度
     * @return 结果
     */
    @Override
    public int updateHwPlanItem(HwPlanItem hwPlanItem)
    {
        return hwPlanItemMapper.updateHwPlanItem(hwPlanItem);
    }


    /**
     * 修改进度状态为已审核
     *
     * @param id 进度
     * @return 结果
     */
    @Override
    public int updateItemScale(Long id)
    {
        return hwPlanItemMapper.updateItemScale(id);
    }


    /**
     * 修改进度状态为已驳回
     *
     * @param id 进度
     * @return 结果
     */
    @Override
    public int updateItemReject(Long id)
    {
        return hwPlanItemMapper.updateItemReject(id);
    }
    

    /**
     * 批量删除进度
     * 
     * @param ids 需要删除的进度主键
     * @return 结果
     */
    @Override
    public int deleteHwPlanItemByIds(Long[] ids)
    {
        return hwPlanItemMapper.deleteHwPlanItemByIds(ids);
    }

    /**
     * 删除进度信息
     * 
     * @param id 进度主键
     * @return 结果
     */
    @Override
    public int deleteHwPlanItemById(Long id)
    {
        return hwPlanItemMapper.deleteHwPlanItemById(id);
    }
}
