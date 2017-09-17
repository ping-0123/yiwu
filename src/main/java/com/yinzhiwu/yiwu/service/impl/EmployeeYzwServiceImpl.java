package com.yinzhiwu.yiwu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.EmployeeYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.model.view.EmployeeApiView;
import com.yinzhiwu.yiwu.service.EmployeeYzwService;

/**
 * 
 * @author ping
 * @Date 2017年9月17日 下午9:21:17
 *
 */
@Service
public class EmployeeYzwServiceImpl extends BaseServiceImpl<EmployeeYzw, Integer> implements EmployeeYzwService {

	@Autowired public void setBaseDao(EmployeeYzwDao dao){super.setBaseDao(dao);}
	
	@Override
	public List<EmployeeApiView> getAllOnJobCoaches() {
		// TODO Auto-generated method stub
		return null;
	}

}
