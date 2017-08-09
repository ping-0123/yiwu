package com.yinzhiwu.yiwu.enums;

public enum Gender {
	MALE("男"),
	FEMALE("女");
	
	private final String cnGender;
	private Gender(String gender){
		this.cnGender= gender;
	}
	public String getCnGender() {
		return cnGender;
	}
	
	public static Gender fromCnGender(String cnGender){
		switch (cnGender) {
		case "男":
			return Gender.MALE;
		case "女":
			return Gender.FEMALE;
		default:
			throw new UnsupportedOperationException(cnGender + "is not supported");
		}
	} 
}
