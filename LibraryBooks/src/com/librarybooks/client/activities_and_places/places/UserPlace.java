package com.librarybooks.client.activities_and_places.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class UserPlace extends Place {
	private String param;

	public UserPlace(String token) {
		this.param = token;
	}

	public String getParam() {
		return param;
	}

	public static class Tokenizer implements PlaceTokenizer<UserPlace> {

		@Override
		public String getToken(UserPlace place) {
			return place.getParam();
		}

		@Override
		public UserPlace getPlace(String token) {
			return new UserPlace(token);
		}

	}

}
