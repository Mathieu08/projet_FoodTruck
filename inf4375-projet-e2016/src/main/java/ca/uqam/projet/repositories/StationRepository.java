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
public class StationRepository {

	@Autowired private JdbcTemplate jdbcTemplate;

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