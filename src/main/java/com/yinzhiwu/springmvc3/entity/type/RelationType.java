package com.yinzhiwu.springmvc3.entity.type;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("RelationType")
public class RelationType extends BaseType   {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1355546539849095987L;
	
	public static final RelationType SELF_WITH_SELF = new RelationType("SELF_WITH_SELF");
	
	public static final RelationType SELF_WITH_SUPERIOR = new RelationType("SELF_WITH_SUPERIOR");
	
	public static final RelationType SELF_WITH_GRAND = new RelationType("SELF_WITH_GRAND");
	
	public RelationType() {
	}
	
	public RelationType(String name) {
		super(name);
	}

	public boolean equals(RelationType anohter){
		return this.getName().equals(anohter.getName())?true:false;
	}

}
