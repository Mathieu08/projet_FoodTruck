package ca.uqam.projet.resources;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodTruck {
	@JsonProperty("type") String type;
	@JsonProperty("geometry") Geometry location;
	@JsonProperty("properties") Properties info;

	@Override
	public String toString() {
		return "TYPE --->" + type + info.toString();
	}
}