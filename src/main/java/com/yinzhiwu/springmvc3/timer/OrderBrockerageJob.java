package com.yinzhiwu.springmvc3.timer;

import java.io.Serializable;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class OrderBrockerageJob extends QuartzJobBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6444725477676758565L;

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		
	}
	



}
