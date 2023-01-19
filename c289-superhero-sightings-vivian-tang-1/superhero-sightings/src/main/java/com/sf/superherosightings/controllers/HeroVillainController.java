package com.sf.superherosightings.controllers;

import com.sf.superherosightings.dao.*;
import com.sf.superherosightings.dto.HeroVillain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HeroVillainController {

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

    @GetMapping("herovillains")
    public String displayHeroVillains(Model model) {
        List<HeroVillain> heroVillains = heroVillainDao.getAllHeroVillains();
        model.addAttribute("herovillains", heroVillains);
        return "herovillains";
    }

    @PostMapping("addHeroVillain")
    public String addHeroVillain(HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        HeroVillain heroVillain = new HeroVillain();
        heroVillain.setName(name);
        heroVillain.setDescription(description);

        heroVillainDao.addHeroVillain(heroVillain);

        return "redirect:/herovillains";
    }

    @GetMapping("editHeroVillain")
    public String editHeroVillain(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        HeroVillain heroVillain = heroVillainDao.getHeroVillainById(id);

        model.addAttribute("herovillain", heroVillain);

        return "editHeroVillain";
    }

    @PostMapping("editHeroVillain")
    public String performEditHeroVillain(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        HeroVillain heroVillain = heroVillainDao.getHeroVillainById(id);

        heroVillain.setName(request.getParameter("name"));
        heroVillain.setDescription(request.getParameter("description"));

        heroVillainDao.updateHeroVillain(heroVillain);

        return "redirect:/herovillains";
    }

    @GetMapping("deleteHeroVillain")
    public String deleteHeroVillain(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        heroVillainDao.deleteHeroVillainById(id);

        return "redirect:/herovillains";
    }
}
