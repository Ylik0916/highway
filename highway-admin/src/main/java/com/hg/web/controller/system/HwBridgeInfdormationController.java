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
import com.hg.system.domain.HwBridgeInfdormation;
import com.hg.system.service.IHwBridgeInfdormationService;
import com.hg.common.utils.poi.ExcelUtil;
import com.hg.common.core.page.TableDataInfo;

/**
 * 桥梁信息Controller
 *
 * @author ruoyi
 * @date 2023-03-14
 */
@RestController
@RequestMapping("/system/infdormation")
public class HwBridgeInfdormationController extends BaseController
{
    @Autowired
    private IHwBridgeInfdormationService hwBridgeInfdormationService;

    /**
     * 查询桥梁信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:infdormation:list')")
    @GetMapping("/list")
    public TableDataInfo list(HwBridgeInfdormation hwBridgeInfdormation)
    {
        startPage();
        List<HwBridgeInfdormation> list = hwBridgeInfdormationService.selectHwBridgeInfdormationList(hwBridgeInfdormation);
        return getDataTable(list);
    }

    /**
     * 导出桥梁信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:infdormation:export')")
    @Log(title = "桥梁信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HwBridgeInfdormation hwBridgeInfdormation)
    {
        List<HwBridgeInfdormation> list = hwBridgeInfdormationService.selectHwBridgeInfdormationList(hwBridgeInfdormation);
        ExcelUtil<HwBridgeInfdormation> util = new ExcelUtil<HwBridgeInfdormation>(HwBridgeInfdormation.class);
        util.exportExcel(response, list, "桥梁信息数据");
    }

    /**
     * 获取桥梁信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:infdormation:query')")
    @GetMapping(value = "/{routeId}")
    public AjaxResult getInfo(@PathVariable("routeId") Long routeId)
    {
        return success(hwBridgeInfdormationService.selectHwBridgeInfdormationByRouteId(routeId));
    }

    /**
     * 新增桥梁信息
     */
    @PreAuthorize("@ss.hasPermi('system:infdormation:add')")
    @Log(title = "桥梁信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HwBridgeInfdormation hwBridgeInfdormation)
    {
        return toAjax(hwBridgeInfdormationService.insertHwBridgeInfdormation(hwBridgeInfdormation));
    }

    /**
     * 修改桥梁信息
     */
    @PreAuthorize("@ss.hasPermi('system:infdormation:edit')")
    @Log(title = "桥梁信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HwBridgeInfdormation hwBridgeInfdormation)
    {
        return toAjax(hwBridgeInfdormationService.updateHwBridgeInfdormation(hwBridgeInfdormation));
    }

    /**
     * 删除桥梁信息
     */
    @PreAuthorize("@ss.hasPermi('system:infdormation:remove')")
    @Log(title = "桥梁信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{routeIds}")
    public AjaxResult remove(@PathVariable Long[] routeIds)
    {
        return toAjax(hwBridgeInfdormationService.deleteHwBridgeInfdormationByRouteIds(routeIds));
    }
}
