package com.hg.system.service;

import java.util.List;

import com.hg.system.domain.HwProjectInformation;
import com.hg.system.mapper.HwProjectInformationMapper;
import com.hg.system.service.impl.HwProjectInformationServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目信息管理Service接口
 *
 * @author W-yf
 * @date 2023-03-07
 */
public interface IHwProjectInformationService {
    /**
     * 查询项目信息管理
     *
     * @param projectId 项目信息管理主键
     * @return 项目信息管理
     */
    public HwProjectInformation selectHwProjectInformationByProjectId(Long projectId);

    /**
     * 查询项目信息管理列表
     *
     * @param hwProjectInformation 项目信息管理
     * @return 项目信息管理集合
     */
    public List<HwProjectInformation> selectHwProjectInformationList(HwProjectInformation hwProjectInformation);

    /**
     * 新增项目信息管理
     *
     * @param hwProjectInformation 项目信息管理
     * @return 结果
     */
    public int insertHwProjectInformation(HwProjectInformation hwProjectInformation);

    /**
     * 修改项目信息管理
     *
     * @param hwProjectInformation 项目信息管理
     * @return 结果
     */
    public int updateHwProjectInformation(HwProjectInformation hwProjectInformation);

    /**
     * 批量删除项目信息管理
     *
     * @param projectIds 需要删除的项目信息管理主键集合
     * @return 结果
     */
    public int deleteHwProjectInformationByProjectIds(Long[] projectIds);

    /**
     * 删除项目信息管理信息
     *
     * @param projectId 项目信息管理主键
     * @return 结果
     */
    public int deleteHwProjectInformationByProjectId(Long projectId);

    /**
     * 新版查询，包含两个标段
     *
     * @param hwProjectInformation 项目信息管理
     * @return 结果
     */
    List<HwProjectInformation> selectAllProjectInformationList(HwProjectInformation hwProjectInformation);
}
