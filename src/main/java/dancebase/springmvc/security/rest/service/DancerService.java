package dancebase.springmvc.security.rest.service;

import dancebase.springmvc.security.rest.entities.Dancer;

import java.util.List;

public interface DancerService {
    List<Dancer> getAllDancers();
    Dancer getDancerById(int id);
    void addDancer(Dancer dancer);
    void deleteDancer(int id);
}
