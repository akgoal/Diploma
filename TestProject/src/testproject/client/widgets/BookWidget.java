package testproject.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import testproject.client.BookService;
import testproject.client.BookServiceAsync;
import testproject.client.ToDisplay;
import testproject.client.objects.Book;

public class BookWidget extends Composite implements ClickHandler {

	private static final String SERVER_ERROR = "An error occurred while " + "attempting to contact the server. Please check your network " + "connection and try again.";

	private final BookServiceAsync bookService = GWT.create(BookService.class);

	private ToDisplay dspl = new ToDisplay();

	private Label l_author = new Label();
	private Label l_title = new Label();
	private Label l_genre = new Label();
	private Button button = new Button("Подробнее");
	private Image img = new Image();
	private Book choose_book = new Book();
	private Button chooseButton = new Button("В корзину");
	private Label back_book = new Label();

	public BookWidget(long id_book, String author, long id_author, String title, String genre, long id_genre, String img_src) {

		// img_src = "http://files.books.ru/pic/643001-644000/643136/1966072586c.jpg";
		img_src = GWT.getModuleBaseURL() + "img/template.jpg";

		choose_book.setBook(id_book, author, id_author, title, genre, id_genre, img_src);

		l_author.setText(author);
		l_title.setText(title);
		l_genre.setText(genre);
		img.setUrl(img_src);
		img.setPixelSize(img.getWidth() * 200 / img.getHeight(), 200);

		l_author.getElement().setId("link_center");
		l_title.getElement().setId("text_center");
		l_genre.getElement().setId("link_center");
		button.setStyleName("button_center");
		chooseButton.setStyleName("button_center");

		FlowPanel flowPanel = new FlowPanel();
		VerticalPanel panel = new VerticalPanel();
		panel.add(back_book);
		panel.add(img);
		panel.add(l_author);
		panel.add(l_title);
		panel.add(l_genre);
		panel.add(button);
		panel.add(chooseButton);

		button.addClickHandler(this);

		l_author.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				History.newItem("author=" + choose_book.getIdAuthor());
				// dspl.findBooksByAuthor(choose_book.getIdAuthor());
			}
		});
		l_genre.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				History.newItem("genre=" + choose_book.getIdGenre());
				// dspl.findBooksByGenre(choose_book.getIdGenre());
			}
		});
		chooseButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				chooseBookToServer();
			}
		});

		flowPanel.add(panel);
		flowPanel.addStyleName("item");
		initWidget(flowPanel);

	}

	public void onClick(ClickEvent event) {

		/*
		 * int left = Window.getClientWidth() / 2 - 150; int top = 100; dialogBox.setPopupPosition(left, top); dialogBox.show(); closeButton.setFocus(true);
		 * 
		 */
		History.newItem("book=" + choose_book.getIdBook());
		// dspl.selectBooks(choose_book.getIdBook());

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
