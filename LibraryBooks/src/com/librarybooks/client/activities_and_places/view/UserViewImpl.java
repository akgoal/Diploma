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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.librarybooks.client.BookService;
import com.librarybooks.client.BookServiceAsync;
import com.librarybooks.client.activities_and_places.places.AdminPlace;
import com.librarybooks.client.objects.Book;
import com.librarybooks.client.objects.Genre;
import com.librarybooks.client.widgets.BookWidget;
import com.librarybooks.client.widgets.SelectedBookWidget;

public class UserViewImpl extends Composite implements UserView, ClickHandler {

	Presenter listener;
	String name;
	long id;
	String type;
	Button button = new Button("send");
	HTML one = new HTML();
	HTML two = new HTML();
	HorizontalPanel hPanel = new HorizontalPanel();
	VerticalPanel vPanel = new VerticalPanel();
	FlowPanel fPanel = new FlowPanel();

	private static final String SERVER_ERROR = "An error occurred while " + "attempting to contact the server. Please check your network " + "connection and try again.";

	private final BookServiceAsync bookService = GWT.create(BookService.class);

	public UserViewImpl() {

		hPanel.add(one);
		one.setHTML("Hello, ");
		hPanel.add(two);
		hPanel.add(button);
		button.addClickHandler(this);
		vPanel.add(hPanel);
		vPanel.add(fPanel);
		initWidget(vPanel);
	}

	@Override
	public void setName(String name) {
		if (name.equals("all")) {
			type = "all";
		}
		if (name.matches("author=[0-9]+")) {
			type = "author";
			id = Long.valueOf(name.replaceAll("author=", ""));
		}
		if (name.matches("genre=[0-9]+")) {
			type = "genre";
			id = Long.valueOf(name.replaceAll("genre=", ""));
		}
		if (name.matches("book=[0-9]+")) {
			type = "book";
			id = Long.valueOf(name.replaceAll("book=", ""));
		}
		two.setHTML(name + type + id);
		this.name = name;
		fPanel.clear();
		switch (type) {
		case "all": {
			bookService.sendServer(new AsyncCallback<ArrayList<Book>>() {
				public void onFailure(Throwable caught) {
					Label text = new Label(SERVER_ERROR);
					fPanel.add(text);
				}

				public void onSuccess(ArrayList<Book> result) {
					FlowPanel panel = new FlowPanel();
					for (int i = 0; i < result.size(); i++) {

						String author = new String((result.get(i)).getAuthor());
						String title = new String((result.get(i)).getTitle());
						ArrayList<Genre> genre = new ArrayList<Genre>((result.get(i)).getGenre());
						String img_src = new String((result.get(i)).getImg());
						long id_author = (result.get(i)).getIdAuthor();
						long id_book = (result.get(i)).getIdBook();
						BookWidget bb = new BookWidget(id_book, author, id_author, title, genre, img_src);
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
					FlowPanel panel = new FlowPanel();
					for (int i = 0; i < result.size(); i++) {

						String author = new String((result.get(i)).getAuthor());
						String title = new String((result.get(i)).getTitle());
						ArrayList<Genre> genre = new ArrayList<Genre>((result.get(i)).getGenre());
						String img_src = new String((result.get(i)).getImg());
						long id_author = (result.get(i)).getIdAuthor();
						long id_book = (result.get(i)).getIdBook();
						BookWidget bb = new BookWidget(id_book, author, id_author, title, genre, img_src);
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
					FlowPanel panel = new FlowPanel();
					for (int i = 0; i < result.size(); i++) {

						String author = new String((result.get(i)).getAuthor());
						String title = new String((result.get(i)).getTitle());
						ArrayList<Genre> genre = new ArrayList<Genre>((result.get(i)).getGenre());
						String img_src = new String((result.get(i)).getImg());
						long id_author = (result.get(i)).getIdAuthor();
						long id_book = (result.get(i)).getIdBook();
						BookWidget bb = new BookWidget(id_book, author, id_author, title, genre, img_src);
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
					String author = new String(result.getAuthor());
					String title = new String(result.getTitle());
					ArrayList<Genre> genre = new ArrayList<Genre>(result.getGenre());
					String img_src = new String(result.getImg());
					long id_author = result.getIdAuthor();
					long id_book = result.getIdBook();
					SelectedBookWidget bb = new SelectedBookWidget(id_book, author, id_author, title, genre, img_src);
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

	@Override
	public void setPresenter(Presenter listener) {
		this.listener = listener;
	}

	@Override
	public void onClick(ClickEvent event) {
		listener.goTo(new AdminPlace(name));
	}
}