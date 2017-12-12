package com.yinzhiwu.yiwu.web.operating.controller;

import java.util.ArrayList;
import java.util.Date;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw;
import com.yinzhiwu.yiwu.exception.data.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;
import com.yinzhiwu.yiwu.service.DepartmentYzwService;
import com.yinzhiwu.yiwu.service.DistributerService;
import com.yinzhiwu.yiwu.service.OrderYzwService;
import com.yinzhiwu.yiwu.service.PayedMethodService;
import com.yinzhiwu.yiwu.service.ProductYzwService;
import com.yinzhiwu.yiwu.util.ServletRequestUtils;
import com.yinzhiwu.yiwu.web.operating.view.DistributerVO;
import com.yinzhiwu.yiwu.web.operating.view.DistributerVO.DistributerVOConverter;
import com.yinzhiwu.yiwu.web.operating.view.OrderView;
import com.yinzhiwu.yiwu.web.operating.view.OrderView.OrderViewConverter;

/**
* @author 作者 ping
* @Date 创建时间：2017年11月26日 下午1:38:43
*
*/


@Controller
@RequestMapping("/system/distributers")
public class DistributerController extends com.yinzhiwu.yiwu.controller.BaseController{
	
	@Autowired private DistributerService distributerService;
	@Autowired private OrderYzwService orderService;
	@Autowired private ProductYzwService productService;
	@Autowired private DepartmentYzwService deptService;
	@Autowired private PayedMethodService pmService;
	
	@GetMapping 
	public String index(){
		return "redirect:distributers/list";
	}
	
	@GetMapping(value="/list")
	public String list(Model model){
		return "distributers/list";
	}
	
	@GetMapping(value="/createForm")
	public String showCreateForm(){
		return "distributers/createForm";
	}
	
	@GetMapping(value="/{id}/updateForm")
	public String showModifyForm(@PathVariable(name="id") int id, Model model) throws DataNotFoundException
	{
		Distributer distributer = distributerService.get(id);
//		DistributerVO vo = DistributerVOConverter.INSTANCE.fromPO(distributer);
		model.addAttribute("distributer", distributer);
		return "distributers/updateForm";
	}
	
	@PostMapping(value="/datatable")
	@ResponseBody
	public DataTableBean<?> findDatatable(HttpServletRequest request) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException{
		QueryParameter arg = (QueryParameter) ServletRequestUtils.parseParameter(request, QueryParameter.class);
		ServletRequestUtils.transferQueryParamter(arg, DistributerVO.class);
		
		DataTableBean<Distributer> table =  distributerService.findDataTable(arg);
		java.util.List<DistributerVO> vos = new ArrayList<>();
		for (Distributer distributer : table.getData()) {
			vos.add(DistributerVOConverter.INSTANCE.fromPO(distributer));
		}
		
		return new DataTableBean<>(
				table.getDraw(),table.getRecordsTotal(), table.getRecordsFiltered(), vos, table.getError());
	}
	
	@PostMapping
	@ResponseBody
	public YiwuJson<?> create(@Valid Distributer distributer, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return YiwuJson.createByErrorMessage(getErrorsMessage(bindingResult));
		}
		distributerService.save(distributer);
		return YiwuJson.createBySuccess(distributer);
	}
	
	@PutMapping(value="/{id}")
	@ResponseBody
	public YiwuJson<?> modify(@PathVariable(name="id") int id, Distributer distributer) throws IllegalArgumentException, IllegalAccessException, DataNotFoundException{
		distributerService.modify(id, distributer);
		return YiwuJson.createBySuccess();
	}
	
	@ResponseBody
	@DeleteMapping(value="/{id}")
	public YiwuJson<?> delete(@PathVariable(name="id") Integer id)
	{
		distributerService.delete(id);
		return YiwuJson.createBySuccess();
	}
	
	
	//客户订单
	
	@GetMapping("/{id}/orders/list")
	public String showDistributerOrderList(@PathVariable(name="id") int id, Model model) throws DataNotFoundException{
		DistributerVO distributer = DistributerVOConverter.INSTANCE.fromPO(distributerService.get(id));
		model.addAttribute("distributer", distributer);
		return "distributers/orders/list";
	}
	
	@PostMapping("/{id}/orders/datatable")
	@ResponseBody
	public DataTableBean<?> findDatatable(@PathVariable(name="id") int id, HttpServletRequest request) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException{
		QueryParameter datatableQuery = ServletRequestUtils.parseDatatableQuery(request);
		ServletRequestUtils.transferQueryParamter(datatableQuery, OrderView.class);
		
		DataTableBean<OrderYzw> table = orderService.findDataTableByProperty(datatableQuery, "distributer.id", id);
		java.util.List<OrderView> views = new ArrayList<>();
		for(OrderYzw order: table.getData()){
			views.add(OrderViewConverter.INSTANCE.fromPO(order));
		}
		
		return new DataTableBean<>(
				table.getDraw(),table.getRecordsTotal(), table.getRecordsFiltered(),views,table.getError());
	}
	
	
	@GetMapping("/{id}/orders/createForm")
	public String showCreateOrderForm(
			@PathVariable(name="id") int id, Model model) throws DataNotFoundException{
		Distributer distributer = distributerService.get(id);
		model.addAttribute("distributer", distributer);
		model.addAttribute("products",productService.findAll());
		model.addAttribute("allStores",deptService.findAllStores());
		model.addAttribute("stores",deptService.findVisableStores());
		model.addAttribute("payedMethods", pmService.findAll());
		return "orders/createForm";
	}
	
	
	@PostMapping("/{id}/orders")
	@ResponseBody
	public YiwuJson<?> doCreateOrder(
			Integer productId, Integer count, Float payedAmount, String comments,
			Integer[] validStoreIds,
			@RequestParam(name="contractStart", required=false) Date contractStart) throws DataNotFoundException{
			
		if(null == contractStart)
			contractStart = new Date();
		
		ProductYzw product = productService.get(productId);
		OrderYzw order = new OrderYzw();
		order.setProduct(product);
		order.setCount(count);
		order.setPayedAmount(payedAmount);
		order.setComments(comments);
		
		return null;
		
	}
}
