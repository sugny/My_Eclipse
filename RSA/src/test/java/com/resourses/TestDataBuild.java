package com.resourses;

import java.util.ArrayList;
import java.util.List;

import com.pojo.AddPlace;
import com.pojo.Location;

public class TestDataBuild {

	public AddPlace placePayload(String name, String language, String address) {

		AddPlace addPlace = new AddPlace();
		addPlace.setAccuracy(50);
		addPlace.setName(name);
		addPlace.setAddress(address);
		addPlace.setLanguage(language);
		addPlace.setPhone_number("(+91) 983 893 3937");
		addPlace.setWebsite("http://google.com");
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		addPlace.setLocation(location);
		List<String> typesList = new ArrayList<String>();
		typesList.add("shoe park");
		typesList.add("shop");
		addPlace.setTypes(typesList);
		return addPlace;

	}

	public String deletePayload(String place_id) {

		return "{\r\n    \"place_id\":\""+place_id+"\"\r\n}";

	}

}
