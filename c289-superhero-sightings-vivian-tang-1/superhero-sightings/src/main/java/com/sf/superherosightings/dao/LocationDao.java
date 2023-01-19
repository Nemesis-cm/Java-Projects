package com.sf.superherosightings.dao;

import com.sf.superherosightings.dto.Location;

import java.util.List;

public interface LocationDao {
    Location getLocationById(int id);
    List<Location> getAllLocations();
    Location addLocation(Location Location);
    void updateLocation(Location Location);
    void deleteLocationById(int id);

    public Location getLocationByName(String locationName);
}
