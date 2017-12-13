package com.yinzhiwu.yiwu.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.service.CityService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/cities")
public class CityApiController {

	@Autowired private CityService cityService;
	
	@GetMapping
	@ApiOperation(value="返回存在音之舞门店的城市列表")
	public YiwuJson<?> findAll(){
		java.util.List<String> cities = cityService.getCities();
		return YiwuJson.createBySuccess(cities);
	}
	
	@GetMapping(value="/{cityName}/districts")
	@ApiOperation(value="返回行政区")
	public YiwuJson<?> findDistricts(@PathVariable(name="cityName") String city){
		return YiwuJson.createBySuccess(cityService.findDistrictsByCity(city));
	}
	
	@GetMapping(value="/{cityname}/districts/{districtname}/stores")
	@ApiOperation(value="返回位于行政区{districtname}的门店")
	public YiwuJson<?> findStores(@PathVariable(name="cityname") String cityname, @PathVariable(name="districtname") String disctrict){
		return YiwuJson.createBySuccess(cityService.findStoresByDistrict(disctrict));
	}
}
