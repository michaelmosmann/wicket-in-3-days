package de.mosmann.config;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

import de.mosmann.persistence.service.DummyService;
import de.mosmann.persistence.service.IDummyService;


public class DummyServiceModul extends AbstractModule {

	@Override
	protected void configure() {
		bind(IDummyService.class).to(DummyService.class).in(Scopes.SINGLETON);
	}

}
