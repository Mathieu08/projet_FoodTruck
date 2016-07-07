package ca.uqam.projet.resources;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodTruck {
	@JsonProperty("type") String type;
	@JsonProperty("geometry") Geometry location;
	@JsonProperty("properties") Properties info;

	public Properties getInfo() { return info; }
	public Geometry getLocation() { return location; }

	@Override
	public String toString() {
		return "TYPE --->" + type + info.toString();
	}
}