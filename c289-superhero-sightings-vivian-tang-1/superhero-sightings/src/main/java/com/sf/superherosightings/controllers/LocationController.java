package com.sf.superherosightings.controllers;

import com.sf.superherosightings.dao.*;
import com.sf.superherosightings.dto.Location;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class LocationController {

    @GetMapping("locations")
    public String displayLocations(Model model) {
        List<Location> locationList = LocationDaoDB.getAllLocations();
        model.addAttribute("locations", locationList);
        return "locations";
    }

    @PostMapping("addLocation")
    public String addLocation(HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String address = request.getParameter("address");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");

        Location newLocation = new Location(name, description, address
                , new BigDecimal(latitude), new BigDecimal(longitude));
        LocationDao serviceLayer = null;
        serviceLayer.addLocation(newLocation);

        return "redirect:/locations";
    }

    @GetMapping("deleteLocation")
    public String deleteLocation(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        LocationDao serviceLayer = null;
        serviceLayer.deleteLocationById(id);

        return "redirect:/locations";
    }

    @GetMapping("editLocation")
    public String editLocation(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Location location = LocationDaoDB.getLocationById(id);

        model.addAttribute("location", location);

        return "editLocation";
    }

    @PostMapping("editLocation")
    public String performEditLocation(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Location location = LocationDaoDB.getLocationById(id);

        location.setId(Integer.parseInt(request.getParameter("Id")));
        location.setAddress1(request.getParameter("address"));
        location.setLatitude(new BigDecimal(request.getParameter("latitude")));
        location.setLongitude(new BigDecimal(request.getParameter("longitude")));

        LocationDaoDB.editLocation(id, location);

        return "redirect:/locations";
    }
}
