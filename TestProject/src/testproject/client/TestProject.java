package testproject.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TestProject implements EntryPoint {

	private ToDisplay dspl = new ToDisplay();

	public void onModuleLoad() {

		final Button sendBooks = new Button("Все книги");

		RootPanel.get("sendButtonContainer").add(sendBooks);
		sendBooks.setStyleName("button_center");

		class BookHandler implements ClickHandler, KeyUpHandler {

			public void onClick(ClickEvent event) {
				History.newItem("allbooks");
				dspl.allBooks();
			}

			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					// sendToServer();
				}
			}

		}

		BookHandler handlerBook = new BookHandler();
		sendBooks.addClickHandler(handlerBook);

		History.newItem("main");
		History.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				// TODO Auto-generated method stub
				if (event.getValue().equals("main")) {
					RootPanel.get("listBook").clear();
				}
				if (event.getValue().equals("allbooks")) {
					dspl.allBooks();
				}
				if (event.getValue().matches("author=[0-9]+")) {
					long id_author = Long.valueOf(event.getValue().replaceAll("author=", ""));
					dspl.findBooksByAuthor(id_author);
				}
				if (event.getValue().matches("genre=[0-9]+")) {
					long id_genre = Long.valueOf(event.getValue().replaceAll("genre=", ""));
					dspl.findBooksByGenre(id_genre);
				}
				if (event.getValue().matches("book=[0-9]+")) {
					long id_book = Long.valueOf(event.getValue().replaceAll("book=", ""));
					dspl.selectBooks(id_book);
				}

			}
		});
	}

}
