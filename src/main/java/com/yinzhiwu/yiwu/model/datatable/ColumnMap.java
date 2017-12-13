
package com.yinzhiwu.yiwu.model.datatable;

import java.util.HashMap;

/**
 * @author ping
 * @date 2017年12月13日下午5:28:26
 * @since v2.2.0
 *	
 */
public class ColumnMap extends HashMap<String, Object> {
	
	private static final long serialVersionUID = -7932208204407060101L;
	
	
	private static final String keyName = "data";
	private static final String keyOperator = "operator";
	private static final String keyMinValue = "min";
	private static final String keyMaxValue = "max";
	private static final String keyValue = "value";
	private static final String keySearchable = "searchable";
	private static final String keyOrderable = "orderable";
	private static final String keySearch = "search";
	
	public ColumnMap() {
		super(8);
		this.put(keySearch, new SearchMap());
	}
	
	
	
}
