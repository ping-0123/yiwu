package com.yinzhiwu.yiwu.model.datatable;

import java.util.List;

/**
*@Author ping
*@Time  创建时间:2017年9月22日下午7:40:02
*
*/

public class QueryParameter {
	
	private Integer draw;
	private List<Column> column;
	private Integer start;
	private Integer length;
	private Search search;
	private List<Order> order;
	
	public Integer getDraw() {
		return draw;
	}
	public List<Column> getColumn() {
		return column;
	}
	public Integer getStart() {
		return start;
	}
	public Integer getLength() {
		return length;
	}
	public Search getSearch() {
		return search;
	}
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	public void setColumn(List<Column> column) {
		this.column = column;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public void setSearch(Search search) {
		this.search = search;
	}
	public List<Order> getOrder() {
		return order;
	}
	public void setOrder(List<Order> order) {
		this.order = order;
	}
	
	
}
