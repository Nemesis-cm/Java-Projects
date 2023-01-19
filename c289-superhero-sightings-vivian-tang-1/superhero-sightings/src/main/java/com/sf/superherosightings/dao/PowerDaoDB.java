package com.sf.superherosightings.dao;

import com.sf.superherosightings.dto.Power;
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
public class PowerDaoDB implements PowerDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Power getPowerById(int id) {
        try {
            final String SELECT_POWER_BY_ID = "SELECT * FROM hero_villain_powers WHERE power_id = ?";
            Power power = jdbc.queryForObject(SELECT_POWER_BY_ID, new PowerMapper(), id);
            return power;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Power> getAllPowers() {
        final String SELECT_ALL_POWERS = "SELECT * FROM hero_villain_powers";
        List<Power> powers = jdbc.query(SELECT_ALL_POWERS, new PowerMapper());
        return powers;
    }

    @Override
    @Transactional
    public Power addPower(Power power) {
        final String INSERT_POWER = "INSERT INTO hero_villain_powers(hero_villain_id, description) VALUES(?,?)";
        jdbc.update(INSERT_POWER,
                power.getHeroVillainId(),
                power.getDescription());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        power.setId(newId);
        return power;
    }

    @Override
    @Transactional
    public void updatePower(Power power) {
        final String UPDATE_POWER = "UPDATE hero_villain_powers SET hero_villain_id = ?, description = ? WHERE power_id = ?";
        jdbc.update(UPDATE_POWER,
                power.getHeroVillainId(),
                power.getDescription(),
                power.getId());
    }

    @Override
    public void deletePowerById(int id) {
        final String DELETE_POWER = "DELETE FROM hero_villain_powers WHERE power_id = ?";
        jdbc.update(DELETE_POWER, id);
    }

    public static final class PowerMapper implements RowMapper<Power> {
        @Override
        public Power mapRow(ResultSet rs, int index) throws SQLException {
            Power power = new Power();
            power.setId(rs.getInt("power_id"));
            power.setHeroVillainId(rs.getInt("hero_villain_id"));
            power.setDescription(rs.getString("description"));

            return power;
        }
    }
}
