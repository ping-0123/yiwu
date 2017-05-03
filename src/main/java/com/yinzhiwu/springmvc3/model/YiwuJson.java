package com.yinzhiwu.springmvc3.model;

public class YiwuJson<T> {
	
	private int returnCode;
	
	private boolean secure;
	
	private String msg;
	
	private T data;
	
	private boolean result;
	
	
	public YiwuJson(){
		this.returnCode = 200;
		this.secure = false;
		this.msg = "success";
		this.result = true;
	};
	
	public YiwuJson(T data){
		this.returnCode = 200;
		this.secure = false;
		this.msg = "success";
		this.data = data;
		this.result = true;
	}

	
	
	public YiwuJson(int returnCode, boolean secure, String msg, T data, boolean result) {
		this.returnCode = returnCode;
		this.secure = secure;
		this.msg = msg;
		this.data = data;
		this.result = result;
	}

	public final T getData() {
		return data;
	}

	public final void setData(T data) {
		this.data = data;
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

	
	
}
