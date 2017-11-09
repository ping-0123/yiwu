package com.yinzhiwu.yiwu.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

public enum Relation {
	
	SELF_WITH_SELF(10015),
	SELF_WITH_SUPERIOR(10016),
	SELF_WITH_GRAND(10017);
	
	private final int id;

	private Relation(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	public static Relation fromId(Integer id){
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
	public static class ContributerBenificiaryRelationConverter implements AttributeConverter<Relation, Integer>{

		@Override
		public Integer convertToDatabaseColumn(Relation relation) {
			return relation==null?null:relation.getId();
		}

		@Override
		public Relation convertToEntityAttribute(Integer dbData) {
			return (null == dbData)?Relation.fromId(dbData):null;
		}
		
	}
	
}
