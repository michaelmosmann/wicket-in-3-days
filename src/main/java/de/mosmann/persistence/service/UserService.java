package de.mosmann.persistence.service;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import de.mosmann.persistence.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class UserService implements IUserService {
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
