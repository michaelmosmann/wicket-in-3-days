package de.mosmann.persistence.service;


public interface IDummyService {

	Dummy newInstance();
	
	Dummy byId(int id);
	
	Integer save(Dummy dummy);
}
