package testproject.client.widgets;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

public class GenreLabel extends Composite implements ClickHandler {

	private long choose_id;

	public GenreLabel(String genre, long id_genre) {

		choose_id = id_genre;

		Label label = new Label(genre);
		label.setStyleName("linkFull");
		label.addClickHandler(this);
		initWidget(label);
	}

	@Override
	public void onClick(ClickEvent event) {
		History.newItem("genre=" + choose_id);
	}

}
