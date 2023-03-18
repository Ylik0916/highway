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
import com.hg.system.domain.HwUnitEvaluate;
import com.hg.system.service.IHwUnitEvaluateService;
import com.hg.common.utils.poi.ExcelUtil;
import com.hg.common.core.page.TableDataInfo;

/**
 * 从业单位评价Controller
 *
 * @author W-yf
 * @date 2023-03-15
 */
@RestController
@RequestMapping("/system/evaluate")
public class HwUnitEvaluateController extends BaseController {
    @Autowired
    private IHwUnitEvaluateService hwUnitEvaluateService;

    /**
     * 查询从业单位评价列表
     */
    @PreAuthorize("@ss.hasPermi('system:evaluate:list')")
    @GetMapping("/list")
    public TableDataInfo list(HwUnitEvaluate hwUnitEvaluate) {
        startPage();
        List<HwUnitEvaluate> list = hwUnitEvaluateService.selectHwUnitEvaluateList(hwUnitEvaluate);
        return getDataTable(list);
    }

    /**
     * 导出从业单位评价列表
     */
    @PreAuthorize("@ss.hasPermi('system:evaluate:export')")
    @Log(title = "从业单位评价", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HwUnitEvaluate hwUnitEvaluate) {
        List<HwUnitEvaluate> list = hwUnitEvaluateService.selectHwUnitEvaluateList(hwUnitEvaluate);
        ExcelUtil<HwUnitEvaluate> util = new ExcelUtil<HwUnitEvaluate>(HwUnitEvaluate.class);
        util.exportExcel(response, list, "从业单位评价数据");
    }

    /**
     * 获取从业单位评价详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:evaluate:query')")
    @GetMapping(value = "/{unitId}")
    public AjaxResult getInfo(@PathVariable("unitId") Long unitId) {
        return success(hwUnitEvaluateService.selectHwUnitEvaluateByUnitId(unitId));
    }

    /**
     * 新增从业单位评价
     */
    @PreAuthorize("@ss.hasPermi('system:evaluate:add')")
    @Log(title = "从业单位评价", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HwUnitEvaluate hwUnitEvaluate) {
        return toAjax(hwUnitEvaluateService.insertHwUnitEvaluate(hwUnitEvaluate));
    }

    /**
     * 修改从业单位评价
     */
    @PreAuthorize("@ss.hasPermi('system:evaluate:edit')")
    @Log(title = "从业单位评价", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HwUnitEvaluate hwUnitEvaluate) {
        return toAjax(hwUnitEvaluateService.updateHwUnitEvaluate(hwUnitEvaluate));
    }

    /**
     * 删除从业单位评价
     */
    @PreAuthorize("@ss.hasPermi('system:evaluate:remove')")
    @Log(title = "从业单位评价", businessType = BusinessType.DELETE)
    @DeleteMapping("/{unitIds}")
    public AjaxResult remove(@PathVariable Long[] unitIds) {
        return toAjax(hwUnitEvaluateService.deleteHwUnitEvaluateByUnitIds(unitIds));
    }

    /**
     * 查询从业单位评价列表和成绩
     *
     * @param hwUnitEvaluate 从业单位评价
     * @return 从业单位评价集合
     */
    @PreAuthorize("@ss.hasPermi('system:evaluate:list')")
    @GetMapping("/scoreList")
    public TableDataInfo scoreList(HwUnitEvaluate hwUnitEvaluate) {
        startPage();
        List<HwUnitEvaluate> list = hwUnitEvaluateService.selectHwUnitEvaluateAndScoreList(hwUnitEvaluate);
        return getDataTable(list);
    }
}
