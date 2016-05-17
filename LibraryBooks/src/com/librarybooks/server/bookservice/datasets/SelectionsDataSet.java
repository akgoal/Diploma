package com.librarybooks.server.bookservice.datasets;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="selections")
public class SelectionsDataSet {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "name_original")
	private String originalName;

	@Column(name = "addition_date")
	private Date additionDate;
	
	@Column(name = "description")
	@Lob
	@Type(type="org.hibernate.type.StringType")
	private String description;

	@Column(name = "image_name")
	private String imageName;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "selection_book", joinColumns = @JoinColumn(name = "selection_id") ,
		inverseJoinColumns = @JoinColumn(name = "book_id"))
	private Set<BooksDataSet> books = new HashSet<>();

	/* Constructors */
	public SelectionsDataSet(){
	}
	
	public SelectionsDataSet(long id, String name, String originalName, Date additionDate, String description,
			String imageName, Set<BooksDataSet> books) {
		this.id = id;
		this.name = name;
		this.originalName = originalName;
		this.additionDate = additionDate;
		this.description = description;
		this.imageName = imageName;
		this.books = books;
	}
	
	public SelectionsDataSet(String name, String originalName, Date additionDate, String description,
			String imageName, Set<BooksDataSet> books) {
		this.id = -1;
		this.name = name;
		this.originalName = originalName;
		this.additionDate = additionDate;
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

	public Date getAdditionDate() {
		return additionDate;
	}

	public void setAdditionDate(Date additionDate) {
		this.additionDate = additionDate;
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
