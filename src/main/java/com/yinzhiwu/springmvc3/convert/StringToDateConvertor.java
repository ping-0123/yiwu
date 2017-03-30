package com.yinzhiwu.springmvc3.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.convert.converter.Converter;

public class StringToDateConvertor 
	implements Converter<String, Date>
{
	
	private static final Log logger =
			LogFactory.getLog(StringToDateConvertor.class);
	
	private String datePattern;
	private SimpleDateFormat simpleDateFormat;
	

	public StringToDateConvertor(String datePattern) {
		this.setDatePattern(datePattern);
		this.simpleDateFormat = new SimpleDateFormat(datePattern);
	}


	@Override
	public Date convert(String arg0) {
		try {
			return simpleDateFormat.parse(arg0);
		} catch (ParseException e) {
			logger.equals("日期格式不匹配: " + datePattern);
			return null;
		}
	}


	public String getDatePattern() {
		return datePattern;
	}


	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}

}
