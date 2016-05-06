package com.librarybooks.client.activities_and_places.view;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class AdminViewImpl extends Composite implements AdminView {

	// SimplePanel viewPanel = new SimplePanel();
	Element nameSpan = DOM.createSpan();
	// Button button = new Button("send");
	HTML one = new HTML();
	HTML two = new HTML();
	HorizontalPanel hPanel = new HorizontalPanel();

	public AdminViewImpl() {
		hPanel.add(one);
		one.setHTML("Good bay, ");
		hPanel.add(two);
		// hPanel.add(button);
		initWidget(hPanel);
		// viewPanel.getElement().appendChild(nameSpan);
		// initWidget(viewPanel);
	}

	@Override
	public void setName(String name) {
		two.setHTML(name);
		// nameSpan.setInnerText("Good-bye, " + name);
	}

}