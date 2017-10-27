package com.yinzhiwu.yiwu.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

import com.yinzhiwu.yiwu.event.ContentEvent;

/**
*@Author ping
*@Time  创建时间:2017年10月27日下午3:25:11
*
*/

@Component
public class WangwuListener implements SmartApplicationListener {

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		 System.out.println("王五在孙六之前收到新的内容：" + event.getSource());  
	}

	@Override
	public int getOrder() {
		return 1;
	}

	@Override
	public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
		return ContentEvent.class == eventType;
	}

	@Override
	public boolean supportsSourceType(Class<?> sourceType) {
		return String.class==sourceType;
	}

}
