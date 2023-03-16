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
import com.hg.system.domain.HwMaintainRegion;
import com.hg.system.service.IHwMaintainRegionService;
import com.hg.common.utils.poi.ExcelUtil;
import com.hg.common.core.page.TableDataInfo;

/**
 * 养护单位关联行政区Controller
 * 
 * @author hg
 * @date 2023-03-14
 */
@RestController
@RequestMapping("/system/region")
public class HwMaintainRegionController extends BaseController
{
    @Autowired
    private IHwMaintainRegionService hwMaintainRegionService;

    /**
     * 查询养护单位关联行政区列表
     */
    @PreAuthorize("@ss.hasPermi('system:region:list')")
    @GetMapping("/list")
    public TableDataInfo list(HwMaintainRegion hwMaintainRegion)
    {
        startPage();
        List<HwMaintainRegion> list = hwMaintainRegionService.selectHwMaintainRegionList(hwMaintainRegion);
        return getDataTable(list);
    }

    /**
     * 导出养护单位关联行政区列表
     */
    @PreAuthorize("@ss.hasPermi('system:region:export')")
    @Log(title = "养护单位关联行政区", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HwMaintainRegion hwMaintainRegion)
    {
        List<HwMaintainRegion> list = hwMaintainRegionService.selectHwMaintainRegionList(hwMaintainRegion);
        ExcelUtil<HwMaintainRegion> util = new ExcelUtil<HwMaintainRegion>(HwMaintainRegion.class);
        util.exportExcel(response, list, "养护单位关联行政区数据");
    }

    /**
     * 获取养护单位关联行政区详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:region:query')")
    @GetMapping(value = "/{mrid}")
    public AjaxResult getInfo(@PathVariable("mrid") Long mrid)
    {
        return success(hwMaintainRegionService.selectHwMaintainRegionByMrid(mrid));
    }

    /**
     * 新增养护单位关联行政区
     */
    @PreAuthorize("@ss.hasPermi('system:region:add')")
    @Log(title = "养护单位关联行政区", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HwMaintainRegion hwMaintainRegion)
    {
        return toAjax(hwMaintainRegionService.insertHwMaintainRegion(hwMaintainRegion));
    }

    /**
     * 修改养护单位关联行政区
     */
    @PreAuthorize("@ss.hasPermi('system:region:edit')")
    @Log(title = "养护单位关联行政区", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HwMaintainRegion hwMaintainRegion)
    {
        return toAjax(hwMaintainRegionService.updateHwMaintainRegion(hwMaintainRegion));
    }

    /**
     * 删除养护单位关联行政区
     */
    @PreAuthorize("@ss.hasPermi('system:region:remove')")
    @Log(title = "养护单位关联行政区", businessType = BusinessType.DELETE)
	@DeleteMapping("/{mrids}")
    public AjaxResult remove(@PathVariable Long[] mrids)
    {
        return toAjax(hwMaintainRegionService.deleteHwMaintainRegionByMrids(mrids));
    }
}
