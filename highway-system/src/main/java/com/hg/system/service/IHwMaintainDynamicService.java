package com.hg.system.service;

import com.hg.system.domain.HwMaintainDynamic;

import java.util.List;

/**
 * 动态养护管理Service接口
 * 
 * @author ruoyi
 * @date 2023-03-07
 */
public interface IHwMaintainDynamicService 
{
    /**
     * 查询动态养护管理
     * 
     * @param mdid 动态养护管理主键
     * @return 动态养护管理
     */
    public HwMaintainDynamic selectHwMaintainDynamicByMdid(Long mdid);

    /**
     * 查询动态养护管理列表
     * 
     * @param hwMaintainDynamic 动态养护管理
     * @return 动态养护管理集合
     */
    public List<HwMaintainDynamic> selectHwMaintainDynamicList(HwMaintainDynamic hwMaintainDynamic);

    /**
     * 新增动态养护管理
     * 
     * @param hwMaintainDynamic 动态养护管理
     * @return 结果
     */
    public int insertHwMaintainDynamic(HwMaintainDynamic hwMaintainDynamic);

    /**
     * 修改动态养护管理
     * 
     * @param hwMaintainDynamic 动态养护管理
     * @return 结果
     */
    public int updateHwMaintainDynamic(HwMaintainDynamic hwMaintainDynamic);

    /**
     * 批量删除动态养护管理
     * 
     * @param mdids 需要删除的动态养护管理主键集合
     * @return 结果
     */
    public int deleteHwMaintainDynamicByMdids(Long[] mdids);

    /**
     * 删除动态养护管理信息
     * 
     * @param mdid 动态养护管理主键
     * @return 结果
     */
    public int deleteHwMaintainDynamicByMdid(Long mdid);
}
