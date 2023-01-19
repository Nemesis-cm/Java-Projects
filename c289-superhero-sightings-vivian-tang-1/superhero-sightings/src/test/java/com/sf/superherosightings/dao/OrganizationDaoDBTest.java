package com.sf.superherosightings.dao;

import com.sf.superherosightings.dto.Location;
import com.sf.superherosightings.dto.Organization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrganizationDaoDBTest {

    @Autowired
    OrganizationDao orgDao;

    @Autowired
    LocationDao locationDao;

    @BeforeEach
    public void setUp() {


        List<Organization> orgs = orgDao.getAllOrganizations();
        for (Organization org : orgs) {
            orgDao.deleteOrganizationById(org.getId());
        }

        List<Location> locations = locationDao.getAllLocations();
        for (Location lcation : locations) {
            locationDao.deleteLocationById(lcation.getId());
        }

    }

    @Test
    public void testGetAllOrganizations() {
        Location location = new Location();
        location.setAddress1("20 W 34th St");
        location.setAddress2("Fl 10");
        location.setCity("New York");
        location.setState("NY");
        location.setLatitude(74.259869);
        location.setLongitude(40.697149);
        location.setZip(10001);
        Location fromLocationDao = locationDao.addLocation(location);
        int locationId = fromLocationDao.getId();

        Location location2 = new Location();
        location2.setAddress1("201 E Randolph St");
        location2.setAddress2("");
        location2.setCity("Chicago");
        location2.setState("IL");
        location2.setLatitude(88.012151);
        location2.setLongitude(41.833392);
        location2.setZip(60602);
        Location fromLocationDao2 = locationDao.addLocation(location2);
        int locationId2 = fromLocationDao2.getId();

        Organization organization = new Organization();
        organization.setName("ABC");
        organization.setDescription("A Superhero test organization");
        organization.setContactInfo("123");
        organization.setLocationId(locationId);
        orgDao.addOrganization(organization);

        Organization organization2 = new Organization();
        organization2.setName("XYZ");
        organization2.setDescription("A Superhero test organization 2");
        organization2.setContactInfo("456");
        organization2.setLocationId(locationId2);
        orgDao.addOrganization(organization2);

        List<Organization> orgs = orgDao.getAllOrganizations();

        assertEquals(2, orgs.size());
        assertTrue(orgs.contains(organization));
        assertTrue(orgs.contains(organization2));
    }

    @Test
    public void testAddGetOrganization() {
        Location location = new Location();
        location.setAddress1("20 W 34th St");
        location.setAddress2("Fl 10");
        location.setCity("New York");
        location.setState("NY");
        location.setLatitude(74.259869);
        location.setLongitude(40.697149);
        location.setZip(10001);
        Location fromLocationDao = locationDao.addLocation(location);
        int locationId = fromLocationDao.getId();

        Organization organization = new Organization();
        organization.setName("ABC");
        organization.setDescription("A Superhero test organization");
        organization.setContactInfo("123");
        organization.setLocationId(locationId);
        organization = orgDao.addOrganization(organization);

        Organization fromDao = orgDao.getOrganizationById(organization.getId());

        assertEquals(organization, fromDao);
    }

    @Test
    public void testUpdateOrganization() {
        Location location = new Location();
        location.setAddress1("20 W 34th St");
        location.setAddress2("Fl 10");
        location.setCity("New York");
        location.setState("NY");
        location.setLatitude(74.259869);
        location.setLongitude(40.697149);
        location.setZip(10001);
        Location fromLocationDao = locationDao.addLocation(location);
        int locationId = fromLocationDao.getId();

        Organization organization = new Organization();
        organization.setName("ABC");
        organization.setDescription("A Superhero test organization");
        organization.setContactInfo("123");
        organization.setLocationId(locationId);
        organization = orgDao.addOrganization(organization);

        Organization fromDao = orgDao.getOrganizationById(organization.getId());

        assertEquals(organization, fromDao);

        organization.setDescription("A Supervillain test organization");

        orgDao.updateOrganization(organization);

        assertNotEquals(organization, fromDao);

        fromDao = orgDao.getOrganizationById(organization.getId());

        assertEquals(organization, fromDao);
    }


    @Test
    public void testDeleteOrganization() {
        Location location = new Location();
        location.setAddress1("20 W 34th St");
        location.setAddress2("Fl 10");
        location.setCity("New York");
        location.setState("NY");
        location.setLatitude(74.259869);
        location.setLongitude(40.697149);
        location.setZip(10001);
        Location fromLocationDao = locationDao.addLocation(location);
        int locationId = fromLocationDao.getId();

        Organization organization = new Organization();
        organization.setName("ABC");
        organization.setDescription("A Superhero test organization");
        organization.setContactInfo("123");
        organization.setLocationId(locationId);
        organization = orgDao.addOrganization(organization);

        orgDao.deleteOrganizationById(organization.getId());

        Organization fromDao = orgDao.getOrganizationById(organization.getId());
        assertNull(fromDao);
    }
}
