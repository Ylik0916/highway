package com.hg.system.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hg.system.mapper.HwProjectInformationMapper;
import com.hg.system.domain.HwProjectInformation;
import com.hg.system.service.IHwProjectInformationService;

/**
 * 项目信息管理Service业务层处理
 *
 * @author W-yf
 * @date 2023-03-07
 */
@Service
public class HwProjectInformationServiceImpl implements IHwProjectInformationService {
    @Autowired
    private HwProjectInformationMapper hwProjectInformationMapper;

    /**
     * 查询项目信息管理
     *
     * @param projectId 项目信息管理主键
     * @return 项目信息管理
     */
    @Override
    public HwProjectInformation selectHwProjectInformationByProjectId(Long projectId) {
        return hwProjectInformationMapper.selectHwProjectInformationByProjectId(projectId);
    }

    /**
     * 查询项目信息管理列表
     *
     * @param hwProjectInformation 项目信息管理
     * @return 项目信息管理
     */
    @Override
    public List<HwProjectInformation> selectHwProjectInformationList(HwProjectInformation hwProjectInformation) {
        return hwProjectInformationMapper.selectHwProjectInformationList(hwProjectInformation);
    }

    /**
     * 新增项目信息管理
     *
     * @param hwProjectInformation 项目信息管理
     * @return 结果
     */
    @Override
    public int insertHwProjectInformation(HwProjectInformation hwProjectInformation) {
        return hwProjectInformationMapper.insertHwProjectInformation(hwProjectInformation);
    }

    /**
     * 修改项目信息管理
     *
     * @param hwProjectInformation 项目信息管理
     * @return 结果
     */
    @Override
    public int updateHwProjectInformation(HwProjectInformation hwProjectInformation) {
        return hwProjectInformationMapper.updateHwProjectInformation(hwProjectInformation);
    }

    /**
     * 批量删除项目信息管理
     *
     * @param projectIds 需要删除的项目信息管理主键
     * @return 结果
     */
    @Override
    public int deleteHwProjectInformationByProjectIds(Long[] projectIds) {
        return hwProjectInformationMapper.deleteHwProjectInformationByProjectIds(projectIds);
    }

    /**
     * 删除项目信息管理信息
     *
     * @param projectId 项目信息管理主键
     * @return 结果
     */
    @Override
    public int deleteHwProjectInformationByProjectId(Long projectId) {
        return hwProjectInformationMapper.deleteHwProjectInformationByProjectId(projectId);
    }

    /**
     * 新版查询，包含两个标段
     *
     * @param hwProjectInformation 项目信息管理
     * @return 结果
     */
    @Override
    public List<HwProjectInformation> selectAllProjectInformationList(@Param("hwProjectInformation") HwProjectInformation hwProjectInformation) {
        return hwProjectInformationMapper.selectAllProjectInformationList(hwProjectInformation);
    }
}
