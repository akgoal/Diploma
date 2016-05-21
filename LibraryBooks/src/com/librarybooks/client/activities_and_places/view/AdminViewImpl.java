package com.librarybooks.client.activities_and_places.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.Widget;
import com.librarybooks.client.activities_and_places.places.AdminPlace;

public class AdminViewImpl extends Composite implements AdminView {

	private static AdminViewImplUiBinder uiBinder = GWT.create(AdminViewImplUiBinder.class);

	interface AdminViewImplUiBinder extends UiBinder<Widget, AdminViewImpl> {
	}

	@UiField
	HTMLPanel menuBar;

	@UiField
	HTMLPanel htmlPanel;
	// VerticalPanel vPanel;

	Presenter listener;
	long id;
	AddBook addBook;

	public AdminViewImpl() {

		initWidget(uiBinder.createAndBindUi(this));

		Command command = new Command() {
			public void execute() {
				listener.goTo(new AdminPlace("add"));
			}
		};
		MenuBar menuMain = new MenuBar();
		menuMain.addItem("Добавить книгу", true, command);
		// menuMain.addItem("One", true, command);
		// menuMain.addItem("Two", true, command);
		// menuMain.addItem("Other", true, command);
		menuBar.add(menuMain);
	}

	@Override
	public void setAddView(String ref) {

		addBook = new AddBook();
		htmlPanel.add(addBook);

	}

	public AddBook getAddBook() {
		return addBook;
	}

	@Override
	public void setPresenter(Presenter listener) {
		this.listener = listener;
	}

}