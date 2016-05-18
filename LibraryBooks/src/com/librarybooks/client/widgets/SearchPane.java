package com.librarybooks.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.librarybooks.client.activities_and_places.places.UserPlace;

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

		searchBox.addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					String param_search = searchBox.getText();
					if (!param_search.isEmpty() & !param_search.matches("[\\s]+")) {
						History.newItem("UserPlace:search="
								+ param_search.trim().replaceAll("[\\s]+", "\u005F") + "&p=1");
					}
				}
			}
		});
		searchButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String param_search = searchBox.getText();
				if (!param_search.isEmpty() & !param_search.matches("[\\s]+")) {
					History.newItem("UserPlace:search="
							+ param_search.trim().replaceAll("[\\s]+", "\u005F") + "&p=1");
				}
			}
		});

	}

	public TextBox getSearchBox() {
		return searchBox;
	}

	public Button getSearchButton() {
		return searchButton;
	}

}
