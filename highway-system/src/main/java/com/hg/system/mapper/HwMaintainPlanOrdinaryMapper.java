package com.hg.system.mapper;

import com.hg.system.domain.HwMaintainPlanOrdinary;

import java.util.List;

/**
 * 一般养护Mapper接口
 * 
 * @author ruoyi
 * @date 2023-03-14
 */
public interface HwMaintainPlanOrdinaryMapper 
{
    /**
     * 查询一般养护
     * 
     * @param mpoid 一般养护主键
     * @return 一般养护
     */
    public HwMaintainPlanOrdinary selectHwMaintainPlanOrdinaryByMpoid(Long mpoid);

    /**
     * 查询一般养护列表
     * 
     * @param hwMaintainPlanOrdinary 一般养护
     * @return 一般养护集合
     */
    public List<HwMaintainPlanOrdinary> selectHwMaintainPlanOrdinaryList(HwMaintainPlanOrdinary hwMaintainPlanOrdinary);

    /**
     * 新增一般养护
     * 
     * @param hwMaintainPlanOrdinary 一般养护
     * @return 结果
     */
    public int insertHwMaintainPlanOrdinary(HwMaintainPlanOrdinary hwMaintainPlanOrdinary);

    /**
     * 修改一般养护
     * 
     * @param hwMaintainPlanOrdinary 一般养护
     * @return 结果
     */
    public int updateHwMaintainPlanOrdinary(HwMaintainPlanOrdinary hwMaintainPlanOrdinary);

    /**
     * 删除一般养护
     * 
     * @param mpoid 一般养护主键
     * @return 结果
     */
    public int deleteHwMaintainPlanOrdinaryByMpoid(Long mpoid);

    /**
     * 批量删除一般养护
     * 
     * @param mpoids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHwMaintainPlanOrdinaryByMpoids(Long[] mpoids);
}
