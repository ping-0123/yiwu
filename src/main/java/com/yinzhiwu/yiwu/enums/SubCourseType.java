package com.yinzhiwu.yiwu.enums;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.util.StringUtils;

/**
* @author 作者 ping
* @Date 创建时间：2017年11月1日 上午12:10:20
*
*/

public enum SubCourseType {
	
	OPEN_A(1,"开放式A", CourseType.OPENED),
	OPEN_B(2,"开放式B", CourseType.OPENED),
	OPEN_CJ(5,"少儿集训初级",CourseType.OPENED),
	OPEN_ZJ(6,"少儿集训中级",CourseType.OPENED),
	CLOSED(3,"封闭式", CourseType.CLOSED),
	PRIVATE(4,"私教课", CourseType.PRIVATE);
	
	private final int id;
	private final String name;
	private final CourseType supperType;
	
	private SubCourseType(int id,String name ,CourseType type){
		this.id = id;
		this.name= name;
		this.supperType= type;
	}

	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	public CourseType getSupperType() {
		return supperType;
	}
	
	public static SubCourseType fromId(Integer id){
		switch (id) {
		case 1:
			return SubCourseType.OPEN_A;
		case 2:
			return SubCourseType.OPEN_B;
		case 3:
			return SubCourseType.CLOSED;
		case 4:
			return SubCourseType.PRIVATE;
		case 5:
			return SubCourseType.OPEN_CJ;
		case 6:
			return SubCourseType.OPEN_ZJ;
		default:
			throw new UnsupportedOperationException(id + "is not supported from enum SubCourseType");
		}
	}
	
	public static SubCourseType fromName(String name){
		switch (name) {
		case "封闭式":
			return SubCourseType.CLOSED;
		case "开放式A":
			return SubCourseType.OPEN_A;
		case "开放式B":
			return SubCourseType.OPEN_B;
		case "私教课":
			return SubCourseType.PRIVATE;
		case "少儿集训初级":
			return SubCourseType.OPEN_CJ;
		case "少儿集训中级":
			return SubCourseType.OPEN_ZJ;
		default:
			throw new UnsupportedOperationException(
					name + "is not supported from enum SubCourseType");
		}
	}
	
	public static List<SubCourseType> fromCourseType(CourseType type){
		List<SubCourseType> subs = new ArrayList<>();
		for (SubCourseType sub : SubCourseType.values()) {
			if(sub.getSupperType() == type)
				subs.add(sub);
		}
		return subs;
	}


	@Converter
	public static class SubCourseTypeConverter implements AttributeConverter<SubCourseType, String>{
	
		@Override
		public String convertToDatabaseColumn(SubCourseType attribute) {
			if(attribute == null)
				return null;
			return attribute.getName();
		}
	
		@Override
		public SubCourseType convertToEntityAttribute(String dbData) {
			if(!StringUtils.hasLength(dbData))
				return null;
			return SubCourseType.fromName(dbData);
		}
	}
		

	@Converter
	public static class SubCourseTypeIntegerConverter implements AttributeConverter<SubCourseType, Integer>{
	
		@Override
		public Integer convertToDatabaseColumn(SubCourseType attribute) {
			if(attribute == null)
				return null;
			return attribute.getId();
		}
	
		@Override
		public SubCourseType convertToEntityAttribute(Integer dbData) {
			if(dbData == null)
				return null;
			return SubCourseType.fromId(dbData);
		}
		
	}
}