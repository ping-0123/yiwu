package com.yinzhiwu.yiwu.entity.yzw;

import com.yinzhiwu.yiwu.common.entity.DateIdGenerator;

public class OrderIdGenerator extends DateIdGenerator {
	
	public static final String SEQUENCE_KEY = "ORDER_ID";
	
	private static final String PREFIX = "";
	
	public OrderIdGenerator(int value){
		super(PREFIX,value);
	}
}
