package com.yinzhiwu.springmvc3.timer;

import java.io.Serializable;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.yinzhiwu.springmvc3.service.MoneyRecordService;

@Component
public class OrderBrockerageJob extends QuartzJobBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6444725477676758565L;
	

	@Autowired
	private MoneyRecordService moneyRecordService;

	@Override
	public void executeInternal(JobExecutionContext context) throws JobExecutionException {
		System.out.println(moneyRecordService);
//		moneyRecordService.saveCommissionRecord();
	}


}
