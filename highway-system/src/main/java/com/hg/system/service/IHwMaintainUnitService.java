package com.hg.system.service;

import com.hg.system.domain.HwMaintainUnit;

import java.util.List;

/**
 * 养护单位管理Service接口
 * 
 * @author ruoyi
 * @date 2023-03-07
 */
public interface IHwMaintainUnitService 
{
    /**
     * 查询养护单位管理
     * 
     * @param muid 养护单位管理主键
     * @return 养护单位管理
     */
    public HwMaintainUnit selectHwMaintainUnitByMuid(Long muid);

    /**
     * 查询养护单位管理列表
     * 
     * @param hwMaintainUnit 养护单位管理
     * @return 养护单位管理集合
     */
    public List<HwMaintainUnit> selectHwMaintainUnitList(HwMaintainUnit hwMaintainUnit);

    /**
     * 新增养护单位管理
     * 
     * @param hwMaintainUnit 养护单位管理
     * @return 结果
     */
    public int insertHwMaintainUnit(HwMaintainUnit hwMaintainUnit);

    /**
     * 修改养护单位管理
     * 
     * @param hwMaintainUnit 养护单位管理
     * @return 结果
     */
    public int updateHwMaintainUnit(HwMaintainUnit hwMaintainUnit);

    /**
     * 批量删除养护单位管理
     * 
     * @param muids 需要删除的养护单位管理主键集合
     * @return 结果
     */
    public int deleteHwMaintainUnitByMuids(Long[] muids);

    /**
     * 删除养护单位管理信息
     * 
     * @param muid 养护单位管理主键
     * @return 结果
     */
    public int deleteHwMaintainUnitByMuid(Long muid);
}
