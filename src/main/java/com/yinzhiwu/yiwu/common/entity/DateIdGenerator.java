package com.yinzhiwu.yiwu.common.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateIdGenerator implements IdGenerator<String> {
	
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
	/**
	 * 默认的随机数位数
	 */
	private static final int DEFAULT_RANDOM_DIGIT = 3;
	
	private String prefix;
	private Date date;
	private int randomValue;
	private int randomDigit;
	
	
	
	public DateIdGenerator(String prefix, Date date, int randomValue, int randomDigit) {
		this.prefix = prefix;
		this.date = date;
		this.randomValue = randomValue;
		this.randomDigit = randomDigit;
	}

	

	public DateIdGenerator(String prefix, Date date, int randomValue) {
		this.prefix = prefix;
		this.date = date;
		this.randomValue = randomValue;
		this.randomDigit = DEFAULT_RANDOM_DIGIT;
	}

	

	public DateIdGenerator(String prefix, int randomValue) {
		this.prefix = prefix;
		this.randomValue = randomValue;
		this.date = new Date();
		this.randomDigit = DEFAULT_RANDOM_DIGIT;
	}



	@Override
	public String generateId() {
		return prefix + DATE_FORMAT.format(date)+ String.format("%0" +randomDigit +"d", randomValue);
	}
	
}
