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
import javax.annotation.PostConstruct;

@Component
public class BixiTask {
	private static final Logger log = LoggerFactory.getLogger(BixiTask.class);
	private static final String URL = "https://montreal.bixi.com/data/bikeStations.xml";

	@Autowired private JdbcTemplate jdbcTemplate;

	@Scheduled(cron="* */10 * * * ?")
	@PostConstruct
	public void execute() {
		RestTemplate restTemplate = new RestTemplate();
		ListeStation lStation = restTemplate.getForObject(URL, ListeStation.class);
		log.info("la: " + lStation.toString());
		List<Station> liste = lStation.getStations();
		for (int i = 0; i<liste.size(); ++i) {
        	insert(liste.get(i));
        }
	}

	private static final String FIND_ALL_BY_DISTANCE = 
      " select"
    + "   nom,"
    + "   ST_X(position::geometry),"
    + "   ST_Y(position::geometry),"
    + "   nbVelos,"
    + "   disponibilite"
    + " from"
    + "   stations"
    + " where"
    + " ST_DWithin(position, ST_MakePoint(?, ?), 200.0);"
    ;

    public List<StationBixi> findAllByDistance(Double lon, Double lat) {
        return jdbcTemplate.query(FIND_ALL_BY_DISTANCE, new Object[]{lon,lat}, new StationRowMapper());
    }

	private static final String INSERT_STMT =
      " INSERT INTO stations (nom, position, nbVelos, Disponibilite)"
    + " VALUES (?, ST_MakePoint(?, ?),?, ?)"
    + " on conflict do nothing"
	;

	public int insert(Station station){
		return jdbcTemplate.update(conn -> {
			PreparedStatement ps = conn.prepareStatement(INSERT_STMT);
			ps.setString(1, station.getNom());
      		ps.setDouble(2, station.getLon());
      		ps.setDouble(3, station.getLat());
			ps.setInt(4, station.getNbVelos());
			ps.setInt(5, station.getDisponibilite());
			return ps;
		});
	}

	class StationRowMapper implements RowMapper<StationBixi> {
        public StationBixi mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new StationBixi(
                rs.getString("nom"),
                rs.getDouble("ST_X"),
                rs.getDouble("ST_Y"),
                rs.getInt("nbVelos"),
                rs.getInt("disponibilite")
            );
        }
    }
}