package com.hg.system.service.impl;

import com.hg.system.domain.HwSectionInformation;
import com.hg.system.mapper.HwSectionInformationMapper;
import com.hg.system.service.IHwSectionInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 路段信息Service业务层处理
 *
 * @author ruoyi
 * @date 2023-03-13
 */
@Service
public class HwSectionInformationServiceImpl implements IHwSectionInformationService
{
    @Autowired
    private HwSectionInformationMapper hwSectionInformationMapper;

    /**
     * 查询路段信息
     *
     * @param roadsectionid 路段信息主键
     * @return 路段信息
     */
    @Override
    public HwSectionInformation selectHwSectionInformationByRoadsectionid(Long roadsectionid)
    {
        return hwSectionInformationMapper.selectHwSectionInformationByRoadsectionid(roadsectionid);
    }

    /**
     * 查询路段信息列表
     *
     * @param hwSectionInformation 路段信息
     * @return 路段信息
     */
    @Override
    public List<HwSectionInformation> selectHwSectionInformationList(HwSectionInformation hwSectionInformation)
    {
        return hwSectionInformationMapper.selectHwSectionInformationList(hwSectionInformation);
    }

    /**
     * 新增路段信息
     *
     * @param hwSectionInformation 路段信息
     * @return 结果
     */
    @Override
    public int insertHwSectionInformation(HwSectionInformation hwSectionInformation)
    {
        return hwSectionInformationMapper.insertHwSectionInformation(hwSectionInformation);
    }

    /**
     * 修改路段信息
     *
     * @param hwSectionInformation 路段信息
     * @return 结果
     */
    @Override
    public int updateHwSectionInformation(HwSectionInformation hwSectionInformation)
    {
        return hwSectionInformationMapper.updateHwSectionInformation(hwSectionInformation);
    }

    /**
     * 批量删除路段信息
     *
     * @param roadsectionids 需要删除的路段信息主键
     * @return 结果
     */
    @Override
    public int deleteHwSectionInformationByRoadsectionids(Long[] roadsectionids)
    {
        return hwSectionInformationMapper.deleteHwSectionInformationByRoadsectionids(roadsectionids);
    }

    /**
     * 删除路段信息信息
     *
     * @param roadsectionid 路段信息主键
     * @return 结果
     */
    @Override
    public int deleteHwSectionInformationByRoadsectionid(Long roadsectionid)
    {
        return hwSectionInformationMapper.deleteHwSectionInformationByRoadsectionid(roadsectionid);
    }
}
