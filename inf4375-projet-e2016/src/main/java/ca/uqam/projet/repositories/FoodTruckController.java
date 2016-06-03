package ca.uqam.projet.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ca.uqam.projet.resources.*;
import java.util.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.scheduling.annotation.*;
import ca.uqam.projet.Application;


@Component
public class FoodTruckController {
	private static final Logger log = LoggerFactory.getLogger(FoodTruckController.class);
    private static final String URL = "http://camionderue.com/donneesouvertes/geojson";

    @Scheduled(cron="*/2 * * * * ?")
    public void execute() {
    	RestTemplate restTemplate = new RestTemplate();
        ListeTruck lcamion = restTemplate.getForObject(URL, ListeTruck.class);
        log.info("ici" + lcamion.toString());
    }
}