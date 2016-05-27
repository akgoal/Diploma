package com.librarybooks.client.objects;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class BookEdit implements Serializable {

	private String title;
	private String title_original;
	private String author;
	private String genre;
	private String img;
	private String price;
	private String year_create;
	private String publish;
	private String year_publish;
	private String isbn;
	private String col_pages;
	private String description;
	private String specific;
	private Date addition_date;

	public BookEdit(String title, String title_original, String author, String genre, String img,
			String year_create, String publish, String year_publish, String isbn, String col_pages,
			String description, String specific, Date addition_date) {

		this.title = (title.isEmpty()) ? null : title;
		this.title_original = (title_original.isEmpty()) ? null : title_original;
		this.author = (author.isEmpty()) ? null : author;
		this.genre = (genre.isEmpty()) ? null : genre;
		this.img = (img.isEmpty()) ? null : img;
		this.year_create = (year_create.isEmpty()) ? null : year_create;
		this.publish = (publish.isEmpty()) ? null : publish;
		this.year_publish = (year_publish.isEmpty()) ? null : year_publish;
		this.isbn = (isbn.isEmpty()) ? null : isbn;
		this.col_pages = (col_pages.isEmpty()) ? null : col_pages;
		this.description = (description.isEmpty()) ? null : description;
		this.specific = (specific.isEmpty()) ? null : specific;
		this.addition_date = addition_date;
		this.price = price;
	}

	// new
	public BookEdit(String title, String title_original, String author, String genre, String img,
			String year_create, String publish, String year_publish, String isbn, String col_pages,
			String description, String specific, Date addition_date, String price) {

		this.title = (title.isEmpty()) ? null : title;
		this.title_original = (title_original.isEmpty()) ? null : title_original;
		this.author = (author.isEmpty()) ? null : author;
		this.genre = (genre.isEmpty()) ? null : genre;
		this.img = (img.isEmpty()) ? null : img;
		this.year_create = (year_create.isEmpty()) ? null : year_create;
		this.publish = (publish.isEmpty()) ? null : publish;
		this.year_publish = (year_publish.isEmpty()) ? null : year_publish;
		this.isbn = (isbn.isEmpty()) ? null : isbn;
		this.col_pages = (col_pages.isEmpty()) ? null : col_pages;
		this.description = (description.isEmpty()) ? null : description;
		this.specific = (specific.isEmpty()) ? null : specific;
		this.addition_date = addition_date;
		this.price = price;
	}

	public BookEdit() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle_original() {
		return title_original;
	}

	public void setTitle_original(String title_original) {
		this.title_original = title_original;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getYear_create() {
		return year_create;
	}

	public void setYear_create(String year_create) {
		this.year_create = year_create;
	}

	public String getPublish() {
		return publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}

	public String getYear_publish() {
		return year_publish;
	}

	public void setYear_publish(String year_publish) {
		this.year_publish = year_publish;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn.trim();
	}

	public String getCol_pages() {
		return col_pages;
	}

	public void setCol_pages(String col_pages) {
		this.col_pages = col_pages;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSpecific() {
		return specific;
	}

	public void setSpecific(String specific) {
		this.specific = specific;
	}

	public Date getAddition_date() {
		return addition_date;
	}

	public void setAddition_date(Date addition_date) {
		this.addition_date = addition_date;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
