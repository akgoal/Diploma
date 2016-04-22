package testproject.client.widgets;

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

import testproject.client.GreetingService;
import testproject.client.GreetingServiceAsync;
import testproject.client.objects.Book;

public class BookWidget extends Composite implements ClickHandler {

	private static final String SERVER_ERROR = "An error occurred while " + "attempting to contact the server. Please check your network " + "connection and try again.";

	private final GreetingServiceAsync greetingServic = GWT.create(GreetingService.class);

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

	public BookWidget(String autor, String book, String type, String img_src) {

		choose_book.setBook(autor, book, type, img_src);

		l_autor.setText(autor);
		l_book.setText(book);
		l_type.setText(type);
		button.addClickHandler(this);

		l_autor.getElement().setId("text_center");
		l_book.getElement().setId("text_center");
		l_type.getElement().setId("text_center");
		button.setStyleName("button_center");

		FlowPanel flowPanel = new FlowPanel();
		VerticalPanel panel = new VerticalPanel();
		panel.add(img);
		panel.add(l_autor);
		panel.add(l_book);
		panel.add(l_type);
		panel.add(button);
		img.setUrl(img_src);
		try {
			img.setPixelSize(img.getWidth() * 200 / img.getHeight(), 200);
		} catch (Exception e) {
			img.setUrl(img_src);
			img.setPixelSize(img.getWidth() * 200 / img.getHeight(), 200);
		}

		closeButton.getElement().setId("closeButton");
		dialogBox.setAnimationEnabled(true);
		dialogBox.setGlassEnabled(true);
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
		// dialogBox.center();
		int left = Window.getClientWidth() / 2 - 150;
		int top = 100;
		dialogBox.setPopupPosition(left, top);
		dialogBox.show();
		closeButton.setFocus(true);
		full_autor.setText(this.choose_book.getAutor());
		full_book.setText(this.choose_book.getNameBook());
		full_type.setText(this.choose_book.getType());
		full_img.setUrl(this.choose_book.getImg());
		full_img.setPixelSize(full_img.getWidth() * 300 / full_img.getHeight(), 300);

	}

	public void chooseBookToServer() {

		Book callInput = new Book();
		callInput.setBook(this.choose_book.getAutor(), this.choose_book.getNameBook(), this.choose_book.getType(), this.choose_book.getImg());
		greetingServic.bookToServer(callInput, new AsyncCallback<Book>() {
			public void onFailure(Throwable caught) {
				Label text = new Label(SERVER_ERROR);
				RootPanel.get("listBook").add(text);
			}

			public void onSuccess(Book result) {

				back_book.setText(result.getAutor() + " " + result.getNameBook());
			}
		});
	}
}