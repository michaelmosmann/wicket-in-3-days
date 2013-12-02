package de.mosmann.persistence.service;

import java.util.concurrent.ConcurrentHashMap;


public class DummyService implements IDummyService {

	static ConcurrentHashMap<Integer, Dummy> map=new ConcurrentHashMap<>();
	static int counter=0;
	
	@Override
	public Dummy newInstance() {
		return new Dummy();
	}
	
	

	@Override
	public Integer save(Dummy dummy) {
		dummy.setId(counter++);
		map.put(dummy.getId(), dummy);
		return dummy.getId();
	}



	@Override
	public Dummy byId(int id) {
		return map.get(id);
	}

}
