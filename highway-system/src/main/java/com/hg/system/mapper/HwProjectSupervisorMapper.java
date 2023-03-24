package com.hg.system.mapper;

import java.util.List;
import com.hg.system.domain.HwProjectSupervisor;

/**
 * 项目信息管理==》监理标段Mapper接口
 * 
 * @author W-yf
 * @date 2023-03-14
 */
public interface HwProjectSupervisorMapper 
{
    /**
     * 查询项目信息管理==》监理标段
     * 
     * @param supervisorId 项目信息管理==》监理标段主键
     * @return 项目信息管理==》监理标段
     */
    public HwProjectSupervisor selectHwProjectSupervisorBySupervisorId(Long supervisorId);

    /**
     * 查询项目信息管理==》监理标段列表
     * 
     * @param hwProjectSupervisor 项目信息管理==》监理标段
     * @return 项目信息管理==》监理标段集合
     */
    public List<HwProjectSupervisor> selectHwProjectSupervisorList(HwProjectSupervisor hwProjectSupervisor);

    /**
     * 新增项目信息管理==》监理标段
     * 
     * @param hwProjectSupervisor 项目信息管理==》监理标段
     * @return 结果
     */
    public int insertHwProjectSupervisor(HwProjectSupervisor hwProjectSupervisor);

    /**
     * 修改项目信息管理==》监理标段
     * 
     * @param hwProjectSupervisor 项目信息管理==》监理标段
     * @return 结果
     */
    public int updateHwProjectSupervisor(HwProjectSupervisor hwProjectSupervisor);

    /**
     * 删除项目信息管理==》监理标段
     * 
     * @param supervisorId 项目信息管理==》监理标段主键
     * @return 结果
     */
    public int deleteHwProjectSupervisorBySupervisorId(Long supervisorId);

    /**
     * 批量删除项目信息管理==》监理标段
     * 
     * @param supervisorIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHwProjectSupervisorBySupervisorIds(Long[] supervisorIds);

    /**
     * 根据项目id查询其全部标段
     *
     * @param projectId 项目信息管理id
     * @return 项目信息管理==》监理标段
     */
    public List<HwProjectSupervisor> selectHwProjectSupervisorByProjectId(Integer projectId);

    /**
     * 根据项目id查询其最新标段
     *
     * @param projectId 项目信息管理id
     * @return 项目信息管理==》监理标段
     */
    HwProjectSupervisor selectHwProjectSupervisorByProjectIdByNew(Integer projectId);
}
