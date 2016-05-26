package com.librarybooks.client.activities_and_places.view;

import java.util.ArrayList;

import javax.swing.text.html.HTML;

import org.springframework.asm.Label;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.librarybooks.client.BookService;
import com.librarybooks.client.BookServiceAsync;
import com.librarybooks.client.activities_and_places.places.AdminPlace;
import com.librarybooks.client.objects.Book;
import com.librarybooks.client.objects.BookEdit;

import sun.java2d.pipe.SpanShapeRenderer.Simple;

public class AdminViewImpl extends Composite implements AdminView {

	private static AdminViewImplUiBinder uiBinder = GWT.create(AdminViewImplUiBinder.class);

	interface AdminViewImplUiBinder extends UiBinder<Widget, AdminViewImpl> {
	}

	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	private final BookServiceAsync bookService = GWT.create(BookService.class);

	@UiField
	HTMLPanel menuBar;

	@UiField
	HTMLPanel htmlPanel;

	Presenter listener;
	long id;
	AddBook addBook;
	// BookServiceAsync bookService;
	HorizontalPanel hp = new HorizontalPanel();
	HTMLPanel panel = new HTMLPanel("");
	SimplePanel sPanel = new SimplePanel();

	public AdminViewImpl() {

		initWidget(uiBinder.createAndBindUi(this));

		MenuBar menuMain = new MenuBar();
		menuMain.addItem("Добавить книгу", new Command() {
			public void execute() {
				listener.goTo(new AdminPlace("add"));
			}
		});
		menuMain.addItem("Изменить книгу", new Command() {
			public void execute() {
				listener.goTo(new AdminPlace("change"));
			}
		});

		menuBar.add(menuMain);
	}

	@Override
	public void setAddView(String ref) {
		htmlPanel.clear();
		addBook = new AddBook(false);
		htmlPanel.add(addBook);

	}

	public AddBook getAddBook() {
		return addBook;
	}

	void setPanel(String s) {

	}

	ListBox lb = new ListBox();

	public ListBox getListBox() {
		return lb;
	}

	@Override
	public void setPresenter(Presenter listener) {
		this.listener = listener;
	}

	@Override
	public void setChangeView(String ref, ArrayList<Book> books) {
		// this.bookService = _bookService;
		htmlPanel.clear();
		panel.clear();
		sPanel.clear();

		panel.setWidth("300px");
		panel.setHeight("400px");

		for (Book book : books) {
			lb.addItem(book.getTitle() + " — " + book.getAuthor().get(0).getAuthor(),
					book.getIdBook() + "");
		}
		lb.setVisibleItemCount(30);
		/* lb.addChangeHandler(new ChangeHandler() {
		 * 
		 * @Override public void onChange(ChangeEvent event) { int index = lb.getSelectedIndex(); Window.alert(lb.getValue(index)); id = Integer.valueOf(lb.getValue(index)); bookService.selectBookEdit(Integer.valueOf(lb.getValue(index)), new AsyncCallback<BookEdit>() {
		 * 
		 * public void onFailure(Throwable caught) { Window.alert("Отсутствует метод вызова"); sPanel.clear(); addBook = new AddBook(null); sPanel.add(addBook); }
		 * 
		 * @Override public void onSuccess(BookEdit book) { sPanel.clear(); addBook = new AddBook(book); // id = Integer.valueOf(lb.getValue(index)); sPanel.add(addBook); addBook.getButtonDel().addClickHandler(new ClickHandler() {
		 * 
		 * @Override public void onClick(ClickEvent event) { // book = addBook.getBookEdt(); // if (book != null) { bookService.DelBook(79, new AsyncCallback<Void>() {
		 * 
		 * public void onFailure(Throwable caught) { // ChangeViewERROR(); }
		 * 
		 * @Override public void onSuccess(Void result) { // TODO Auto-generated method stub } }); // }
		 * 
		 * } }); } });
		 * 
		 * } }); */
		// sPanel.
		addBook = new AddBook(true);
		sPanel.add(addBook);
		panel.add(lb);
		hp.add(panel);
		hp.add(sPanel);
		htmlPanel.add(hp);

	}

}