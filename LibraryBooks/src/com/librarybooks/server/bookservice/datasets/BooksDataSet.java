package com.librarybooks.server.bookservice.datasets;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

@Entity
@Indexed
@Table(name = "books")
public class BooksDataSet {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "code")
	private String code;

	@Field
	@Column(name = "title", nullable = false)
	private String title;

	@Field
	@Column(name = "title_original")
	private String originalTitle;

	@Column(name = "description")
	@Lob
	@Type(type="org.hibernate.type.StringType")
	private String description;

	@IndexedEmbedded
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id") ,
		inverseJoinColumns = @JoinColumn(name = "author_id"))
	private Set<AuthorsDataSet> authors = new HashSet<>();

	@Column(name = "creation_year")
	private int creationYear;
	
	@IndexedEmbedded
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "book_genre", joinColumns = @JoinColumn(name = "book_id") ,
		inverseJoinColumns = @JoinColumn(name = "genre_id"))
	private Set<GenresDataSet> genres = new HashSet<>();
	
	@Column(name = "image_name")
	private String imageName;

	@ManyToOne
	@JoinColumn(name = "publisher_id")
	private PublishersDataSet publisher;

	@Column(name = "publication_year")
	private int publicationYear;

	@Column(name = "isbn")
	private String isbn;

	@Column(name = "pages")
	private int pages;

	@ManyToOne
	@JoinColumn(name = "binding_id")
	private BindingsDataSet binding;

	@Column(name = "addition_date")
	private Date additionDate;

	@Column(name = "rate")
	private float rate;
	
	@Column(name="price")
	private int price;

	/* Constructors */
	public BooksDataSet() {
	}

	public BooksDataSet(long id, String code, String title, String originalTitle, String description,
			Set<AuthorsDataSet> authors, int creationYear, Set<GenresDataSet> genres, String imageName,
			PublishersDataSet publisher, int publicationYear, String isbn, int pages, BindingsDataSet binding,
			Date additionDate, int rate, int price) {
		this.id = id;
		this.code = code;
		this.title = title;
		this.originalTitle = originalTitle;
		this.description = description;
		this.authors = authors;
		this.creationYear = creationYear;
		this.genres = genres;
		this.imageName = imageName;
		this.publisher = publisher;
		this.publicationYear = publicationYear;
		this.isbn = isbn;
		this.pages = pages;
		this.binding = binding;
		this.additionDate = additionDate;
		this.rate = rate;
		this.price = price;
	}

	public BooksDataSet(String code, String title, String originalTitle, String description,
			Set<AuthorsDataSet> authors, int creationYear, Set<GenresDataSet> genres, String imageName,
			PublishersDataSet publisher, int publicationYear, String isbn, int pages, BindingsDataSet binding,
			Date additionDate, int rate, int price) {
		this.id = -1;
		this.code = code;
		this.title = title;
		this.originalTitle = originalTitle;
		this.description = description;
		this.authors = authors;
		this.creationYear = creationYear;
		this.genres = genres;
		this.imageName = imageName;
		this.publisher = publisher;
		this.publicationYear = publicationYear;
		this.isbn = isbn;
		this.pages = pages;
		this.binding = binding;
		this.additionDate = additionDate;
		this.rate = rate;
		this.price = price;
	}

	/* Setters and Getters */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<AuthorsDataSet> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<AuthorsDataSet> authors) {
		this.authors = authors;
	}

	public int getCreationYear() {
		return creationYear;
	}

	public void setCreationYear(int creationYear) {
		this.creationYear = creationYear;
	}

	public Set<GenresDataSet> getGenres() {
		return genres;
	}

	public void setGenres(Set<GenresDataSet> genres) {
		this.genres = genres;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public PublishersDataSet getPublisher() {
		return publisher;
	}

	public void setPublisher(PublishersDataSet publisher) {
		this.publisher = publisher;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public BindingsDataSet getBinding() {
		return binding;
	}

	public void setBinding(BindingsDataSet binding) {
		this.binding = binding;
	}

	public Date getAdditionDate() {
		return additionDate;
	}

	public void setAdditionDate(Date additionDate) {
		this.additionDate = additionDate;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	
}
