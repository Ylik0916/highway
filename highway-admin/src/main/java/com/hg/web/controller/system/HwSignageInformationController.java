package com.hg.web.controller.system;

import com.hg.common.annotation.Log;
import com.hg.common.core.controller.BaseController;
import com.hg.common.core.domain.AjaxResult;
import com.hg.common.core.page.TableDataInfo;
import com.hg.common.enums.BusinessType;
import com.hg.common.utils.poi.ExcelUtil;
import com.hg.system.domain.HwSignageInformation;
import com.hg.system.service.IHwSignageInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 标识标牌Controller
 * 
 * @author ruoyi
 * @date 2023-03-14
 */
@RestController
@RequestMapping("/system/signage")
public class HwSignageInformationController extends BaseController
{
    @Autowired
    private IHwSignageInformationService hwSignageInformationService;

    /**
     * 查询标识标牌列表
     */
    @PreAuthorize("@ss.hasPermi('system:signage:list')")
    @GetMapping("/list")
    public TableDataInfo list(HwSignageInformation hwSignageInformation)
    {
        startPage();
        List<HwSignageInformation> list = hwSignageInformationService.selectHwSignageInformationList(hwSignageInformation);
        return getDataTable(list);
    }

    /**
     * 导出标识标牌列表
     */
    @PreAuthorize("@ss.hasPermi('system:signage:export')")
    @Log(title = "标识标牌", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HwSignageInformation hwSignageInformation)
    {
        List<HwSignageInformation> list = hwSignageInformationService.selectHwSignageInformationList(hwSignageInformation);
        ExcelUtil<HwSignageInformation> util = new ExcelUtil<HwSignageInformation>(HwSignageInformation.class);
        util.exportExcel(response, list, "标识标牌数据");
    }

    /**
     * 获取标识标牌详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:signage:query')")
    @GetMapping(value = "/{identificationTagId}")
    public AjaxResult getInfo(@PathVariable("identificationTagId") Long identificationTagId)
    {
        return success(hwSignageInformationService.selectHwSignageInformationByIdentificationTagId(identificationTagId));
    }

    /**
     * 根据路线ID获取标识标牌详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:signage:querybyrouteid')")
    @GetMapping(value = "/getHwSignageInformationListByRouteId/{routeid}")
    public AjaxResult getHwSignageInformationListByRouteId(@PathVariable("routeid") Long routeid)
    {
        return success(hwSignageInformationService.selectHwSignageInformationListByRouteId(routeid));
    }

    /**
     * 新增标识标牌
     */
    @PreAuthorize("@ss.hasPermi('system:signage:add')")
    @Log(title = "标识标牌", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HwSignageInformation hwSignageInformation)
    {
        return toAjax(hwSignageInformationService.insertHwSignageInformation(hwSignageInformation));
    }

    /**
     * 修改标识标牌
     */
    @PreAuthorize("@ss.hasPermi('system:signage:edit')")
    @Log(title = "标识标牌", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HwSignageInformation hwSignageInformation)
    {
        return toAjax(hwSignageInformationService.updateHwSignageInformation(hwSignageInformation));
    }

    /**
     * 删除标识标牌
     */
    @PreAuthorize("@ss.hasPermi('system:signage:remove')")
    @Log(title = "标识标牌", businessType = BusinessType.DELETE)
	@DeleteMapping("/{identificationTagIds}")
    public AjaxResult remove(@PathVariable Long[] identificationTagIds)
    {
        return toAjax(hwSignageInformationService.deleteHwSignageInformationByIdentificationTagIds(identificationTagIds));
    }
}
