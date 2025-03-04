package com.hg.system.mapper;

import java.util.List;

import com.hg.system.domain.HwHistoryEvaluate;

/**
 * 从业单位及人员历史评价Mapper接口
 *
 * @author W-yf
 * @date 2023-03-18
 */
public interface HwHistoryEvaluateMapper {
    /**
     * 查询从业单位及人员历史评价
     *
     * @param evaluateId 从业单位及人员历史评价主键
     * @return 从业单位及人员历史评价
     */
    public HwHistoryEvaluate selectHwHistoryEvaluateByEvaluateId(Long evaluateId);

    /**
     * 查询从业单位及人员历史评价列表
     *
     * @param hwHistoryEvaluate 从业单位及人员历史评价
     * @return 从业单位及人员历史评价集合
     */
    public List<HwHistoryEvaluate> selectHwHistoryEvaluateList(HwHistoryEvaluate hwHistoryEvaluate);

    /**
     * 新增从业单位及人员历史评价
     *
     * @param hwHistoryEvaluate 从业单位及人员历史评价
     * @return 结果
     */
    public int insertHwHistoryEvaluate(HwHistoryEvaluate hwHistoryEvaluate);

    /**
     * 修改从业单位及人员历史评价
     *
     * @param hwHistoryEvaluate 从业单位及人员历史评价
     * @return 结果
     */
    public int updateHwHistoryEvaluate(HwHistoryEvaluate hwHistoryEvaluate);

    /**
     * 删除从业单位及人员历史评价
     *
     * @param evaluateId 从业单位及人员历史评价主键
     * @return 结果
     */
    public int deleteHwHistoryEvaluateByEvaluateId(Long evaluateId);

    /**
     * 批量删除从业单位及人员历史评价
     *
     * @param evaluateIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHwHistoryEvaluateByEvaluateIds(Long[] evaluateIds);

    /**
     * 查询从业单位及人员历史评价根据单位id
     *
     * @param evaluateUnitId 从业单位及人员历史评价主键
     * @return 从业单位及人员历史评价
     */
    public List<HwHistoryEvaluate> selectHwHistoryEvaluateByUnitId(Integer evaluateUnitId);

    /**
     * 查询从业单位及人员历史评价根据单位id仅查最新
     *
     * @param evaluateUnitId 从业单位及人员历史评价主键
     * @return 从业单位及人员历史评价
     */
    HwHistoryEvaluate selectHwHistoryEvaluateByUnitIdByNew(Integer evaluateUnitId);

    /**
     * 查询从业单位及人员历史评价根据从业人员id
     *
     * @param evaluatePersonId 从业人员历史评价主键
     * @return 从业单位及人员历史评价
     */
    public List<HwHistoryEvaluate> selectHwHistoryEvaluateByPersonId(Integer evaluatePersonId);

    /**
     * 查询从业单位及人员历史评价根据从业人员id仅查最新
     *
     * @param evaluatePersonId 从业人员历史评价主键
     * @return 从业单位及人员历史评价
     */
    HwHistoryEvaluate selectHwHistoryEvaluateByPersonIdByNew(Integer evaluatePersonId);
}
