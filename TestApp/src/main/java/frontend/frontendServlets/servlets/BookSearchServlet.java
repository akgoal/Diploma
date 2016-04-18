package frontend.frontendServlets.servlets;

import bookservice.Book;
import bookservice.BookServiceI;
import org.apache.logging.log4j.LogManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Dmitry on 16.04.2016.
 */
public class BookSearchServlet extends HttpServlet {

    public static String PAGE_URL = "/search";
    static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(BookSearchServlet.class.getName());

    private BookServiceI bookService;

    public BookSearchServlet(BookServiceI bookService) {
        this.bookService = bookService;
    }

    // поиск книги по названию
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");

        String title = request.getParameter("title");
        if (title == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        ArrayList<Book> books = new ArrayList<>();

        try {
            books = bookService.findBooks(title);
        } catch(Exception e) {
            logger.error(e);
        }

        response.setContentType("text/plain;charset=utf-8");
        response.getWriter().println("Результаты поиска:");
        for (Book b : books)
            response.getWriter().println("\"" + b.getTitle() + "\", " + b.getAuthor());
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
