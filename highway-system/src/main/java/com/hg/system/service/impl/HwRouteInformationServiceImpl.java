package com.hg.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hg.system.mapper.HwRouteInformationMapper;
import com.hg.system.domain.HwRouteInformation;
import com.hg.system.service.IHwRouteInformationService;

/**
 * 路线列表Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-07
 */
@Service
public class HwRouteInformationServiceImpl implements IHwRouteInformationService 
{
    @Autowired
    private HwRouteInformationMapper hwRouteInformationMapper;

    /**
     * 查询路线列表
     * 
     * @param id 路线列表主键
     * @return 路线列表
     */
    @Override
    public HwRouteInformation selectHwRouteInformationById(Long id)
    {
        return hwRouteInformationMapper.selectHwRouteInformationById(id);
    }

    /**
     * 查询路线列表列表
     * 
     * @param hwRouteInformation 路线列表
     * @return 路线列表
     */
    @Override
    public List<HwRouteInformation> selectHwRouteInformationList(HwRouteInformation hwRouteInformation)
    {
        return hwRouteInformationMapper.selectHwRouteInformationList(hwRouteInformation);
    }

    /**
     * 新增路线列表
     * 
     * @param hwRouteInformation 路线列表
     * @return 结果
     */
    @Override
    public int insertHwRouteInformation(HwRouteInformation hwRouteInformation)
    {
        return hwRouteInformationMapper.insertHwRouteInformation(hwRouteInformation);
    }

    /**
     * 修改路线列表
     * 
     * @param hwRouteInformation 路线列表
     * @return 结果
     */
    @Override
    public int updateHwRouteInformation(HwRouteInformation hwRouteInformation)
    {
        return hwRouteInformationMapper.updateHwRouteInformation(hwRouteInformation);
    }

    /**
     * 批量删除路线列表
     * 
     * @param ids 需要删除的路线列表主键
     * @return 结果
     */
    @Override
    public int deleteHwRouteInformationByIds(Long[] ids)
    {
        return hwRouteInformationMapper.deleteHwRouteInformationByIds(ids);
    }

    /**
     * 删除路线列表信息
     * 
     * @param id 路线列表主键
     * @return 结果
     */
    @Override
    public int deleteHwRouteInformationById(Long id)
    {
        return hwRouteInformationMapper.deleteHwRouteInformationById(id);
    }
}
