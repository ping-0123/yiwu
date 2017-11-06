package com.yinzhiwu.yiwu.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.yinzhiwu.yiwu.event.ContentEvent;

/**
*@Author ping
*@Time  创建时间:2017年10月27日下午3:18:53
*
*/

@Component
public class LisiListener implements ApplicationListener<ApplicationEvent> {

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if(event instanceof ContentEvent){
			System.out.println("Lisi listener execute in thread " + Thread.currentThread());
//			throw new RuntimeException("中断 Lisi listner 执行");
			System.out.println("李四收到了新的内容: " + event.getSource());
		}
	}
	
	@Async
	@EventListener(classes={ContentEvent.class})
	public  void useEventListener(ContentEvent event){
		System.out.println("Event listener execute in thread " + Thread.currentThread());
		System.out.println("李四在EventListener中收到了新额内容: " +  event.getSource());
	}
}
