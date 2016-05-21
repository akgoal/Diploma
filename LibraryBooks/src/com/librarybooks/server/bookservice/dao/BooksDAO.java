package com.librarybooks.server.bookservice.dao;

import java.util.ArrayList;
import java.util.List;

import com.librarybooks.server.bookservice.datasets.AuthorsDataSet;
import com.librarybooks.server.bookservice.datasets.BindingsDataSet;
import com.librarybooks.server.bookservice.datasets.BooksDataSet;
import com.librarybooks.server.bookservice.datasets.GenresDataSet;
import com.librarybooks.server.bookservice.datasets.PublishersDataSet;
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
    void addPublisher(PublishersDataSet publisher);
    void addBinding(BindingsDataSet binding);
    
    ArrayList<BooksDataSet> getBooksByAuthorId(long authorId);
    ArrayList<BooksDataSet> getBooksByGenreId(long genreId);
    ArrayList<BooksDataSet> getBooksBySelectionId(long selectionId);
    
    AuthorsDataSet getAuthorById(long authorId);
    GenresDataSet getGenreById(long genreId);
    SelectionsDataSet getSelectionById(long selectionId);
    
    ArrayList<AuthorsDataSet> getAllAuthors();
    ArrayList<GenresDataSet> getAllGenres();
    ArrayList<SelectionsDataSet> getAllSelections();
    
    ArrayList<BooksDataSet> searchBooks(List<String> words);
    
    void indexBooks();
    
    AuthorsDataSet getOrCreateAuthorByName(String authorName);
	GenresDataSet getOrCreateGenreByName(String genreName);
	PublishersDataSet getOrCreatePublisherByName(String publisherName);
	BindingsDataSet getOrCreateBindingByName(String bindingName);
}
