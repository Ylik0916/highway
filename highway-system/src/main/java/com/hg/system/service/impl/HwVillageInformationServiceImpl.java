package com.hg.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hg.system.mapper.HwVillageInformationMapper;
import com.hg.system.domain.HwVillageInformation;
import com.hg.system.service.IHwVillageInformationService;

/**
 * 建制村Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-14
 */
@Service
public class HwVillageInformationServiceImpl implements IHwVillageInformationService 
{
    @Autowired
    private HwVillageInformationMapper hwVillageInformationMapper;

    /**
     * 查询建制村
     * 
     * @param villageId 建制村主键
     * @return 建制村
     */
    @Override
    public HwVillageInformation selectHwVillageInformationByVillageId(Long villageId)
    {
        return hwVillageInformationMapper.selectHwVillageInformationByVillageId(villageId);
    }

    /**
     * 查询建制村列表
     * 
     * @param hwVillageInformation 建制村
     * @return 建制村
     */
    @Override
    public List<HwVillageInformation> selectHwVillageInformationList(HwVillageInformation hwVillageInformation)
    {
        return hwVillageInformationMapper.selectHwVillageInformationList(hwVillageInformation);
    }

    /**
     * 新增建制村
     * 
     * @param hwVillageInformation 建制村
     * @return 结果
     */
    @Override
    public int insertHwVillageInformation(HwVillageInformation hwVillageInformation)
    {
        return hwVillageInformationMapper.insertHwVillageInformation(hwVillageInformation);
    }

    /**
     * 修改建制村
     * 
     * @param hwVillageInformation 建制村
     * @return 结果
     */
    @Override
    public int updateHwVillageInformation(HwVillageInformation hwVillageInformation)
    {
        return hwVillageInformationMapper.updateHwVillageInformation(hwVillageInformation);
    }

    /**
     * 批量删除建制村
     * 
     * @param villageIds 需要删除的建制村主键
     * @return 结果
     */
    @Override
    public int deleteHwVillageInformationByVillageIds(Long[] villageIds)
    {
        return hwVillageInformationMapper.deleteHwVillageInformationByVillageIds(villageIds);
    }

    /**
     * 删除建制村信息
     * 
     * @param villageId 建制村主键
     * @return 结果
     */
    @Override
    public int deleteHwVillageInformationByVillageId(Long villageId)
    {
        return hwVillageInformationMapper.deleteHwVillageInformationByVillageId(villageId);
    }
}
