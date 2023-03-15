package com.hg.system.service;

import java.util.List;
import com.hg.system.domain.HwVillageInformation;

/**
 * 建制村Service接口
 * 
 * @author ruoyi
 * @date 2023-03-14
 */
public interface IHwVillageInformationService 
{
    /**
     * 查询建制村
     * 
     * @param villageId 建制村主键
     * @return 建制村
     */
    public HwVillageInformation selectHwVillageInformationByVillageId(Long villageId);

    /**
     * 查询建制村列表
     * 
     * @param hwVillageInformation 建制村
     * @return 建制村集合
     */
    public List<HwVillageInformation> selectHwVillageInformationList(HwVillageInformation hwVillageInformation);

    /**
     * 新增建制村
     * 
     * @param hwVillageInformation 建制村
     * @return 结果
     */
    public int insertHwVillageInformation(HwVillageInformation hwVillageInformation);

    /**
     * 修改建制村
     * 
     * @param hwVillageInformation 建制村
     * @return 结果
     */
    public int updateHwVillageInformation(HwVillageInformation hwVillageInformation);

    /**
     * 批量删除建制村
     * 
     * @param villageIds 需要删除的建制村主键集合
     * @return 结果
     */
    public int deleteHwVillageInformationByVillageIds(Long[] villageIds);

    /**
     * 删除建制村信息
     * 
     * @param villageId 建制村主键
     * @return 结果
     */
    public int deleteHwVillageInformationByVillageId(Long villageId);
}
