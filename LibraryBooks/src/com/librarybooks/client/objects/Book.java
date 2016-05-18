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
	// private int year_create;
	// private String publish;
	// private int year_publish;
	// private String isbn;
	// private int col_pages;
	// private String cover;
	// private String specific;

	// новый
	public Book(long id_book, ArrayList<Author> author, String title, ArrayList<Genre> genre,
			String img) {
		this.id_book = id_book;
		this.author = author;
		this.title = title;
		this.genre = genre;
		this.img = img;
		this.id_book = id_book;
	}

	public Book(long id_book, ArrayList<Author> author, String title, ArrayList<Genre> genre,
			String img, int year_create, String publish, int year_publish, String isbn,
			int col_pages, String cover, String specific) {
		this.id_book = id_book;
		this.author = author;
		this.title = title;
		this.genre = genre;
		this.img = img;
		this.id_book = id_book;
		// this.year_create = year_create;
		// this.publish = publish;
		// this.year_publish = year_publish;
		// this.isbn = isbn;
		// this.col_pages = col_pages;
		// this.cover = cover;
		// this.specific = specific;
	}

	public void setBook(long id_book, ArrayList<Author> author, String title,
			ArrayList<Genre> genre, String img) {
		this.id_book = id_book;
		this.author = author;
		this.title = title;
		this.genre = genre;
		this.img = img;
		this.id_book = id_book;
	}

	public void setBook(long id_book, ArrayList<Author> author, String title,
			ArrayList<Genre> genre, String img, int year_create, String publish, int year_publish,
			String isbn, int col_pages, String cover, String specific) {
		this.id_book = id_book;
		this.author = author;
		this.title = title;
		this.genre = genre;
		this.img = img;
		this.id_book = id_book;
		// this.year_create = year_create;
		// this.publish = publish;
		// this.year_publish = year_publish;
		// this.isbn = isbn;
		// this.col_pages = col_pages;
		// this.cover = cover;
		// this.specific = specific;
	}

	// этот конструктор заменить новым
	public Book(ArrayList<Author> author, String title, ArrayList<Genre> genre, String img) {
		this.author = author;
		this.title = title;
		this.genre = genre;
		this.img = img;
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

}