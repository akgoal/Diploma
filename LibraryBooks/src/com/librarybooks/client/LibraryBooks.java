package com.librarybooks.client;

import com.librarybooks.client.AppActivityMapper;
import com.librarybooks.client.AppPlaceHistoryMapper;
import com.librarybooks.client.ClientFactory;
import com.librarybooks.client.activities_and_places.places.*;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ScrollEvent;
import com.google.gwt.user.client.Window.ScrollHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;

/** Entry point classes define <code>onModuleLoad()</code>. */
public class LibraryBooks implements EntryPoint {

	private static final BookServiceAsync bookService = GWT.create(BookService.class);

	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	private Place defaultPlace = new UserPlace("all=0&p=1");
	private SimplePanel appWidget = new SimplePanel();
	private SimplePanel navWidget = new SimplePanel();

	String html;

	public void onModuleLoad() {

		ClientFactory clientFactory = GWT.create(ClientFactory.class);
		EventBus eventBus = clientFactory.getEventBus();
		PlaceController placeController = clientFactory.getPlaceController();

		ActivityMapper activityMapper = new AppActivityMapper(clientFactory);
		ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
		activityManager.setDisplay(appWidget);

		AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);

		RootPanel.get("body").add(appWidget);
		historyHandler.handleCurrentHistory();

		/* Window.addWindowScrollHandler(new ScrollHandler() {
		 * 
		 * @Override public void onWindowScroll(ScrollEvent event) {
		 * 
		 * if (event.getScrollTop() > 45) { Document.get().getElementById("search_panel").getStyle() .setPosition(Position.FIXED); Document.get().getElementById("search_panel").getStyle().setTop(0, Unit.PX); } else { Document.get().getElementById("search_panel").getStyle().setTop(45, Unit.PX);
		 * Document.get().getElementById("search_panel").getStyle() .setPosition(Position.ABSOLUTE);
		 * 
		 * } } }); */

	}

	native void consoleLog(String message) /*-{
		console.log("me:" + message);
	}-*/;
}
