package testproject.client.widgets;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import testproject.client.BookService;
import testproject.client.BookServiceAsync;
import testproject.client.GreetingService;
import testproject.client.GreetingServiceAsync;
import testproject.client.objects.Book;

public class BookWidget extends Composite implements ClickHandler {

	private static final String SERVER_ERROR = "An error occurred while " + "attempting to contact the server. Please check your network " + "connection and try again.";

	private final BookServiceAsync bookService = GWT.create(BookService.class);

	private Label l_autor = new Label();
	private Label l_book = new Label();
	private Label l_type = new Label();
	private Button button = new Button("Подробнее");
	private Image img = new Image();
	private Book choose_book = new Book();
	private DialogBox dialogBox = new DialogBox();
	private Button closeButton = new Button("Закрыть");
	private Button chooseButton = new Button("В корзину");
	private Label full_autor = new Label();
	private Label full_book = new Label();
	private Label full_type = new Label();
	private Image full_img = new Image();
	private Label back_book = new Label();

	public BookWidget(long id_book, String autor, long id_autor, String title, String genre, long id_genre, String img_src) {

		// img_src = "http://files.books.ru/pic/643001-644000/643136/1966072586c.jpg";
		img_src = GWT.getModuleBaseURL() + "img/template.jpg";

		choose_book.setBook(id_book, autor, id_autor, title, genre, id_genre, img_src);

		l_autor.setText(autor);
		l_book.setText(title);
		l_type.setText(genre);
		img.setUrl(img_src);
		img.setPixelSize(img.getWidth() * 200 / img.getHeight(), 200);

		l_autor.getElement().setId("link_center");
		l_book.getElement().setId("text_center");
		l_type.getElement().setId("link_center");
		button.setStyleName("button_center");

		FlowPanel flowPanel = new FlowPanel();
		VerticalPanel panel = new VerticalPanel();
		panel.add(img);
		panel.add(l_autor);
		panel.add(l_book);
		panel.add(l_type);
		panel.add(button);

		closeButton.getElement().setId("closeButton");
		dialogBox.setAnimationEnabled(true);
		dialogBox.setGlassEnabled(true);

		button.addClickHandler(this);

		l_autor.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				findBooksByAutor(choose_book.getIdAuthor());
			}
		});
		l_type.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				findBooksByGenre(choose_book.getIdGenre());
			}
		});
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});
		chooseButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				chooseBookToServer();
			}
		});

		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.addStyleName("dialogBox");
		dialogVPanel.add(full_autor);
		dialogVPanel.add(full_book);
		dialogVPanel.add(full_type);
		dialogVPanel.add(full_img);
		dialogVPanel.add(back_book);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(chooseButton);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		flowPanel.add(panel);
		flowPanel.addStyleName("item");
		initWidget(flowPanel);

	}

	public void onClick(ClickEvent event) {

		int left = Window.getClientWidth() / 2 - 150;
		int top = 100;
		dialogBox.setPopupPosition(left, top);
		dialogBox.show();
		closeButton.setFocus(true);
		full_autor.setText(choose_book.getAuthor());
		full_book.setText(choose_book.getTitle());
		full_type.setText(choose_book.getGenre());
		full_img.setUrl(choose_book.getImg());
		full_img.setPixelSize(full_img.getWidth() * 300 / full_img.getHeight(), 300);

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
	}

	public void findBooksByAutor(long id) {

		bookService.findBooksByAuthorBook(id, new AsyncCallback<ArrayList<Book>>() {
			public void onFailure(Throwable caught) {
				Label text = new Label(SERVER_ERROR);
				RootPanel.get("listBook").add(text);
			}

			public void onSuccess(ArrayList<Book> result) {
				RootPanel.get("listBook").clear();
				FlowPanel panel = new FlowPanel();
				for (int i = 0; i < result.size(); i++) {

					String author = new String((result.get(i)).getAuthor());
					String title = new String((result.get(i)).getTitle());
					String genre = new String((result.get(i)).getGenre());
					String img_src = new String((result.get(i)).getImg());
					long id_author = (result.get(i)).getIdAuthor();
					long id_genre = (result.get(i)).getIdGenre();
					long id_book = (result.get(i)).getIdBook();
					BookWidget bb = new BookWidget(id_book, author, id_author, title, genre, id_genre, img_src);
					panel.add(bb);

				}
				RootPanel.get("listBook").add(panel);

			}
		});
	}

	public void findBooksByGenre(long id) {

		bookService.findBooksByGenreBook(id, new AsyncCallback<ArrayList<Book>>() {
			public void onFailure(Throwable caught) {
				Label text = new Label(SERVER_ERROR);
				RootPanel.get("listBook").add(text);
			}

			public void onSuccess(ArrayList<Book> result) {
				RootPanel.get("listBook").clear();
				FlowPanel panel = new FlowPanel();
				for (int i = 0; i < result.size(); i++) {

					String author = new String((result.get(i)).getAuthor());
					String title = new String((result.get(i)).getTitle());
					String genre = new String((result.get(i)).getGenre());
					String img_src = new String((result.get(i)).getImg());
					long id_author = (result.get(i)).getIdAuthor();
					long id_genre = (result.get(i)).getIdGenre();
					long id_book = (result.get(i)).getIdBook();
					BookWidget bb = new BookWidget(id_book, author, id_author, title, genre, id_genre, img_src);
					panel.add(bb);

				}
				RootPanel.get("listBook").add(panel);
			}
		});
	}
}
