package com.librarybooks.server.security;

import com.librarybooks.client.OrderService;
import com.librarybooks.client.objects.Book;
import com.librarybooks.client.objects.OrderDto;
import com.librarybooks.server.bookservice.dao.BooksDAO;
import com.librarybooks.server.bookservice.datasets.BooksDataSet;
import com.librarybooks.server.security.dao.OrderBookDao;
import com.librarybooks.server.security.dao.OrderDao;
import com.librarybooks.server.security.dao.UserDao;
import com.librarybooks.server.security.data.Order;
import com.librarybooks.server.security.data.OrderBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrderBookDao orderBookDao;

    @Autowired
    private BooksDAO booksDAO;

    ConcurrentHashMap<String, ArrayList<Book>> books = new ConcurrentHashMap<>();
    long i = 0;

    @Override
    public ArrayList<OrderDto> listOrder(String username) {
        List<Order> orders = orderDao.getByUserId(userDao.findUserByUsername(username).getId());

        ArrayList<OrderDto> dtoList = new ArrayList<>();
        for (Order order : orders) {
            OrderDto dto = new OrderDto();
            dto.setBooks(new ArrayList<Book>());
            dto.setId_order(order.getOrderNum());
            dto.setDate(order.getDate());
            dto.setDateBack(order.getDateBack());
            dto.setPrice(order.getPrice());
            dto.setState(order.getState());

            for (BooksDataSet dbBook : order.getOrderBooks()) {
                Book book = new Book();
                book.setIdBook(dbBook.getId());
                book.setTitle(dbBook.getTitle());
                dto.getBooks().add(book);
            }

            dtoList.add(dto);
        }

        return dtoList;
    }

    @Override
    public ArrayList<Book> listBook(String username) {
        return books.get(username);
    }

    @Override
    public void addBook(Book book, String username) {
        if (!books.containsKey(username)) {
            books.put(username, new ArrayList<Book>());
        }
        books.get(username).add(book);
    }

    @Override
    public void delBook(int i, String username) {
        // TODO Auto-generated method stub
        books.remove(i);
    }

    @Override
    public void addOrder(int i, String username) {
        // TODO Auto-generated method stub
        Order order = new Order();
        int price = 0;
        for (Book book : books.get(username)) {
            price = price + Integer.valueOf(book.getPrice());
        }
        order.setPrice(price + "");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        order.setDate(dateFormat.format(new Date()));
        order.setDateBack(dateFormat.format(new Date()));
        if (i == 0) {
            order.setDateBack("—");
        } else {
            // Тут нужно прибавить i недель
            order.setDateBack(dateFormat.format(new Date()));
        }
        order.setOrderNum(i++);
        if (i % 2 == 0)
            order.setState("В ожидании");
        else
            order.setState("Принят");

        order.setUserId(userDao.findUserByUsername(username).getId());
        orderDao.save(order);

        for (Book book : books.get(username)) {
            OrderBook orderBook = new OrderBook();
            orderBook.setOrder(order);
            orderBook.setBooksDataSet(booksDAO.getBookById(book.getIdBook()));
            orderBookDao.save(orderBook);
        }

    }

}
