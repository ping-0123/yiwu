package com.yinzhiwu.yiwu.enums;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


/**
* @author 作者 ping
* @Date 创建时间：2017年11月1日 上午12:08:59
*
*/

public enum CourseType{
	CLOSED("封闭式"),
	OPENED("开放式"),
	PRIVATE("私教课"),
	GRADE_EXAM("考级"),
	REFERENCE_ORDER("以订单为准");
	
	
	private final String name;
	public String getName(){
		return name;
	}
	private CourseType(String name){
		this.name = name;
	}
	
	/**
	 * 
	 * @return 可以用于排课的课程类型
	 */
	public static List<CourseType>  getEffectiveCourseTypes(){
		List<CourseType> types = new ArrayList<>();
		types.add(CourseType.CLOSED);
		types.add(CourseType.OPENED);
		types.add(CourseType.PRIVATE);
		return types;
	}
	
	public  List<SubCourseType> getSubCourseTypes(){
		SubCourseType[] all = SubCourseType.values();
		List<SubCourseType> subs = new ArrayList<>();
		for (int i = 0; i < all.length; i++) {
			if(all[i].getSupperType()==this)
				subs.add(all[i]);
		}
		
		return subs;
	}
	public static CourseType fromName(String name){
		switch (name) {
			case "封闭式":
				return CourseType.CLOSED;
			case "开放式":
			return CourseType.OPENED;
			case "私教课":
				return CourseType.PRIVATE;
			case "考级":
				return CourseType.GRADE_EXAM;
			case "以订单为准":
				return CourseType.REFERENCE_ORDER;
			default:
				throw new UnsupportedOperationException(name + "is not supported for enum CourseType");
		}
	}


	@Converter
	public static class CourseTypeConverter implements AttributeConverter<CourseType, String>{
	
		@Override
		public String convertToDatabaseColumn(CourseType attribute) {
			if(attribute == null 
					|| attribute == CourseType.GRADE_EXAM 
					|| attribute == CourseType.REFERENCE_ORDER)
				return null;
			return attribute.getName();
		}
	
		@Override
		public CourseType convertToEntityAttribute(String dbData) {
			if(dbData == null || "".equals(dbData.trim()))
				return null;
			return CourseType.fromName(dbData);
		}
		
	}
}
