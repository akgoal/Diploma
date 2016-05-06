package com.librarybooks.client.activities_and_places.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AdminPlace extends Place {
	private String goodbyeName;

	public AdminPlace(String token) {
		this.goodbyeName = token;
	}

	public String getAdminName() {
		return goodbyeName;
	}

	public static class Tokenizer implements PlaceTokenizer<AdminPlace> {
		@Override
		public String getToken(AdminPlace place) {
			return place.getAdminName();
		}

		@Override
		public AdminPlace getPlace(String token) {
			return new AdminPlace(token);
		}
	}

}
