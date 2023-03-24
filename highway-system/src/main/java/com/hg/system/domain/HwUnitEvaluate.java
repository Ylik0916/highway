package com.hg.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hg.common.annotation.Excel;
import com.hg.common.core.domain.BaseEntity;

/**
 * 从业单位评价对象 hw_unit_evaluate
 *
 * @author W-yf
 * @date 2023-03-15
 */
public class HwUnitEvaluate extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 单位id
     */
    private Integer unitId;

    /**
     * 单位名称
     */
    @Excel(name = "单位名称")
    private String unitName;

    /**
     * 单位类型
     */
    @Excel(name = "单位类型")
    private String unitType;

    /**
     * 评价年度
     */
    @Excel(name = "评价年度")
    private String evaluateYears;

    /**
     * 历史评价
     */
    private HwHistoryEvaluate hwHistoryEvaluate;

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setEvaluateYears(String evaluateYears) {
        this.evaluateYears = evaluateYears;
    }

    public String getEvaluateYears() {
        return evaluateYears;
    }

    public HwHistoryEvaluate getHwHistoryEvaluate() {
        return hwHistoryEvaluate;
    }

    public void setHwHistoryEvaluate(HwHistoryEvaluate hwHistoryEvaluate) {
        this.hwHistoryEvaluate = hwHistoryEvaluate;
    }

    @Override
    public String toString() {
        return "HwUnitEvaluate{" +
                "unitId=" + unitId +
                ", unitName='" + unitName + '\'' +
                ", unitType='" + unitType + '\'' +
                ", evaluateYears='" + evaluateYears + '\'' +
                ", hwHistoryEvaluate=" + hwHistoryEvaluate +
                '}';
    }
}
