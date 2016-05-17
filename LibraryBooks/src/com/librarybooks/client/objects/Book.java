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

	// новый
	public Book(long id_book, ArrayList<Author> author, String title, ArrayList<Genre> genre,
			String img) {
		this.id_book = id_book;
		this.author = author;
		this.title = title;
		this.genre = genre;
		this.img = img;
	}

	public void setBook(long id_book, ArrayList<Author> author, String title,
			ArrayList<Genre> genre, String img) {
		this.id_book = id_book;
		this.author = author;
		this.title = title;
		this.genre = genre;
		this.img = img;
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

	public Book(Book book) {
		this.id_book = book.getIdBook();
		this.author = book.getAuthor();
		this.title = book.getTitle();
		this.genre = book.getGenre();
		this.img = book.getImg();
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