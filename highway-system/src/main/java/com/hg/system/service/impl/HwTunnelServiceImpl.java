package com.hg.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hg.system.mapper.HwTunnelMapper;
import com.hg.system.domain.HwTunnel;
import com.hg.system.service.IHwTunnelService;

/**
 * 隧道信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-14
 */
@Service
public class HwTunnelServiceImpl implements IHwTunnelService 
{
    @Autowired
    private HwTunnelMapper hwTunnelMapper;

    /**
     * 查询隧道信息
     * 
     * @param tunnelId 隧道信息主键
     * @return 隧道信息
     */
    @Override
    public HwTunnel selectHwTunnelByTunnelId(Long tunnelId)
    {
        return hwTunnelMapper.selectHwTunnelByTunnelId(tunnelId);
    }

    /**
     * 查询隧道信息列表
     * 
     * @param hwTunnel 隧道信息
     * @return 隧道信息
     */
    @Override
    public List<HwTunnel> selectHwTunnelList(HwTunnel hwTunnel)
    {
        return hwTunnelMapper.selectHwTunnelList(hwTunnel);
    }

    /**
     * 新增隧道信息
     * 
     * @param hwTunnel 隧道信息
     * @return 结果
     */
    @Override
    public int insertHwTunnel(HwTunnel hwTunnel)
    {
        return hwTunnelMapper.insertHwTunnel(hwTunnel);
    }

    /**
     * 修改隧道信息
     * 
     * @param hwTunnel 隧道信息
     * @return 结果
     */
    @Override
    public int updateHwTunnel(HwTunnel hwTunnel)
    {
        return hwTunnelMapper.updateHwTunnel(hwTunnel);
    }

    /**
     * 批量删除隧道信息
     * 
     * @param tunnelIds 需要删除的隧道信息主键
     * @return 结果
     */
    @Override
    public int deleteHwTunnelByTunnelIds(Long[] tunnelIds)
    {
        return hwTunnelMapper.deleteHwTunnelByTunnelIds(tunnelIds);
    }

    /**
     * 删除隧道信息信息
     * 
     * @param tunnelId 隧道信息主键
     * @return 结果
     */
    @Override
    public int deleteHwTunnelByTunnelId(Long tunnelId)
    {
        return hwTunnelMapper.deleteHwTunnelByTunnelId(tunnelId);
    }
}
