package com.librarybooks.client.activities_and_places.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.librarybooks.client.BookService;
import com.librarybooks.client.BookServiceAsync;
import com.librarybooks.client.activities_and_places.places.AdminPlace;
import com.librarybooks.client.objects.Author;
import com.librarybooks.client.objects.Book;
import com.librarybooks.client.objects.Genre;
import com.librarybooks.client.objects.Selection;
import com.librarybooks.client.widgets.Basket;
import com.librarybooks.client.widgets.BookWidget;
import com.librarybooks.client.widgets.SearchPane;
import com.librarybooks.client.widgets.SelectedBookWidget;

public class UserViewImpl extends Composite implements UserView, ClickHandler {

	private static UserViewImplUiBinder uiBinder = GWT.create(UserViewImplUiBinder.class);

	interface UserViewImplUiBinder extends UiBinder<Widget, UserViewImpl> {
	}

	Presenter listener;
	long id;
	String[] options = { "all", "author", "genre", "selection", "book" };
	int page;
	int col_books = 12;
	int col_page;
	int start;
	int stop;
	Book bookInWidget = new Book();

	@UiField
	HTMLPanel searchPanel;
	@UiField
	DivElement textElement;
	@UiField
	HTMLPanel menuBar;
	@UiField
	HorizontalPanel titlePanel;
	@UiField
	HorizontalPanel hPanel;
	@UiField
	HorizontalPanel sprintHPanel;
	@UiField
	VerticalPanel vPanel;
	@UiField
	FlowPanel fPanel;
	@UiField
	LIElement ligenre;
	@UiField
	LIElement liauthor;
	@UiField
	LIElement liselection;

	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	private final BookServiceAsync bookService = GWT.create(BookService.class);

	final SearchPane sp = new SearchPane();
	final Basket basket = new Basket();
	BookWidget bb;

	public UserViewImpl() {

		initWidget(uiBinder.createAndBindUi(this));
		searchPanel.getElement().setId("search_panel");
		basket.setStyleName("basket");
		searchPanel.add(basket);
		searchPanel.add(sp);
		textElement.setInnerHTML(
				"<a href=\"#UserPlace:all=0&p=1\"><img src=\"img/logo_mini.png\"></img><h1>LIBRARY BOOK</h1></a>");
		basket.getSpanElement().setInnerText("0");
		Command command = new Command() {
			public void execute() {
				Window.alert("Command Fired");
			}
		};
		MenuBar menuMain = new MenuBar();
		menuMain.addItem("Home", true, command);
		menuMain.addItem("One", true, command);
		menuMain.addItem("Two", true, command);
		menuMain.addItem("Other", true, command);
		menuBar.add(menuMain);

		vPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		hPanel.getElement().setId("sprint_top_page");
		sprintHPanel.getElement().setId("sprint_page");
		leftNav();
	}

	@Override
	public SearchPane getSearchPane() {
		return sp;
	}

	@Override
	public Basket getBasket() {
		return basket;
	}

	public void leftNav() {
		bookService.listOfGenres(new AsyncCallback<ArrayList<Genre>>() {
			public void onFailure(Throwable caught) {
				ligenre.setInnerHTML("<p>" + SERVER_ERROR + "</p>");
			}

			public void onSuccess(ArrayList<Genre> result) {
				// Window.alert("up");
				Collections.sort(result, new Comparator<Genre>() {
					@Override
					public int compare(Genre o1, Genre o2) {
						return o1.getGenre().compareToIgnoreCase(o2.getGenre());
					}
				});
				ligenre.setInnerHTML("<a href=\"#\">Жанры <span>" + result.size() + "</span></a>");
				UListElement ul = Document.get().createULElement();
				for (int i = 0; i < result.size(); i++) {
					LIElement li = Document.get().createLIElement();
					li.setInnerHTML(li("genre", result.get(i).getGenre(),
							result.get(i).getIdGenre(), result.get(i).getColBook()));
					ul.appendChild(li);
				}
				ligenre.appendChild(ul);
				bookService.listOfAuthors(new AsyncCallback<ArrayList<Author>>() {
					public void onFailure(Throwable caught) {
						// Label text = new Label(SERVER_ERROR);
					}

					public void onSuccess(ArrayList<Author> result) {
						Collections.sort(result, new Comparator<Author>() {
							@Override
							public int compare(Author o1, Author o2) {
								return o1.getAuthor().compareToIgnoreCase(o2.getAuthor());
							}
						});
						liauthor.setInnerHTML(
								"<a href=\"#\">Авторы <span>" + result.size() + "</span></a>");
						UListElement ul = Document.get().createULElement();
						for (int i = 0; i < result.size(); i++) {
							LIElement li = Document.get().createLIElement();
							li.setInnerHTML(li("author", result.get(i).getAuthor(),
									result.get(i).getIdAuthor(), result.get(i).getColBook()));
							ul.appendChild(li);
						}
						liauthor.appendChild(ul);
						bookService.listOfSelections(new AsyncCallback<ArrayList<Selection>>() {
							public void onFailure(Throwable caught) {
								// Label text = new Label(SERVER_ERROR);
							}

							public void onSuccess(ArrayList<Selection> result) {
								Collections.sort(result, new Comparator<Selection>() {
									@Override
									public int compare(Selection o1, Selection o2) {
										return o1.getSelection()
												.compareToIgnoreCase(o2.getSelection());
									}
								});
								liselection.setInnerHTML("<a href=\"#\">Подборки <span>"
										+ result.size() + "</span></a>");
								UListElement ul = Document.get().createULElement();
								for (int i = 0; i < result.size(); i++) {
									LIElement li = Document.get().createLIElement();
									li.setInnerHTML(li("selection", result.get(i).getSelection(),
											result.get(i).getIdSelection(),
											result.get(i).getColBook()));
									ul.appendChild(li);
								}
								liselection.appendChild(ul);
								scr();
							}
						});
					}
				});

			}
		});
	}

	@Override
	public void setView(ArrayList<Book> books, int col_page, int page, String type, String param,
			String html_title) {

		fPanel.clear();
		sprintHPanel.clear();
		hPanel.clear();
		titlePanel.clear();
		if (html_title != null)
			titlePanel.add(new HTML(html_title));

		pageNav(col_page, page, param);
		FlowPanel panel = new FlowPanel();
		for (int i = 0; i < books.size(); i++) {
			String title = new String((books.get(i)).getTitle());
			ArrayList<Author> author = new ArrayList<Author>((books.get(i)).getAuthor());
			ArrayList<Genre> genre = new ArrayList<Genre>((books.get(i)).getGenre());
			String img_src = new String((books.get(i)).getImg());
			long id_book = (books.get(i)).getIdBook();
			bb = new BookWidget(id_book, author, title, genre, img_src);
			bb.getChooseButton().addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					basket.getSpanElement().setInnerText(
							Integer.valueOf(basket.getSpanElement().getInnerText()) + 1 + "");
					// basket.getHTMLPanel().add(new HTML("<p>" + bb.getBook().getTitle() + "</p>"));

				}
			});
			panel.add(bb);
		}
		fPanel.add(panel);
	}

	@Override
	public void setView(Book book) {

		fPanel.clear();
		sprintHPanel.clear();
		hPanel.clear();
		titlePanel.clear();
		FlowPanel panel = new FlowPanel();
		/* String title = new String(book.getTitle()); ArrayList<Author> author = new ArrayList<Author>(book.getAuthor()); ArrayList<Genre> genre = new ArrayList<Genre>(book.getGenre()); String img_src = new String(book.getImg()); long id_book = book.getIdBook(); Window.alert("1"); //
		 * SelectedBookWidget bb = new SelectedBookWidget(id_book, author, title, genre, img_src, null, // "ЭКСМО", "1999", null, "190", "Мягкая", null); // SelectedBookWidget bb = new SelectedBookWidget(id_book, author, title, genre, img_src, // book.getYear_create(), book.getPublish(),
		 * book.getYear_publish(), book.getIsbn(), // book.getCol_pages(), book.getCover(), book.getSpecific()); */
		SelectedBookWidget bb = new SelectedBookWidget(book);
		panel.add(bb);
		fPanel.add(panel);
	}

	@Override
	public void setView(String ref) {

		fPanel.clear();
		sprintHPanel.clear();
		hPanel.clear();
		fPanel.add(new HTML("<p align=\"center\">" + ref + "</p>"));

	}

	private void ChangeViewERROR() {
		fPanel.clear();
		sprintHPanel.clear();
		hPanel.clear();
		Label text = new Label(SERVER_ERROR);
		fPanel.add(text);
	}

	private void pageNav(int col_page, int page, String param) {

		if (col_page > 1) {
			if (page == 1) {
				HTML num_page = new HTML("<a id=\"col_page\" class=\"active\">" + "<" + "</a>");
				sprintHPanel.add(num_page);
				hPanel.add(new HTML("<a id=\"col_page\" class=\"active\">" + "<" + "</a>"));
			} else {
				HTML num_page = new HTML("<a id=\"col_page\" href=\"#UserPlace:" + param + "&p="
						+ (page - 1) + "\">" + "<" + "</a>");
				sprintHPanel.add(num_page);
				hPanel.add(new HTML("<a id=\"col_page\" href=\"#UserPlace:" + param + "&p="
						+ (page - 1) + "\">" + "<" + "</a>"));
			}
			for (int i = 1; i <= col_page; i++) {
				if (i == page) {
					HTML num_page = new HTML("<a id=\"col_page\" class=\"active\" >" + i + "</a>");
					sprintHPanel.add(num_page);
					hPanel.add(new HTML("<a id=\"col_page\" class=\"active\" >" + i + "</a>"));
				} else {
					HTML num_page = new HTML("<a id=\"col_page\" href=\"#UserPlace:" + param + "&p="
							+ i + "\">" + i + "</a>");
					sprintHPanel.add(num_page);
					hPanel.add(new HTML("<a id=\"col_page\" href=\"#UserPlace:" + param + "&p=" + i
							+ "\">" + i + "</a>"));
				}
			}
			if (page == col_page) {
				HTML num_page = new HTML("<a id=\"col_page\" class=\"active\">" + ">" + "</a>");
				sprintHPanel.add(num_page);
				hPanel.add(new HTML("<a id=\"col_page\" class=\"active\">" + ">" + "</a>"));
			} else {
				HTML num_page = new HTML("<a id=\"col_page\" href=\"#UserPlace:" + param + "&p="
						+ (page + 1) + "\">" + ">" + "</a>");
				sprintHPanel.add(num_page);
				hPanel.add(new HTML("<a id=\"col_page\" href=\"#UserPlace:" + param + "&p="
						+ (page + 1) + "\">" + ">" + "</a>"));
			}
		}

	}

	public String li(String type, String name, long id, long col) {

		return "<a href=\"#UserPlace:" + type + "=" + id + "&p=1\">" + name + " <span>" + col
				+ "</span></a>";

	}

	private static native void scr() /*-{
		{

			var menu_ul = $wnd.$('.menu > li > ul'), menu_a = $wnd
					.$('.menu > li > a'), menu_li_a = $wnd
					.$('.menu > li > ul > li > a');

			menu_ul.hide();
			menu_li_a.click(function(e) {
				if (!$wnd.$(this).hasClass('active')) {
					menu_li_a.removeClass('active');
					$wnd.$(this).addClass('active');
				} else {
					$wnd.$(this).removeClass('active');
				}
			});
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

	// @UiHandler("button")
	public void onClick(ClickEvent event) {
		listener.goTo(new AdminPlace(""));
	}
}