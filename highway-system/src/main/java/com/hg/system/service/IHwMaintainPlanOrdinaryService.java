package com.hg.system.service;

import java.util.List;
import com.hg.system.domain.HwMaintainPlanOrdinary;

/**
 * 一般养护Service接口
 * 
 * @author ruoyi
 * @date 2023-03-14
 */
public interface IHwMaintainPlanOrdinaryService 
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
     * 批量删除一般养护
     * 
     * @param mpoids 需要删除的一般养护主键集合
     * @return 结果
     */
    public int deleteHwMaintainPlanOrdinaryByMpoids(Long[] mpoids);

    /**
     * 删除一般养护信息
     * 
     * @param mpoid 一般养护主键
     * @return 结果
     */
    public int deleteHwMaintainPlanOrdinaryByMpoid(Long mpoid);
}
