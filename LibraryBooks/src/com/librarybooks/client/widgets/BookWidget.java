package com.librarybooks.client.widgets;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.librarybooks.client.BookService;
import com.librarybooks.client.BookServiceAsync;
import com.librarybooks.client.ClientFactory;
import com.librarybooks.client.activities_and_places.places.UserPlace;
import com.librarybooks.client.objects.Book;
import com.librarybooks.client.objects.Genre;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;

public class BookWidget extends Composite implements ClickHandler {

	private static final String SERVER_ERROR = "An error occurred while " + "attempting to contact the server. Please check your network " + "connection and try again.";

	private final BookServiceAsync bookService = GWT.create(BookService.class);

	private Label l_author = new Label();
	private Label l_title = new Label();
	private Label l_genre = new Label();
	private Button button = new Button("Подробнее");
	private Book choose_book = new Book();
	private Button chooseButton = new Button("Добавить");
	private Label back_book = new Label();
	private final VerticalPanel verticalPanel_1 = new VerticalPanel();
	FlowPanel flowPanel = new FlowPanel();
	VerticalPanel verticalPanel = new VerticalPanel();
	HorizontalPanel hPanel = new HorizontalPanel();
	HTMLPanel panel = new HTMLPanel("");
	HTMLPanel butPanel = new HTMLPanel("");

	public BookWidget(long id_book, String author, long id_author, String title, ArrayList<Genre> genre, String img_src) {

		img_src = GWT.getModuleBaseURL() + "img/template.jpg";

		choose_book.setBook(id_book, author, id_author, title, genre, img_src);

		String html_img = new String("<div img class=\"img_book\">" + "<img align=\"center\" id=\"imageBook\" src=\"" + img_src + "\">" + "</div>");
		flowPanel.setStyleName("elem");
		l_author.setStyleName("linkFull");
		l_title.setStyleName("linkToBook");
		l_genre.setStyleName("linkFull");
		button.setStyleName("buttonAddIn");
		chooseButton.setStyleName("buttonAddIn");

		panel.getElement().setId("info");
		verticalPanel_1.getElement().setId("but");
		l_author.getElement().setId("link_center");
		l_title.getElement().setId("text_center");
		button.getElement().setId("but_info");
		chooseButton.getElement().setId("but_info");

		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		l_title.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel_1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel_1.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

		l_author.setText(author);
		l_title.setText(title);
		flowPanel.add(back_book);
		flowPanel.add(verticalPanel);
		verticalPanel.add(new HTML(html_img));
		verticalPanel.add(l_author);
		verticalPanel.add(l_title);
		hPanel.add(new GenreLabel(genre.get(0).getGenre(), genre.get(0).getIdGenre()));
		for (int i = 1; i < genre.size(); i++) {
			hPanel.add(new HTML(",&nbsp"));
			hPanel.add(new GenreLabel(genre.get(i).getGenre(), genre.get(i).getIdGenre()));
		}
		verticalPanel.add(hPanel);
		butPanel.add(button);
		butPanel.add(chooseButton);
		verticalPanel_1.add(butPanel);
		panel.add(verticalPanel);
		panel.add(verticalPanel_1);
		flowPanel.add(panel);

		verticalPanel_1.setSize("200px", "310px");
		l_author.setWidth("170px");
		l_title.setSize("170px", "");
		button.setSize("130px", "30px");
		chooseButton.setSize("130px", "30px");

		l_author.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ClientFactory clientFactory = GWT.create(ClientFactory.class);
				PlaceController placeController = clientFactory.getPlaceController();
				placeController.goTo(new UserPlace("author=" + choose_book.getIdAuthor()));
			}
		});
		chooseButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				chooseBookToServer();
			}
		});

		button.addClickHandler(this);

		initWidget(flowPanel);
	}

	public void onClick(ClickEvent event) {

		ClientFactory clientFactory = GWT.create(ClientFactory.class);
		PlaceController placeController = clientFactory.getPlaceController();
		placeController.goTo(new UserPlace("book=" + choose_book.getIdBook()));

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
