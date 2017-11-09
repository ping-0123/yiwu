package com.yinzhiwu.yiwu.enums;

/**
 * 
 * @author ping
 * @Date 2017年10月29日 下午5:11:40
 *
 */
public enum IncomeType {
	
	EXP(10012,"经验"),
	FUNDS(10013,"基金"),
	BROKERAGE(10014,"佣金");
	
	private final int id;
	private final String name;

	public int getId() {
		return id;
	}

	private IncomeType(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
