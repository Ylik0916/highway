package com.hg.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hg.common.annotation.Log;
import com.hg.common.core.controller.BaseController;
import com.hg.common.core.domain.AjaxResult;
import com.hg.common.core.page.TableDataInfo;
import com.hg.common.enums.BusinessType;
import com.hg.common.utils.poi.ExcelUtil;
import com.hg.system.domain.HwMaintainUnit;
import com.hg.system.service.IHwMaintainUnitService;
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

/**
 * 养护单位管理Controller
 * 
 * @author ruoyi
 * @date 2023-03-07
 */
@RestController
@RequestMapping("/system/unit")
public class HwMaintainUnitController extends BaseController
{
    @Autowired
    private IHwMaintainUnitService hwMaintainUnitService;

    /**
     * 查询养护单位管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:unit:list')")
    @GetMapping("/list")
    public TableDataInfo list(HwMaintainUnit hwMaintainUnit)
    {
        startPage();
        List<HwMaintainUnit> list = hwMaintainUnitService.selectHwMaintainUnitList(hwMaintainUnit);
        return getDataTable(list);
    }

    /**
     * 导出养护单位管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:unit:export')")
    @Log(title = "养护单位管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HwMaintainUnit hwMaintainUnit)
    {
        List<HwMaintainUnit> list = hwMaintainUnitService.selectHwMaintainUnitList(hwMaintainUnit);
        ExcelUtil<HwMaintainUnit> util = new ExcelUtil<HwMaintainUnit>(HwMaintainUnit.class);
        util.exportExcel(response, list, "养护单位管理数据");
    }

    /**
     * 获取养护单位管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:unit:query')")
    @GetMapping(value = "/{muid}")
    public AjaxResult getInfo(@PathVariable("muid") Long muid)
    {
        return success(hwMaintainUnitService.selectHwMaintainUnitByMuid(muid));
    }

    /**
     * 新增养护单位管理
     */
    @PreAuthorize("@ss.hasPermi('system:unit:add')")
    @Log(title = "养护单位管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HwMaintainUnit hwMaintainUnit)
    {
        return toAjax(hwMaintainUnitService.insertHwMaintainUnit(hwMaintainUnit));
    }

    /**
     * 修改养护单位管理
     */
    @PreAuthorize("@ss.hasPermi('system:unit:edit')")
    @Log(title = "养护单位管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HwMaintainUnit hwMaintainUnit)
    {
        return toAjax(hwMaintainUnitService.updateHwMaintainUnit(hwMaintainUnit));
    }

    /**
     * 删除养护单位管理
     */
    @PreAuthorize("@ss.hasPermi('system:unit:remove')")
    @Log(title = "养护单位管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{muids}")
    public AjaxResult remove(@PathVariable Long[] muids)
    {
        return toAjax(hwMaintainUnitService.deleteHwMaintainUnitByMuids(muids));
    }
}
