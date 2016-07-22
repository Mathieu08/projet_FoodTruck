package ca.uqam.projet.resources;

import com.fasterxml.jackson.annotation.*;

public class StationBixi {
  private String nom;
  private Double lon;
  private Double lat;
  private int nbVelos;
  private int disponibilite;

  public StationBixi(String nom, Double lon, Double lat, int nbVelos, int disponibilite) {
    this.nom = nom;
    this.lon = lon;
    this.lat = lat;
    this.nbVelos = nbVelos;
    this.disponibilite = disponibilite;
  }

  @JsonProperty public String getNom() { return nom; }
  @JsonProperty public Double getLon() { return lon; }
  @JsonProperty public Double getLat() { return lat; }
  @JsonProperty public int getNbVelos() { return nbVelos; }
  @JsonProperty public int getDisponibilite() { return disponibilite; }

}
