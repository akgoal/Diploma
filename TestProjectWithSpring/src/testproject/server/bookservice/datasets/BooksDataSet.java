package testproject.server.bookservice.datasets;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Dmitry on 17.04.2016.
 */

@Entity
@Table(name="books")
public class BooksDataSet{
	
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="title")
    private String title;
    
    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name="author_id")
    private AuthorsDataSet author;

    @ManyToOne
    @JoinColumn(name="genre_id")
    private GenresDataSet genre;
    
    @Column(name="img_url")
    private String imgUrl;
    
    public BooksDataSet() {
    }

    public BooksDataSet(long id, String title, String description, AuthorsDataSet author, GenresDataSet genre, String imgUrl) {
        this.setId(id);
        this.setTitle(title);
        this.setAuthor(author);
        this.setDescription(description);
        this.setImgUrl(imgUrl);
        this.setGenre(genre);
    }

    public BooksDataSet(String title, String description, AuthorsDataSet author, GenresDataSet genre, String imgUrl) {
        this.setId(-1);
        this.setTitle(title);
        this.setAuthor(author);
        this.setDescription(description);
        this.setImgUrl(imgUrl);
        this.setGenre(genre);
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AuthorsDataSet getAuthor() {
		return author;
	}

	public void setAuthor(AuthorsDataSet author) {
		this.author = author;
	}

	public GenresDataSet getGenre() {
		return genre;
	}

	public void setGenre(GenresDataSet genre) {
		this.genre = genre;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

    
}
