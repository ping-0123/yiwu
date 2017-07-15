package com.yinzhiwu.springmvc3.quartz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
*@Author ping
*@Time  创建时间:2017年7月15日下午1:48:56
*
*/

public class CronJob extends QuartzJobBean {
	private static final Log logger = LogFactory.getLog(CronJob.class);

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.debug("....CronJob is executing....");
	}

}
