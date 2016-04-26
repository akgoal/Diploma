package testproject.client.widgets;

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

import testproject.client.BookService;
import testproject.client.BookServiceAsync;
import testproject.client.ToDisplay;
import testproject.client.objects.Book;

public class SelectedBookWidget extends Composite {

	private static final String SERVER_ERROR = "An error occurred while " + "attempting to contact the server. Please check your network " + "connection and try again.";

	private final BookServiceAsync bookService = GWT.create(BookService.class);

	private ToDisplay dspl = new ToDisplay();

	private Book choose_book = new Book();
	private Button closeButton = new Button("Назад");
	private Button chooseButton = new Button("В корзину");
	private Label full_author = new Label();
	private Label full_title = new Label();
	private Label full_genre = new Label();
	private Image full_img = new Image();
	private Label back_book = new Label();

	public SelectedBookWidget(long id_book, String autor, long id_autor, String title, String genre, long id_genre, String img_src) {

		// img_src = "http://files.books.ru/pic/643001-644000/643136/1966072586c.jpg";
		img_src = GWT.getModuleBaseURL() + "img/template.jpg";

		choose_book.setBook(id_book, autor, id_autor, title, genre, id_genre, img_src);

		RootPanel.get("listBook").clear();
		full_author.setText(autor);
		full_title.setText(title);
		full_genre.setText(genre);
		full_img.setUrl(img_src);
		full_img.setPixelSize(full_img.getWidth() * 300 / full_img.getHeight(), 300);

		full_author.getElement().setId("link_center");
		full_title.getElement().setId("text_center");
		full_genre.getElement().setId("link_center");
		closeButton.setStyleName("button_center");
		chooseButton.setStyleName("button_center");

		VerticalPanel panel = new VerticalPanel();

		panel.add(back_book);
		panel.add(full_img);
		panel.add(full_author);
		panel.add(full_title);
		panel.add(full_genre);
		panel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		panel.add(chooseButton);
		panel.add(closeButton);

		full_author.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				History.newItem("author=" + choose_book.getIdAuthor());
				dspl.findBooksByAuthor(choose_book.getIdAuthor());
			}
		});
		full_genre.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				History.newItem("genre=" + choose_book.getIdAuthor());
				dspl.findBooksByGenre(choose_book.getIdGenre());
			}
		});
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// dialogBox.hide();
				History.back();
			}
		});
		chooseButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				chooseBookToServer();
			}
		});

		initWidget(panel);

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
