package com.librarybooks.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.librarybooks.client.BookServiceAsync;

public class Rate extends Composite {
	private static RateUiBinder uiBinder = GWT.create(RateUiBinder.class);

	interface RateUiBinder extends UiBinder<Widget, Rate> {
	}

	BookServiceAsync bookService;
	long id;

	@UiField
	HTML r1;
	@UiField
	HTML r2;
	@UiField
	HTML r3;
	@UiField
	HTML r4;
	@UiField
	HTML r5;

	public Rate(BookServiceAsync _bookService, long _id, int rate) {
		this.bookService = _bookService;
		this.id = _id;
		initWidget(uiBinder.createAndBindUi(this));
		r1.setHTML("<span>&#9734</span>");
		r2.setHTML("<span>&#9734</span>");
		r3.setHTML("<span>&#9734</span>");
		r4.setHTML("<span>&#9734</span>");
		r5.setHTML("<span>&#9734</span>");

		change(rate);

		if (_bookService != null) {
			r1.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					bookService.changeRate(id, 1, new AsyncCallback<Integer>() {
						public void onFailure(Throwable caught) {
							Window.alert("Ошибка!");
						}

						public void onSuccess(Integer rate) {
							change(rate);
						}
					});
				}
			});
			r2.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					bookService.changeRate(id, 2, new AsyncCallback<Integer>() {
						public void onFailure(Throwable caught) {
							Window.alert("Ошибка!");
						}

						public void onSuccess(Integer rate) {
							change(rate);
						}
					});
				}
			});
			r3.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					bookService.changeRate(id, 3, new AsyncCallback<Integer>() {
						public void onFailure(Throwable caught) {
							Window.alert("Ошибка!");
						}

						public void onSuccess(Integer rate) {
							change(rate);
						}
					});
				}
			});
			r4.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					bookService.changeRate(id, 4, new AsyncCallback<Integer>() {
						public void onFailure(Throwable caught) {
							Window.alert("Ошибка!");
						}

						public void onSuccess(Integer rate) {
							change(rate);
						}
					});
				}
			});
			r5.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					bookService.changeRate(id, 5, new AsyncCallback<Integer>() {
						public void onFailure(Throwable caught) {
							Window.alert("Ошибка!");
						}

						public void onSuccess(Integer rate) {
							change(rate);
						}
					});
				}
			});
		}
	}

	public void change(int rate) {
		switch (rate) {
		case 0:
			r1.setStyleName("state", false);
			r2.setStyleName("state", false);
			r3.setStyleName("state", false);
			r4.setStyleName("state", false);
			r5.setStyleName("state", false);
			break;
		case 1:
			r1.setStyleName("state", true);
			r2.setStyleName("state", false);
			r3.setStyleName("state", false);
			r4.setStyleName("state", false);
			r5.setStyleName("state", false);
			break;
		case 2:
			r1.setStyleName("state", true);
			r2.setStyleName("state", true);
			r3.setStyleName("state", false);
			r4.setStyleName("state", false);
			r5.setStyleName("state", false);
			break;
		case 3:
			r1.setStyleName("state", true);
			r2.setStyleName("state", true);
			r3.setStyleName("state", true);
			r4.setStyleName("state", false);
			r5.setStyleName("state", false);
			break;
		case 4:
			r1.setStyleName("state", true);
			r2.setStyleName("state", true);
			r3.setStyleName("state", true);
			r4.setStyleName("state", true);
			r5.setStyleName("state", false);
			break;
		case 5:
			r1.setStyleName("state", true);
			r2.setStyleName("state", true);
			r3.setStyleName("state", true);
			r4.setStyleName("state", true);
			r5.setStyleName("state", true);
			break;
		default:
			break;
		}
	};

}
