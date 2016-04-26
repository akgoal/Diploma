package testproject.server.bookservice.dao;

import java.util.ArrayList;

import testproject.server.bookservice.datasets.BooksDataSet;

/**
 * Created by Dmitry on 17.04.2016.
 */
public interface BooksDAOI {
    ArrayList<BooksDataSet> findBooks(String title);
    ArrayList<BooksDataSet> getAllBooks();
    void addBook(BooksDataSet book);
}
