package com.librarybooks.client.objects;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Genre implements Serializable {

	private String genre;
	private long id_genre;

	// новый
	public Genre(String genre, long id_genre) {
		this.genre = genre;
		this.id_genre = id_genre;
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

}
