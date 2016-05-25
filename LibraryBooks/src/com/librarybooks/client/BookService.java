package com.librarybooks.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.librarybooks.client.objects.Author;
import com.librarybooks.client.objects.Book;
import com.librarybooks.client.objects.BookEdit;
import com.librarybooks.client.objects.Genre;
import com.librarybooks.client.objects.Selection;

/** The client-side stub for the RPC service. */
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

	ArrayList<Book> searchBooks(ArrayList<String> param);

	String titleByIdSelection(long id);

	String titleByIdGenre(long id);

	String titleByIdAuthor(long id);

	void addBook(BookEdit book);

	ArrayList<Book> listNew();

	ArrayList<Book> listPopular();

	ArrayList<Book> listClassic();

	ArrayList<Book> listChild();

	ArrayList<Book> listForeign();

	int changeRate(long id, int rate_new);

	BookEdit selectBookEdit(long id);


	void DelBook(long id);

}
