package com.librarybooks.client;

import com.librarybooks.client.AppActivityMapper;
import com.librarybooks.client.AppPlaceHistoryMapper;
import com.librarybooks.client.ClientFactory;
import com.librarybooks.client.activities_and_places.places.*;
import com.librarybooks.client.objects.Genre;

import antlr.debug.Event;

import java.util.ArrayList;

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
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ScrollEvent;
import com.google.gwt.user.client.Window.ScrollHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;

/** Entry point classes define <code>onModuleLoad()</code>. */
public class LibraryBooks implements EntryPoint {

	private static native void scr() /*-{
		{

			var menu_ul = $wnd.$('.menu > li > ul'), menu_a = $wnd
					.$('.menu > li > a');

			menu_ul.hide();
			menu_a.click(function(e) {
				e.preventDefault();
				if (!$wnd.$(this).hasClass('active')) {
					menu_a.removeClass('active');
					menu_ul.filter(':visible').slideUp('normal');
					$wnd.$(this).addClass('active').next().stop(true, true)
							.slideDown('normal');
				} else {
					$wnd.$(this).removeClass('active');
					$wnd.$(this).next().stop(true, true).slideUp('normal');
				}
			});

		}
	}-*/;

	private static native void h() /*-{
		{
			$wnd.$(".cont").height($wnd.$(document).height());
		}
	}-*/;

	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	private Place defaultPlace = new UserPlace("all=0&p=1");
	private SimplePanel appWidget = new SimplePanel();
	String html = new String("");

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

		bookService.listOfGenres(new AsyncCallback<ArrayList<Genre>>() {
			public void onFailure(Throwable caught) {
				Label text = new Label(SERVER_ERROR);
			}

			public void onSuccess(ArrayList<Genre> result) {

				html = html + "<ul class=\"menu\">"
						+ "<li class=\"item1\"><a href=\"#\">Жанры <span>" + result.size()
						+ "</span></a>" + "<ul>";
				for (int i = 0; i < result.size(); i++) {
					html = html + "<li class=\"subitem" + (i + 1) + "\"><a href=\"#UserPlace:genre="
							+ result.get(i).getIdGenre() + "&p=1\">" + result.get(i).getGenre()
							+ " <span>" + result.get(i).getColBook() + "</span></a></li>";
				}
				html = html + "</ul>" + "</li>";
				html = html + "<li class=\"item2\"><a href=\"#\">Авторы <span>147</span></a>"
						+ "<ul>"
						+ "<li class=\"subitem1\"><a href=\"#\">Автор 1 <span>14</span></a></li>"
						+ "<li class=\"subitem2\"><a href=\"#\">Автор 2 <span>6</span></a></li>"
						+ "<li class=\"subitem3\"><a href=\"#\">Автор 3 <span>2</span></a></li>"
						+ "</ul>" + "</li>"
						+ "<li class=\"item3\"><a href=\"#\">Подборки <span>340</span></a>" + "<ul>"
						+ "<li class=\"subitem1\"><a href=\"#\">Подборка 1 <span>14</span></a></li>"
						+ "<li class=\"subitem2\"><a href=\"#\">Подборка 2 <span>6</span></a></li>"
						+ "<li class=\"subitem3\"><a href=\"#\">Подборка 3 <span>2</span></a></li>"
						+ "</ul>" + "</li>" + "</ul>";
				RootPanel.get("wrapper").add(new HTML(html));

				scr();

			}
		});

		/*
		 * SafeHtml navHtml = new SafeHtml() {
		 * 
		 * @Override public String asString() { return "<ul class=\"menu\">" + "<li class=\"item1\">" + "<a href=\"#\">Подборки <span>340</span></a>" + "<ul>" + "<li class=\"subitem1\"><a href=\"#\">Cute Kittens <span>14</span></a></li>" +
		 * "<li class=\"subitem2\"><a href=\"#\">Strange “Stuff” <span>6</span></a></li>" + "<li class=\"subitem3\"><a href=\"#\">Automatic Fails <span>2</span></a></li>" + "</ul>" + "</li>" + "<li class=\"item2\"><a href=\"#\">Жанры <span>147</span></a>" + "<ul>" +
		 * "<li class=\"subitem1\"><a href=\"#\">Cute Kittens <span>14</span></a></li>" + "<li class=\"subitem2\"><a href=\"#\">Strange “Stuff” <span>6</span></a></li>" + "<li class=\"subitem3\"><a href=\"#\">Automatic Fails <span>2</span></a></li>" + "</ul>" + "</li>" +
		 * "<li class=\"item3\"><a href=\"#\">Авторы <span>340</span></a>" + "<ul>" + "<li class=\"subitem1\"><a href=\"#\">Cute Kittens <span>14</span></a></li>" + "<li class=\"subitem2\"><a href=\"#\">Strange “Stuff” <span>6</span></a></li>" +
		 * "<li class=\"subitem3\"><a href=\"#\">Automatic Fails <span>2</span></a></li>" + "</ul>" + "</li>" + "</ul>";
		 * 
		 * } };
		 */

		Window.addWindowScrollHandler(new ScrollHandler() {

			@Override
			public void onWindowScroll(ScrollEvent event) {

				if (event.getScrollTop() > 45) {
					Document.get().getElementById("search_panel").getStyle()
							.setPosition(Position.FIXED);
					Document.get().getElementById("search_panel").getStyle().setTop(0, Unit.PX);
				} else {
					Document.get().getElementById("search_panel").getStyle().setTop(45, Unit.PX);
					Document.get().getElementById("search_panel").getStyle()
							.setPosition(Position.ABSOLUTE);

				}

				// double bef = Double
				// .valueOf(Document.get().getElementById("cont_list").getStyle().getWidth());
				// double af = Double
				// .valueOf(Document.get().getElementById("wrapper").getStyle().getWidth());

				// if (bef < af) {
				// Document.get().getElementById("cont_list").getStyle()
				// .setHeight(Window.getScrollTop(), Unit.PX);
				// }

			}
		});

	}
}
