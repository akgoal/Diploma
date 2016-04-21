package testproject.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import testproject.client.objects.Book;
import testproject.client.objects.CallInput;

public interface GreetingServiceAsync {

	void sendServer(CallInput callInput, AsyncCallback<ArrayList<Book>> asyncCallback);

	void bookToServer(Book callInput, AsyncCallback<Book> callback);

}
