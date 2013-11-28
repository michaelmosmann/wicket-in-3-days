package de.mosmann.persistence.config;

import java.util.Properties;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;


public class Jpa extends AbstractModule {

	@Override
	protected void configure() {
		install(new JpaPersistModule("one"));
	}
}
