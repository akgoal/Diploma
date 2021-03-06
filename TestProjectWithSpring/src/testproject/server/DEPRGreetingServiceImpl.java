package testproject.server;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import testproject.client.BookService;
import testproject.client.objects.Book;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class DEPRGreetingServiceImpl extends RemoteServiceServlet implements BookService {

	public ArrayList<Book> sendServer() {
		ArrayList<Book> list = new ArrayList<Book>();
		list.clear();

		for (int i = 0; i < 8; i++) {
			Book book = new Book();
			book.setIdBook(i);
			book.setIdAuthor(i);
			book.setIdGenre(i);
			book.setAuthor("автор " + i + "; ");
			book.setTitle("название " + i + "; ");
			book.setGenre("жанр " + i + "; ");
			book.setImg("src");
			list.add(book);
		}

		return list;
	}

	public ArrayList<Book> findBooksByAuthorBook(long id_author) {
		ArrayList<Book> list = new ArrayList<Book>();
		list.clear();
		for (int i = 0; i < 8; i++) {
			Book book = new Book();
			book.setIdBook(i);
			book.setIdAuthor(id_author);
			book.setIdGenre(i);
			book.setAuthor("автор " + id_author + "; ");
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
			book.setIdAuthor(i);
			book.setIdGenre(id_genre);
			book.setAuthor("автор " + i + "; ");
			book.setTitle("название " + i + "; ");
			book.setGenre("жанр " + id_genre + "; ");
			book.setImg("src");
			list.add(book);
		}

		return list;
	}

	public Book bookToServer(Book input) {
		Book back = new Book();
		back.setAuthor(" -> " + input.getAuthor());
		back.setTitle(" -> " + input.getTitle());
		return back;
	}

	/**
	 * @param id_genre
	 * @return
	 */
	public Book selectBook(long id_book) {

		Book book = new Book();
		book.setIdBook(id_book);
		book.setIdAuthor(id_book);
		book.setIdGenre(id_book);
		book.setAuthor("автор " + id_book + "; ");
		book.setTitle("название " + id_book + "; ");
		book.setGenre("жанр " + id_book + "; ");
		book.setImg("src");

		return book;
	}

}
