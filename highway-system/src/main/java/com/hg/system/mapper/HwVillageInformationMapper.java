package com.hg.system.mapper;

import java.util.List;
import com.hg.system.domain.HwVillageInformation;

/**
 * 建制村Mapper接口
 * 
 * @author ruoyi
 * @date 2023-03-14
 */
public interface HwVillageInformationMapper 
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
     * 删除建制村
     * 
     * @param villageId 建制村主键
     * @return 结果
     */
    public int deleteHwVillageInformationByVillageId(Long villageId);

    /**
     * 批量删除建制村
     * 
     * @param villageIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHwVillageInformationByVillageIds(Long[] villageIds);
}
