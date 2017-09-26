package com.yinzhiwu.yiwu.enums;

/**
*@Author ping
*@Time  创建时间:2017年8月29日下午3:14:50
*
*/

public enum DataStatus {
	NORMAL(0,"正常"),
	FORBID(1,"禁用"),
	DELETE(2,"删除");
	
	private final int index;
	private final String name;
	
	private DataStatus(int index, String name)
	{
		this.index = index;
		this.name= name;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}
	
	
}
