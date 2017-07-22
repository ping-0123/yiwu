package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.yinzhiwu.yiwu.service.UserService;

//@Component
public class ServiceInvoker {
	
	@Autowired
	public void say(@Qualifier("main") UserService userService){
		userService.show();
	}
	

//		@Autowired
//		private List<UserService> list;
//		
//		public void say(){
//			if (list != null && 0 !=list.size()){
//				for (UserService userService : list) {
//					System.out.println(userService.getClass().getName());
//				}
//			}else
//				System.out.println("no UserService instance in IoC");
//		}
}
