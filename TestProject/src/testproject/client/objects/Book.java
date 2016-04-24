package testproject.client.objects;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Book implements Serializable {

	private String text;
	private String autor;
	private String title;
	private String genre;
	private String img;
	private long id_autor;
	private long id_genre;
	private long id_book;

	// новый
	public Book(long id_book, String autor, long id_autor, String title, String genre, long id_genre, String img) {
		this.id_book = id_book;
		this.autor = autor;
		this.id_autor = id_autor;
		this.title = title;
		this.genre = genre;
		this.id_genre = id_genre;
		this.img = img;
	}

	public void setBook(long id_book, String autor, long id_autor, String title, String genre, long id_genre, String img) {
		this.id_book = id_book;
		this.autor = autor;
		this.id_autor = id_autor;
		this.title = title;
		this.genre = genre;
		this.id_genre = id_genre;
		this.img = img;
	}

	// этот конструктор заменить новым
	public Book(String autor, String title, String genre, String img) {
		this.autor = autor;
		this.title = title;
		this.genre = genre;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public long getIdAutor() {
		return id_autor;
	}

	public void setIdAutor(long id_autor) {
		this.id_autor = id_autor;
	}

	public long getIdGenre() {
		return id_genre;
	}

	public void setIdGenre(long id_genre) {
		this.id_genre = id_genre;
	}

	public long getIdBook() {
		return id_book;
	}

	public void setIdBook(long id_book) {
		this.id_book = id_book;

	}
}