package com.librarybooks.client.widgets;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.librarybooks.client.activities_and_places.places.UserPlace;
import com.librarybooks.client.activities_and_places.view.UserView.Presenter;

public class ListLabel extends Composite implements ClickHandler {

	private long choose_id;
	private String choose_type;
	private Label label = new Label();
	Presenter listener;

	public ListLabel(Presenter _listener, String type, String name, long id) {
		this.listener = _listener;
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
		listener.goTo(new UserPlace(choose_type + "=" + choose_id + "&p=1"));
		// History.newItem("UserPlace:" + choose_type + "=" + choose_id + "&p=1");
	}

}
