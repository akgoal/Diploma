package com.librarybooks.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.librarybooks.client.objects.Book;
import com.librarybooks.client.objects.OrderDto;

import java.util.ArrayList;

public interface OrderServiceAsync {

    void listOrder(String username, AsyncCallback<ArrayList<OrderDto>> callback);

    void listBook(String username, AsyncCallback<ArrayList<Book>> callback);

    void addBook(Book book, String username, AsyncCallback<Void> callback);

    void delBook(int i, String username, AsyncCallback<Void> callback);

    void addOrder(int i, String username, AsyncCallback<Void> callback);

}
