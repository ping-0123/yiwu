package com.yinzhiwu.springmvc3.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table
public class ExpGrade extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6432236461203601711L;

	private String name;
	
	private float upgradeExp;
	
	@OneToOne
	@JoinColumn(name="next_grade_id")
	private ExpGrade nextGrade;

	private float increaseDiscont;
	
	@OneToMany(mappedBy="expGrade")
	private List<Distributer> owners = new ArrayList<>();
	
	public ExpGrade() {
		super();
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getUpgradeExp() {
		return upgradeExp;
	}

	public void setUpgradeExp(float upgradeExp) {
		this.upgradeExp = upgradeExp;
	}

	public ExpGrade getNextGrade() {
		return nextGrade;
	}

	public void setNextGrade(ExpGrade nextGrade) {
		this.nextGrade = nextGrade;
	}

	public float getIncreaseDiscont() {
		return increaseDiscont;
	}

	public void setIncreaseDiscont(float increaseDiscont) {
		this.increaseDiscont = increaseDiscont;
	}


	public List<Distributer> getOwners() {
		return owners;
	}


	public void setOwners(List<Distributer> owners) {
		this.owners = owners;
	}


	
	
}
