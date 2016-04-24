package testproject.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import testproject.client.objects.Book;
import testproject.client.widgets.BookWidget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TestProject implements EntryPoint {

	private static final String SERVER_ERROR = "An error occurred while " + "attempting to contact the server. Please check your network " + "connection and try again.";

	private final GreetingServiceAsync greetService = GWT.create(GreetingService.class);

	public void onModuleLoad() {

		final Button sendBooks = new Button("Все книги");

		RootPanel.get("sendButtonContainer").add(sendBooks);
		sendBooks.setStyleName("button_center");

		class BookHandler implements ClickHandler, KeyUpHandler {

			public void onClick(ClickEvent event) {
				sendToServer();
			}

			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					// sendToServer();
				}
			}

			private void sendToServer() {

				greetService.sendServer(new AsyncCallback<ArrayList<Book>>() {
					public void onFailure(Throwable caught) {
						Label text = new Label(SERVER_ERROR);
						RootPanel.get("listBook").add(text);
					}

					public void onSuccess(ArrayList<Book> result) {
						RootPanel.get("listBook").clear();
						FlowPanel panel = new FlowPanel();
						for (int i = 0; i < result.size(); i++) {

							String autor = new String((result.get(i)).getAutor());
							String title = new String((result.get(i)).getTitle());
							String genre = new String((result.get(i)).getGenre());
							String img_src = new String((result.get(i)).getImg());
							long id_autor = (result.get(i)).getIdAutor();
							long id_genre = (result.get(i)).getIdGenre();
							long id_book = (result.get(i)).getIdBook();
							BookWidget bb = new BookWidget(id_book, autor, id_autor, title, genre, id_genre, img_src);
							panel.add(bb);
							RootPanel.get("listBook").add(panel);

						}
					}
				});
			}
		}

		BookHandler handlerBook = new BookHandler();
		sendBooks.addClickHandler(handlerBook);
	}

}
