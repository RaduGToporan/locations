package com.wdev.locations.controllers;

import com.wdev.locations.beans.MyLocation;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MyLocationsController extends HttpServlet {
    private HashMap<Integer, MyLocation> myLocations = new HashMap<>();
    private int count = 0;
    private String s;

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/addlocation")
    public String location() {
        return "addlocation.html";
    }

    @PostMapping("/addlocation")
    public void addLocation(HttpServletResponse response, HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        String xCoord = parameterMap.get("xcoord")[0];
        String yCoord = parameterMap.get("ycoord")[0];
        String shortNote = parameterMap.get("shortNote")[0];
        String comments = parameterMap.get("comments")[0];
        count++;
        MyLocation location = new MyLocation(count, xCoord, yCoord, shortNote, comments);
        myLocations.put(count, location);
        try {
            response.sendRedirect("/browselocations");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/browselocations")
    public String browseLocations(Model model) {
        List<MyLocation> locations = new ArrayList<>();
        for (int key : myLocations.keySet()) {
            locations.add(myLocations.get(key));
        }
        model.addAttribute("locations", locations);
        return "browselocations.html";
    }

    @GetMapping("/view/{id}")
    public String viewLocations(Model model, @PathVariable("id") int id) {
        model.addAttribute("location", myLocations.get(id));
        return "view.html";
    }

    @GetMapping("/findclosestlocation")
    public String findClosestLocation(Model model, @RequestParam(value = "x", required = false) Double x,
                                      @RequestParam(value = "y", required = false) Double y) {
        model.addAttribute("location", closestLocation(myLocations, x, y));
        return "findclosestlocation.html";
    }

    private MyLocation closestLocation(Map<Integer, MyLocation> locations, Double x, Double y) {
        if ((x == null) || (y == null)) {
            return null;
        }
        double min = Double.MAX_VALUE;
        int minIndex = -1;
        for (int key : locations.keySet()) {
            double x0 = Double.parseDouble(locations.get(key).getXcoord());
            double y0 = Double.parseDouble(locations.get(key).getYcoord());
            double dist = (x - x0) * (x - x0) + (y - y0) * (y - y0);
            if (dist < min) {
                min = dist;
                minIndex = key;
            }
        }
        if (minIndex < 0) {
            return null;
        } else {
            return locations.get(minIndex);
        }
    }
}
