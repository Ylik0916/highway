package com.hg.system.service;

import java.util.List;
import com.hg.system.domain.HwTownshipInformation;

/**
 * 乡镇Service接口
 * 
 * @author ruoyi
 * @date 2023-03-14
 */
public interface IHwTownshipInformationService 
{
    /**
     * 查询乡镇
     * 
     * @param townshipId 乡镇主键
     * @return 乡镇
     */
    public HwTownshipInformation selectHwTownshipInformationByTownshipId(Long townshipId);

    /**
     * 查询乡镇列表
     * 
     * @param hwTownshipInformation 乡镇
     * @return 乡镇集合
     */
    public List<HwTownshipInformation> selectHwTownshipInformationList(HwTownshipInformation hwTownshipInformation);

    /**
     * 新增乡镇
     * 
     * @param hwTownshipInformation 乡镇
     * @return 结果
     */
    public int insertHwTownshipInformation(HwTownshipInformation hwTownshipInformation);

    /**
     * 修改乡镇
     * 
     * @param hwTownshipInformation 乡镇
     * @return 结果
     */
    public int updateHwTownshipInformation(HwTownshipInformation hwTownshipInformation);

    /**
     * 批量删除乡镇
     * 
     * @param townshipIds 需要删除的乡镇主键集合
     * @return 结果
     */
    public int deleteHwTownshipInformationByTownshipIds(Long[] townshipIds);

    /**
     * 删除乡镇信息
     * 
     * @param townshipId 乡镇主键
     * @return 结果
     */
    public int deleteHwTownshipInformationByTownshipId(Long townshipId);
}
