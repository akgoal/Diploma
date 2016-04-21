package bookservice;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bookservice.dao.BooksDAO;
import bookservice.datasets.BooksDataSet;

/**
 * Created by Dmitry on 16.04.2016.
 */
public class BookService implements BookServiceI {

    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "update";

    private final SessionFactory sessionFactory;

    public BookService() {
        //Configuration configuration = getH2Configuration();
        Configuration configuration = getPostgreSqlConfiguration();
        sessionFactory = createSessionFactory(configuration);
    }

    private Configuration getH2Configuration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(BooksDataSet.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:./h2db");
        configuration.setProperty("hibernate.connection.username", "ak");
        configuration.setProperty("hibernate.connection.password", "ak");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
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

    @Override
    public void printInfo() {
        try {
            SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) sessionFactory;
            Connection connection = sessionFactoryImpl.getConnectionProvider().getConnection();
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }


    @Override
    public ArrayList<Book> findBooks(String title) throws Exception{
        ArrayList<Book> books = new ArrayList<>();
        try {
            Session session = sessionFactory.openSession();
            BooksDAO dao = new BooksDAO(session);
            ArrayList<BooksDataSet> dataSets = dao.findBooks(title);
            for (BooksDataSet bds:dataSets)
                books.add(new Book(bds.getTitle(), bds.getAuthor()));
            session.close();
        } catch (HibernateException e) {
            throw new Exception(e);
        }
        return books;
    }

    @Override
    public ArrayList<Book> getAllBooks() throws Exception{
        ArrayList<Book> books = new ArrayList<>();
        try {
            Session session = sessionFactory.openSession();
            BooksDAO dao = new BooksDAO(session);
            ArrayList<BooksDataSet> dataSets = dao.getAllBooks();
            for (BooksDataSet bds:dataSets)
                books.add(new Book(bds.getTitle(), bds.getAuthor()));
            session.close();
        } catch (HibernateException e) {
            throw new Exception(e);
        }
        return books;
    }

    @Override
    public void addBook(Book book)throws Exception {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            BooksDAO dao = new BooksDAO(session);
            dao.addBook(new BooksDataSet(book.getTitle(), book.getAuthor()));
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            throw new Exception(e);
        }
    }
}
