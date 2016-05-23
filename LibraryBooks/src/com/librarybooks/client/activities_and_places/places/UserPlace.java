package com.librarybooks.client.activities_and_places.places;

import java.util.ArrayList;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.librarybooks.client.objects.Book;

public class UserPlace extends Place {
	private String param;
	private Book basket;
	private ArrayList<Book> basket_list = new ArrayList<Book>();

	public UserPlace(String token) {
		this.param = token;
		// this.param1 = param1;
	}

	public String getParam() {
		return param;
	}

	public void setParamBasket(Book basket) {
		this.basket = basket;
	}

	public Book getParamBasket() {
		return basket;
	}

	public ArrayList<Book> getBasketList() {
		return basket_list;
	}

	public void setBasketList(ArrayList<Book> basket_list) {
		this.basket_list = basket_list;
	}

	public static class Tokenizer implements PlaceTokenizer<UserPlace> {

		@Override
		public String getToken(UserPlace place) {
			return place.getParam();
		}

		@Override
		public UserPlace getPlace(String token) {
			return new UserPlace(token);
		}

	}

}
