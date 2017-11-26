package com.yinzhiwu.yiwu.web.operating.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;
import com.yinzhiwu.yiwu.service.ProductYzwService;
import com.yinzhiwu.yiwu.util.ServletRequestUtils;
import com.yinzhiwu.yiwu.web.operating.view.ProductView;
import com.yinzhiwu.yiwu.web.operating.view.ProductView.ProductViewConverter;

/**
* @author 作者 ping
* @Date 创建时间：2017年11月26日 下午11:15:00
*
*/

@Controller
@RequestMapping("/system/products")
public class ProductController extends BaseController {

	@Autowired private ProductYzwService productService;
	
	@GetMapping(value="/list")
	public String list(){
		return "products/list";
	}
	
	@PostMapping(value="/datatable")
	@ResponseBody
	public DataTableBean<?> findDatatable(HttpServletRequest request) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException{
		QueryParameter arg = ServletRequestUtils.parseDatatableQuery(request);
		ServletRequestUtils.transferQueryParamter(arg, ProductView.class);
		
		DataTableBean<ProductYzw> table = productService.findDataTable(arg);
		List<ProductView> views = new ArrayList<>();
		for(ProductYzw prod: table.getData()){
			views.add(ProductViewConverter.INSTANCE.fromPO(prod));
		}
		
		return new DataTableBean<>(
				table.getDraw(),table.getRecordsTotal(),table.getRecordsFiltered(),views,table.getError());
	}
}
