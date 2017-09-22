package com.yinzhiwu.yiwu.model.datatable;

import java.io.Serializable;

/**
*@Author ping
*@Time  创建时间:2017年9月22日下午7:40:02
*
*/

public class QueryParameter implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer draw;
	private Column[] columns;
	private Integer start;
	private Integer length;
	private Search search;
	private Order[] order;
	
	public Integer getDraw() {
		return draw;
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

	public void setStart(Integer start) {
		this.start = start;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public void setSearch(Search search) {
		this.search = search;
	}

	/**
	 * @return the columns
	 */
	public Column[] getColumns() {
		return columns;
	}

	/**
	 * @param columns the columns to set
	 */
	public void setColumns(Column[] columns) {
		this.columns = columns;
	}

	/**
	 * @return the order
	 */
	public Order[] getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(Order[] order) {
		this.order = order;
	}
	
	
}
