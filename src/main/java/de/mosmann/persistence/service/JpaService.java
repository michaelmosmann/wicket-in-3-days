package de.mosmann.persistence.service;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import de.mosmann.persistence.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class JpaService implements IService {
    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public User getUser() {
        Query q = em.createQuery("FROM User");
        q.setMaxResults(1);
        User u = (User) q.getSingleResult();
        return u;
    }
}
