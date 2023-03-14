package com.hg.system.mapper;

import java.util.List;
import com.hg.system.domain.HwTunnel;

/**
 * 隧道信息Mapper接口
 * 
 * @author ruoyi
 * @date 2023-03-14
 */
public interface HwTunnelMapper 
{
    /**
     * 查询隧道信息
     * 
     * @param tunnelId 隧道信息主键
     * @return 隧道信息
     */
    public HwTunnel selectHwTunnelByTunnelId(Long tunnelId);

    /**
     * 查询隧道信息列表
     * 
     * @param hwTunnel 隧道信息
     * @return 隧道信息集合
     */
    public List<HwTunnel> selectHwTunnelList(HwTunnel hwTunnel);

    /**
     * 新增隧道信息
     * 
     * @param hwTunnel 隧道信息
     * @return 结果
     */
    public int insertHwTunnel(HwTunnel hwTunnel);

    /**
     * 修改隧道信息
     * 
     * @param hwTunnel 隧道信息
     * @return 结果
     */
    public int updateHwTunnel(HwTunnel hwTunnel);

    /**
     * 删除隧道信息
     * 
     * @param tunnelId 隧道信息主键
     * @return 结果
     */
    public int deleteHwTunnelByTunnelId(Long tunnelId);

    /**
     * 批量删除隧道信息
     * 
     * @param tunnelIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHwTunnelByTunnelIds(Long[] tunnelIds);
}
