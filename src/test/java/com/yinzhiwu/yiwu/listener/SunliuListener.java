package com.yinzhiwu.yiwu.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

import com.yinzhiwu.yiwu.event.ContentEvent;

/**
*@Author ping
*@Time  创建时间:2017年10月27日下午3:26:53
*
*/

@Component
public class SunliuListener implements SmartApplicationListener {

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		System.out.println("孙六在王五之后收到新的内容：" + event.getSource());  
		
	}

	@Override
	public int getOrder() {
		return 2;
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
