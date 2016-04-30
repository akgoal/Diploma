package testproject.client.widgets;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import testproject.client.objects.Book;
import testproject.client.objects.Genre;
import testproject.client.BookService;
import testproject.client.BookServiceAsync;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

public class SelectedBookWidget extends Composite {

	private static final String SERVER_ERROR = "An error occurred while " + "attempting to contact the server. Please check your network " + "connection and try again.";

	private final BookServiceAsync bookService = GWT.create(BookService.class);

	private Book choose_book = new Book();
	private Button chooseButton = new Button("Добавить");

	private Image full_img = new Image();
	private Label back_book = new Label();
	private VerticalPanel vPanel = new VerticalPanel();
	HorizontalPanel hPanelGenre = new HorizontalPanel();

	private LayoutPanel generalLPanel = new LayoutPanel();
	private LayoutPanel topLPanel = new LayoutPanel();
	private LayoutPanel leftLPanel = new LayoutPanel();
	private LayoutPanel rightLPanel = new LayoutPanel();
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
	private Label dnmc__author = new Label();
	private Label dnmc_title = new Label();
	private Label dnmc_id_book = new Label();
	private Label dnmc_year_create = new Label("2016");
	private Label dnmc_publish = new Label("изд");
	private Label dnmc_year_publish = new Label("2016");
	private Label dnmc_isbn = new Label("isbn");
	private Label dnmc_col_pages = new Label("327");
	private Label dnmc_cover = new Label("тип");
	private Label dnmc_specific = new Label();

	private Label link_list = new Label("Назад");

	private HTML Html_br = new HTML("<hr />", true);

	public SelectedBookWidget(long id_book, String autor, long id_autor, String title, ArrayList<Genre> genre, String img_src) {

		img_src = GWT.getModuleBaseURL() + "img/template.jpg";

		choose_book.setBook(id_book, autor, id_autor, title, genre, img_src);

		vPanel.setStyleName("panelForSelect");
		generalLPanel.setSize("700px", "415px");
		vPanel.add(generalLPanel);
		generalLPanel.add(leftLPanel);
		generalLPanel.setWidgetLeftWidth(leftLPanel, 0.0, Unit.PX, 230.0, Unit.PX);
		generalLPanel.setWidgetTopHeight(leftLPanel, 36.0, Unit.PX, 375.0, Unit.PX);
		leftLPanel.setSize("230px", "375px");
		full_img.setStyleName("imageFull");
		leftLPanel.add(full_img);
		full_img.setUrl(img_src);
		full_img.setPixelSize(full_img.getWidth() * 300 / full_img.getHeight(), 300);
		leftLPanel.add(lbl_rating);
		leftLPanel.setWidgetLeftWidth(lbl_rating, 58.0, Unit.PX, 56.0, Unit.PX);
		leftLPanel.setWidgetTopHeight(lbl_rating, 332.0, Unit.PX, 16.0, Unit.PX);
		leftLPanel.add(dnmc_rating);
		leftLPanel.setWidgetLeftWidth(dnmc_rating, 120.0, Unit.PX, 56.0, Unit.PX);
		leftLPanel.setWidgetTopHeight(dnmc_rating, 332.0, Unit.PX, 16.0, Unit.PX);
		generalLPanel.add(rightLPanel);
		generalLPanel.setWidgetLeftWidth(rightLPanel, 235.0, Unit.PX, 465.0, Unit.PX);
		generalLPanel.setWidgetTopHeight(rightLPanel, 36.0, Unit.PX, 373.0, Unit.PX);
		rightLPanel.setSize("460px", "373px");
		dnmc_title.setStyleName("nameBookFull");
		dnmc_title.setDirectionEstimator(true);
		dnmc_title.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		rightLPanel.add(dnmc_title);
		rightLPanel.setWidgetLeftWidth(dnmc_title, 15.0, Unit.PX, 412.0, Unit.PX);
		rightLPanel.setWidgetTopHeight(dnmc_title, 10.0, Unit.PX, 28.0, Unit.PX);
		lbl_author.setStyleName("labelFullNotBorder");
		rightLPanel.add(lbl_author);
		lbl_author.setSize("", "");
		rightLPanel.setWidgetLeftWidth(lbl_author, 15.0, Unit.PX, 39.0, Unit.PX);
		rightLPanel.setWidgetTopHeight(lbl_author, 44.0, Unit.PX, 19.0, Unit.PX);
		dnmc__author.setStyleName("linkFull");
		dnmc__author.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		rightLPanel.add(dnmc__author);
		rightLPanel.setWidgetLeftWidth(dnmc__author, 55.0, Unit.PX, 350.0, Unit.PX);
		rightLPanel.setWidgetTopHeight(dnmc__author, 44.0, Unit.PX, 19.0, Unit.PX);
		lbl_id_book.setStyleName("labelFull");
		lbl_id_book.setDirectionEstimator(false);
		rightLPanel.add(lbl_id_book);
		rightLPanel.setWidgetLeftWidth(lbl_id_book, 15.0, Unit.PX, 160.0, Unit.PX);
		rightLPanel.setWidgetTopHeight(lbl_id_book, 164.0, Unit.PX, 16.0, Unit.PX);
		lbl_year_create.setStyleName("labelFull");
		rightLPanel.add(lbl_year_create);
		rightLPanel.setWidgetLeftWidth(lbl_year_create, 15.0, Unit.PX, 160.0, Unit.PX);
		rightLPanel.setWidgetTopHeight(lbl_year_create, 186.0, Unit.PX, 16.0, Unit.PX);
		lbl_publish.setStyleName("labelFull");
		rightLPanel.add(lbl_publish);
		rightLPanel.setWidgetLeftWidth(lbl_publish, 15.0, Unit.PX, 160.0, Unit.PX);
		rightLPanel.setWidgetTopHeight(lbl_publish, 208.0, Unit.PX, 16.0, Unit.PX);
		lbl_year_publish.setStyleName("labelFull");
		rightLPanel.add(lbl_year_publish);
		rightLPanel.setWidgetLeftWidth(lbl_year_publish, 15.0, Unit.PX, 160.0, Unit.PX);
		rightLPanel.setWidgetTopHeight(lbl_year_publish, 230.0, Unit.PX, 16.0, Unit.PX);
		lbl_isbn.setStyleName("labelFull");
		rightLPanel.add(lbl_isbn);
		rightLPanel.setWidgetLeftWidth(lbl_isbn, 15.0, Unit.PX, 160.0, Unit.PX);
		rightLPanel.setWidgetTopHeight(lbl_isbn, 252.0, Unit.PX, 16.0, Unit.PX);
		lbl_col_pages.setStyleName("labelFull");
		rightLPanel.add(lbl_col_pages);
		rightLPanel.setWidgetLeftWidth(lbl_col_pages, 15.0, Unit.PX, 160.0, Unit.PX);
		rightLPanel.setWidgetTopHeight(lbl_col_pages, 274.0, Unit.PX, 16.0, Unit.PX);
		lbl_cover.setStyleName("labelFull");
		rightLPanel.add(lbl_cover);
		rightLPanel.setWidgetLeftWidth(lbl_cover, 15.0, Unit.PX, 160.0, Unit.PX);
		rightLPanel.setWidgetTopHeight(lbl_cover, 296.0, Unit.PX, 16.0, Unit.PX);
		lbl_genre.setStyleName("labelFull");
		rightLPanel.add(lbl_genre);
		rightLPanel.setWidgetLeftWidth(lbl_genre, 15.0, Unit.PX, 160.0, Unit.PX);
		rightLPanel.setWidgetTopHeight(lbl_genre, 142.0, Unit.PX, 16.0, Unit.PX);
		layoutPanel_4.setStyleName("panelForSelect");
		rightLPanel.add(layoutPanel_4);
		rightLPanel.setWidgetLeftWidth(layoutPanel_4, 15.0, Unit.PX, 318.0, Unit.PX);
		rightLPanel.setWidgetTopHeight(layoutPanel_4, 69.0, Unit.PX, 55.0, Unit.PX);
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
		hPanelGenre.add(new GenreLabel(genre.get(0).getGenre(), genre.get(0).getIdGenre()));
		for (int i = 1; i < genre.size(); i++) {
			hPanelGenre.add(new HTML(",&nbsp"));
			hPanelGenre.add(new GenreLabel(genre.get(i).getGenre(), genre.get(i).getIdGenre()));
		}
		rightLPanel.add(hPanelGenre);
		rightLPanel.setWidgetLeftWidth(hPanelGenre, 178.0, Unit.PX, 264.0, Unit.PX);
		rightLPanel.setWidgetTopHeight(hPanelGenre, 142.0, Unit.PX, 16.0, Unit.PX);
		rightLPanel.add(dnmc_id_book);
		rightLPanel.setWidgetLeftWidth(dnmc_id_book, 178.0, Unit.PX, 264.0, Unit.PX);
		rightLPanel.setWidgetTopHeight(dnmc_id_book, 164.0, Unit.PX, 16.0, Unit.PX);
		rightLPanel.add(dnmc_year_create);
		rightLPanel.setWidgetLeftWidth(dnmc_year_create, 178.0, Unit.PX, 264.0, Unit.PX);
		rightLPanel.setWidgetTopHeight(dnmc_year_create, 186.0, Unit.PX, 16.0, Unit.PX);
		dnmc_publish.setStyleName("linkFull");
		rightLPanel.add(dnmc_publish);
		rightLPanel.setWidgetLeftWidth(dnmc_publish, 178.0, Unit.PX, 264.0, Unit.PX);
		rightLPanel.setWidgetTopHeight(dnmc_publish, 208.0, Unit.PX, 16.0, Unit.PX);
		rightLPanel.add(dnmc_year_publish);
		rightLPanel.setWidgetLeftWidth(dnmc_year_publish, 178.0, Unit.PX, 264.0, Unit.PX);
		rightLPanel.setWidgetTopHeight(dnmc_year_publish, 230.0, Unit.PX, 16.0, Unit.PX);
		rightLPanel.add(dnmc_isbn);
		rightLPanel.setWidgetLeftWidth(dnmc_isbn, 178.0, Unit.PX, 264.0, Unit.PX);
		rightLPanel.setWidgetTopHeight(dnmc_isbn, 252.0, Unit.PX, 16.0, Unit.PX);
		rightLPanel.add(dnmc_col_pages);
		rightLPanel.setWidgetLeftWidth(dnmc_col_pages, 178.0, Unit.PX, 264.0, Unit.PX);
		rightLPanel.setWidgetTopHeight(dnmc_col_pages, 274.0, Unit.PX, 16.0, Unit.PX);
		rightLPanel.add(dnmc_cover);
		rightLPanel.setWidgetLeftWidth(dnmc_cover, 178.0, Unit.PX, 264.0, Unit.PX);
		rightLPanel.setWidgetTopHeight(dnmc_cover, 296.0, Unit.PX, 16.0, Unit.PX);
		dnmc__author.setText(autor);
		dnmc_title.setText(title);
		dnmc_id_book.setText(String.valueOf(id_book));
		generalLPanel.add(topLPanel);
		generalLPanel.setWidgetLeftWidth(topLPanel, 0.0, Unit.PX, 700.0, Unit.PX);
		generalLPanel.setWidgetTopHeight(topLPanel, 0.0, Unit.PX, 30.0, Unit.PX);
		topLPanel.setSize("700px", "30px");
		topLPanel.add(link_list);
		topLPanel.setWidgetLeftWidth(link_list, 20.0, Unit.PX, 140.0, Unit.PX);
		topLPanel.setWidgetTopHeight(link_list, 14.0, Unit.PX, 16.0, Unit.PX);
		link_list.setStyleName("linkFull");
		vPanel.setSize("700px", "");
		vPanel.add(bottomLPanel);
		bottomLPanel.setSize("700px", "59px");
		bottomLPanel.add(Html_br);
		bottomLPanel.setWidgetLeftWidth(Html_br, 0.0, Unit.PX, 700.0, Unit.PX);
		bottomLPanel.setWidgetTopHeight(Html_br, 44.0, Unit.PX, 12.0, Unit.PX);
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

		dnmc__author.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				History.newItem("author=" + choose_book.getIdAuthor());
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

		Book callInput = new Book(this.choose_book.getAuthor(), this.choose_book.getTitle(), this.choose_book.getGenre(), this.choose_book.getImg());
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
