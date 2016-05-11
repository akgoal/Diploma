package com.librarybooks.client;

import com.librarybooks.client.AppActivityMapper;
import com.librarybooks.client.AppPlaceHistoryMapper;
import com.librarybooks.client.ClientFactory;
import com.librarybooks.client.activities_and_places.places.*;
import com.librarybooks.client.objects.Author;
import com.librarybooks.client.objects.Genre;
import com.librarybooks.client.objects.Selection;

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

	private static final BookServiceAsync bookService = GWT.create(BookService.class);

	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	private Place defaultPlace = new UserPlace("all=0&p=1");
	private SimplePanel appWidget = new SimplePanel();
	String html;

	public void onModuleLoad() {

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
				RootPanel.get("wrapper").add(new HTML("<p>" + SERVER_ERROR + "</p>"));
			}

			public void onSuccess(ArrayList<Genre> result) {
				html = "<ul class=\"menu\">";
				html = html.concat("<li class=\"item1\"><a href=\"#\">Жанры <span>" + result.size()
						+ "</span></a>" + "<ul>");
				for (int i = 0; i < result.size(); i++) {
					html = html.concat(li("genre", result.get(i).getGenre(),
							result.get(i).getIdGenre(), result.get(i).getColBook()));
				}
				html = html.concat("</ul> </li>");
				bookService.listOfAuthors(new AsyncCallback<ArrayList<Author>>() {
					public void onFailure(Throwable caught) {
						Label text = new Label(SERVER_ERROR);
					}

					public void onSuccess(ArrayList<Author> result) {
						html = html.concat("<li class=\"item2\"><a href=\"#\">Авторы <span>"
								+ result.size() + "</span></a>" + "<ul>");

						for (int i = 0; i < result.size(); i++) {
							html = html.concat(li("author", result.get(i).getAuthor(),
									result.get(i).getIdAuthor(), result.get(i).getColBook()));
						}
						html = html.concat("</ul> </li>");

						bookService.listOfSelections(new AsyncCallback<ArrayList<Selection>>() {
							public void onFailure(Throwable caught) {
								Label text = new Label(SERVER_ERROR);
							}

							public void onSuccess(ArrayList<Selection> result) {
								html = html
										.concat("<li class=\"item3\"><a href=\"#\">Подборки <span>"
												+ result.size() + "</span></a>" + "<ul>");

								for (int i = 0; i < result.size(); i++) {
									html = html.concat(li("selection", result.get(i).getSelection(),
											result.get(i).getIdSelection(),
											result.get(i).getColBook()));
								}
								html = html.concat("</ul> </li> </ul>");
								RootPanel.get("wrapper").add(new HTML(html));
								scr();

							}
						});
					}
				});
			}
		});

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
			}
		});

	}

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

	public String li(String type, String name, long id, long col) {

		return "<li class=\"subitem\"><a href=\"#UserPlace:" + type + "=" + id + "&p=1\">" + name
				+ " <span>" + col + "</span></a></li>";

	}
}
