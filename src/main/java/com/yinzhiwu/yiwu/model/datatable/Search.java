package com.yinzhiwu.yiwu.model.datatable;

/**
*@Author ping
*@Time  创建时间:2017年9月22日下午7:25:09
*
*/

public class Search {
	
	private String value;
	private boolean regex;
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the regex
	 */
	public boolean isRegex() {
		return regex;
	}
	/**
	 * @param regex the regex to set
	 */
	public void setRegex(boolean regex) {
		this.regex = regex;
	}
	
	
	
}
