package com.librarybooks.client.activities_and_places.activities;

import java.util.ArrayList;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.librarybooks.client.BookService;
import com.librarybooks.client.BookServiceAsync;
import com.librarybooks.client.ClientFactory;
import com.librarybooks.client.activities_and_places.places.AdminPlace;
import com.librarybooks.client.activities_and_places.view.AdminView;
import com.librarybooks.client.objects.Book;
import com.librarybooks.client.objects.BookEdit;

public class AdminActivity extends AbstractActivity implements AdminView.Presenter {
	private ClientFactory clientFactory;
	private String info;
	private AdminView userView;
	BookEdit book;
	long id;
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	private final BookServiceAsync bookService = GWT.create(BookService.class);

	public AdminActivity(AdminPlace place, ClientFactory clientFactory) {
		this.info = place.getParam();
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {

		userView = clientFactory.getAdminView();

		if (info.equals("add")) {
			userView.setAddView(info);
			userView.getAddBook().getButton().addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					book = userView.getAddBook().getBookEdt();
					if (book != null) {
						bookService.addBook(book, new AsyncCallback<Void>() {

							public void onFailure(Throwable caught) {
								Window.alert("Ошибка!!!");
							}

							@Override
							public void onSuccess(Void result) {
								Window.alert("Книга успешно добавлена!!!");
							}
						});
					}

				}
			});

		} else if (info.equals("change")) {
			bookService.sendServer(new AsyncCallback<ArrayList<Book>>() {

				public void onFailure(Throwable caught) {
					Window.alert("Ошибка!");
				}

				@Override
				public void onSuccess(ArrayList<Book> books) {
					userView.setChangeView(info, books);
					userView.getAddBook().getButtonDel().addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							int id = Integer.valueOf(userView.getListBox()
									.getValue(userView.getListBox().getSelectedIndex()));
							bookService.DelBook(id, new AsyncCallback<Void>() {

								@Override
								public void onFailure(Throwable caught) {
									Window.alert("Ошибка!");
								}

								@Override
								public void onSuccess(Void result) {
									int index = userView.getListBox().getSelectedIndex();
									Window.alert("Удаление прошло успешно");
									userView.getListBox().removeItem(index);

								}
							});
						}
					});
					userView.getAddBook().getButtonEdt().addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {

							int id = Integer.valueOf(userView.getListBox()
									.getValue(userView.getListBox().getSelectedIndex()));
							BookEdit book = userView.getAddBook().getBookEdt();
							bookService.EditBook(id, book, new AsyncCallback<Void>() {

								@Override
								public void onFailure(Throwable caught) {
									Window.alert("Ошибка!");
								}

								@Override
								public void onSuccess(Void result) {
									bookService.sendServer(new AsyncCallback<ArrayList<Book>>() {

										public void onFailure(Throwable caught) {
											Window.alert("Ошибка!");
										}

										@Override
										public void onSuccess(ArrayList<Book> books) {
											int index = userView.getListBox().getSelectedIndex();
											String name = userView.getAddBook().getBookEdt()
													.getTitle();
											String author = userView.getAddBook().getBookEdt()
													.getAuthor();
											Window.alert("Изменение прошло успешно");
											userView.getListBox().setItemText(index,
													name + " — " + author);
										}
									});

								}
							});
						}
					});
					userView.getListBox().addChangeHandler(new ChangeHandler() {

						@Override
						public void onChange(ChangeEvent event) {
							String index = userView.getListBox()
									.getValue(userView.getListBox().getSelectedIndex());

							bookService.selectBookEdit(Integer.valueOf(index),
									new AsyncCallback<BookEdit>() {

										@Override
										public void onFailure(Throwable caught) {
											Window.alert("Ошибка!!!");

										}

										@Override
										public void onSuccess(BookEdit result) {
											userView.getAddBook().setAddBook(result);
										}

									});

						}

					});

				}
			});

		}

		userView.setPresenter(this);
		containerWidget.setWidget(userView.asWidget());
	}

	@Override
	public String mayStop() {
		return null;
	}

	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}

	native void consoleLog(String message) /*-{
		console.log("me:" + message);
	}-*/;
}