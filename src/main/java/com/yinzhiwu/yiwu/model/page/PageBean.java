package com.yinzhiwu.yiwu.model.page;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PageBean<T> {

	// public static final Class<?> beanClazz =
	// (Class<?>) ((ParameterizedType)
	// PageBean.class.getGenericSuperclass()).getActualTypeArguments()[0];

	public static final int DEFAULT_PAGE_SIZE = 10;

	public static final int DEFAULT_PAGE_NO = 1;

	private int pageSize; // 每页显示多少条记录

	private int currentPage; // 当前第几页数据

	private int totalRecord; // 一共多少条记录

	private int totalPage;

	private List<T> data = new ArrayList<T>();

	public PageBean() {
	}

	public PageBean(int pageSize, int pageNo, List<T> sourceList) {
		if (sourceList == null || sourceList.size() == 0)
			return;
		if (pageSize <= 0)
			throw new IllegalArgumentException("pageSize should be positive.");
		if (pageNo <= 0)
			throw new IllegalArgumentException("pageNum should be positive.");

		this.pageSize = pageSize;
		this.totalRecord = sourceList.size();
		this.totalPage = this.totalRecord / this.pageSize;
		if (this.totalRecord % this.pageSize != 0)
			this.totalPage = this.totalPage + 1;
		this.currentPage = this.totalPage < pageNo ? this.totalPage : pageNo;
		int fromIndex = this.pageSize * (pageNo - 1);
		int toIndex = pageSize * pageNo > totalRecord ? totalRecord : pageSize * pageNo;
		this.data = sourceList.subList(fromIndex, toIndex);
	}

	public PageBean(int pageSize, int pageNo, int totalRecord, List<T> datas) {
		if (datas == null || datas.size() == 0)
			return;
		if (datas.size() > pageSize)
			throw new IllegalArgumentException("the size of datas should be less than ot equal to pageSize. ");
		if (pageSize <= 0)
			throw new IllegalArgumentException("pageSize should be positive.");
		if (pageNo <= 0)
			throw new IllegalArgumentException("pageNum should be positive.");
		if (totalRecord <= 0)
			throw new IllegalArgumentException("totalRecord should be positive.");

		this.pageSize = pageSize;
		this.currentPage = pageNo;
		this.totalRecord = totalRecord;
		this.totalPage = this.totalRecord / this.pageSize;
		if (this.totalRecord % this.pageSize != 0)
			this.totalPage = this.totalPage + 1;
		this.data = datas;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public int getTatalPage() {
		return totalPage;
	}

	@JsonIgnore
	public List<T> getList() {
		return data;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public void setTatalPage(int tatalPage) {
		this.totalPage = tatalPage;
	}

	public void setList(List<T> list) {
		this.data = list;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
