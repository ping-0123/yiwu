package com.yinzhiwu.yiwu.model.datatable;

/**
*@Author ping
*@Time  创建时间:2017年9月22日下午7:37:21
*
*/

public class Column {
	
	// datatable 引用的列名
	private String data; 
	private String name;
	private boolean searchable;
	private Search search;
	private boolean orderable;
	
	
	public String getData() {
		return data;
	}
	public String getName() {
		return name;
	}
	public boolean isSearchable() {
		return searchable;
	}
	public Search getSearch() {
		return search;
	}
	public boolean isOrderable() {
		return orderable;
	}
	public void setData(String data) {
		this.data = data;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSearchable(boolean searchable) {
		this.searchable = searchable;
	}
	public void setSearch(Search search) {
		this.search = search;
	}
	public void setOrderable(boolean orderable) {
		this.orderable = orderable;
	}
	
}
