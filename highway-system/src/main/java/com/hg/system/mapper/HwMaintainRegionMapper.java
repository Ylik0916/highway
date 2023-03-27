package com.hg.system.mapper;

import java.util.List;
import com.hg.system.domain.HwMaintainRegion;
import org.apache.ibatis.annotations.Param;

/**
 * 养护单位关联行政区Mapper接口
 * 
 * @author hg
 * @date 2023-03-14
 */
public interface HwMaintainRegionMapper
{

    /**
     * 批量插入
     * @param regionList
     * @return
     */
    public int insertHwMaintainRegionList(@Param("list") List<HwMaintainRegion> regionList);

    /**
     * 查询养护单位关联行政区
     * 
     * @param mrid 养护单位关联行政区主键
     * @return 养护单位关联行政区
     */
    public HwMaintainRegion selectHwMaintainRegionByMrid(Long mrid);

    /**
     * 根据muid查询
     * @param muid
     * @return
     */
    public List<Integer> selectHwMaintainRegionByMuid(Long muid);

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
     * 删除养护单位关联行政区
     * 
     * @param mrid 养护单位关联行政区主键
     * @return 结果
     */
    public int deleteHwMaintainRegionByMrid(Long mrid);

    /**
     * 批量删除养护单位关联行政区
     * 
     * @param mrids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHwMaintainRegionByMrids(Long[] mrids);

    /**
     * 删除养护单位关联行政区根据muid
     * @param muid
     * @return
     */
    public int deleteHwMaintainRegionByMuid(Long muid);

    /**
     * 批量删除养护单位关联行政区根据muid
     * @param muids
     * @return
     */
    public int deleteHwMaintainRegionByMuids(Long[] muids);
}
