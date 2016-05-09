package com.librarybooks.client.activities_and_places.view;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.librarybooks.client.BookService;
import com.librarybooks.client.BookServiceAsync;
import com.librarybooks.client.activities_and_places.places.AdminPlace;
import com.librarybooks.client.objects.Author;
import com.librarybooks.client.objects.Book;
import com.librarybooks.client.objects.Genre;
import com.librarybooks.client.widgets.BookWidget;
import com.librarybooks.client.widgets.SelectedBookWidget;

public class UserViewImpl extends Composite implements UserView, ClickHandler {

	Presenter listener;
	String name;
	long id;
	String type;
	int page;
	int col_books = 12;
	int col_page;
	int start;
	int stop;
	Button button = new Button("send");
	HTML one = new HTML();
	HTML two = new HTML();
	HorizontalPanel hPanel = new HorizontalPanel();
	HorizontalPanel sprintHPanel = new HorizontalPanel();
	VerticalPanel vPanel = new VerticalPanel();
	FlowPanel fPanel = new FlowPanel();

	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	private final BookServiceAsync bookService = GWT.create(BookService.class);

	public UserViewImpl() {

		hPanel.add(one);
		one.setHTML("Hello, ");
		hPanel.add(two);
		hPanel.add(button);
		button.addClickHandler(this);
		vPanel.add(hPanel);
		vPanel.add(fPanel);
		vPanel.add(sprintHPanel);
		vPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		sprintHPanel.getElement().setId("sprint_page");
		initWidget(vPanel);
	}

	@Override
	public void setName(String name) {
		if (name.matches("all=[0-9]+&p=[1-9][0-9]*")) {
			type = "all";
			String a = (name.replaceAll("all=", "")).replaceAll("p=", "");
			id = Long.valueOf(a.substring(0, a.indexOf("&")));
			page = Integer.valueOf(a.substring(a.indexOf("&") + 1));
		}
		if (name.matches("author=[0-9]+&p=[1-9][0-9]*")) {
			type = "author";
			String a = (name.replaceAll("author=", "")).replaceAll("p=", "");
			id = Long.valueOf(a.substring(0, a.indexOf("&")));
			page = Integer.valueOf(a.substring(a.indexOf("&") + 1));
		}
		if (name.matches("genre=[0-9]+&p=[1-9][0-9]*")) {
			type = "genre";
			String a = (name.replaceAll("genre=", "")).replaceAll("p=", "");
			id = Long.valueOf(a.substring(0, a.indexOf("&")));
			page = Integer.valueOf(a.substring(a.indexOf("&") + 1));
		}
		if (name.matches("book=[0-9]+")) {
			type = "book";
			id = Long.valueOf(name.replaceAll("book=", ""));
		}
		two.setHTML(name + type + id);
		this.name = name;

		fPanel.clear();
		sprintHPanel.clear();
		switch (type) {
		case "all": {
			bookService.sendServer(new AsyncCallback<ArrayList<Book>>() {
				public void onFailure(Throwable caught) {
					Label text = new Label(SERVER_ERROR);
					fPanel.add(text);
				}

				public void onSuccess(ArrayList<Book> result) {
					pageNav(result.size(), type);
					FlowPanel panel = new FlowPanel();
					for (int i = start; i < stop; i++) {
						String title = new String((result.get(i)).getTitle());
						ArrayList<Author> author = new ArrayList<Author>(
								(result.get(i)).getAuthor());
						ArrayList<Genre> genre = new ArrayList<Genre>((result.get(i)).getGenre());
						String img_src = new String((result.get(i)).getImg());
						long id_book = (result.get(i)).getIdBook();
						BookWidget bb = new BookWidget(id_book, author, title, genre, img_src);
						panel.add(bb);
					}
					fPanel.add(panel);
				}
			});
			break;
		}

		case "author": {
			bookService.findBooksByAuthorBook(id, new AsyncCallback<ArrayList<Book>>() {
				public void onFailure(Throwable caught) {
					Label text = new Label(SERVER_ERROR);
					fPanel.add(text);
				}

				public void onSuccess(ArrayList<Book> result) {

					pageNav(result.size(), type);
					FlowPanel panel = new FlowPanel();
					for (int i = start; i < stop; i++) {

						String title = new String((result.get(i)).getTitle());
						ArrayList<Author> author = new ArrayList<Author>(
								(result.get(i)).getAuthor());
						ArrayList<Genre> genre = new ArrayList<Genre>((result.get(i)).getGenre());
						String img_src = new String((result.get(i)).getImg());
						long id_book = (result.get(i)).getIdBook();
						BookWidget bb = new BookWidget(id_book, author, title, genre, img_src);
						panel.add(bb);

					}
					fPanel.add(panel);
				}
			});
			break;
		}

		case "genre": {
			bookService.findBooksByGenreBook(id, new AsyncCallback<ArrayList<Book>>() {
				public void onFailure(Throwable caught) {
					Label text = new Label(SERVER_ERROR);
					fPanel.add(text);
				}

				public void onSuccess(ArrayList<Book> result) {
					pageNav(result.size(), type);
					FlowPanel panel = new FlowPanel();
					for (int i = start; i < stop; i++) {

						String title = new String((result.get(i)).getTitle());
						ArrayList<Author> author = new ArrayList<Author>(
								(result.get(i)).getAuthor());
						ArrayList<Genre> genre = new ArrayList<Genre>((result.get(i)).getGenre());
						String img_src = new String((result.get(i)).getImg());
						long id_book = (result.get(i)).getIdBook();
						BookWidget bb = new BookWidget(id_book, author, title, genre, img_src);
						panel.add(bb);

					}
					fPanel.add(panel);
				}
			});
		}
			break;
		case "book": {
			bookService.selectBook(id, new AsyncCallback<Book>() {
				public void onFailure(Throwable caught) {
					Label text = new Label(SERVER_ERROR);
					fPanel.add(text);
				}

				public void onSuccess(Book result) {
					FlowPanel panel = new FlowPanel();
					String title = new String(result.getTitle());
					ArrayList<Author> author = new ArrayList<Author>(result.getAuthor());
					ArrayList<Genre> genre = new ArrayList<Genre>(result.getGenre());
					String img_src = new String(result.getImg());
					long id_book = result.getIdBook();
					SelectedBookWidget bb = new SelectedBookWidget(id_book, author, title, genre,
							img_src);
					panel.add(bb);
					fPanel.add(panel);

				}

			});
		}
			break;
		default:
			break;
		}

	}

	private void pageNav(int res, String type) {
		col_page = res / 12;
		if (res % 12 > 0)
			col_page++;
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
				HTML num_page = new HTML("<a id=\"col_page\" href=\"#UserPlace:" + type + "=" + id
						+ "&p=" + i + "\">" + i + "</a>");
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

	}

	@Override
	public void setPresenter(Presenter listener) {
		this.listener = listener;
	}

	@Override
	public void onClick(ClickEvent event) {
		listener.goTo(new AdminPlace(name));
	}
}