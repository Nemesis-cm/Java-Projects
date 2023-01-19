package com.sf.superherosightings.dao;

import com.sf.superherosightings.dto.Organization;
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
public class OrganizationDaoDB implements OrganizationDao {
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Organization getOrganizationById(int id) {
        try {
            final String SELECT_ORG_BY_ID = "SELECT * FROM organization WHERE organization_id = ?";
            Organization organization = jdbc.queryForObject(SELECT_ORG_BY_ID,
                    new OrganizationMapper(), id);
            return organization;
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrganizations() {
        final String SELECT_ALL_ORGS = "SELECT * FROM organization";
        List<Organization> organizations = jdbc.query(SELECT_ALL_ORGS, new OrganizationMapper());

        return organizations;
    }

    @Override
    public Organization addOrganization(Organization organization) {
        final String INSERT_ORG = "INSERT INTO organization(name, description, contact_info, location_id) VALUES(?,?,?,?)";
        jdbc.update(INSERT_ORG,
                organization.getName(),
                organization.getDescription(),
                organization.getContactInfo(),
                organization.getLocationId());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        organization.setId(newId);

        return organization;
    }

    @Override
    public void updateOrganization(Organization organization) {
        final String UPDATE_ORG = "UPDATE organization "
                + "SET name = ?, description = ?, contact_info = ?, location_id = ? WHERE organization_id = ?";
        jdbc.update(UPDATE_ORG,
                organization.getName(),
                organization.getDescription(),
                organization.getContactInfo(),
                organization.getLocationId(),
                organization.getId());
    }

    @Override
    @Transactional
    public void deleteOrganizationById(int id) {
        final String DELETE_HERO_VILLAIN_ORGANIZATION = "DELETE FROM hero_villain_organization "
                + "WHERE organization_id = ?";
        jdbc.update(DELETE_HERO_VILLAIN_ORGANIZATION, id);
        
        final String DELETE_ORG = "DELETE FROM organization WHERE organization_id = ?";
        jdbc.update(DELETE_ORG, id);
    }

    public static final class OrganizationMapper implements RowMapper<Organization> {
        @Override
        public Organization mapRow(ResultSet rs, int index) throws SQLException {
            Organization organization = new Organization();
            organization.setId(rs.getInt("organization_id"));
            organization.setName(rs.getString("name"));
            organization.setDescription(rs.getString("description"));
            organization.setContactInfo(rs.getString("contact_info"));
            organization.setLocationId(rs.getInt("location_id"));

            return organization;
        }
    }
}
