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
import com.hg.system.domain.HwBridgeQuality;
import com.hg.system.service.IHwBridgeQualityService;
import com.hg.common.utils.poi.ExcelUtil;
import com.hg.common.core.page.TableDataInfo;

/**
 * 桥梁质量Controller
 * 
 * @author ruoyi
 * @date 2023-03-01
 */
@RestController
@RequestMapping("/system/quality")
public class HwBridgeQualityController extends BaseController
{
    @Autowired
    private IHwBridgeQualityService hwBridgeQualityService;

    /**
     * 查询桥梁质量列表
     */
    @PreAuthorize("@ss.hasPermi('system:quality:list')")
    @GetMapping("/list")
    public TableDataInfo list(HwBridgeQuality hwBridgeQuality)
    {
        startPage();
        List<HwBridgeQuality> list = hwBridgeQualityService.selectHwBridgeQualityList(hwBridgeQuality);
        return getDataTable(list);
    }

    /**
     * 导出桥梁质量列表
     */
    @PreAuthorize("@ss.hasPermi('system:quality:export')")
    @Log(title = "桥梁质量", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HwBridgeQuality hwBridgeQuality)
    {
        List<HwBridgeQuality> list = hwBridgeQualityService.selectHwBridgeQualityList(hwBridgeQuality);
        ExcelUtil<HwBridgeQuality> util = new ExcelUtil<HwBridgeQuality>(HwBridgeQuality.class);
        util.exportExcel(response, list, "桥梁质量数据");
    }

    /**
     * 获取桥梁质量详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:quality:query')")
    @GetMapping(value = "/{routeQualityId}")
    public AjaxResult getInfo(@PathVariable("routeQualityId") Long routeQualityId)
    {
        return success(hwBridgeQualityService.selectHwBridgeQualityByRouteQualityId(routeQualityId));
    }

    /**
     * 新增桥梁质量
     */
    @PreAuthorize("@ss.hasPermi('system:quality:add')")
    @Log(title = "桥梁质量", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HwBridgeQuality hwBridgeQuality)
    {
        return toAjax(hwBridgeQualityService.insertHwBridgeQuality(hwBridgeQuality));
    }

    /**
     * 修改桥梁质量
     */
    @PreAuthorize("@ss.hasPermi('system:quality:edit')")
    @Log(title = "桥梁质量", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HwBridgeQuality hwBridgeQuality)
    {
        return toAjax(hwBridgeQualityService.updateHwBridgeQuality(hwBridgeQuality));
    }

    /**
     * 删除桥梁质量
     */
    @PreAuthorize("@ss.hasPermi('system:quality:remove')")
    @Log(title = "桥梁质量", businessType = BusinessType.DELETE)
	@DeleteMapping("/{routeQualityIds}")
    public AjaxResult remove(@PathVariable Long[] routeQualityIds)
    {
        return toAjax(hwBridgeQualityService.deleteHwBridgeQualityByRouteQualityIds(routeQualityIds));
    }
}
