package com.librarybooks.client.objects;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Selection implements Serializable {

	private String selection;
	private long id_selection;
	private long col_book;

	// новый
	public Selection(String selection, long id_selection) {
		this.selection = selection;
		this.id_selection = id_selection;
	}

	public Selection(String selection, long id_selection, long col_book) {
		this.selection = selection;
		this.id_selection = id_selection;
		this.col_book = col_book;
	}

	public void setSelection(String selection, long id_selection, long col_book) {
		this.selection = selection;
		this.id_selection = id_selection;
		this.col_book = col_book;
	}

	public void setSelection(String selection, long id_selection) {
		this.selection = selection;
		this.id_selection = id_selection;

	}

	public Selection() {
	}

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	public long getIdSelection() {
		return id_selection;
	}

	public void setIdSelection(long id_selection) {
		this.id_selection = id_selection;
	}

	public long getColBook() {
		return col_book;
	}

	public void setColBook(long col_book) {
		this.col_book = col_book;
	}

}