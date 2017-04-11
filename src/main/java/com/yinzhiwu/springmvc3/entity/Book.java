package com.yinzhiwu.springmvc3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Book {

	@Id
	private long id;
	
	@Column(length=20)
	private String isbn;
	
	@Column(length=50)
	private String title;
	
	@ManyToOne
	private Category category;
	
	@Column(length=50)
	private String author;
	
	@Column
	private float price;
	
	
	
	public Book(){}

	public Book(long id, String isbn, String title, Category category, String author, float price) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.category = category;
		this.author = author;
		this.price = price;
	}

	public final long getId() {
		return id;
	}

	public final void setId(long id) {
		this.id = id;
	}

	public final String getIsbn() {
		return isbn;
	}

	public final void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public final String getTitle() {
		return title;
	}

	public final void setTitle(String title) {
		this.title = title;
	}


	public final Category getCategory() {
		return category;
	}

	public final void setCategory(Category category) {
		this.category = category;
	}

	public final float getPrice() {
		return price;
	}

	public final void setPrice(float price) {
		this.price = price;
	}

	public final String getAuthor() {
		return author;
	}

	public final void setAuthor(String author) {
		this.author = author;
	}
	
	
}
