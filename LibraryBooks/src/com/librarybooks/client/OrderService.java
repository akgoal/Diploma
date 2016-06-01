package com.librarybooks.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.librarybooks.client.objects.Book;
import com.librarybooks.client.objects.OrderDto;

import java.util.ArrayList;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("services/orderService")
public interface OrderService extends RemoteService {

    ArrayList<OrderDto> listOrder(String username);

    ArrayList<Book> listBook(String username);

    void addBook(Book book, String username);

    void delBook(int i, String username);

    void addOrder(int i, String username);

}
