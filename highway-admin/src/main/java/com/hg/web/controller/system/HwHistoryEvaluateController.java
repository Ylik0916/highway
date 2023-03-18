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
import com.hg.system.domain.HwHistoryEvaluate;
import com.hg.system.service.IHwHistoryEvaluateService;
import com.hg.common.utils.poi.ExcelUtil;
import com.hg.common.core.page.TableDataInfo;

/**
 * 从业单位及人员历史评价Controller
 *
 * @author W-yf
 * @date 2023-03-18
 */
@RestController
@RequestMapping("/system/historyEvaluate")
public class HwHistoryEvaluateController extends BaseController {
    @Autowired
    private IHwHistoryEvaluateService hwHistoryEvaluateService;

    /**
     * 查询从业单位及人员历史评价列表
     */
    @PreAuthorize("@ss.hasPermi('system:evaluate:list')")
    @GetMapping("/list")
    public TableDataInfo list(HwHistoryEvaluate hwHistoryEvaluate) {
        startPage();
        List<HwHistoryEvaluate> list = hwHistoryEvaluateService.selectHwHistoryEvaluateList(hwHistoryEvaluate);
        return getDataTable(list);
    }

    /**
     * 导出从业单位及人员历史评价列表
     */
    @PreAuthorize("@ss.hasPermi('system:evaluate:export')")
    @Log(title = "从业单位及人员历史评价", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HwHistoryEvaluate hwHistoryEvaluate) {
        List<HwHistoryEvaluate> list = hwHistoryEvaluateService.selectHwHistoryEvaluateList(hwHistoryEvaluate);
        ExcelUtil<HwHistoryEvaluate> util = new ExcelUtil<HwHistoryEvaluate>(HwHistoryEvaluate.class);
        util.exportExcel(response, list, "从业单位及人员历史评价数据");
    }

    /**
     * 获取从业单位及人员历史评价详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:evaluate:query')")
    @GetMapping(value = "/{evaluateId}")
    public AjaxResult getInfo(@PathVariable("evaluateId") Long evaluateId) {
        return success(hwHistoryEvaluateService.selectHwHistoryEvaluateByEvaluateId(evaluateId));
    }

    /**
     * 新增从业单位及人员历史评价
     */
    @PreAuthorize("@ss.hasPermi('system:evaluate:add')")
    @Log(title = "从业单位及人员历史评价", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HwHistoryEvaluate hwHistoryEvaluate) {
        return toAjax(hwHistoryEvaluateService.insertHwHistoryEvaluate(hwHistoryEvaluate));
    }

    /**
     * 修改从业单位及人员历史评价
     */
    @PreAuthorize("@ss.hasPermi('system:evaluate:edit')")
    @Log(title = "从业单位及人员历史评价", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HwHistoryEvaluate hwHistoryEvaluate) {
        return toAjax(hwHistoryEvaluateService.updateHwHistoryEvaluate(hwHistoryEvaluate));
    }

    /**
     * 删除从业单位及人员历史评价
     */
    @PreAuthorize("@ss.hasPermi('system:evaluate:remove')")
    @Log(title = "从业单位及人员历史评价", businessType = BusinessType.DELETE)
    @DeleteMapping("/{evaluateIds}")
    public AjaxResult remove(@PathVariable Long[] evaluateIds) {
        return toAjax(hwHistoryEvaluateService.deleteHwHistoryEvaluateByEvaluateIds(evaluateIds));
    }

    /**
     * 查询从业单位及人员历史评价根据单位id
     *
     * @param evaluateUnitId 从业单位及人员历史评价主键
     * @return 从业单位及人员历史评价
     */
    @PreAuthorize("@ss.hasPermi('system:evaluate:list')")
    @GetMapping("/historyUnitList/{evaluateUnitId}")
    public TableDataInfo historyUnitList(@PathVariable("evaluateUnitId") Integer evaluateUnitId) {
        startPage();
        List<HwHistoryEvaluate> list = hwHistoryEvaluateService.selectHwHistoryEvaluateByUnitId(evaluateUnitId);
        return getDataTable(list);
    }
}
