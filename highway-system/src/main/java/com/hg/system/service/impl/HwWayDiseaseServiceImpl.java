package com.hg.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.hg.system.domain.HwWayDisease;
import com.hg.system.domain.vo.RouteMileage;
import com.hg.system.domain.vo.RouterType;
import com.hg.system.mapper.HwWayDiseaseMapper;
import com.hg.system.service.IHwWayDiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 道路病害管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-08
 */
@Service
public class HwWayDiseaseServiceImpl implements IHwWayDiseaseService
{
    @Autowired
    private HwWayDiseaseMapper hwWayDiseaseMapper;

    /**
     * 查询道路病害管理
     * 
     * @param wdid 道路病害管理主键
     * @return 道路病害管理
     */
    @Override
    public HwWayDisease selectHwWayDiseaseByWdid(Long wdid)
    {
        return hwWayDiseaseMapper.selectHwWayDiseaseByWdid(wdid);
    }

    /**
     * 查询道路病害管理列表
     * 
     * @param hwWayDisease 道路病害管理
     * @return 道路病害管理
     */
    @Override
    public List<HwWayDisease> selectHwWayDiseaseList(HwWayDisease hwWayDisease)
    {
        return hwWayDiseaseMapper.selectHwWayDiseaseList(hwWayDisease);
    }

    /**
     * 新增道路病害管理
     * 
     * @param hwWayDisease 道路病害管理
     * @return 结果
     */
    @Override
    public int insertHwWayDisease(HwWayDisease hwWayDisease)
    {
        return hwWayDiseaseMapper.insertHwWayDisease(hwWayDisease);
    }

    /**
     * 修改道路病害管理
     * 
     * @param hwWayDisease 道路病害管理
     * @return 结果
     */
    @Override
    public int updateHwWayDisease(HwWayDisease hwWayDisease)
    {
        return hwWayDiseaseMapper.updateHwWayDisease(hwWayDisease);
    }

    /**
     * 批量删除道路病害管理
     * 
     * @param wdids 需要删除的道路病害管理主键
     * @return 结果
     */
    @Override
    public int deleteHwWayDiseaseByWdids(Long[] wdids)
    {
        return hwWayDiseaseMapper.deleteHwWayDiseaseByWdids(wdids);
    }

    /**
     * 删除道路病害管理信息
     * 
     * @param wdid 道路病害管理主键
     * @return 结果
     */
    @Override
    public int deleteHwWayDiseaseByWdid(Long wdid)
    {
        return hwWayDiseaseMapper.deleteHwWayDiseaseByWdid(wdid);
    }

    @Override
    public List<RouterType> diseaseCount() {
        return hwWayDiseaseMapper.diseaseCount();
    }

    @Override
    public RouteMileage routeDisease() {
        RouteMileage routeMileage = new RouteMileage();
        List<String> name = new ArrayList<>();
        List<String> value = new ArrayList<>();
        List<RouterType> routerTypes = hwWayDiseaseMapper.routeDisease();
        for (RouterType routerType : routerTypes) {
            name.add(routerType.getName());
            value.add(routerType.getValue());
        }
        routeMileage.setName(name);
        routeMileage.setValue(value);
        return routeMileage;
    }
}
