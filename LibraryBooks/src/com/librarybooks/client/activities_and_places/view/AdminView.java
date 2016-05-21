package com.librarybooks.client.activities_and_places.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface AdminView extends IsWidget {
	void setAddView(String ref);

	AddBook getAddBook();

	void setPresenter(Presenter listener);

	public interface Presenter {
		void goTo(Place place);
	}
}
