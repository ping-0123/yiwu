package com.yinzhiwu.springmvc3.quartz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
*@Author ping
*@Time  创建时间:2017年7月15日上午11:53:38
*
*/

@Component
public class TestJob extends QuartzJobBean {
	private static final Log logger = LogFactory.getLog(TestJob.class);

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.debug("....is executing.....");
		System.out.println("........is executing......");
	}

}
