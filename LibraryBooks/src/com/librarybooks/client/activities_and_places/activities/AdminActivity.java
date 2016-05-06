package com.librarybooks.client.activities_and_places.activities;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.librarybooks.client.ClientFactory;
import com.librarybooks.client.activities_and_places.places.AdminPlace;
import com.librarybooks.client.activities_and_places.view.AdminView;

public class AdminActivity extends AbstractActivity {
	private ClientFactory clientFactory;
	// Name that will be appended to "Good-bye, "
	private String name;

	public AdminActivity(AdminPlace place, ClientFactory clientFactory) {
		this.name = place.getAdminName();
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		AdminView goodbyeView = clientFactory.getAdminView();
		goodbyeView.setName(name);
		containerWidget.setWidget(goodbyeView.asWidget());
	}
}