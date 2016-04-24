package testproject.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import testproject.client.objects.Book;

public interface BookServiceAsync {

	void findBooksByAutorBook(long callInput, AsyncCallback<ArrayList<Book>> callback);

	void findBooksByGenreBook(long callInput, AsyncCallback<ArrayList<Book>> callback);

}
