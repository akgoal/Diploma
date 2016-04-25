package testproject.server.bookservice;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import testproject.client.GreetingService;
import testproject.client.objects.Book;
import testproject.server.bookservice.dao.BooksDAO;
import testproject.server.bookservice.datasets.BooksDataSet;

/**
 * The server-side implementation of the RPC service, using database.
 */
@SuppressWarnings("serial")
public class GreetingServiceDBImpl extends RemoteServiceServlet implements GreetingService {

	private static final String hibernate_show_sql = "true";
	private static final String hibernate_hbm2ddl_auto = "update";

	private final SessionFactory sessionFactory;

	public GreetingServiceDBImpl() {
		Configuration configuration = getPostgreSqlConfiguration();
		sessionFactory = createSessionFactory(configuration);
	}

	private Configuration getPostgreSqlConfiguration() {
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(BooksDataSet.class);

		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
		configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/db_library_books");
		configuration.setProperty("hibernate.connection.username", "postgres");
		configuration.setProperty("hibernate.connection.password", "ak");
		configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
		configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
		return configuration;
	}

	private static SessionFactory createSessionFactory(Configuration configuration) {
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
		builder.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = builder.build();
		return configuration.buildSessionFactory(serviceRegistry);
	}

	private ArrayList<Book> getAllBooks() throws Exception {
		ArrayList<Book> books = new ArrayList<>();
		try {
			Session session = sessionFactory.openSession();
			BooksDAO dao = new BooksDAO(session);
			ArrayList<BooksDataSet> dataSets = dao.getAllBooks();
			for (BooksDataSet bds : dataSets)
				books.add(new Book(bds.getAuthor(), bds.getTitle(), "type", "img"));
			session.close();
		} catch (HibernateException e) {
			throw new Exception(e);
		}
		return books;
	}

	@Override
	public ArrayList<Book> sendServer() {
		ArrayList<Book> list = new ArrayList<Book>();
		list.clear();

		try {
			list = getAllBooks();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public Book bookToServer(Book callInput) {
		Book back = new Book();
		back.setAuthor(" -> " + callInput.getAuthor());
		back.setTitle(" -> " + callInput.getTitle());
		return back;
	}

}
