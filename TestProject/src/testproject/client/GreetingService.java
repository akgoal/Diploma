package testproject.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import testproject.client.objects.Book;
import testproject.client.objects.CallInput;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {

	ArrayList<Book> sendServer(CallInput callInput);

	Book bookToServer(Book callInput);
}
