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
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ScrollEvent;
import com.google.gwt.user.client.Window.ScrollHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.StackPanel;

import com.google.web.bindery.event.shared.EventBus;

/** Entry point classes define <code>onModuleLoad()</code>. */
public class LibraryBooks implements EntryPoint {

	private Place defaultPlace = new UserPlace("all=0&p=1");
	private SimplePanel appWidget = new SimplePanel();

	public void onModuleLoad() {
		BookServiceAsync bookService = GWT.create(BookService.class);
		ClientFactory clientFactory = GWT.create(ClientFactory.class);
		EventBus eventBus = clientFactory.getEventBus();
		PlaceController placeController = clientFactory.getPlaceController();

		// Start ActivityManager for the main widget with our ActivityMapper
		ActivityMapper activityMapper = new AppActivityMapper(clientFactory);
		ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
		activityManager.setDisplay(appWidget);

		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);

		RootPanel.get("listBook").add(appWidget);
		// Goes to place represented on URL or default place
		historyHandler.handleCurrentHistory();

		// com.google.web.bindery.event.shared.EventBus eventBus = new
		// SimpleEventBus();
		// final PlaceController placeController = new
		// PlaceController(eventBus);

		/*
		 * final MenuBar pages = new MenuBar(true); Command cmd1 = new Command() { public void execute() { placeController.goTo(new UserPlace("all")); } }; pages.addItem("Page1", cmd1); Command cmd2 = new Command() { public void execute() { placeController.goTo(new UserPlace("Page2")); } };
		 * pages.addItem("Page2", cmd2); MenuBar menu = new MenuBar(); menu.addItem("Pages", pages); VerticalPanel panel = new VerticalPanel(); panel.add(menu); panel.add(appWidget); RootPanel.get("container").add(panel); RootPanel.get("listBook").add(appWidget); //
		 * panelR.getElement().getStyle().setPosition(Position.ABSOLUTE); // panelR.add(panel);
		 * 
		 * // final Button sendBooks = new Button("Все книги");
		 * 
		 * // RootPanel.get("sendButtonContainer").add(sendBooks); // sendBooks.setStyleName("button_center");
		 */

		// HorizontalPanel hPanel = new HorizontalPanel();

		Command command = new Command() {
			public void execute() {
				Window.alert("Command Fired");
			}
		};
		MenuBar menuMain = new MenuBar();
		MenuBar menuSub = new MenuBar();
		MenuItem item1 = new MenuItem("One", command);
		MenuItem item2 = new MenuItem("Two", command);
		menuMain.addItem("Home", true, menuSub);
		menuMain.addItem("One", true, command);
		menuMain.addItem("Two", true, command);
		menuMain.addItem("Other", true, command);
		menuSub.addItem(item1);
		menuSub.addItem(item2);
		RootPanel.get("container").add(menuMain);

		StackPanel panel = new StackPanel();
		panel.add(new Label("Item1"), "Item1");
		panel.add(new Label("Item2"), "Item2");
		panel.add(new Label("Item3"), "Item3");
		// panel.setWidth("200px");
		// final RootPanel panelR=RootPanel.get("container");
		// panelR.getElement().getStyle().setPosition(Position.ABSOLUTE);
		RootPanel.get("stackContainer").add(panel);

		Window.addWindowScrollHandler(new ScrollHandler() {

			@Override
			public void onWindowScroll(ScrollEvent event) {
				if (event.getScrollTop() > 45) {
					Document.get().getElementById("search_panel").getStyle().setPosition(Position.FIXED);
					Document.get().getElementById("search_panel").getStyle().setTop(0, Unit.PX);
				} else {
					Document.get().getElementById("search_panel").getStyle().setTop(45, Unit.PX);
					Document.get().getElementById("search_panel").getStyle().setPosition(Position.ABSOLUTE);

				}

			}
		});
	}
}
