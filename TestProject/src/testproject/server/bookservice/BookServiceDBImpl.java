package testproject.server.bookservice;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import testproject.client.BookService;
import testproject.client.objects.Book;
import testproject.client.objects.Genre;
import testproject.server.bookservice.dao.BooksDAO;
import testproject.server.bookservice.datasets.AuthorsDataSet;
import testproject.server.bookservice.datasets.BooksDataSet;
import testproject.server.bookservice.datasets.GenresDataSet;

/**
 * The server-side implementation of the RPC service, using database.
 */
@SuppressWarnings("serial")
public class BookServiceDBImpl extends RemoteServiceServlet implements BookService {

	private static final String hibernate_show_sql = "true";
	private static final String hibernate_hbm2ddl_auto = "update";

	private final SessionFactory sessionFactory;

	public BookServiceDBImpl() {
		Configuration configuration = getPostgreSqlConfiguration();
		// Configuration configuration = new Configuration();
		// configuration.configure("hibernate.cfg.xml");
		sessionFactory = createSessionFactory(configuration);

		// addEntries();
	}

	private Configuration getPostgreSqlConfiguration() {
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(BooksDataSet.class);

		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
		configuration.setProperty("hibernate.connection.url",
				"jdbc:postgresql://localhost:5432/testproject_library_books");
		configuration.setProperty("hibernate.connection.username", "postgres");
		configuration.setProperty("hibernate.connection.password", "ak");
		configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
		configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
		configuration.addAnnotatedClass(testproject.server.bookservice.datasets.BooksDataSet.class);
		configuration.addAnnotatedClass(testproject.server.bookservice.datasets.AuthorsDataSet.class);
		configuration.addAnnotatedClass(testproject.server.bookservice.datasets.GenresDataSet.class);
		return configuration;
	}

	private static SessionFactory createSessionFactory(Configuration configuration) {
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
		builder.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = builder.build();
		return configuration.buildSessionFactory(serviceRegistry);
	}

	@Override
	public ArrayList<Book> sendServer() throws Exception {
		ArrayList<Book> books = new ArrayList<>();
		try {
			Session session = sessionFactory.openSession();
			BooksDAO dao = new BooksDAO(session);
			ArrayList<BooksDataSet> dataSets = dao.getAllBooks();
			for (BooksDataSet bds : dataSets) {
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
	public ArrayList<Book> findBooksByAuthorBook(long callInput) throws Exception {
		ArrayList<Book> books = new ArrayList<>();
		try {
			Session session = sessionFactory.openSession();
			BooksDAO dao = new BooksDAO(session);
			ArrayList<BooksDataSet> dataSets = dao.getBooksByAuthorId(callInput);
			for (BooksDataSet bds : dataSets) {
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
			for (BooksDataSet bds : dataSets) {
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
	public Book selectBook(long callInput) throws Exception {
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
		ArrayList<Genre> gList = new ArrayList<>();
		gList.add(new Genre(booksDataSet.getGenre().getName(), booksDataSet.getGenre().getId()));
		return new Book(booksDataSet.getId(), booksDataSet.getAuthor().getName(), booksDataSet.getAuthor().getId(),
				booksDataSet.getTitle(), gList, booksDataSet.getImgUrl());
	}

	/*
	 * private <T> T doSession(Action<T> operation) throws HibernateException {
	 * Session session = sessionFactory.openSession(); T res =
	 * operation.perform(); session.close(); return res; }
	 * 
	 * private interface Action<ResultType> { ResultType perform(); }
	 */

	@SuppressWarnings("unused")
	private void addEntries() {
		try {
			Session session = sessionFactory.openSession();
			BooksDAO dao = new BooksDAO(session);

			AuthorsDataSet tolkien = new AuthorsDataSet("Дж. Р. Р. Толкин");
			AuthorsDataSet orwell = new AuthorsDataSet("Дж. Оруэлл");
			AuthorsDataSet bradbury = new AuthorsDataSet("Р. Брэдбери");

			GenresDataSet fantasy = new GenresDataSet("Фэнтези");
			GenresDataSet dystopia = new GenresDataSet("Антиутопия");
			GenresDataSet satire = new GenresDataSet("Сатира");

			dao.addAuthor(orwell);
			dao.addAuthor(tolkien);
			dao.addAuthor(bradbury);

			dao.addGenre(fantasy);
			dao.addGenre(dystopia);
			dao.addGenre(satire);

			dao.addBook(new BooksDataSet("Властелин колец. Братство Кольца", "братство", tolkien, fantasy, null));
			dao.addBook(new BooksDataSet("Властелин колец. Две крепости", "крепости", tolkien, fantasy, null));
			dao.addBook(new BooksDataSet("Властелин колец. Возвращение короля", "возвращение", tolkien, fantasy, null));
			dao.addBook(new BooksDataSet("1984", "большой брат", orwell, dystopia, null));
			dao.addBook(new BooksDataSet("451 градус по Фаренгейту", "пожарный", bradbury, dystopia, null));
			dao.addBook(new BooksDataSet("Скотный двор", "все животные равны", orwell, satire, null));

			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			;
		}
	}

}
