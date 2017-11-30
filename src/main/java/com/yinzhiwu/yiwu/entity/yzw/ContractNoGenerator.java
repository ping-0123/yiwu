package com.yinzhiwu.yiwu.entity.yzw;

import com.yinzhiwu.yiwu.zhangkaitao.common.entity.DateIdGenerator;

public class ContractNoGenerator extends DateIdGenerator {

	public static final String SEQUENCE_KEY = "CONTRACT_NO";
	
	private static final String PREFIX = "No";
	
	public ContractNoGenerator(int randomValue) {
		super(PREFIX, randomValue);
	}

}
