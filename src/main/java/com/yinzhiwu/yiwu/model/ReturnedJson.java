package com.yinzhiwu.yiwu.model;

public class ReturnedJson {

	private int returnCode;

	private boolean secure;

	private String msg;

	private Object data;

	private boolean result;

	public ReturnedJson() {
	};

	public ReturnedJson(Object data) {
		this.returnCode = 200;
		this.secure = false;
		this.msg = "success";
		this.data = data;
		this.result = true;
	}

	public ReturnedJson(int returnCode, boolean secure, String msg, Object data, boolean result) {
		this.returnCode = returnCode;
		this.secure = secure;
		this.msg = msg;
		this.data = data;
		this.result = result;
	}

	public final boolean isResult() {
		return result;
	}

	public final void setResult(boolean result) {
		this.result = result;
	}

	public final int getReturnCode() {
		return returnCode;
	}

	public final void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	public final boolean isSecure() {
		return secure;
	}

	public final void setSecure(boolean secure) {
		this.secure = secure;
	}

	public final String getMsg() {
		return msg;
	}

	public final void setMsg(String msg) {
		this.msg = msg;
	}

	public final Object getData() {
		return data;
	}

	public final void setData(Object data) {
		this.data = data;
	}

}
