package com.librarybooks.client.activities_and_places.activities;

import java.util.ArrayList;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.librarybooks.client.BookService;
import com.librarybooks.client.BookServiceAsync;
import com.librarybooks.client.ClientFactory;
import com.librarybooks.client.activities_and_places.places.AdminPlace;
import com.librarybooks.client.activities_and_places.view.AdminView;
import com.librarybooks.client.objects.Book;
import com.librarybooks.client.objects.BookEdit;

public class AdminActivity extends AbstractActivity implements AdminView.Presenter {
	private ClientFactory clientFactory;
	private String info;
	private AdminView userView;
	BookEdit book;
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	private final BookServiceAsync bookService = GWT.create(BookService.class);

	public AdminActivity(AdminPlace place, ClientFactory clientFactory) {
		this.info = place.getParam();
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {

		userView = clientFactory.getAdminView();
		if (info.equals("add")) {
			userView.setAddView(info);
			userView.getAddBook().getButton().addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					book = userView.getAddBook().getBookEdt();
					if (book != null) {
						bookService.addBook(book, new AsyncCallback<Void>() {

							public void onFailure(Throwable caught) {
								// ChangeViewERROR();
							}

							@Override
							public void onSuccess(Void result) {
								// TODO Auto-generated method stub
							}
						});
					}

				}
			});
		} else if (info.equals("change")) {
			bookService.sendServer(new AsyncCallback<ArrayList<Book>>() {

				public void onFailure(Throwable caught) {
					// ChangeViewERROR();
				}

				@Override
				public void onSuccess(ArrayList<Book> books) {
					// TODO Auto-generated method stub
					userView.setChangeView(info, books, bookService);
				}
			});

		}
		userView.setPresenter(this);
		containerWidget.setWidget(userView.asWidget());
	}

	@Override
	public String mayStop() {
		return null;
	}

	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}

	native void consoleLog(String message) /*-{
		console.log("me:" + message);
	}-*/;
}