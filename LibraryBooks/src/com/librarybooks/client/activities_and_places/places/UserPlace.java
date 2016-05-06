package com.librarybooks.client.activities_and_places.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class UserPlace extends Place {
	private String userInfo;

	public UserPlace(String token) {
		this.userInfo = token;
	}

	public String getName() {
		return userInfo;
	}

	public static class Tokenizer implements PlaceTokenizer<UserPlace> {

		@Override
		public String getToken(UserPlace place) {
			return place.getName();
		}

		@Override
		public UserPlace getPlace(String token) {
			return new UserPlace(token);
		}

	}

}
