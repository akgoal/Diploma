package com.librarybooks.client.activities_and_places.view;

import java.util.ArrayList;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.librarybooks.client.BookServiceAsync;
import com.librarybooks.client.objects.Book;

public interface AdminView extends IsWidget {
	void setAddView(String ref);

	void setChangeView(String ref, ArrayList<Book> books, BookServiceAsync bookService);

	AddBook getAddBook();

	void setPresenter(Presenter listener);

	public interface Presenter {
		void goTo(Place place);
	}
}
