package com.librarybooks.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.librarybooks.client.objects.Author;
import com.librarybooks.client.objects.Book;
import com.librarybooks.client.objects.Genre;
import com.librarybooks.client.objects.Selection;

public interface BookServiceAsync {

	void findBooksByAuthorBook(long callInput, AsyncCallback<ArrayList<Book>> callback);

	void findBooksByGenreBook(long callInput, AsyncCallback<ArrayList<Book>> callback);

	void sendServer(AsyncCallback<ArrayList<Book>> callback);

	void bookToServer(Book callInput, AsyncCallback<Book> callback);

	void selectBook(long callInput, AsyncCallback<Book> callback);

	void listOfGenres(AsyncCallback<ArrayList<Genre>> callback);

	void findBooksBySelectionBook(long id, AsyncCallback<ArrayList<Book>> callback);

	void listOfAuthors(AsyncCallback<ArrayList<Author>> callback);

	void listOfSelections(AsyncCallback<ArrayList<Selection>> callback);

	void searchBooks(ArrayList<String> param, AsyncCallback<ArrayList<Book>> callback);

	void titleByIdSelection(long id, AsyncCallback<String> callback);

	void titleByIdGenre(long id, AsyncCallback<String> callback);

	void titleByIdAuthor(long id, AsyncCallback<String> callback);

}
