package de.mosmann.persistence.service;

import com.google.inject.ImplementedBy;

import de.mosmann.persistence.entity.User;

@ImplementedBy(JpaService.class)
public interface IService {
    public User getUser();
}
