package com.yinzhiwu.yiwu.util;

import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {

	public static boolean isAudit(Date birthday) {
		Calendar today = Calendar.getInstance();
		Calendar bir = Calendar.getInstance();
		bir.setTime(birthday);
		bir.add(Calendar.YEAR, 18);
		if (bir.before(today))
			return true;
		else
			return false;

	}

	public static long getAge(Date birthDay) {
		if(birthDay == null ) throw new IllegalArgumentException("birthDay can not be null");
		
		Calendar today = Calendar.getInstance();
		Calendar birthday = Calendar.getInstance();
		birthday.setTime(birthDay);
		long age = today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
		if(today.get(Calendar.MONTH) <= birthday.get(Calendar.MONTH)){
			if(today.get(Calendar.MONTH) == birthday.get(Calendar.MONTH)){
				if(today.get(Calendar.DAY_OF_MONTH) < birthday.get(Calendar.DAY_OF_MONTH))
					age--;
			}else
				age--;
		}
		return age;
	}

	public static Calendar getTodayBegin() {
		return getDayBegin(Calendar.getInstance());
	}

	public static Calendar getTodayEnd() {
		return getDayEnd(Calendar.getInstance());
	}

	public static Calendar getDayBegin(final Calendar calendar) {
		return getDayBegin(calendar.getTime());
	}

	public static Calendar getDayEnd(final Calendar calendar) {
		return CalendarUtil.getDayEnd(calendar.getTime());
	}

	public static Calendar getDayBegin(final Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c;
	}

	public static Calendar getDayEnd(final Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar;
	}
	
}
