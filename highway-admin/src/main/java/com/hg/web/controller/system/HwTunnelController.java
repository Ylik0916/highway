package com.hg.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hg.common.annotation.Log;
import com.hg.common.core.controller.BaseController;
import com.hg.common.core.domain.AjaxResult;
import com.hg.common.enums.BusinessType;
import com.hg.system.domain.HwTunnel;
import com.hg.system.service.IHwTunnelService;
import com.hg.common.utils.poi.ExcelUtil;
import com.hg.common.core.page.TableDataInfo;

/**
 * 隧道信息Controller
 * 
 * @author ruoyi
 * @date 2023-03-14
 */
@RestController
@RequestMapping("/system/tunnel")
public class HwTunnelController extends BaseController
{
    @Autowired
    private IHwTunnelService hwTunnelService;

    /**
     * 查询隧道信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:tunnel:list')")
    @GetMapping("/list")
    public TableDataInfo list(HwTunnel hwTunnel)
    {
        startPage();
        List<HwTunnel> list = hwTunnelService.selectHwTunnelList(hwTunnel);
        return getDataTable(list);
    }

    /**
     * 导出隧道信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:tunnel:export')")
    @Log(title = "隧道信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HwTunnel hwTunnel)
    {
        System.out.println(hwTunnel.toString());
        List<HwTunnel> list = hwTunnelService.selectHwTunnelList(hwTunnel);
        ExcelUtil<HwTunnel> util = new ExcelUtil<HwTunnel>(HwTunnel.class);
        util.exportExcel(response, list, "隧道信息数据");
    }

    /**
     * 获取隧道信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:tunnel:query')")
    @GetMapping(value = "/{tunnelId}")
    public AjaxResult getInfo(@PathVariable("tunnelId") Long tunnelId)
    {
        return success(hwTunnelService.selectHwTunnelByTunnelId(tunnelId));
    }

    /**
     * 新增隧道信息
     */
    @PreAuthorize("@ss.hasPermi('system:tunnel:add')")
    @Log(title = "隧道信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HwTunnel hwTunnel)
    {
        return toAjax(hwTunnelService.insertHwTunnel(hwTunnel));
    }

    /**
     * 修改隧道信息
     */
    @PreAuthorize("@ss.hasPermi('system:tunnel:edit')")
    @Log(title = "隧道信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HwTunnel hwTunnel)
    {
        return toAjax(hwTunnelService.updateHwTunnel(hwTunnel));
    }

    /**
     * 删除隧道信息
     */
    @PreAuthorize("@ss.hasPermi('system:tunnel:remove')")
    @Log(title = "隧道信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tunnelIds}")
    public AjaxResult remove(@PathVariable Long[] tunnelIds)
    {
        return toAjax(hwTunnelService.deleteHwTunnelByTunnelIds(tunnelIds));
    }
}
