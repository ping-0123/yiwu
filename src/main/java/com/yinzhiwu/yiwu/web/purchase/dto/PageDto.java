package com.yinzhiwu.yiwu.web.purchase.dto;

import io.swagger.annotations.ApiModel;

/**
*@Author ping
*@Time  创建时间:2017年7月31日下午7:23:55
*
*/

@ApiModel
public class PageDto {

	private Integer pageNo;
	private Integer pageSize;
	
	public Integer getPageNo() {
		return pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
