package com.hg.web.controller.system;

import com.hg.common.annotation.Log;
import com.hg.common.core.controller.BaseController;
import com.hg.common.core.domain.AjaxResult;
import com.hg.common.core.page.TableDataInfo;
import com.hg.common.enums.BusinessType;
import com.hg.common.utils.poi.ExcelUtil;
import com.hg.system.domain.HwModernIndustrialParkInformation;
import com.hg.system.service.IHwModernIndustrialParkInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 现代产业园Controller
 * 
 * @author ruoyi
 * @date 2023-03-14
 */
@RestController
@RequestMapping("/system/industrial")
public class HwModernIndustrialParkInformationController extends BaseController
{
    @Autowired
    private IHwModernIndustrialParkInformationService hwModernIndustrialParkInformationService;

    /**
     * 查询现代产业园列表
     */
    @PreAuthorize("@ss.hasPermi('system:industrial:list')")
    @GetMapping("/list")
    public TableDataInfo list(HwModernIndustrialParkInformation hwModernIndustrialParkInformation)
    {
        startPage();
        List<HwModernIndustrialParkInformation> list = hwModernIndustrialParkInformationService.selectHwModernIndustrialParkInformationList(hwModernIndustrialParkInformation);
        return getDataTable(list);
    }

    /**
     * 导出现代产业园列表
     */
    @PreAuthorize("@ss.hasPermi('system:industrial:export')")
    @Log(title = "现代产业园", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HwModernIndustrialParkInformation hwModernIndustrialParkInformation)
    {
        List<HwModernIndustrialParkInformation> list = hwModernIndustrialParkInformationService.selectHwModernIndustrialParkInformationList(hwModernIndustrialParkInformation);
        ExcelUtil<HwModernIndustrialParkInformation> util = new ExcelUtil<HwModernIndustrialParkInformation>(HwModernIndustrialParkInformation.class);
        util.exportExcel(response, list, "现代产业园数据");
    }

    /**
     * 获取现代产业园详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:industrial:query')")
    @GetMapping(value = "/{industrialParkId}")
    public AjaxResult getInfo(@PathVariable("industrialParkId") Long industrialParkId)
    {
        return success(hwModernIndustrialParkInformationService.selectHwModernIndustrialParkInformationByIndustrialParkId(industrialParkId));
    }

    /**
     * 新增现代产业园
     */
    @PreAuthorize("@ss.hasPermi('system:industrial:add')")
    @Log(title = "现代产业园", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HwModernIndustrialParkInformation hwModernIndustrialParkInformation)
    {
        return toAjax(hwModernIndustrialParkInformationService.insertHwModernIndustrialParkInformation(hwModernIndustrialParkInformation));
    }

    /**
     * 修改现代产业园
     */
    @PreAuthorize("@ss.hasPermi('system:industrial:edit')")
    @Log(title = "现代产业园", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HwModernIndustrialParkInformation hwModernIndustrialParkInformation)
    {
        return toAjax(hwModernIndustrialParkInformationService.updateHwModernIndustrialParkInformation(hwModernIndustrialParkInformation));
    }

    /**
     * 删除现代产业园
     */
    @PreAuthorize("@ss.hasPermi('system:industrial:remove')")
    @Log(title = "现代产业园", businessType = BusinessType.DELETE)
	@DeleteMapping("/{industrialParkIds}")
    public AjaxResult remove(@PathVariable Long[] industrialParkIds)
    {
        return toAjax(hwModernIndustrialParkInformationService.deleteHwModernIndustrialParkInformationByIndustrialParkIds(industrialParkIds));
    }
}
