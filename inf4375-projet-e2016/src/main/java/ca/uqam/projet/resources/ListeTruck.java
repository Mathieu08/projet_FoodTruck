package ca.uqam.projet.resources;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListeTruck {
	@JsonProperty("type") String type;
	@JsonProperty("features") FoodTruck truck[];

	@Override
	public String toString() {
		return "TYPE --->" + type + truck[0].toString();
	}
}