package com.yinzhiwu.yiwu.model;

import java.util.Date;
import java.util.List;

public class RevenueList {
	
	private Date date;
	
	/**
	 * 一天所有门店的营业额
	 */
	private List<RevenueModel> list;
	
	

	public RevenueList(Date date, List<RevenueModel> list) {
		this.date = date;
		this.list = list;
	}


	public final Date getDate() {
		return date;
	}

	public final void setDate(Date date) {
		this.date = date;
	}

	public final List<RevenueModel> getList() {
		return list;
	}

	public final void setList(List<RevenueModel> list) {
		this.list = list;
	}
	
	
}
