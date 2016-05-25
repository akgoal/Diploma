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
import com.google.gwt.user.client.History;
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
	private UserPlace place;
	private String info;
	private Book basket;
	private UserView userView;

	private long id;
	private String param;
	private String[] options = { "reg", "all", "author", "genre", "search", "selection", "book",
			"new", "popular", "classic", "child", "foreign" };
	private ArrayList<String> search_param = new ArrayList<String>();
	private String type;
	private int page;
	private int col_books = 12;
	private int col_page;
	private int start;
	private int stop;
	private String title;

	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	private final BookServiceAsync bookService = GWT.create(BookService.class);

	public UserActivity(UserPlace place, ClientFactory clientFactory) {
		this.place = place;
		this.info = place.getParam();
		// place.setParam1("111");
		this.basket = place.getParamBasket();
		this.clientFactory = clientFactory;
		// clientFactory.getPlaceController().
		parsing(info, options);

	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {

		userView = clientFactory.getUserView();
		showView(place, type);
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
			switch (option) {
			case "reg":
				if (ref.matches(option)) {
					this.type = option;
				}
				break;
			case "new":
			case "popular":
			case "classic":
			case "child":
			case "foreign":
				if (ref.matches(option + "&p=[1-9][0-9]*")) {
					this.type = option;
					this.param = ref.replaceAll(ref.substring(ref.indexOf("&p=")), "");
					String a = (ref.replaceAll(option, "")).replaceAll("p=", "");
					this.page = Integer.valueOf(a.substring(a.indexOf("&") + 1));
				}
				break;
			case "search":
				if (ref.matches(option
						+ "=[0-9A-Za-z/а-яёА-ЯЁ/]+(\u005F[0-9A-Za-z/а-яёА-ЯЁ/]+)*&p=[1-9][0-9]*")) {
					this.type = option;
					this.param = ref.replaceAll(ref.substring(ref.indexOf("&p=")), "");
					this.page = Integer.valueOf(ref.substring(ref.indexOf("&p=") + 3));
					String s = ref.replaceAll(ref.substring(ref.indexOf("&p=")), "")
							.replaceAll("search" + "=", "").replace("\u005F", " ").trim();
					consoleLog(s);
					String str[] = s.split(" ");
					consoleLog(str.length + "");
					for (int i = 0; i < str.length; i++)
						search_param.add(str[i]);
				}
				break;
			case "book":
				if (ref.matches(option + "=[0-9]+")) {
					this.type = option;
					this.id = Long.valueOf(ref.replaceAll(option + "=", ""));
				}
				break;
			case "all":
			case "author":
			case "genre":
			case "selection":
				if (ref.matches(option + "=[0-9]+&p=[1-9][0-9]*")) {
					this.type = option;
					this.param = ref.replaceAll(ref.substring(ref.indexOf("&p=")), "");
					String a = (ref.replaceAll(option + "=", "")).replaceAll("p=", "");
					this.id = Long.valueOf(a.substring(0, a.indexOf("&")));
					this.page = Integer.valueOf(a.substring(a.indexOf("&") + 1));
				}
				break;
			default:
				break;
			}
		}

	}

	private void showView(UserPlace place, String type) {

		switch (type) {
		case "reg":
			ChangeViewReg();
			break;
		case "all":
			bookService.sendServer(new AsyncCallback<ArrayList<Book>>() {
				public void onFailure(Throwable caught) {
					ChangeViewERROR();
				}

				public void onSuccess(ArrayList<Book> books) {
					ChangeViewBooksList(books, null);
				}
			});
			break;

		case "author":
			bookService.titleByIdAuthor(id, new AsyncCallback<String>() {
				public void onFailure(Throwable caught) {
					ChangeViewERROR();
				}

				public void onSuccess(String res) {
					title = "<h3>Книги автора \"" + res + "\"</h3>";
					bookService.findBooksByAuthorBook(id, new AsyncCallback<ArrayList<Book>>() {

						public void onFailure(Throwable caught) {
							ChangeViewERROR();
						}

						public void onSuccess(ArrayList<Book> books) {
							title = title + "<h5>Колличество книг: " + books.size() + "</h5>";
							ChangeViewBooksList(books, title);
						}
					});
				}
			});

			break;

		case "genre":
			bookService.titleByIdGenre(id, new AsyncCallback<String>() {
				public void onFailure(Throwable caught) {
					ChangeViewERROR();
				}

				public void onSuccess(String res) {
					title = "<h3>Книги жанра \"" + res + "\"</h3>";
					bookService.findBooksByGenreBook(id, new AsyncCallback<ArrayList<Book>>() {
						public void onFailure(Throwable caught) {
							ChangeViewERROR();
						}

						public void onSuccess(ArrayList<Book> books) {
							title = title + "<h5>Колличество книг: " + books.size() + "</h5>";
							ChangeViewBooksList(books, title);
						}
					});
				}
			});

			break;

		case "selection":
			bookService.titleByIdSelection(id, new AsyncCallback<String>() {
				public void onFailure(Throwable caught) {
					ChangeViewERROR();
				}

				public void onSuccess(String res) {
					title = "<h3>Книги из подборки \"" + res + "\"</h3>";
					bookService.findBooksBySelectionBook(id, new AsyncCallback<ArrayList<Book>>() {
						public void onFailure(Throwable caught) {
							ChangeViewERROR();
						}

						public void onSuccess(ArrayList<Book> books) {
							title = title + "<h5>Колличество книг: " + books.size() + "</h5>";
							ChangeViewBooksList(books, title);
						}
					});
				}
			});

			break;

		case "new":
			bookService.listNew(new AsyncCallback<ArrayList<Book>>() {
				public void onFailure(Throwable caught) {
					ChangeViewERROR();
				}

				public void onSuccess(ArrayList<Book> books) {
					title = "<h2>Новинки</h2>";
					title = title + "<h5>Колличество книг: " + books.size() + "</h5>";
					ChangeViewBooksList(books, title);
				}
			});
			break;

		case "popular":
			bookService.listPopular(new AsyncCallback<ArrayList<Book>>() {
				public void onFailure(Throwable caught) {
					ChangeViewERROR();
				}

				public void onSuccess(ArrayList<Book> books) {
					title = "<h2>Популярные книги</h2>";
					title = title + "<h5>Колличество книг: " + books.size() + "</h5>";
					ChangeViewBooksList(books, title);
				}
			});
			break;

		case "classic":
			bookService.listClassic(new AsyncCallback<ArrayList<Book>>() {
				public void onFailure(Throwable caught) {
					ChangeViewERROR();
				}

				public void onSuccess(ArrayList<Book> books) {
					title = "<h2>Классическая литература</h2>";
					title = title + "<h5>Колличество книг: " + books.size() + "</h5>";
					ChangeViewBooksList(books, title);
				}
			});
			break;

		case "child":
			bookService.listChild(new AsyncCallback<ArrayList<Book>>() {
				public void onFailure(Throwable caught) {
					ChangeViewERROR();
				}

				public void onSuccess(ArrayList<Book> books) {
					title = "<h2>Книги для детей</h2>";
					title = title + "<h5>Колличество книг: " + books.size() + "</h5>";
					ChangeViewBooksList(books, title);
				}
			});
			break;

		case "foreign":
			bookService.listForeign(new AsyncCallback<ArrayList<Book>>() {
				public void onFailure(Throwable caught) {
					ChangeViewERROR();
				}

				public void onSuccess(ArrayList<Book> books) {
					title = "<h2>Зарубежная литература</h2>";
					title = title + "<h5>Колличество книг: " + books.size() + "</h5>";
					ChangeViewBooksList(books, title);
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
					consoleLog(String.valueOf(search_param.size()));
					consoleLog(String.valueOf(books.size()));
					String title = "<h3>Поиск по \"";
					for (String param : search_param)
						title = title + param + " ";
					title = title + "\"</h3><h5>Колличество книг: " + books.size() + "</h5>";
					if (books.size() > 1)
						ChangeViewBooksList(books, title);
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

	private void ChangeViewBooksList(ArrayList<Book> books, String title) {
		pageNav(books.size());
		userView.setView(place, new ArrayList<Book>(books.subList(start, stop)), col_page, page,
				type, param, title);
	}

	private void ChangeViewReg() {
		userView.setViewReg();
	}

	private void ChangeViewBook(Book book) {
		userView.setView(bookService, place, book);
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
