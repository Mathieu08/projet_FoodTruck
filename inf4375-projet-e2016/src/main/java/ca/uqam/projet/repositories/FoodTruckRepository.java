package ca.uqam.projet.repositories;

import java.util.*;
import java.sql.*;

import ca.uqam.projet.resources.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.*;
import org.springframework.stereotype.*;


@Component
public class FoodTruckRepository {

	@Autowired private JdbcTemplate jdbcTemplate;

	private static final String FIND_ALL_STMT =
        " select"
      + "   id,"
      + "   nom,"
      + "   lieu,"
      + "   ST_X(position::geometry),"
      + "   ST_Y(position::geometry),"
      + "   dateTruck,"
      + "   heureDebut,"
      + "   heureFin"
      + " from"
      + "   trucks"
      ;

    public List<Truck> findAll() {
        return jdbcTemplate.query(FIND_ALL_STMT, new TruckRowMapper());
    }

    private static final String FIND_ALL_BY_DATE = 
      " select"
    + "   id,"
    + "   nom,"
    + "   lieu,"
    + "   ST_X(position::geometry),"
    + "   ST_Y(position::geometry),"
    + "   dateTruck,"
    + "   heureDebut,"
    + "   heureFin"
    + " from"
    + "   trucks"
    + " where"
    + " dateTruck >= ? and dateTruck <= ?"
    ;

    public List<Truck> findAllByDate(String dateDebut, String dateFin) {
        return jdbcTemplate.query(FIND_ALL_BY_DATE, new Object[]{dateDebut,dateFin}, new TruckRowMapper());
    }

    private static final String INSERT_STMT =
      " INSERT INTO trucks (id, nom, lieu, position, dateTruck, heureDebut, heureFin)"
    + " VALUES (?, ?, ?, ST_MakePoint(?, ?),?, ?, ?)"
    + " on conflict do nothing"
	;

	public int insert(FoodTruck camion){
		return jdbcTemplate.update(conn -> {
			PreparedStatement ps = conn.prepareStatement(INSERT_STMT);
			ps.setString(1, camion.getInfo().getIdTruck());
			ps.setString(2, camion.getInfo().getNom());
			ps.setString(3, camion.getInfo().getLieu());
      		ps.setDouble(4, camion.getLocation().getLon());
      		ps.setDouble(5, camion.getLocation().getLat());
			ps.setString(6, camion.getInfo().getDate());
			ps.setString(7, camion.getInfo().getHeureDebut());
			ps.setString(8, camion.getInfo().getHeureFin());
			return ps;
		});
	}

    class TruckRowMapper implements RowMapper<Truck> {
        public Truck mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Truck(
                rs.getString("id"),
                rs.getString("nom"),
                rs.getString("lieu"),
                rs.getDouble("ST_X"),
                rs.getDouble("ST_Y"),
                rs.getString("dateTruck"),
                rs.getString("heureDebut"),
                rs.getString("heureFin")
            );
        }
    }
}