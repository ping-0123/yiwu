package com.yinzhiwu.yiwu.model;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

//@JsonSerialize(include =  JsonSerialize.Inclusion.NON_NULL)
@JsonInclude(value= Include.NON_NULL)
//保证序列化json的时候,如果是null的对象,key也会消失
public class YiwuJson<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7000760666908532411L;
	private int 	returnCode;
	private boolean secure = false;
	private String	msg;
	private T 		data;
	private boolean result;

	public YiwuJson() {
		this.returnCode = 200;
		this.secure = false;
		this.msg = "success";
		this.result = true;
	};

	public YiwuJson(T data) {
		this.returnCode = 200;
		this.secure = false;
		this.msg = "success";
		this.data = data;
		this.result = true;
	}

	public YiwuJson(String msg, T data) {
		this.returnCode = 200;
		this.secure = false;
		if (StringUtils.hasLength(msg))
			this.msg = msg;
		else
			this.msg = "success";
		this.data = data;
		this.result = true;
	}

	public YiwuJson(String msg) {
		this.returnCode = 500;
		this.secure = false;
		this.msg = msg;
		this.result = false;
	}

	public YiwuJson(int returnCode, boolean secure, String msg, T data, boolean result) {
		this.returnCode = returnCode;
		this.secure = secure;
		this.msg = msg;
		this.data = data;
		this.result = result;
	}
	private YiwuJson(int returnCode){
		this.returnCode = returnCode;
		this.secure = false;
		this.result = this.isSuccess();
	}
	private YiwuJson(int returnCode, String msg){
		this.returnCode = returnCode;
		this.secure = false;
		this.msg = msg;
		this.result = this.isSuccess();
	}
	private YiwuJson(int returnCode, T data){
		this.returnCode = returnCode;
		this.secure = false;
		this.data = data;
		this.result = this.isSuccess();
	}
	
	private YiwuJson(int returnCode, String message, T data){
		this.returnCode = returnCode;
		this.secure = false;
		this.msg = message;
		this.data = data;
		this.result = this.isSuccess();
	}
	
	public static <T> YiwuJson<T> createBySuccess(){
        return new YiwuJson<T>(ReturnCode.SUCCESS.getCode());
    }

    public static <T> YiwuJson<T> createBySuccessMessage(String msg){
        return new YiwuJson<T>(ReturnCode.SUCCESS.getCode(),msg);
    }

    public static <T> YiwuJson<T> createBySuccess(T data){
        return new YiwuJson<T>(ReturnCode.SUCCESS.getCode(),data);
    }

    public static <T> YiwuJson<T> createBySuccess(String msg,T data){
        return new YiwuJson<T>(ReturnCode.SUCCESS.getCode(),msg,data);
    }


	public static <T> YiwuJson<T> createByError(){
        return new YiwuJson<T>(ReturnCode.ERROR.getCode(),ReturnCode.ERROR.getDesc());
    }


    public static <T> YiwuJson<T> createByErrorMessage(String errorMessage){
        return new YiwuJson<T>(ReturnCode.ERROR.getCode(),errorMessage);
    }

    public static <T> YiwuJson<T> createByErrorCodeMessage(int errorCode,String errorMessage){
        return new YiwuJson<T>(errorCode,errorMessage);
    }

	@JsonIgnore
    public boolean isSuccess(){
        return this.returnCode == ReturnCode.SUCCESS.getCode();
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

	private enum ReturnCode{
		SUCCESS(0, "success"),
		ERROR(1001, "error"),
		NEED_LOGIN(1002, "need login"),
		ILLEGAL_ARGUMENT(1003,"illegal argument"),
		NO_PERMISSION(1004, "do not permmited");
		
		private final int code;
		private final String desc;
		
		public int getCode() {
			return code;
		}
		public String getDesc() {
			return desc;
		}
		
		private ReturnCode(int code, String desc) {
			this.code = code;
			this.desc = desc;
		}

		
		
	}
}
