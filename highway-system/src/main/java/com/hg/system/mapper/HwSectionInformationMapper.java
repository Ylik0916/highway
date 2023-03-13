package com.hg.system.mapper;

import com.hg.system.domain.HwSectionInformation;

import java.util.List;

/**
 * 路段信息Mapper接口
 *
 * @author ruoyi
 * @date 2023-03-13
 */
public interface HwSectionInformationMapper
{
    /**
     * 查询路段信息
     *
     * @param roadsectionid 路段信息主键
     * @return 路段信息
     */
    public HwSectionInformation selectHwSectionInformationByRoadsectionid(Long roadsectionid);

    /**
     * 查询路段信息列表
     *
     * @param hwSectionInformation 路段信息
     * @return 路段信息集合
     */
    public List<HwSectionInformation> selectHwSectionInformationList(HwSectionInformation hwSectionInformation);

    /**
     * 新增路段信息
     *
     * @param hwSectionInformation 路段信息
     * @return 结果
     */
    public int insertHwSectionInformation(HwSectionInformation hwSectionInformation);

    /**
     * 修改路段信息
     *
     * @param hwSectionInformation 路段信息
     * @return 结果
     */
    public int updateHwSectionInformation(HwSectionInformation hwSectionInformation);

    /**
     * 删除路段信息
     *
     * @param roadsectionid 路段信息主键
     * @return 结果
     */
    public int deleteHwSectionInformationByRoadsectionid(Long roadsectionid);

    /**
     * 批量删除路段信息
     *
     * @param roadsectionids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHwSectionInformationByRoadsectionids(Long[] roadsectionids);
}
