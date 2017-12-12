
package com.yinzhiwu.yiwu.exception.business;

/**
 * @author ping
 * @date 2017年12月12日上午11:07:34
 * @since v2.2.0
 *	
 */
@SuppressWarnings("serial")
public class SettleLessonException extends Exception {
	
	private Integer lessonId;

	public SettleLessonException(Integer lessonId, String message) {
		super(lessonId + "  不能结算： " + message );
		this.lessonId = lessonId;
	}

	public SettleLessonException(Integer lessonId, Throwable cause) {
		super(lessonId + " 不能结算：" + cause.getLocalizedMessage(), cause);
		this.lessonId = lessonId;
	}
	
	
}
