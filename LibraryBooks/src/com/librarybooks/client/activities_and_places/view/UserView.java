package com.librarybooks.client.activities_and_places.view;

import java.util.ArrayList;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.librarybooks.client.objects.Author;
import com.librarybooks.client.objects.Book;
import com.librarybooks.client.objects.Genre;
import com.librarybooks.client.objects.Selection;
import com.librarybooks.client.widgets.SearchPane;

/** View interface. Extends IsWidget so a view impl can easily provide its container widget.
 * 
 * @author drfibonacci */
public interface UserView extends IsWidget {
	void setView(String ref);

	void setView(ArrayList<Book> book, int col_page, int page, String type);

	SearchPane getSearchPane();

	void setView(Book book);

	void setNav(ArrayList<Genre> genres, ArrayList<Author> authors,
			ArrayList<Selection> selections);

	void setPresenter(Presenter listener);

	public interface Presenter {
		void goTo(Place place);
	}
}