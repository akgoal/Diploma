package com.librarybooks.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.librarybooks.client.objects.Author;
import com.librarybooks.client.objects.Book;
import com.librarybooks.client.objects.Genre;
import com.librarybooks.client.objects.Selection;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("services/bookService")
public interface BookService extends RemoteService {

	ArrayList<Book> findBooksByAuthorBook(long callInput) throws Exception;

	ArrayList<Book> findBooksByGenreBook(long callInput) throws Exception;

	ArrayList<Book> sendServer() throws Exception;

	Book selectBook(long callInput) throws Exception;

	Book bookToServer(Book callInput) throws Exception;

	ArrayList<Genre> listOfGenres();

	ArrayList<Book> findBooksBySelectionBook(long id);

	ArrayList<Author> listOfAuthors();

	ArrayList<Selection> listOfSelections();
}
