package com.hg.system.service;

import java.util.List;
import com.hg.system.domain.HwProjectConstruction;

/**
 * W-yfService接口
 * 
 * @author W-yf
 * @date 2023-03-15
 */
public interface IHwProjectConstructionService 
{
    /**
     * 查询W-yf
     * 
     * @param constructionId W-yf主键
     * @return W-yf
     */
    public HwProjectConstruction selectHwProjectConstructionByConstructionId(Long constructionId);

    /**
     * 查询W-yf列表
     * 
     * @param hwProjectConstruction W-yf
     * @return W-yf集合
     */
    public List<HwProjectConstruction> selectHwProjectConstructionList(HwProjectConstruction hwProjectConstruction);

    /**
     * 新增W-yf
     * 
     * @param hwProjectConstruction W-yf
     * @return 结果
     */
    public int insertHwProjectConstruction(HwProjectConstruction hwProjectConstruction);

    /**
     * 修改W-yf
     * 
     * @param hwProjectConstruction W-yf
     * @return 结果
     */
    public int updateHwProjectConstruction(HwProjectConstruction hwProjectConstruction);

    /**
     * 批量删除W-yf
     * 
     * @param constructionIds 需要删除的W-yf主键集合
     * @return 结果
     */
    public int deleteHwProjectConstructionByConstructionIds(Long[] constructionIds);

    /**
     * 删除W-yf信息
     * 
     * @param constructionId W-yf主键
     * @return 结果
     */
    public int deleteHwProjectConstructionByConstructionId(Long constructionId);

    /**
     * 新版查询施工
     *
     * @param constructionProjectId 项目id
     * @return W-yf集合
     */
    public List<HwProjectConstruction> selectHwProjectConstructionSupervisor(Integer constructionProjectId);
}
