package com.sf.superherosightings.dao;

import com.sf.superherosightings.dto.HeroVillain;
import com.sf.superherosightings.dto.Power;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PowerDaoDBTest {
    @Autowired
    PowerDao powerDao;

    @Autowired
    HeroVillainDao heroVillainDao;

    public PowerDaoDBTest() {}

    @BeforeEach
    public void setUp() {
        List<Power> powers = powerDao.getAllPowers();
        for (Power power: powers) {
            powerDao.deletePowerById(power.getId());
        }

        List<HeroVillain> heroVillains = heroVillainDao.getAllHeroVillains();
        for (HeroVillain heroVillain: heroVillains) {
            heroVillainDao.deleteHeroVillainById(heroVillain.getId());
        }
    }

    @Test
    public void testAddAndGetPower() {
        HeroVillain heroVillain = new HeroVillain();
        heroVillain.setName("Test Name");
        heroVillain.setDescription("Test Description");
        heroVillain = heroVillainDao.addHeroVillain(heroVillain);
        HeroVillain fromDao = heroVillainDao.getHeroVillainById(heroVillain.getId());
        assertEquals(heroVillain, fromDao);

        Power power = new Power();
        power.setHeroVillainId(heroVillain.getId());
        power.setDescription("invisibility");
        power = powerDao.addPower(power);
        Power powerFromDao = powerDao.getPowerById(power.getId());
        assertEquals(power, powerFromDao);
    }

    @Test
    public void testGetAllPowers() {
        HeroVillain heroVillain = new HeroVillain();
        heroVillain.setName("Test Name");
        heroVillain.setDescription("Test Description");
        heroVillain = heroVillainDao.addHeroVillain(heroVillain);
        HeroVillain fromDao = heroVillainDao.getHeroVillainById(heroVillain.getId());
        assertEquals(heroVillain, fromDao);

        Power power1 = new Power();
        power1.setHeroVillainId(heroVillain.getId());
        power1.setDescription("invisibility");
        power1 = powerDao.addPower(power1);

        Power power2 = new Power();
        power2.setHeroVillainId(heroVillain.getId());
        power2.setDescription("mind reading");
        power2 = powerDao.addPower(power2);

        List<Power> powers = powerDao.getAllPowers();
        assertEquals(2, powers.size());
        assertTrue(powers.contains(power1));
        assertTrue(powers.contains(power2));
    }

    @Test
    public void testUpdatePower() {
        HeroVillain heroVillain = new HeroVillain();
        heroVillain.setName("Test Name");
        heroVillain.setDescription("Test Description");
        heroVillain = heroVillainDao.addHeroVillain(heroVillain);
        HeroVillain fromDao = heroVillainDao.getHeroVillainById(heroVillain.getId());
        assertEquals(heroVillain, fromDao);

        Power power = new Power();
        power.setHeroVillainId(heroVillain.getId());
        power.setDescription("invisibility");
        power = powerDao.addPower(power);
        Power powerFromDao = powerDao.getPowerById(power.getId());
        assertEquals(power, powerFromDao);

        power.setDescription("super smelling");
        powerDao.updatePower(power);
        assertNotEquals(power, powerFromDao);

        powerFromDao = powerDao.getPowerById(power.getId());
        assertEquals(power, powerFromDao);
    }

    @Test
    public void testDeletePower() {
        HeroVillain heroVillain = new HeroVillain();
        heroVillain.setName("Test Name");
        heroVillain.setDescription("Test Description");
        heroVillain = heroVillainDao.addHeroVillain(heroVillain);

        Power power = new Power();
        power.setHeroVillainId(heroVillain.getId());
        power.setDescription("invisibility");
        power = powerDao.addPower(power);
        powerDao.deletePowerById(power.getId());

        Power powerFromDao = powerDao.getPowerById(power.getId());
        assertNull(powerFromDao);
    }

}