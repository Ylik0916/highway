package com.hg.system.service;

import com.hg.system.domain.HwSignageInformation;

import java.util.List;

/**
 * 标识标牌Service接口
 * 
 * @author ruoyi
 * @date 2023-03-14
 */
public interface IHwSignageInformationService 
{
    /**
     * 查询标识标牌
     * 
     * @param identificationTagId 标识标牌主键
     * @return 标识标牌
     */
    public HwSignageInformation selectHwSignageInformationByIdentificationTagId(Long identificationTagId);

    /**
     * 查询标识标牌列表
     * 
     * @param hwSignageInformation 标识标牌
     * @return 标识标牌集合
     */
    public List<HwSignageInformation> selectHwSignageInformationList(HwSignageInformation hwSignageInformation);

    /**
     * 新增标识标牌
     * 
     * @param hwSignageInformation 标识标牌
     * @return 结果
     */
    public int insertHwSignageInformation(HwSignageInformation hwSignageInformation);

    /**
     * 修改标识标牌
     * 
     * @param hwSignageInformation 标识标牌
     * @return 结果
     */
    public int updateHwSignageInformation(HwSignageInformation hwSignageInformation);

    /**
     * 批量删除标识标牌
     * 
     * @param identificationTagIds 需要删除的标识标牌主键集合
     * @return 结果
     */
    public int deleteHwSignageInformationByIdentificationTagIds(Long[] identificationTagIds);

    /**
     * 删除标识标牌信息
     * 
     * @param identificationTagId 标识标牌主键
     * @return 结果
     */
    public int deleteHwSignageInformationByIdentificationTagId(Long identificationTagId);

    /**
     * 根据路线Id查询所有的标识标牌
     * @param routeid 路段id
     * @return 标识标牌集合
     */
    public List<HwSignageInformation> selectHwSignageInformationListByRouteId(Long routeid);
}
