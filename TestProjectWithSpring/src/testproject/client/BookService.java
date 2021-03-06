package testproject.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import testproject.client.objects.Book;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("springGwtServices/bookService")
public interface BookService extends RemoteService {

	ArrayList<Book> findBooksByAuthorBook(long callInput) throws Exception;

	ArrayList<Book> findBooksByGenreBook(long callInput) throws Exception;

	ArrayList<Book> sendServer() throws Exception;

	Book selectBook(long callInput) throws Exception;

	Book bookToServer(Book callInput) throws Exception;

}
