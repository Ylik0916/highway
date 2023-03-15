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
import com.hg.system.domain.HwPlan;
import com.hg.system.service.IHwPlanService;
import com.hg.common.utils.poi.ExcelUtil;
import com.hg.common.core.page.TableDataInfo;

/**
 * 年度计划Controller
 * 
 * @author Cxh
 * @date 2023-03-15
 */
@RestController
@RequestMapping("/system/plan")
public class HwPlanController extends BaseController
{
    @Autowired
    private IHwPlanService hwPlanService;

    /**
     * 查询年度计划列表
     */
    @PreAuthorize("@ss.hasPermi('system:plan:list')")
    @GetMapping("/list")
    public TableDataInfo list(HwPlan hwPlan)
    {
        startPage();
        List<HwPlan> list = hwPlanService.selectHwPlanList(hwPlan);
        return getDataTable(list);
    }

    /**
     * 导出年度计划列表
     */
    @PreAuthorize("@ss.hasPermi('system:plan:export')")
    @Log(title = "年度计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HwPlan hwPlan)
    {
        List<HwPlan> list = hwPlanService.selectHwPlanList(hwPlan);
        ExcelUtil<HwPlan> util = new ExcelUtil<HwPlan>(HwPlan.class);
        util.exportExcel(response, list, "年度计划数据");
    }

    /**
     * 获取年度计划详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:plan:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(hwPlanService.selectHwPlanById(id));
    }

    /**
     * 新增年度计划
     */
    @PreAuthorize("@ss.hasPermi('system:plan:add')")
    @Log(title = "年度计划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HwPlan hwPlan)
    {
        return toAjax(hwPlanService.insertHwPlan(hwPlan));
    }

    /**
     * 修改年度计划
     */
    @PreAuthorize("@ss.hasPermi('system:plan:edit')")
    @Log(title = "年度计划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HwPlan hwPlan)
    {
        return toAjax(hwPlanService.updateHwPlan(hwPlan));
    }

    /**
     * 删除年度计划
     */
    @PreAuthorize("@ss.hasPermi('system:plan:remove')")
    @Log(title = "年度计划", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(hwPlanService.deleteHwPlanByIds(ids));
    }
}
