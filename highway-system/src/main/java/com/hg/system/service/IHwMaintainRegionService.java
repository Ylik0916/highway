package com.hg.system.service;

import java.util.List;
import com.hg.system.domain.HwMaintainRegion;

/**
 * 养护单位关联行政区Service接口
 * 
 * @author hg
 * @date 2023-03-14
 */
public interface IHwMaintainRegionService 
{
    /**
     * 查询养护单位关联行政区
     * 
     * @param mrid 养护单位关联行政区主键
     * @return 养护单位关联行政区
     */
    public HwMaintainRegion selectHwMaintainRegionByMrid(Long mrid);

    /**
     * 查询养护单位关联行政区列表
     * 
     * @param hwMaintainRegion 养护单位关联行政区
     * @return 养护单位关联行政区集合
     */
    public List<HwMaintainRegion> selectHwMaintainRegionList(HwMaintainRegion hwMaintainRegion);

    /**
     * 新增养护单位关联行政区
     * 
     * @param hwMaintainRegion 养护单位关联行政区
     * @return 结果
     */
    public int insertHwMaintainRegion(HwMaintainRegion hwMaintainRegion);

    /**
     * 修改养护单位关联行政区
     * 
     * @param hwMaintainRegion 养护单位关联行政区
     * @return 结果
     */
    public int updateHwMaintainRegion(HwMaintainRegion hwMaintainRegion);

    /**
     * 批量删除养护单位关联行政区
     * 
     * @param mrids 需要删除的养护单位关联行政区主键集合
     * @return 结果
     */
    public int deleteHwMaintainRegionByMrids(Long[] mrids);

    /**
     * 删除养护单位关联行政区信息
     * 
     * @param mrid 养护单位关联行政区主键
     * @return 结果
     */
    public int deleteHwMaintainRegionByMrid(Long mrid);
}
