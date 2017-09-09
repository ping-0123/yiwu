package com.yinzhiwu.yiwu.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.service.DistributerService;
import com.yinzhiwu.yiwu.service.OrderYzwService;
import com.yinzhiwu.yiwu.service.PurchaseEventService;
import com.yinzhiwu.yiwu.util.CalendarUtil;

/**
*@Author ping
*@Time  创建时间:2017年9月9日下午12:02:46
*
*/

@Service(value="task")
public class TempTaskImpl {
	
	private static final Log logger = LogFactory.getLog(TempTaskImpl.class);
	
	@Autowired private DistributerService distributerService;
	@Autowired private OrderYzwService orderService;
	@Autowired private PurchaseEventService purchaseEventService;
	
	
	public void setSuperAndCaculateBrockerage(){
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		Date end = CalendarUtil.getDayEnd( calendar).getTime();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date start = CalendarUtil.getDayBegin(calendar).getTime();
		
		set_super_distributer_and_caculate_brokerage(start , end);
	}
	
	/**
	 * 找出一段时间内没有使用邀请码注册的员工, 设置他的客户归属为服务归属对应的distributer, 并且重新计算佣金
	 * @param start 
	 * @param end
	 */
//	@Transactional(value="transactionManager")
	public void set_super_distributer_and_caculate_brokerage(Date start, Date end){
		
		/**
		 * 1.找出没有上级分销者（客户归属)的 ditributers
		 * 2.将他的客户归属改为服务归属（如果存在）对应的distributer
		 * 3.计算佣金
		 */
		
		List<Distributer>  distributers = distributerService.findCustomerDistributersWhoNoSuperByRegisterDate(start, end);
		if(logger.isDebugEnabled()){
			logger.debug(distributers.size() + "个客户没有客户归属");
		}
		for (Distributer distributer : distributers) {
			
			if(logger.isDebugEnabled()){
				logger.debug("开始设置第" + distributers.indexOf(distributer) + "个客户：" + distributer.getId() + " 的客户归属");
			}
			
			distributerService.setSuperToServer(distributer);
			List<OrderYzw> orders = orderService.findBrokeragableOrdersSinceRegesterDate(distributer);
			
			if(logger.isDebugEnabled()){
				logger.debug("Distributer " + distributer.getId() + "自注册后购买了 " + orders.size() + "单");
			}
			
			for (OrderYzw order : orders) {
				
				if(logger.isDebugEnabled()){
					logger.debug("开始为订单 " + order.getId() + " 生成佣金收益");
				}
				
				purchaseEventService.savePurchaseEventWithoutSelfIncome(order);		
			}
		}
		
	}
}
