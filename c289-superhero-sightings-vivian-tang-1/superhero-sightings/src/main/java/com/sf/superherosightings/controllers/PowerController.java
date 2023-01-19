package com.sf.superherosightings.controllers;

import com.sf.superherosightings.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PowerController {
    @Autowired
    HeroVillainDao heroVillainDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    PowerDao powerDao;

    @Autowired
    SightingDao sightingDao;
}
