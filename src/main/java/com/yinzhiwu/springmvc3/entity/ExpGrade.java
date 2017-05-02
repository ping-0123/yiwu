package com.yinzhiwu.springmvc3.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(uniqueConstraints={@UniqueConstraint(name="uk_expgrade_gradeNo", columnNames="gradeNo"),
		@UniqueConstraint(name="uk_expgrade_name", columnNames="name")
})
public class ExpGrade extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6432236461203601711L;

	
	@Column(unique=true, nullable=false)  //uk
	private int gradeNo;
	
	@Column(length=32, nullable=false)  //uk
	private String name;
	
	private float upgradeExp;
	
	@OneToOne
	@JoinColumn(name="next_grade_id", foreignKey=@ForeignKey(name="fk_expGrade_nextGrade_id"))
	private ExpGrade nextGrade;

	private float increaseDiscont;
	
	private Boolean lowestGrade;
	
	private Boolean highesGrade;
	
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


	public int getGradeNo() {
		return gradeNo;
	}


	public Boolean getLowestGrade() {
		return lowestGrade;
	}


	public Boolean getHighesGrade() {
		return highesGrade;
	}


	public void setGradeNo(int gradeNo) {
		this.gradeNo = gradeNo;
	}


	public void setLowestGrade(Boolean lowestGrade) {
		this.lowestGrade = lowestGrade;
	}


	public void setHighesGrade(Boolean highesGrade) {
		this.highesGrade = highesGrade;
	}



	public ExpGrade(int gradeNo, String name, float upgradeExp, ExpGrade nextGrade, float increaseDiscont) {
		super();
		this.gradeNo = gradeNo;
		this.name = name;
		this.upgradeExp = upgradeExp;
		this.nextGrade = nextGrade;
		this.increaseDiscont = increaseDiscont;
	}


	
	
}
