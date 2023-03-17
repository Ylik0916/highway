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
import com.hg.system.domain.HwTownshipInformation;
import com.hg.system.service.IHwTownshipInformationService;
import com.hg.common.utils.poi.ExcelUtil;
import com.hg.common.core.page.TableDataInfo;

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
     * 查询乡镇列表
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
     * 导出乡镇列表
     */
    @PreAuthorize("@ss.hasPermi('system:township:export')")
    @Log(title = "乡镇", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HwTownshipInformation hwTownshipInformation)
    {
        List<HwTownshipInformation> list = hwTownshipInformationService.selectHwTownshipInformationList(hwTownshipInformation);
        ExcelUtil<HwTownshipInformation> util = new ExcelUtil<HwTownshipInformation>(HwTownshipInformation.class);
        util.exportExcel(response, list, "乡镇数据");
    }

    /**
     * 获取乡镇详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:township:query')")
    @GetMapping(value = "/{townshipId}")
    public AjaxResult getInfo(@PathVariable("townshipId") Long townshipId)
    {
        return success(hwTownshipInformationService.selectHwTownshipInformationByTownshipId(townshipId));
    }

    /**
     * 新增乡镇
     */
    @PreAuthorize("@ss.hasPermi('system:township:add')")
    @Log(title = "乡镇", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HwTownshipInformation hwTownshipInformation)
    {
        return toAjax(hwTownshipInformationService.insertHwTownshipInformation(hwTownshipInformation));
    }

    /**
     * 修改乡镇
     */
    @PreAuthorize("@ss.hasPermi('system:township:edit')")
    @Log(title = "乡镇", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HwTownshipInformation hwTownshipInformation)
    {
        return toAjax(hwTownshipInformationService.updateHwTownshipInformation(hwTownshipInformation));
    }

    /**
     * 删除乡镇
     */
    @PreAuthorize("@ss.hasPermi('system:township:remove')")
    @Log(title = "乡镇", businessType = BusinessType.DELETE)
	@DeleteMapping("/{townshipIds}")
    public AjaxResult remove(@PathVariable Long[] townshipIds)
    {
        return toAjax(hwTownshipInformationService.deleteHwTownshipInformationByTownshipIds(townshipIds));
    }
}
