package testproject.server.bookservice.dao;

import java.util.ArrayList;

import testproject.server.bookservice.datasets.*;

/**
 * Created by Dmitry on 17.04.2016.
 */
public interface BooksDAOI {
    ArrayList<BooksDataSet> findBooksByTitle(String title);
    ArrayList<BooksDataSet> getAllBooks();
    
    void addBook(BooksDataSet book);
    void addAuthor(AuthorsDataSet author);
    void addGenre(GenresDataSet genre);
    
    ArrayList<BooksDataSet> getBooksByAuthorId(long authorId);
    ArrayList<BooksDataSet> getBooksByGenreId(long genreId);
    
    AuthorsDataSet getAuthorById(long authorId);
    GenresDataSet getGenreById(long genreId);
}
