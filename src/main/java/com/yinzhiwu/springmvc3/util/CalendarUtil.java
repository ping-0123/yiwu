package com.yinzhiwu.springmvc3.util;

import java.util.Calendar;
import java.util.Date;

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
}
