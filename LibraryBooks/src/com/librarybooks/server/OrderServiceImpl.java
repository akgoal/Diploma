package com.librarybooks.server;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibm.icu.text.SimpleDateFormat;
import com.librarybooks.client.OrderService;
import com.librarybooks.client.objects.Book;
import com.librarybooks.client.objects.Order;

/** The server-side implementation of the RPC service. */
@SuppressWarnings("serial")
public class OrderServiceImpl extends RemoteServiceServlet implements OrderService {

	ArrayList<Book> books = new ArrayList<Book>();
	ArrayList<Order> orders = new ArrayList<Order>();
	long i = 0;

	@Override
	public ArrayList<Order> listOrder() {
		return orders;
	}

	@Override
	public ArrayList<Book> listBook() {
		return books;
	}

	@Override
	public void addBook(Book book) {
		// TODO Auto-generated method stub
		books.add(book);
	}

	@Override
	public void delBook(int i) {
		// TODO Auto-generated method stub
		books.remove(i);
	}

	@Override
	public void addOrder(int i) {
		// TODO Auto-generated method stub
		Order order = new Order();
		ArrayList<Book> _books = new ArrayList<Book>(books);
		int price = 0;
		for (Book book : _books) {
			price = price + Integer.valueOf(book.getPrice());
		}
		order.setPrice(price + "");
		order.setBooks(_books);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		order.setDate(dateFormat.format(new Date()));
		order.setDateBack(dateFormat.format(new Date()));
		if (i == 0) {
			order.setDateBack("—");
		} else {
			// Тут нужно прибавить i недель
			order.setDateBack(dateFormat.format(new Date()));
		}
		order.setId_order(i++);
		if (i % 2 == 0)
			order.setState("В ожидании");
		else
			order.setState("Принят");
		orders.add(order);
		this.books.clear();
	}

}
