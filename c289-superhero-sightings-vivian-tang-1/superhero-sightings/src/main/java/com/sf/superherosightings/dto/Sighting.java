package com.sf.superherosightings.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class Sighting {
    private int id;
    private int locationId;
    private Location location;
    private int heroVillainId;
    private HeroVillain heroVillain;
    private LocalDateTime timestamp;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getHeroVillainId() {
        return heroVillainId;
    }

    public void setHeroVillainId(int heroVillainId) {
        this.heroVillainId = heroVillainId;
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public HeroVillain getHeroVillain() {
        return heroVillain;
    }

    public void setHeroVillain(HeroVillain heroVillain) {
        this.heroVillain = heroVillain;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sighting sighting = (Sighting) o;
        return id == sighting.id && locationId == sighting.locationId && heroVillainId == sighting.heroVillainId && location.equals(sighting.location) && heroVillain.equals(sighting.heroVillain) && timestamp.equals(sighting.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, locationId, location, heroVillainId, heroVillain, timestamp);
    }

    @Override
    public String toString() {
        return "Sighting{" +
                "id=" + id +
                ", locationId=" + locationId +
                ", location=" + location +
                ", heroVillainId=" + heroVillainId +
                ", heroVillain=" + heroVillain +
                ", timestamp=" + timestamp +
                '}';
    }
}
