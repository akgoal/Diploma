package testproject.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import testproject.client.objects.Book;

public interface GreetingServiceAsync {

	void sendServer(AsyncCallback<ArrayList<Book>> asyncCallback);

	void bookToServer(Book callInput, AsyncCallback<Book> callback);

}
