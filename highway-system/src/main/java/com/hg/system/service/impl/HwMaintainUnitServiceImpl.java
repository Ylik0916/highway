package com.hg.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.hg.system.domain.HwMaintainRegion;
import com.hg.system.domain.HwMaintainUnit;
import com.hg.system.mapper.HwMaintainRegionMapper;
import com.hg.system.mapper.HwMaintainUnitMapper;
import com.hg.system.service.IHwMaintainUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 养护单位管理Service业务层处理
 * 
 * @author hg
 * @date 2023-03-07
 */
@Service
@Transactional
public class HwMaintainUnitServiceImpl implements IHwMaintainUnitService
{
    @Autowired
    private HwMaintainUnitMapper hwMaintainUnitMapper;

    @Autowired
    private HwMaintainRegionMapper hwMaintainRegionMapper;


    /**
     * 查询养护单位管理
     * 
     * @param muid 养护单位管理主键
     * @return 养护单位管理
     */
    @Override
    public HwMaintainUnit selectHwMaintainUnitByMuid(Long muid)
    {
        HwMaintainUnit hwMaintainUnit = hwMaintainUnitMapper.selectHwMaintainUnitByMuid(muid);
        List<Integer> list = hwMaintainRegionMapper.selectHwMaintainRegionByMuid(muid);
        hwMaintainUnit.setAdministrative(list);
        return hwMaintainUnit;
    }

    /**
     * 查询养护单位管理列表
     * 
     * @param hwMaintainUnit 养护单位管理
     * @return 养护单位管理
     */
    @Override
    public List<HwMaintainUnit> selectHwMaintainUnitList(HwMaintainUnit hwMaintainUnit)
    {
        return hwMaintainUnitMapper.selectHwMaintainUnitList(hwMaintainUnit);
    }

    /**
     * 新增养护单位管理
     * 
     * @param hwMaintainUnit 养护单位管理
     * @return 结果
     */
    @Override
    public int insertHwMaintainUnit(HwMaintainUnit hwMaintainUnit)
    {
        hwMaintainUnitMapper.insertHwMaintainUnit(hwMaintainUnit);
        List<HwMaintainRegion> regionList = new ArrayList<>();
        for (Integer integer : hwMaintainUnit.getAdministrative()) {
            HwMaintainRegion hwMaintainRegion = new HwMaintainRegion();
            hwMaintainRegion.setMuid(hwMaintainUnit.getMuid());
            hwMaintainRegion.setRegionid(Long.valueOf(integer));
            regionList.add(hwMaintainRegion);
        }
        return hwMaintainRegionMapper.insertHwMaintainRegionList(regionList);
    }

    /**
     * 修改养护单位管理
     * 
     * @param hwMaintainUnit 养护单位管理
     * @return 结果
     */
    @Override
    public int updateHwMaintainUnit(HwMaintainUnit hwMaintainUnit)
    {
        return hwMaintainUnitMapper.updateHwMaintainUnit(hwMaintainUnit);
    }

    /**
     * 批量删除养护单位管理
     * 
     * @param muids 需要删除的养护单位管理主键
     * @return 结果
     */
    @Override
    public int deleteHwMaintainUnitByMuids(Long[] muids)
    {
        return hwMaintainUnitMapper.deleteHwMaintainUnitByMuids(muids);
    }

    /**
     * 删除养护单位管理信息
     * 
     * @param muid 养护单位管理主键
     * @return 结果
     */
    @Override
    public int deleteHwMaintainUnitByMuid(Long muid)
    {
        return hwMaintainUnitMapper.deleteHwMaintainUnitByMuid(muid);
    }
}
