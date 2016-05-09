package com.librarybooks.client.objects;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Genre implements Serializable {

	private String genre;
	private long id_genre;
	private long col_book;

	// новый
	public Genre(String genre, long id_genre) {
		this.genre = genre;
		this.id_genre = id_genre;
	}

	public Genre(String genre, long id_genre, long col_book) {
		this.genre = genre;
		this.id_genre = id_genre;
		this.col_book = col_book;
	}

	public void setGenre(String genre, long id_genre, long col_book) {
		this.genre = genre;
		this.id_genre = id_genre;
		this.col_book = col_book;
	}

	public void setGenre(String genre, long id_genre) {
		this.genre = genre;
		this.id_genre = id_genre;

	}

	public Genre() {
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public long getIdGenre() {
		return id_genre;
	}

	public void setIdGenre(long id_genre) {
		this.id_genre = id_genre;
	}

	public long getColBook() {
		return col_book;
	}

	public void setColBook(long col_book) {
		this.col_book = col_book;
	}

}
