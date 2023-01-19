package com.sf.superherosightings.dao;

import com.sf.superherosightings.dto.Power;

import java.util.List;

public interface PowerDao {
    Power getPowerById(int id);
    List<Power> getAllPowers();
    Power addPower(Power Power);
    void updatePower(Power Power);
    void deletePowerById(int id);
}
