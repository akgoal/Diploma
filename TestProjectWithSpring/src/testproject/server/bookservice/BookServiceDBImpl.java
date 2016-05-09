package testproject.server.bookservice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import testproject.client.BookService;
import testproject.client.objects.Book;
import testproject.server.bookservice.dao.BooksDAOI;
import testproject.server.bookservice.datasets.BooksDataSet;

/**
 * The server-side implementation of the RPC service, using database.
 */

@SuppressWarnings("serial")
@Service("bookService")
public class BookServiceDBImpl extends RemoteServiceServlet implements BookService {

	@Autowired
	private BooksDAOI dao;

	@Override
	public ArrayList<Book> sendServer() {
		ArrayList<Book> books = new ArrayList<>();

		ArrayList<BooksDataSet> dataSets = dao.getAllBooks();
		for (BooksDataSet bds : dataSets) {
			books.add(convertToBook(bds));
		}

		return books;
	}

	@Override
	public ArrayList<Book> findBooksByAuthorBook(long callInput) {
		ArrayList<Book> books = new ArrayList<>();
		ArrayList<BooksDataSet> dataSets = dao.getBooksByAuthorId(callInput);
		for (BooksDataSet bds : dataSets)
			books.add(convertToBook(bds));
		return books;
	}

	@Override
	public ArrayList<Book> findBooksByGenreBook(long callInput) {
		ArrayList<Book> books = new ArrayList<>();

		ArrayList<BooksDataSet> dataSets = dao.getBooksByGenreId(callInput);
		for (BooksDataSet bds : dataSets) {
			books.add(convertToBook(bds));
		}

		return books;
	}

	@Override
	public Book selectBook(long callInput) {
		BooksDataSet dataSet = dao.getBookById(callInput);
		Book book = convertToBook(dataSet);

		return book;
	}

	@Override
	public Book bookToServer(Book callInput) {
		Book back = new Book();
		back.setAuthor(" -> " + callInput.getAuthor());
		back.setTitle(" -> " + callInput.getTitle());
		return back;
	}

	private Book convertToBook(BooksDataSet booksDataSet) {
		return new Book(booksDataSet.getId(), booksDataSet.getAuthor().getName(), booksDataSet.getAuthor().getId(),
				booksDataSet.getTitle(), booksDataSet.getGenre().getName(), booksDataSet.getGenre().getId(),
				booksDataSet.getImgUrl());
	}

}
