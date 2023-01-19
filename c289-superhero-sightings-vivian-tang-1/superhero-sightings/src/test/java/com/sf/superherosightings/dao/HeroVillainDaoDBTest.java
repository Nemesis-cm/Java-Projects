package com.sf.superherosightings.dao;

import com.sf.superherosightings.dto.HeroVillain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class HeroVillainDaoDBTest {
    @Autowired
    HeroVillainDao heroVillainDao;

    @BeforeEach
    public void setUp() {
        List<HeroVillain> heroVillains = heroVillainDao.getAllHeroVillains();
        for (HeroVillain heroVillain: heroVillains) {
            heroVillainDao.deleteHeroVillainById(heroVillain.getId());
        }
    }

    @Test
    public void testAddAndGetHeroVillain() {
        HeroVillain heroVillain = new HeroVillain();
        heroVillain.setName("Test Name");
        heroVillain.setDescription("Test Description");
        heroVillain = heroVillainDao.addHeroVillain(heroVillain);

        HeroVillain fromDao = heroVillainDao.getHeroVillainById(heroVillain.getId());

        assertEquals(heroVillain, fromDao);
    }

    @Test
    public void testGetAllHeroVillains() {
        HeroVillain heroVillain1 = new HeroVillain();
        heroVillain1.setName("Test Name 1");
        heroVillain1.setDescription("Test Description 1");
        heroVillain1 = heroVillainDao.addHeroVillain(heroVillain1);

        HeroVillain heroVillain2 = new HeroVillain();
        heroVillain2.setName("Test Name 2");
        heroVillain2.setDescription("Test Description 2");
        heroVillain2 = heroVillainDao.addHeroVillain(heroVillain2);

        List<HeroVillain> heroVillains = heroVillainDao.getAllHeroVillains();

        assertEquals(2, heroVillains.size());
        assertTrue(heroVillains.contains(heroVillain1));
        assertTrue(heroVillains.contains(heroVillain2));
    }

    @Test
    public void testUpdateHeroVillain() {
        HeroVillain heroVillain = new HeroVillain();
        heroVillain.setName("Test Name");
        heroVillain.setDescription("Test Description");
        heroVillain = heroVillainDao.addHeroVillain(heroVillain);

        HeroVillain fromDao = heroVillainDao.getHeroVillainById(heroVillain.getId());
        assertEquals(heroVillain, fromDao);

        heroVillain.setName("New Test Name");
        heroVillainDao.updateHeroVillain(heroVillain);

        assertNotEquals(heroVillain, fromDao);

        fromDao = heroVillainDao.getHeroVillainById(heroVillain.getId());

        assertEquals(heroVillain, fromDao);
    }

    @Test
    public void testDeleteHeroVillainById() {
        // TODO: depends on the organization, sighting, and power dao

    }
}
