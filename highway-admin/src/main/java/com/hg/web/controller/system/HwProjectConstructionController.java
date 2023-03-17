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
import com.hg.system.domain.HwProjectConstruction;
import com.hg.system.service.IHwProjectConstructionService;
import com.hg.common.utils.poi.ExcelUtil;
import com.hg.common.core.page.TableDataInfo;

/**
 * W-yfController
 *
 * @author W-yf
 * @date 2023-03-15
 */
@RestController
@RequestMapping("/system/construction")
public class HwProjectConstructionController extends BaseController {
    @Autowired
    private IHwProjectConstructionService hwProjectConstructionService;

    /**
     * 查询W-yf列表
     */
    @PreAuthorize("@ss.hasPermi('system:construction:list')")
    @GetMapping("/list")
    public TableDataInfo list(HwProjectConstruction hwProjectConstruction) {
        startPage();
        List<HwProjectConstruction> list = hwProjectConstructionService.selectHwProjectConstructionList(hwProjectConstruction);
        return getDataTable(list);
    }

    /**
     * 导出W-yf列表
     */
    @PreAuthorize("@ss.hasPermi('system:construction:export')")
    @Log(title = "W-yf", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HwProjectConstruction hwProjectConstruction) {
        List<HwProjectConstruction> list = hwProjectConstructionService.selectHwProjectConstructionList(hwProjectConstruction);
        ExcelUtil<HwProjectConstruction> util = new ExcelUtil<HwProjectConstruction>(HwProjectConstruction.class);
        util.exportExcel(response, list, "W-yf数据");
    }

    /**
     * 获取W-yf详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:construction:query')")
    @GetMapping(value = "/{constructionId}")
    public AjaxResult getInfo(@PathVariable("constructionId") Long constructionId) {
        return success(hwProjectConstructionService.selectHwProjectConstructionByConstructionId(constructionId));
    }

    /**
     * 新增W-yf
     */
    @PreAuthorize("@ss.hasPermi('system:construction:add')")
    @Log(title = "W-yf", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HwProjectConstruction hwProjectConstruction) {
        return toAjax(hwProjectConstructionService.insertHwProjectConstruction(hwProjectConstruction));
    }

    /**
     * 修改W-yf
     */
    @PreAuthorize("@ss.hasPermi('system:construction:edit')")
    @Log(title = "W-yf", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HwProjectConstruction hwProjectConstruction) {
        return toAjax(hwProjectConstructionService.updateHwProjectConstruction(hwProjectConstruction));
    }

    /**
     * 删除W-yf
     */
    @PreAuthorize("@ss.hasPermi('system:construction:remove')")
    @Log(title = "W-yf", businessType = BusinessType.DELETE)
    @DeleteMapping("/{constructionIds}")
    public AjaxResult remove(@PathVariable Long[] constructionIds) {
        return toAjax(hwProjectConstructionService.deleteHwProjectConstructionByConstructionIds(constructionIds));
    }

    /**
     * 新版查询施工
     *
     * @param constructionProjectId 项目id
     * @return W-yf集合
     */
    @PreAuthorize("@ss.hasPermi('system:construction:list')")
    @GetMapping("/constructionList/{constructionProjectId}")
    public TableDataInfo selectHwProjectConstructionList(@PathVariable("constructionProjectId") Integer constructionProjectId) {
        startPage();
        List<HwProjectConstruction> list = hwProjectConstructionService.selectHwProjectConstructionSupervisor(constructionProjectId);
        return getDataTable(list);
    }
}
