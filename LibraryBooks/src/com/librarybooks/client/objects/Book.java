package com.librarybooks.client.objects;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Book implements Serializable {

	private String text;
	private String title;
	ArrayList<Author> author;
	private ArrayList<Genre> genre;
	private String img;
	private long id_book;
	private String year_create;
	private String publish;
	private String year_publish;
	private String isbn;
	private String col_pages;
	private String cover;
	private String specific;

	public Book(long id_book, ArrayList<Author> author, String title, ArrayList<Genre> genre,
			String img) {
		this.id_book = id_book;
		this.author = author;
		this.title = title;
		this.genre = genre;
		this.img = img;
		this.id_book = id_book;
		this.year_create = null;
		this.publish = null;
		this.year_publish = null;
		this.isbn = null;
		this.col_pages = null;
		this.cover = null;
		this.specific = null;
	}

	public void setBook(long id_book, ArrayList<Author> author, String title,
			ArrayList<Genre> genre, String img) {
		this.id_book = id_book;
		this.author = author;
		this.title = title;
		this.genre = genre;
		this.img = img;
		this.id_book = id_book;
		this.year_create = null;
		this.publish = null;
		this.year_publish = null;
		this.isbn = null;
		this.col_pages = null;
		this.cover = null;
		this.specific = null;
	}

	public Book() {
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public ArrayList<Author> getAuthor() {
		return author;
	}

	public void setAuthor(ArrayList<Author> author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<Genre> getGenre() {
		return genre;
	}

	public void setGenre(ArrayList<Genre> genre) {
		this.genre = genre;
	}

	public long getIdBook() {
		return id_book;
	}

	public void setIdBook(long id_book) {
		this.id_book = id_book;

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
		this.isbn = isbn;
	}

	public String getCol_pages() {
		return col_pages;
	}

	public void setCol_pages(String col_pages) {
		this.col_pages = col_pages;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getSpecific() {
		return specific;
	}

	public void setSpecific(String specific) {
		this.specific = specific;
	}

}