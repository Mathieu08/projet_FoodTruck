package ca.uqam.projet.resources;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Geometry {
	@JsonProperty("type") String type;
	@JsonProperty("coordinates") Double coordonnees[];

	@Override
	public String toString() {
		return "TYPE --->" + type;
	}
}