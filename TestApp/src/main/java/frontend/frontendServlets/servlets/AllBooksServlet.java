package frontend.frontendServlets.servlets;

import bookservice.Book;
import bookservice.BookServiceI;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.hibernate.HibernateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Dmitry on 16.04.2016.
 */
public class AllBooksServlet extends HttpServlet {

    public static String PAGE_URL = "/books";
    static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(AllBooksServlet.class.getName());

    private BookServiceI bookService;

    public AllBooksServlet(BookServiceI bookService) {
        this.bookService = bookService;
    }

    //список всех книг
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        ArrayList<Book> books = new ArrayList<>();
        try {
            books = bookService.getAllBooks();
        } catch (Exception e) {
            logger.error(e);
        }

        response.setContentType("text/plain;charset=utf-8");
        response.getWriter().println("Все книги:");
        for (Book b : books)
            response.getWriter().println("\"" + b.getTitle() + "\", " + b.getAuthor());
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
