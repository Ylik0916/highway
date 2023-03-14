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
import com.hg.system.domain.HwVillageInformation;
import com.hg.system.service.IHwVillageInformationService;
import com.hg.common.utils.poi.ExcelUtil;
import com.hg.common.core.page.TableDataInfo;

/**
 * 建制村Controller
 * 
 * @author ruoyi
 * @date 2023-03-14
 */
@RestController
@RequestMapping("/system/village")
public class HwVillageInformationController extends BaseController
{
    @Autowired
    private IHwVillageInformationService hwVillageInformationService;

    /**
     * 查询建制村列表
     */
    @PreAuthorize("@ss.hasPermi('system:village:list')")
    @GetMapping("/list")
    public TableDataInfo list(HwVillageInformation hwVillageInformation)
    {
        startPage();
        List<HwVillageInformation> list = hwVillageInformationService.selectHwVillageInformationList(hwVillageInformation);
        return getDataTable(list);
    }

    /**
     * 导出建制村列表
     */
    @PreAuthorize("@ss.hasPermi('system:village:export')")
    @Log(title = "建制村", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HwVillageInformation hwVillageInformation)
    {
        List<HwVillageInformation> list = hwVillageInformationService.selectHwVillageInformationList(hwVillageInformation);
        ExcelUtil<HwVillageInformation> util = new ExcelUtil<HwVillageInformation>(HwVillageInformation.class);
        util.exportExcel(response, list, "建制村数据");
    }

    /**
     * 获取建制村详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:village:query')")
    @GetMapping(value = "/{villageId}")
    public AjaxResult getInfo(@PathVariable("villageId") Long villageId)
    {
        return success(hwVillageInformationService.selectHwVillageInformationByVillageId(villageId));
    }

    /**
     * 新增建制村
     */
    @PreAuthorize("@ss.hasPermi('system:village:add')")
    @Log(title = "建制村", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HwVillageInformation hwVillageInformation)
    {
        return toAjax(hwVillageInformationService.insertHwVillageInformation(hwVillageInformation));
    }

    /**
     * 修改建制村
     */
    @PreAuthorize("@ss.hasPermi('system:village:edit')")
    @Log(title = "建制村", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HwVillageInformation hwVillageInformation)
    {
        return toAjax(hwVillageInformationService.updateHwVillageInformation(hwVillageInformation));
    }

    /**
     * 删除建制村
     */
    @PreAuthorize("@ss.hasPermi('system:village:remove')")
    @Log(title = "建制村", businessType = BusinessType.DELETE)
	@DeleteMapping("/{villageIds}")
    public AjaxResult remove(@PathVariable Long[] villageIds)
    {
        return toAjax(hwVillageInformationService.deleteHwVillageInformationByVillageIds(villageIds));
    }
}
