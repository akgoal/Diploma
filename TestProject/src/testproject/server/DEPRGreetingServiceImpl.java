package testproject.server;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import testproject.client.BookService;
import testproject.client.GreetingService;
import testproject.client.objects.Book;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class DEPRGreetingServiceImpl extends RemoteServiceServlet implements BookService, GreetingService {

	public ArrayList<Book> sendServer() {
		ArrayList<Book> list = new ArrayList<Book>();
		list.clear();

		for (int i = 0; i < 8; i++) {
			Book book = new Book();
			book.setIdBook(i);
			book.setIdAutor(i);
			book.setIdGenre(i);
			book.setAutor("автор " + i + "; ");
			book.setTitle("название " + i + "; ");
			book.setGenre("жанр " + i + "; ");
			book.setImg("src");
			list.add(book);
		}

		return list;
	}

	public ArrayList<Book> findBooksByAutorBook(long id_autor) {
		ArrayList<Book> list = new ArrayList<Book>();
		list.clear();
		for (int i = 0; i < 8; i++) {
			Book book = new Book();
			book.setIdBook(i);
			book.setIdAutor(id_autor);
			book.setIdGenre(i);
			book.setAutor("автор " + id_autor + "; ");
			book.setTitle("название " + i + "; ");
			book.setGenre("жанр " + i + "; ");
			book.setImg("src");
			list.add(book);
		}

		return list;
	}

	public ArrayList<Book> findBooksByGenreBook(long id_genre) {
		ArrayList<Book> list = new ArrayList<Book>();
		list.clear();
		for (int i = 0; i < 8; i++) {
			Book book = new Book();
			book.setIdBook(i);
			book.setIdAutor(i);
			book.setIdGenre(id_genre);
			book.setAutor("автор " + i + "; ");
			book.setTitle("название " + i + "; ");
			book.setGenre("жанр " + id_genre + "; ");
			book.setImg("src");
			list.add(book);
		}

		return list;
	}

	public Book bookToServer(Book input) {
		Book back = new Book();
		back.setAutor(" -> " + input.getAutor());
		back.setTitle(" -> " + input.getTitle());
		return back;
	}

}
