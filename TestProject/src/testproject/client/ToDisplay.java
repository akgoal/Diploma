package testproject.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import testproject.client.objects.Book;
import testproject.client.objects.Genre;
import testproject.client.widgets.BookWidget;
import testproject.client.widgets.SelectedBookWidget;

public class ToDisplay {

	private static final String SERVER_ERROR = "An error occurred while " + "attempting to contact the server. Please check your network " + "connection and try again.";

	private final BookServiceAsync bookService = GWT.create(BookService.class);

	public void findBooksByAuthor(long id) {

		bookService.findBooksByAuthorBook(id, new AsyncCallback<ArrayList<Book>>() {
			public void onFailure(Throwable caught) {
				Label text = new Label(SERVER_ERROR);
				RootPanel.get("listBook").clear();
				RootPanel.get("listBook").add(text);
			}

			public void onSuccess(ArrayList<Book> result) {
				RootPanel.get("listBook").clear();
				FlowPanel panel = new FlowPanel();
				for (int i = 0; i < result.size(); i++) {

					String author = new String((result.get(i)).getAuthor());
					String title = new String((result.get(i)).getTitle());
					ArrayList<Genre> genre = new ArrayList<Genre>((result.get(i)).getGenre());
					String img_src = new String((result.get(i)).getImg());
					long id_author = (result.get(i)).getIdAuthor();
					long id_book = (result.get(i)).getIdBook();
					BookWidget bb = new BookWidget(id_book, author, id_author, title, genre, img_src);
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
				RootPanel.get("listBook").clear();
				RootPanel.get("listBook").add(text);
			}

			public void onSuccess(ArrayList<Book> result) {
				RootPanel.get("listBook").clear();
				FlowPanel panel = new FlowPanel();
				for (int i = 0; i < result.size(); i++) {

					String author = new String((result.get(i)).getAuthor());
					String title = new String((result.get(i)).getTitle());
					ArrayList<Genre> genre = new ArrayList<Genre>((result.get(i)).getGenre());
					String img_src = new String((result.get(i)).getImg());
					long id_author = (result.get(i)).getIdAuthor();
					long id_book = (result.get(i)).getIdBook();
					BookWidget bb = new BookWidget(id_book, author, id_author, title, genre, img_src);
					panel.add(bb);

				}
				RootPanel.get("listBook").add(panel);
			}
		});
	}

	public void allBooks() {

		bookService.sendServer(new AsyncCallback<ArrayList<Book>>() {
			public void onFailure(Throwable caught) {
				Label text = new Label(SERVER_ERROR);
				RootPanel.get("listBook").clear();
				RootPanel.get("listBook").add(text);
			}

			public void onSuccess(ArrayList<Book> result) {
				RootPanel.get("listBook").clear();
				FlowPanel panel = new FlowPanel();
				for (int i = 0; i < result.size(); i++) {

					String author = new String((result.get(i)).getAuthor());
					String title = new String((result.get(i)).getTitle());
					ArrayList<Genre> genre = new ArrayList<Genre>((result.get(i)).getGenre());
					String img_src = new String((result.get(i)).getImg());
					long id_author = (result.get(i)).getIdAuthor();
					long id_book = (result.get(i)).getIdBook();
					BookWidget bb = new BookWidget(id_book, author, id_author, title, genre, img_src);
					panel.add(bb);
					RootPanel.get("listBook").add(panel);

				}
			}
		});
	}

	public void selectBooks(long id_book) {

		bookService.selectBook(id_book, new AsyncCallback<Book>() {
			public void onFailure(Throwable caught) {
				Label text = new Label(SERVER_ERROR);
				RootPanel.get("listBook").add(text);
			}

			public void onSuccess(Book result) {
				RootPanel.get("listBook").clear();
				FlowPanel panel = new FlowPanel();
				String author = new String(result.getAuthor());
				String title = new String(result.getTitle());
				ArrayList<Genre> genre = new ArrayList<Genre>(result.getGenre());
				String img_src = new String(result.getImg());
				long id_author = result.getIdAuthor();
				long id_book = result.getIdBook();
				SelectedBookWidget bb = new SelectedBookWidget(id_book, author, id_author, title, genre, img_src);
				panel.add(bb);
				RootPanel.get("listBook").add(panel);

			}

		});
	}

}
