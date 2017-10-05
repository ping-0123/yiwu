package com.yinzhiwu.yiwu.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="yiwu_region")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Region implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5555408900498082205L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private Level level;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="upid", foreignKey=@ForeignKey(name="fk_region_up_id", value=ConstraintMode.NO_CONSTRAINT))
	private Region up;
	
	@OneToMany(mappedBy="up")
	private List<Region> subRegions = new ArrayList<Region>();
	
	
	
	
	public static enum Level{
		NATION,
		PROVINCE,
		CITY,
		DISTRICT,
		TOWN
	}




	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}




	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}




	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}




	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}




	/**
	 * @return the level
	 */
	public Level getLevel() {
		return level;
	}




	/**
	 * @param level the level to set
	 */
	public void setLevel(Level level) {
		this.level = level;
	}




	/**
	 * @return the up
	 */
	public Region getUp() {
		return up;
	}




	/**
	 * @param up the up to set
	 */
	public void setUp(Region up) {
		this.up = up;
	}




	/**
	 * @return the subRegions
	 */
	public List<Region> getSubRegions() {
		return subRegions;
	}




	/**
	 * @param subRegions the subRegions to set
	 */
	public void setSubRegions(List<Region> subRegions) {
		this.subRegions = subRegions;
	}
}
