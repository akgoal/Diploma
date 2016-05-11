package com.librarybooks.client.activities_and_places.view;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.librarybooks.client.BookService;
import com.librarybooks.client.BookServiceAsync;
import com.librarybooks.client.activities_and_places.places.AdminPlace;
import com.librarybooks.client.objects.Author;
import com.librarybooks.client.objects.Book;
import com.librarybooks.client.objects.Genre;
import com.librarybooks.client.objects.Selection;
import com.librarybooks.client.widgets.BookWidget;
import com.librarybooks.client.widgets.SelectedBookWidget;

public class UserViewImpl extends Composite implements UserView, ClickHandler {

	private static UserViewImplUiBinder uiBinder = GWT.create(UserViewImplUiBinder.class);

	interface UserViewImplUiBinder extends UiBinder<Widget, UserViewImpl> {
	}

	Presenter listener;
	String ref;
	String html;
	long id;
	String[] options = { "all", "author", "genre", "selection", "book" };
	String type;
	int page;
	int col_books = 12;
	int col_page;
	int start;
	int stop;

	@UiField
	Button button;
	@UiField
	HTML one;
	@UiField
	HTML two;
	@UiField
	HTML navig;
	@UiField
	HTMLPanel menuBar;
	@UiField
	HorizontalPanel hPanel;
	@UiField
	HorizontalPanel sprintHPanel;
	@UiField
	VerticalPanel vPanel;
	@UiField
	FlowPanel fPanel;

	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	private final BookServiceAsync bookService = GWT.create(BookService.class);

	public UserViewImpl() {

		initWidget(uiBinder.createAndBindUi(this));

		// one.setHTML("Hello, ");

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
		menuBar.add(menuMain);

		vPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		sprintHPanel.getElement().setId("sprint_page");
		bookService.listOfGenres(new AsyncCallback<ArrayList<Genre>>() {
			public void onFailure(Throwable caught) {
				navig.setHTML("<p>" + SERVER_ERROR + "</p>");
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
								navig.setHTML(html);
								scr();
							}
						});
					}
				});

			}
		});

		// initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setView(String ref) {

		parsing(ref, options);

		two.setHTML(ref + type + id);
		this.ref = ref;

		showView(type);

	}

	private void parsing(String ref, String[] selected_option) {

		for (String option : selected_option) {
			if (ref.matches(option + "=[0-9]+&p=[1-9][0-9]*")) {
				type = option;
				String a = (ref.replaceAll(option + "=", "")).replaceAll("p=", "");
				id = Long.valueOf(a.substring(0, a.indexOf("&")));
				page = Integer.valueOf(a.substring(a.indexOf("&") + 1));
			} else {
				if (option.equals("book")) {
					if (ref.matches(option + "=[0-9]+")) {
						type = option;
						id = Long.valueOf(ref.replaceAll(option + "=", ""));
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

		default:
			break;
		}

	}

	private void ChangeViewBooksList(ArrayList<Book> books) {
		fPanel.clear();
		sprintHPanel.clear();
		pageNav(books.size(), type);
		FlowPanel panel = new FlowPanel();
		for (int i = start; i < stop; i++) {

			String title = new String((books.get(i)).getTitle());
			ArrayList<Author> author = new ArrayList<Author>((books.get(i)).getAuthor());
			ArrayList<Genre> genre = new ArrayList<Genre>((books.get(i)).getGenre());
			String img_src = new String((books.get(i)).getImg());
			long id_book = (books.get(i)).getIdBook();
			BookWidget bb = new BookWidget(id_book, author, title, genre, img_src);
			panel.add(bb);

		}
		fPanel.add(panel);
	}

	private void ChangeViewBook(Book book) {
		fPanel.clear();
		sprintHPanel.clear();
		FlowPanel panel = new FlowPanel();
		String title = new String(book.getTitle());
		ArrayList<Author> author = new ArrayList<Author>(book.getAuthor());
		ArrayList<Genre> genre = new ArrayList<Genre>(book.getGenre());
		String img_src = new String(book.getImg());
		long id_book = book.getIdBook();
		SelectedBookWidget bb = new SelectedBookWidget(id_book, author, title, genre, img_src);
		panel.add(bb);
		fPanel.add(panel);
	}

	private void ChangeViewERROR() {
		fPanel.clear();
		sprintHPanel.clear();
		Label text = new Label(SERVER_ERROR);
		fPanel.add(text);
	}

	private void pageNav(int res, String type) {
		col_page = res / 12;
		if (res % 12 > 0)
			col_page++;
		if (col_page > 1) {
			if (page == 1) {
				HTML num_page = new HTML("<a id=\"col_page\" class=\"active\">" + "<" + "</a>");
				sprintHPanel.add(num_page);
			} else {
				HTML num_page = new HTML("<a id=\"col_page\" href=\"#UserPlace:" + type + "=" + id
						+ "&p=" + (page - 1) + "\">" + "<" + "</a>");
				sprintHPanel.add(num_page);
			}
			for (int i = 1; i <= col_page; i++) {
				if (i == page) {
					HTML num_page = new HTML("<a id=\"col_page\" class=\"active\" >" + i + "</a>");
					sprintHPanel.add(num_page);
				} else {
					HTML num_page = new HTML("<a id=\"col_page\" href=\"#UserPlace:" + type + "="
							+ id + "&p=" + i + "\">" + i + "</a>");
					sprintHPanel.add(num_page);
				}
			}
			if (page == col_page) {
				HTML num_page = new HTML("<a id=\"col_page\" class=\"active\">" + ">" + "</a>");
				sprintHPanel.add(num_page);
			} else {
				HTML num_page = new HTML("<a id=\"col_page\" href=\"#UserPlace:" + type + "=" + id
						+ "&p=" + (page + 1) + "\">" + ">" + "</a>");
				sprintHPanel.add(num_page);
			}
			start = (page - 1) * col_books;
			if (res <= col_books * page) {
				stop = res;
			} else
				stop = page * col_books;
		} else {
			stop = res;
			start = 0;
		}

	}

	public String li(String type, String name, long id, long col) {

		return "<li class=\"subitem\"><a href=\"#UserPlace:" + type + "=" + id + "&p=1\">" + name
				+ " <span>" + col + "</span></a></li>";

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

	@Override
	public void setPresenter(Presenter listener) {
		this.listener = listener;
	}

	@UiHandler("button")
	public void onClick(ClickEvent event) {
		listener.goTo(new AdminPlace(ref));
	}
}