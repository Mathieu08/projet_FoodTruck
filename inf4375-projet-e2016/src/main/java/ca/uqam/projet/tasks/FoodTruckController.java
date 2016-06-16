package ca.uqam.projet.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ca.uqam.projet.resources.*;
import java.util.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.scheduling.annotation.*;
import ca.uqam.projet.Application;

import java.sql.*;
import org.springframework.dao.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.*;
import org.springframework.beans.factory.annotation.*;




@Component
public class FoodTruckController {
	private static final Logger log = LoggerFactory.getLogger(FoodTruckController.class);
    private static final String URL = "http://camionderue.com/donneesouvertes/geojson";

    @Autowired private JdbcTemplate jdbcTemplate;

    @Scheduled(cron="*/10 * * * * ?")
    public void execute() {
    	RestTemplate restTemplate = new RestTemplate();
        ListeTruck lcamion = restTemplate.getForObject(URL, ListeTruck.class);
        log.info("ici" + lcamion.toString());
        List<FoodTruck> liste = lcamion.getTruck();
        for (int i = 0; i<liste.size(); ++i) {
        	insert(liste.get(i));
        }
        
    }

    private static final String INSERT_STMT =
      " INSERT INTO trucks (id, nom, lieu, dateTruck, heureDebut, heureFin)"
    + " VALUES (?, ?, ?, ?, ?, ?)"
	;

	public int insert(FoodTruck camion){
		return jdbcTemplate.update(conn -> {
			PreparedStatement ps = conn.prepareStatement(INSERT_STMT);
			ps.setString(1, camion.getInfo().getIdTruck());
			ps.setString(2, camion.getInfo().getNom());
			ps.setString(3, camion.getInfo().getLieu());
			ps.setString(4, camion.getInfo().getDate());
			ps.setString(5, camion.getInfo().getHeureDebut());
			ps.setString(6, camion.getInfo().getHeureFin());
			return ps;
		});
	}

}