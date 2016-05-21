package com.librarybooks.client.activities_and_places.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AdminPlace extends Place {
	private String param;

	public AdminPlace(String token) {
		this.param = token;
	}

	public String getParam() {
		return param;
	}

	public static class Tokenizer implements PlaceTokenizer<AdminPlace> {

		@Override
		public String getToken(AdminPlace place) {
			return place.getParam();
		}

		@Override
		public AdminPlace getPlace(String token) {
			return new AdminPlace(token);
		}

	}

}
