
package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.OperatingPlanDao;
import com.yinzhiwu.yiwu.entity.OperatingPlan;
import com.yinzhiwu.yiwu.service.OperatingPlanService;

/**
 * @author ping
 * @date 2017年12月6日上午11:45:34
 * @since v2.2.0
 *	
 */

@Service
public class OperatingPlanServiceImpl extends BaseServiceImpl<OperatingPlan,Integer> implements OperatingPlanService{
	
	@Autowired
	public void setBaseDao(OperatingPlanDao dao){super.setBaseDao(dao);}
	
	
}
