package com.librarybooks.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class SearchPane extends Composite {

	private String choose_text;

	private static SearchPaneUiBinder uiBinder = GWT.create(SearchPaneUiBinder.class);

	interface SearchPaneUiBinder extends UiBinder<Widget, SearchPane> {
	}

	@UiField
	HorizontalPanel searchHPanel;
	@UiField
	TextBox searchBox;
	@UiField
	Button searchButton;

	private String param = "";

	public SearchPane() {

		initWidget(uiBinder.createAndBindUi(this));
		searchBox.setVisibleLength(50);
		searchBox.setStyleName("searchBox");
		searchBox.setText(param);
		// searchBox.set
		// searchBox.setTitle("Поиск...");
		searchButton.setHTML("<img src=\"" + GWT.getHostPageBaseURL() + "img/search.png\">");
		searchButton.setStyleName("searchButton");
		searchHPanel.setStyleName("searchHPanel");

	}

	public String getText() {
		return choose_text;
	}

	public void setText(String param) {
		this.param = param;
		searchBox.setText(param);
		searchBox.setValue(param);
	}

	public TextBox getSearchBox() {
		return searchBox;
	}

	public Button getSearchButton() {
		return searchButton;
	}

}
