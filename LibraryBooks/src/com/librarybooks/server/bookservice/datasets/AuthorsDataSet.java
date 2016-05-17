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
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;

@Entity
@Table(name = "authors")
public class AuthorsDataSet {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Field
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "name_original")
	private String originalName;

	@Column(name = "description")
	@Lob
	@Type(type="org.hibernate.type.StringType")
	private String description;

	@Column(name = "image_name")
	private String imageName;

	@ContainedIn
	@ManyToMany(mappedBy = "authors")
	private Set<BooksDataSet> books = new HashSet<>();

	/* Constructors */
	public AuthorsDataSet() {
	}
	
	public AuthorsDataSet(long id, String name, String originalName, String description, String imageName,
			Set<BooksDataSet> books) {
		this.id = id;
		this.name = name;
		this.originalName = originalName;
		this.description = description;
		this.imageName = imageName;
		this.books = books;
	}
	
	public AuthorsDataSet(String name, String originalName, String description, String imageName,
			Set<BooksDataSet> books) {
		this.id = -1;
		this.name = name;
		this.originalName = originalName;
		this.description = description;
		this.imageName = imageName;
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

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Set<BooksDataSet> getBooks() {
		return books;
	}

	public void setBooks(Set<BooksDataSet> books) {
		this.books = books;
	}
	
}
