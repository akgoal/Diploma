package com.librarybooks.client.activities_and_places.view;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.librarybooks.client.activities_and_places.places.AdminPlace;
import com.librarybooks.client.objects.Book;

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
		addBook = new AddBook(true);
		sPanel.add(addBook);
		panel.add(lb);
		hp.add(panel);
		hp.add(sPanel);
		htmlPanel.add(hp);

	}

}