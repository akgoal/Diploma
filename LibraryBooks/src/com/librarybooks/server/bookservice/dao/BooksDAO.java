package com.librarybooks.server.bookservice.dao;

import java.util.ArrayList;

import com.librarybooks.server.bookservice.datasets.AuthorsDataSet;
import com.librarybooks.server.bookservice.datasets.BooksDataSet;
import com.librarybooks.server.bookservice.datasets.GenresDataSet;
import com.librarybooks.server.bookservice.datasets.SelectionsDataSet;

/**
 * Created by Dmitry on 17.04.2016.
 */
public interface BooksDAO {
    ArrayList<BooksDataSet> findBooksByTitle(String title);
    ArrayList<BooksDataSet> getAllBooks();
    BooksDataSet getBookById(long bookId);
    
    void addBook(BooksDataSet book);
    void addAuthor(AuthorsDataSet author);
    void addGenre(GenresDataSet genre);
    
    ArrayList<BooksDataSet> getBooksByAuthorId(long authorId);
    ArrayList<BooksDataSet> getBooksByGenreId(long genreId);
    ArrayList<BooksDataSet> getBooksBySelectionId(long selectionId);
    
    AuthorsDataSet getAuthorById(long authorId);
    GenresDataSet getGenreById(long genreId);
    
    ArrayList<AuthorsDataSet> getAllAuthors();
    ArrayList<GenresDataSet> getAllGenres();
    ArrayList<SelectionsDataSet> getAllSelections();
}
