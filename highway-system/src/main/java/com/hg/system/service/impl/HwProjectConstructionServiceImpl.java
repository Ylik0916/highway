package com.hg.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hg.system.mapper.HwProjectConstructionMapper;
import com.hg.system.domain.HwProjectConstruction;
import com.hg.system.service.IHwProjectConstructionService;

/**
 * W-yfService业务层处理
 * 
 * @author W-yf
 * @date 2023-03-15
 */
@Service
public class HwProjectConstructionServiceImpl implements IHwProjectConstructionService 
{
    @Autowired
    private HwProjectConstructionMapper hwProjectConstructionMapper;

    /**
     * 查询W-yf
     * 
     * @param constructionId W-yf主键
     * @return W-yf
     */
    @Override
    public HwProjectConstruction selectHwProjectConstructionByConstructionId(Long constructionId)
    {
        return hwProjectConstructionMapper.selectHwProjectConstructionByConstructionId(constructionId);
    }

    /**
     * 查询W-yf列表
     * 
     * @param hwProjectConstruction W-yf
     * @return W-yf
     */
    @Override
    public List<HwProjectConstruction> selectHwProjectConstructionList(HwProjectConstruction hwProjectConstruction)
    {
        return hwProjectConstructionMapper.selectHwProjectConstructionList(hwProjectConstruction);
    }

    /**
     * 新增W-yf
     * 
     * @param hwProjectConstruction W-yf
     * @return 结果
     */
    @Override
    public int insertHwProjectConstruction(HwProjectConstruction hwProjectConstruction)
    {
        return hwProjectConstructionMapper.insertHwProjectConstruction(hwProjectConstruction);
    }

    /**
     * 修改W-yf
     * 
     * @param hwProjectConstruction W-yf
     * @return 结果
     */
    @Override
    public int updateHwProjectConstruction(HwProjectConstruction hwProjectConstruction)
    {
        return hwProjectConstructionMapper.updateHwProjectConstruction(hwProjectConstruction);
    }

    /**
     * 批量删除W-yf
     * 
     * @param constructionIds 需要删除的W-yf主键
     * @return 结果
     */
    @Override
    public int deleteHwProjectConstructionByConstructionIds(Long[] constructionIds)
    {
        return hwProjectConstructionMapper.deleteHwProjectConstructionByConstructionIds(constructionIds);
    }

    /**
     * 删除W-yf信息
     * 
     * @param constructionId W-yf主键
     * @return 结果
     */
    @Override
    public int deleteHwProjectConstructionByConstructionId(Long constructionId)
    {
        return hwProjectConstructionMapper.deleteHwProjectConstructionByConstructionId(constructionId);
    }
}
