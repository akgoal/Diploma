package com.librarybooks.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class SearchPane extends Composite {

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
		searchButton.setHTML("<img src=\"" + GWT.getHostPageBaseURL() + "img/search.png\">");
		searchButton.setStyleName("searchButton");
		searchHPanel.setStyleName("searchHPanel");

	}

	public TextBox getSearchBox() {
		return searchBox;
	}

	public Button getSearchButton() {
		return searchButton;
	}

}
