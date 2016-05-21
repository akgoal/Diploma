package com.librarybooks.client.activities_and_places.view;

import java.util.Date;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Style.VerticalAlign;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.librarybooks.client.objects.BookEdit;

public class AddBook extends Composite {

	/* TextBox author; TextBox title; TextBox title_original; TextBox genre; FileUpload img_src; TextBox year_create; TextBox publish; TextBox year_publish; TextBox isbn; TextBox col_pages; TextBox cover; TextArea specific; Label l_author; Label l_title; Label l_title_original; Label l_genre; Label
	 * l_img_src; Label l_year_create; Label l_publish; Label l_year_publish; Label l_isbn; Label l_col_pages; Label l_cover; Label l_specific; */

	Label l_img_src = new Label("Изображение");
	FileUpload img_src = new FileUpload();

	Label l_title = new Label("Название");
	TextBox title = new TextBox();

	Label l_title_original = new Label("Оригинальное название");
	TextBox title_original = new TextBox();

	Label l_author = new Label("Автор");
	TextBox author = new TextBox();

	Label l_genre = new Label("Жанр");
	TextBox genre = new TextBox();

	Label l_year_create = new Label("Год создания");
	TextBox year_create = new TextBox();

	Label l_publish = new Label("Издательство");
	TextBox publish = new TextBox();

	Label l_year_publish = new Label("Год издания");
	TextBox year_publish = new TextBox();

	Label l_col_pages = new Label("Страниц");
	TextBox col_pages = new TextBox();

	Label l_description = new Label("Переплет");
	TextBox description = new TextBox();

	Label l_isbn = new Label("ISBN");
	TextBox isbn = new TextBox();

	Label l_specific = new Label("Описание");
	TextArea specific = new TextArea();

	VerticalPanel vPanel = new VerticalPanel();
	Button button = new Button("Добавить");
	BookEdit book;

	public AddBook() {

		// l_img_src = new Label("Изображение");
		l_img_src.setStyleName("labelFull");
		// img_src = new FileUpload();

		// l_title = new Label("Название");
		l_title.setStyleName("labelFull");
		// title = new TextBox();
		title.setVisibleLength(30);
		title.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					title_original.setFocus(true);
				}

			}
		});

		// l_title_original = new Label("Название");
		l_title_original.setStyleName("labelFull");
		// title_original = new TextBox();
		title_original.setVisibleLength(30);
		title_original.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					author.setFocus(true);
				}

			}
		});

		// l_author = new Label("Автор");
		l_author.setStyleName("labelFull");
		// author = new TextBox();
		author.setVisibleLength(30);
		author.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					genre.setFocus(true);
				}

			}
		});
		author.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (!(event.getCharCode() + "").matches("[, 0-9A-Za-zа-яёА-ЯЁ]")) {
					((TextBox) event.getSource()).cancelKey();
				}
			}
		});

		// l_genre = new Label("Жанр");
		l_genre.setStyleName("labelFull");
		// genre = new TextBox();
		genre.setVisibleLength(30);
		genre.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					year_create.setFocus(true);
				}

			}
		});
		genre.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (!(event.getCharCode() + "").matches("[, 0-9A-Za-zа-яёА-ЯЁ]")) {
					((TextBox) event.getSource()).cancelKey();
				}
			}
		});

		// l_year_create = new Label("Год создания");
		l_year_create.setStyleName("labelFull");
		// year_create = new TextBox();
		year_create.setVisibleLength(30);
		year_create.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (!Character.isDigit(event.getCharCode())) {
					((TextBox) event.getSource()).cancelKey();
				}
			}
		});
		year_create.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					publish.setFocus(true);
				}

			}
		});

		// l_publish = new Label("Издательство");
		l_publish.setStyleName("labelFull");
		// publish = new TextBox();
		publish.setVisibleLength(30);
		publish.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					year_publish.setFocus(true);
				}

			}
		});

		// l_year_publish = new Label("Год издания");
		l_year_publish.setStyleName("labelFull");
		// year_publish = new TextBox();
		year_publish.setVisibleLength(30);
		year_publish.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (!Character.isDigit(event.getCharCode())) {
					((TextBox) event.getSource()).cancelKey();
				}
			}
		});
		year_publish.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					col_pages.setFocus(true);
				}

			}
		});

		// l_col_pages = new Label("Страниц");
		l_col_pages.setStyleName("labelFull");
		// col_pages = new TextBox();
		col_pages.setVisibleLength(30);
		col_pages.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (!Character.isDigit(event.getCharCode())) {
					((TextBox) event.getSource()).cancelKey();
				}
			}
		});
		col_pages.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					description.setFocus(true);
				}
			}
		});

		// l_cover = new Label("Переплет");
		l_description.setStyleName("labelFull");
		// cover = new TextBox();
		description.setVisibleLength(30);
		description.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					isbn.setFocus(true);
				}

			}
		});

		// l_isbn = new Label("ISBN");
		l_isbn.setStyleName("labelFull");
		// isbn = new TextBox();
		isbn.setVisibleLength(30);
		isbn.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					specific.setFocus(true);
				}

			}
		});
		isbn.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (!(event.getCharCode() + "").matches("[0-9A-Za-zа-яёА-ЯЁ]")) {
					((TextBox) event.getSource()).cancelKey();
				}
			}
		});

		// l_specific = new Label("Описание");
		l_specific.setStyleName("labelFull");
		// specific = new TextArea();
		specific.setCharacterWidth(30);
		specific.setVisibleLines(5);

		final TextBox tb = new TextBox();
		tb.setMaxLength(10);

		tb.setVisibleLength(50);
		Grid grid = new Grid(12, 2);
		int row = 0;
		grid.setWidget(row, 0, l_img_src);
		grid.setWidget(row++, 1, img_src);
		grid.setWidget(row, 0, l_title);
		grid.setWidget(row++, 1, title);
		grid.setWidget(row, 0, l_title_original);
		grid.setWidget(row++, 1, title_original);
		grid.setWidget(row, 0, l_author);
		grid.setWidget(row++, 1, author);
		grid.setWidget(row, 0, l_genre);
		grid.setWidget(row++, 1, genre);
		grid.setWidget(row, 0, l_year_create);
		grid.setWidget(row++, 1, year_create);
		grid.setWidget(row, 0, l_publish);
		grid.setWidget(row++, 1, publish);
		grid.setWidget(row, 0, l_year_publish);
		grid.setWidget(row++, 1, year_publish);
		grid.setWidget(row, 0, l_col_pages);
		grid.setWidget(row++, 1, col_pages);
		grid.setWidget(row, 0, l_description);
		grid.setWidget(row++, 1, description);
		grid.setWidget(row, 0, l_isbn);
		grid.setWidget(row++, 1, isbn);
		grid.setWidget(row, 0, l_specific);
		grid.setWidget(row++, 1, specific);

		vPanel.add(grid);
		grid.getRowFormatter().setVerticalAlign(row - 1, HasVerticalAlignment.ALIGN_TOP);
		grid.setCellPadding(5);
		button.getElement().getStyle().setMarginBottom(30, Unit.PX);
		button.getElement().getStyle().setMarginTop(15, Unit.PX);
		vPanel.setWidth("100%");
		vPanel.setCellHorizontalAlignment(grid, HasHorizontalAlignment.ALIGN_CENTER);

		vPanel.add(button);
		vPanel.setCellHorizontalAlignment(button, HasHorizontalAlignment.ALIGN_CENTER);

		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				book = new BookEdit(title.getText().trim(), title_original.getText().trim(),
						author.getText().trim(), genre.getText().trim(),
						img_src.getFilename().replace("C:\\fakepath\\", ""),
						year_create.getText().trim(), publish.getText().trim(),
						year_publish.getText().trim(), isbn.getText().trim(),
						col_pages.getText().trim(), description.getText().trim(),
						specific.getText().trim(), new Date().toString());
			}
		});

		initWidget(vPanel);
	}

	public BookEdit getBookEdt() {
		return book;
	}

	public Button getButton() {
		return button;
	}
}
