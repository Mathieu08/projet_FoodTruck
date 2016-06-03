package ca.uqam.projet.resources;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties {
	@JsonProperty("Camion") String camion;
	

	@Override
	public String toString() {
		return "TYPE --->" + camion;
	}
}