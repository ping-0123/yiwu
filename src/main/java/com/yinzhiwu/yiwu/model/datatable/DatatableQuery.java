package com.yinzhiwu.yiwu.model.datatable;

/**
*@Author ping
*@Time  创建时间:2017年9月22日下午7:40:02
*
*/

public class DatatableQuery {
	
	private int draw;
	private Column[] columns = new Column[50];
	private int start;
	private int length;
	private Search search;
	private Order[] order = new Order[50];
	
	public Integer getDraw() {
		return draw;
	}
	public Column[] getColumns() {
		return columns;
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
	public Order[] getOrder() {
		return order;
	}
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	public void setColumns(Column[] columns) {
		this.columns = columns;
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
	public void setOrder(Order[] order) {
		this.order = order;
	}
	
	
	
	
}
