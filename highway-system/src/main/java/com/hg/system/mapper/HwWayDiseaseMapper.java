package com.hg.system.mapper;

import com.hg.system.domain.HwWayDisease;
import com.hg.system.domain.vo.RouterType;

import java.util.List;


/**
 * 道路病害管理Mapper接口
 * 
 * @author ruoyi
 * @date 2023-03-08
 */
public interface HwWayDiseaseMapper 
{
    /**
     * 查询道路病害管理
     * 
     * @param wdid 道路病害管理主键
     * @return 道路病害管理
     */
    public HwWayDisease selectHwWayDiseaseByWdid(Long wdid);

    /**
     * 查询道路病害管理列表
     * 
     * @param hwWayDisease 道路病害管理
     * @return 道路病害管理集合
     */
    public List<HwWayDisease> selectHwWayDiseaseList(HwWayDisease hwWayDisease);

    /**
     * 新增道路病害管理
     * 
     * @param hwWayDisease 道路病害管理
     * @return 结果
     */
    public int insertHwWayDisease(HwWayDisease hwWayDisease);

    /**
     * 修改道路病害管理
     * 
     * @param hwWayDisease 道路病害管理
     * @return 结果
     */
    public int updateHwWayDisease(HwWayDisease hwWayDisease);

    /**
     * 删除道路病害管理
     * 
     * @param wdid 道路病害管理主键
     * @return 结果
     */
    public int deleteHwWayDiseaseByWdid(Long wdid);

    /**
     * 批量删除道路病害管理
     * 
     * @param wdids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHwWayDiseaseByWdids(Long[] wdids);

    List<RouterType> diseaseCount();

    List<RouterType> routeDisease();
}
