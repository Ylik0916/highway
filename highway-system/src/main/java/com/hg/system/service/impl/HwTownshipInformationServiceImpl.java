package com.hg.system.service.impl;

import com.hg.system.domain.HwTownshipInformation;
import com.hg.system.mapper.HwTownshipInformationMapper;
import com.hg.system.service.IHwTownshipInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 乡镇信息Service业务层处理
 *
 * @author ruoyi
 * @date 2023-03-24
 */
@Service
public class HwTownshipInformationServiceImpl implements IHwTownshipInformationService
{
    @Autowired
    private HwTownshipInformationMapper hwTownshipInformationMapper;

    /**
     * 查询乡镇信息
     *
     * @param townshipId 乡镇信息主键
     * @return 乡镇信息
     */
    @Override
    public HwTownshipInformation selectHwTownshipInformationByTownshipId(Long townshipId)
    {
        return hwTownshipInformationMapper.selectHwTownshipInformationByTownshipId(townshipId);
    }

    /**
     * 查询乡镇信息列表
     *
     * @param hwTownshipInformation 乡镇信息
     * @return 乡镇信息
     */
    @Override
    public List<HwTownshipInformation> selectHwTownshipInformationList(HwTownshipInformation hwTownshipInformation)
    {
        return hwTownshipInformationMapper.selectHwTownshipInformationList(hwTownshipInformation);
    }

    /**
     * 新增乡镇信息
     *
     * @param hwTownshipInformation 乡镇信息
     * @return 结果
     */
    @Override
    public int insertHwTownshipInformation(HwTownshipInformation hwTownshipInformation)
    {
        return hwTownshipInformationMapper.insertHwTownshipInformation(hwTownshipInformation);
    }

    /**
     * 修改乡镇信息
     *
     * @param hwTownshipInformation 乡镇信息
     * @return 结果
     */
    @Override
    public int updateHwTownshipInformation(HwTownshipInformation hwTownshipInformation)
    {
        return hwTownshipInformationMapper.updateHwTownshipInformation(hwTownshipInformation);
    }

    /**
     * 批量删除乡镇信息
     *
     * @param townshipIds 需要删除的乡镇信息主键
     * @return 结果
     */
    @Override
    public int deleteHwTownshipInformationByTownshipIds(Long[] townshipIds)
    {
        return hwTownshipInformationMapper.deleteHwTownshipInformationByTownshipIds(townshipIds);
    }

    /**
     * 删除乡镇信息信息
     *
     * @param townshipId 乡镇信息主键
     * @return 结果
     */
    @Override
    public int deleteHwTownshipInformationByTownshipId(Long townshipId)
    {
        return hwTownshipInformationMapper.deleteHwTownshipInformationByTownshipId(townshipId);
    }
}
