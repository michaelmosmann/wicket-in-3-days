package de.mosmann.persistence.service;

import com.google.inject.ImplementedBy;

import de.mosmann.persistence.entity.User;

@ImplementedBy(UserService.class)
public interface IUserService {
    public User getUser();
}
