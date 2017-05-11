package com.yinzhiwu.springmvc3.timer;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;


/**
 * 自动任务包括 order 产生佣金 和等级自动晋级
 * @author ping
 *
 */
@Component
public class BusinessReport implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		_perform();
	}
	
	public void execute(){
		_perform();
	}
	private void _perform(){
//		System.out.println("开始执行报表的业务逻辑了---现在的时间是---" + new Date());
	}

}
