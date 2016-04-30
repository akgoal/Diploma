package testproject.client.widgets;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import testproject.client.objects.Book;
import testproject.client.objects.Genre;
import testproject.client.BookService;
import testproject.client.BookServiceAsync;

import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;

public class BookWidget extends Composite implements ClickHandler {

	private static final String SERVER_ERROR = "An error occurred while " + "attempting to contact the server. Please check your network " + "connection and try again.";

	private final BookServiceAsync bookService = GWT.create(BookService.class);

	private Label l_author = new Label();
	private Label l_title = new Label();
	private Label l_genre = new Label();
	private Button button = new Button("Подробнее");
	private Image img = new Image();
	private Book choose_book = new Book();
	private Button chooseButton = new Button("Добавить");
	private Label back_book = new Label();
	private final LayoutPanel layoutPanel = new LayoutPanel();
	private final VerticalPanel verticalPanel_1 = new VerticalPanel();

	public BookWidget(long id_book, String author, long id_author, String title, ArrayList<Genre> genre, String img_src) {

		img_src = GWT.getModuleBaseURL() + "img/template.jpg";

		choose_book.setBook(id_book, author, id_author, title, genre, img_src);

		FlowPanel flowPanel = new FlowPanel();
		flowPanel.setStyleName("item");
		flowPanel.add(back_book);
		flowPanel.addStyleName("item");
		initWidget(flowPanel);
		flowPanel.setSize("197px", "427px");

		flowPanel.add(layoutPanel);
		layoutPanel.setSize("", "");

		VerticalPanel verticalPanel = new VerticalPanel();
		flowPanel.add(verticalPanel);
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.setSize("197px", "346px");
		img.setStyleName("imageBook");
		img.addClickHandler(this);
		verticalPanel.add(img);
		img.setUrl(img_src);
		img.setSize("", "250px");
		l_author.setStyleName("linkFull");
		verticalPanel.add(l_author);
		l_author.setWidth("170px");

		l_author.setText(author);

		l_author.getElement().setId("link_center");

		l_author.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				History.newItem("author=" + choose_book.getIdAuthor());
			}
		});
		l_title.setStyleName("linkToBook");
		l_title.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.add(l_title);
		l_title.setText(title);
		l_title.setSize("170px", "");

		l_genre.setStyleName("linkFull");

		l_genre.setWidth("170px");
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.add(new GenreLabel(genre.get(0).getGenre(), genre.get(0).getIdGenre()));
		for (int i = 1; i < genre.size(); i++) {
			hPanel.add(new HTML(",&nbsp"));
			hPanel.add(new GenreLabel(genre.get(i).getGenre(), genre.get(i).getIdGenre()));
		}
		verticalPanel.add(hPanel);
		l_title.getElement().setId("text_center");
		verticalPanel_1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel_1.setVerticalAlignment(HasVerticalAlignment.ALIGN_BOTTOM);

		flowPanel.add(verticalPanel_1);
		verticalPanel_1.setSize("196px", "80px");
		verticalPanel_1.add(button);
		button.setSize("130px", "30px");
		button.setStyleName("buttonAddIn");
		verticalPanel_1.add(chooseButton);
		chooseButton.setSize("130px", "30px");
		chooseButton.setStyleName("buttonAddIn");
		chooseButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				chooseBookToServer();
			}
		});

		l_title.addClickHandler(this);
		button.addClickHandler(this);

	}

	public void onClick(ClickEvent event) {

		History.newItem("book=" + choose_book.getIdBook());

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
