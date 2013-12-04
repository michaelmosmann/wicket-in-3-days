package de.mosmann.persistence.service;

import java.util.List;

import com.google.common.base.Optional;

import de.mosmann.loginmgmt.events.NewLogin;


public interface ILoginService {

	int create(NewLogin login);

	Login getById(int id);

	void save(Login login);

	List<Login> findAll();

	Optional<Login> getByName(String name);

}
