package com.hg.system.mapper;

import java.util.List;
import com.hg.system.domain.HwPersonEvaluate;

/**
 * 从业人员评价Mapper接口
 * 
 * @author W-yf
 * @date 2023-03-07
 */
public interface HwPersonEvaluateMapper 
{
    /**
     * 查询从业人员评价
     * 
     * @param personId 从业人员评价主键
     * @return 从业人员评价
     */
    public HwPersonEvaluate selectHwPersonEvaluateByPersonId(Long personId);

    /**
     * 查询从业人员评价列表
     * 
     * @param hwPersonEvaluate 从业人员评价
     * @return 从业人员评价集合
     */
    public List<HwPersonEvaluate> selectHwPersonEvaluateList(HwPersonEvaluate hwPersonEvaluate);

    /**
     * 新增从业人员评价
     * 
     * @param hwPersonEvaluate 从业人员评价
     * @return 结果
     */
    public int insertHwPersonEvaluate(HwPersonEvaluate hwPersonEvaluate);

    /**
     * 修改从业人员评价
     * 
     * @param hwPersonEvaluate 从业人员评价
     * @return 结果
     */
    public int updateHwPersonEvaluate(HwPersonEvaluate hwPersonEvaluate);

    /**
     * 删除从业人员评价
     * 
     * @param personId 从业人员评价主键
     * @return 结果
     */
    public int deleteHwPersonEvaluateByPersonId(Long personId);

    /**
     * 批量删除从业人员评价
     * 
     * @param personIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHwPersonEvaluateByPersonIds(Long[] personIds);

    /**
     * 查询从业人员评价列表新版
     *
     * @param hwPersonEvaluate 从业人员评价
     * @return 从业人员评价集合
     */
    public List<HwPersonEvaluate> selectHwPersonEvaluateListNew(HwPersonEvaluate hwPersonEvaluate);
}
