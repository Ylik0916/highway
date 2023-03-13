package com.hg.system.service.impl;

import java.util.List;

import com.hg.system.domain.HwMaintainPlanDelicacy;
import com.hg.system.mapper.HwMaintainPlanDelicacyMapper;
import com.hg.system.service.IHwMaintainPlanDelicacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 精品示范Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-08
 */
@Service
public class HwMaintainPlanDelicacyServiceImpl implements IHwMaintainPlanDelicacyService
{
    @Autowired
    private HwMaintainPlanDelicacyMapper hwMaintainPlanDelicacyMapper;

    /**
     * 查询精品示范
     * 
     * @param mpdid 精品示范主键
     * @return 精品示范
     */
    @Override
    public HwMaintainPlanDelicacy selectHwMaintainPlanDelicacyByMpdid(Long mpdid)
    {
        return hwMaintainPlanDelicacyMapper.selectHwMaintainPlanDelicacyByMpdid(mpdid);
    }

    /**
     * 查询精品示范列表
     * 
     * @param hwMaintainPlanDelicacy 精品示范
     * @return 精品示范
     */
    @Override
    public List<HwMaintainPlanDelicacy> selectHwMaintainPlanDelicacyList(HwMaintainPlanDelicacy hwMaintainPlanDelicacy)
    {
        return hwMaintainPlanDelicacyMapper.selectHwMaintainPlanDelicacyList(hwMaintainPlanDelicacy);
    }

    /**
     * 新增精品示范
     * 
     * @param hwMaintainPlanDelicacy 精品示范
     * @return 结果
     */
    @Override
    public int insertHwMaintainPlanDelicacy(HwMaintainPlanDelicacy hwMaintainPlanDelicacy)
    {
        return hwMaintainPlanDelicacyMapper.insertHwMaintainPlanDelicacy(hwMaintainPlanDelicacy);
    }

    /**
     * 修改精品示范
     * 
     * @param hwMaintainPlanDelicacy 精品示范
     * @return 结果
     */
    @Override
    public int updateHwMaintainPlanDelicacy(HwMaintainPlanDelicacy hwMaintainPlanDelicacy)
    {
        return hwMaintainPlanDelicacyMapper.updateHwMaintainPlanDelicacy(hwMaintainPlanDelicacy);
    }

    /**
     * 批量删除精品示范
     * 
     * @param mpdids 需要删除的精品示范主键
     * @return 结果
     */
    @Override
    public int deleteHwMaintainPlanDelicacyByMpdids(Long[] mpdids)
    {
        return hwMaintainPlanDelicacyMapper.deleteHwMaintainPlanDelicacyByMpdids(mpdids);
    }

    /**
     * 删除精品示范信息
     * 
     * @param mpdid 精品示范主键
     * @return 结果
     */
    @Override
    public int deleteHwMaintainPlanDelicacyByMpdid(Long mpdid)
    {
        return hwMaintainPlanDelicacyMapper.deleteHwMaintainPlanDelicacyByMpdid(mpdid);
    }
}
