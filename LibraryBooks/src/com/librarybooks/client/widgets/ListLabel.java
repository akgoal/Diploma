package com.librarybooks.client.widgets;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

public class ListLabel extends Composite implements ClickHandler {

	private long choose_id;
	private String choose_type;
	private Label label = new Label();

	public ListLabel(String type, String name, long id) {

		choose_id = id;
		choose_type = type;
		label.setText(name);
		if (type == "genre")
			label.setStyleName("linkGenre");
		else
			label.setStyleName("linkAuthor");
		label.addClickHandler(this);
		initWidget(label);
	}

	@Override
	public void onClick(ClickEvent event) {
		History.newItem("UserPlace:" + choose_type + "=" + choose_id + "&p=1");
	}

}
