package com.hg.system.service.impl;

import java.util.Date;
import java.util.List;

import com.hg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hg.system.mapper.HwHistoryEvaluateMapper;
import com.hg.system.domain.HwHistoryEvaluate;
import com.hg.system.service.IHwHistoryEvaluateService;

/**
 * 从业单位及人员历史评价Service业务层处理
 *
 * @author W-yf
 * @date 2023-03-18
 */
@Service
public class HwHistoryEvaluateServiceImpl implements IHwHistoryEvaluateService {
    @Autowired
    private HwHistoryEvaluateMapper hwHistoryEvaluateMapper;

    /**
     * 查询从业单位及人员历史评价
     *
     * @param evaluateId 从业单位及人员历史评价主键
     * @return 从业单位及人员历史评价
     */
    @Override
    public HwHistoryEvaluate selectHwHistoryEvaluateByEvaluateId(Long evaluateId) {
        return hwHistoryEvaluateMapper.selectHwHistoryEvaluateByEvaluateId(evaluateId);
    }

    /**
     * 查询从业单位及人员历史评价列表
     *
     * @param hwHistoryEvaluate 从业单位及人员历史评价
     * @return 从业单位及人员历史评价
     */
    @Override
    public List<HwHistoryEvaluate> selectHwHistoryEvaluateList(HwHistoryEvaluate hwHistoryEvaluate) {
        return hwHistoryEvaluateMapper.selectHwHistoryEvaluateList(hwHistoryEvaluate);
    }

    /**
     * 新增从业单位及人员历史评价
     *
     * @param hwHistoryEvaluate 从业单位及人员历史评价
     * @return 结果
     */
    @Override
    public int insertHwHistoryEvaluate(HwHistoryEvaluate hwHistoryEvaluate) {
        Date nowDate = DateUtils.getNowDate();
        hwHistoryEvaluate.setEvaluateTime(nowDate);
        String grade = null;
        Long score = hwHistoryEvaluate.getEvaluateScore();
        if (100 >= score && 95 <= score) {
            grade = "AA";
        } else if (90 <= score && 95 > score) {
            grade = "A";
        } else if (80 <= score && 90 > score) {
            grade = "B";
        } else if (70 <= score && 80 > score) {
            grade = "C";
        } else {
            grade = "D";
        }
        hwHistoryEvaluate.setEvaluateGrade(grade);
        return hwHistoryEvaluateMapper.insertHwHistoryEvaluate(hwHistoryEvaluate);
    }

    /**
     * 修改从业单位及人员历史评价
     *
     * @param hwHistoryEvaluate 从业单位及人员历史评价
     * @return 结果
     */
    @Override
    public int updateHwHistoryEvaluate(HwHistoryEvaluate hwHistoryEvaluate) {
        Date nowDate = DateUtils.getNowDate();
        hwHistoryEvaluate.setEvaluateTime(nowDate);
        String grade = null;
        Long score = hwHistoryEvaluate.getEvaluateScore();
        if (100 >= score && 95 <= score) {
            grade = "AA";
        } else if (90 <= score && 95 > score) {
            grade = "A";
        } else if (80 <= score && 90 > score) {
            grade = "B";
        } else if (70 <= score && 80 > score) {
            grade = "C";
        } else {
            grade = "D";
        }
        hwHistoryEvaluate.setEvaluateGrade(grade);
        return hwHistoryEvaluateMapper.updateHwHistoryEvaluate(hwHistoryEvaluate);
    }

    /**
     * 批量删除从业单位及人员历史评价
     *
     * @param evaluateIds 需要删除的从业单位及人员历史评价主键
     * @return 结果
     */
    @Override
    public int deleteHwHistoryEvaluateByEvaluateIds(Long[] evaluateIds) {
        return hwHistoryEvaluateMapper.deleteHwHistoryEvaluateByEvaluateIds(evaluateIds);
    }

    /**
     * 删除从业单位及人员历史评价信息
     *
     * @param evaluateId 从业单位及人员历史评价主键
     * @return 结果
     */
    @Override
    public int deleteHwHistoryEvaluateByEvaluateId(Long evaluateId) {
        return hwHistoryEvaluateMapper.deleteHwHistoryEvaluateByEvaluateId(evaluateId);
    }

    /**
     * 查询从业单位及人员历史评价根据单位id
     *
     * @param evaluateUnitId 从业单位及人员历史评价主键
     * @return 从业单位及人员历史评价
     */
    @Override
    public List<HwHistoryEvaluate> selectHwHistoryEvaluateByUnitId(Integer evaluateUnitId) {
        return hwHistoryEvaluateMapper.selectHwHistoryEvaluateByUnitId(evaluateUnitId);
    }

    /**
     * 查询从业单位及人员历史评价根据从业人员id
     *
     * @param evaluatePersonId 从业人员历史评价主键
     * @return 从业单位及人员历史评价
     */
    @Override
    public List<HwHistoryEvaluate> selectHwHistoryEvaluateByPersonId(Integer evaluatePersonId) {
        return hwHistoryEvaluateMapper.selectHwHistoryEvaluateByPersonId(evaluatePersonId);
    }
}
