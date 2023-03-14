package com.hg.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hg.system.mapper.HwMaintainRegionMapper;
import com.hg.system.domain.HwMaintainRegion;
import com.hg.system.service.IHwMaintainRegionService;

/**
 * 养护单位关联行政区Service业务层处理
 * 
 * @author hg
 * @date 2023-03-14
 */
@Service
public class HwMaintainRegionServiceImpl implements IHwMaintainRegionService 
{
    @Autowired
    private HwMaintainRegionMapper hwMaintainRegionMapper;

    /**
     * 查询养护单位关联行政区
     * 
     * @param mrid 养护单位关联行政区主键
     * @return 养护单位关联行政区
     */
    @Override
    public HwMaintainRegion selectHwMaintainRegionByMrid(Long mrid)
    {
        return hwMaintainRegionMapper.selectHwMaintainRegionByMrid(mrid);
    }

    /**
     * 查询养护单位关联行政区列表
     * 
     * @param hwMaintainRegion 养护单位关联行政区
     * @return 养护单位关联行政区
     */
    @Override
    public List<HwMaintainRegion> selectHwMaintainRegionList(HwMaintainRegion hwMaintainRegion)
    {
        return hwMaintainRegionMapper.selectHwMaintainRegionList(hwMaintainRegion);
    }

    /**
     * 新增养护单位关联行政区
     * 
     * @param hwMaintainRegion 养护单位关联行政区
     * @return 结果
     */
    @Override
    public int insertHwMaintainRegion(HwMaintainRegion hwMaintainRegion)
    {
        return hwMaintainRegionMapper.insertHwMaintainRegion(hwMaintainRegion);
    }

    /**
     * 修改养护单位关联行政区
     * 
     * @param hwMaintainRegion 养护单位关联行政区
     * @return 结果
     */
    @Override
    public int updateHwMaintainRegion(HwMaintainRegion hwMaintainRegion)
    {
        return hwMaintainRegionMapper.updateHwMaintainRegion(hwMaintainRegion);
    }

    /**
     * 批量删除养护单位关联行政区
     * 
     * @param mrids 需要删除的养护单位关联行政区主键
     * @return 结果
     */
    @Override
    public int deleteHwMaintainRegionByMrids(Long[] mrids)
    {
        return hwMaintainRegionMapper.deleteHwMaintainRegionByMrids(mrids);
    }

    /**
     * 删除养护单位关联行政区信息
     * 
     * @param mrid 养护单位关联行政区主键
     * @return 结果
     */
    @Override
    public int deleteHwMaintainRegionByMrid(Long mrid)
    {
        return hwMaintainRegionMapper.deleteHwMaintainRegionByMrid(mrid);
    }
}
