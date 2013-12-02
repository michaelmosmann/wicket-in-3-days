package de.mosmann.persistence.service;

import java.util.List;


public interface IDummyService {

	Dummy newInstance();
	
	Dummy byId(int id);
	
	Integer save(Dummy dummy);
	
	List<Dummy> asList();
}
