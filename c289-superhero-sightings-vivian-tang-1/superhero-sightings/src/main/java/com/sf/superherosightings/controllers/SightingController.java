package com.sf.superherosightings.controllers;

import com.sf.superherosightings.dao.*;
import com.sf.superherosightings.dto.Sighting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/sighting")
public class SightingController {
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

    @GetMapping("/all")
    public String getAllSightings(Model model) {
        List<Sighting> sightings = sightingDao.getAllSightings();
        System.out.println(sightings);
        model.addAttribute("sightings", sightings);
        return "herosighting";
    }

    @PostMapping("/addHeroVillainSighting")
    public String saveSighting(HttpServletRequest request) {
        String heroVillainId = request.getParameter("heroVillainId");
        String locationId = request.getParameter("locationId");

        Sighting sighting = new Sighting();
        sighting.setHeroVillainId(Integer.parseInt(heroVillainId));
        sighting.setLocationId(Integer.parseInt(locationId));
        sightingDao.addSighting(sighting);
        return "redirect:/sighting/all";
    }

    @GetMapping("/deleteHeroVillainSighting")
    public String deleteSightingById(HttpServletRequest request) {
        String sightingId = request.getParameter("sightingId");
        sightingDao.deleteSightingById(Integer.parseInt(sightingId));
        return "redirect:/sighting/all";
    }
}
