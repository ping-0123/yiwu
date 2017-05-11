package com.yinzhiwu.springmvc3.timer;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class BusinessReport implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		_perform();
	}
	
	public void execute(){
		_perform();
	}
	private void _perform(){
		System.out.println("开始执行报表的业务逻辑了---现在的时间是---" + new Date());
	}

}
