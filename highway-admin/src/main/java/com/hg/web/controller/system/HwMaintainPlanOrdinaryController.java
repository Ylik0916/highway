package com.hg.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hg.common.annotation.Log;
import com.hg.common.core.controller.BaseController;
import com.hg.common.core.domain.AjaxResult;
import com.hg.common.core.page.TableDataInfo;
import com.hg.common.enums.BusinessType;
import com.hg.common.utils.poi.ExcelUtil;
import com.hg.system.domain.HwMaintainPlanOrdinary;
import com.hg.system.service.IHwMaintainPlanOrdinaryService;
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
 * 一般养护Controller
 * 
 * @author ruoyi
 * @date 2023-03-08
 */
@RestController
@RequestMapping("/system/ordinary")
public class HwMaintainPlanOrdinaryController extends BaseController
{
    @Autowired
    private IHwMaintainPlanOrdinaryService hwMaintainPlanOrdinaryService;

    /**
     * 查询一般养护列表
     */
    @PreAuthorize("@ss.hasPermi('system:ordinary:list')")
    @GetMapping("/list")
    public TableDataInfo list(HwMaintainPlanOrdinary hwMaintainPlanOrdinary)
    {
        startPage();
        List<HwMaintainPlanOrdinary> list = hwMaintainPlanOrdinaryService.selectHwMaintainPlanOrdinaryList(hwMaintainPlanOrdinary);
        return getDataTable(list);
    }

    /**
     * 导出一般养护列表
     */
    @PreAuthorize("@ss.hasPermi('system:ordinary:export')")
    @Log(title = "一般养护", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HwMaintainPlanOrdinary hwMaintainPlanOrdinary)
    {
        List<HwMaintainPlanOrdinary> list = hwMaintainPlanOrdinaryService.selectHwMaintainPlanOrdinaryList(hwMaintainPlanOrdinary);
        ExcelUtil<HwMaintainPlanOrdinary> util = new ExcelUtil<HwMaintainPlanOrdinary>(HwMaintainPlanOrdinary.class);
        util.exportExcel(response, list, "一般养护数据");
    }

    /**
     * 获取一般养护详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:ordinary:query')")
    @GetMapping(value = "/{mpoid}")
    public AjaxResult getInfo(@PathVariable("mpoid") Long mpoid)
    {
        return success(hwMaintainPlanOrdinaryService.selectHwMaintainPlanOrdinaryByMpoid(mpoid));
    }

    /**
     * 新增一般养护
     */
    @PreAuthorize("@ss.hasPermi('system:ordinary:add')")
    @Log(title = "一般养护", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HwMaintainPlanOrdinary hwMaintainPlanOrdinary)
    {
        return toAjax(hwMaintainPlanOrdinaryService.insertHwMaintainPlanOrdinary(hwMaintainPlanOrdinary));
    }

    /**
     * 修改一般养护
     */
    @PreAuthorize("@ss.hasPermi('system:ordinary:edit')")
    @Log(title = "一般养护", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HwMaintainPlanOrdinary hwMaintainPlanOrdinary)
    {
        return toAjax(hwMaintainPlanOrdinaryService.updateHwMaintainPlanOrdinary(hwMaintainPlanOrdinary));
    }

    /**
     * 删除一般养护
     */
    @PreAuthorize("@ss.hasPermi('system:ordinary:remove')")
    @Log(title = "一般养护", businessType = BusinessType.DELETE)
	@DeleteMapping("/{mpoids}")
    public AjaxResult remove(@PathVariable Long[] mpoids)
    {
        return toAjax(hwMaintainPlanOrdinaryService.deleteHwMaintainPlanOrdinaryByMpoids(mpoids));
    }
}
