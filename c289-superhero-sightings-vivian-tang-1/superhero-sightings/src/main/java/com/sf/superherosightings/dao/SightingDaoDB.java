package com.sf.superherosightings.dao;

import com.sf.superherosightings.dto.HeroVillain;
import com.sf.superherosightings.dto.Location;
import com.sf.superherosightings.dto.Sighting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SightingDaoDB implements SightingDao {
    private final String START_OF_DAY_TIME = "00:00:00";
    private final String END_OF_DAY_TIME = "11:59:59";

    @Autowired
    private HeroVillainDao heroVillainDao;

    @Autowired
    private LocationDao locationDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Retrieve specific sighting from db using an id passed in by user
     * @param id
     * @return
     */
    @Override
    public Sighting getSightingById(int id) {
        final String sqlStmt = "SELECT * FROM hero_villain_sighting WHERE sighting_id = ?;";
        Sighting sighting = jdbcTemplate.queryForObject(sqlStmt, new SightingMapper(), id);
        sighting.setHeroVillain(heroVillainDao.getHeroVillainById(sighting.getHeroVillainId()));
        sighting.setLocation(locationDao.getLocationById(sighting.getLocationId()));
        return sighting;
    }

    /**
     * Get all Heroes based on a particular location
     */
    @Override
    public List<HeroVillain> getAllHeroVillainByLocationId(int locationId) {
        List<HeroVillain> heroVillainsAtLocationId = new ArrayList<>();
        final String sqlStmt = "SELECT * FROM hero_villain_sighting WHERE location_id = ?;";
        List<Sighting> sightingsOfHeroesByLocationId =  jdbcTemplate.query(sqlStmt, new SightingMapper(), locationId);
        for (Sighting sighting : sightingsOfHeroesByLocationId) {
            heroVillainsAtLocationId.add(heroVillainDao.getHeroVillainById(sighting.getHeroVillainId()));
        }
        return heroVillainsAtLocationId;
    }


    /**
     * Get all locations that a specific hero has been to
     *
     */
    @Override
    public List<Location> getAllLocationsByHeroId(int heroId) {
        List<Location> locationsByHeroId = new ArrayList<>();
        final String sqlStmt = "SELECT * FROM hero_villain_sighting WHERE hero_villain_id = ?;";
        List<Sighting> sightingsOFLocationsByHeroId = jdbcTemplate.query(sqlStmt, new SightingMapper(), heroId);
        for (Sighting sighting : sightingsOFLocationsByHeroId) {
            locationsByHeroId.add(locationDao.getLocationById(sighting.getLocationId()));
        }
        return locationsByHeroId;
    }

    /**
     * Get all sightings for particular date
     *
     */
    @Override
    public List<Sighting> getAllSightingsByDate(LocalDateTime dateTime) {
        DateTimeFormatter customDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedString = dateTime.format(customDateFormat);
        String date = formattedString.split(" ")[0];
        String sqlStmt = "SELECT * from hero_villain_sighting WHERE timestamp BETWEEN" + date + " " + START_OF_DAY_TIME + " AND " + date + " " + END_OF_DAY_TIME;
        return jdbcTemplate.query(sqlStmt, new SightingMapper());
    }

    /**
     * Retrieve all sightings from db
     * @return List<Sighting> - List of Sighting objects
     */
    @Override
    public List<Sighting> getAllSightings() {
//        final String sqlStmt = "SELECT h.*, s.*, l.* FROM hero_villain_sighting s\n" +
//                "JOIN hero_villain h ON h.hero_villain_id = s.hero_villain_id\n" +
//                "JOIN location l ON s.location_id = l.location_id;";
        final String sqlStmt = "SELECT * from hero_villain_sighting;";
        List<Sighting> sightings = jdbcTemplate.query(sqlStmt, new SightingMapper());
        for(Sighting sighting : sightings) {
            sighting.setLocation(locationDao.getLocationById(sighting.getLocationId()));
            sighting.setHeroVillain(heroVillainDao.getHeroVillainById(sighting.getHeroVillainId()));
        }
        return sightings;
    }

    /**
     * Add sighting to db using the location_id, and hero_villain_id fields passed in from request body. (Timestamp is auto generated)
     * @param sighting
     * @return
     */
    @Override
    public Sighting addSighting(Sighting sighting) {
        System.out.println(sighting);
        final String sqlStmt = "INSERT INTO hero_villain_sighting(location_id, hero_villain_id) VALUES (?, ?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement stmt = conn.prepareStatement(
                    sqlStmt,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, sighting.getLocationId());
            stmt.setInt(2, sighting.getHeroVillainId());
            return stmt;

        }, keyHolder);
        sighting.setId(keyHolder.getKey().intValue());
        // This line is to retrieve the Sighting with timestamp field that was auto generated by the db
        return getSightingById(sighting.getId());
        }


    /**
     * Update potential sighting
     * @param sighting object with new data to be updated in database
     * @return boolean of whether or not a row of data has been updated
     */
    @Override
    public boolean updateSighting(Sighting sighting) {
        final String sqlStmt = "UPDATE hero_villain_sighting SET location_id = ?, hero_villain_id = ? WHERE sighting_id = ?;";
        return jdbcTemplate.update(sqlStmt, sighting.getLocationId(), sighting.getHeroVillainId()) > 0;
    }

    @Override
    public boolean deleteSightingById(int id) {
        final String sqlStmt = "DELETE FROM hero_villain_sighting WHERE sighting_id = ?;";
        return jdbcTemplate.update(sqlStmt, id) > 0;
    }

    public static final class SightingMapper implements RowMapper<Sighting> {
        @Override
        public Sighting mapRow(ResultSet rs, int index) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setId(rs.getInt("sighting_id"));
            sighting.setLocationId(rs.getInt("location_id"));
            sighting.setHeroVillainId(rs.getInt("hero_villain_id"));
            sighting.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime());

            return sighting;
        }
    }

}
