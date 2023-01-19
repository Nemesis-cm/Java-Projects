package com.sf.superherosightings.dao;

import com.sf.superherosightings.dto.HeroVillain;
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
public class HeroVillainDaoDB implements HeroVillainDao {
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public HeroVillain getHeroVillainById(int id) {
        try {
            final String GET_HEROVILLAIN_BY_ID = "SELECT * FROM hero_villain WHERE `hero_villain_id` = ?";
            return jdbc.queryForObject(GET_HEROVILLAIN_BY_ID, new HeroVillainMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<HeroVillain> getAllHeroVillains() {
        final String GET_ALL_HEROVILLAIN = "SELECT * FROM hero_villain";
        return jdbc.query(GET_ALL_HEROVILLAIN, new HeroVillainMapper());
    }

    @Override
    @Transactional
    public HeroVillain addHeroVillain(HeroVillain HeroVillain) {
        final String INSERT_HEROVILLAIN = "INSERT INTO hero_villain(`name`, `description`) " +
                "VALUES(?,?)";
        jdbc.update(INSERT_HEROVILLAIN,
                HeroVillain.getName(),
                HeroVillain.getDescription());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        HeroVillain.setId(newId);
        return HeroVillain;
    }

    @Override
    public void updateHeroVillain(HeroVillain HeroVillain) {
        final String UPDATE_HEROVILLAIN = "UPDATE hero_villain SET `name` = ?, `description` = ? " +
                "WHERE `hero_villain_id` = ?";
        jdbc.update(UPDATE_HEROVILLAIN,
                HeroVillain.getName(),
                HeroVillain.getDescription(),
                HeroVillain.getId());
    }

    @Override
    @Transactional
    public void deleteHeroVillainById(int id) {
        // delete hero villain organization
        final String DELETE_HEROVILLAIN_ORG = "DELETE FROM hero_villain_organization WHERE `hero_villain_id` = ?";
        jdbc.update(DELETE_HEROVILLAIN_ORG, id);

        //delete hero villain sighting
        final String DELETE_HEROVILLAIN_SIGHTING = "DELETE FROM hero_villain_sighting WHERE `hero_villain_id` = ?";
        jdbc.update(DELETE_HEROVILLAIN_SIGHTING, id);

        // delete hero villain power
        final String DELETE_HEROVILLAIN_POWER = "DELETE FROM hero_villain_powers WHERE `hero_villain_id` = ?";
        jdbc.update(DELETE_HEROVILLAIN_POWER, id);

        // delete hero villain
        final String DELETE_HEROVILLAIN = "DELETE FROM hero_villain WHERE `hero_villain_id` = ?";
        jdbc.update(DELETE_HEROVILLAIN, id);
    }

    public static final class HeroVillainMapper implements RowMapper<HeroVillain> {
        @Override
        public HeroVillain mapRow(ResultSet rs, int index) throws SQLException {
            HeroVillain heroVillain = new HeroVillain();
            heroVillain.setId(rs.getInt("hero_villain_id"));
            heroVillain.setName(rs.getString("name"));
            heroVillain.setDescription(rs.getString("description"));

            return heroVillain;
        }
    }
}
