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
import com.hg.system.domain.HwPlanItem;
import com.hg.system.service.IHwPlanItemService;
import com.hg.common.utils.poi.ExcelUtil;
import com.hg.common.core.page.TableDataInfo;

/**
 * 进度Controller
 * 
 * @author Cxh
 * @date 2023-03-17
 */
@RestController
@RequestMapping("/system/item")
public class HwPlanItemController extends BaseController
{
    @Autowired
    private IHwPlanItemService hwPlanItemService;

    /**
     * 查询进度列表 和 所属的年度计划
     */
    // @PreAuthorize("@ss.hasPermi('system:item:list')")
    // @GetMapping("/andPlanList")
    // public TableDataInfo listAndPlan(HwPlanItem hwPlanItem)
    // {
    //     startPage();
    //     List<HwPlanItem> list = hwPlanItemService.selectHwPlanAndItemList(hwPlanItem);
    //     return getDataTable(list);
    // }


    /**
     *  根据年度计划查询进度列表
     */
    @PreAuthorize("@ss.hasPermi('system:item:list')")
    @GetMapping("/listByPlanId/{id}")
    public TableDataInfo listByPlanId(@PathVariable("id") long id)
    {
        startPage();
        List<HwPlanItem> list = hwPlanItemService.selectHwPlanItemListByPlanId(id);
        return getDataTable(list);
    }



    /**
     * 查询进度列表
     */
    @PreAuthorize("@ss.hasPermi('system:item:list')")
    @GetMapping("/list")
    public TableDataInfo list(HwPlanItem hwPlanItem)
    {
        startPage();
        List<HwPlanItem> list = hwPlanItemService.selectHwPlanItemList(hwPlanItem);
        return getDataTable(list);
    }


    /**
     * 查询待上报明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:item:list')")
    @GetMapping("/listAnd0")
    public TableDataInfo listAnd0(HwPlanItem hwPlanItem)
    {
        startPage();
        List<HwPlanItem> list = hwPlanItemService.selectHwPlanItemListAnd0(hwPlanItem);
        return getDataTable(list);
    }


    /**
     * 查询等待审核明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:item:list')")
    @GetMapping("/listAnd1")
    public TableDataInfo listAnd1(HwPlanItem hwPlanItem)
    {
        startPage();
        List<HwPlanItem> list = hwPlanItemService.selectHwPlanItemListAnd1(hwPlanItem);
        return getDataTable(list);
    }


    /**
     * 查询已驳回明细上报列表 以及对应的年度计划名称
     */
    @PreAuthorize("@ss.hasPermi('system:item:list')")
    @GetMapping("/listAnd2")
    public TableDataInfo listAnd2(HwPlanItem hwPlanItem)
    {
        startPage();
        List<HwPlanItem> list = hwPlanItemService.selectHwPlanItemListAnd2(hwPlanItem);
        return getDataTable(list);
    }


    /**
     * 查询已审核明细上报列表
     */
    @PreAuthorize("@ss.hasPermi('system:item:list')")
    @GetMapping("/listAnd3")
    public TableDataInfo listAnd3(HwPlanItem hwPlanItem)
    {
        startPage();
        List<HwPlanItem> list = hwPlanItemService.selectHwPlanItemListAnd3(hwPlanItem);
        return getDataTable(list);
    }



    /**
     * 导出进度列表
     */
    @PreAuthorize("@ss.hasPermi('system:item:export')")
    @Log(title = "进度", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HwPlanItem hwPlanItem)
    {
        List<HwPlanItem> list = hwPlanItemService.selectHwPlanItemList(hwPlanItem);
        ExcelUtil<HwPlanItem> util = new ExcelUtil<HwPlanItem>(HwPlanItem.class);
        util.exportExcel(response, list, "进度数据");
    }

    /**
     * 获取进度详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:item:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(hwPlanItemService.selectHwPlanItemById(id));
    }

    /**
     * 新增进度
     */
    @PreAuthorize("@ss.hasPermi('system:item:add')")
    @Log(title = "进度", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HwPlanItem hwPlanItem)
    {
        return toAjax(hwPlanItemService.insertHwPlanItem(hwPlanItem));
    }

    /**
     * 修改进度
     */
    @PreAuthorize("@ss.hasPermi('system:item:edit')")
    @Log(title = "进度", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HwPlanItem hwPlanItem)
    {
        return toAjax(hwPlanItemService.updateHwPlanItem(hwPlanItem));
    }


    /**
     * 修改进度状态为已审核
     */
    @PreAuthorize("@ss.hasPermi('system:item:edit')")
    @Log(title = "进度", businessType = BusinessType.UPDATE)
    @PutMapping("/scale/{id}")
    public AjaxResult editScale(@PathVariable Long id)
    {
        return toAjax(hwPlanItemService.updateItemScale(id));
    }

    /**
     * 修改进度状态为已驳回
     */
    @PreAuthorize("@ss.hasPermi('system:item:edit')")
    @Log(title = "进度", businessType = BusinessType.UPDATE)
    @PutMapping("/reject/{id}")
    public AjaxResult rejectScale(@PathVariable Long id)
    {
        return toAjax(hwPlanItemService.updateItemReject(id));
    }
    

    /**
     * 删除进度
     */
    @PreAuthorize("@ss.hasPermi('system:item:remove')")
    @Log(title = "进度", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(hwPlanItemService.deleteHwPlanItemByIds(ids));
    }
}
