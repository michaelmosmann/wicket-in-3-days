package de.mosmann.config;

import com.google.inject.AbstractModule;

import de.mosmann.persistence.config.Jpa;
import de.mosmann.persistence.config.OpenSession;


public class Tests extends AbstractModule {

	@Override
	protected void configure() {
		install(new Jpa());
		install(new OpenSession());
		install(new DummyServiceModul());
	}

}
