package com.hg.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hg.system.mapper.HwProjectSupervisorMapper;
import com.hg.system.domain.HwProjectSupervisor;
import com.hg.system.service.IHwProjectSupervisorService;

/**
 * 项目信息管理==》监理标段Service业务层处理
 *
 * @author W-yf
 * @date 2023-03-14
 */
@Service
public class HwProjectSupervisorServiceImpl implements IHwProjectSupervisorService {
    @Autowired
    private HwProjectSupervisorMapper hwProjectSupervisorMapper;

    /**
     * 查询项目信息管理==》监理标段
     *
     * @param supervisorId 项目信息管理==》监理标段主键
     * @return 项目信息管理==》监理标段
     */
    @Override
    public HwProjectSupervisor selectHwProjectSupervisorBySupervisorId(Long supervisorId) {
        return hwProjectSupervisorMapper.selectHwProjectSupervisorBySupervisorId(supervisorId);
    }

    /**
     * 查询项目信息管理==》监理标段列表
     *
     * @param hwProjectSupervisor 项目信息管理==》监理标段
     * @return 项目信息管理==》监理标段
     */
    @Override
    public List<HwProjectSupervisor> selectHwProjectSupervisorList(HwProjectSupervisor hwProjectSupervisor) {
        return hwProjectSupervisorMapper.selectHwProjectSupervisorList(hwProjectSupervisor);
    }

    /**
     * 新增项目信息管理==》监理标段
     *
     * @param hwProjectSupervisor 项目信息管理==》监理标段
     * @return 结果
     */
    @Override
    public int insertHwProjectSupervisor(HwProjectSupervisor hwProjectSupervisor) {
        return hwProjectSupervisorMapper.insertHwProjectSupervisor(hwProjectSupervisor);
    }

    /**
     * 修改项目信息管理==》监理标段
     *
     * @param hwProjectSupervisor 项目信息管理==》监理标段
     * @return 结果
     */
    @Override
    public int updateHwProjectSupervisor(HwProjectSupervisor hwProjectSupervisor) {
        return hwProjectSupervisorMapper.updateHwProjectSupervisor(hwProjectSupervisor);
    }

    /**
     * 批量删除项目信息管理==》监理标段
     *
     * @param supervisorIds 需要删除的项目信息管理==》监理标段主键
     * @return 结果
     */
    @Override
    public int deleteHwProjectSupervisorBySupervisorIds(Long[] supervisorIds) {
        return hwProjectSupervisorMapper.deleteHwProjectSupervisorBySupervisorIds(supervisorIds);
    }

    /**
     * 删除项目信息管理==》监理标段信息
     *
     * @param supervisorId 项目信息管理==》监理标段主键
     * @return 结果
     */
    @Override
    public int deleteHwProjectSupervisorBySupervisorId(Long supervisorId) {
        return hwProjectSupervisorMapper.deleteHwProjectSupervisorBySupervisorId(supervisorId);
    }

    /**
     * 根据项目id查询其全部标段
     *
     * @param projectId 项目信息管理id
     * @return 项目信息管理==》监理标段
     */
    @Override
    public List<HwProjectSupervisor> selectHwProjectSupervisorByProjectId(Integer projectId) {
        return hwProjectSupervisorMapper.selectHwProjectSupervisorByProjectId(projectId);
    }
}
