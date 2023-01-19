package com.sf.superherosightings.dto;

import java.util.Objects;

public class Power {
    private int id;
    private int heroVillainId;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeroVillainId() {
        return heroVillainId;
    }

    public void setHeroVillainId(int heroVillainId) {
        this.heroVillainId = heroVillainId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Power power = (Power) o;
        return id == power.id && heroVillainId == power.heroVillainId && Objects.equals(description, power.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, heroVillainId, description);
    }
}
