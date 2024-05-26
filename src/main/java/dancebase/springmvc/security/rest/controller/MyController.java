package dancebase.springmvc.security.rest.controller;

import dancebase.springmvc.security.rest.entities.Dancer;
import dancebase.springmvc.security.rest.service.DancerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MyController {
    @Autowired
    private DancerService service;
    @GetMapping("/")
    public String firstPage(){
        return "first-page";
    }
    @RequestMapping("/check")
    public String secondPage(){
     return "second-page";
    }
}
