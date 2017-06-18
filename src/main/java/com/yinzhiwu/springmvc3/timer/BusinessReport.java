package com.yinzhiwu.springmvc3.timer;

import java.io.Serializable;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;


/**
 * 自动任务包括 order 产生佣金 和等级自动晋级
 * @author ping
 *
 */
@Service
public class BusinessReport implements Job, Serializable{
	

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 5470492588127078141L;
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		_perform();
	}
	
	public void execute(){
		_perform();
	}
	private void _perform(){
		try {
			long l = (long) (Math.random()* 6*1000);
			Thread.sleep(l);
//			System.out.println("暂停: " + l/1000 + "秒开始执行报表的业务逻辑了---现在的时间是---" + new Date());
//			System.out.println(customerService.getClass().getSimpleName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
