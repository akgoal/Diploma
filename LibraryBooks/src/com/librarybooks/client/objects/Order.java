package com.librarybooks.client.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("serial")
public class Order implements Serializable {

	private ArrayList<Book> books;
	private String date;
	private String price;
	private String dateBack;
	private String state;
	private long id_order;

	public Order() {
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getId_order() {
		return id_order;
	}

	public void setId_order(long id_order) {
		this.id_order = id_order;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDateBack() {
		return dateBack;
	}

	public void setDateBack(String dateBack) {
		this.dateBack = dateBack;
	}

}
