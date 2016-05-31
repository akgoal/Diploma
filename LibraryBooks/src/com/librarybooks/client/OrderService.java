package com.librarybooks.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.librarybooks.client.objects.Book;
import com.librarybooks.client.objects.Order;

/** The client-side stub for the RPC service. */
@RemoteServiceRelativePath("services/orderService")
public interface OrderService extends RemoteService {

	ArrayList<Order> listOrder();

	ArrayList<Book> listBook();

	void addBook(Book book);

	void delBook(int i);

	void addOrder(int i);

}
