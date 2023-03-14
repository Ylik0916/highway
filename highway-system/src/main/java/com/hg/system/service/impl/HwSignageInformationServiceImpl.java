package com.hg.system.service.impl;

import com.hg.system.domain.HwSignageInformation;
import com.hg.system.mapper.HwSignageInformationMapper;
import com.hg.system.service.IHwSignageInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 标识标牌Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-14
 */
@Service
public class HwSignageInformationServiceImpl implements IHwSignageInformationService 
{
    @Autowired
    private HwSignageInformationMapper hwSignageInformationMapper;

    /**
     * 查询标识标牌
     * 
     * @param identificationTagId 标识标牌主键
     * @return 标识标牌
     */
    @Override
    public HwSignageInformation selectHwSignageInformationByIdentificationTagId(Long identificationTagId)
    {
        return hwSignageInformationMapper.selectHwSignageInformationByIdentificationTagId(identificationTagId);
    }

    /**
     * 查询标识标牌列表
     * 
     * @param hwSignageInformation 标识标牌
     * @return 标识标牌
     */
    @Override
    public List<HwSignageInformation> selectHwSignageInformationList(HwSignageInformation hwSignageInformation)
    {
        return hwSignageInformationMapper.selectHwSignageInformationList(hwSignageInformation);
    }

    /**
     * 新增标识标牌
     * 
     * @param hwSignageInformation 标识标牌
     * @return 结果
     */
    @Override
    public int insertHwSignageInformation(HwSignageInformation hwSignageInformation)
    {
        return hwSignageInformationMapper.insertHwSignageInformation(hwSignageInformation);
    }

    /**
     * 修改标识标牌
     * 
     * @param hwSignageInformation 标识标牌
     * @return 结果
     */
    @Override
    public int updateHwSignageInformation(HwSignageInformation hwSignageInformation)
    {
        return hwSignageInformationMapper.updateHwSignageInformation(hwSignageInformation);
    }

    /**
     * 批量删除标识标牌
     * 
     * @param identificationTagIds 需要删除的标识标牌主键
     * @return 结果
     */
    @Override
    public int deleteHwSignageInformationByIdentificationTagIds(Long[] identificationTagIds)
    {
        return hwSignageInformationMapper.deleteHwSignageInformationByIdentificationTagIds(identificationTagIds);
    }

    /**
     * 删除标识标牌信息
     * 
     * @param identificationTagId 标识标牌主键
     * @return 结果
     */
    @Override
    public int deleteHwSignageInformationByIdentificationTagId(Long identificationTagId)
    {
        return hwSignageInformationMapper.deleteHwSignageInformationByIdentificationTagId(identificationTagId);
    }

    @Override
    public List<HwSignageInformation> selectHwSignageInformationListByRouteId(Long routeid) {
        return hwSignageInformationMapper.selectHwSignageInformationListByRouteId(routeid);
    }
}
