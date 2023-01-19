package com.sf.superherosightings.dao;

import com.sf.superherosightings.dto.HeroVillain;
import com.sf.superherosightings.dto.Location;
import com.sf.superherosightings.dto.Sighting;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SightingDao {
    Sighting getSightingById(int id);

    List<HeroVillain> getAllHeroVillainByLocationId(int locationId);

    List<Location> getAllLocationsByHeroId(int heroId);

    List<Sighting> getAllSightingsByDate(LocalDateTime dateTime);

    List<Sighting> getAllSightings();
    Sighting addSighting(Sighting Sighting);
    boolean updateSighting(Sighting Sighting);
    boolean deleteSightingById(int id);
}
