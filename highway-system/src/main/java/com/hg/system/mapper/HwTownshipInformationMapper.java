package com.hg.system.mapper;

import com.hg.system.domain.HwTownshipInformation;

import java.util.List;

/**
 * 乡镇信息Mapper接口
 *
 * @author ruoyi
 * @date 2023-03-24
 */
public interface HwTownshipInformationMapper
{
    /**
     * 查询乡镇信息
     *
     * @param townshipId 乡镇信息主键
     * @return 乡镇信息
     */
    public HwTownshipInformation selectHwTownshipInformationByTownshipId(Long townshipId);

    /**
     * 查询乡镇信息列表
     *
     * @param hwTownshipInformation 乡镇信息
     * @return 乡镇信息集合
     */
    public List<HwTownshipInformation> selectHwTownshipInformationList(HwTownshipInformation hwTownshipInformation);

    /**
     * 新增乡镇信息
     *
     * @param hwTownshipInformation 乡镇信息
     * @return 结果
     */
    public int insertHwTownshipInformation(HwTownshipInformation hwTownshipInformation);

    /**
     * 修改乡镇信息
     *
     * @param hwTownshipInformation 乡镇信息
     * @return 结果
     */
    public int updateHwTownshipInformation(HwTownshipInformation hwTownshipInformation);

    /**
     * 删除乡镇信息
     *
     * @param townshipId 乡镇信息主键
     * @return 结果
     */
    public int deleteHwTownshipInformationByTownshipId(Long townshipId);

    /**
     * 批量删除乡镇信息
     *
     * @param townshipIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHwTownshipInformationByTownshipIds(Long[] townshipIds);
}
