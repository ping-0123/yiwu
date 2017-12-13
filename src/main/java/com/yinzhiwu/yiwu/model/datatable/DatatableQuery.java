package com.yinzhiwu.yiwu.model.datatable;

import java.util.ArrayList;
import java.util.List;

/**
*@Author ping
*@Time  创建时间:2017年9月22日下午7:40:02
*
*/

public class DatatableQuery {
	
	private int draw;
	private List<ColumnMap> columns = new ArrayList<>();
	private int start;
	private int length;
	private SearchMap search;
	private List<OrderMap> orders = new ArrayList<>();
	
	
	public int getDraw() {
		return draw;
	}
	public List<ColumnMap> getColumns() {
		return columns;
	}
	public int getStart() {
		return start;
	}
	public int getLength() {
		return length;
	}
	public SearchMap getSearch() {
		return search;
	}
	public List<OrderMap> getOrders() {
		return orders;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public void setColumns(List<ColumnMap> columns) {
		this.columns = columns;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public void setSearch(SearchMap search) {
		this.search = search;
	}
	public void setOrders(List<OrderMap> orders) {
		this.orders = orders;
	}
	
	
	
}
