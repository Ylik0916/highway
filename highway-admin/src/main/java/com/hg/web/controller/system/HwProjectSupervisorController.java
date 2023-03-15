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
import com.hg.system.domain.HwProjectSupervisor;
import com.hg.system.service.IHwProjectSupervisorService;
import com.hg.common.utils.poi.ExcelUtil;
import com.hg.common.core.page.TableDataInfo;

/**
 * 项目信息管理==》监理标段Controller
 * 
 * @author W-yf
 * @date 2023-03-14
 */
@RestController
@RequestMapping("/system/supervisor")
public class HwProjectSupervisorController extends BaseController
{
    @Autowired
    private IHwProjectSupervisorService hwProjectSupervisorService;

    /**
     * 查询项目信息管理==》监理标段列表
     */
    @PreAuthorize("@ss.hasPermi('system:supervisor:list')")
    @GetMapping("/list")
    public TableDataInfo list(HwProjectSupervisor hwProjectSupervisor)
    {
        startPage();
        List<HwProjectSupervisor> list = hwProjectSupervisorService.selectHwProjectSupervisorList(hwProjectSupervisor);
        return getDataTable(list);
    }

    /**
     * 导出项目信息管理==》监理标段列表
     */
    @PreAuthorize("@ss.hasPermi('system:supervisor:export')")
    @Log(title = "项目信息管理==》监理标段", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HwProjectSupervisor hwProjectSupervisor)
    {
        List<HwProjectSupervisor> list = hwProjectSupervisorService.selectHwProjectSupervisorList(hwProjectSupervisor);
        ExcelUtil<HwProjectSupervisor> util = new ExcelUtil<HwProjectSupervisor>(HwProjectSupervisor.class);
        util.exportExcel(response, list, "项目信息管理==》监理标段数据");
    }

    /**
     * 获取项目信息管理==》监理标段详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:supervisor:query')")
    @GetMapping(value = "/{supervisorId}")
    public AjaxResult getInfo(@PathVariable("supervisorId") Long supervisorId)
    {
        return success(hwProjectSupervisorService.selectHwProjectSupervisorBySupervisorId(supervisorId));
    }

    /**
     * 新增项目信息管理==》监理标段
     */
    @PreAuthorize("@ss.hasPermi('system:supervisor:add')")
    @Log(title = "项目信息管理==》监理标段", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HwProjectSupervisor hwProjectSupervisor)
    {
        return toAjax(hwProjectSupervisorService.insertHwProjectSupervisor(hwProjectSupervisor));
    }

    /**
     * 修改项目信息管理==》监理标段
     */
    @PreAuthorize("@ss.hasPermi('system:supervisor:edit')")
    @Log(title = "项目信息管理==》监理标段", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HwProjectSupervisor hwProjectSupervisor)
    {
        return toAjax(hwProjectSupervisorService.updateHwProjectSupervisor(hwProjectSupervisor));
    }

    /**
     * 删除项目信息管理==》监理标段
     */
    @PreAuthorize("@ss.hasPermi('system:supervisor:remove')")
    @Log(title = "项目信息管理==》监理标段", businessType = BusinessType.DELETE)
	@DeleteMapping("/{supervisorIds}")
    public AjaxResult remove(@PathVariable Long[] supervisorIds)
    {
        return toAjax(hwProjectSupervisorService.deleteHwProjectSupervisorBySupervisorIds(supervisorIds));
    }
}
