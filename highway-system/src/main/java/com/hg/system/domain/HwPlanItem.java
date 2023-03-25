package com.hg.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hg.common.annotation.Excel;
import com.hg.common.core.domain.BaseEntity;

/**
 * 进度对象 hw_plan_item
 * 
 * @author Cxh
 * @date 2023-03-17
 */
public class HwPlanItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 进度id */
    private Long id;

    /** 路线编码 */
    @Excel(name = "路线编码")
    private String encoding;

    /** 路线名称 */
    @Excel(name = "路线名称")
    private String name;

    /** 路线全长(公里) */
    @Excel(name = "路线全长(公里)")
    private String length;

    /** 管养单位 */
    @Excel(name = "管养单位")
    private String custodyUnit;

    /** 行政区域 */
    @Excel(name = "行政区域")
    private String region;

    /** 待建里程(公里) */
    @Excel(name = "待建里程(公里)")
    private Long unfinished;

    /** 技术等级 */
    @Excel(name = "技术等级", dictType = "technical_grade")
    private Integer level;

    /** 计划修建路面类型 */
    @Excel(name = "计划修建路面类型", dictType = "constructed_type")
    private Integer constructedType;

    /** 路面宽度 */
    @Excel(name = "路面宽度")
    private Long surfaceWidth;

    /** 路基宽度 */
    @Excel(name = "路基宽度")
    private Long roadbedWidth;

    /** 起点桩号 */
    @Excel(name = "起点桩号")
    private String startingNumber;

    /** 终点桩号 */
    @Excel(name = "终点桩号")
    private String terminusNumber;

    /** 国省补助 */
    @Excel(name = "国省补助")
    private Long nationalProvinceSubsidy;

    /** 市州投资 */
    @Excel(name = "市州投资")
    private Long municipalityInvest;

    /** 县区自筹 */
    @Excel(name = "县区自筹")
    private Long countySelfFund;

    /** 批复资金 */
    @Excel(name = "批复资金")
    private Long replyCapital;

    /** 合同资金 */
    @Excel(name = "合同资金")
    private Long contractCapital;

    /** 未开工原因 */
    @Excel(name = "未开工原因")
    private String notInServiceCause;

    /** 进展进度 */
    @Excel(name = "进展进度")
    private String progress;

    /** 批复/整合文件文号 */
    @Excel(name = "批复/整合文件文号")
    private String replyFileNumber;

    /** 责任单位 */
    @Excel(name = "责任单位")
    private String accountabilityUnit;

    /** 责任人 */
    @Excel(name = "责任人")
    private String personInCharge;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    /** 开工年限 */
    @Excel(name = "开工年限", dictType = "plan_year")
    private Integer startWorkYear;

    /** 完工年限 */
    @Excel(name = "完工年限", dictType = "plan_year")
    private Integer completionYear;

    /** 施工图设计是否完成 */
    @Excel(name = "施工图设计是否完成", dictType = "item_true_false")
    private String productionDrawing;

    /** 施工图审批是否完成 */
    @Excel(name = "施工图审批是否完成", dictType = "item_true_false")
    private String productionDrawingApproval;

    /** 施工招投标是否完成 */
    @Excel(name = "施工招投标是否完成", dictType = "item_true_false")
    private String bidWhetherCompletion;

    /** 备注 */
    @Excel(name = "备注")
    private String postscript;

    /** 年度计划ID */
    @Excel(name = "年度计划ID")
    private Long planId;

    /** 病害内容 */
    @Excel(name = "病害内容")
    private String disease;

    /** 明细状态 */
    @Excel(name = "明细状态", dictType = "detail_scale")
    private Integer detailScale;

    /** 所属年度计划名称 */
    @Excel(name = "所属年度计划名称")
    private String planName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getCustodyUnit() {
        return custodyUnit;
    }

    public void setCustodyUnit(String custodyUnit) {
        this.custodyUnit = custodyUnit;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Long getUnfinished() {
        return unfinished;
    }

    public void setUnfinished(Long unfinished) {
        this.unfinished = unfinished;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getConstructedType() {
        return constructedType;
    }

    public void setConstructedType(Integer constructedType) {
        this.constructedType = constructedType;
    }

    public Long getSurfaceWidth() {
        return surfaceWidth;
    }

    public void setSurfaceWidth(Long surfaceWidth) {
        this.surfaceWidth = surfaceWidth;
    }

    public Long getRoadbedWidth() {
        return roadbedWidth;
    }

    public void setRoadbedWidth(Long roadbedWidth) {
        this.roadbedWidth = roadbedWidth;
    }

    public String getStartingNumber() {
        return startingNumber;
    }

    public void setStartingNumber(String startingNumber) {
        this.startingNumber = startingNumber;
    }

    public String getTerminusNumber() {
        return terminusNumber;
    }

    public void setTerminusNumber(String terminusNumber) {
        this.terminusNumber = terminusNumber;
    }

    public Long getNationalProvinceSubsidy() {
        return nationalProvinceSubsidy;
    }

    public void setNationalProvinceSubsidy(Long nationalProvinceSubsidy) {
        this.nationalProvinceSubsidy = nationalProvinceSubsidy;
    }

    public Long getMunicipalityInvest() {
        return municipalityInvest;
    }

    public void setMunicipalityInvest(Long municipalityInvest) {
        this.municipalityInvest = municipalityInvest;
    }

    public Long getCountySelfFund() {
        return countySelfFund;
    }

    public void setCountySelfFund(Long countySelfFund) {
        this.countySelfFund = countySelfFund;
    }

    public Long getReplyCapital() {
        return replyCapital;
    }

    public void setReplyCapital(Long replyCapital) {
        this.replyCapital = replyCapital;
    }

    public Long getContractCapital() {
        return contractCapital;
    }

    public void setContractCapital(Long contractCapital) {
        this.contractCapital = contractCapital;
    }

    public String getNotInServiceCause() {
        return notInServiceCause;
    }

    public void setNotInServiceCause(String notInServiceCause) {
        this.notInServiceCause = notInServiceCause;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getReplyFileNumber() {
        return replyFileNumber;
    }

    public void setReplyFileNumber(String replyFileNumber) {
        this.replyFileNumber = replyFileNumber;
    }

    public String getAccountabilityUnit() {
        return accountabilityUnit;
    }

    public void setAccountabilityUnit(String accountabilityUnit) {
        this.accountabilityUnit = accountabilityUnit;
    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStartWorkYear() {
        return startWorkYear;
    }

    public void setStartWorkYear(Integer startWorkYear) {
        this.startWorkYear = startWorkYear;
    }

    public Integer getCompletionYear() {
        return completionYear;
    }

    public void setCompletionYear(Integer completionYear) {
        this.completionYear = completionYear;
    }

    public String getProductionDrawing() {
        return productionDrawing;
    }

    public void setProductionDrawing(String productionDrawing) {
        this.productionDrawing = productionDrawing;
    }

    public String getProductionDrawingApproval() {
        return productionDrawingApproval;
    }

    public void setProductionDrawingApproval(String productionDrawingApproval) {
        this.productionDrawingApproval = productionDrawingApproval;
    }

    public String getBidWhetherCompletion() {
        return bidWhetherCompletion;
    }

    public void setBidWhetherCompletion(String bidWhetherCompletion) {
        this.bidWhetherCompletion = bidWhetherCompletion;
    }

    public String getPostscript() {
        return postscript;
    }

    public void setPostscript(String postscript) {
        this.postscript = postscript;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public Integer getDetailScale() {
        return detailScale;
    }

    public void setDetailScale(Integer detailScale) {
        this.detailScale = detailScale;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    @Override
    public String toString() {
        return "HwPlanItem{" +
                "id=" + id +
                ", encoding='" + encoding + '\'' +
                ", name='" + name + '\'' +
                ", length='" + length + '\'' +
                ", custodyUnit='" + custodyUnit + '\'' +
                ", region='" + region + '\'' +
                ", unfinished=" + unfinished +
                ", level=" + level +
                ", constructedType=" + constructedType +
                ", surfaceWidth=" + surfaceWidth +
                ", roadbedWidth=" + roadbedWidth +
                ", startingNumber='" + startingNumber + '\'' +
                ", terminusNumber='" + terminusNumber + '\'' +
                ", nationalProvinceSubsidy=" + nationalProvinceSubsidy +
                ", municipalityInvest=" + municipalityInvest +
                ", countySelfFund=" + countySelfFund +
                ", replyCapital=" + replyCapital +
                ", contractCapital=" + contractCapital +
                ", notInServiceCause='" + notInServiceCause + '\'' +
                ", progress='" + progress + '\'' +
                ", replyFileNumber='" + replyFileNumber + '\'' +
                ", accountabilityUnit='" + accountabilityUnit + '\'' +
                ", personInCharge='" + personInCharge + '\'' +
                ", phone='" + phone + '\'' +
                ", startWorkYear=" + startWorkYear +
                ", completionYear=" + completionYear +
                ", productionDrawing='" + productionDrawing + '\'' +
                ", productionDrawingApproval='" + productionDrawingApproval + '\'' +
                ", bidWhetherCompletion='" + bidWhetherCompletion + '\'' +
                ", postscript='" + postscript + '\'' +
                ", planId=" + planId +
                ", disease='" + disease + '\'' +
                ", detailScale=" + detailScale +
                ", planName='" + planName + '\'' +
                '}';
    }
}
