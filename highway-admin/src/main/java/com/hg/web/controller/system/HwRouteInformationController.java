package com.hg.web.controller.system;

import com.hg.common.annotation.Log;
import com.hg.common.core.controller.BaseController;
import com.hg.common.core.domain.AjaxResult;
import com.hg.common.core.page.TableDataInfo;
import com.hg.common.enums.BusinessType;
import com.hg.common.utils.poi.ExcelUtil;
import com.hg.system.domain.HwRouteInformation;
import com.hg.system.domain.vo.RouteMileage;
import com.hg.system.domain.vo.RouterType;
import com.hg.system.service.IHwRouteInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 路线列表Controller
 *
 * @author ruoyi
 * @date 2023-03-07
 */
@RestController
@RequestMapping("/system/information")
public class HwRouteInformationController extends BaseController
{
    @Autowired
    private IHwRouteInformationService hwRouteInformationService;

    /**
     * 查询路线列表列表
     */
    @PreAuthorize("@ss.hasPermi('system:information:list')")
    @GetMapping("/list")
    public TableDataInfo list(HwRouteInformation hwRouteInformation)
    {
        startPage();
        List<HwRouteInformation> list = hwRouteInformationService.selectHwRouteInformationList(hwRouteInformation);
        return getDataTable(list);
    }

    /**
     * 导出路线列表列表
     */
    @PreAuthorize("@ss.hasPermi('system:information:export')")
    @Log(title = "路线列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HwRouteInformation hwRouteInformation)
    {
        List<HwRouteInformation> list = hwRouteInformationService.selectHwRouteInformationList(hwRouteInformation);
        ExcelUtil<HwRouteInformation> util = new ExcelUtil<HwRouteInformation>(HwRouteInformation.class);
        util.exportExcel(response, list, "路线列表数据");
    }

    /**
     * 获取路线列表详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:information:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(hwRouteInformationService.selectHwRouteInformationById(id));
    }

    /**
     * 新增路线列表
     */
    @PreAuthorize("@ss.hasPermi('system:information:add')")
    @Log(title = "路线列表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HwRouteInformation hwRouteInformation)
    {
        return toAjax(hwRouteInformationService.insertHwRouteInformation(hwRouteInformation));
    }

    /**
     * 修改路线列表
     */
    @PreAuthorize("@ss.hasPermi('system:information:edit')")
    @Log(title = "路线列表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HwRouteInformation hwRouteInformation)
    {
        return toAjax(hwRouteInformationService.updateHwRouteInformation(hwRouteInformation));
    }

    /**
     * 删除路线列表
     */
    @PreAuthorize("@ss.hasPermi('system:information:remove')")
    @Log(title = "路线列表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(hwRouteInformationService.deleteHwRouteInformationByIds(ids));
    }

    @GetMapping("/routeType")
    public List<RouterType> routerType(){
        return hwRouteInformationService.routetype();
    }

    @GetMapping("/routeMileage")
    public RouteMileage routeMileage(){
        return hwRouteInformationService.routeMileage();
    }
}
