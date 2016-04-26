package testproject.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import testproject.client.objects.Book;

public interface BookServiceAsync {

	void findBooksByAuthorBook(long callInput, AsyncCallback<ArrayList<Book>> callback);

	void findBooksByGenreBook(long callInput, AsyncCallback<ArrayList<Book>> callback);

	void sendServer(AsyncCallback<ArrayList<Book>> callback);

	void bookToServer(Book callInput, AsyncCallback<Book> callback);

	void selectBook(long callInput, AsyncCallback<Book> callback);

}
