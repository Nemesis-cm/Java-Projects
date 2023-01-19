package com.sf.superherosightings.dao;

import com.sf.superherosightings.dto.HeroVillain;

import java.util.List;

public interface HeroVillainDao {
    HeroVillain getHeroVillainById(int id);
    List<HeroVillain> getAllHeroVillains();
    HeroVillain addHeroVillain(HeroVillain HeroVillain);
    void updateHeroVillain(HeroVillain HeroVillain);
    void deleteHeroVillainById(int id);
}
