package com.librarybooks.client.activities_and_places.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * View interface. Extends IsWidget so a view impl can easily provide its container widget.
 * 
 * @author drfibonacci
 */
public interface UserView extends IsWidget {
	void setView(String ref);

	void setPresenter(Presenter listener);

	public interface Presenter {
		void goTo(Place place);
	}
}