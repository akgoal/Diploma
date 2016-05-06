package com.librarybooks.client.objects;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CallResult implements Serializable {

	private String text;
	private String autor;
	private String nameBook;
	private String img;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getNameBook() {
		return nameBook;
	}

	public void setNameBook(String nameBook) {
		this.nameBook = nameBook;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
}
