package com.hg.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hg.common.annotation.Log;
import com.hg.common.core.controller.BaseController;
import com.hg.common.core.domain.AjaxResult;
import com.hg.common.core.page.TableDataInfo;
import com.hg.common.enums.BusinessType;
import com.hg.common.utils.poi.ExcelUtil;
import com.hg.system.domain.HwMaintainDynamic;
import com.hg.system.service.IHwMaintainDynamicService;
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
 * 动态养护管理Controller
 * 
 * @author ruoyi
 * @date 2023-03-07
 */
@RestController
@RequestMapping("/system/dynamic")
public class HwMaintainDynamicController extends BaseController
{
    @Autowired
    private IHwMaintainDynamicService hwMaintainDynamicService;

    /**
     * 查询动态养护管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:dynamic:list')")
    @GetMapping("/list")
    public TableDataInfo list(HwMaintainDynamic hwMaintainDynamic)
    {
        startPage();
        List<HwMaintainDynamic> list = hwMaintainDynamicService.selectHwMaintainDynamicList(hwMaintainDynamic);
        return getDataTable(list);
    }

    /**
     * 导出动态养护管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:dynamic:export')")
    @Log(title = "动态养护管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HwMaintainDynamic hwMaintainDynamic)
    {
        List<HwMaintainDynamic> list = hwMaintainDynamicService.selectHwMaintainDynamicList(hwMaintainDynamic);
        ExcelUtil<HwMaintainDynamic> util = new ExcelUtil<HwMaintainDynamic>(HwMaintainDynamic.class);
        util.exportExcel(response, list, "动态养护管理数据");
    }

    /**
     * 获取动态养护管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:dynamic:query')")
    @GetMapping(value = "/{mdid}")
    public AjaxResult getInfo(@PathVariable("mdid") Long mdid)
    {
        return success(hwMaintainDynamicService.selectHwMaintainDynamicByMdid(mdid));
    }

    /**
     * 新增动态养护管理
     */
    @PreAuthorize("@ss.hasPermi('system:dynamic:add')")
    @Log(title = "动态养护管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HwMaintainDynamic hwMaintainDynamic)
    {
        return toAjax(hwMaintainDynamicService.insertHwMaintainDynamic(hwMaintainDynamic));
    }

    /**
     * 修改动态养护管理
     */
    @PreAuthorize("@ss.hasPermi('system:dynamic:edit')")
    @Log(title = "动态养护管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HwMaintainDynamic hwMaintainDynamic)
    {
        return toAjax(hwMaintainDynamicService.updateHwMaintainDynamic(hwMaintainDynamic));
    }

    /**
     * 删除动态养护管理
     */
    @PreAuthorize("@ss.hasPermi('system:dynamic:remove')")
    @Log(title = "动态养护管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{mdids}")
    public AjaxResult remove(@PathVariable Long[] mdids)
    {
        return toAjax(hwMaintainDynamicService.deleteHwMaintainDynamicByMdids(mdids));
    }
}
