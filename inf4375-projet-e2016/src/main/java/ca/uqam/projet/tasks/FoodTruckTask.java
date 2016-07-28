package ca.uqam.projet.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.uqam.projet.resources.*;
import ca.uqam.projet.repositories.*;

import java.util.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.scheduling.annotation.*;
import ca.uqam.projet.Application;


import org.springframework.beans.factory.annotation.*;
import javax.annotation.PostConstruct;



@Component
public class FoodTruckTask {
	private static final Logger log = LoggerFactory.getLogger(FoodTruckTask.class);
    private static final String URL = "http://camionderue.com/donneesouvertes/geojson";

    @Autowired private FoodTruckRepository repository;
    
    @Scheduled(cron="0 0 0,12 * * ?")
    @PostConstruct
    public void execute() {
    	RestTemplate restTemplate = new RestTemplate();
      ListeTruck lcamion = restTemplate.getForObject(URL, ListeTruck.class);
      List<FoodTruck> liste = lcamion.getTruck();
      for (int i = 0; i<liste.size(); ++i) {
      	repository.insert(liste.get(i));
      }
      log.info("Insertion des Food Trucks dans la BD.");        
    }

    
}