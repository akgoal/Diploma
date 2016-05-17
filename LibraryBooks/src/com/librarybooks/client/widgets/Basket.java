package com.librarybooks.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class Basket extends Composite {
	private static BasketUiBinder uiBinder = GWT.create(BasketUiBinder.class);

	interface BasketUiBinder extends UiBinder<Widget, Basket> {
	}

	@UiField
	HTMLPanel basketPanel;
	@UiField
	HTMLPanel popupPanel;
	@UiField
	SpanElement basket;

	public Basket() {

		initWidget(uiBinder.createAndBindUi(this));
		popupPanel.addStyleName("popup");
	}

	public SpanElement getSpanElement() {
		return basket;
	}

	public HTMLPanel getHTMLPanel() {
		return popupPanel;
	}
}
