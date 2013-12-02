package de.mosmann.usermgmt;

import org.apache.wicket.model.IModel;

import de.mosmann.persistence.service.Dummy;
import de.mosmann.persistence.service.IDummyService;


public class Wrong implements IModel<Dummy> {

	IDummyService service;
	
	@Override
	public void detach() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Dummy getObject() {
		return service.newInstance();
	}

	@Override
	public void setObject(Dummy object) {
		
	}

}
