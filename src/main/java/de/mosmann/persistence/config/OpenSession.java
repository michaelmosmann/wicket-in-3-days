package de.mosmann.persistence.config;

import com.google.inject.persist.PersistFilter;
import com.google.inject.servlet.ServletModule;


public class OpenSession extends ServletModule {

	@Override
	protected void configureServlets() {
		filter("/*").through(PersistFilter.class);
	}
}
