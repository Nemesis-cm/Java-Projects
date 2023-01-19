package com.sf.superherosightings.dao;

import com.sf.superherosightings.dto.HeroVillain;
import com.sf.superherosightings.dto.Location;
import com.sf.superherosightings.dto.Sighting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SightingDaoDBTest {

    @Autowired
    SightingDao sightingDao;

    @Autowired
    HeroVillainDao heroVillainDao;

    @Autowired
    LocationDao locationDao;

    @BeforeEach
    public void setUp() {
        List<Sighting> allSightings = sightingDao.getAllSightings();
        for(Sighting sighting : allSightings) {
            sightingDao.deleteSightingById(sighting.getId());
        }

        List<HeroVillain> heroVillains = heroVillainDao.getAllHeroVillains();
        for (HeroVillain heroVillain: heroVillains) {
            heroVillainDao.deleteHeroVillainById(heroVillain.getId());
        }
    }

    @Test
    void getSightingById() {
    }

    @Test
    void testGetAllSightings() {
        HeroVillain heroVillain = new HeroVillain();
        heroVillain.setName("Jason Bourne");
        heroVillain.setDescription("Test Description");
        heroVillain = heroVillainDao.addHeroVillain(heroVillain);

        Location location = new Location();
        location.setAddress1("22 Broadway Ln");
        location.setAddress2("Apt 24C");
        location.setCity("Albany");
        location.setState("NY");
        location.setZip(94040);
        location.setLatitude(93.021458);
        location.setLongitude(93.021458);

        Location insertedLocation = locationDao.addLocation(location);

        Sighting sighting = new Sighting();
        sighting.setLocationId(insertedLocation.getId());
        sighting.setHeroVillainId(heroVillain.getId());
        Sighting insertedSighting = sightingDao.addSighting(sighting);
        List<Sighting> allSightings = sightingDao.getAllSightings();
        assertNotNull(allSightings);
        assertEquals(insertedSighting, allSightings.get(0));
    }

    @Test
    void testAddAndGetSighting() {
        HeroVillain heroVillain = new HeroVillain();
        heroVillain.setName("Super Mario");
        heroVillain.setDescription("Test Description");
        heroVillain = heroVillainDao.addHeroVillain(heroVillain);

        Location location = new Location();
        location.setAddress1("22 Broadway Ln");
        location.setAddress2("Apt 24C");
        location.setCity("Albany");
        location.setState("NY");
        location.setZip(94040);
        location.setLatitude(93.021458);
        location.setLongitude(93.021458);

        Location insertedLocation = locationDao.addLocation(location);

        Sighting sighting = new Sighting();
        sighting.setLocationId(insertedLocation.getId());
        sighting.setHeroVillainId(heroVillain.getId());
        Sighting insertedSighting = sightingDao.addSighting(sighting);
        Sighting retrievedSighting = sightingDao.getSightingById(insertedSighting.getId());

        assertEquals(insertedSighting, retrievedSighting);
    }

    @Test
    void updateSighting() {
    }

    @Test
    void deleteSightingById() {
    }
}