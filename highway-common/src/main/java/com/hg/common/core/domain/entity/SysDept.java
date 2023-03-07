package com.hg.common.core.domain.entity;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hg.common.core.domain.BaseEntity;

/**
 * 部门表 sys_dept
 * 
 * @author ruoyi
 */
public class SysDept extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 部门ID */
    private Long deptId;

    /** 父部门ID */
    private Long parentId;

    /** 祖级列表 */
    private String ancestors;

    /** 部门名称 */
    private String deptName;

    /** 显示顺序 */
    private Integer orderNum;

    /** 负责人 */
    private String leader;

    /** 联系电话 */
    private String phone;

    /** 邮箱 */
    private String email;

    /** 部门状态:0正常,1停用 */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 父部门名称 */
    private String parentName;

//    所属地形
    private String landForm;
//    行政编码
    private String postcode;
//    经度
    private String longitude;
//    纬度
    private String latitude;
//    优选通达路线名称
    private String priorityRoute;
//    优选通达路线编码
    private String priorityRouteCode;
//    优选通达路线行政等级
    private String priorityRouteGrade;
//    通达现状
    private String situation;

    public String getLandForm() {
        return landForm;
    }

    public void setLandForm(String landForm) {
        this.landForm = landForm;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getPriorityRoute() {
        return priorityRoute;
    }

    public void setPriorityRoute(String priorityRoute) {
        this.priorityRoute = priorityRoute;
    }

    public String getPriorityRouteCode() {
        return priorityRouteCode;
    }

    public void setPriorityRouteCode(String priorityRouteCode) {
        this.priorityRouteCode = priorityRouteCode;
    }

    public String getPriorityRouteGrade() {
        return priorityRouteGrade;
    }

    public void setPriorityRouteGrade(String priorityRouteGrade) {
        this.priorityRouteGrade = priorityRouteGrade;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    /** 子部门 */
    private List<SysDept> children = new ArrayList<SysDept>();

    public Long getDeptId()
    {
        return deptId;
    }

    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public String getAncestors()
    {
        return ancestors;
    }

    public void setAncestors(String ancestors)
    {
        this.ancestors = ancestors;
    }

    @NotBlank(message = "部门名称不能为空")
    @Size(min = 0, max = 30, message = "部门名称长度不能超过30个字符")
    public String getDeptName()
    {
        return deptName;
    }

    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }

    @NotNull(message = "显示顺序不能为空")
    public Integer getOrderNum()
    {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum)
    {
        this.orderNum = orderNum;
    }

    public String getLeader()
    {
        return leader;
    }

    public void setLeader(String leader)
    {
        this.leader = leader;
    }

    @Size(min = 0, max = 11, message = "联系电话长度不能超过11个字符")
    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    @Email(message = "邮箱格式不正确")
    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getParentName()
    {
        return parentName;
    }

    public void setParentName(String parentName)
    {
        this.parentName = parentName;
    }

    public List<SysDept> getChildren()
    {
        return children;
    }

    public void setChildren(List<SysDept> children)
    {
        this.children = children;
    }

//    @Override
//    public String toString() {
//        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
//            .append("deptId", getDeptId())
//            .append("parentId", getParentId())
//            .append("ancestors", getAncestors())
//            .append("deptName", getDeptName())
//            .append("orderNum", getOrderNum())
//            .append("leader", getLeader())
//            .append("phone", getPhone())
//            .append("email", getEmail())
//            .append("status", getStatus())
//            .append("delFlag", getDelFlag())
//            .append("createBy", getCreateBy())
//            .append("createTime", getCreateTime())
//            .append("updateBy", getUpdateBy())
//            .append("updateTime", getUpdateTime())
//            .toString();
//    }

    @Override
    public String toString() {
        return "SysDept{" +
                "deptId=" + deptId +
                ", parentId=" + parentId +
                ", ancestors='" + ancestors + '\'' +
                ", deptName='" + deptName + '\'' +
                ", orderNum=" + orderNum +
                ", leader='" + leader + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", parentName='" + parentName + '\'' +
                ", landForm='" + landForm + '\'' +
                ", postcode='" + postcode + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", priorityRoute='" + priorityRoute + '\'' +
                ", priorityRouteCode='" + priorityRouteCode + '\'' +
                ", priorityRouteGrade='" + priorityRouteGrade + '\'' +
                ", situation='" + situation + '\'' +
                ", children=" + children +
                '}';
    }
}
