package com.yinzhiwu.yiwu.web.operating.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw.CustomerAgeType;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw.PerformanceType;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw.ProductCardType;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw.UsableRangeType;
import com.yinzhiwu.yiwu.enums.CourseType;
import com.yinzhiwu.yiwu.enums.SubCourseType;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;
import com.yinzhiwu.yiwu.service.DanceGradeYzwService;
import com.yinzhiwu.yiwu.service.DanceYzwService;
import com.yinzhiwu.yiwu.service.DepartmentYzwService;
import com.yinzhiwu.yiwu.service.ElectricContractTypeService;
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
	@Autowired private ElectricContractTypeService ectService;
	@Autowired private DanceYzwService danceService;
	@Autowired private DanceGradeYzwService dgService;
	@Autowired private DepartmentYzwService deptService;
	
	@GetMapping(value="/list")
	public String list(){
		return "products/list";
	}
	
	@GetMapping(value="/createForm")
	public String showCreateForm(Model model){
		setCommon(model);
		return "products/createForm";
	}
	
	@GetMapping(value="/{id}/updateForm")
	public String showUpdateForm(@PathVariable(name="id") Integer id, Model model) throws DataNotFoundException{
		setCommon(model);
		model.addAttribute("product", productService.get(id));
		return "products/updateForm";
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
	
	@ResponseBody
	@GetMapping("/{id}")
	public YiwuJson<?> doGet(@PathVariable(name="id") Integer id ) throws DataNotFoundException{
		return YiwuJson.createBySuccess(ProductViewConverter.INSTANCE.fromPO( productService.get(id)));
	}
	
	@PostMapping
	@ResponseBody
	public YiwuJson<?> doCreate(@Valid ProductYzw product, BindingResult result){
		if(result.hasErrors())
			return YiwuJson.createByErrorMessage(getErrorsMessage(result));
		
		productService.save(product);
		
		return YiwuJson.createBySuccess(ProductViewConverter.INSTANCE.fromPO(product));
	}
	
	@ResponseBody
	@PutMapping("/{id}")
	public YiwuJson<?> doUpdate(@PathVariable(name="id") Integer id, ProductYzw product) throws IllegalArgumentException, IllegalAccessException, DataNotFoundException{
		productService.modify(id, product);
		
		return YiwuJson.createBySuccess(ProductViewConverter.INSTANCE.fromPO(product));
	}
	
	@ResponseBody
	@DeleteMapping("/{id}")
	public YiwuJson<?> doDelete(@PathVariable(name="id") Integer id){
		productService.delete(id);
		return YiwuJson.createBySuccess();
	}
	
	private void setCommon(Model model){
		model.addAttribute("performanceTypes", PerformanceType.values());
		model.addAttribute("cardTypes", ProductCardType.values());
		model.addAttribute("customerAgeTypes", CustomerAgeType.values());
		model.addAttribute("performaceTypes", PerformanceType.values());
		model.addAttribute("contractTypes", ectService.findAll());
		model.addAttribute("dances", danceService.findAll());
		model.addAttribute("danceGrades", dgService.findAll());
		model.addAttribute("courseTypes", CourseType.getEffectiveCourseTypes());
		model.addAttribute("subCourseTypes", SubCourseType.values());
		model.addAttribute("usableRangeTypes", UsableRangeType.values());
		model.addAttribute("stores", deptService.findAllStores());
		model.addAttribute("companies", deptService.findCompanies());
	}
}
