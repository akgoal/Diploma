package testproject.server;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import testproject.client.GreetingService;
import testproject.client.objects.Book;
import testproject.client.objects.CallInput;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class DEPRGreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	public ArrayList<Book> sendServer(CallInput input) {
		ArrayList<Book> list = new ArrayList<Book>();
		list.clear();
		switch (input.getText()) {
		case "all": {
			for (int i = 0; i < 8; i++) {
				Book book = new Book();
				book.setAutor("автор " + (i + 1) + "; ");
				book.setNameBook("название " + (i + 1) + "; ");
				book.setType("жанр " + (i + 1) + "; ");
				book.setImg("http://files.books.ru/pic/643001-644000/643136/1966072586c.jpg");
				list.add(book);
			}
			break;
		}
		default:
			break;
		}

		return list;
	}

	public Book bookToServer(Book input) {
		Book back = new Book();
		back.setAutor(" -> " + input.getAutor());
		back.setNameBook(" -> " + input.getNameBook());
		return back;
	}

}
