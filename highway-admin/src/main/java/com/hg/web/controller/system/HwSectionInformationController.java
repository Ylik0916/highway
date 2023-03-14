package com.hg.web.controller.system;

import com.hg.common.annotation.Log;
import com.hg.common.core.controller.BaseController;
import com.hg.common.core.domain.AjaxResult;
import com.hg.common.core.page.TableDataInfo;
import com.hg.common.enums.BusinessType;
import com.hg.common.utils.poi.ExcelUtil;
import com.hg.system.domain.HwSectionInformation;
import com.hg.system.service.IHwSectionInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 路段信息Controller
 *
 * @author ruoyi
 * @date 2023-03-13
 */
@RestController
@RequestMapping("/system/sectionInformation")
public class HwSectionInformationController extends BaseController
{
    @Autowired
    private IHwSectionInformationService hwSectionInformationService;

    /**
     * 查询路段信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:sectionInformation:list')")
    @GetMapping("/list")
    public TableDataInfo list(HwSectionInformation hwSectionInformation)
    {
        startPage();
        List<HwSectionInformation> list = hwSectionInformationService.selectHwSectionInformationList(hwSectionInformation);
        return getDataTable(list);
    }

    /**
     * 导出路段信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:sectionInformation:export')")
    @Log(title = "路段信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HwSectionInformation hwSectionInformation)
    {
        List<HwSectionInformation> list = hwSectionInformationService.selectHwSectionInformationList(hwSectionInformation);
        ExcelUtil<HwSectionInformation> util = new ExcelUtil<HwSectionInformation>(HwSectionInformation.class);
        util.exportExcel(response, list, "路段信息数据");
    }

    /**
     * 获取路段信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:sectionInformation:query')")
    @GetMapping(value = "/{roadsectionid}")
    public AjaxResult getInfo(@PathVariable("roadsectionid") Long roadsectionid)
    {
        return success(hwSectionInformationService.selectHwSectionInformationByRoadsectionid(roadsectionid));
    }

    /**
     * 新增路段信息
     */
    @PreAuthorize("@ss.hasPermi('system:sectionInformation:add')")
    @Log(title = "路段信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HwSectionInformation hwSectionInformation)
    {
        return toAjax(hwSectionInformationService.insertHwSectionInformation(hwSectionInformation));
    }

    /**
     * 修改路段信息
     */
    @PreAuthorize("@ss.hasPermi('system:sectionInformation:edit')")
    @Log(title = "路段信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HwSectionInformation hwSectionInformation)
    {
        return toAjax(hwSectionInformationService.updateHwSectionInformation(hwSectionInformation));
    }

    /**
     * 删除路段信息
     */
    @PreAuthorize("@ss.hasPermi('system:sectionInformation:remove')")
    @Log(title = "路段信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{roadsectionids}")
    public AjaxResult remove(@PathVariable Long[] roadsectionids)
    {
        return toAjax(hwSectionInformationService.deleteHwSectionInformationByRoadsectionids(roadsectionids));
    }
}
