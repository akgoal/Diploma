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
import testproject.client.objects.CallInput;
import testproject.client.widgets.BookWidget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TestProject implements EntryPoint {

	private static final String SERVER_ERROR = "An error occurred while " + "attempting to contact the server. Please check your network " + "connection and try again.";

	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

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
					sendToServer();
				}
			}

			private void sendToServer() {

				CallInput callInput = new CallInput();
				callInput.setText("all");
				greetingService.sendServer(callInput, new AsyncCallback<ArrayList<Book>>() {
					public void onFailure(Throwable caught) {
						Label text = new Label(SERVER_ERROR);
						RootPanel.get("listBook").add(text);
					}

					public void onSuccess(ArrayList<Book> result) {
						RootPanel.get("listBook").clear();
						FlowPanel panel = new FlowPanel();
						for (int i = 0; i < 8; i++) {
							String autor = new String((result.get(i)).getAutor());
							String book = new String((result.get(i)).getNameBook());
							String type = new String((result.get(i)).getType());
							String img_src = new String((result.get(i)).getImg());
							BookWidget bb = new BookWidget(autor, book, type, img_src);
							panel.add(bb);

						}
						RootPanel.get("listBook").add(panel);

					}
				});
			}
		}

		BookHandler handlerBook = new BookHandler();
		sendBooks.addClickHandler(handlerBook);
	}

}
