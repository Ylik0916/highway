package com.hg.system.mapper;

import com.hg.system.domain.HwModernIndustrialParkInformation;

import java.util.List;

/**
 * 现代产业园Mapper接口
 * 
 * @author ruoyi
 * @date 2023-03-14
 */
public interface HwModernIndustrialParkInformationMapper 
{
    /**
     * 查询现代产业园
     * 
     * @param industrialParkId 现代产业园主键
     * @return 现代产业园
     */
    public HwModernIndustrialParkInformation selectHwModernIndustrialParkInformationByIndustrialParkId(Long industrialParkId);

    /**
     * 查询现代产业园列表
     * 
     * @param hwModernIndustrialParkInformation 现代产业园
     * @return 现代产业园集合
     */
    public List<HwModernIndustrialParkInformation> selectHwModernIndustrialParkInformationList(HwModernIndustrialParkInformation hwModernIndustrialParkInformation);

    /**
     * 新增现代产业园
     * 
     * @param hwModernIndustrialParkInformation 现代产业园
     * @return 结果
     */
    public int insertHwModernIndustrialParkInformation(HwModernIndustrialParkInformation hwModernIndustrialParkInformation);

    /**
     * 修改现代产业园
     * 
     * @param hwModernIndustrialParkInformation 现代产业园
     * @return 结果
     */
    public int updateHwModernIndustrialParkInformation(HwModernIndustrialParkInformation hwModernIndustrialParkInformation);

    /**
     * 删除现代产业园
     * 
     * @param industrialParkId 现代产业园主键
     * @return 结果
     */
    public int deleteHwModernIndustrialParkInformationByIndustrialParkId(Long industrialParkId);

    /**
     * 批量删除现代产业园
     * 
     * @param industrialParkIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHwModernIndustrialParkInformationByIndustrialParkIds(Long[] industrialParkIds);
}
