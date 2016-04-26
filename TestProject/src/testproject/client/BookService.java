package testproject.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import testproject.client.objects.Book;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface BookService extends RemoteService {

	ArrayList<Book> findBooksByAuthorBook(long callInput);

	ArrayList<Book> findBooksByGenreBook(long callInput);

	ArrayList<Book> sendServer();

	Book selectBook(long callInput);

	Book bookToServer(Book callInput);

}
