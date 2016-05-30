package com.librarybooks.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.librarybooks.client.OrderServiceAsync;
import com.sun.org.apache.xpath.internal.operations.String;

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
	HTML all_price;
	@UiField
	SpanElement basket;
	@UiField
	Button button;
	@UiField
	RadioButton radio_one_week;
	@UiField
	RadioButton radio_two_week;
	@UiField
	RadioButton radio_three_week;
	@UiField
	RadioButton radio_one_month;
	@UiField
	RadioButton buy;

	int check;

	public Basket() {
		initWidget(uiBinder.createAndBindUi(this));
		popupPanel.addStyleName("popup");
		popupPanel.setCellHorizontalAlignment(button, HasHorizontalAlignment.ALIGN_CENTER);
		all_price.setStyleName("price");

		radio_one_week.setValue(true);
		check = 1;
		radio_one_week.setHTML("На 1 неделю<span>Вам вернется 80% суммы</span>");
		radio_two_week.setHTML("На 2 недели<span>Вам вернется 70% суммы</span>");
		radio_three_week.setHTML("На 3 недели<span>Вам вернется 60% суммы</span>");
		radio_one_month.setHTML("На 1 месяц<span>Вам вернется 50% суммы</span>");
		buy.setHTML("Купить");

		radio_one_week.addValueChangeHandler(new ValueChangeHandler<java.lang.Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				check = 1;
			}
		});

		radio_two_week.addValueChangeHandler(new ValueChangeHandler<java.lang.Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				check = 2;
			}
		});
		radio_three_week.addValueChangeHandler(new ValueChangeHandler<java.lang.Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				check = 3;
			}
		});
		radio_one_month.addValueChangeHandler(new ValueChangeHandler<java.lang.Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				check = 4;
			}
		});
		buy.addValueChangeHandler(new ValueChangeHandler<java.lang.Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				check = 0;
			}
		});

		// RadioButton radioButton = new RadioButton("sport", sport);
	}

	public SpanElement getSpanElement() {
		return basket;
	}

	public HTMLPanel getHTMLPanel() {
		return panelFlex;
	}

	public Button getButton() {
		return button;
	}

	public VerticalPanel getPopup() {
		return popupPanel;
	}

	public int getCheck() {
		return check;
	}

	public void setPrice(int price) {
		all_price.setHTML(price + "<span>ք</span>");
	}

}
