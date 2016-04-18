package frontend.frontendServlets;

import bookservice.Book;
import bookservice.BookService;
import bookservice.BookServiceI;
import frontend.FrontendI;
import frontend.frontendServlets.servlets.AllBooksServlet;
import frontend.frontendServlets.servlets.BookSearchServlet;
import org.apache.logging.log4j.LogManager;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.logging.Logger;

/**
 * Created by Dmitry on 16.04.2016.
 */

/**
 * Реализация фронтенда с использованием сервлетов
 */
public class FrontendServlets implements FrontendI {

    static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(FrontendServlets.class.getName());

    public FrontendServlets() {
    }

    /* Запуск фронтенда */
    public void start() {
        BookServiceI bookService = new BookService();
        bookService.printInfo();
        /*try {
            bookService.addBook(new Book("Гарри Поттер и философский камень", "Дж. К. Роулинг"));
            bookService.addBook(new Book("Гарри Поттер и Тайная комната", "Дж. К. Роулинг"));
            bookService.addBook(new Book("Гарри Поттер и узник Азкабана", "Дж. К. Роулинг"));
            bookService.addBook(new Book("Гарри Поттер и Кубок огня", "Дж. К. Роулинг"));
            bookService.addBook(new Book("Гарри Поттер и Орден Феникса", "Дж. К. Роулинг"));
            bookService.addBook(new Book("Гарри Поттер и Принц-полукровка", "Дж. К. Роулинг"));
            bookService.addBook(new Book("Гарри Поттер и Дары Смерти", "Дж. К. Роулинг"));

            bookService.addBook(new Book("Властелин колец. Братство Кольца", "Дж. Р. Р. Толкин"));
            bookService.addBook(new Book("Властелин колец. Две крепости", "Дж. Р. Р. Толкин"));
            bookService.addBook(new Book("Властелин колец. Возвращение короля", "Дж. Р. Р. Толкин"));
        } catch (Exception e) {
            logger.info("" + e);
        }*/

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new BookSearchServlet(bookService)), BookSearchServlet.PAGE_URL);
        context.addServlet(new ServletHolder(new AllBooksServlet(bookService)), AllBooksServlet.PAGE_URL);

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(8080);
        server.setHandler(handlers);

        try {
            server.start();
            logger.info("Server started");
            server.join();
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
