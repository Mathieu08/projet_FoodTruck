package ca.uqam.projet.resources;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;


@XmlRootElement(name="stations")
public class ListeStation {
	@XmlElement(name="station")
	private Station stations[];

	public List<Station> getStations() { return Arrays.asList(stations); }
	
}