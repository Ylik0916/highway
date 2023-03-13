package com.hg.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hg.common.annotation.Log;
import com.hg.common.core.controller.BaseController;
import com.hg.common.core.domain.AjaxResult;
import com.hg.common.core.page.TableDataInfo;
import com.hg.common.enums.BusinessType;
import com.hg.common.utils.poi.ExcelUtil;
import com.hg.system.domain.HwMaintainPlanDelicacy;
import com.hg.system.service.IHwMaintainPlanDelicacyService;
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
 * 精品示范Controller
 * 
 * @author ruoyi
 * @date 2023-03-08
 */
@RestController
@RequestMapping("/system/delicacy")
public class HwMaintainPlanDelicacyController extends BaseController
{
    @Autowired
    private IHwMaintainPlanDelicacyService hwMaintainPlanDelicacyService;

    /**
     * 查询精品示范列表
     */
    @PreAuthorize("@ss.hasPermi('system:delicacy:list')")
    @GetMapping("/list")
    public TableDataInfo list(HwMaintainPlanDelicacy hwMaintainPlanDelicacy)
    {
        startPage();
        List<HwMaintainPlanDelicacy> list = hwMaintainPlanDelicacyService.selectHwMaintainPlanDelicacyList(hwMaintainPlanDelicacy);
        return getDataTable(list);
    }

    /**
     * 导出精品示范列表
     */
    @PreAuthorize("@ss.hasPermi('system:delicacy:export')")
    @Log(title = "精品示范", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HwMaintainPlanDelicacy hwMaintainPlanDelicacy)
    {
        List<HwMaintainPlanDelicacy> list = hwMaintainPlanDelicacyService.selectHwMaintainPlanDelicacyList(hwMaintainPlanDelicacy);
        ExcelUtil<HwMaintainPlanDelicacy> util = new ExcelUtil<HwMaintainPlanDelicacy>(HwMaintainPlanDelicacy.class);
        util.exportExcel(response, list, "精品示范数据");
    }

    /**
     * 获取精品示范详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:delicacy:query')")
    @GetMapping(value = "/{mpdid}")
    public AjaxResult getInfo(@PathVariable("mpdid") Long mpdid)
    {
        return success(hwMaintainPlanDelicacyService.selectHwMaintainPlanDelicacyByMpdid(mpdid));
    }

    /**
     * 新增精品示范
     */
    @PreAuthorize("@ss.hasPermi('system:delicacy:add')")
    @Log(title = "精品示范", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HwMaintainPlanDelicacy hwMaintainPlanDelicacy)
    {
        return toAjax(hwMaintainPlanDelicacyService.insertHwMaintainPlanDelicacy(hwMaintainPlanDelicacy));
    }

    /**
     * 修改精品示范
     */
    @PreAuthorize("@ss.hasPermi('system:delicacy:edit')")
    @Log(title = "精品示范", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HwMaintainPlanDelicacy hwMaintainPlanDelicacy)
    {
        return toAjax(hwMaintainPlanDelicacyService.updateHwMaintainPlanDelicacy(hwMaintainPlanDelicacy));
    }

    /**
     * 删除精品示范
     */
    @PreAuthorize("@ss.hasPermi('system:delicacy:remove')")
    @Log(title = "精品示范", businessType = BusinessType.DELETE)
	@DeleteMapping("/{mpdids}")
    public AjaxResult remove(@PathVariable Long[] mpdids)
    {
        return toAjax(hwMaintainPlanDelicacyService.deleteHwMaintainPlanDelicacyByMpdids(mpdids));
    }
}
