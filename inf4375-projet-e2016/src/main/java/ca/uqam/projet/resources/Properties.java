package ca.uqam.projet.resources;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties {
	@JsonProperty("Camion") String camion;
	@JsonProperty("Lieu") String lieu;
	@JsonProperty("Date") String date;
	@JsonProperty("Heure_debut") String heureDebut;
	@JsonProperty("Heure_fin") String heureFin;
	@JsonProperty("Truckid") String idTruck;
	

	public String getNom() { return camion; }
	public String getLieu() { return lieu; }
	public String getDate() { return date; }
	public String getHeureDebut() { return heureDebut; }
	public String getHeureFin() { return heureFin; }
	public String getIdTruck() { return idTruck; }

	@Override
	public String toString() {
		return "TYPE --->" + camion + lieu + date + idTruck;
	}
}