package com.yinzhiwu.springmvc3.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class CalendarUtil {

	public static boolean isAudit(Date birthday)
	{
		Calendar today = Calendar.getInstance();
		Calendar bir = Calendar.getInstance();
		bir.setTime(birthday);
		bir.add(Calendar.YEAR, 18);
		if(bir.before(today))
			return true;
		else
			return false;
			
	}
	
	public static long getAge(Date birthday){
		Calendar today = Calendar.getInstance();
		Calendar bir = Calendar.getInstance();
		bir.setTime(birthday);
		long age = (today.getTimeInMillis() - bir.getTimeInMillis())/(1000*3600*24*365);
		return age;
	}
	
	public static Date getTodayZeroTime(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND,0);
		return calendar.getTime();
	}
}
