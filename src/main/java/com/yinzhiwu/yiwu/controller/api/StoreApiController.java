package com.yinzhiwu.yiwu.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.StoreApiView;
import com.yinzhiwu.yiwu.model.view.StoreApiView.StoreApiViewConverter;
import com.yinzhiwu.yiwu.service.DepartmentYzwService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/store")
public class StoreApiController {

	@Autowired private DepartmentYzwService departmentService;

	@GetMapping(value = "/list")
	@ApiOperation("获取区域{distributerId}下的门店列表")
	public List<StoreApiView> getStoreList(@RequestParam Integer districtId) {
		return departmentService.findStoreApiViewsUnderOrganization(districtId);
	}
	
	@GetMapping
	@ApiOperation("获取所有门店")
	public YiwuJson<List<StoreApiView>> doGet(){
		List<StoreApiView> views = new ArrayList<>();
		List<DepartmentYzw> stores = departmentService.findAllStores();
		for (DepartmentYzw store : stores) {
			views.add(StoreApiViewConverter.INSTANCE.fromPO(store));
		}
		return YiwuJson.createBySuccess(views);
	}


	@GetMapping(value = "/{storeId}")
	public StoreApiView getStoreById(@PathVariable Integer storeId) throws DataNotFoundException {
		return StoreApiViewConverter.INSTANCE.fromPO(
				departmentService.get(storeId));
	}

	@GetMapping(value = "/getStoresByCity")
	public List<StoreApiView> getStoresByCity(@RequestParam String city) {
		List<StoreApiView> views = new ArrayList<>();
		List<DepartmentYzw> depts = departmentService.findByCity(city);
		for (DepartmentYzw dept : depts) {
			views.add(new StoreApiView(dept.getId(),
					dept.getName(),
					dept.getOfficialAddress()==null?"":dept.getOfficialAddress().getDetailAddress(),
		//TODO department表里没有官方电话， 要加进去 然后把 下面的参数替换掉
					dept.getOfficialAccount()));
		}
		return views;
	}

	
	@Deprecated
	@GetMapping(value = "/getAllStores")
	@ApiOperation("获取所有门店  已弃用 请使用/api/store")
	public List<StoreApiView> getAllStores() {
		List<StoreApiView> views = new ArrayList<>();
		List<DepartmentYzw> stores = departmentService.findAllStores();
		for (DepartmentYzw store : stores) {
			views.add(StoreApiViewConverter.INSTANCE.fromPO(store));
		}
		return views;
	}

	@Deprecated
	@GetMapping(value = "/getAllApiStores")
	@ApiOperation("获取所有门店,已启用, 使用/api/store")
	public YiwuJson<List<StoreApiView>> getAllApiStores() {
		List<StoreApiView> views = new ArrayList<>();
		List<DepartmentYzw> stores = departmentService.findAllStores();
		for (DepartmentYzw store : stores) {
			views.add(StoreApiViewConverter.INSTANCE.fromPO(store));
		}
		return new YiwuJson<>(views);
	}
}
