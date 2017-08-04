package com.yinzhiwu.yiwu.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.entity.StoreInfo;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.StoreApiView;
import com.yinzhiwu.yiwu.service.DepartmentYzwService;
import com.yinzhiwu.yiwu.service.StoreInfoService;

@RestController
@RequestMapping(value = "/api/store")
public class StoreApiController {

	@Autowired private StoreInfoService storeInfoService;
	@Autowired private DepartmentYzwService departmentYzwService;

	@GetMapping(value = "/list")
	public List<StoreApiView> getStoreList(@RequestParam Integer districtId) {
		return departmentYzwService.findStoreApiViewsUnderOrganization(districtId);
	}

	@Deprecated
	@GetMapping(value = "/id/{id}")
	public StoreApiView getStore(@PathVariable int id) {
		StoreInfo  info = storeInfoService.get(id);
		if(info != null)
			return new StoreApiView(info);
		return null;
	}

	@GetMapping(value = "/{storeId}")
	public StoreApiView getStoreById(@PathVariable int storeId) {
		StoreInfo  info = storeInfoService.get(storeId);
		if(info != null)
			return new StoreApiView(info);
		return null;
	}

	@GetMapping(value = "/getStoresByCity")
	public List<StoreApiView> getStoresByCity(@RequestParam String city) {
		List<StoreApiView> views = new ArrayList<>();
		List<StoreInfo> infos = storeInfoService.findByProperty("address.city", city);
		if(infos.size() >=0){
			for (StoreInfo storeInfo : infos) {
				views.add(new StoreApiView(storeInfo));
			}
		}
		return views;
	}

	@GetMapping(value = "/getAllStores")
	public List<StoreApiView> getAllStores() {
		List<StoreApiView> views = new ArrayList<>();
		List<StoreInfo> infos = storeInfoService.findAll();
		if(infos.size() >=0){
			for (StoreInfo storeInfo : infos) {
				views.add(new StoreApiView(storeInfo));
			}
		}
		return views;
	}

	@GetMapping(value = "/getAllApiStores")
	public YiwuJson<List<StoreApiView>> getAllApiStores() {
		List<StoreApiView> views = new ArrayList<>();
		List<StoreInfo> infos = storeInfoService.findAll();
		if(infos.size() >=0){
			for (StoreInfo storeInfo : infos) {
				views.add(new StoreApiView(storeInfo));
			}
		}
		return new YiwuJson<>(views);
	}
}