package com.hg.system.service.impl;

import com.hg.system.domain.HwModernIndustrialParkInformation;
import com.hg.system.mapper.HwModernIndustrialParkInformationMapper;
import com.hg.system.service.IHwModernIndustrialParkInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 现代产业园Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-14
 */
@Service
public class HwModernIndustrialParkInformationServiceImpl implements IHwModernIndustrialParkInformationService 
{
    @Autowired
    private HwModernIndustrialParkInformationMapper hwModernIndustrialParkInformationMapper;

    /**
     * 查询现代产业园
     * 
     * @param industrialParkId 现代产业园主键
     * @return 现代产业园
     */
    @Override
    public HwModernIndustrialParkInformation selectHwModernIndustrialParkInformationByIndustrialParkId(Long industrialParkId)
    {
        return hwModernIndustrialParkInformationMapper.selectHwModernIndustrialParkInformationByIndustrialParkId(industrialParkId);
    }

    /**
     * 查询现代产业园列表
     * 
     * @param hwModernIndustrialParkInformation 现代产业园
     * @return 现代产业园
     */
    @Override
    public List<HwModernIndustrialParkInformation> selectHwModernIndustrialParkInformationList(HwModernIndustrialParkInformation hwModernIndustrialParkInformation)
    {
        return hwModernIndustrialParkInformationMapper.selectHwModernIndustrialParkInformationList(hwModernIndustrialParkInformation);
    }

    /**
     * 新增现代产业园
     * 
     * @param hwModernIndustrialParkInformation 现代产业园
     * @return 结果
     */
    @Override
    public int insertHwModernIndustrialParkInformation(HwModernIndustrialParkInformation hwModernIndustrialParkInformation)
    {
        return hwModernIndustrialParkInformationMapper.insertHwModernIndustrialParkInformation(hwModernIndustrialParkInformation);
    }

    /**
     * 修改现代产业园
     * 
     * @param hwModernIndustrialParkInformation 现代产业园
     * @return 结果
     */
    @Override
    public int updateHwModernIndustrialParkInformation(HwModernIndustrialParkInformation hwModernIndustrialParkInformation)
    {
        return hwModernIndustrialParkInformationMapper.updateHwModernIndustrialParkInformation(hwModernIndustrialParkInformation);
    }

    /**
     * 批量删除现代产业园
     * 
     * @param industrialParkIds 需要删除的现代产业园主键
     * @return 结果
     */
    @Override
    public int deleteHwModernIndustrialParkInformationByIndustrialParkIds(Long[] industrialParkIds)
    {
        return hwModernIndustrialParkInformationMapper.deleteHwModernIndustrialParkInformationByIndustrialParkIds(industrialParkIds);
    }

    /**
     * 删除现代产业园信息
     * 
     * @param industrialParkId 现代产业园主键
     * @return 结果
     */
    @Override
    public int deleteHwModernIndustrialParkInformationByIndustrialParkId(Long industrialParkId)
    {
        return hwModernIndustrialParkInformationMapper.deleteHwModernIndustrialParkInformationByIndustrialParkId(industrialParkId);
    }
}
