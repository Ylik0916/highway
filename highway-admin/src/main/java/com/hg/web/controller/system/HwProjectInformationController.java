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
import com.hg.system.domain.HwProjectInformation;
import com.hg.system.service.IHwProjectInformationService;
import com.hg.common.utils.poi.ExcelUtil;
import com.hg.common.core.page.TableDataInfo;

/**
 * 项目信息管理Controller
 *
 * @author W-yf
 * @date 2023-03-07
 */
@RestController
@RequestMapping("/system/projectInformation")
public class HwProjectInformationController extends BaseController {
    @Autowired
    private IHwProjectInformationService hwProjectInformationService;

    /**
     * 查询项目信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:projectInformation:list')")
    @GetMapping("/list")
    public TableDataInfo list(HwProjectInformation hwProjectInformation) {
        startPage();
        List<HwProjectInformation> list = hwProjectInformationService.selectHwProjectInformationList(hwProjectInformation);
        return getDataTable(list);
    }

    /**
     * 导出项目信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:projectInformation:export')")
    @Log(title = "项目信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HwProjectInformation hwProjectInformation) {
        List<HwProjectInformation> list = hwProjectInformationService.selectHwProjectInformationList(hwProjectInformation);
        ExcelUtil<HwProjectInformation> util = new ExcelUtil<HwProjectInformation>(HwProjectInformation.class);
        util.exportExcel(response, list, "项目信息管理数据");
    }

    /**
     * 获取项目信息管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:projectInformation:query')")
    @GetMapping(value = "/{projectId}")
    public AjaxResult getInfo(@PathVariable("projectId") Long projectId) {
        return success(hwProjectInformationService.selectHwProjectInformationByProjectId(projectId));
    }

    /**
     * 新增项目信息管理
     */
    @PreAuthorize("@ss.hasPermi('system:projectInformation:add')")
    @Log(title = "项目信息管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HwProjectInformation hwProjectInformation) {
        return toAjax(hwProjectInformationService.insertHwProjectInformation(hwProjectInformation));
    }

    /**
     * 修改项目信息管理
     */
    @PreAuthorize("@ss.hasPermi('system:projectInformation:edit')")
    @Log(title = "项目信息管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HwProjectInformation hwProjectInformation) {
        return toAjax(hwProjectInformationService.updateHwProjectInformation(hwProjectInformation));
    }

    /**
     * 删除项目信息管理
     */
    @PreAuthorize("@ss.hasPermi('system:projectInformation:remove')")
    @Log(title = "项目信息管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{projectIds}")
    public AjaxResult remove(@PathVariable Long[] projectIds) {
        return toAjax(hwProjectInformationService.deleteHwProjectInformationByProjectIds(projectIds));
    }

    /**
     * 新版查询，包含两个标段
     *
     * @param hwProjectInformation 项目信息管理
     * @return 结果
     */
    @PreAuthorize("@ss.hasPermi('system:projectInformation:list')")
    @GetMapping("/allInformationList")
    public TableDataInfo allInformationList(HwProjectInformation hwProjectInformation) {
        startPage();
        List<HwProjectInformation> list = hwProjectInformationService.selectAllProjectInformationList(hwProjectInformation);
        return getDataTable(list);
    }
}

