package bookservice;

import java.util.ArrayList;

/**
 * Created by Dmitry on 16.04.2016.
 */
public interface BookServiceI {
    ArrayList<Book> findBooks(String title) throws Exception;
    ArrayList<Book> getAllBooks() throws Exception;
    void addBook(Book book) throws Exception;
    void printInfo();
}
