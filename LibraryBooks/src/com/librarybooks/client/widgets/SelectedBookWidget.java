package com.librarybooks.client.widgets;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ErrorEvent;
import com.google.gwt.event.dom.client.ErrorHandler;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.librarybooks.client.BookService;
import com.librarybooks.client.BookServiceAsync;
import com.librarybooks.client.activities_and_places.places.UserPlace;
import com.librarybooks.client.activities_and_places.view.UserView.Presenter;
import com.librarybooks.client.objects.Author;
import com.librarybooks.client.objects.Book;
import com.librarybooks.client.objects.Genre;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Style.VerticalAlign;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;

public class SelectedBookWidget extends Composite {

	private static SelectedBookWidgetUiBinder uiBinder = GWT
			.create(SelectedBookWidgetUiBinder.class);

	interface SelectedBookWidgetUiBinder extends UiBinder<Widget, SelectedBookWidget> {
	}

	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	private final BookServiceAsync bookService = GWT.create(BookService.class);

	private Book choose_book = new Book();

	@UiField
	Button chooseButton;
	@UiField
	Image full_img;
	@UiField
	VerticalPanel vPanel;
	@UiField
	HorizontalPanel hPanelAuthor;
	@UiField
	FlowPanel generalPanel;
	@UiField
	VerticalPanel leftPanel;
	@UiField
	VerticalPanel rightPanel;
	@UiField
	LayoutPanel layoutPanel;
	@UiField
	Label lbl_author;
	@UiField
	Label lbl_data;
	@UiField
	Label lbl_text_for_data;
	@UiField
	HTMLPanel dnmc_rating;
	@UiField
	Label link_list;
	@UiField
	Label dnmc_title;

	FlowPanel hPanelGenre = new FlowPanel();
	LayoutPanel bottomPanel = new LayoutPanel();

	final Label lbl_id_book = new Label("Код книги");
	final Label lbl_year_create = new Label("Год создания");
	final Label lbl_publish = new Label("Издательство");
	final Label lbl_year_publish = new Label("Год издания");
	final Label lbl_isbn = new Label("ISBN");
	final Label lbl_col_pages = new Label("Страниц");
	final Label lbl_cover = new Label("Переплет");
	final Label lbl_genre = new Label("Жанр");
	final Label lbl_specific = new Label("Описание");

	final Label dnmc_id_book = new Label();
	final Label dnmc_year_create = new Label();
	final Label dnmc_publish = new Label();
	final Label dnmc_year_publish = new Label();
	final Label dnmc_isbn = new Label();
	final Label dnmc_col_pages = new Label();
	final Label dnmc_cover = new Label();
	final Label dnmc_specific = new Label();

	final HTML Html_br = new HTML("<hr />", true);

	Presenter listener;
	UserPlace place;

	// public SelectedBookWidget(long id_book, ArrayList<Author> author, String title,
	// ArrayList<Genre> genre, String img_src, String year_create, String publish,
	// String year_publish, String isbn, String col_pages, String cover, String specific)
	public SelectedBookWidget(BookServiceAsync bookService, UserPlace _place, Presenter _listener,
			Book book) {
		this.listener = _listener;
		this.place = _place;

		choose_book.setBook(book.getIdBook(), book.getAuthor(), book.getTitle(), book.getGenre(),
				book.getImg());

		long id_book = book.getIdBook();
		ArrayList<Author> author = book.getAuthor();
		String title = book.getTitle();
		ArrayList<Genre> genre = book.getGenre();
		String img_src = book.getImg();
		String year_create = book.getYear_create();
		String publish = book.getPublish();
		String year_publish = book.getYear_publish();
		String isbn = book.getIsbn();
		String col_pages = book.getCol_pages();
		String cover = book.getCover();
		String specific = book.getSpecific();

		initWidget(uiBinder.createAndBindUi(this));
		Grid grid = new Grid(7, 2);

		dnmc_rating.add(new Rate(bookService, id_book, book.getRate()));
		img_src = GWT.getHostPageBaseURL() + "covers/" + img_src;
		full_img.setUrl(img_src);

		if (title != null)
			dnmc_title.setText(title);
		if (author != null) {
			for (int i = 0; i < author.size(); i++) {
				if (i > 0) {
					hPanelAuthor.add(new HTML(",&nbsp"));
					hPanelAuthor.add(new ListLabel(listener, "author", author.get(i).getAuthor(),
							author.get(i).getIdAuthor()));
				} else {
					hPanelAuthor.add(new ListLabel(listener, "author", author.get(i).getAuthor(),
							author.get(i).getIdAuthor()));
				}
			}
		} else
			hPanelAuthor.add(new Label("Неизвестен"));

		int row = 0;
		if (year_create != null) {
			dnmc_year_create.setText(year_create);
			grid.setWidget(row, 0, lbl_year_create);
			grid.setWidget(row++, 1, dnmc_year_create);
		}
		if (publish != null) {
			dnmc_publish.setText(publish);
			grid.setWidget(row, 0, lbl_publish);
			grid.setWidget(row++, 1, dnmc_publish);
		}

		if (year_publish != null) {
			dnmc_year_publish.setText(year_publish);
			grid.setWidget(row, 0, lbl_year_publish);
			grid.setWidget(row++, 1, dnmc_year_publish);
		}

		if (isbn != null) {
			dnmc_isbn.setText(isbn);
			grid.setWidget(row, 0, lbl_isbn);
			grid.setWidget(row++, 1, dnmc_isbn);
		}

		if (col_pages != null) {
			dnmc_col_pages.setText(col_pages + "");
			grid.setWidget(row, 0, lbl_col_pages);
			grid.setWidget(row++, 1, dnmc_col_pages);
		}

		if (cover != null) {
			dnmc_cover.setText(cover);
			grid.setWidget(row, 0, lbl_cover);
			grid.setWidget(row++, 1, dnmc_cover);
		}

		if (genre != null) {
			for (int i = 0; i < genre.size(); i++) {
				if (i > 0) {
					HTML tab = new HTML(",&nbsp");
					tab.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
					hPanelGenre.add(tab);
					hPanelGenre.add(new ListLabel(listener, "genre", genre.get(i).getGenre(),
							genre.get(i).getIdGenre()));
				} else {
					hPanelGenre.add(new ListLabel(listener, "genre", genre.get(i).getGenre(),
							genre.get(i).getIdGenre()));
				}
			}
			grid.setWidget(row, 0, lbl_genre);
			grid.setWidget(row++, 1, hPanelGenre);
		}
		if (row > 0) {
			rightPanel.add(grid);
		}

		if (specific != null) {
			bottomPanel.add(Html_br);
			bottomPanel.add(lbl_specific);
			dnmc_specific.setText(specific);
			vPanel.add(bottomPanel);
			vPanel.add(dnmc_specific);

			bottomPanel.setWidgetLeftWidth(lbl_specific, 10.0, Unit.PX, 92.0, Unit.PX);
			bottomPanel.setWidgetTopHeight(lbl_specific, 24.0, Unit.PX, 35.0, Unit.PX);
		}

		layoutPanel.setWidgetLeftWidth(chooseButton, 191.0, Unit.PX, 115.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(chooseButton, 7.0, Unit.PX, 38.0, Unit.PX);

		vPanel.setStyleName("panelForSelect");
		full_img.setStyleName("imageFull");
		rightPanel.getElement().setId("rightPanel");
		dnmc_title.setStyleName("nameBookFull");
		lbl_author.setStyleName("labelFullNotBorder");
		dnmc_publish.setStyleName("linkFull");
		link_list.setStyleName("linkFull");
		lbl_specific.setStyleName("labelText");
		dnmc_specific.setStyleName("textForBook");
		layoutPanel.setStyleName("panel");
		lbl_text_for_data.setStyleName("textForDate");
		lbl_data.setStyleName("textDate");
		chooseButton.setStyleName("buttonAddIn");
		lbl_id_book.setStyleName("labelFull");
		lbl_year_create.setStyleName("labelFull");
		lbl_publish.setStyleName("labelFull");
		lbl_year_publish.setStyleName("labelFull");
		lbl_isbn.setStyleName("labelFull");
		lbl_col_pages.setStyleName("labelFull");
		lbl_cover.setStyleName("labelFull");
		lbl_genre.setStyleName("labelFull");

		layoutPanel.setHeight("50px");
		layoutPanel.setWidth("290px");
		lbl_author.setSize("", "");
		vPanel.setSize("", "");
		generalPanel.setSize("", "");
		leftPanel.setSize("230px", "");
		full_img.addErrorHandler(new ErrorHandler() {

			@Override
			public void onError(ErrorEvent event) {
				full_img.setSize("200px", "300px");

			}
		});
		full_img.addLoadHandler(new LoadHandler() {

			@Override
			public void onLoad(LoadEvent event) {
				full_img.setSize("200px", "");

			}
		});

		rightPanel.setSize("400px", "");
		vPanel.setSize("", "");
		bottomPanel.setSize("", "59px");
		dnmc_specific.setSize("650", "");

		link_list.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		dnmc_rating.getElement().getStyle().setTextAlign(TextAlign.CENTER);
		rightPanel.getElement().getStyle().setVerticalAlign(VerticalAlign.TOP);
		leftPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		dnmc_title.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		dnmc_specific.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		grid.getRowFormatter().setVerticalAlign(6, HasVerticalAlignment.ALIGN_TOP);
		dnmc_title.setDirectionEstimator(true);
		lbl_id_book.setDirectionEstimator(false);
		leftPanel.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
		rightPanel.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
		rightPanel.setSpacing(15);
		layoutPanel.setWidgetLeftWidth(lbl_text_for_data, 12.0, Unit.PX, 129.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lbl_text_for_data, 7.0, Unit.PX, 16.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lbl_data, 29.0, Unit.PX, 16.0, Unit.PX);
		layoutPanel.setWidgetLeftWidth(lbl_data, 12.0, Unit.PX, 106.0, Unit.PX);

		if (book.getPrice() != null)
			lbl_data.setText(book.getPrice() + " руб.");

		link_list.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				History.back();
			}
		});

		chooseButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				place.getBasketList().add(new Book());
			}
		});

	}

	public Button getChooseButton() {
		return chooseButton;
	}

	public void chooseBookToServer() {

		Book callInput = new Book(this.choose_book.getIdBook(), this.choose_book.getAuthor(),
				this.choose_book.getTitle(), this.choose_book.getGenre(),
				this.choose_book.getImg());
		bookService.bookToServer(callInput, new AsyncCallback<Book>() {
			public void onFailure(Throwable caught) {
				Label text = new Label(SERVER_ERROR);
				RootPanel.get("listBook").add(text);
			}

			public void onSuccess(Book result) {

				// back_book.setText(result.getAuthor() + " " + result.getTitle());
			}
		});
		chooseButton.setFocus(false);
	}
}