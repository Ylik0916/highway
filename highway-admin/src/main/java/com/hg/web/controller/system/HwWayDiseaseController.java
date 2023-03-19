package com.hg.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hg.common.annotation.Log;
import com.hg.common.core.controller.BaseController;
import com.hg.common.core.domain.AjaxResult;
import com.hg.common.core.page.TableDataInfo;
import com.hg.common.enums.BusinessType;
import com.hg.common.utils.poi.ExcelUtil;
import com.hg.system.domain.HwWayDisease;
import com.hg.system.service.IHwWayDiseaseService;
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
 * 道路病害管理Controller
 * 
 * @author ruoyi
 * @date 2023-03-08
 */
@RestController
@RequestMapping("/system/disease")
public class HwWayDiseaseController extends BaseController
{
    @Autowired
    private IHwWayDiseaseService hwWayDiseaseService;

    /**
     * 查询道路病害管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:disease:list')")
    @GetMapping("/list")
    public TableDataInfo list(HwWayDisease hwWayDisease)
    {
        startPage();
        List<HwWayDisease> list = hwWayDiseaseService.selectHwWayDiseaseList(hwWayDisease);
        return getDataTable(list);
    }

    /**
     * 导出道路病害管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:disease:export')")
    @Log(title = "道路病害管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HwWayDisease hwWayDisease)
    {
        List<HwWayDisease> list = hwWayDiseaseService.selectHwWayDiseaseList(hwWayDisease);
        ExcelUtil<HwWayDisease> util = new ExcelUtil<HwWayDisease>(HwWayDisease.class);
        util.exportExcel(response, list, "道路病害管理数据");
    }

    /**
     * 获取道路病害管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:disease:query')")
    @GetMapping(value = "/{wdid}")
    public AjaxResult getInfo(@PathVariable("wdid") Long wdid)
    {
        return success(hwWayDiseaseService.selectHwWayDiseaseByWdid(wdid));
    }

    /**
     * 新增道路病害管理
     */
    @PreAuthorize("@ss.hasPermi('system:disease:add')")
    @Log(title = "道路病害管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HwWayDisease hwWayDisease)
    {
        return toAjax(hwWayDiseaseService.insertHwWayDisease(hwWayDisease));
    }

    /**
     * 修改道路病害管理
     */
    @PreAuthorize("@ss.hasPermi('system:disease:edit')")
    @Log(title = "道路病害管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HwWayDisease hwWayDisease)
    {
        return toAjax(hwWayDiseaseService.updateHwWayDisease(hwWayDisease));
    }

    /**
     * 删除道路病害管理
     */
    @PreAuthorize("@ss.hasPermi('system:disease:remove')")
    @Log(title = "道路病害管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{wdids}")
    public AjaxResult remove(@PathVariable Long[] wdids)
    {
        return toAjax(hwWayDiseaseService.deleteHwWayDiseaseByWdids(wdids));
    }
}
