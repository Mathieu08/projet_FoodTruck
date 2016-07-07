package ca.uqam.projet.resources;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Geometry {
	@JsonProperty("type") String type;
	@JsonProperty("coordinates") Double coordonnees[];

	public Double getLon() { return coordonnees[0]; }
	public Double getLat() { return coordonnees[1]; }

	@Override
	public String toString() {
		return "TYPE --->" + type;
	}
}