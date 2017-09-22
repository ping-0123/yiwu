package com.yinzhiwu.yiwu.model.datatable;

import java.io.Serializable;

public class QueryParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer draw;
	private Integer start;
	private Integer length;
	/**
	 * @return the draw
	 */
	public Integer getDraw() {
		return draw;
	}
	/**
	 * @param draw the draw to set
	 */
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	/**
	 * @return the start
	 */
	public Integer getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(Integer start) {
		this.start = start;
	}
	/**
	 * @return the length
	 */
	public Integer getLength() {
		return length;
	}
	/**
	 * @param length the length to set
	 */
	public void setLength(Integer length) {
		this.length = length;
	}
	/**
	 * @return the search
	 */
}
