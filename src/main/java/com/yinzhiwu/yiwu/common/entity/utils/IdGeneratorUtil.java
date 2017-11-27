package com.yinzhiwu.yiwu.common.entity.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IdGeneratorUtil {

	private static Log log = LogFactory.getLog(IdGeneratorUtil.class);

	private static final String PREFIX = "E5";
	/**
	 * 合约号前缀
	 */
	private static final String CONTRACT_NO_PREFIX ="E5";
	private static final int LENGTH = 8;

	private static final char PADDING_CHAR = '0';

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

	public static String generateMemberId(int id) {
		return String.format(PREFIX + "%" + PADDING_CHAR + (LENGTH - PREFIX.length()) + "d", id);
	}

	public static String generateYzwId(String maxId) {
		String id_date = maxId.substring(0, 8);
		log.debug(id_date);
		Date date = null;
		Date today2 = new Date();
		// Date today= new Date(today2.getYear(),today2.getMonth(),
		// today2.getDate());
		Date today = null;
		try {
			today = DATE_FORMAT.parse(DATE_FORMAT.format(today2));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		log.debug("today is " + today);
		try {
			date = DATE_FORMAT.parse(id_date);
			log.debug("date is " + date);
		} catch (ParseException e) {
			log.debug(e.getStackTrace());
			return _generate_yzw_id(today, 1);
		}
		if (today.after(date)) {
			log.debug("today is after " + date);
			return _generate_yzw_id(today, 1);
		}

		int id_num = Integer.valueOf(maxId.replace(id_date, ""));
		log.debug(id_num);
		return _generate_yzw_id(today, id_num + 1);
	}

	public static String generateContractNo(String orderId) {
		return CONTRACT_NO_PREFIX + orderId;
	}

	private static String _generate_yzw_id(Date today, int i) {
		return DATE_FORMAT.format(today) + String.format("%03d", i);
	}

}
