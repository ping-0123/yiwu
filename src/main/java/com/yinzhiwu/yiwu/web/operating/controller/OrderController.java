package com.yinzhiwu.yiwu.web.operating.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.exception.data.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;
import com.yinzhiwu.yiwu.service.OrderYzwService;
import com.yinzhiwu.yiwu.service.ProductYzwService;
import com.yinzhiwu.yiwu.util.ServletRequestUtils;
import com.yinzhiwu.yiwu.web.operating.view.OrderView;
import com.yinzhiwu.yiwu.web.operating.view.OrderView.OrderViewConverter;

/**
* @author 作者 ping
* @Date 创建时间：2017年11月26日 下午6:37:44
*
*/

@Controller
@RequestMapping("/system/orders")
public class OrderController extends BaseController {
	
	@Autowired private OrderYzwService orderService;
	@Autowired private ProductYzwService productService;
	
	@GetMapping(value="/list")
	public String list(){
		return "orders/list";
	}
	
	private void setCommon(Model model){
		model.addAttribute("products",productService.findAll());
	}
	
	@GetMapping
	public String showCreateForm(Model model){
		model.addAttribute("products",productService.findAll());
		return "orders/createForm";
	}
	
	@GetMapping("/{id}/updateForm")
	public String showUpdateOrderForm(
			 @PathVariable(name="id") String orderId,Model model) throws DataNotFoundException{
//		model.addAttribute("distributer", distributer);
		OrderYzw order = orderService.get(orderId);
		
		model.addAttribute("order",order);
		setCommon(model);
		return "orders/updateForm";
	}
	
	@PostMapping(value="/datatable")
	@ResponseBody
	public DataTableBean<?> findDatatable(HttpServletRequest request) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException{
		QueryParameter arg = ServletRequestUtils.parseDatatableQuery(request);
		ServletRequestUtils.transferQueryParamter(arg, OrderView.class);
		
		DataTableBean<OrderYzw> table = orderService.findDataTable(arg);
		List<OrderView> views = new ArrayList<>();
		for(OrderYzw order: table.getData()){
			views.add(OrderViewConverter.INSTANCE.fromPO(order));
		}
		
		return new DataTableBean<>(
				table.getDraw(),table.getRecordsTotal(),table.getRecordsFiltered(),views,table.getError());
	}
	
	@PostMapping
	@ResponseBody
	public YiwuJson<?> doCreate(@Valid OrderYzw order, BindingResult result){
		if(result.hasErrors()){
			return YiwuJson.createByErrorMessage(getErrorsMessage(result));
		}
		
		orderService.save(order);
		
		return YiwuJson.createBySuccess(OrderViewConverter.INSTANCE.fromPO(order));
	}
}
