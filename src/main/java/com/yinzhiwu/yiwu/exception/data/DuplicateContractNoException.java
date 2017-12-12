
package com.yinzhiwu.yiwu.exception.data;

/**
 * @author ping
 * @date 2017年12月12日上午11:29:50
 * @since v2.2.0
 *	
 */
@SuppressWarnings("serial")
public class DuplicateContractNoException extends Exception{
	
	private String contractNo;

	public DuplicateContractNoException(String contractNo) {
		super(contractNo + " was duplicated in order table");
		this.contractNo = contractNo;
	}
	
	
}
