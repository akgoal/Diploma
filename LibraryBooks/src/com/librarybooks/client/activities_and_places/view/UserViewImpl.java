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
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
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
import com.librarybooks.client.OrderService;
import com.librarybooks.client.OrderServiceAsync;
import com.librarybooks.client.activities_and_places.places.UserPlace;
import com.librarybooks.client.objects.Author;
import com.librarybooks.client.objects.Book;
import com.librarybooks.client.objects.Genre;
import com.librarybooks.client.objects.Order;
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
	HorizontalPanel but_panel = new HorizontalPanel();
	Button button_auth = new Button("Вход");
	Anchor a_reg = new Anchor("   Регистрация");
	Button button_outh = new Button("Выход");
	Label historyOrder = new Label("История заказов");

	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	private final BookServiceAsync bookService = GWT.create(BookService.class);
	private final OrderServiceAsync orderService = GWT.create(OrderService.class);

	private final SecurityServiceAsync securityService = GWT.create(SecurityService.class);

	final SearchPane sp = new SearchPane();
	final Basket basket = new Basket();
	private FlexTable basketFlex = new FlexTable();

	public UserViewImpl() {

		initWidget(uiBinder.createAndBindUi(this));
		form_auth.setStyleName("form_auth");
		form_auth.add(username);
		form_auth.add(password);
		but_panel.add(button_auth);
		but_panel.add(a_reg);
		a_reg.getElement().getStyle().setMarginLeft(10, Unit.PX);
		form_auth.add(but_panel);
		button_auth.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				form_auth.clear();
				form_auth.add(new HTML(
						"<p>Вы вошли под именем <span>" + username.getText() + "</span></p>"));
				form_auth.add(button_outh);
				historyOrder.setVisible(true);
				basket.setVisible(true);
			}
			// securityService.authenticate(username.getText(), password.getText(),
			// new AsyncCallback<Boolean>() {
			// public void onFailure(Throwable caught) {
			// Window.alert("Ошибка!!!");
			// }
			//
			// public void onSuccess(Boolean auth_result) {
			// if (auth_result) {
			// Window.alert("Добро пожаловать, " + username.getText() + "!");
			// form_auth.clear();
			// form_auth.add(new HTML("<p>Вы вошли под именем <span>"
			// + username.getText() + "</span></p>"));
			// form_auth.add(button_outh);
			// } else {
			// username.setText("");
			// password.setText("");
			// }
			// }
			// });
			// }
		});
		button_outh.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				form_auth.clear();
				form_auth.add(username);
				form_auth.add(password);
				but_panel.add(button_auth);
				but_panel.add(a_reg);
				form_auth.add(but_panel);
				historyOrder.setVisible(false);
				basket.setVisible(false);
			}
		});

		a_reg.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				listener.goTo(new UserPlace("reg"));
			}
		});

		historyOrder.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				listener.goTo(new UserPlace("history_order"));
			}
		});

		basket.getButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				orderService.addOrder(new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(Void result) {
						addBookInBasket();
						listener.goTo(new UserPlace("history_order"));
					}
				});

			}
		});

		searchPanel.getElement().setId("search_panel");
		basket.setStyleName("basket");
		basket.getHTMLPanel().add(basketFlex);
		addBookInBasket();

		historyOrder.setVisible(false);
		basket.setVisible(false);
		searchPanel.add(basket);
		searchPanel.add(historyOrder);
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
			BookWidget bb = new BookWidget(listener, books.get(i));
			bookInWidget.setTitle((books.get(i)).getTitle());
			bb.getChooseButton().addClickHandler(this);
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
		SelectedBookWidget sb = new SelectedBookWidget(listener, book);
		sb.getChooseButton().addClickHandler(this);
		panel.add(sb);
		fPanel.add(panel);
	}

	@Override
	public void setView(String ref) {

		fPanel.clear();
		sprintHPanel.clear();
		hPanel.clear();
		titlePanel.clear();
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

		orderService.listBook(new AsyncCallback<ArrayList<Book>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ArrayList<Book> result) {
				// TODO Auto-generated method stub
				basketFlex.removeAllRows();
				basketFlex.setText(0, 0, "Название");
				basketFlex.setText(0, 1, "Цена");
				basketFlex.setText(0, 2, "");
				basketFlex.setHTML(1, 0, "<hr>");
				basketFlex.getFlexCellFormatter().setColSpan(1, 0, 3);
				basketFlex.getFlexCellFormatter().setWidth(0, 0, "180px");
				basketFlex.getFlexCellFormatter().setWidth(0, 1, "50px");
				basketFlex.getFlexCellFormatter().setWidth(0, 2, "30px");
				basketFlex.getFlexCellFormatter().getElement(0, 0).getStyle().setPadding(10,
						Unit.PX);
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
				basket.getSpanElement().setInnerText(result.size() + "");
				int row = basketFlex.getRowCount();
				for (Book book : result) {
					basketFlex.setHTML(row, 0, "<a href=\"#UserPlace:book=" + book.getIdBook()
							+ "\">" + book.getTitle() + "</a>");
					// final Book delBook = book;
					final int index = row - 2;
					basketFlex.setText(row, 1, book.getPrice() + "ք");
					Label removeBook = new Label("x");
					removeBook.setStyleName("removeBook");
					removeBook.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent event) {
							orderService.delBook(index, new AsyncCallback<Void>() {

								@Override
								public void onFailure(Throwable caught) {
									// TODO Auto-generated method stub

								}

								@Override
								public void onSuccess(Void result) {
									// TODO Auto-generated method stub
									addBookInBasket();
								}
							});
						}
					});
					basketFlex.setWidget(row, 2, removeBook);
					for (int _row = 2; _row < basketFlex.getRowCount(); _row++) {
						if (_row % 2 == 0)
							basketFlex.getRowFormatter().getElement(_row).getStyle()
									.setBackgroundColor("#F9F9F9");
						else
							basketFlex.getRowFormatter().getElement(_row).getStyle()
									.setBackgroundColor("#FFF");
					}
					basketFlex.getFlexCellFormatter().getElement(row, 0).getStyle()
							.setPaddingBottom(5, Unit.PX);
					basketFlex.getFlexCellFormatter().getElement(row, 0).getStyle().setPaddingTop(5,
							Unit.PX);
					basketFlex.getFlexCellFormatter().getElement(row, 0).getStyle()
							.setPaddingLeft(20, Unit.PX);
					basketFlex.setWidget(row, 2, removeBook);
					basketFlex.getFlexCellFormatter().setHorizontalAlignment(row, 1,
							HasHorizontalAlignment.ALIGN_CENTER);
					basketFlex.getFlexCellFormatter().setHorizontalAlignment(row++, 2,
							HasHorizontalAlignment.ALIGN_CENTER);
				}

			}
		});

	}

	@Override
	public BookWidget getBookWidget() {
		// return bb;
		return null;
	}

	@Override
	public void setViewReg() {
		// TODO Auto-generated method stub
		fPanel.clear();
		sprintHPanel.clear();
		hPanel.clear();
		titlePanel.clear();
		fPanel.add(new Reg());

	}

	@Override
	public void setViewHistory() {
		// TODO Auto-generated method stub
		fPanel.clear();
		sprintHPanel.clear();
		hPanel.clear();
		titlePanel.clear();
		orderService.listOrder(new AsyncCallback<ArrayList<Order>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ArrayList<Order> orders) {
				Grid grid = new Grid(orders.size() + 1, 4);
				grid.setWidget(0, 0, new Label("№ Заказа"));
				grid.setWidget(0, 1, new Label("Дата заказа"));
				grid.setWidget(0, 2, new Label("Содержимое"));
				grid.setWidget(0, 3, new Label("Статус"));
				int row = 1;
				if (orders.size() > 0)
					for (Order order : orders) {
						grid.setWidget(row, 0, new Label(order.getId_order() + ""));
						grid.setWidget(row, 1, new Label(order.getDate()));
						VerticalPanel vp = new VerticalPanel();
						for (Book book : order.getBooks()) {
							vp.add(new HTML("<a href=\"#UserPlace:book=" + book.getIdBook() + "\">"
									+ book.getTitle() + "</a>"));
						}
						grid.setWidget(row, 2, vp);
						grid.setWidget(row++, 3, new Label(order.getState()));
					}
				fPanel.add(grid);

			}
		});

	}
}