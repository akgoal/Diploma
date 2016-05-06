package com.librarybooks.client.widgets;

import com.librarybooks.client.ClientFactory;
import com.librarybooks.client.activities_and_places.places.UserPlace;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

public class GenreLabel extends Composite implements ClickHandler {

	private long choose_id;

	public GenreLabel(String genre, long id_genre) {

		choose_id = id_genre;

		Label label = new Label(genre);
		label.setStyleName("linkFull");
		label.addClickHandler(this);
		initWidget(label);
	}

	@Override
	public void onClick(ClickEvent event) {
		ClientFactory clientFactory = GWT.create(ClientFactory.class);
		PlaceController placeController = clientFactory.getPlaceController();
		placeController.goTo(new UserPlace("genre=" + choose_id));
	}

}
