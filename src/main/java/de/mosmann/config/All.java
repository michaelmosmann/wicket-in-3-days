package de.mosmann.config;

import com.google.inject.AbstractModule;

import de.mosmann.persistence.config.Jpa;
import de.mosmann.persistence.config.OpenSession;
import de.mosmann.persistence.service.DummyService;
import de.mosmann.persistence.service.IDummyService;

public class All extends AbstractModule {

	@Override
	protected void configure() {
		install(new Jpa());
		install(new OpenSession());
		install(new Application());
		
		bind(IDummyService.class).to(DummyService.class);
	}

}
