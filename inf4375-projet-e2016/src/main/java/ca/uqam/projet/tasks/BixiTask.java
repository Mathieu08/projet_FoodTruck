package ca.uqam.projet.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ca.uqam.projet.resources.*;
import java.util.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.scheduling.annotation.*;
import ca.uqam.projet.Application;
import ca.uqam.projet.repositories.*;

import java.sql.*;

import org.springframework.beans.factory.annotation.*;
import javax.annotation.PostConstruct;

@Component
public class BixiTask {
	private static final Logger log = LoggerFactory.getLogger(BixiTask.class);
	private static final String URL = "https://montreal.bixi.com/data/bikeStations.xml";

	@Autowired private StationRepository repository;

	@Scheduled(cron="0 0/10 * * * ?")
	@PostConstruct
	public void execute() {
		RestTemplate restTemplate = new RestTemplate();
		ListeStation lStation = restTemplate.getForObject(URL, ListeStation.class);
		log.info("la: " + lStation.toString());
		List<Station> liste = lStation.getStations();
		for (int i = 0; i<liste.size(); ++i) {
        	repository.insert(liste.get(i));
        }
	}

	
}