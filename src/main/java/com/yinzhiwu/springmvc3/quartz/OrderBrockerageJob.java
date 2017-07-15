package com.yinzhiwu.springmvc3.quartz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.yinzhiwu.springmvc3.service.PurchaseEventService;

public class OrderBrockerageJob extends QuartzJobBean{
	
	private static final Log logger = LogFactory.getLog(OrderBrockerageJob.class);
	@Autowired private PurchaseEventService purchaseEventService;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		logger.debug(purchaseEventService.hashCode());
		purchaseEventService.saveAllLastDayPurchaseEvents();
	}
	



}
