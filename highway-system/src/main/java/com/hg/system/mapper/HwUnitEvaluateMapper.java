package com.hg.system.mapper;

import java.util.List;
import com.hg.system.domain.HwUnitEvaluate;

/**
 * 从业单位评价Mapper接口
 * 
 * @author W-yf
 * @date 2023-03-15
 */
public interface HwUnitEvaluateMapper 
{
    /**
     * 查询从业单位评价
     * 
     * @param unitId 从业单位评价主键
     * @return 从业单位评价
     */
    public HwUnitEvaluate selectHwUnitEvaluateByUnitId(Long unitId);

    /**
     * 查询从业单位评价列表
     * 
     * @param hwUnitEvaluate 从业单位评价
     * @return 从业单位评价集合
     */
    public List<HwUnitEvaluate> selectHwUnitEvaluateList(HwUnitEvaluate hwUnitEvaluate);

    /**
     * 新增从业单位评价
     * 
     * @param hwUnitEvaluate 从业单位评价
     * @return 结果
     */
    public int insertHwUnitEvaluate(HwUnitEvaluate hwUnitEvaluate);

    /**
     * 修改从业单位评价
     * 
     * @param hwUnitEvaluate 从业单位评价
     * @return 结果
     */
    public int updateHwUnitEvaluate(HwUnitEvaluate hwUnitEvaluate);

    /**
     * 删除从业单位评价
     * 
     * @param unitId 从业单位评价主键
     * @return 结果
     */
    public int deleteHwUnitEvaluateByUnitId(Long unitId);

    /**
     * 批量删除从业单位评价
     * 
     * @param unitIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHwUnitEvaluateByUnitIds(Long[] unitIds);
}
