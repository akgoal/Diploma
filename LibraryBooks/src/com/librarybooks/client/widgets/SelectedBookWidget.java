package com.librarybooks.client.widgets;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
import com.librarybooks.client.BookService;
import com.librarybooks.client.BookServiceAsync;
import com.librarybooks.client.objects.Author;
import com.librarybooks.client.objects.Book;
import com.librarybooks.client.objects.Genre;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Style.VerticalAlign;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;

public class SelectedBookWidget extends Composite {

	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	private final BookServiceAsync bookService = GWT.create(BookService.class);

	private Book choose_book = new Book();
	private Button chooseButton = new Button("Добавить");

	private Image full_img = new Image();
	private Label back_book = new Label();
	private VerticalPanel vPanel = new VerticalPanel();
	// HorizontalPanel hPanelGenre = new HorizontalPanel();
	FlowPanel hPanelGenre = new FlowPanel();
	HorizontalPanel hPanelAuthor = new HorizontalPanel();

	private FlowPanel generalLPanel = new FlowPanel();
	private LayoutPanel topLPanel = new LayoutPanel();
	// private LayoutPanel leftLPanel = new LayoutPanel();
	// private LayoutPanel rightLPanel = new LayoutPanel();
	private VerticalPanel leftLPanel = new VerticalPanel();
	private VerticalPanel rightLPanel = new VerticalPanel();
	LayoutPanel bottomLPanel = new LayoutPanel();
	LayoutPanel layoutPanel_4 = new LayoutPanel();

	private Label lbl_rating = new Label("Рейтинг");
	private Label lbl_author = new Label("Автоp:");
	private Label lbl_id_book = new Label("Код книги");
	private Label lbl_year_create = new Label("Год создания");
	private Label lbl_publish = new Label("Издательство");
	private Label lbl_year_publish = new Label("Год издания");
	private Label lbl_isbn = new Label("ISBN");
	private Label lbl_col_pages = new Label("Страниц");
	private Label lbl_cover = new Label("Переплет");
	private Label lbl_genre = new Label("Жанр");
	private Label lbl_data = new Label("15.05.2016");
	private Label lbl_specific = new Label("Описание");
	private Label lbl_text_for_data = new Label("Можно получить:");

	private Label dnmc_rating = new Label("New label");
	// private Label dnmc_author = new Label();
	private Label dnmc_title = new Label();
	private Label dnmc_id_book = new Label("");
	private Label dnmc_year_create = new Label("2016");
	private Label dnmc_publish = new Label("изд");
	private Label dnmc_year_publish = new Label("2016");
	private Label dnmc_isbn = new Label("isbn");
	private Label dnmc_col_pages = new Label("327");
	private Label dnmc_cover = new Label("тип");
	private Label dnmc_specific = new Label();

	private Label link_list = new Label("Назад");

	private HTML Html_br = new HTML("<hr />", true);

	public SelectedBookWidget(long id_book, ArrayList<Author> author, String title,
			ArrayList<Genre> genre, String img_src) {

		// img_src = GWT.getHostPageBaseURL() + "img/template.jpg";

		img_src = GWT.getHostPageBaseURL() + "covers/" + img_src;

		choose_book.setBook(id_book, author, title, genre, img_src);

		vPanel.setStyleName("panelForSelect");
		vPanel.setSize("", "");
		generalLPanel.setSize("", "");

		Grid grid = new Grid(7, 2);
		grid.getRowFormatter().setVerticalAlign(6, HasVerticalAlignment.ALIGN_TOP);

		// int numRows = grid.getRowCount();
		// int numColumns = grid.getColumnCount();
		// for (int row = 0; row < numRows; row++) {
		// for (int col = 0; col < numColumns; col++) {
		// grid.setWidget(row, col, new Image(Showcase.images.gwtLogo()));
		// }
		// }

		vPanel.add(link_list);
		// topLPanel.add(link_list);
		// topLPanel.setWidgetLeftWidth(link_list, 20.0, Unit.PX, 140.0, Unit.PX);
		// topLPanel.setWidgetTopHeight(link_list, 14.0, Unit.PX, 16.0, Unit.PX);

		leftLPanel.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
		rightLPanel.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
		vPanel.add(generalLPanel);
		generalLPanel.add(leftLPanel);
		leftLPanel.setSize("230px", "");
		full_img.setStyleName("imageFull");
		leftLPanel.add(full_img);
		full_img.setUrl(img_src);
		full_img.setSize("200px", "");
		leftLPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		leftLPanel.setWidth("250px");
		leftLPanel.add(lbl_rating);
		// leftLPanel.setWidgetLeftWidth(lbl_rating, 58.0, Unit.PX, 56.0, Unit.PX);
		// leftLPanel.setWidgetTopHeight(lbl_rating, 332.0, Unit.PX, 16.0, Unit.PX);
		leftLPanel.add(dnmc_rating);
		// leftLPanel.setWidgetLeftWidth(dnmc_rating, 120.0, Unit.PX, 56.0, Unit.PX);
		// leftLPanel.setWidgetTopHeight(dnmc_rating, 332.0, Unit.PX, 16.0, Unit.PX);
		generalLPanel.add(rightLPanel);
		rightLPanel.getElement().setId("rightPanel");
		rightLPanel.setSize("400px", "");
		dnmc_title.setStyleName("nameBookFull");
		dnmc_title.setDirectionEstimator(true);
		dnmc_title.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		rightLPanel.add(dnmc_title);
		lbl_author.setStyleName("labelFullNotBorder");
		hPanelAuthor.add(lbl_author);
		lbl_author.setSize("", "");

		for (int i = 0; i < author.size(); i++) {
			if (i > 0) {
				hPanelAuthor.add(new HTML(",&nbsp"));
				hPanelAuthor.add(new ListLabel("author", author.get(i).getAuthor(),
						author.get(i).getIdAuthor()));
			} else {
				hPanelAuthor.add(new ListLabel("author", author.get(i).getAuthor(),
						author.get(i).getIdAuthor()));
			}
		}
		rightLPanel.add(hPanelAuthor);

		rightLPanel.add(layoutPanel_4);

		lbl_id_book.setStyleName("labelFull");
		lbl_id_book.setDirectionEstimator(false);

		lbl_year_create.setStyleName("labelFull");
		grid.setWidget(0, 0, lbl_year_create);
		grid.setWidget(0, 1, dnmc_year_create);
		lbl_publish.setStyleName("labelFull");
		grid.setWidget(1, 0, lbl_publish);
		grid.setWidget(1, 1, dnmc_publish);
		lbl_year_publish.setStyleName("labelFull");

		grid.setWidget(2, 0, lbl_year_publish);
		grid.setWidget(2, 1, dnmc_year_publish);

		lbl_isbn.setStyleName("labelFull");

		grid.setWidget(3, 0, lbl_isbn);
		grid.setWidget(3, 1, dnmc_isbn);

		lbl_col_pages.setStyleName("labelFull");

		grid.setWidget(4, 0, lbl_col_pages);
		grid.setWidget(4, 1, dnmc_col_pages);

		lbl_cover.setStyleName("labelFull");

		grid.setWidget(5, 0, lbl_cover);
		grid.setWidget(5, 1, dnmc_cover);

		lbl_genre.setStyleName("labelFull");
		rightLPanel.add(lbl_genre);
		grid.setWidget(6, 0, lbl_genre);
		grid.setWidget(6, 1, hPanelGenre);
		rightLPanel.add(grid);
		rightLPanel.setSpacing(15);
		layoutPanel_4.setStyleName("panel");

		lbl_text_for_data.setStyleName("textForDate");
		layoutPanel_4.add(lbl_text_for_data);
		layoutPanel_4.setWidgetLeftWidth(lbl_text_for_data, 12.0, Unit.PX, 129.0, Unit.PX);
		layoutPanel_4.setWidgetTopHeight(lbl_text_for_data, 7.0, Unit.PX, 16.0, Unit.PX);
		lbl_data.setStyleName("textDate");
		layoutPanel_4.add(lbl_data);
		layoutPanel_4.setWidgetTopHeight(lbl_data, 29.0, Unit.PX, 16.0, Unit.PX);
		layoutPanel_4.setWidgetLeftWidth(lbl_data, 12.0, Unit.PX, 106.0, Unit.PX);
		chooseButton.setStyleName("buttonAddIn");
		layoutPanel_4.add(chooseButton);
		layoutPanel_4.setWidgetLeftWidth(chooseButton, 191.0, Unit.PX, 115.0, Unit.PX);
		layoutPanel_4.setWidgetTopHeight(chooseButton, 7.0, Unit.PX, 38.0, Unit.PX);
		layoutPanel_4.setHeight("50px");
		layoutPanel_4.setWidth("290px");

		for (int i = 0; i < genre.size(); i++) {
			if (i > 0) {
				HTML tab = new HTML(",&nbsp");
				tab.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
				hPanelGenre.add(tab);
				hPanelGenre.add(
						new ListLabel("genre", genre.get(i).getGenre(), genre.get(i).getIdGenre()));
			} else {
				hPanelGenre.add(
						new ListLabel("genre", genre.get(i).getGenre(), genre.get(i).getIdGenre()));
			}
		}
		dnmc_id_book.setText(String.valueOf(id_book));

		dnmc_publish.setStyleName("linkFull");

		dnmc_title.setText(title);
		link_list.setStyleName("linkFull");
		vPanel.setSize("", "");
		vPanel.add(bottomLPanel);
		bottomLPanel.setSize("", "59px");
		bottomLPanel.add(Html_br);
		lbl_specific.setStyleName("labelText");
		bottomLPanel.add(lbl_specific);
		bottomLPanel.setWidgetLeftWidth(lbl_specific, 10.0, Unit.PX, 92.0, Unit.PX);
		bottomLPanel.setWidgetTopHeight(lbl_specific, 24.0, Unit.PX, 35.0, Unit.PX);
		dnmc_specific.setText(
				"\tNew label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label\r\n\tNew label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New label New labelNew label New label New label New label New label New label New label New label New label New label New label New label New label New label New label.");
		dnmc_specific.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		dnmc_specific.setStyleName("textForBook");
		vPanel.add(dnmc_specific);
		dnmc_specific.setSize("650", "");

		link_list.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				History.back();
			}
		});

		chooseButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				chooseBookToServer();
			}
		});

		initWidget(vPanel);

	}

	public void chooseBookToServer() {

		Book callInput = new Book(this.choose_book.getAuthor(), this.choose_book.getTitle(),
				this.choose_book.getGenre(), this.choose_book.getImg());
		bookService.bookToServer(callInput, new AsyncCallback<Book>() {
			public void onFailure(Throwable caught) {
				Label text = new Label(SERVER_ERROR);
				RootPanel.get("listBook").add(text);
			}

			public void onSuccess(Book result) {

				back_book.setText(result.getAuthor() + " " + result.getTitle());
			}
		});
		chooseButton.setFocus(false);
	}
}
