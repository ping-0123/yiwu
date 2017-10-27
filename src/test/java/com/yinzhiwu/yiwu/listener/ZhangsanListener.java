package com.yinzhiwu.yiwu.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.yinzhiwu.yiwu.event.ContentEvent;

/**
*@Author ping
*@Time  创建时间:2017年10月27日下午3:23:52
*
*/

@Component
public class ZhangsanListener implements ApplicationListener<ContentEvent>{

	@Override
	public void onApplicationEvent(ContentEvent event) {
		System.out.println("张三收到了新的内容： " + event.getSource());
	}

}
