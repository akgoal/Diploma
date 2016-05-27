package com.librarybooks.client.activities_and_places.view;

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
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Reg extends Composite {

	Label l_name = new Label("Имя*");
	TextBox name = new TextBox();

	Label l_surname = new Label("Фамилия*");
	TextBox surname = new TextBox();

	Label l_patronymic = new Label("Отчество");
	TextBox patronymic = new TextBox();

	Label l_email = new Label("e-mail*");
	TextBox email = new TextBox();

	Label l_login = new Label("Логин*");
	TextBox login = new TextBox();

	Label l_password = new Label("Пароль*");
	TextBox password = new TextBox();

	Label l_password2 = new Label("Повторите пароль*");
	TextBox password2 = new TextBox();

	VerticalPanel vPanel = new VerticalPanel();
	HTML title = new HTML("<h2>Регистрация нового пользователя</h2>");
	Button button = new Button("Зарегистрироваться");

	public Reg() {

		vPanel.add(title);

		l_name.setStyleName("labelFull");
		name.setVisibleLength(30);
		// name.getElement().setPropertyString("placeholder", "");
		name.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					surname.setFocus(true);
					name.setStyleName("error_focus", false);
				}
			}
		});
		name.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (!(event.getCharCode() + "").matches("[A-Za-zа-яёА-ЯЁ]")) {
					((TextBox) event.getSource()).cancelKey();
					name.setStyleName("error_focus", true);
				} else
					name.setStyleName("error_focus", false);
			}
		});

		l_surname.setStyleName("labelFull");
		surname.setVisibleLength(30);
		// surname.getElement().setPropertyString("placeholder", "");
		surname.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					patronymic.setFocus(true);
					surname.setStyleName("error_focus", false);
				}

			}
		});
		surname.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (!(event.getCharCode() + "").matches("[A-Za-zа-яёА-ЯЁ]")) {
					((TextBox) event.getSource()).cancelKey();
					surname.setStyleName("error_focus", true);
				} else
					surname.setStyleName("error_focus", false);
			}
		});

		l_patronymic.setStyleName("labelFull");
		patronymic.setVisibleLength(30);
		// patronymic.getElement().setPropertyString("placeholder", "");
		patronymic.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					email.setFocus(true);
					patronymic.setStyleName("error_focus", false);
				}
			}
		});
		patronymic.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (!(event.getCharCode() + "").matches("[A-Za-zа-яёА-ЯЁ]")) {
					((TextBox) event.getSource()).cancelKey();
					patronymic.setStyleName("error_focus", true);
				} else
					patronymic.setStyleName("error_focus", false);
			}
		});

		l_email.setStyleName("labelFull");
		email.setVisibleLength(30);
		// email.getElement().setPropertyString("placeholder", "");
		email.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					login.setFocus(true);
					if (email.getText()
							.matches("\\w+([\\.-_]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{1,4}"))
						email.setStyleName("error_focus", false);
					else
						email.setStyleName("error_focus", true);
				}

			}
		});
		email.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (!(event.getCharCode() + "").matches("[A-Za-z@._]")) {
					((TextBox) event.getSource()).cancelKey();
					email.setStyleName("error_focus", true);
				} else if (email.getText()
						.matches("\\w+([\\.-_]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{1,4}"))
					email.setStyleName("error_focus", false);
				else
					email.setStyleName("error_focus", true);
			}
		});

		l_login.setStyleName("labelFull");
		login.setVisibleLength(30);
		// login.getElement().setPropertyString("placeholder", "");
		login.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (!(event.getCharCode() + "").matches("[A-Za-zа-яёА-ЯЁ_]")) {
					((TextBox) event.getSource()).cancelKey();
					login.setStyleName("error_focus", true);
				} else
					login.setStyleName("error_focus", false);
			}
		});
		login.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					password.setFocus(true);
				}

			}
		});

		l_password.setStyleName("labelFull");
		password.setVisibleLength(30);
		// password.getElement().setPropertyString("placeholder", "");
		password.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					password2.setFocus(true);
				}

			}
		});
		password.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (!(event.getCharCode() + "").matches("[0-9A-Za-zа-яёА-ЯЁ_]")) {
					((TextBox) event.getSource()).cancelKey();
					password.setStyleName("error_focus", true);
				} else
					password.setStyleName("error_focus", false);
			}
		});

		l_password2.setStyleName("labelFull");
		password2.setVisibleLength(30);
		// password2.getElement().setPropertyString("placeholder", "");
		password2.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (!(event.getCharCode() + "").matches("[0-9A-Za-zа-яёА-ЯЁ_]")) {
					((TextBox) event.getSource()).cancelKey();
				} else if (password.getText().equals(password.getText())) {
					password.setStyleName("error_focus", false);
					password2.setStyleName("error_focus", false);
				}

				else {
					password.setStyleName("error_focus", true);
					password2.setStyleName("error_focus", true);
				}

			}
		});
		password2.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					if (password.getText().equals(password.getText()))
						password.setStyleName("error_focus", false);
					else
						password.setStyleName("error_focus", true);
					button.click();
				}

			}
		});

		final TextBox tb = new TextBox();
		tb.setMaxLength(10);

		tb.setVisibleLength(50);
		Grid grid = new Grid(7, 2);
		int row = 0;
		grid.setWidget(row, 0, l_name);
		grid.setWidget(row++, 1, name);
		grid.setWidget(row, 0, l_surname);
		grid.setWidget(row++, 1, surname);
		grid.setWidget(row, 0, l_patronymic);
		grid.setWidget(row++, 1, patronymic);
		grid.setWidget(row, 0, l_email);
		grid.setWidget(row++, 1, email);
		grid.setWidget(row, 0, l_login);
		grid.setWidget(row++, 1, login);
		grid.setWidget(row, 0, l_password);
		grid.setWidget(row++, 1, password);
		grid.setWidget(row, 0, l_password2);
		grid.setWidget(row++, 1, password2);

		vPanel.add(grid);
		grid.getRowFormatter().setVerticalAlign(row - 1, HasVerticalAlignment.ALIGN_TOP);
		grid.setCellPadding(5);
		button.getElement().getStyle().setMarginTop(15, Unit.PX);
		vPanel.setWidth("100%");
		vPanel.setStyleName("reg");
		vPanel.setCellHorizontalAlignment(grid, HasHorizontalAlignment.ALIGN_CENTER);

		vPanel.add(button);
		vPanel.setCellHorizontalAlignment(button, HasHorizontalAlignment.ALIGN_CENTER);

		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				boolean b = true;
				if (!name.getText().matches("[0-9A-Za-zа-яёА-ЯЁ_]+")) {
					// title.setFocus(true);
					name.setStyleName("error_focus", true);
					name.setText("");
					b = false;
				}
				if (!surname.getText().matches("[0-9A-Za-zа-яёА-ЯЁ_]+")) {
					// author.setFocus(true);
					surname.setStyleName("error_focus", true);
					surname.setText("");
					b = false;
				}
				if (!patronymic.getText().matches("[0-9A-Za-zа-яёА-ЯЁ_]*")) {
					// genre.setFocus(true);
					patronymic.setStyleName("error_focus", true);
					patronymic.setText("");
					b = false;
				}
				if (!email.getText().matches("\\w+([\\.-_]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}")) {
					// year_create.setFocus(true);
					email.setStyleName("error_focus", true);
					email.setText("");
					b = false;
				}
				if (!login.getText().matches("[A-Za-zа-яёА-ЯЁ_]+")) {
					// year_publish.setFocus(true);
					login.setStyleName("error_focus", true);
					login.setText("");
					b = false;
				}
				if (!password.getText().matches("[0-9A-Za-zа-яёА-ЯЁ_]+")) {
					// col_pages.setFocus(true);
					password.setStyleName("error_focus", true);
					password2.setStyleName("error_focus", true);
					password.setText("");
					b = false;
				}
				if (!password.getText().equals(password2.getText())) {
					// col_pages.setFocus(true);
					password.setStyleName("error_focus", true);
					password2.setStyleName("error_focus", true);
					password.setText("");
					password2.setText("");
					b = false;
				}
				if (b) {
					Window.alert("Данные верны");
				}

			}
		});

		initWidget(vPanel);
	}

	public Button getButton() {
		return button;
	}
}
