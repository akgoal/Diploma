package testproject.client.objects;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Book implements Serializable {

	private String text;
	private String autor;
	private String nameBook;
	private String type;
	private String img;

	public Book(String autor, String nameBook, String type, String img) {
		this.autor = autor;
		this.nameBook = nameBook;
		this.type = type;
		this.img = img;
	}

	public void setBook(String autor, String nameBook, String type, String img) {
		this.autor = autor;
		this.nameBook = nameBook;
		this.type = type;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}