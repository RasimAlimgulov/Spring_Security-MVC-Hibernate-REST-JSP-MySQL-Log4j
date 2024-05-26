package dancebase.springmvc.security.rest.dao;

import dancebase.springmvc.security.rest.entities.Dancer;
import dancebase.springmvc.security.rest.service.DancerService;
import dancebase.springmvc.security.rest.service.DancerServiceImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DancerDAOImpl implements DancerDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Dancer> getAllDancers() {
        Session session = sessionFactory.getCurrentSession();
        List<Dancer> dancers = session.createQuery("from Dancer ", Dancer.class).getResultList();
        return dancers;
    }

    @Override
    public Dancer getDancerById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Dancer dancer = session.get(Dancer.class, id);
        return dancer;
    }

    @Override
    public void addDancer(Dancer dancer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(dancer);
    }

    @Override
    public void deleteDancer(int id) {
        Session session = sessionFactory.getCurrentSession();
        Dancer dancer = session.get(Dancer.class, id);
        session.delete(dancer);
    }
}
