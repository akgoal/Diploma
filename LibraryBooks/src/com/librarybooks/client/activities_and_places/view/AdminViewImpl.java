package com.librarybooks.client.activities_and_places.view;

import java.util.ArrayList;

import javax.swing.text.html.HTML;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
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
import com.google.gwt.user.client.ui.Widget;
import com.librarybooks.client.BookServiceAsync;
import com.librarybooks.client.activities_and_places.places.AdminPlace;
import com.librarybooks.client.objects.Book;
import com.librarybooks.client.objects.BookEdit;

public class AdminViewImpl extends Composite implements AdminView {

	private static AdminViewImplUiBinder uiBinder = GWT.create(AdminViewImplUiBinder.class);

	interface AdminViewImplUiBinder extends UiBinder<Widget, AdminViewImpl> {
	}

	@UiField
	HTMLPanel menuBar;

	@UiField
	HTMLPanel htmlPanel;

	Presenter listener;
	long id;
	AddBook addBook;
	BookServiceAsync bookService;

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
		// menuMain.addItem("Two", true, command);
		// menuMain.addItem("Other", true, command);
		menuBar.add(menuMain);
	}

	@Override
	public void setAddView(String ref) {
		htmlPanel.clear();
		addBook = new AddBook(null);
		htmlPanel.add(addBook);

	}

	public AddBook getAddBook() {
		return addBook;
	}

	@Override
	public void setPresenter(Presenter listener) {
		this.listener = listener;
	}

	@Override
	public void setChangeView(String ref, ArrayList<Book> books, BookServiceAsync _bookService) {
		// TODO Auto-generated method stub
		this.bookService = _bookService;
		htmlPanel.clear();
		addBook = new AddBook(null);
		HorizontalPanel hp = new HorizontalPanel();
		HTMLPanel panel = new HTMLPanel("");
		panel.setWidth("300px");
		panel.setHeight("400px");
		final ListBox lb = new ListBox();
		for (Book book : books) {
			lb.addItem(book.getTitle() + " — " + book.getAuthor().get(0).getAuthor(),
					book.getIdBook() + "");
		}
		lb.setVisibleItemCount(30);
		lb.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				int index = lb.getSelectedIndex();
				// Window.alert(lb.getItemText(index));
				Window.alert(lb.getValue(index));
				bookService.selectBookEdit(Integer.valueOf(lb.getValue(index)),
						new AsyncCallback<BookEdit>() {

							public void onFailure(Throwable caught) {
								Window.alert("Отсутствует метод вызова");
							}

							@Override
							public void onSuccess(BookEdit book) {

								addBook = new AddBook(book);
							}
						});
			}
		});
		panel.add(lb);
		hp.add(panel);
		hp.add(addBook);
		htmlPanel.add(hp);
	}

}