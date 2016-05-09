package com.librarybooks.client.objects;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Author implements Serializable {

	private String author;
	private long id_author;
	private long col_book;

	// новый
	public Author(String author, long id_author) {
		this.author = author;
		this.id_author = id_author;
	}

	public Author(String author, long id_author, long col_book) {
		this.author = author;
		this.id_author = id_author;
		this.col_book = col_book;
	}

	public void setAuthor(String author, long id_author, long col_book) {
		this.author = author;
		this.id_author = id_author;
		this.col_book = col_book;
	}

	public void setGenre(String author, long id_author) {
		this.author = author;
		this.id_author = id_author;

	}

	public Author() {
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public long getIdAuthor() {
		return id_author;
	}

	public void setIdAuthor(long id_author) {
		this.id_author = id_author;
	}

	public long getColBook() {
		return col_book;
	}

	public void setColBook(long col_book) {
		this.col_book = col_book;
	}

}
