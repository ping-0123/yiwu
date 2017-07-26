package com.yinzhiwu.yiwu.convert;

public class StringToBooleanConvertor {

	// @Override
	public Boolean convert(String source) {
		if ("true".equals(source))
			return new Boolean(true);
		else
			return new Boolean(false);
	}

}
