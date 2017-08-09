package com.yinzhiwu.yiwu.entity.type;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.yinzhiwu.yiwu.entity.Distributer;

@Entity
@DiscriminatorValue("RelationType")
public class RelationType extends BaseType {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1355546539849095987L;

	public static final RelationType SELF_WITH_SELF = new RelationType(10015, "SELF_WITH_SELF");

	public static final RelationType SELF_WITH_SUPERIOR = new RelationType(10016, "SELF_WITH_SUPERIOR");

	public static final RelationType SELF_WITH_GRAND = new RelationType(10017, "SELF_WITH_GRAND");

	public RelationType() {
	}

	public RelationType(String name) {
		super(name);
	}

	public RelationType(int i, String string) {
		super(i, string);
	}

	public Distributer getRelativeDistributer(Distributer d) {
		try {
			switch ((int) this.getId()) {
			case 10015:
				return d;
			case 10016:
				return d.getSuperDistributer();
			case 10017:
				return d.getSuperDistributer().getSuperDistributer();
			default:
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public boolean equals(RelationType anohter) {
		return this.getName().equals(anohter.getName()) ? true : false;
	}

}
