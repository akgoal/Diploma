package com.librarybooks.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.librarybooks.client.objects.Book;
import com.librarybooks.client.objects.Order;

public interface OrderServiceAsync {

	void listOrder(AsyncCallback<ArrayList<Order>> callback);

	void listBook(AsyncCallback<ArrayList<Book>> callback);

	void addBook(Book book, AsyncCallback<Void> callback);

	void delBook(int i, AsyncCallback<Void> callback);

	void addOrder(AsyncCallback<Void> callback);

}
