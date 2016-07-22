package ca.uqam.projet.resources;

import javax.xml.bind.annotation.XmlElement;

public class Station {
		@XmlElement(name="name")
		private String nom;

		@XmlElement(name="lat")
		private Double lat;

		@XmlElement(name="long")
		private Double lon;

		@XmlElement(name="nbBikes")
		private int nbVelos;

		@XmlElement(name="nbEmptyDocks")
		private int disponibilite;

		public String getNom() { return nom; }
		public Double getLat() { return lat; }
		public Double getLon() { return lon; }
		public int getNbVelos() { return nbVelos; }
		public int getDisponibilite() { return disponibilite; }
	}