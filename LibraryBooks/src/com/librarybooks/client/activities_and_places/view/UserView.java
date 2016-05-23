package com.librarybooks.client.activities_and_places.view;

import java.util.ArrayList;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.librarybooks.client.BookServiceAsync;
import com.librarybooks.client.activities_and_places.places.UserPlace;
import com.librarybooks.client.objects.Book;
import com.librarybooks.client.widgets.Basket;
import com.librarybooks.client.widgets.BookWidget;
import com.librarybooks.client.widgets.SearchPane;

/** View interface. Extends IsWidget so a view impl can easily provide its container widget.
 * 
 * @author drfibonacci */
public interface UserView extends IsWidget {
	void setView(String ref);

	void setView(UserPlace place, ArrayList<Book> book, int col_page, int page, String type,
			String param, String title);

	SearchPane getSearchPane();

	Basket getBasket();

	BookWidget getBookWidget();

	void setView(BookServiceAsync bookService, UserPlace place, Book book);

	void setPresenter(Presenter listener);

	public interface Presenter {
		void goTo(Place place);
	}
}