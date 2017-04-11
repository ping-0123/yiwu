package com.yinzhiwu.springmvc3.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table
public class Category {

	@Id
	private int id;
	
	@NaturalId
	@Column(name="name", length=20, unique=true)
	private String name;
	
	@OneToMany(mappedBy="category", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Book> books = new HashSet<>();
	
	public Category(){}
	
	public Category(int id ,String name){
		this.id  =id;
		this.name = name;
	}

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}
	
	
}
