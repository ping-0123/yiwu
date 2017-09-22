package com.yinzhiwu.yiwu.model.datatable;

/**
*@Author ping
*@Time  创建时间:2017年9月22日下午7:34:25
*
*/

public class Order {
	public static enum Direction{
		asc,desc}
	
	private Integer column;
	private Direction dir;
	
	
	
	public Order() {
	}
	
	
	public Order(Integer column, Direction dir) {
		this.column = column;
		this.dir = dir;
	}


	public Integer getColumn() {
		return column;
	}
	public Direction getDir() {
		return dir;
	}
	public void setColumn(Integer column) {
		this.column = column;
	}
	public void setDir(Direction dir) {
		this.dir = dir;
	}
	
	
}
