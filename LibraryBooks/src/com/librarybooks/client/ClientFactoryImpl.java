package com.librarybooks.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.librarybooks.client.activities_and_places.view.*;

public class ClientFactoryImpl implements ClientFactory {
	private static final EventBus eventBus = new SimpleEventBus();
	private static final PlaceController placeController = new PlaceController(eventBus);
	private static final UserView userView = new UserViewImpl();
	private static final AdminView adminView = new AdminViewImpl();

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public UserView getUserView() {
		return userView;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public AdminView getAdminView() {
		return adminView;
	}

}
