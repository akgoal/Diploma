package com.librarybooks.client.activities_and_places.view;

import java.util.Date;

import com.google.gwt.dom.client.Style.Unit;
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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.librarybooks.client.objects.BookEdit;
import com.librarybooks.shared.FieldVerifier;

public class AddBook extends Composite implements ClickHandler {

	Label l_img_src = new Label("Изображение*");
	FileUpload img_src = new FileUpload();

	Label l_price = new Label("Цена*");
	TextBox price = new TextBox();

	Label l_title = new Label("Название*");
	TextBox title = new TextBox();

	Label l_title_original = new Label("Оригинальное название");
	TextBox title_original = new TextBox();

	Label l_author = new Label("Автор*");
	TextBox author = new TextBox();

	Label l_genre = new Label("Жанр*");
	TextBox genre = new TextBox();

	Label l_year_create = new Label("Год создания");
	TextBox year_create = new TextBox();

	Label l_publish = new Label("Издательство");
	TextBox publish = new TextBox();

	Label l_year_publish = new Label("Год издания");
	TextBox year_publish = new TextBox();

	Label l_col_pages = new Label("Страниц");
	TextBox col_pages = new TextBox();

	Label l_cover = new Label("Переплет");
	TextBox cover = new TextBox();

	Label l_isbn = new Label("ISBN");
	TextBox isbn = new TextBox();

	Label l_specific = new Label("Описание");
	TextArea specific = new TextArea();

	VerticalPanel vPanel = new VerticalPanel();
	HorizontalPanel hPanel = new HorizontalPanel();
	Button button = new Button("Добавить");
	Button edt = new Button("Изменить");
	Button del = new Button("Удалить");
	BookEdit book;

	public void setAddBook(BookEdit bookEdit) {
		if (bookEdit != null) {
			title.setText(bookEdit.getTitle());
			title_original.setText(bookEdit.getTitle_original());
			author.setText(bookEdit.getAuthor());
			genre.setText(bookEdit.getGenre());
			year_create.setText(bookEdit.getYear_create());
			publish.setText(bookEdit.getPublish());
			year_publish.setText(bookEdit.getYear_publish());
			col_pages.setText(bookEdit.getCol_pages());
			cover.setText(bookEdit.getDescription());
			isbn.setText(bookEdit.getIsbn());
			specific.setText(bookEdit.getSpecific());
			price.setText(bookEdit.getPrice());
			title.setStyleName("error_focus", false);
			title_original.setStyleName("error_focus", false);
			author.setStyleName("error_focus", false);
			genre.setStyleName("error_focus", false);
			year_create.setStyleName("error_focus", false);
			publish.setStyleName("error_focus", false);
			year_publish.setStyleName("error_focus", false);
			col_pages.setStyleName("error_focus", false);
			cover.setStyleName("error_focus", false);
			isbn.setStyleName("error_focus", false);
			specific.setStyleName("error_focus", false);
			price.setStyleName("error_focus", false);
		}
	}

	public AddBook(Boolean b) {

		l_img_src.setStyleName("labelFull");
		img_src.getElement().getStyle().setColor("gray");

		l_price.setStyleName("labelFull");
		price.setVisibleLength(30);
		price.getElement().setPropertyString("placeholder", "Пример: 300");
		price.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (!Character.isDigit(event.getCharCode())) {
					((TextBox) event.getSource()).cancelKey();
				} else
					price.setStyleName("error_focus", false);
			}
		});
		price.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					title.setFocus(true);
				}

			}
		});

		l_title.setStyleName("labelFull");
		title.setVisibleLength(30);
		title.getElement().setPropertyString("placeholder", "Пример: 451 градус по Фаренгейту");
		title.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					title_original.setFocus(true);
				}
				title.setStyleName("error_focus", false);
			}
		});

		l_title_original.setStyleName("labelFull");
		title_original.setVisibleLength(30);
		title_original.getElement().setPropertyString("placeholder", "Пример: Fahrenheit 451");
		title_original.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					author.setFocus(true);
				}

			}
		});

		l_author.setStyleName("labelFull");
		author.setVisibleLength(30);
		author.getElement().setPropertyString("placeholder", "Пример: Рэй Брэдбери, др.");
		author.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					genre.setFocus(true);
					genre.setStyleName("error_focus", false);
				}
			}
		});
		author.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (!(event.getCharCode() + "").matches("[, 0-9A-Za-zа-яёА-ЯЁ]")) {
					((TextBox) event.getSource()).cancelKey();
				} else
					author.setStyleName("error_focus", false);
			}
		});

		l_genre.setStyleName("labelFull");
		genre.setVisibleLength(30);
		genre.getElement().setPropertyString("placeholder", "Пример: Фантастика, Антиутопия");
		genre.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					genre.setFocus(true);
				}

			}
		});
		genre.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (!(event.getCharCode() + "").matches("[, 0-9A-Za-zа-яёА-ЯЁ]")) {
					((TextBox) event.getSource()).cancelKey();
				} else
					genre.setStyleName("error_focus", false);
			}
		});

		l_year_create.setStyleName("labelFull");
		year_create.setVisibleLength(30);
		year_create.getElement().setPropertyString("placeholder", "Пример: 2015");
		year_create.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (!Character.isDigit(event.getCharCode())) {
					((TextBox) event.getSource()).cancelKey();
				} else
					year_create.setStyleName("error_focus", false);
			}
		});
		year_create.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					publish.setFocus(true);
				}

			}
		});

		l_publish.setStyleName("labelFull");
		publish.setVisibleLength(30);
		publish.getElement().setPropertyString("placeholder", "Пример: Эксмо");
		publish.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					year_publish.setFocus(true);
				}

			}
		});

		l_year_publish.setStyleName("labelFull");
		year_publish.setVisibleLength(30);
		year_publish.getElement().setPropertyString("placeholder", "Пример: 1953");
		year_publish.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (!Character.isDigit(event.getCharCode())) {
					((TextBox) event.getSource()).cancelKey();
				} else
					year_publish.setStyleName("error_focus", false);
			}
		});
		year_publish.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					col_pages.setFocus(true);
				}

			}
		});

		l_col_pages.setStyleName("labelFull");
		col_pages.setVisibleLength(30);
		col_pages.getElement().setPropertyString("placeholder", "Пример: 240");
		col_pages.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (!Character.isDigit(event.getCharCode())) {
					((TextBox) event.getSource()).cancelKey();
				} else
					col_pages.setStyleName("error_focus", false);
			}
		});
		col_pages.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					cover.setFocus(true);
				}
			}
		});

		l_cover.setStyleName("labelFull");
		cover.setVisibleLength(30);
		cover.getElement().setPropertyString("placeholder", "Пример: Мягкая обложка");
		cover.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					isbn.setFocus(true);
				}

			}
		});

		l_isbn.setStyleName("labelFull");
		isbn.setVisibleLength(30);
		isbn.getElement().setPropertyString("placeholder", "Пример: 978-5-699-45537-9");
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

		l_specific.setStyleName("labelFull");
		specific.setCharacterWidth(30);
		specific.setVisibleLines(5);

		final TextBox tb = new TextBox();
		tb.setMaxLength(10);

		tb.setVisibleLength(50);
		Grid grid = new Grid(13, 2);
		int row = 0;
		grid.setWidget(row, 0, l_img_src);
		grid.setWidget(row++, 1, img_src);
		grid.setWidget(row, 0, l_price);
		grid.setWidget(row++, 1, price);
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
		grid.setWidget(row, 0, l_cover);
		grid.setWidget(row++, 1, cover);
		grid.setWidget(row, 0, l_isbn);
		grid.setWidget(row++, 1, isbn);
		grid.setWidget(row, 0, l_specific);
		grid.setWidget(row++, 1, specific);

		vPanel.add(grid);
		grid.getRowFormatter().setVerticalAlign(row - 1, HasVerticalAlignment.ALIGN_TOP);
		grid.setCellPadding(5);
		button.getElement().getStyle().setMarginBottom(30, Unit.PX);
		button.getElement().getStyle().setMarginTop(15, Unit.PX);
		edt.getElement().getStyle().setMarginBottom(30, Unit.PX);
		edt.getElement().getStyle().setMarginTop(15, Unit.PX);
		del.getElement().getStyle().setMarginBottom(30, Unit.PX);
		del.getElement().getStyle().setMarginTop(15, Unit.PX);
		vPanel.setWidth("100%");
		vPanel.setCellHorizontalAlignment(grid, HasHorizontalAlignment.ALIGN_CENTER);

		if (b) {
			hPanel.clear();
			hPanel.add(edt);
			hPanel.add(del);
			vPanel.add(hPanel);
		} else {
			hPanel.clear();
			hPanel.add(button);
			vPanel.add(hPanel);
		}
		hPanel.setSpacing(10);
		vPanel.setCellHorizontalAlignment(hPanel, HasHorizontalAlignment.ALIGN_CENTER);

		button.addClickHandler(this);
		edt.addClickHandler(this);
		initWidget(vPanel);
	}

	public BookEdit getBookEdt() {
		return book;
	}

	public Button getButton() {
		return button;
	}

	public Button getButtonEdt() {
		return edt;
	}

	public Button getButtonDel() {
		return del;
	}

	@Override
	public void onClick(ClickEvent event) {
		book = null;
		boolean b = true;
		if (img_src.getFilename().isEmpty()) {
			Window.alert("Для добавления книги нужна обложка");
			b = false;
		}
		if (title.getText().trim().isEmpty()) {
			title.setStyleName("error_focus", true);
			title.setText("");
			b = false;
		}
		if (!FieldVerifier.isListSrting(author.getText()) | author.getText().isEmpty()) {
			author.setStyleName("error_focus", true);
			author.setText("");
			b = false;
		}
		if (!FieldVerifier.isListSrting(genre.getText()) | genre.getText().isEmpty()) {
			genre.setStyleName("error_focus", true);
			genre.setText("");
			b = false;
		}
		if (!FieldVerifier.isNumber(year_create.getText())) {
			year_create.setStyleName("error_focus", true);
			year_create.setText("");
			b = false;
		}
		if (!FieldVerifier.isNumber(year_publish.getText())) {
			year_publish.setStyleName("error_focus", true);
			year_publish.setText("");
			b = false;
		}
		if (!FieldVerifier.isNumber(col_pages.getText())) {
			col_pages.setStyleName("error_focus", true);
			col_pages.setText("");
			b = false;
		}
		if (!FieldVerifier.isNumber(price.getText())) {
			price.setStyleName("error_focus", true);
			price.setText("");
			b = false;
		}
		if (b) {
			book = new BookEdit(title.getText().trim(), title_original.getText().trim(),
					author.getText().trim(), genre.getText().trim(),
					img_src.getFilename().replace("C:\\fakepath\\", ""),
					year_create.getText().trim(), publish.getText().trim(),
					year_publish.getText().trim(), isbn.getText().trim(),
					col_pages.getText().trim(), cover.getText().trim(), specific.getText().trim(),
					new Date(), price.getText().trim());
		}

	}

}
