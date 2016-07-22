package ca.uqam.projet.resources;

import com.fasterxml.jackson.annotation.*;

public class Truck {
  private String id;
  private String nom;
  private String lieu;
  private Double lon;
  private Double lat;
  private String dateTruck;
  private String heureDebut;
  private String heureFin;

  public Truck(String id, String nom, String lieu, Double lon, Double lat, String dateTruck, String heureDebut, String heureFin) {
    this.id = id;
    this.nom = nom;
    this.lieu = lieu;
    this.lon = lon;
    this.lat = lat;
    this.dateTruck = dateTruck;
    this.heureDebut = heureDebut;
    this.heureFin = heureFin;
  }

  @JsonProperty public String getId() { return id; }
  @JsonProperty public String getNom() { return nom; }
  @JsonProperty public String getLieu() { return lieu; }
  @JsonProperty public Double getLon() { return lon; }
  @JsonProperty public Double getLat() { return lat; }
  @JsonProperty public String getDateTruck() { return dateTruck; }
  @JsonProperty public String getHeureDebut() { return heureDebut; }
  @JsonProperty public String getHeureFin() { return heureFin; }

}
