package com.hg.web.controller.system;

import com.hg.common.annotation.Log;
import com.hg.common.core.controller.BaseController;
import com.hg.common.core.domain.AjaxResult;
import com.hg.common.core.page.TableDataInfo;
import com.hg.common.enums.BusinessType;
import com.hg.common.utils.poi.ExcelUtil;
import com.hg.system.domain.HwTownshipInformation;
import com.hg.system.service.IHwTownshipInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 乡镇Controller
 *
 * @author ruoyi
 * @date 2023-03-14
 */
@RestController
@RequestMapping("/system/township")
public class HwTownshipInformationController extends BaseController
{
    @Autowired
    private IHwTownshipInformationService hwTownshipInformationService;

    /**
     * 查询乡镇信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:township:list')")
    @GetMapping("/list")
    public TableDataInfo list(HwTownshipInformation hwTownshipInformation)
    {
        startPage();
        List<HwTownshipInformation> list = hwTownshipInformationService.selectHwTownshipInformationList(hwTownshipInformation);
        return getDataTable(list);
    }

    /**
     * 导出乡镇信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:township:export')")
    @Log(title = "乡镇信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HwTownshipInformation hwTownshipInformation)
    {
        List<HwTownshipInformation> list = hwTownshipInformationService.selectHwTownshipInformationList(hwTownshipInformation);
        ExcelUtil<HwTownshipInformation> util = new ExcelUtil<HwTownshipInformation>(HwTownshipInformation.class);
        util.exportExcel(response, list, "乡镇信息数据");
    }

    /**
     * 获取乡镇信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:township:query')")
    @GetMapping(value = "/{townshipId}")
    public AjaxResult getInfo(@PathVariable("townshipId") Long townshipId)
    {
        return success(hwTownshipInformationService.selectHwTownshipInformationByTownshipId(townshipId));
    }

    /**
     * 新增乡镇信息
     */
    @PreAuthorize("@ss.hasPermi('system:township:add')")
    @Log(title = "乡镇信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HwTownshipInformation hwTownshipInformation)
    {
        return toAjax(hwTownshipInformationService.insertHwTownshipInformation(hwTownshipInformation));
    }

    /**
     * 修改乡镇信息
     */
    @PreAuthorize("@ss.hasPermi('system:township:edit')")
    @Log(title = "乡镇信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HwTownshipInformation hwTownshipInformation)
    {
        return toAjax(hwTownshipInformationService.updateHwTownshipInformation(hwTownshipInformation));
    }

    /**
     * 删除乡镇信息
     */
    @PreAuthorize("@ss.hasPermi('system:township:remove')")
    @Log(title = "乡镇信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{townshipIds}")
    public AjaxResult remove(@PathVariable Long[] townshipIds)
    {
        return toAjax(hwTownshipInformationService.deleteHwTownshipInformationByTownshipIds(townshipIds));
    }
}
