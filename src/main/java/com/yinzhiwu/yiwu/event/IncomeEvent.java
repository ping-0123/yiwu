package com.yinzhiwu.yiwu.event;

/**
 * 实现该接口的事件发生后会产生相应的收益
* @author 作者 ping
* @Date 创建时间：2017年11月9日 上午12:34:52
*
*/

public interface IncomeEvent {
	
	/**
	 * 事件类型， 不同的类型产生的收益不同
	 * @return
	 */
	public IncomeEventType getType();
	
	public Object getSource();
	
	public String getSourceId();
	
	public Object getSubject();
	
	public Float getValue();
}
