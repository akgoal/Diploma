package com.librarybooks.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.librarybooks.client.activities_and_places.view.AdminView;
import com.librarybooks.client.activities_and_places.view.UserView;

public interface ClientFactory {

	EventBus getEventBus();

	PlaceController getPlaceController();

	UserView getUserView();

	AdminView getAdminView();
}
