package com.yinzhiwu.yiwu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.DepartmentYzwDao;
import com.yinzhiwu.yiwu.service.CityService;

/**
*@Author ping
*@Time  创建时间:2017年9月13日下午7:47:43
*
*/

@Service
public class CityServiceImpl implements CityService{

	@Autowired private DepartmentYzwDao deptDao;

	/* (non-Javadoc)
	 * @see com.yinzhiwu.yiwu.service.CityService#getCities()
	 */
	@Override
	public List<String> getCities() {
		return deptDao.findCities();
	}

	/* (non-Javadoc)
	 * @see com.yinzhiwu.yiwu.service.CityService#findDistrictsByCity(java.lang.String)
	 */
	@Override
	public Object findDistrictsByCity(String city) {
		return deptDao.findDistrictsByCity(city);
	}

	/* (non-Javadoc)
	 * @see com.yinzhiwu.yiwu.service.CityService#findStoresByDistrict(java.lang.String)
	 */
	@Override
	public Object findStoresByDistrict(String disctrict) {
		return deptDao.findStoresByAdministrativeDistrict(disctrict);
	}
	
}
