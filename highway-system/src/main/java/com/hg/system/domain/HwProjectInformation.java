package com.hg.system.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hg.common.annotation.Excel;
import com.hg.common.core.domain.BaseEntity;

/**
 * 项目信息管理对象 hw_project_information
 *
 * @author W-yf
 * @date 2023-03-07
 */
public class HwProjectInformation extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 项目id
     */
    private Long projectId;

    /**
     * 项目名称
     */
    @Excel(name = "项目名称")
    private String projectName;

    /**
     * 行政区域
     */
    @Excel(name = "行政区域")
    private Integer administrativeRegion;

    /**
     * 路线信息==》路线编号（路线编码）
     */
    @Excel(name = "路线信息==》路线编号", readConverterExp = "路线编码")
    private Long routeCoding;

    /**
     * 路线信息==》路线名称
     */
    @Excel(name = "路线信息==》路线名称")
    private String routeName;

    /**
     * 项目阶段
     */
    @Excel(name = "项目阶段")
    private Integer projectPhase;

    /**
     * 开工时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开工时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 交工时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交工时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    /**
     * 竣工时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "竣工时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 投资估算==》建安费，单位：万元
     */
    @Excel(name = "投资估算==》建安费，单位：万元")
    private Long investCost;

    /**
     * 投资估算==》设备费，单位：万元
     */
    @Excel(name = "投资估算==》设备费，单位：万元")
    private Long investEquipmentCost;

    /**
     * 投资估算==》工程费，单位：万元
     */
    @Excel(name = "投资估算==》工程费，单位：万元")
    private Long investEngineeringCost;

    /**
     * 设计概算==》建安费，单位：万元
     */
    @Excel(name = "设计概算==》建安费，单位：万元")
    private Long designCost;

    /**
     * 设计概算==》设备费，单位：万元
     */
    @Excel(name = "设计概算==》设备费，单位：万元")
    private Long designEquipmentCost;

    /**
     * 设计概算==》工程费，单位：万元
     */
    @Excel(name = "设计概算==》工程费，单位：万元")
    private Long designEngineeringCost;

    /**
     * 施工预算==》建安费，单位：万元
     */
    @Excel(name = "施工预算==》建安费，单位：万元")
    private Long constructionCost;

    /**
     * 施工预算==》设备费，单位：万元
     */
    @Excel(name = "施工预算==》设备费，单位：万元")
    private Long constructionEquipmentCost;

    /**
     * 施工预算==》工程费，单位：万元
     */
    @Excel(name = "施工预算==》工程费，单位：万元")
    private Long constructionEngineeringCost;

    /**
     * 招标费用==》合同价，单位：万元，不含暂定金
     */
    @Excel(name = "招标费用==》合同价，单位：万元，不含暂定金")
    private Long contractCost;

    /**
     * 招标费用==》暂定金，单位：万元
     */
    @Excel(name = "招标费用==》暂定金，单位：万元")
    private Long provisionalPayment;

    /**
     * 关联监理标段实体
     */
    private HwProjectSupervisor hwProjectSupervisor;

    /**
     * 关联施工标段
     */
    private HwProjectConstruction hwProjectConstruction;

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setAdministrativeRegion(Integer administrativeRegion) {
        this.administrativeRegion = administrativeRegion;
    }

    public Integer getAdministrativeRegion() {
        return administrativeRegion;
    }

    public void setRouteCoding(Long routeCoding) {
        this.routeCoding = routeCoding;
    }

    public Long getRouteCoding() {
        return routeCoding;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setProjectPhase(Integer projectPhase) {
        this.projectPhase = projectPhase;
    }

    public Integer getProjectPhase() {
        return projectPhase;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setInvestCost(Long investCost) {
        this.investCost = investCost;
    }

    public Long getInvestCost() {
        return investCost;
    }

    public void setInvestEquipmentCost(Long investEquipmentCost) {
        this.investEquipmentCost = investEquipmentCost;
    }

    public Long getInvestEquipmentCost() {
        return investEquipmentCost;
    }

    public void setInvestEngineeringCost(Long investEngineeringCost) {
        this.investEngineeringCost = investEngineeringCost;
    }

    public Long getInvestEngineeringCost() {
        return investEngineeringCost;
    }

    public void setDesignCost(Long designCost) {
        this.designCost = designCost;
    }

    public Long getDesignCost() {
        return designCost;
    }

    public void setDesignEquipmentCost(Long designEquipmentCost) {
        this.designEquipmentCost = designEquipmentCost;
    }

    public Long getDesignEquipmentCost() {
        return designEquipmentCost;
    }

    public void setDesignEngineeringCost(Long designEngineeringCost) {
        this.designEngineeringCost = designEngineeringCost;
    }

    public Long getDesignEngineeringCost() {
        return designEngineeringCost;
    }

    public void setConstructionCost(Long constructionCost) {
        this.constructionCost = constructionCost;
    }

    public Long getConstructionCost() {
        return constructionCost;
    }

    public void setConstructionEquipmentCost(Long constructionEquipmentCost) {
        this.constructionEquipmentCost = constructionEquipmentCost;
    }

    public Long getConstructionEquipmentCost() {
        return constructionEquipmentCost;
    }

    public void setConstructionEngineeringCost(Long constructionEngineeringCost) {
        this.constructionEngineeringCost = constructionEngineeringCost;
    }

    public Long getConstructionEngineeringCost() {
        return constructionEngineeringCost;
    }

    public void setContractCost(Long contractCost) {
        this.contractCost = contractCost;
    }

    public Long getContractCost() {
        return contractCost;
    }

    public void setProvisionalPayment(Long provisionalPayment) {
        this.provisionalPayment = provisionalPayment;
    }

    public Long getProvisionalPayment() {
        return provisionalPayment;
    }

    public HwProjectSupervisor getHwProjectSupervisor() {
        return hwProjectSupervisor;
    }

    public void setHwProjectSupervisor(HwProjectSupervisor hwProjectSupervisor) {
        this.hwProjectSupervisor = hwProjectSupervisor;
    }

    public HwProjectConstruction getHwProjectConstruction() {
        return hwProjectConstruction;
    }

    public void setHwProjectConstruction(HwProjectConstruction hwProjectConstruction) {
        this.hwProjectConstruction = hwProjectConstruction;
    }

    @Override
    public String toString() {
        return "HwProjectInformation{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", administrativeRegion=" + administrativeRegion +
                ", routeCoding=" + routeCoding +
                ", routeName='" + routeName + '\'' +
                ", projectPhase=" + projectPhase +
                ", startTime=" + startTime +
                ", submitTime=" + submitTime +
                ", endTime=" + endTime +
                ", investCost=" + investCost +
                ", investEquipmentCost=" + investEquipmentCost +
                ", investEngineeringCost=" + investEngineeringCost +
                ", designCost=" + designCost +
                ", designEquipmentCost=" + designEquipmentCost +
                ", designEngineeringCost=" + designEngineeringCost +
                ", constructionCost=" + constructionCost +
                ", constructionEquipmentCost=" + constructionEquipmentCost +
                ", constructionEngineeringCost=" + constructionEngineeringCost +
                ", contractCost=" + contractCost +
                ", provisionalPayment=" + provisionalPayment +
                ", hwProjectSupervisor=" + hwProjectSupervisor +
                ", hwProjectConstruction=" + hwProjectConstruction +
                '}';
    }
}
