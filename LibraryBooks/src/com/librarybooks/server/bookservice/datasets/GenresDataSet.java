package com.librarybooks.server.bookservice.datasets;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="genres")
public class GenresDataSet {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description")
	@Lob
	@Type(type="org.hibernate.type.StringClobType")
	private String description;

	@ManyToMany(mappedBy = "genres")
	private Set<BooksDataSet> books = new HashSet<>();
	
	/* Constructors */
	public GenresDataSet(){
	}
	
	public GenresDataSet(long id, String name, String description, Set<BooksDataSet> books) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.books = books;
	}
	
	public GenresDataSet(String name, String description, Set<BooksDataSet> books) {
		this.id = -1;
		this.name = name;
		this.description = description;
		this.books = books;
	}

	/* Setters and Getters */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<BooksDataSet> getBooks() {
		return books;
	}

	public void setBooks(Set<BooksDataSet> books) {
		this.books = books;
	}

}
