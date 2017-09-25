package com.yinzhiwu.yiwu.util;

import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;

public class MySQLExtendDialect extends MySQLDialect {

	public MySQLExtendDialect() {
		super();
		registerFunction("convert_gbk", new SQLFunctionTemplate(new StringType(), "convert(?1 using gbk)"));
		registerFunction("cast_to_char", new SQLFunctionTemplate(new IntegerType(), "cast(?1 as char)"));
	}
}
