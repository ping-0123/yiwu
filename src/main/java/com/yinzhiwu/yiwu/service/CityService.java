package com.yinzhiwu.yiwu.service;

import java.util.List;

public interface CityService {

	public List<String> getCities();

	/**
	 * @param city
	 * @return
	 */
	public Object findDistrictsByCity(String city);

	/**
	 * @param disctrict
	 * @return
	 */
	public Object findStoresByDistrict(String disctrict);
}
