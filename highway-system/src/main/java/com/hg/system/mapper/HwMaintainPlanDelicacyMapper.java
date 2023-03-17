package com.hg.system.mapper;

import com.hg.system.domain.HwMaintainPlanDelicacy;

import java.util.List;


/**
 * 精品示范Mapper接口
 * 
 * @author ruoyi
 * @date 2023-03-08
 */
public interface HwMaintainPlanDelicacyMapper 
{
    /**
     * 查询精品示范
     * 
     * @param mpdid 精品示范主键
     * @return 精品示范
     */
    public HwMaintainPlanDelicacy selectHwMaintainPlanDelicacyByMpdid(Long mpdid);

    /**
     * 查询精品示范列表
     * 
     * @param hwMaintainPlanDelicacy 精品示范
     * @return 精品示范集合
     */
    public List<HwMaintainPlanDelicacy> selectHwMaintainPlanDelicacyList(HwMaintainPlanDelicacy hwMaintainPlanDelicacy);

    /**
     * 新增精品示范
     * 
     * @param hwMaintainPlanDelicacy 精品示范
     * @return 结果
     */
    public int insertHwMaintainPlanDelicacy(HwMaintainPlanDelicacy hwMaintainPlanDelicacy);

    /**
     * 修改精品示范
     * 
     * @param hwMaintainPlanDelicacy 精品示范
     * @return 结果
     */
    public int updateHwMaintainPlanDelicacy(HwMaintainPlanDelicacy hwMaintainPlanDelicacy);

    /**
     * 删除精品示范
     * 
     * @param mpdid 精品示范主键
     * @return 结果
     */
    public int deleteHwMaintainPlanDelicacyByMpdid(Long mpdid);

    /**
     * 批量删除精品示范
     * 
     * @param mpdids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHwMaintainPlanDelicacyByMpdids(Long[] mpdids);
}
