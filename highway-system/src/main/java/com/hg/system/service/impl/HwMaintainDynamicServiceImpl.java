package com.hg.system.service.impl;

import java.util.List;

import com.hg.system.domain.HwMaintainDynamic;
import com.hg.system.mapper.HwMaintainDynamicMapper;
import com.hg.system.service.IHwMaintainDynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 动态养护管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-07
 */
@Service
public class HwMaintainDynamicServiceImpl implements IHwMaintainDynamicService
{
    @Autowired
    private HwMaintainDynamicMapper hwMaintainDynamicMapper;

    /**
     * 查询动态养护管理
     * 
     * @param mdid 动态养护管理主键
     * @return 动态养护管理
     */
    @Override
    public HwMaintainDynamic selectHwMaintainDynamicByMdid(Long mdid)
    {
        return hwMaintainDynamicMapper.selectHwMaintainDynamicByMdid(mdid);
    }

    /**
     * 查询动态养护管理列表
     * 
     * @param hwMaintainDynamic 动态养护管理
     * @return 动态养护管理
     */
    @Override
    public List<HwMaintainDynamic> selectHwMaintainDynamicList(HwMaintainDynamic hwMaintainDynamic)
    {
        return hwMaintainDynamicMapper.selectHwMaintainDynamicList(hwMaintainDynamic);
    }

    /**
     * 新增动态养护管理
     * 
     * @param hwMaintainDynamic 动态养护管理
     * @return 结果
     */
    @Override
    public int insertHwMaintainDynamic(HwMaintainDynamic hwMaintainDynamic)
    {
        return hwMaintainDynamicMapper.insertHwMaintainDynamic(hwMaintainDynamic);
    }

    /**
     * 修改动态养护管理
     * 
     * @param hwMaintainDynamic 动态养护管理
     * @return 结果
     */
    @Override
    public int updateHwMaintainDynamic(HwMaintainDynamic hwMaintainDynamic)
    {
        return hwMaintainDynamicMapper.updateHwMaintainDynamic(hwMaintainDynamic);
    }

    /**
     * 批量删除动态养护管理
     * 
     * @param mdids 需要删除的动态养护管理主键
     * @return 结果
     */
    @Override
    public int deleteHwMaintainDynamicByMdids(Long[] mdids)
    {
        return hwMaintainDynamicMapper.deleteHwMaintainDynamicByMdids(mdids);
    }

    /**
     * 删除动态养护管理信息
     * 
     * @param mdid 动态养护管理主键
     * @return 结果
     */
    @Override
    public int deleteHwMaintainDynamicByMdid(Long mdid)
    {
        return hwMaintainDynamicMapper.deleteHwMaintainDynamicByMdid(mdid);
    }
}
