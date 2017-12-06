
package com.yinzhiwu.yiwu.web.operating.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.OperatingPlan;
import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw.PerformanceType;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;
import com.yinzhiwu.yiwu.service.DepartmentYzwService;
import com.yinzhiwu.yiwu.service.OperatingPlanService;
import com.yinzhiwu.yiwu.util.ServletRequestUtils;
import com.yinzhiwu.yiwu.web.operating.view.Amount;
import com.yinzhiwu.yiwu.web.operating.view.PlanView;
import com.yinzhiwu.yiwu.web.operating.view.PlanView.PlanViewConverter;


/**
 * @author ping
 * @date 2017年12月6日下午12:03:28
 * @since v2.2.0
 *	
 */

@Controller
@RequestMapping("/system/plans")
public class OperatingPlanController  extends BaseController{
	
	@Autowired private OperatingPlanService opService;
	@Autowired private DepartmentYzwService deptService;
	
	@GetMapping("/list")
	public String  list(){
		return "plans/list";
	}
	
	private void setCommon(Model model){
		model.addAttribute("stores", deptService.findVisableStores());
		model.addAttribute("performanceTypes", PerformanceType.values());
	}
	
	@GetMapping("/createForm")
	public String showCreateForm(Model model){
		setCommon(model);
		return "plans/createForm";		
	}
	
	@GetMapping("/{id}/updateForm")
	public String showUpdateForm(@PathVariable(name="id") Integer id, Model model) throws DataNotFoundException{
		model.addAttribute("plan",opService.get(id));
		return "plans/updateForm";
	}
	
	@PostMapping("/datatable")
	@ResponseBody
	public DataTableBean<?> findDatatable(HttpServletRequest request) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException{
		QueryParameter arg = ServletRequestUtils.parseDatatableQuery(request);
		ServletRequestUtils.transferQueryParamter(arg, PlanView.class);
		
		DataTableBean<OperatingPlan> table = opService.findDataTable(arg);
		java.util.List<PlanView> views = new ArrayList<>();
		for(OperatingPlan plan: table.getData()){
			views.add(PlanViewConverter.INSTANCE.fromPO(plan));
		}
		
		return new DataTableBean<>(
				table.getDraw(),table.getRecordsTotal(),table.getRecordsFiltered(),views,table.getError());
	}
	
	
	@PostMapping
	@ResponseBody
	public YiwuJson<?> doCreate(@Valid PlanView plan,BindingResult re) throws DataNotFoundException{
		if(re.hasErrors()){
			return YiwuJson.createByErrorMessage(getErrorsMessage(re));
		}
		logger.info("month is "  + plan.getMonth());
		
		DepartmentYzw store = deptService.get(plan.getStoreId());
		
		java.util.List<Amount> amounts = plan.getAmounts();
		for (Amount amount : amounts) {
			if(null != amount.getAmount() && amount.getAmount().floatValue()>0){
				OperatingPlan p = new OperatingPlan();
				p.setStore(store);
				p.setMonth(plan.getMonth());
				p.setType(amount.getType());
				p.setAmount(amount.getAmount());
				
				opService.save(p);
			}
		}
		
		
		return YiwuJson.createBySuccess(plan);
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	public YiwuJson<?> doUpdate(@PathVariable(name="id") Integer id, OperatingPlan plan) throws IllegalArgumentException, IllegalAccessException, DataNotFoundException
	{
		opService.modify(id, plan);
		
		return YiwuJson.createBySuccess(PlanViewConverter.INSTANCE.fromPO(plan));
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public YiwuJson<?> deDelete(@PathVariable(name="id") Integer id){
		opService.delete(id);
		
		return YiwuJson.createBySuccess();
	}
}
