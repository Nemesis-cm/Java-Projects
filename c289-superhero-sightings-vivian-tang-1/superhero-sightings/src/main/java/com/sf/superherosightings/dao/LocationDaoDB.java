package com.sf.superherosightings.dao;

import com.sf.superherosightings.dto.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LocationDaoDB implements LocationDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public static Location getLocationById(int locationId) {
        try {
            final String SELECT_ORGANIZATION_BY_ID = "SELECT * FROM location WHERE location_id = ?";
            Location location = jdbc.queryForObject(SELECT_ORGANIZATION_BY_ID, new LocationMapper(), locationId);
            return location;
        }
        catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public static List<Location> getAllLocations() {
        final String GET_ALL_LOCATIONS = "SELECT * FROM location";
        return jdbc.query(GET_ALL_LOCATIONS, new LocationMapper());
    }

    @Override
    @Transactional
    public Location addLocation(Location location) {
        final String INSERT_LOCATION = "INSERT INTO location (address1, address2, city, state, zip, latitude, longitude) VALUES (?, ?, ?, ?, ?, ?, ?);";
        jdbc.update(INSERT_LOCATION,
                location.getAddress1(),
                location.getAddress2(),
                location.getCity(),
                location.getState(),
                location.getZip(),
                location.getLatitude(),
                location.getLongitude());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        location.setId(newId);
        return location;
    }




    public void deleteLocationById(int locationId) {
        final String DELETE_SIGHTING = "DELETE s.* FROM hero_villain_sighting s WHERE location_id = ?";
        jdbc.update(DELETE_SIGHTING, locationId);

//        final String DELETE_ORGHEROVILLAIN = "DELETE ohv.* FROM organizationsuperperson ohv "
//                + "JOIN organization o ON ohv.organizationId = o.organizationId WHERE o.location_id = ?";
//        jdbc.update(DELETE_ORGHEROVILLAIN, locationId);

        final String DELETE_ORGANIZATION = "DELETE o.* FROM organization o WHERE location_id = ?";
        jdbc.update(DELETE_ORGANIZATION, locationId);

        final String DELETE_LOCATION = "DELETE l.* FROM location l WHERE location_id = ?";
        jdbc.update(DELETE_LOCATION, locationId);
    }

    public static void editLocation(int id, Location location) {
        final String UPDATE_LOCATION = "UPDATE location SET locationName = ?, locationCity = ?, "
                + " locationState = ?, locationAddress = ?,? WHERE location_id = ?";
        jdbc.update(UPDATE_LOCATION, location.getId(), location.getCity(),
                location.getState(), location.getAddress1(), location.getLatitude());
    }


    public Location getLocationByName(String locationName) {
        try {
            final String GET_LOCATION_BY_NAME = "SELECT  * FROM location WHERE locationName = ?";
            return jdbc.queryForObject(GET_LOCATION_BY_NAME, new LocationMapper(), locationName);
        }
        catch(DataAccessException ex) {
            return null;
        }
    }

    public static final class LocationMapper implements RowMapper<Location> {
        @Override
        public Location mapRow(ResultSet rs, int index) throws SQLException {
            Location location = new Location();
            location.setId(rs.getInt("location_id"));
            location.setAddress1(rs.getString("address1"));
            location.setAddress2(rs.getString("address2"));
            location.setCity(rs.getString("city"));
            location.setState(rs.getString("state"));
            location.setZip(rs.getInt("zip"));
            location.setLatitude(rs.getDouble("latitude"));
            location.setLongitude(rs.getDouble("longitude"));

            return location;
        }
    }
}
