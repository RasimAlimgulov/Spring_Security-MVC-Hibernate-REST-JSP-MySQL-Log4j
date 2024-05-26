package dancebase.springmvc.security.rest.service;

import dancebase.springmvc.security.rest.dao.DancerDAO;
import dancebase.springmvc.security.rest.entities.Dancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DancerServiceImpl implements DancerService{
    @Autowired
    private DancerDAO dancerDAO;

    @Transactional
    public List<Dancer> getAllDancers() {
        return dancerDAO.getAllDancers();
    }

    @Override
    @Transactional
    public Dancer getDancerById(int id) {
        return dancerDAO.getDancerById(id);
    }

    @Override
    @Transactional
    public void addDancer(Dancer dancer) {
    dancerDAO.addDancer(dancer);
    }

    @Override
    @Transactional
    public void deleteDancer(int id) {
    dancerDAO.deleteDancer(id);
    }
}
