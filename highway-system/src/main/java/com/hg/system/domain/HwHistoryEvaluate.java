package com.hg.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hg.common.annotation.Excel;
import com.hg.common.core.domain.BaseEntity;

/**
 * 从业单位及人员历史评价对象 hw_history_evaluate
 * 
 * @author W-yf
 * @date 2023-03-18
 */
public class HwHistoryEvaluate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评价id */
    private Long evaluateId;

    /** 评价时间，自动生成 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "评价时间，自动生成", width = 30, dateFormat = "yyyy-MM-dd")
    private Date evaluateTime;

    /** 评价分数 */
    @Excel(name = "评价分数")
    private Long evaluateScore;

    /** 评价等级，根据评分自动规划等级 */
    @Excel(name = "评价等级，根据评分自动规划等级")
    private String evaluateGrade;

    /** 从业单位id */
    @Excel(name = "从业单位id")
    private Long evaluateUnitId;

    /** 从业人员id */
    @Excel(name = "从业人员id")
    private Long evaluatePersonId;

    /** 附件 */
    @Excel(name = "附件")
    private String evaluateDocumentUpload;

    public void setEvaluateId(Long evaluateId) 
    {
        this.evaluateId = evaluateId;
    }

    public Long getEvaluateId() 
    {
        return evaluateId;
    }
    public void setEvaluateTime(Date evaluateTime) 
    {
        this.evaluateTime = evaluateTime;
    }

    public Date getEvaluateTime() 
    {
        return evaluateTime;
    }
    public void setEvaluateScore(Long evaluateScore) 
    {
        this.evaluateScore = evaluateScore;
    }

    public Long getEvaluateScore() 
    {
        return evaluateScore;
    }
    public void setEvaluateGrade(String evaluateGrade) 
    {
        this.evaluateGrade = evaluateGrade;
    }

    public String getEvaluateGrade() 
    {
        return evaluateGrade;
    }
    public void setEvaluateUnitId(Long evaluateUnitId) 
    {
        this.evaluateUnitId = evaluateUnitId;
    }

    public Long getEvaluateUnitId() 
    {
        return evaluateUnitId;
    }
    public void setEvaluatePersonId(Long evaluatePersonId) 
    {
        this.evaluatePersonId = evaluatePersonId;
    }

    public Long getEvaluatePersonId() 
    {
        return evaluatePersonId;
    }
    public void setEvaluateDocumentUpload(String evaluateDocumentUpload) 
    {
        this.evaluateDocumentUpload = evaluateDocumentUpload;
    }

    public String getEvaluateDocumentUpload() 
    {
        return evaluateDocumentUpload;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("evaluateId", getEvaluateId())
            .append("evaluateTime", getEvaluateTime())
            .append("evaluateScore", getEvaluateScore())
            .append("evaluateGrade", getEvaluateGrade())
            .append("evaluateUnitId", getEvaluateUnitId())
            .append("evaluatePersonId", getEvaluatePersonId())
            .append("evaluateDocumentUpload", getEvaluateDocumentUpload())
            .toString();
    }
}
