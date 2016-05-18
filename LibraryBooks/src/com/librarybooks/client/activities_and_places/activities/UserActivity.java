package com.librarybooks.client.activities_and_places.activities;

import java.util.ArrayList;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.librarybooks.client.BookService;
import com.librarybooks.client.BookServiceAsync;
import com.librarybooks.client.ClientFactory;
import com.librarybooks.client.activities_and_places.places.UserPlace;
import com.librarybooks.client.activities_and_places.view.UserView;
import com.librarybooks.client.objects.Book;

public class UserActivity extends AbstractActivity implements UserView.Presenter {

	private ClientFactory clientFactory;
	private String info;
	private UserView userView;

	private long id;
	private String param;
	private String[] options = { "all", "author", "genre", "selection", "book", "search" };
	private ArrayList<String> search_param = new ArrayList<String>();
	private String type;
	private int page;
	private int col_books = 12;
	private int col_page;
	private int start;
	private int stop;

	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	private final BookServiceAsync bookService = GWT.create(BookService.class);

	public UserActivity(UserPlace place, ClientFactory clientFactory) {
		this.info = place.getParam();
		this.clientFactory = clientFactory;
		parsing(info, options);

	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {

		userView = clientFactory.getUserView();

		showView(type);
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

	private void parsing(String ref, String[] selected_option) {

		for (String option : selected_option) {
			if (ref.matches(option + "=[0-9]+&p=[1-9][0-9]*")) {
				this.type = option;
				this.param = ref.replaceAll(ref.substring(ref.indexOf("&p=")), "");
				String a = (ref.replaceAll(option + "=", "")).replaceAll("p=", "");
				this.id = Long.valueOf(a.substring(0, a.indexOf("&")));
				this.page = Integer.valueOf(a.substring(a.indexOf("&") + 1));
			} else {
				if (option.equals("book")) {
					if (ref.matches(option + "=[0-9]+")) {
						this.type = option;
						this.id = Long.valueOf(ref.replaceAll(option + "=", ""));
					}
				} else {
					if (option.equals("search")) {
						if (ref.matches(option + "=.+[\u005F.+]*&p=[1-9][0-9]*")) {
							this.type = option;
							this.param = ref.replaceAll(ref.substring(ref.indexOf("&p=")), "");
							this.page = Integer.valueOf(ref.substring(ref.indexOf("&p=") + 3));
							String s = ref.replaceAll(ref.substring(ref.indexOf("&p=")), "")
									.replaceAll("search" + "=", "").replace("\u005F", " ").trim();
							String str[] = s.split(" ");
							for (int i = 0; i < str.length; i++)
								search_param.add(str[i]);

						}
					}
				}
			}
		}

	}

	private void showView(String type) {

		switch (type) {
		case "all":
			bookService.sendServer(new AsyncCallback<ArrayList<Book>>() {
				public void onFailure(Throwable caught) {
					ChangeViewERROR();
				}

				public void onSuccess(ArrayList<Book> books) {
					ChangeViewBooksList(books);
				}
			});
			break;

		case "author":
			bookService.findBooksByAuthorBook(id, new AsyncCallback<ArrayList<Book>>() {

				public void onFailure(Throwable caught) {
					ChangeViewERROR();
				}

				public void onSuccess(ArrayList<Book> books) {
					ChangeViewBooksList(books);
				}
			});
			break;

		case "genre":
			bookService.findBooksByGenreBook(id, new AsyncCallback<ArrayList<Book>>() {
				public void onFailure(Throwable caught) {
					ChangeViewERROR();
				}

				public void onSuccess(ArrayList<Book> books) {
					ChangeViewBooksList(books);
				}
			});
			break;

		case "selection":
			bookService.findBooksBySelectionBook(id, new AsyncCallback<ArrayList<Book>>() {
				public void onFailure(Throwable caught) {
					ChangeViewERROR();
				}

				public void onSuccess(ArrayList<Book> books) {
					ChangeViewBooksList(books);
				}
			});
			break;

		case "book":
			bookService.selectBook(id, new AsyncCallback<Book>() {
				public void onFailure(Throwable caught) {
					ChangeViewERROR();
				}

				public void onSuccess(Book book) {
					ChangeViewBook(book);
				}
			});
			break;

		case "search":
			bookService.searchBooks(search_param, new AsyncCallback<ArrayList<Book>>() {
				public void onFailure(Throwable caught) {
					ChangeViewERROR();
				}

				public void onSuccess(ArrayList<Book> books) {
					consoleLog(String.valueOf(books.size()));
					if (books.size() > 1)
						ChangeViewBooksList(books);
					else {
						if (books.size() == 1)
							ChangeViewBook(books.get(0));
						else {
							userView.setView(
									"К сожалению, с такими параметрами не найдено ни одной книги");
						}
					}
				}
			});
			break;

		default:
			break;
		}

	}

	private void ChangeViewBooksList(ArrayList<Book> books) {
		pageNav(books.size());
		userView.setView(new ArrayList<Book>(books.subList(start, stop)), col_page, page, type,
				param);
	}

	private void ChangeViewBook(Book book) {
		userView.setView(book);
	}

	private void ChangeViewERROR() {

	}

	private void pageNav(int res) {
		col_page = res / 12;
		if (res % 12 > 0)
			col_page++;
		if (col_page > 1) {
			this.start = (page - 1) * col_books;
			if (res <= col_books * page) {
				this.stop = res;
			} else
				this.stop = page * col_books;
		} else {
			this.stop = res;
			this.start = 0;
		}

	}

	native void consoleLog(String message) /*-{
		console.log("me:" + message);
	}-*/;
}
