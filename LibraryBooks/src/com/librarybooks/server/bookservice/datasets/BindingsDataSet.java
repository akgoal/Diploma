package com.librarybooks.server.bookservice.datasets;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="bindings")
public class BindingsDataSet {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description")
	@Lob
	@Type(type="org.hibernate.type.StringType")
	private String description;

	/* Constructors */
	public BindingsDataSet(){
	}
	
	public BindingsDataSet(long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public BindingsDataSet(String name, String description) {
		this.id = -1;
		this.name = name;
		this.description = description;
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
	

}
