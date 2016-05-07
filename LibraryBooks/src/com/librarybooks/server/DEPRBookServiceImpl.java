package com.librarybooks.server;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.librarybooks.client.BookService;
import com.librarybooks.client.objects.Book;
import com.librarybooks.client.objects.Genre;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class DEPRBookServiceImpl extends RemoteServiceServlet implements BookService {

	public ArrayList<Book> sendServer() {
		ArrayList<Book> list = new ArrayList<Book>();
		list.clear();

		for (int i = 0; i < 30; i++) {
			Book book = new Book();
			book.setIdBook(i);
			book.setIdAuthor(i);
			book.setAuthor("И.И.Иванов " + i);
			book.setTitle("Название. Название. Название. Название. " + i);

			ArrayList<Genre> lg = new ArrayList<Genre>();
			lg.add(new Genre("Жанр " + i, i));
			lg.add(new Genre("Жанр " + (i + 1), (i + 1)));
			book.setGenre(lg);

			book.setImg("src");
			list.add(book);
		}

		return list;
	}

	public ArrayList<Book> findBooksByAuthorBook(long id_author) {
		ArrayList<Book> list = new ArrayList<Book>();
		list.clear();
		for (int i = 0; i < 30; i++) {
			Book book = new Book();
			book.setIdBook(i);
			book.setIdAuthor(id_author);
			book.setAuthor("И.И.Иванов " + id_author);
			book.setTitle("Название. Название. Название. Название. " + i);

			ArrayList<Genre> lg = new ArrayList<Genre>();
			lg.add(new Genre("Жанр " + i, i));
			lg.add(new Genre("Жанр " + (i + 1), (i + 1)));
			book.setGenre(lg);

			book.setImg("src");
			list.add(book);
		}

		return list;
	}

	public ArrayList<Book> findBooksByGenreBook(long id_genre) {
		ArrayList<Book> list = new ArrayList<Book>();
		list.clear();
		for (int i = 0; i < 30; i++) {
			Book book = new Book();
			book.setIdBook(i);
			book.setIdAuthor(i);
			book.setAuthor("И.И.Иванов " + i);
			book.setTitle("Название. Название. Название. Название. " + i);

			ArrayList<Genre> lg = new ArrayList<Genre>();
			lg.add(new Genre("Жанр " + id_genre, id_genre));
			lg.add(new Genre("Жанр " + (id_genre + 1), (id_genre + 1)));
			book.setGenre(lg);

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
		book.setAuthor("И.И.Иванов " + id_book);
		book.setTitle("Название. Название. Название. Название. " + id_book);

		ArrayList<Genre> lg = new ArrayList<Genre>();
		lg.add(new Genre("Жанр " + id_book, id_book));
		lg.add(new Genre("Жанр " + (id_book + 1), (id_book + 1)));
		book.setGenre(lg);

		book.setImg("src");

		return book;
	}

}
