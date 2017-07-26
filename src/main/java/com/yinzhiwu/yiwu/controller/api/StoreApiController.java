package com.yinzhiwu.yiwu.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.service.DepartmentService;

@Controller
@RequestMapping(value = "/api/store")
public class StoreApiController {

	@Autowired
	private DepartmentService departmentService;

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object getStoreList(@RequestParam int districtId) {
		return departmentService.findStoreInfosByDistrictId(districtId);
	}

	@Deprecated
	@GetMapping(value = "/id/{id}")
	@ResponseBody
	public Object getStore(@PathVariable int id) {
		return departmentService.findStoreInfoById(id);
	}

	@GetMapping(value = "/{storeId}")
	@ResponseBody
	public Object getStoreById(@PathVariable int storeId) {
		return departmentService.findStoreInfoById(storeId);
	}

	@RequestMapping(value = "/getStoresByCity", method = { RequestMethod.GET })
	@ResponseBody
	public Object getStoresByCity(@RequestParam String city) {
		return departmentService.findStoreByCities(city);
	}

	@GetMapping(value = "/getAllStores")
	@ResponseBody
	public Object getAllStores() {
		return departmentService.getAllStores();
	}

	@GetMapping(value = "/getAllApiStores")
	@ResponseBody
	public YiwuJson<?> getAllApiStores() {
		return new YiwuJson<>(departmentService.getAllStores());
	}
}
