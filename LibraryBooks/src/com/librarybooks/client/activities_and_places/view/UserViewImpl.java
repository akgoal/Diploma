package com.librarybooks.client.activities_and_places.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.librarybooks.client.BookService;
import com.librarybooks.client.BookServiceAsync;
import com.librarybooks.client.activities_and_places.places.UserPlace;
import com.librarybooks.client.objects.Author;
import com.librarybooks.client.objects.Book;
import com.librarybooks.client.objects.Genre;
import com.librarybooks.client.objects.Selection;
import com.librarybooks.client.security.SecurityService;
import com.librarybooks.client.security.SecurityServiceAsync;
import com.librarybooks.client.widgets.Basket;
import com.librarybooks.client.widgets.BookWidget;
import com.librarybooks.client.widgets.SearchPane;
import com.librarybooks.client.widgets.SelectedBookWidget;

public class UserViewImpl extends Composite implements UserView, ClickHandler {

	private static UserViewImplUiBinder uiBinder = GWT.create(UserViewImplUiBinder.class);

	interface UserViewImplUiBinder extends UiBinder<Widget, UserViewImpl> {
	}

	Presenter listener;
	UserPlace place;
	long id;
	String[] options = { "all", "author", "genre", "selection", "book", "new", "popular", "classic",
			"child", "foreign" };
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
	VerticalPanel form_auth;
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

	MenuBar menuMain = new MenuBar();
	TextBox username = new TextBox();
	PasswordTextBox password = new PasswordTextBox();
	Button button_auth = new Button("Войти");
	Button button_outh = new Button("Выйти");

	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	private final BookServiceAsync bookService = GWT.create(BookService.class);

	private final SecurityServiceAsync securityService = GWT.create(SecurityService.class);

	final SearchPane sp = new SearchPane();
	final Basket basket = new Basket();
	private FlexTable basketFlex = new FlexTable();

	public UserViewImpl() {

		initWidget(uiBinder.createAndBindUi(this));
		form_auth.setStyleName("form_auth");
		form_auth.add(username);
		form_auth.add(password);
		form_auth.add(button_auth);
		button_auth.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				securityService.authenticate(username.getText(), password.getText(),
						new AsyncCallback<Boolean>() {
							public void onFailure(Throwable caught) {
								Window.alert("Ошибка!!!");
							}

							public void onSuccess(Boolean auth_result) {
								if (auth_result) {
									Window.alert("Добро пожаловать, " + username.getText() + "!");
									form_auth.clear();
									form_auth.add(new HTML("<p>Вы вошли под именем <span>"
											+ username.getText() + "</span></p>"));
									form_auth.add(button_outh);
								} else {
									username.setText("");
									password.setText("");
								}
							}
						});
			}
		});
		button_outh.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				form_auth.clear();
				form_auth.add(username);
				form_auth.add(password);
				form_auth.add(button_auth);
			}
		});

		searchPanel.getElement().setId("search_panel");
		basket.setStyleName("basket");
		basket.getHTMLPanel().add(basketFlex);
		basketFlex.setText(0, 0, "Название");
		basketFlex.setText(0, 1, "Кол-во");
		basketFlex.setText(0, 2, "");
		basketFlex.setHTML(1, 0, "<hr>");
		basketFlex.getFlexCellFormatter().setColSpan(1, 0, 3);
		;
		basketFlex.getFlexCellFormatter().setWidth(0, 0, "180px");
		basketFlex.getFlexCellFormatter().setWidth(0, 1, "50px");
		basketFlex.getFlexCellFormatter().setWidth(0, 2, "30px");
		basketFlex.getFlexCellFormatter().getElement(0, 0).getStyle().setPadding(10, Unit.PX);
		basketFlex.getFlexCellFormatter().getElement(0, 0).getStyle().setColor("dimgray");
		basketFlex.getFlexCellFormatter().getElement(0, 1).getStyle().setColor("dimgray");
		basketFlex.getFlexCellFormatter().setHorizontalAlignment(0, 0,
				HasHorizontalAlignment.ALIGN_CENTER);
		basketFlex.getFlexCellFormatter().setHorizontalAlignment(0, 1,
				HasHorizontalAlignment.ALIGN_CENTER);
		basketFlex.getFlexCellFormatter().setHorizontalAlignment(0, 2,
				HasHorizontalAlignment.ALIGN_CENTER);
		basketFlex.setCellPadding(0);
		basketFlex.setCellSpacing(0);
		basketFlex.setBorderWidth(0);

		searchPanel.add(basket);
		searchPanel.add(sp);
		textElement.setInnerHTML(
				"<a href=\"#UserPlace:all=0&p=1\"><img src=\"img/logo_mini.png\"></img><h1>LIBRARY BOOK</h1></a>");
		basket.getSpanElement().setInnerText("0");
		menuMain.addItem("Новинки", new Command() {

			@Override
			public void execute() {
				go("new");
			}
		});
		menuMain.addItem("Популяное", new Command() {

			@Override
			public void execute() {
				go("popular");
			}
		});
		;
		menuMain.addItem("Классическая литература", new Command() {

			@Override
			public void execute() {
				go("classic");
			}
		});
		menuMain.addItem("Для детей", new Command() {

			@Override
			public void execute() {
				go("child");
			}
		});
		menuMain.addItem("Зарубежная литература", new Command() {

			@Override
			public void execute() {
				go("foreign");
			}
		});
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
	public void setView(UserPlace _place, ArrayList<Book> books, int col_page, int page,
			String type, String param, String html_title) {

		this.place = _place;
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
			BookWidget bb = new BookWidget(place, listener, id_book, author, title, genre, img_src);
			bookInWidget.setTitle(title);
			bb.getChooseButton().addClickHandler(this);
			panel.add(bb);
		}
		fPanel.add(panel);
	}

	@Override
	public void setView(BookServiceAsync bookService, UserPlace place, Book book) {

		fPanel.clear();
		sprintHPanel.clear();
		hPanel.clear();
		titlePanel.clear();
		FlowPanel panel = new FlowPanel();
		SelectedBookWidget sb = new SelectedBookWidget(bookService, place, listener, book);
		sb.getChooseButton().addClickHandler(this);
		panel.add(sb);
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

	public void go(String type) {
		listener.goTo(new UserPlace(type + "&p=1"));
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
		addBookInBasket();
	}

	private void addBookInBasket() {
		basket.getSpanElement().setInnerText(place.getBasketList().size() + "");

		int row = basketFlex.getRowCount();
		basketFlex.setHTML(row, 0, "<a href=\"#UserPlace:book="
				+ place.getBasketList().get(place.getBasketList().size() - 1).getIdBook() + "\">"
				+ place.getBasketList().get(place.getBasketList().size() - 1).getTitle() + "</a>");
		final Book delBook = place.getBasketList().get(place.getBasketList().size() - 1);
		basketFlex.setText(row, 1, "1");
		Label removeBook = new Label("x");
		removeBook.setStyleName("removeBook");
		removeBook.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int removedIndex = place.getBasketList().indexOf(delBook);
				place.getBasketList().remove(removedIndex);
				basketFlex.removeRow(removedIndex + 2);
				basket.getSpanElement().setInnerText(place.getBasketList().size() + "");
				for (int row = 2; row < basketFlex.getRowCount(); row++) {
					if (row % 2 == 0)
						basketFlex.getRowFormatter().getElement(row).getStyle()
								.setBackgroundColor("#F9F9F9");
					else
						basketFlex.getRowFormatter().getElement(row).getStyle()
								.setBackgroundColor("#FFF");
				}
			}
		});
		if (row % 2 == 0)
			basketFlex.getRowFormatter().getElement(row).getStyle().setBackgroundColor("#F9F9F9");
		else
			basketFlex.getRowFormatter().getElement(row).getStyle().setBackgroundColor("#FFF");
		basketFlex.getFlexCellFormatter().getElement(row, 0).getStyle().setPaddingBottom(5,
				Unit.PX);
		basketFlex.getFlexCellFormatter().getElement(row, 0).getStyle().setPaddingTop(5, Unit.PX);
		basketFlex.getFlexCellFormatter().getElement(row, 0).getStyle().setPaddingLeft(20, Unit.PX);
		basketFlex.setWidget(row, 2, removeBook);
		basketFlex.getFlexCellFormatter().setHorizontalAlignment(row, 1,
				HasHorizontalAlignment.ALIGN_CENTER);
		basketFlex.getFlexCellFormatter().setHorizontalAlignment(row, 2,
				HasHorizontalAlignment.ALIGN_CENTER);

	}

	@Override
	public BookWidget getBookWidget() {
		// TODO Auto-generated method stub
		// return bb;
		return null;
	}
}