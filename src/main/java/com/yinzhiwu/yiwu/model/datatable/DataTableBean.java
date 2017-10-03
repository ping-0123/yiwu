package com.yinzhiwu.yiwu.model.datatable;

import java.io.Serializable;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
*@Author ping
*@Time  创建时间:2017年9月22日下午3:31:58
*
*/

@JsonInclude(value= Include.NON_NULL)
public class DataTableBean<T> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6766541790023377659L;
	private Integer draw;
	private Integer recordsTotal;
	private Integer recordsFiltered;
	private Collection<T> data;
	private String error;
	
	
	public DataTableBean(){};
	
	
	public DataTableBean(Integer draw, Integer recordsTotal, Integer recordsFiltered, Collection<T> data, String error) {
		this.draw = draw;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.data = data;
		if(this.error==null)
			this.error = "";
	}

	private DataTableBean(String errorMessage){
		this.error = errorMessage;
	}

	public Integer getDraw() {
		return draw;
	}
	public Integer getRecordsTotal() {
		return recordsTotal;
	}
	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}
	public Collection<?> getData() {
		return data;
	}
	public String getError() {
		return error;
	}
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public void setRecordsFiltered(Integer recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public void setData(Collection<T> data) {
		this.data = data;
	}
	public void setError(String error) {
		this.error = error;
	}


	public static DataTableBean<?> createByErrorMessage(String message) {
		return new DataTableBean<>(message);
	}

}
