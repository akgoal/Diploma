package com.librarybooks.client;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.librarybooks.client.activities_and_places.places.*;

@WithTokenizers({ UserPlace.Tokenizer.class, AdminPlace.Tokenizer.class })
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}
