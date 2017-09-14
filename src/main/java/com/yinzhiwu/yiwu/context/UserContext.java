package com.yinzhiwu.yiwu.context;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.validator.internal.util.Contracts;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.model.view.DistributerApiView;

/**
*@Author ping
*@Time  创建时间:2017年7月13日下午1:35:57
*
*/

public abstract class UserContext {

	private static ThreadLocal<Map<String, Object>> map = new ThreadLocal<>();
	
	public static void setUser(Distributer user){
		getSureMap().put(Constants.CURRENT_USER, user);
	}
	
	public static Distributer getUser(){
		return (Distributer) getSureMap().get(Constants.CURRENT_USER);
	}
	
	public static void setDistributer(DistributerApiView view){
		getSureMap().put(Constants.CURRENT_DISTRIBUTER_VIWE, view);
	}
	public static DistributerApiView getDistributer(){
		return (DistributerApiView) getSureMap().get(Constants.CURRENT_DISTRIBUTER_VIWE);
	}
	
	public static void setEmployeeUser(EmployeeYzw emp){
		getSureMap().put(Constants.CURRENT_EMPLOYEE_USER,emp);
	}
	
	public static EmployeeYzw getEmployeeUser(){
		return (EmployeeYzw) getSureMap().get(Constants.CURRENT_EMPLOYEE_USER);
	}
	
	private static Map<String, Object> getSureMap(){
		if(map.get() == null)
			map.set(new HashMap<>());
		return map.get();
	}
}
