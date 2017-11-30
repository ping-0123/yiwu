package com.yinzhiwu.yiwu.entity.yzw;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.yinzhiwu.yiwu.common.entity.IdGenerator;

public class EmployeeNumberGenerator implements IdGenerator<String>{
	
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyMMdd");
	public static final String SEQUENCE_KEY = "EMPLOYEE_NUMBER";
	
	
	
	public EmployeeNumberGenerator(Date date, Integer value) {
		this.date = date;
		this.value = value;
	}
	
	

	public EmployeeNumberGenerator(Integer value) {
		this.date = new Date();
		this.value = value;
	}



	private Date date;
	private Integer value;
	
	@Override
	public String generateId() {
		return DATE_FORMAT.format(date)+ String.format("%02d", value);
	}

}
