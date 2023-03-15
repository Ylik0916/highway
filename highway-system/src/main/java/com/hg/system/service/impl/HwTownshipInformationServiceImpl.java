package com.hg.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hg.system.mapper.HwTownshipInformationMapper;
import com.hg.system.domain.HwTownshipInformation;
import com.hg.system.service.IHwTownshipInformationService;

/**
 * 乡镇Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-14
 */
@Service
public class HwTownshipInformationServiceImpl implements IHwTownshipInformationService 
{
    @Autowired
    private HwTownshipInformationMapper hwTownshipInformationMapper;

    /**
     * 查询乡镇
     * 
     * @param townshipId 乡镇主键
     * @return 乡镇
     */
    @Override
    public HwTownshipInformation selectHwTownshipInformationByTownshipId(Long townshipId)
    {
        return hwTownshipInformationMapper.selectHwTownshipInformationByTownshipId(townshipId);
    }

    /**
     * 查询乡镇列表
     * 
     * @param hwTownshipInformation 乡镇
     * @return 乡镇
     */
    @Override
    public List<HwTownshipInformation> selectHwTownshipInformationList(HwTownshipInformation hwTownshipInformation)
    {
        return hwTownshipInformationMapper.selectHwTownshipInformationList(hwTownshipInformation);
    }

    /**
     * 新增乡镇
     * 
     * @param hwTownshipInformation 乡镇
     * @return 结果
     */
    @Override
    public int insertHwTownshipInformation(HwTownshipInformation hwTownshipInformation)
    {
        return hwTownshipInformationMapper.insertHwTownshipInformation(hwTownshipInformation);
    }

    /**
     * 修改乡镇
     * 
     * @param hwTownshipInformation 乡镇
     * @return 结果
     */
    @Override
    public int updateHwTownshipInformation(HwTownshipInformation hwTownshipInformation)
    {
        return hwTownshipInformationMapper.updateHwTownshipInformation(hwTownshipInformation);
    }

    /**
     * 批量删除乡镇
     * 
     * @param townshipIds 需要删除的乡镇主键
     * @return 结果
     */
    @Override
    public int deleteHwTownshipInformationByTownshipIds(Long[] townshipIds)
    {
        return hwTownshipInformationMapper.deleteHwTownshipInformationByTownshipIds(townshipIds);
    }

    /**
     * 删除乡镇信息
     * 
     * @param townshipId 乡镇主键
     * @return 结果
     */
    @Override
    public int deleteHwTownshipInformationByTownshipId(Long townshipId)
    {
        return hwTownshipInformationMapper.deleteHwTownshipInformationByTownshipId(townshipId);
    }
}
