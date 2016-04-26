package testproject.server.bookservice.datasets;

import javax.persistence.*;

import testproject.server.bookservice.dao.BooksDAO;

import java.io.Serializable;

/**
 * Created by Dmitry on 17.04.2016.
 */

@Entity
@Table(name="books")
public class BooksDataSet implements Serializable{
    private static final long serialVersionBID = -8706689714326132798L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="title")
    private String title;

    @Column(name="author")
    private String author;

    public BooksDataSet() {
    }

    public BooksDataSet(long id, String title, String author) {
        this.setId(id);
        this.setTitle(title);
        this.setAuthor(author);
    }

    public BooksDataSet(String title, String author) {
        this.setId(-1);
        this.setTitle(title);
        this.setAuthor(author);
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
