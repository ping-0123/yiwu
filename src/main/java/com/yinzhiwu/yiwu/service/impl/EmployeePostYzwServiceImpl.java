package com.yinzhiwu.yiwu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.EmployeePostYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.EmployeePostYzw;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;
import com.yinzhiwu.yiwu.service.EmployeePostYzwService;

@Service
public class EmployeePostYzwServiceImpl extends BaseServiceImpl<EmployeePostYzw,Integer> implements EmployeePostYzwService {

	@Autowired public void setBaseDao(EmployeePostYzwDao dao){super.setBaseDao(dao);}
	@Autowired private EmployeePostYzwDao epDao;
	
	@Override
	public List<EmployeePostYzw> findByEmployeeId(Integer empId) {
		return epDao.findByEmployeeId(empId);
	}

	@Override
	public DataTableBean<?> findDataTableByEmployeeId(QueryParameter parameter, Integer empId) throws NoSuchFieldException, SecurityException {
		return epDao.findDataTableByEmployeeId(parameter,empId);
	}

	
	
	
}
