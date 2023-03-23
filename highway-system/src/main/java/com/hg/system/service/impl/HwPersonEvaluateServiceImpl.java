package com.hg.system.service.impl;

import java.util.List;

import com.hg.system.domain.HwHistoryEvaluate;
import com.hg.system.mapper.HwHistoryEvaluateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hg.system.mapper.HwPersonEvaluateMapper;
import com.hg.system.domain.HwPersonEvaluate;
import com.hg.system.service.IHwPersonEvaluateService;

/**
 * 从业人员评价Service业务层处理
 *
 * @author W-yf
 * @date 2023-03-07
 */
@Service
public class HwPersonEvaluateServiceImpl implements IHwPersonEvaluateService {
    @Autowired
    private HwPersonEvaluateMapper hwPersonEvaluateMapper;

    @Autowired
    private HwHistoryEvaluateMapper hwHistoryEvaluateMapper;

    /**
     * 查询从业人员评价
     *
     * @param personId 从业人员评价主键
     * @return 从业人员评价
     */
    @Override
    public HwPersonEvaluate selectHwPersonEvaluateByPersonId(Long personId) {
        return hwPersonEvaluateMapper.selectHwPersonEvaluateByPersonId(personId);
    }

    /**
     * 查询从业人员评价列表
     *
     * @param hwPersonEvaluate 从业人员评价
     * @return 从业人员评价
     */
    @Override
    public List<HwPersonEvaluate> selectHwPersonEvaluateList(HwPersonEvaluate hwPersonEvaluate) {
        return hwPersonEvaluateMapper.selectHwPersonEvaluateList(hwPersonEvaluate);
    }

    /**
     * 新增从业人员评价
     *
     * @param hwPersonEvaluate 从业人员评价
     * @return 结果
     */
    @Override
    public int insertHwPersonEvaluate(HwPersonEvaluate hwPersonEvaluate) {
        return hwPersonEvaluateMapper.insertHwPersonEvaluate(hwPersonEvaluate);
    }

    /**
     * 修改从业人员评价
     *
     * @param hwPersonEvaluate 从业人员评价
     * @return 结果
     */
    @Override
    public int updateHwPersonEvaluate(HwPersonEvaluate hwPersonEvaluate) {
        return hwPersonEvaluateMapper.updateHwPersonEvaluate(hwPersonEvaluate);
    }

    /**
     * 批量删除从业人员评价
     *
     * @param personIds 需要删除的从业人员评价主键
     * @return 结果
     */
    @Override
    public int deleteHwPersonEvaluateByPersonIds(Long[] personIds) {
        return hwPersonEvaluateMapper.deleteHwPersonEvaluateByPersonIds(personIds);
    }

    /**
     * 删除从业人员评价信息
     *
     * @param personId 从业人员评价主键
     * @return 结果
     */
    @Override
    public int deleteHwPersonEvaluateByPersonId(Long personId) {
        return hwPersonEvaluateMapper.deleteHwPersonEvaluateByPersonId(personId);
    }

    /**
     * 查询从业人员评价列表新版
     *
     * @param hwPersonEvaluate 从业人员评价
     * @return 从业人员评价集合
     */
    @Override
    public List<HwPersonEvaluate> selectHwPersonEvaluateListNew(HwPersonEvaluate hwPersonEvaluate) {
        List<HwPersonEvaluate> evaluateList = hwPersonEvaluateMapper.selectHwPersonEvaluateListNew(hwPersonEvaluate);
        for (int i = 0; i < evaluateList.size(); i++) {
            HwPersonEvaluate evaluate = evaluateList.get(i);
            Integer personId = evaluate.getPersonId();
            HwHistoryEvaluate historyEvaluate = hwHistoryEvaluateMapper.selectHwHistoryEvaluateByPersonIdByNew(personId);
            evaluate.setHwHistoryEvaluate(historyEvaluate);
        }
        return evaluateList;
    }
}
