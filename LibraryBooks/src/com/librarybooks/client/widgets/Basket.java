package com.librarybooks.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Basket extends Composite {
	private static BasketUiBinder uiBinder = GWT.create(BasketUiBinder.class);

	interface BasketUiBinder extends UiBinder<Widget, Basket> {
	}

	@UiField
	HTMLPanel basketPanel;
	@UiField
	VerticalPanel popupPanel;
	@UiField
	HTMLPanel panelFlex;
	@UiField
	SpanElement basket;
	@UiField
	Button button;

	public Basket() {

		initWidget(uiBinder.createAndBindUi(this));
		popupPanel.addStyleName("popup");
		popupPanel.setCellHorizontalAlignment(button, HasHorizontalAlignment.ALIGN_CENTER);
		button.getElement().getStyle().setMargin(10, Unit.PX);
	}

	public SpanElement getSpanElement() {
		return basket;
	}

	public HTMLPanel getHTMLPanel() {
		return panelFlex;
	}
}
