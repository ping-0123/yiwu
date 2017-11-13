package com.yinzhiwu.yiwu.context;

import java.util.HashMap;
import java.util.Map;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.sys.User;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;

/**
*@Author ping
*@Time  创建时间:2017年7月13日下午1:35:57
*
*/

public abstract class UserContext {

	private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();
	
	public static void setUser(User user){
		getSureMap().put(Constants.CURRENT_USER, user);
	}
	
	public static User getUser(){
		return (User) getSureMap().get(Constants.CURRENT_USER);
	}
	
	
	public static void setDistributer(Distributer distributer){
		getSureMap().put(Constants.CURRENT_DISTRIBUTER, distributer);
	}
	public static Distributer getDistributer(){
		return (Distributer) getSureMap().get(Constants.CURRENT_DISTRIBUTER);
	}
	
	public static void setEmployeeUser(EmployeeYzw emp){
		getSureMap().put(Constants.CURRENT_EMPLOYEE_USER,emp);
	}
	
	public static EmployeeYzw getEmployeeUser(){
		return (EmployeeYzw) getSureMap().get(Constants.CURRENT_EMPLOYEE_USER);
	}
	
	private static Map<String, Object> getSureMap(){
		if(threadLocal.get() == null)
			threadLocal.set(new HashMap<>());
		return threadLocal.get();
	}
}
