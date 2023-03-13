package com.hg.system.mapper;

import java.util.List;
import com.hg.system.domain.HwProjectInformation;

/**
 * 项目信息管理Mapper接口
 * 
 * @author W-yf
 * @date 2023-03-07
 */
public interface HwProjectInformationMapper
{
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
     * 删除项目信息管理
     *
     * @param projectId 项目信息管理主键
     * @return 结果
     */
    public int deleteHwProjectInformationByProjectId(Long projectId);

    /**
     * 批量删除项目信息管理
     *
     * @param projectIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHwProjectInformationByProjectIds(Long[] projectIds);
}
