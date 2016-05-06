package testproject.server.bookservice;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import testproject.client.BookService;
import testproject.client.objects.Book;
import testproject.server.bookservice.dao.BooksDAO;
import testproject.server.bookservice.datasets.BooksDataSet;

/**
 * The server-side implementation of the RPC service, using database.
 */

@SuppressWarnings("serial")
@Service("bookService")
public class BookServiceDBImpl extends RemoteServiceServlet implements BookService {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public ArrayList<Book> sendServer() throws Exception{
		ArrayList<Book> books = new ArrayList<>();
		try {
			Session session = sessionFactory.openSession();
			BooksDAO dao = new BooksDAO(session);
			ArrayList<BooksDataSet> dataSets = dao.getAllBooks();
			for (BooksDataSet bds : dataSets)
			{
				books.add(convertToBook(bds));
			}
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return books;
	}

	@Override
	public ArrayList<Book> findBooksByAuthorBook(long callInput) throws Exception{
		ArrayList<Book> books = new ArrayList<>();
		try {
			Session session = sessionFactory.openSession();
			BooksDAO dao = new BooksDAO(session);
			ArrayList<BooksDataSet> dataSets = dao.getBooksByAuthorId(callInput);
			for (BooksDataSet bds : dataSets)
			{
				books.add(convertToBook(bds));
			}
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return books;
	}

	@Override
	public ArrayList<Book> findBooksByGenreBook(long callInput) throws Exception {
		ArrayList<Book> books = new ArrayList<>();
		try {
			Session session = sessionFactory.openSession();
			BooksDAO dao = new BooksDAO(session);
			ArrayList<BooksDataSet> dataSets = dao.getBooksByGenreId(callInput);
			for (BooksDataSet bds : dataSets)
			{
				books.add(convertToBook(bds));
			}
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return books;
	}
	
	@Override
	public Book selectBook(long callInput) throws Exception{
		Book book = new Book();
		try {
			Session session = sessionFactory.openSession();
			BooksDAO dao = new BooksDAO(session);
			BooksDataSet dataSet = dao.getBookById(callInput);
			book = convertToBook(dataSet);
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
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
		return new Book(
				booksDataSet.getId(), 
				booksDataSet.getAuthor().getName(),
				booksDataSet.getAuthor().getId(),
				booksDataSet.getTitle(),
				booksDataSet.getGenre().getName(),
				booksDataSet.getGenre().getId(),
				booksDataSet.getImgUrl()
				);
	}
	
}
