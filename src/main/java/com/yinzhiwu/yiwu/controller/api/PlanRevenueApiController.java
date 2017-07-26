package com.yinzhiwu.yiwu.controller.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.yiwu.entity.PlanRevenue;
import com.yinzhiwu.yiwu.entity.type.ProductType;
import com.yinzhiwu.yiwu.model.PlanRevenueApiModel;
import com.yinzhiwu.yiwu.model.ReturnedJson;
import com.yinzhiwu.yiwu.model.Store;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.service.DepartmentService;
import com.yinzhiwu.yiwu.service.PlanRevenueService;
import com.yinzhiwu.yiwu.service.ProductTypeService;

@Controller
@RequestMapping(value = "/api/planRevenue")
public class PlanRevenueApiController {

	private static final Log logger = LogFactory.getLog(PlanRevenueApiController.class);

	@Autowired
	private PlanRevenueService planRevenueService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private ProductTypeService productTypeService;

	@ResponseBody
	@RequestMapping(value = "/getMonthlyPlanRevenue", method = { RequestMethod.GET })
	public ReturnedJson getMonthlyPlanRevenue(@RequestParam int districtId, @RequestParam int year,
			@RequestParam int month, @RequestParam int productTypeId) {

		return new ReturnedJson(
				planRevenueService.getDistricMonthlyPlanRevenue(districtId, year, month, productTypeId));
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("plan", new PlanRevenue());
		return "planRevenue/form";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		PlanRevenueApiModel plan = planRevenueService.get(id);
		model.addAttribute("plan", plan);
		return "planRevenue/edit";
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public YiwuJson<PlanRevenueApiModel> doGet(@PathVariable int id) {
		return new YiwuJson<>(planRevenueService.get(id));

	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String doPost(@Valid @ModelAttribute("plan") PlanRevenue plan, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			FieldError fieldError = bindingResult.getFieldError();
			logger.info("Code:" + fieldError.getCode() + ",field:" + fieldError.getField());
			return "planRevenue/form";
		}
		int id = planRevenueService.save(plan);
		if (id > 0) {
			model.addAttribute("id", id);
			// model.addAttribute("year", plan.getYear());
			// model.addAttribute("month",plan.getMonth());
			// model.addAttribute("storeId", plan.getStoreId());
			// model.addAttribute("productType.id",
			// plan.getProductType().getId());
			return "redirect:/api/planRevenue/list";
		} else
			return "error";
	}

	@GetMapping("/list")
	public String list(@ModelAttribute("plan") PlanRevenue plan, Model model) {
		List<PlanRevenueApiModel> list = new ArrayList<>();
		logger.debug(plan.getStoreId());
		int productTypeId = 0;
		if (plan.getId() > 0) {
			list.add(planRevenueService.get(plan.getId()));
		} else {
			if (plan.getProductType() != null) {
				productTypeId = plan.getProductType().getId();
			}
			list.addAll(planRevenueService.findByProperties(plan.getStoreId(), plan.getYear(), plan.getMonth(),
					productTypeId));
		}

		model.addAttribute("list", list);
		return "planRevenue/list";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String doPut(@ModelAttribute PlanRevenueApiModel plan, Model model) {
		PlanRevenue p = new PlanRevenue();
		p.setId(plan.getId());
		p.setStoreId(plan.getStoreId());
		p.setYear(plan.getYear());
		p.setMonth(plan.getMonth());
		p.setProductType(new ProductType());
		p.getProductType().setId(plan.getProductTypeId());
		p.setAmount(plan.getAmount());
		planRevenueService.saveOrUpdate(p);

		model.addAttribute("id", plan.getId());
		// model.addAttribute("year", plan.getYear());
		// model.addAttribute("month",plan.getMonth());
		// model.addAttribute("storeId", plan.getStoreId());
		// model.addAttribute("productType.id", plan.getProductTypeId());
		return "redirect:/api/planRevenue/list";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String doDelete(@PathVariable int id) {
		planRevenueService.delete(id);
		return "success";
	}

	@ModelAttribute("allStores")
	public List<Store> getAllStores2() {
		List<Store> stores = new ArrayList<>();
		stores.add(new Store(0, "所有", "", ""));
		stores.addAll(departmentService.getAllStores());
		return stores;
	}

	@ModelAttribute("allProductTypes")
	public List<ProductType> getAllProductTypes2() {
		List<ProductType> productTypes = new ArrayList<>();
		productTypes.add(new ProductType(0, "全部"));
		productTypes.addAll(productTypeService.findAll());
		return productTypes;
	}

	@ModelAttribute("stores")
	public List<Store> getAllStores() {
		List<Store> stores = new ArrayList<>();
		stores.addAll(departmentService.getAllStores());
		return stores;
	}

	@ModelAttribute("productTypes")
	public List<ProductType> getAllProductTypes() {
		List<ProductType> productTypes = new ArrayList<>();
		productTypes.addAll(productTypeService.findAll());
		return productTypes;
	}

}
