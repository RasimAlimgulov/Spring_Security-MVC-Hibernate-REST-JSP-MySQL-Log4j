package dancebase.springmvc.security.rest.controller;

import dancebase.springmvc.security.rest.entities.Dancer;
import dancebase.springmvc.security.rest.exeption_handing.NoSuchDancerException;
import dancebase.springmvc.security.rest.service.DancerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyRestController {
    @Autowired
    private DancerService service;

    @GetMapping("/dancers")
    public List<Dancer> getAllDancers(){
        return service.getAllDancers();
    }

    @GetMapping("/dancers/{id}")
    public Dancer getDancerById(@PathVariable int id){
        Dancer dancer=service.getDancerById(id);
        if(dancer==null){
            throw new NoSuchDancerException("No such dancer with id = "+id);
        }
        return dancer;
    }

    @PostMapping("/dancers")
    public Dancer addDancer(@RequestBody Dancer dancer){
        service.addDancer(dancer);
        return dancer;
    }
    @PutMapping("/dancers")
    public Dancer updateDancer(@RequestBody Dancer dancer){
        service.addDancer(dancer);
        return dancer;
    }
    @DeleteMapping("/dancers/{id}")
    public String  deleteDancer(@PathVariable int id){
        service.deleteDancer(id);
        return "Dancer was deleted";
    }
}
