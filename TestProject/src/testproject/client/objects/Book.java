package testproject.client.objects;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Book implements Serializable {

	private String text;
	private String author;
	private String title;
	private String genre;
	private String img;
	private long id_author;
	private long id_genre;
	private long id_book;

	// новый
	public Book(long id_book, String author, long id_author, String title, String genre, long id_genre, String img) {
		this.id_book = id_book;
		this.author = author;
		this.id_author = id_author;
		this.title = title;
		this.genre = genre;
		this.id_genre = id_genre;
		this.img = img;
	}

	public void setBook(long id_book, String author, long id_author, String title, String genre, long id_genre, String img) {
		this.id_book = id_book;
		this.author = author;
		this.id_author = id_author;
		this.title = title;
		this.genre = genre;
		this.id_genre = id_genre;
		this.img = img;
	}

	// этот конструктор заменить новым
	public Book(String author, String title, String genre, String img) {
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

	public long getIdAuthor() {
		return id_author;
	}

	public void setIdAuthor(long id_autor) {
		this.id_author = id_autor;
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