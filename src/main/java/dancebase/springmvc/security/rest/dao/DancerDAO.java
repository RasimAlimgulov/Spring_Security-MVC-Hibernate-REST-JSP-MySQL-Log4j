package dancebase.springmvc.security.rest.dao;

import dancebase.springmvc.security.rest.entities.Dancer;

import java.util.List;

public interface DancerDAO {
    List<Dancer> getAllDancers();
    Dancer getDancerById(int id);
    void addDancer(Dancer dancer);
    void deleteDancer(int id);
}
