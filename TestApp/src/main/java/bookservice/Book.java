package bookservice;

/**
 * Created by Dmitry on 16.04.2016.
 */
public class Book {
    private long id;
    private String title;
    private String author;

    public Book(String title, String author) {
        this.id = -1;
        this.title = title;
        this.author = author;
    }

    public Book(long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
