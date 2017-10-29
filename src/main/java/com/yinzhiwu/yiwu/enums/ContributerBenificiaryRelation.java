package com.yinzhiwu.yiwu.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

public enum ContributerBenificiaryRelation {
	
	SELF_WITH_SELF(10015),
	SELF_WITH_SUPERIOR(10016),
	SELF_WITH_GRAND(10017);
	
	private final int id;

	private ContributerBenificiaryRelation(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	public static ContributerBenificiaryRelation fromId(Integer id){
		switch (id) {
		case 10015:
			return SELF_WITH_SELF;
		case 10016:
			return SELF_WITH_SUPERIOR;
		case 10017:
			return SELF_WITH_GRAND;
		default:
			throw new UnsupportedOperationException(id + " not supported for enum ContributerBenificiaryRelation");
		}
	}
	
	@Converter
	public static class ContributerBenificiaryRelationConverter implements AttributeConverter<ContributerBenificiaryRelation, Integer>{

		@Override
		public Integer convertToDatabaseColumn(ContributerBenificiaryRelation relation) {
			return relation==null?null:relation.getId();
		}

		@Override
		public ContributerBenificiaryRelation convertToEntityAttribute(Integer dbData) {
			return (null == dbData)?ContributerBenificiaryRelation.fromId(dbData):null;
		}
		
	}
	
}
