package de.mosmann.usermgmt;

import org.apache.wicket.markup.html.panel.Panel;

import com.google.inject.Inject;

import de.mosmann.persistence.service.IDummyService;


public class NewUserPanel extends Panel {

	@Inject
	IDummyService dummyService;
	
	public NewUserPanel(String id) {
		super(id);
	}

}
