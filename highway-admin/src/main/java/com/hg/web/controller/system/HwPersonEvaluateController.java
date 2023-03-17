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
import com.hg.system.domain.HwPersonEvaluate;
import com.hg.system.service.IHwPersonEvaluateService;
import com.hg.common.utils.poi.ExcelUtil;
import com.hg.common.core.page.TableDataInfo;

/**
 * 从业人员评价Controller
 * 
 * @author W-yf
 * @date 2023-03-07
 */
@RestController
@RequestMapping("/system/person")
public class HwPersonEvaluateController extends BaseController
{
    @Autowired
    private IHwPersonEvaluateService hwPersonEvaluateService;

    /**
     * 查询从业人员评价列表
     */
    @PreAuthorize("@ss.hasPermi('system:person:list')")
    @GetMapping("/list")
    public TableDataInfo list(HwPersonEvaluate hwPersonEvaluate)
    {
        startPage();
        List<HwPersonEvaluate> list = hwPersonEvaluateService.selectHwPersonEvaluateList(hwPersonEvaluate);
        return getDataTable(list);
    }

    /**
     * 导出从业人员评价列表
     */
    @PreAuthorize("@ss.hasPermi('system:person:export')")
    @Log(title = "从业人员评价", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HwPersonEvaluate hwPersonEvaluate)
    {
        List<HwPersonEvaluate> list = hwPersonEvaluateService.selectHwPersonEvaluateList(hwPersonEvaluate);
        ExcelUtil<HwPersonEvaluate> util = new ExcelUtil<HwPersonEvaluate>(HwPersonEvaluate.class);
        util.exportExcel(response, list, "从业人员评价数据");
    }

    /**
     * 获取从业人员评价详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:person:query')")
    @GetMapping(value = "/{personId}")
    public AjaxResult getInfo(@PathVariable("personId") Long personId)
    {
        return success(hwPersonEvaluateService.selectHwPersonEvaluateByPersonId(personId));
    }

    /**
     * 新增从业人员评价
     */
    @PreAuthorize("@ss.hasPermi('system:person:add')")
    @Log(title = "从业人员评价", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HwPersonEvaluate hwPersonEvaluate)
    {
        return toAjax(hwPersonEvaluateService.insertHwPersonEvaluate(hwPersonEvaluate));
    }

    /**
     * 修改从业人员评价
     */
    @PreAuthorize("@ss.hasPermi('system:person:edit')")
    @Log(title = "从业人员评价", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HwPersonEvaluate hwPersonEvaluate)
    {
        return toAjax(hwPersonEvaluateService.updateHwPersonEvaluate(hwPersonEvaluate));
    }

    /**
     * 删除从业人员评价
     */
    @PreAuthorize("@ss.hasPermi('system:person:remove')")
    @Log(title = "从业人员评价", businessType = BusinessType.DELETE)
	@DeleteMapping("/{personIds}")
    public AjaxResult remove(@PathVariable Long[] personIds)
    {
        return toAjax(hwPersonEvaluateService.deleteHwPersonEvaluateByPersonIds(personIds));
    }
}
